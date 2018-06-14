package com.blockchain.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {

    //@Autowired
    @Resource(name = "configIP")
    String configIP;

    @Resource(name = "jingbeanConfig")
    HashMap<String,String> map;

    @RequestMapping("index")
    public void init(){
        System.out.println(configIP );
        System.out.println(map );
        if(map!=null)
            System.out.println(map.get("key"));
        System.out.println("欢迎使用spring");
    }
}
