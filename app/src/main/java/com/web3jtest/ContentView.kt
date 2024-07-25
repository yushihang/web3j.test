package com.web3jtest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MainActivity.Web3jVersionNode() {
    val version by viewModel.web3jVersion.collectAsState()

    Button(onClick = {viewModel.updateWeb3jVersion()}) {
        Text("Get Web3j Version")
    }

    Text("Version: $version")
}

@Composable
fun MainActivity.Web3jNetVersionNode() {
    val version by viewModel.web3jNetVersion.collectAsState()

    Button(onClick = {viewModel.updateWeb3jNetVersion()}) {
        Text("Get Web3j Net Version")
    }

    Text("Net Version: $version")
}

@Composable
fun MainActivity.Web3jEthProtocolVersionNode() {
    val version by viewModel.web3jEthProtocolVersion.collectAsState()

    Button(onClick = {viewModel.updateWeb3jEthProtocolVersion()}) {
        Text("Get Web3j EthProtocol Version")
    }

    Text("EthProtocol Version: $version")
}

@Composable
fun MainActivity.Web3jShhVersionNode() {
    val version by viewModel.web3jShhVersion.collectAsState()

    Button(onClick = {viewModel.updateWeb3jShhVersion()}) {
        Text("Get Web3j Shh Version")
    }

    Text("Shh Version: $version")
}

@Composable
fun MainActivity.PeerCountNode() {
    val count by viewModel.peerCount.collectAsState()

    Button(onClick = {viewModel.updatePeerCount()}) {
        Text("Get Peer Count")
    }

    Text("Peer Count: $count")
}


@Composable
fun MainActivity.ContentView(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Web3jVersionNode()

        Web3jNetVersionNode()

        Web3jEthProtocolVersionNode()

        Web3jShhVersionNode()

        PeerCountNode()

    }
}

