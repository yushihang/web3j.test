package com.web3jtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONArray
import org.json.JSONObject
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Bool
import org.web3j.abi.datatypes.DynamicArray
import org.web3j.abi.datatypes.DynamicBytes
import org.web3j.abi.datatypes.Function
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.crypto.Credentials
import org.web3j.crypto.RawTransaction
import org.web3j.crypto.TransactionEncoder
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.RemoteFunctionCall
import org.web3j.protocol.core.Request
import org.web3j.protocol.core.Response
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.http.HttpService
import org.web3j.tx.gas.DefaultGasProvider
import org.web3j.utils.Numeric
import java.math.BigInteger
import java.security.SecureRandom
import java.util.concurrent.CompletableFuture
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


data class Web3jState(
    val web3jVersion: StateFlow<String> = MutableStateFlow("None"),
    val web3jNetVersion: StateFlow<String> = MutableStateFlow("None"),
    val web3jEthProtocolVersion: StateFlow<String> = MutableStateFlow("None"),
    val web3jShhVersion: StateFlow<String> = MutableStateFlow("None"),
    val peerCount: StateFlow<BigInteger> = MutableStateFlow(BigInteger("-1")),
    val balance: StateFlow<BigInteger> = MutableStateFlow(BigInteger("0")),
    var txHash: StateFlow<String> = MutableStateFlow(("None")),
    var txHashReceipt: StateFlow<String> = MutableStateFlow(("None")),
    var contractViewFunctionResponse: StateFlow<String> = MutableStateFlow(("None")),
    var contractViewFunctionResponse2: StateFlow<String> = MutableStateFlow(("None")),
    var contractStateModifyFunctionTxHash: StateFlow<String> = MutableStateFlow(("None")),
    var contractStateModifyFunctionTxHashReceipt: StateFlow<String> = MutableStateFlow(("None")),
)

class ViewModel : ViewModel() {

    private val state = Web3jState()

    private val web3j = Web3j.build(HttpService("http://192.168.0.200:7545"))

    private val privateKey = "f653cbda28c5625ff9c2c9eb40b94dd846d6e68c4f7f8f3d32e5a10832f793f1"

    private val otherAddress = "0xC0B05B621Ab20123bfC52186708444c783351e69"
    private val credentials: Credentials = Credentials.create(privateKey)

    private val contractAddressHex = "0xdc5ECDd72a4201D379a976a118a359a02637D30f"

    private val stateContract : StateContract = StateContract.load(contractAddressHex, web3j, credentials,
        DefaultGasProvider())

    fun getWeb3jVersion(): StateFlow<String> = state.web3jVersion
    fun getWeb3jNetVersion(): StateFlow<String> = state.web3jNetVersion
    fun getWeb3jEthProtocolVersion(): StateFlow<String> = state.web3jEthProtocolVersion
    fun getWeb3jShhVersion(): StateFlow<String> = state.web3jShhVersion
    fun getPeerCount(): StateFlow<BigInteger> = state.peerCount
    fun getBalance(): StateFlow<BigInteger> = state.balance
    fun getTxHash(): StateFlow<String> = state.txHash
    fun getTxHashReceipt(): StateFlow<String> = state.txHashReceipt
    fun getContractViewFunctionResponse(): StateFlow<String> = state.contractViewFunctionResponse
    fun getContractViewFunctionResponse2(): StateFlow<String> = state.contractViewFunctionResponse2
    fun getContractStateModifyFunctionTxHash(): StateFlow<String> = state.contractStateModifyFunctionTxHash
    fun getContractStateModifyFunctionTxHashReceipt(): StateFlow<String> = state.contractStateModifyFunctionTxHashReceipt

    private suspend fun <T> handleContractCall(
        remoteFunctionCall: RemoteFunctionCall<T>
    ): T = suspendCancellableCoroutine { continuation ->
        val future = remoteFunctionCall.sendAsync()
        future.thenAccept { result ->
            continuation.resume(result)
        }.exceptionally { ex ->
            continuation.resumeWithException(ex)
            null
        }

        continuation.invokeOnCancellation {
            future.cancel(true)
        }
    }

    private suspend fun <T : Response<*>> handleWeb3jRequest(
        request: Request<*, T>
    ): T = suspendCancellableCoroutine { continuation ->
        val future: CompletableFuture<T> = request.sendAsync()
        future.thenAccept { result ->
            continuation.resume(result)
        }.exceptionally { ex ->
            ex.printStackTrace()
            continuation.resumeWithException(ex)
            null
        }

        continuation.invokeOnCancellation {
            future.cancel(true)
        }
    }

