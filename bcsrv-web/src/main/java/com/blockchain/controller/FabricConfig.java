package com.blockchain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xinyanfei
 * @Date: 2018/6/7 15:04
 * @Description:
 */
@Controller
@RequestMapping("/")
public class FabricConfig {

    @Resource(name="list")
    private List<Map<String,String>> list;
    @RequestMapping("test")
    public void init(){
        System.out.println(list);
    }
}
