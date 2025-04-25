package com.boock.entity.jpa;

import com.boock.entity.po.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLevelRepository extends JpaRepository<UserLevel, Integer> {
    UserLevel findByUserId(Integer userId);
}
