package com.boock.dao;

import com.boock.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    public User getUser(String username);

    int addUser(User user);
}
