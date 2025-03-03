package com.boock.controller;

import com.boock.entity.dto.UserDto;
import com.boock.entity.po.User;
import com.boock.service.LoginService;
import com.boock.util.CookieUtil;
import com.boock.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> home(HttpServletResponse response) {
        CookieUtil.cleanToken(response); // 清除 token Cookie
        Map<String, String> result = new HashMap<>();
        result.put("status", "success");
        return ResponseEntity.ok(result); // 返回 JSON 数据
    }
}
