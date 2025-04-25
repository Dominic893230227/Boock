package com.boock.controller;

import com.boock.service.impl.MyWechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/wx")
public class WxController {

    @Autowired
    private MyWechatService myWechatService;

    @GetMapping("/getOpenId")
    public Map<String,Object> getOpenId(){
        Map<String,Object> map = myWechatService.getAccessTokenFromService();
        return map;
    }
}
