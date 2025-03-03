package com.boock.controller;

import com.boock.dao.LoginMapper;
import com.boock.entity.dto.UserDto;
import com.boock.entity.po.User;
import com.boock.service.LoginService;
import com.boock.util.CookieUtil;
import com.boock.util.EncryptionUtil;
import com.boock.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/test")
    public String test(HttpServletRequest request){
        Map<String, String> stringStringMap = CookieUtil.readCookie(request, "tooken", "token");
        String tooken = stringStringMap.get("tooken");
        String token = stringStringMap.get("token");
        boolean b = JwtUtil.checkToken(token);
        return tooken+" | "+token+" | "+b;
    }


    @PostMapping
    public Map<String,Object> login(@RequestBody UserDto userDto,HttpServletResponse response){ //@RequestParam String username,@RequestParam String password
        Map<String, Object> hashMap = new HashMap<>();
        if (userDto.getUsername()==null || userDto.getUsername().equals("")){
            hashMap.put("flag",false);
            hashMap.put("data","不是哥们，账号都不输就想进去啊");
            return hashMap;
        }
        Map<String,Object> result = loginService.login(userDto);
        String data = "";
        if((boolean) result.get("flag") == true){
            data = "登陆成功";
            User user = (User) result.get("user");
            String token = JwtUtil.creatJwtToken(user.getId(), user.getUsername(),user.getName());
            CookieUtil.addCookie(response,"/","token",token,-1,true);
        }else{
            data = "账号密码有误！";
        }

        hashMap.put("flag",(boolean) result.get("flag"));
        hashMap.put("data",data);
        return hashMap;
    }

    @PostMapping("/registe")
    public Map<String,Object> registe(@RequestBody User user){    //Map<String,Object>
        Map<String, Object> map = new HashMap<>();
        boolean flag = loginService.registe(user);
        if(flag==true){
            map.put("flag",flag);
            map.put("msg","注册成功");
        }else{
            map.put("flag",flag);
            map.put("msg","注册失败");
        }
        return  map;
    }
}
