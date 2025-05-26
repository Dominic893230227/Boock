package com.boock.service.impl;

import com.boock.dao.UserMapper;
import com.boock.entity.jpa.UserRepository;
import com.boock.entity.po.User;
import com.boock.dao.BackStageUserMapper;
import com.boock.entity.po.UserPhoto;
import com.boock.entity.vo.UserVo;
import com.boock.service.BackStageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackStageUserServiceImpl implements BackStageUserService {
    @Autowired
    private BackStageUserMapper backStageUserMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserVo> getUserList(Integer pageNum, Integer pageSize, String searchName) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<User> userPage = userRepository.findByIdNotAndLike(1,searchName, pageable);
        List<User> content = userPage.getContent();
        List<UserVo> userVos = new ArrayList<>();
        for (User user : content) {
            UserVo userVo = new UserVo();
            UserPhoto photo = userMapper.findPhoto(user.getId());
            userVo.setUser(user);
            userVo.setUserPhoto(photo);
            userVos.add(userVo);
        }
        PageImpl<UserVo> userVoPage = new PageImpl<UserVo>(userVos, userPage.getPageable(), userPage.getTotalElements());
        return userVoPage;
    }

    @Override
    public void deleteAccount(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void addAccount(User user) {
        userRepository.save(user);
    }


}
