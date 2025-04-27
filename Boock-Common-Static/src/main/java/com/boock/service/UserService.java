package com.boock.service;

import com.boock.entity.po.User;
import com.boock.entity.po.UserLevel;
import com.boock.entity.po.UserPhoto;

import java.util.Map;

public interface UserService {
    Map<String,Object> savePhoto(User user, String fileName, String filePath);
    Integer saveInfo(User user);

    User loadUserInfo(Integer id);

    UserPhoto loadUserPhoto(Integer id);

    UserLevel getUserLevel(Integer id);
}
