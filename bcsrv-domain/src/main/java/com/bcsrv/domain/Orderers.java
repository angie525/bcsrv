package com.bcsrv.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xinyanfei
 * @Date: 2018/5/23 15:50
 * @Description: 排序
 */
@Data
public class Orderers {
    /** orderer 排序服务器所在根域名 */
    private String ordererDomainName;
    /** orderer 排序服务器集合 */
    private List<Orderer> orderers;

    public Orderers(){
        orderers=new ArrayList<>();
    }
    /** 新增排序服务器 */
    public void addOrderer(String name,String location){
        orderers.add(new Orderer(name,location));
    }
    /** 获取排序服务器集合 */
    public List<Orderer> get() {
        return orderers;
    }

    /**
     * 排序服务器对象
     */
    @Data
    public class Orderer{
        /** orderer 排序服务器的域名 */
        private String ordererName;
        /** orderer 排序服务器的访问地址 */
        private String ordererLocation;

        public Orderer(String name,String location){
            this.ordererName=name;
            this.ordererLocation=location;
        }
    }
}
