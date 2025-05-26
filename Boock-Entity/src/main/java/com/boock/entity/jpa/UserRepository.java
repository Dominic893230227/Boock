package com.boock.entity.jpa;

import com.boock.entity.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Override
    Page<User> findAll(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.id <> :id AND (u.username LIKE %:searchName% OR u.name LIKE %:searchName%)")
    Page<User> findByIdNotAndLike(@Param("id") Integer id, @Param("searchName")String searchName, Pageable pageable);
}

