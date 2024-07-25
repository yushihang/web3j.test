package com.web3jtest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.Web3ClientVersion
import org.web3j.protocol.http.HttpService
import java.util.concurrent.CompletableFuture

class ViewModel : ViewModel() {

    private val web3j = Web3j.build(HttpService("http://192.168.0.200:7545"))

    private val privateKey = "f653cbda28c5625ff9c2c9eb40b94dd846d6e68c4f7f8f3d32e5a10832f793f1"
    val credentials = Credentials.create(privateKey)

    private val _version = MutableStateFlow("None")
    val version: StateFlow<String> = _version

    fun updateVersion(version: String) {


        val web3ClientVersionFuture: CompletableFuture<Web3ClientVersion> = web3j.web3ClientVersion().sendAsync()

        // 处理异步响应
        web3ClientVersionFuture.thenAccept { web3ClientVersion ->
            println("Connected to Ethereum client version: ${web3ClientVersion.web3ClientVersion}")
            _version.value = web3ClientVersion.web3ClientVersion
        }.exceptionally { ex ->
            ex.printStackTrace()
            null
        }
    }
}