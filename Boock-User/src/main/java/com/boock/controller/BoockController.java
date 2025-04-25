package com.boock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boock.entity.jpa.BoockFileRepository;
import com.boock.entity.po.Boock;
import com.boock.entity.po.BoockFile;
import com.boock.entity.po.Comment;
import com.boock.entity.vo.BoockVo;
import com.boock.service.BoockService;
import com.boock.service.UserLevelService;
import com.boock.util.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/boock")
public class BoockController {
    @Autowired
    private BoockService boockService;

    @Autowired
    private BoockFileRepository boockFileRepository;

    private static final String windowsBoockPath = "D:/SOFTWARE/CODE/uploadFile/boock/";

    @Autowired
    private UserLevelService userLevelService;
    @PostMapping("/submit")
    public HashMap<String, Object> submitBoock(@RequestParam(value = "files",required = false)  List<MultipartFile>  files, @RequestPart(value = "Boock")  String boock){
        //截取出来 然后保存文件 换成url
        Boock boock1 = JSON.parseObject(boock, Boock.class);
        String content = boock1.getContent();
        String content1 = Base64Utils.convertBase64ToURL(content);
        boock1.setContent(content1);
        boockService.saveBoock(boock1);
        HashMap<String, Object> map = userLevelService.add50Exp(boock1.getUserId());

        try {
            File uploadDir = new File(windowsBoockPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            if (files != null && !files.isEmpty()) {
                for (int i = 0; i < files.size(); i++) {
                    MultipartFile file = files.get(i);
                    if (file.isEmpty()) continue;
                    String newFilename = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                    File destFile = new File(windowsBoockPath + newFilename);
                    file.transferTo(destFile);
                    BoockFile boockFile = BoockFile.builder().uuid(UUID.randomUUID().toString()).fileName(newFilename).contentId(boock1.getId()).orderNum(i).build();
                    boockFileRepository.save(boockFile);
                }
            }
        } catch (Exception e) {
            map.put("FileMsg", "上传失败: " + e.getMessage());
            System.out.println(e.getMessage());
        }
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

    @DeleteMapping("/delete")
    public void deleteBoock(@RequestParam(value = "boockId") String boockId){
        boockService.deleteBoock(boockId);
    }
}

