package com.boock.service;

import com.boock.entity.po.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BackStageUserService {
    Page<User> getUserList(Integer pageNum,Integer pageSize);

    void deleteAccount(Integer id);
}
