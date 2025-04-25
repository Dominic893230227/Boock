package com.boock.service.impl;

import com.boock.entity.jpa.UserRepository;
import com.boock.entity.po.User;
import com.boock.dao.BackStageUserMapper;
import com.boock.service.BackStageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackStageUserServiceImpl implements BackStageUserService {
    @Autowired
    private BackStageUserMapper backStageUserMapper;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Page<User> getUserList(Integer pageNum,Integer pageSize) {
//        List<User> userList = backStageUserMapper.getAllUser();
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
//        Page<User> userPage = userRepository.findAll(pageable);
        Page<User> userPage = userRepository.findByIdNot(1,pageable);
        return userPage;
    }

    @Override
    public void deleteAccount(Integer id) {
        userRepository.deleteById(id);
    }
}
