package com.boock.service.impl;

import com.boock.dao.UserMapper;
import com.boock.entity.po.User;
import com.boock.entity.po.UserPhoto;
import com.boock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Map<String,Object> savePhoto(User user, String fileName, String filePath) {
        UserPhoto photo = userMapper.findPhoto(user.getId());
        HashMap<String, Object> map = new HashMap<>();
//        filePath = filePath.substring(filePath.lastIndexOf("/uploadImage/photo"));
        if(photo!=null){
            Integer result = userMapper.updatePhoto(user.getId(), fileName, filePath);
            map.put("type",0);
            map.put("num",result);
        }else{
            Integer result =  userMapper.insertPhoto(user.getId(),fileName,filePath);
            map.put("type",1);
            map.put("num",result);
        }
        return map;
    }
    @Override
    public Integer saveInfo(User user) {
        Integer result = userMapper.saveInfo(user);
//        User user = userMapper.getUser();
        return result;
    }

    @Override
    public User loadUserInfo(Integer id) {
        User user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public UserPhoto loadUserPhoto(Integer id) {
        UserPhoto userPhoto = userMapper.findPhoto(id);
        return userPhoto;
    }

}
