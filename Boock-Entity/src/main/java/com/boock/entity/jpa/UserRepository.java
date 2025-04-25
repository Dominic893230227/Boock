package com.boock.entity.jpa;

import com.boock.entity.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Override
    Page<User> findAll(Pageable pageable);

    Page<User> findByIdNot(Integer id,Pageable pageable);
}
