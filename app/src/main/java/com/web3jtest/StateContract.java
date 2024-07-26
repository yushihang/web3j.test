package com.web3jtest;
//npx hardhat compile
//curl -L get.web3j.io | sh && source ~/.web3j/source.sh
//web3j generate solidity -st -jt -a  abi.json -o . -p com.web3jtest
import com.google.gson.annotations.SerializedName;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes2;
import org.web3j.abi.datatypes.generated.StaticArray2;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.abi.datatypes.reflection.Parameterized;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.0.
 */
@SuppressWarnings("rawtypes")
public class StateContract extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_VERSION = "VERSION";

    public static final String FUNC_ACCEPTOWNERSHIP = "acceptOwnership";

    public static final String FUNC_GETDEFAULTIDTYPE = "getDefaultIdType";

    public static final String FUNC_GETGISTPROOF = "getGISTProof";

    public static final String FUNC_GETGISTPROOFBYBLOCK = "getGISTProofByBlock";

    public static final String FUNC_GETGISTPROOFBYROOT = "getGISTProofByRoot";

    public static final String FUNC_GETGISTPROOFBYTIME = "getGISTProofByTime";

    public static final String FUNC_GETGISTROOT = "getGISTRoot";

    public static final String FUNC_GETGISTROOTHISTORY = "getGISTRootHistory";

    public static final String FUNC_GETGISTROOTHISTORYLENGTH = "getGISTRootHistoryLength";

    public static final String FUNC_GETGISTROOTINFO = "getGISTRootInfo";

    public static final String FUNC_GETGISTROOTINFOBYBLOCK = "getGISTRootInfoByBlock";

    public static final String FUNC_GETGISTROOTINFOBYTIME = "getGISTRootInfoByTime";

    public static final String FUNC_GETSTATEINFOBYID = "getStateInfoById";

    public static final String FUNC_GETSTATEINFOBYIDANDSTATE = "getStateInfoByIdAndState";

    public static final String FUNC_GETSTATEINFOHISTORYBYID = "getStateInfoHistoryById";

    public static final String FUNC_GETSTATEINFOHISTORYLENGTHBYID = "getStateInfoHistoryLengthById";

    public static final String FUNC_GETVERIFIER = "getVerifier";

    public static final String FUNC_IDEXISTS = "idExists";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PENDINGOWNER = "pendingOwner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SETDEFAULTIDTYPE = "setDefaultIdType";

    public static final String FUNC_SETVERIFIER = "setVerifier";

    public static final String FUNC_STATEEXISTS = "stateExists";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_TRANSITSTATE = "transitState";

    public static final String FUNC_TRANSITSTATEGENERIC = "transitStateGeneric";

    public static final Event INITIALIZED_EVENT = new Event("Initialized", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERSTARTED_EVENT = new Event("OwnershipTransferStarted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected StateContract(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected StateContract(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected StateContract(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected StateContract(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<InitializedEventResponse> getInitializedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(INITIALIZED_EVENT, transactionReceipt);
        ArrayList<InitializedEventResponse> responses = new ArrayList<InitializedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InitializedEventResponse typedResponse = new InitializedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.version = (Uint64) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static InitializedEventResponse getInitializedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(INITIALIZED_EVENT, log);
        InitializedEventResponse typedResponse = new InitializedEventResponse();
        typedResponse.log = log;
        typedResponse.version = (Uint64) eventValues.getNonIndexedValues().get(0);
        return typedResponse;
    }

    public Flowable<InitializedEventResponse> initializedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getInitializedEventFromLog(log));
    }

    public Flowable<InitializedEventResponse> initializedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INITIALIZED_EVENT));
        return initializedEventFlowable(filter);
    }

    public static List<OwnershipTransferStartedEventResponse> getOwnershipTransferStartedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERSTARTED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferStartedEventResponse> responses = new ArrayList<OwnershipTransferStartedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferStartedEventResponse typedResponse = new OwnershipTransferStartedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.newOwner = (Address) eventValues.getIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnershipTransferStartedEventResponse getOwnershipTransferStartedEventFromLog(
            Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERSTARTED_EVENT, log);
        OwnershipTransferStartedEventResponse typedResponse = new OwnershipTransferStartedEventResponse();
        typedResponse.log = log;
        typedResponse.previousOwner = (Address) eventValues.getIndexedValues().get(0);
        typedResponse.newOwner = (Address) eventValues.getIndexedValues().get(1);
        return typedResponse;
    }

    public Flowable<OwnershipTransferStartedEventResponse> ownershipTransferStartedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnershipTransferStartedEventFromLog(log));
    }

    public Flowable<OwnershipTransferStartedEventResponse> ownershipTransferStartedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERSTARTED_EVENT));
        return ownershipTransferStartedEventFlowable(filter);
    }

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.newOwner = (Address) eventValues.getIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnershipTransferredEventResponse getOwnershipTransferredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
        OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
        typedResponse.log = log;
        typedResponse.previousOwner = (Address) eventValues.getIndexedValues().get(0);
        typedResponse.newOwner = (Address) eventValues.getIndexedValues().get(1);
        return typedResponse;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnershipTransferredEventFromLog(log));
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public RemoteFunctionCall<Utf8String> VERSION() {
        final Function function = new Function(FUNC_VERSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<TransactionReceipt> acceptOwnership() {
        final Function function = new Function(
                FUNC_ACCEPTOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Bytes2> getDefaultIdType() {
        final Function function = new Function(FUNC_GETDEFAULTIDTYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes2>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<GistProof> getGISTProof(Uint256 id) {
        final Function function = new Function(FUNC_GETGISTPROOF, 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<GistProof>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<GistProof> getGISTProofByBlock(Uint256 id, Uint256 blockNumber) {
        final Function function = new Function(FUNC_GETGISTPROOFBYBLOCK, 
                Arrays.<Type>asList(id, blockNumber), 
                Arrays.<TypeReference<?>>asList(new TypeReference<GistProof>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<GistProof> getGISTProofByRoot(Uint256 id, Uint256 root) {
        final Function function = new Function(FUNC_GETGISTPROOFBYROOT, 
                Arrays.<Type>asList(id, root), 
                Arrays.<TypeReference<?>>asList(new TypeReference<GistProof>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<GistProof> getGISTProofByTime(Uint256 id, Uint256 timestamp) {
        final Function function = new Function(FUNC_GETGISTPROOFBYTIME, 
                Arrays.<Type>asList(id, timestamp), 
                Arrays.<TypeReference<?>>asList(new TypeReference<GistProof>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> getGISTRoot() {
        final Function function = new Function(FUNC_GETGISTROOT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Type> getGISTRootHistory(Uint256 start, Uint256 length) {
        final Function function = new Function(FUNC_GETGISTROOTHISTORY, 
                Arrays.<Type>asList(start, length), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<GistRootInfo>>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> getGISTRootHistoryLength() {
        final Function function = new Function(FUNC_GETGISTROOTHISTORYLENGTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<GistRootInfo> getGISTRootInfo(Uint256 root) {
        final Function function = new Function(FUNC_GETGISTROOTINFO, 
                Arrays.<Type>asList(root), 
                Arrays.<TypeReference<?>>asList(new TypeReference<GistRootInfo>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<GistRootInfo> getGISTRootInfoByBlock(Uint256 blockNumber) {
        final Function function = new Function(FUNC_GETGISTROOTINFOBYBLOCK, 
                Arrays.<Type>asList(blockNumber), 
                Arrays.<TypeReference<?>>asList(new TypeReference<GistRootInfo>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<GistRootInfo> getGISTRootInfoByTime(Uint256 timestamp) {
        final Function function = new Function(FUNC_GETGISTROOTINFOBYTIME, 
                Arrays.<Type>asList(timestamp), 
                Arrays.<TypeReference<?>>asList(new TypeReference<GistRootInfo>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<StateInfo> getStateInfoById(Uint256 id) {
        final Function function = new Function(FUNC_GETSTATEINFOBYID, 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StateInfo>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<StateInfo> getStateInfoByIdAndState(Uint256 id, Uint256 state) {
        final Function function = new Function(FUNC_GETSTATEINFOBYIDANDSTATE, 
                Arrays.<Type>asList(id, state), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StateInfo>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Type> getStateInfoHistoryById(Uint256 id, Uint256 startIndex,
                                                            Uint256 length) {
        final Function function = new Function(FUNC_GETSTATEINFOHISTORYBYID, 
                Arrays.<Type>asList(id, startIndex, length), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<StateInfo>>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> getStateInfoHistoryLengthById(Uint256 id) {
        final Function function = new Function(FUNC_GETSTATEINFOHISTORYLENGTHBYID, 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Address> getVerifier() {
        final Function function = new Function(FUNC_GETVERIFIER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Bool> idExists(Uint256 id) {
        final Function function = new Function(FUNC_IDEXISTS, 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(Address verifierContractAddr,
            Bytes2 defaultIdType, Address owner) {
        final Function function = new Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(verifierContractAddr, defaultIdType, owner), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Address> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Address> pendingOwner() {
        final Function function = new Function(FUNC_PENDINGOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setDefaultIdType(Bytes2 defaultIdType) {
        final Function function = new Function(
                FUNC_SETDEFAULTIDTYPE, 
                Arrays.<Type>asList(defaultIdType), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setVerifier(Address newVerifierAddr) {
        final Function function = new Function(
                FUNC_SETVERIFIER, 
                Arrays.<Type>asList(newVerifierAddr), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Bool> stateExists(Uint256 id, Uint256 state) {
        final Function function = new Function(FUNC_STATEEXISTS, 
                Arrays.<Type>asList(id, state), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(Address newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(newOwner), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transitState(Uint256 id, Uint256 oldState,
            Uint256 newState, Bool isOldStateGenesis, StaticArray2<Uint256> a,
            StaticArray2<StaticArray2<Uint256>> b, StaticArray2<Uint256> c) {
        final Function function = new Function(
                FUNC_TRANSITSTATE, 
                Arrays.<Type>asList(id, oldState, newState, isOldStateGenesis, a, b, c), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transitStateGeneric(Uint256 id, Uint256 oldState,
            Uint256 newState, Bool isOldStateGenesis, Uint256 methodId, DynamicBytes methodParams) {
        final Function function = new Function(
                FUNC_TRANSITSTATEGENERIC, 
                Arrays.<Type>asList(id, oldState, newState, isOldStateGenesis, methodId, methodParams), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static StateContract load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new StateContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static StateContract load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new StateContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static StateContract load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new StateContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static StateContract load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new StateContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class GistProof extends DynamicStruct {
        public Uint256 root;

        public Bool existence;

        public DynamicArray<Uint256> siblings;

        public Uint256 index;


        public Uint256 value;

        public Bool auxExistence;

        public Uint256 auxIndex;

        public Uint256 auxValue;

        public GistProof(Uint256 root, Bool existence, @Parameterized(type = Uint256.class) DynamicArray<Uint256> siblings,
                Uint256 index, Uint256 value, Bool auxExistence, Uint256 auxIndex,
                Uint256 auxValue) {
            super(root, existence, siblings, index, value, auxExistence, auxIndex, auxValue);
            this.root = root;
            this.existence = existence;
            this.siblings = siblings;
            this.index = index;
            this.value = value;
            this.auxExistence = auxExistence;
            this.auxIndex = auxIndex;
            this.auxValue = auxValue;
        }
    }

    public static class GistRootInfo extends StaticStruct {
        public Uint256 root;

        public Uint256 replacedByRoot;

        public Uint256 createdAtTimestamp;

        public Uint256 replacedAtTimestamp;

        public Uint256 createdAtBlock;

        public Uint256 replacedAtBlock;

        public GistRootInfo(Uint256 root, Uint256 replacedByRoot, Uint256 createdAtTimestamp,
                Uint256 replacedAtTimestamp, Uint256 createdAtBlock, Uint256 replacedAtBlock) {
            super(root, replacedByRoot, createdAtTimestamp, replacedAtTimestamp, createdAtBlock, replacedAtBlock);
            this.root = root;
            this.replacedByRoot = replacedByRoot;
            this.createdAtTimestamp = createdAtTimestamp;
            this.replacedAtTimestamp = replacedAtTimestamp;
            this.createdAtBlock = createdAtBlock;
            this.replacedAtBlock = replacedAtBlock;
        }
    }

    public static class StateInfo extends StaticStruct {
        public Uint256 id;

        public Uint256 state;

        public Uint256 replacedByState;

        public Uint256 createdAtTimestamp;

        public Uint256 replacedAtTimestamp;

        public Uint256 createdAtBlock;

        public Uint256 replacedAtBlock;

        public StateInfo(Uint256 id, Uint256 state, Uint256 replacedByState,
                Uint256 createdAtTimestamp, Uint256 replacedAtTimestamp, Uint256 createdAtBlock,
                Uint256 replacedAtBlock) {
            super(id, state, replacedByState, createdAtTimestamp, replacedAtTimestamp, createdAtBlock, replacedAtBlock);
            this.id = id;
            this.state = state;
            this.replacedByState = replacedByState;
            this.createdAtTimestamp = createdAtTimestamp;
            this.replacedAtTimestamp = replacedAtTimestamp;
            this.createdAtBlock = createdAtBlock;
            this.replacedAtBlock = replacedAtBlock;
        }
    }

    public static class InitializedEventResponse extends BaseEventResponse {
        public Uint64 version;
    }

    public static class OwnershipTransferStartedEventResponse extends BaseEventResponse {
        public Address previousOwner;

        public Address newOwner;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public Address previousOwner;

        public Address newOwner;
    }
}
