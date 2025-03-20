package com.boock.repository;

import com.boock.entity.po.UserLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserLevelRepository extends JpaRepository<UserLevel, Integer> {
    UserLevel findByUserId(Integer userId);
}
