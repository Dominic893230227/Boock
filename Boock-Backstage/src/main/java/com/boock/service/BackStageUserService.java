package com.boock.service;

import com.boock.entity.po.User;
import com.boock.entity.vo.UserVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BackStageUserService {
    Page<UserVo> getUserList(Integer pageNum, Integer pageSize , String searchName);

    void deleteAccount(Integer id);

    void addAccount(User user);
}
