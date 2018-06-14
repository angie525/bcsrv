package com.bcsrv;

import com.bcsrv.domain.Chaincode;
import com.bcsrv.domain.Orderers;
import com.bcsrv.domain.Peers;
import com.bcsrv.fabric.FabricConfig;
import com.bcsrv.manager.ChaincodeManager;
import com.bcsrv.manager.FabricManager;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.TransactionException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class App 
{
    private ChaincodeManager manager;
    String directorys = "D:\\workspaces\\blockchain\\bcsrv\\bcsrv-manager\\src\\resources\\fabric";

    private static App instance = null;

    public static App obtain()
            throws CryptoException, InvalidArgumentException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, TransactionException, IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (null == instance) {
            synchronized (FabricManager.class) {
                if (null == instance) {
                    instance = new App();
                }
            }
        }
        return instance;
    }

    private App()
            throws CryptoException, InvalidArgumentException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, TransactionException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        manager = new ChaincodeManager(getConfig());
    }

    /**
     * 获取节点服务器管理器
     *
     * @return 节点服务器管理器
     */
    public ChaincodeManager getManager() {
        return manager;
    }

    /**
     * 根据节点作用类型获取节点服务器配
     *            服务器作用类型（1、执行；2、查询）
     * @return 节点服务器配置
     */
    private FabricConfig getConfig() {
        FabricConfig config = new FabricConfig(directorys);
        config.setOrderers(getOrderers());
        config.setPeers(getPeers());
        config.setChaincode(getChaincode("mychannel", "mycc",
                "/root/go/src/github.com/hyperledger/fabric/rain/chaincode/go/chaincode_example02","1.0"));
//        config.setChannelArtifactsPath(getChannleArtifactsPath());
//        config.setCryptoConfigPath(getCryptoConfigPath());
        return config;
    }

    private Orderers getOrderers() {
        Orderers orderer = new Orderers();
        orderer.setOrdererDomainName("example.com");
        orderer.addOrderer("orderer.example.com", "grpc://116.196.64.244:7050");
        return orderer;
    }

    /**
     * 获取节点服务器集
     *d
     * @return 节点服务器集
     */
    private Peers getPeers() {
        Peers peers = new Peers();
        peers.setOrgName("Org1");
        peers.setOrgMSPID("Org1MSP");
        peers.setOrgDomainName("org1.example.com");
        peers.addPeer("peer0.org1.example.com", "peer0.org1.example.com", "grpc://116.196.64.244:7051",
                "grpc://116.196.64.244:7053", "grpc://116.196.64.244:7054");
//        peers.addPeer("peer0.org2.example.com", "peer0.org2.example.com", "grpc://116.196.125.231:9051",
//                "grpc://116.196.125.231:9053", "http://116.196.125.231:9054");
        return peers;
    }

    /**
     * 获取智能合约
     *
     * @param channelName
     *            频道名称
     * @param chaincodeName
     *            智能合约名称
     * @param chaincodePath
     *            智能合约路径
     * @param chaincodeVersion
     *            智能合约版本
     * @return 智能合约
     */
    private Chaincode getChaincode(String channelName, String chaincodeName, String chaincodePath, String chaincodeVersion) {
        Chaincode chaincode = new Chaincode();
        chaincode.setChannelName(channelName);
        chaincode.setChaincodeName(chaincodeName);
        chaincode.setChaincodePath(chaincodePath);
        chaincode.setChaincodeVersion(chaincodeVersion);
        chaincode.setInvokeWaitTime(10);
        chaincode.setDeployWaitTime(12);
        return chaincode;
    }

    /**
     * 获取channel-artifacts配置路径
     *
     * @return /WEB-INF/classes/fabric/channel-artifacts/
     */
//    private String getChannleArtifactsPath() {
//        //  String directorys = FabricManager.class.getClassLoader().getResource("fabric").getFile();
//        System.out.println("directorys = " + directorys);
//        File directory = new File(directorys);
//        System.out.println("directory = " + directory.getPath());
//        return directory.getPath() + "/channel-artifacts/";
//    }

    /**
     * 获取crypto-config配置路径
     *
     * @return /WEB-INF/classes/fabric/crypto-config/
     */
//    private String getCryptoConfigPath() {
//        System.out.println("directorys = " + directorys);
//        File directory = new File(directorys);
//        System.out.println("directory = " + directory.getPath());
//        return directory.getPath() + "/crypto-config/";
//    }

    public static void main(String[] a){
        queryA();
//        queryB();
//        invoke();
//        queryA();
//        queryB();
//        queryHistory();
    }

    public static void queryA(){
        try{
            ChaincodeManager manage = App.obtain().getManager();
            Map<String ,String> queryResult=manage.invoke("query",new String[] {"a"});
            System.out.println(String.valueOf(queryResult));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public static void queryB(){
        try{
            ChaincodeManager manage = App.obtain().getManager();
            Map<String ,String> queryResult=manage.invoke("query",new String[] {"b"});
            System.out.println(String.valueOf(queryResult));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public static void invoke(){
        try{
            ChaincodeManager manage = App.obtain().getManager();
            Map<String, String> invokeResult = manage.invoke("invoke",new String[] {"a", "b", "1"});
            System.out.println(String.valueOf(invokeResult));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void queryHistory(){
        try{
            ChaincodeManager manage = App.obtain().getManager();
            Map<String ,String> queryResult=manage.invoke("queryHistoryTransactionByKey",new String[] {"a"});
            System.out.println(String.valueOf(queryResult));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}