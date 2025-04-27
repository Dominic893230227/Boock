package com.boock.controller;

import com.boock.entity.po.User;
import com.boock.entity.vo.UserVo;
import com.boock.service.BackStageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backstage/user")
public class BackStageUserController {
    @Autowired
    private BackStageUserService backStageUserService;
    @GetMapping("/getUserList")
    public Page<UserVo> getAllUser(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize") Integer pageSize){
//        System.out.println("damn");
        Page<UserVo> userPage = backStageUserService.getUserList(pageNum,pageSize);
        return userPage;
    }

    @DeleteMapping("/deleteAccount")
    public void deleteAccount(@RequestParam(name = "id") Integer id){
        backStageUserService.deleteAccount(id);
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        backStageUserService.addAccount(user);
    }

    @GetMapping("/searchUser")
    public void deleteAccount(@RequestParam(name = "name") String name){
//        backStageUserService.searchUser(name);
    }
}
