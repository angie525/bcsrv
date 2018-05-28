package com.bcsrv.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xinyanfei
 * @Date: 2018/5/23 15:50
 * @Description: 节点
 */

@Data
public class Peers {
    /** 当前指定的组织名称 */
    private String orgName; // Org1
    /** 当前指定的组织成员服务提供者ID */
    private String orgMSPID; // Org1MSP
    /** 当前指定的组织所在根域名 */
    private String orgDomainName; //org1.example.com
    /** orderer 排序服务器集合 */
    private List<Peer> peers;

    public Peers() {
        peers = new ArrayList<>();
    }

    public void addPeer(String peerName,String peerEventHubName,String peerLocation,String peerEventHubLocation,String caLocation){
        peers.add(new Peer(peerName,peerEventHubName,peerLocation,peerEventHubLocation,caLocation));
    }

    public  List<Peer> get(){
        return this.peers;
    }

    /**
     * 节点服务器对象
     */
    @Data
    public class Peer{
        /** 当前指定的组织节点域名 */
        private String peerName; // peer0.org1.example.com
        /** 当前指定的组织节点事件域名 */
        private String peerEventHubName; // peer0.org1.example.com
        /** 当前指定的组织节点访问地址 */
        private String peerLocation; // grpc://110.131.116.21:7051
        /** 当前指定的组织节点事件监听访问地址 */
        private String peerEventHubLocation; // grpc://110.131.116.21:7053
        /** 当前指定的组织节点ca访问地址 */
        private String caLocation; // http://110.131.116.21:7054
        /** 当前peer是否增加Event事件处理 */
        private boolean addEventHub = true;

        public Peer(String peerName, String peerEventHubName, String peerLocation, String peerEventHubLocation, String caLocation) {
            this.peerName = peerName;
            this.peerEventHubName = peerEventHubName;
            this.peerLocation = peerLocation;
            this.peerEventHubLocation = peerEventHubLocation;
            this.caLocation = caLocation;
        }
    }
}
