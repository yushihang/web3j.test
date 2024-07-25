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

class ViewModel : ViewModel() {

    private val web3j = Web3j.build(HttpService("http://192.168.0.200:7545"))

    private val privateKey = "f653cbda28c5625ff9c2c9eb40b94dd846d6e68c4f7f8f3d32e5a10832f793f1"
    val credentials = Credentials.create(privateKey)

    private val _web3jVersion = MutableStateFlow("None")
    val web3jVersion: StateFlow<String> = _web3jVersion

    private val _web3jNetVersion = MutableStateFlow("None")
    val web3jNetVersion: StateFlow<String> = _web3jNetVersion

    private val _web3jEthProtocolVersion = MutableStateFlow("None")
    val web3jEthProtocolVersion: StateFlow<String> = _web3jEthProtocolVersion

    private val _web3jShhVersion = MutableStateFlow("None")
    val web3jShhVersion: StateFlow<String> = _web3jShhVersion

    private val _peerCount = MutableStateFlow(BigInteger("-1"))
    val peerCount: StateFlow<BigInteger> = _peerCount


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
            _web3jVersion.value = response.web3ClientVersion
        }
    }

    fun updateWeb3jNetVersion() {
        handleRequest(web3j.netVersion()) { response ->
            println("Network version: ${response.netVersion}")
            _web3jNetVersion.value = response.netVersion
        }
    }

    fun updateWeb3jEthProtocolVersion() {
        handleRequest(web3j.ethProtocolVersion()) { response ->
            println("eth protocol version: ${response.result}")
            _web3jEthProtocolVersion.value = response.result
        }
    }

    fun updateWeb3jShhVersion() {
        handleRequest(web3j.shhVersion()) { response ->
            println("Shh version: ${response.result}")
            _web3jShhVersion.value = response.result
        }
    }

    fun updatePeerCount() {
        handleRequest(web3j.netPeerCount()) { response ->
            println("Peer count: ${response.quantity}")
            _peerCount.value = response.quantity
        }
    }
}