    fun updateWeb3jVersion() {
        viewModelScope.launch {
            try {
                val web3ClientVersion = handleWeb3jRequest(web3j.web3ClientVersion()).web3ClientVersion
                println("Connected to Ethereum client version: $web3ClientVersion")
                (state.web3jVersion as MutableStateFlow).value = web3ClientVersion
            } catch (e: Exception) {
                 e.printStackTrace()
            }
        }
    }

    fun updateWeb3jNetVersion() {
        viewModelScope.launch {
            try {
                val netVersion = handleWeb3jRequest(web3j.netVersion()).netVersion
                println("Network version: $netVersion")
                (state.web3jNetVersion as MutableStateFlow).value = netVersion
            } catch (e: Exception) {
                 e.printStackTrace()
            }
        }
    }

    fun updateWeb3jEthProtocolVersion() {
        viewModelScope.launch {
            try {
                val protocolVersion = handleWeb3jRequest(web3j.ethProtocolVersion()).protocolVersion
                println("eth protocol version: $protocolVersion")
                (state.web3jEthProtocolVersion as MutableStateFlow).value = protocolVersion
            } catch (e: Exception) {
                 e.printStackTrace()
            }
        }
    }

    fun updateWeb3jShhVersion() {
        viewModelScope.launch {
            try {
                val version = handleWeb3jRequest(web3j.shhVersion()).version
                println("Shh version: $version")
                (state.web3jShhVersion as MutableStateFlow).value = version
            } catch (e: Exception) {
                 e.printStackTrace()
            }
        }
    }

    fun updatePeerCount() {
        viewModelScope.launch {
            try {
                val quantity = handleWeb3jRequest(web3j.netPeerCount()).quantity
                println("Peer count: $quantity")
                (state.peerCount as MutableStateFlow).value = quantity
            } catch (e: Exception) {
                 e.printStackTrace()
            }
        }
    }

    fun updateBalance() {
        viewModelScope.launch {
            try {
                val balance = handleWeb3jRequest(web3j.ethGetBalance(otherAddress, DefaultBlockParameterName.LATEST)).balance
                println("Balance: $balance")
                (state.balance as MutableStateFlow).value = balance
            } catch (e: Exception) {
                 e.printStackTrace()
            }
        }
    }

