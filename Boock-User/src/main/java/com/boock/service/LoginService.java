package com.boock.service;


import com.boock.entity.dto.UserDto;
import com.boock.entity.po.User;

import java.util.HashMap;
import java.util.Map;

public interface LoginService {
    Map<String,Object> login(UserDto userDto);

    HashMap<String,Object> registe(User user);
}
