package com.blockchain.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("index")
    public void init(){
        System.out.println("欢迎使用spring");
    }
}
