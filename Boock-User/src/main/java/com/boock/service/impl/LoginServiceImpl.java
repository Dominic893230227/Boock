package com.boock.service.impl;

import com.boock.dao.LoginMapper;
import com.boock.entity.dto.UserDto;
import com.boock.entity.po.User;
import com.boock.service.LoginService;
import com.boock.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;


    @Override
    public Map<String,Object> login(UserDto userDto) {
        boolean flag = false;
        String usernameParam = userDto.getUsername();
        String passwordParam = userDto.getPassword();
        User user = loginMapper.getUser(usernameParam);
        HashMap<String, Object> result = new HashMap<>();
        if(user!=null){
            String password1 = user.getPassword();
            if (EncryptionUtil.decrypt(password1).equals(passwordParam)){
                flag = true;
            }
            result.put("user",user);
            result.put("flag",flag);
        }else{
            result.put("user","");
            result.put("flag",flag);
        }



        return result;
//        String encryptPassword = EncryptionUtil.encrypt(password);

    }

    @Override
    public HashMap<String,Object> registe(User user) {
        boolean flag = false ;
        HashMap<String, Object> map = new HashMap<>();
        if(loginMapper.getUser(user.getUsername())!=null){
            map.put("flag",flag);
            map.put("msg","用户名已存在");
            return map;
        }else{
            user.setPassword(EncryptionUtil.encrypt(user.getPassword()));
            int i = loginMapper.addUser(user);
            if(i>=1){
                flag = true;
                map.put("flag",flag);
                map.put("msg","创建成功");
                return map;
            }else{
                map.put("flag",flag);
                map.put("msg","创建失败");
                return map;
            }
        }
    }
}
