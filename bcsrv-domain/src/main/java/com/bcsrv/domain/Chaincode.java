package com.bcsrv.domain;

import lombok.Data;

/**
 * @Auther: xinyanfei
 * @Date: 2018/5/23 15:52
 * @Description: 链码=智能合约
 */

@Data
public class Chaincode {
    /** 当前将要访问的智能合约所属频道名称 */
    private String channelName; //mychannel
    /** 智能合约名称 */
    private String chaincodeName; //mycc
    /** 智能合约安装路径 */
    private String chaincodePath; // /root/go/src/github.com/hyperledger/fabric/examples/chaincode/go/chaincode_example02
    private String chaincodeVersion;
    /** 执行智能合约操作等待时间 */
    private int invokeWaitTime=100000;
    /** 执行智能合约实例等待时间 */
    private int deployWaitTime=100000;
}
