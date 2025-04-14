package com.boock.controller;

import com.boock.entity.po.User;
import com.boock.entity.po.UserLevel;
import com.boock.entity.po.UserPhoto;
import com.boock.service.UserLevelService;
import com.boock.service.UserService;
import com.boock.util.CookieUtil;
import com.boock.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Value("${file.path.windows}")
    private String windowsFilePath;
    @Value("${file.path.linux}")
    private String linuxFilePath;

    @Autowired
    private UserLevelService userLevelService;

    @GetMapping("/test")
    public HashMap<String, String> test(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        HashMap<String, String> map = new HashMap<>();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            map.put(name,value);
        }
        return map;
    }

    @GetMapping("/loadUser")
    public Map<String, Object> loadUser(HttpServletRequest request){
        Map<String, String> map = CookieUtil.readCookie(request, "token");
        String token = map.get("token");
        HashMap<String, Object> tokenMap = JwtUtil.readJwt(token);
        Integer id = Integer.parseInt((String) tokenMap.get("id"));
        User user = userService.loadUserInfo(id);
//        String username = (String) tokenMap.get("username");
//        String name = (String) tokenMap.get("name");
        UserPhoto userPhoto = userService.loadUserPhoto(id);

        UserLevel userLevel = userService.getUserLevel(id);
        HashMap<String, Object> result = new HashMap<>();
        result.put("id",id);
        result.put("username",user.getUsername());
        result.put("name",user.getName());
        result.put("UserPhoto",userPhoto);

        result.put("UserLevel",userLevel);
        return result;
    }

    @GetMapping("/loadUserInfo")
    public Map<String,Object> loadUserInfo(@RequestParam("id") Integer id){
        HashMap<String, Object> map = new HashMap<>();
        User user = userService.loadUserInfo(id);
        UserPhoto userPhoto = userService.loadUserPhoto(id);
        map.put("User",user);
        map.put("UserPhoto",userPhoto);
        UserLevel userLevelById = userLevelService.findUserLevelById(id);
        map.put("UserLevel",userLevelById);
        return map;
    }

    @PostMapping("/saveInfo")
    public String saveInfo(@RequestParam(value = "file",required = false) MultipartFile file,@RequestPart(value = "userJson") String userJson) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userJson, User.class);
        if(file!= null && !file.isEmpty()){
            String fileName = file.getOriginalFilename();
            // 设置文件存储路径
            String filePath ="";
            String os = System.getProperty("os.name");
            if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
                filePath = windowsFilePath + fileName;
            }else{
                filePath = linuxFilePath + fileName;
            }
//            String filePath = "D:/SOFTWARE/CODE/idea/workspace/Boock/src/main/resources/static/uploadImage/photo/" + fileName; // 替换为你的实际存储路径
            Map<String, Object> map = userService.savePhoto(user, fileName, filePath);
            file.transferTo(new File(filePath));
            if(Integer.valueOf(map.get("type").toString())==0){
                System.out.println("已修改头像并保存信息");
            }else{
                System.out.println( "已上传头像并保存信息");
            }
        }
        // 获取文件名
       userService.saveInfo(user);
        // 保存文件
        return "保存成功";
    }
}
