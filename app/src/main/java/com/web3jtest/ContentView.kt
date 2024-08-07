package com.web3jtest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import java.math.BigInteger


@Composable
fun MainActivity.Web3jVersionNode() {
    val version by viewModel.getWeb3jVersion().collectAsState()

    Button(onClick = {viewModel.updateWeb3jVersion()}) {
        Text("Get Web3j Version")
    }

    Text("Version: $version")
}

@Composable
fun MainActivity.Web3jNetVersionNode() {
    val version by viewModel.getWeb3jNetVersion().collectAsState()

    Button(onClick = {viewModel.updateWeb3jNetVersion()}) {
        Text("Get Web3j Net Version")
    }

    Text("Net Version: $version")
}

@Composable
fun MainActivity.Web3jEthProtocolVersionNode() {
    val version by viewModel.getWeb3jEthProtocolVersion().collectAsState()

    Button(onClick = {viewModel.updateWeb3jEthProtocolVersion()}) {
        Text("Get Web3j EthProtocol Version")
    }

    Text("EthProtocol Version: $version")
}

@Composable
fun MainActivity.Web3jShhVersionNode() {
    val version by viewModel.getWeb3jShhVersion().collectAsState()

    Button(onClick = {viewModel.updateWeb3jShhVersion()}) {
        Text("Get Web3j Shh Version")
    }

    Text("Shh Version: $version")
}

@Composable
fun MainActivity.PeerCountNode() {
    val count by viewModel.getPeerCount().collectAsState()

    Button(onClick = {viewModel.updatePeerCount()}) {
        Text("Get Peer Count")
    }

    Text("Peer Count: $count")
}

@Composable
fun MainActivity.BalanceNode() {
    val balance by viewModel.getBalance().collectAsState()

    Button(onClick = {viewModel.updateBalance()}) {
        Text("Get Balance of Specified Account")
    }

    Text("Balance: $balance")
}

@Composable
fun MainActivity.SignAndSendTxNode() {
    val txHash by viewModel.getTxHash().collectAsState()

    Button(onClick = {viewModel.signAndSendTx(BigInteger("12345"))}) {
        Text("Sign & Send Tx")
    }

    Text("Tx Hash: $txHash")
}

@Composable
fun MainActivity.TxHashReceiptNode() {
    val receipt by viewModel.getTxHashReceipt().collectAsState()

    Button(onClick = {viewModel.updateTxHashReceipt()}) {
        Text("Tx Receipt")
    }

    Text("Tx Receipt: $receipt")
}

@Composable
fun MainActivity.CallContractViewFunctionNode() {
    val response by viewModel.getContractViewFunctionResponse().collectAsState()

    Button(onClick = {viewModel.callContractViewFunction()}) {
        Text("Call Contract View Function")
    }

    Text("Call Response: $response")
}

@Composable
fun MainActivity.CallContractViewFunctionNode2() {
    val response by viewModel.getContractViewFunctionResponse2().collectAsState()

    Button(onClick = {viewModel.callContractViewFunction2()}) {
        Text("Call Contract View Function 2")
    }

    Text("Call Response 2: $response")
}

@Composable
fun MainActivity.CallContractStateModifyFunctionNode() {
    val txHash by viewModel.getContractStateModifyFunctionTxHash().collectAsState()

    Button(onClick = {viewModel.callContractStateModifyFunction()}) {
        Text("Call Contract State Modify Function")
    }

    Text("Tx Hash: $txHash")
}

@Composable
fun MainActivity.ContractTxHashReceiptNode() {
    val receipt by viewModel.getContractStateModifyFunctionTxHashReceipt().collectAsState()

    Button(onClick = {viewModel.updateContractStateModifyFunctionTxHashReceipt()}) {
        Text("Contract Func Call Tx Receipt")
    }

    Text("Contract Func Call Tx Receipt: $receipt")
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

        BalanceNode()

        SignAndSendTxNode()

        TxHashReceiptNode()

        CallContractViewFunctionNode()

        CallContractViewFunctionNode2()

        CallContractStateModifyFunctionNode()

        ContractTxHashReceiptNode()

        Spacer(modifier = Modifier.height(30.dp))

    }
}

