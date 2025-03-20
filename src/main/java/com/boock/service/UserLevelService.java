package com.boock.service;

import com.boock.entity.po.UserLevel;

import java.util.HashMap;

public interface UserLevelService {
    void addUserLevel(UserLevel userLevel);

    HashMap<String,Object> add50Exp(Integer userId);
    HashMap<String,Object> add20Exp(Integer userId);
}
