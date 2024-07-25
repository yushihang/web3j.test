package com.web3jtest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.Request
import org.web3j.protocol.core.Response
import org.web3j.protocol.http.HttpService
import java.math.BigInteger

data class Web3jState(
    val web3jVersion: StateFlow<String> = MutableStateFlow("None"),
    val web3jNetVersion: StateFlow<String> = MutableStateFlow("None"),
    val web3jEthProtocolVersion: StateFlow<String> = MutableStateFlow("None"),
    val web3jShhVersion: StateFlow<String> = MutableStateFlow("None"),
    val peerCount: StateFlow<BigInteger> = MutableStateFlow(BigInteger("-1")),
    val balance: StateFlow<BigInteger> = MutableStateFlow(BigInteger("0"))
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


    private fun <T : Response<*>> handleRequest(
        request: Request<*, T>,
        onSuccess: (T) -> Unit
    ) {
        val future = request.sendAsync()
        future.thenAccept { result ->
            onSuccess(result)
        }.exceptionally { ex ->
            ex.printStackTrace()
            null
        }
    }

    // 调用示例
    fun updateWeb3jVersion() {
        handleRequest(web3j.web3ClientVersion()) { response ->
            println("Connected to Ethereum client version: ${response.web3ClientVersion}")
            (state.web3jVersion as MutableStateFlow).value = response.web3ClientVersion
        }
    }

    fun updateWeb3jNetVersion() {
        handleRequest(web3j.netVersion()) { response ->
            println("Network version: ${response.netVersion}")
            (state.web3jNetVersion as MutableStateFlow).value = response.netVersion
        }
    }

    fun updateWeb3jEthProtocolVersion() {
        handleRequest(web3j.ethProtocolVersion()) { response ->
            println("eth protocol version: ${response.result}")
            (state.web3jEthProtocolVersion as MutableStateFlow).value = response.result
        }
    }

    fun updateWeb3jShhVersion() {
        handleRequest(web3j.shhVersion()) { response ->
            println("Shh version: ${response.result}")
            (state.web3jShhVersion as MutableStateFlow).value = response.result
        }
    }

    fun updatePeerCount() {
        handleRequest(web3j.netPeerCount()) { response ->
            println("Peer count: ${response.quantity}")
            (state.peerCount as MutableStateFlow).value = response.quantity
        }
    }
}


