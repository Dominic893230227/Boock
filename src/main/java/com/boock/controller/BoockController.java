package com.boock.controller;

import com.boock.entity.po.Boock;
import com.boock.entity.po.Comment;
import com.boock.entity.vo.BoockVo;
import com.boock.service.BoockService;
import com.boock.service.UserLevelService;
import com.boock.service.UserService;
import com.boock.util.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/boock")
public class BoockController {
    @Autowired
    private BoockService boockService;

    @Autowired
    private UserLevelService userLevelService;
    @PostMapping("/submit")
    public HashMap<String, Object> submitBoock(@RequestBody Boock boock){
        //截取出来 然后保存文件 换成url
        String content = boock.getContent();
        String content1 = Base64Utils.convertBase64ToURL(content);
        boock.setContent(content1);
        boockService.saveBoock(boock);
        HashMap<String, Object> map = userLevelService.add50Exp(boock.getUserId());
//        Base64Utils.convertBase64ToImage();
        return map;
    }

    @GetMapping("/getAllBoock")
    public List<BoockVo> getAllBoock(){
        List<BoockVo> boockVos = boockService.getAllBoock();
//        System.out.println(boockVos.toString());
        return boockVos;
    }

    @GetMapping("/getMyBoock")
    public List<BoockVo> getMyBoock(Integer userId){
        List<BoockVo> boockVos = boockService.getMyBoock(userId);
        return boockVos;
    }

    @PostMapping("/addComment")
    public HashMap<String, Object> addComment(@RequestBody Comment comment){
        Integer result = boockService.addComment(comment);
        HashMap<String, Object> map = userLevelService.add20Exp(comment.getUserId());
        return map;
    }

    @GetMapping("/search")
    public Map<String,Object> search(@RequestParam("search") String param){
        Map<String, Object> result = boockService.search(param);
        return result;
    }
}

