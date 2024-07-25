package com.web3jtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.Request
import org.web3j.protocol.core.Response
import org.web3j.protocol.http.HttpService
import java.math.BigInteger
import java.util.concurrent.CompletableFuture
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

data class Web3jState(
    val web3jVersion: StateFlow<String> = MutableStateFlow("None"),
    val web3jNetVersion: StateFlow<String> = MutableStateFlow("None"),
    val web3jEthProtocolVersion: StateFlow<String> = MutableStateFlow("None"),
    val web3jShhVersion: StateFlow<String> = MutableStateFlow("None"),
    val peerCount: StateFlow<BigInteger> = MutableStateFlow(BigInteger("-1")),
    val balance: StateFlow<BigInteger> = MutableStateFlow(BigInteger("0")),
    var txHash: StateFlow<String> = MutableStateFlow(("None"))
)

class ViewModel : ViewModel() {

    private val state = Web3jState()

    private val web3j = Web3j.build(HttpService("http://192.168.0.200:7545"))

    private val privateKey = "f653cbda28c5625ff9c2c9eb40b94dd846d6e68c4f7f8f3d32e5a10832f793f1"

    private val otherAddress = "0xC0B05B621Ab20123bfC52186708444c783351e69"
    val credentials = Credentials.create(privateKey)

    fun getWeb3jVersion(): StateFlow<String> = state.web3jVersion
    fun getWeb3jNetVersion(): StateFlow<String> = state.web3jNetVersion
    fun getWeb3jEthProtocolVersion(): StateFlow<String> = state.web3jEthProtocolVersion
    fun getWeb3jShhVersion(): StateFlow<String> = state.web3jShhVersion
    fun getPeerCount(): StateFlow<BigInteger> = state.peerCount
    fun getBalance(): StateFlow<BigInteger> = state.balance
    fun getTxHash(): StateFlow<String> = state.txHash




    private suspend fun <T : Response<*>> handleWeb3jRequest(
        request: Request<*, T>
    ): T = suspendCoroutine { continuation ->
        val future: CompletableFuture<T> = request.sendAsync()
        future.thenAccept { result ->
            continuation.resume(result)
        }.exceptionally { ex ->
            ex.printStackTrace()
            continuation.resumeWithException(ex)
            null
        }
    }

    fun updateWeb3jVersion() {
        viewModelScope.launch {
            try {
                val response = handleWeb3jRequest(web3j.web3ClientVersion())
                println("Connected to Ethereum client version: ${response.web3ClientVersion}")
                (state.web3jVersion as MutableStateFlow).value = response.web3ClientVersion
            } catch (e: Exception) {
                // 处理异常
                e.printStackTrace()
            }
        }
    }

    fun updateWeb3jNetVersion() {
        viewModelScope.launch {
            try {
                val response = handleWeb3jRequest(web3j.netVersion())
                println("Network version: ${response.netVersion}")
                (state.web3jNetVersion as MutableStateFlow).value = response.netVersion
            } catch (e: Exception) {
                // 处理异常
                e.printStackTrace()
            }
        }
    }

    fun updateWeb3jEthProtocolVersion() {
        viewModelScope.launch {
            try {
                val response = handleWeb3jRequest(web3j.ethProtocolVersion())
                println("eth protocol version: ${response.result}")
                (state.web3jEthProtocolVersion as MutableStateFlow).value = response.result
            } catch (e: Exception) {
                // 处理异常
                e.printStackTrace()
            }
        }
    }

    fun updateWeb3jShhVersion() {
        viewModelScope.launch {
            try {
                val response = handleWeb3jRequest(web3j.shhVersion())
                println("Shh version: ${response.result}")
                (state.web3jShhVersion as MutableStateFlow).value = response.result
            } catch (e: Exception) {
                // 处理异常
                e.printStackTrace()
            }
        }
    }

    fun updatePeerCount() {
        viewModelScope.launch {
            try {
                val response = handleWeb3jRequest(web3j.netPeerCount())
                println("Peer count: ${response.quantity}")
                (state.peerCount as MutableStateFlow).value = response.quantity
            } catch (e: Exception) {
                // 处理异常
                e.printStackTrace()
            }
        }
    }

    fun signAndSendTx(amount: BigInteger) {

    }
}