    fun signAndSendTx(value: BigInteger) {
        viewModelScope.launch {
            try {
                val nonce = handleWeb3jRequest(web3j.ethGetTransactionCount(credentials.address, DefaultBlockParameterName.LATEST)).transactionCount
                println("nonce: $nonce")
                val gasPrice = handleWeb3jRequest(web3j.ethGasPrice()).gasPrice
                println("gasPrice: $gasPrice")

                val gasLimit = BigInteger.valueOf(21000)

                val rawTransaction = RawTransaction.createTransaction(
                    nonce,
                    gasPrice,
                    gasLimit,
                    otherAddress,
                    value,
                    ""
                )

                val signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials)

                val hexValue = Numeric.toHexString(signedMessage)

                val transactionHash = handleWeb3jRequest(web3j.ethSendRawTransaction(hexValue)).transactionHash

                (state.txHash as MutableStateFlow).value = transactionHash

                updateBalance()
                updateTxHashReceipt()
            } catch (e: Exception) {
                 e.printStackTrace()
            }
        }
    }

    fun updateTxHashReceipt() {
        viewModelScope.launch {
            try {
                val transactionReceipt = handleWeb3jRequest(web3j.ethGetTransactionReceipt(state.txHash.value)).transactionReceipt.get()
                val gson = GsonBuilder().setPrettyPrinting().create()
                val json = gson.toJson(transactionReceipt)
                println("transactionReceipt: $json")
                (state.txHashReceipt as MutableStateFlow).value = json

            } catch (e: Exception) {
                 e.printStackTrace()
            }
        }
    }

    fun callContractViewFunction() {
        viewModelScope.launch {
            try {

                val gistProof = handleContractCall(stateContract.getGISTProof(Uint256(123)))
                val gistProofStr = gistProof.toPrettyString()
                println("response: $gistProofStr")
                (state.contractViewFunctionResponse as MutableStateFlow).value = gistProofStr
            } catch (e: Exception) {
                 e.printStackTrace()
            }
        }
    }

    fun callContractViewFunction2() {
        viewModelScope.launch {
            try {

                val id = Uint256(BigInteger.valueOf(123))

                val function = Function(
                    "getGISTProof",
                    listOf(id),
                    listOf(
                        object : TypeReference<Uint256>() {},
                        object : TypeReference<Bool>() {},
                        object : TypeReference<DynamicArray<Uint256>>() {},
                        object : TypeReference<Uint256>() {},
                        object : TypeReference<Uint256>() {},
                        object : TypeReference<Bool>() {},
                        object : TypeReference<Uint256>() {},
                        object : TypeReference<Uint256>() {}
                    )
                )

                val encodedFunction = FunctionEncoder.encode(function)

                val transaction = Transaction.createEthCallTransaction(credentials.address, contractAddressHex, encodedFunction)

                val ethCall = handleWeb3jRequest(web3j.ethCall(transaction, DefaultBlockParameterName.LATEST))

                val result = ethCall.result
                val decodedResult = FunctionReturnDecoder.decode(result, function.outputParameters)

                // 解析返回值
                val root = decodedResult[0].value as BigInteger
                val existence = decodedResult[1].value as Boolean
                val siblings = (decodedResult[2].value as List<*>).map { it as BigInteger }
                val index = decodedResult[3].value as BigInteger
                val value = decodedResult[4].value as BigInteger
                val auxExistence = decodedResult[5].value as Boolean
                val auxIndex = decodedResult[6].value as BigInteger
                val auxValue = decodedResult[7].value as BigInteger

                // 创建 JSON 对象
                val json = JSONObject()
                json.put("root", root.toString())
                json.put("existence", existence)
                json.put("siblings", JSONArray(siblings.map { it.toString() }))
                json.put("index", index.toString())
                json.put("value", value.toString())
                json.put("auxExistence", auxExistence)
                json.put("auxIndex", auxIndex.toString())
                json.put("auxValue", auxValue.toString())

                val gistProofStr = json.toString(4)

                println("response: $gistProofStr")
                (state.contractViewFunctionResponse2 as MutableStateFlow).value = gistProofStr
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun callContractStateModifyFunction() {


        viewModelScope.launch {
            try {
                /* use abi function
                val txHash = handleContractCall(
                    stateContract.transitStateGeneric(
                        Uint256(generateRandomBigInteger(200)),
                        Uint256(0),
                        Uint256(2),
                        Bool(true),
                        Uint256(1),
                        DynamicBytes.DEFAULT
                    )
                ).transactionHash
                println("txHash: $txHash")
                (state.contractStateModifyFunctionTxHash as MutableStateFlow).value = txHash
                 */


                val func = Function("transitStateGeneric",
                    listOf(
                        Uint256(generateRandomBigInteger(200)),
                        Uint256(0),
                        Uint256(2),
                        Bool(true),
                        Uint256(1),
                        DynamicBytes.DEFAULT
                    ),
                    emptyList()
                )

                val encodedFunc = FunctionEncoder.encode(func)

                val nonce = handleWeb3jRequest(web3j.ethGetTransactionCount(credentials.address, DefaultBlockParameterName.LATEST)).transactionCount
                println("nonce: $nonce")
                val gasPrice = handleWeb3jRequest(web3j.ethGasPrice()).gasPrice
                println("gasPrice: $gasPrice")

                val gasLimit = BigInteger.valueOf(210000)

                val value = BigInteger.ZERO //we just call contract function, not sending ETH
                val rawTransaction = RawTransaction.createTransaction(
                    nonce, gasPrice, gasLimit, contractAddressHex, value, encodedFunc
                )

                val signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials)

                val hexValue = Numeric.toHexString(signedMessage)

                val transactionHash = handleWeb3jRequest(web3j.ethSendRawTransaction(hexValue)).transactionHash

                (state.txHash as MutableStateFlow).value = transactionHash

                (state.contractStateModifyFunctionTxHash as MutableStateFlow).value = transactionHash

                updateContractStateModifyFunctionTxHashReceipt()

            } catch (e: Exception) {
                 e.printStackTrace()
            }
        }
    }

    fun updateContractStateModifyFunctionTxHashReceipt() {
        viewModelScope.launch {
            try {
                val transactionReceipt = handleWeb3jRequest(web3j.ethGetTransactionReceipt(state.contractStateModifyFunctionTxHash.value)).transactionReceipt.get()
                val gson = GsonBuilder().setPrettyPrinting().create()
                val json = gson.toJson(transactionReceipt)
                println("ContractStateModifyFunctionTxHashReceipt: $json")
                (state.contractStateModifyFunctionTxHashReceipt as MutableStateFlow).value = json

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun generateRandomBigInteger(maxBitLength: Int): BigInteger {
        val secureRandom = SecureRandom()
        val bitLength = secureRandom.nextInt(maxBitLength) + 1
        return BigInteger(bitLength, secureRandom)
    }

}

fun StateContract.GistProof.toPrettyString(): String {
    return buildString {
        append("GistProof(\n")
        append("    root: ${root.value},\n")
        append("    existence: ${existence.value},\n")
        append("    siblings: [\n")
        for (item in siblings.value) {
            append("        ${item}\n")
        }
        append("    ],\n")
        append("    index: ${index.value},\n")
        append("    value: ${value.value},\n")
        append("    auxExistence: ${auxExistence.value},\n")
        append("    auxIndex: ${auxIndex.value},\n")
        append("    auxValue: ${auxValue.value}\n")
        append(")")
    }
}
