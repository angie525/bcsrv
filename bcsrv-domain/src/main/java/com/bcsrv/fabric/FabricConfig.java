package com.bcsrv.fabric;

import com.bcsrv.domain.Chaincode;
import com.bcsrv.domain.Orderers;
import com.bcsrv.domain.Peers;
import lombok.Data;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * @Auther: xinyanfei
 * @Date: 2018/5/24 19:48
 * @Description:
 */
@Data
public class FabricConfig {

    private static Logger log = Logger.getLogger(FabricConfig.class);

    /** 节点服务器对象 */
    private Peers peers;
    /** 排序服务器对象 */
    private Orderers orderers;
    /** 智能合约对象 */
    private Chaincode chaincode;
    /** channel-artifacts所在路径：默认channel-artifacts所在路径/xxx/WEB-INF/classes/fabric/channel-artifacts/ */
    private String channelArtifactsPath;
    /** crypto-config所在路径：默认crypto-config所在路径/xxx/WEB-INF/classes/fabric/crypto-config/ */
    private String cryptoConfigPath;
    private boolean registerEvent = false;

    public FabricConfig(String rootPath){
        // 默认channel-artifacts所在路径 /xxx/WEB-INF/classes/fabric/channel-artifacts/
        channelArtifactsPath = getChannelPath(rootPath) + "/channel-artifacts/";
        // 默认crypto-config所在路径 /xxx/WEB-INF/classes/fabric/crypto-config/
        cryptoConfigPath = getChannelPath(rootPath) + "/crypto-config/";
    }

    private String getChannelPath(String rootPath) {
        //rootPath="/root/go/src/github.com/hyperledger/fabric";
        File directory = new File(rootPath);
        log.debug("directory = " + directory.getPath());
        return directory.getPath();
    }
}