package com.boock.controller;

import com.boock.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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
