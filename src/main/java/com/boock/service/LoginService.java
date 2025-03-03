package com.boock.service;


import com.boock.entity.dto.UserDto;
import com.boock.entity.po.User;

import java.util.Map;

public interface LoginService {
    Map<String,Object> login(UserDto userDto);

    boolean registe(User user);
}
