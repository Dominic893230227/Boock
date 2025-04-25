package com.boock.dao;

import com.boock.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackStageUserMapper  {
    List<User> getAllUser();
}
