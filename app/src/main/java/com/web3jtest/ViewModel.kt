package com.web3jtest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewModel : ViewModel() {
    private val _version = MutableStateFlow("None")
    val version: StateFlow<String> = _version

    fun updateVersion(version: String) {
        _version.value = version
    }
}