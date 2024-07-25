package com.web3jtest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.EthProtocolVersion
import org.web3j.protocol.core.methods.response.NetVersion
import org.web3j.protocol.core.methods.response.Web3ClientVersion
import org.web3j.protocol.http.HttpService
import java.util.concurrent.CompletableFuture

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

    fun updateWeb3jVersion() {
        val web3ClientVersionFuture: CompletableFuture<Web3ClientVersion> = web3j.web3ClientVersion().sendAsync()
        // 处理异步响应
        web3ClientVersionFuture.thenAccept { web3ClientVersion ->
            println("Connected to Ethereum client version: ${web3ClientVersion.web3ClientVersion}")
            _web3jVersion.value = web3ClientVersion.web3ClientVersion
        }.exceptionally { ex ->
            ex.printStackTrace()
            null
        }
    }

    fun updateWeb3jNetVersion() {
        val web3NetVersionFuture: CompletableFuture<NetVersion> = web3j.netVersion().sendAsync()
        // 处理异步响应
        web3NetVersionFuture.thenAccept { web3NetVersion ->
            println("{web3ClientVersion.netVersion: ${web3NetVersion.netVersion}")
            _web3jNetVersion.value = web3NetVersion.netVersion
        }.exceptionally { ex ->
            ex.printStackTrace()
            null
        }
    }

    fun updateWeb3jEthProtocolVersion() {
        val web3jEthProtocolVersionFuture: CompletableFuture<EthProtocolVersion> = web3j.ethProtocolVersion().sendAsync()
        // 处理异步响应
        web3jEthProtocolVersionFuture.thenAccept { web3jEthProtocolVersion ->
            println("{web3ClientVersion.netVersion: ${web3jEthProtocolVersion.protocolVersion}")
            _web3jEthProtocolVersion.value = web3jEthProtocolVersion.protocolVersion
        }.exceptionally { ex ->
            ex.printStackTrace()
            null
        }
    }
}