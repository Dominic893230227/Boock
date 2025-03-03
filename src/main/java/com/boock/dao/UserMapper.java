package com.boock.dao;

import com.boock.entity.po.User;
import com.boock.entity.po.UserPhoto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    Integer insertPhoto(@Param("user_id")Integer id, @Param("file_name") String fileName, @Param("path")String filePath);

    Integer updatePhoto(@Param("user_id")Integer id, @Param("file_name") String fileName, @Param("path")String filePath);

    User getUserById(Integer id);

    Integer saveInfo(User user);

    UserPhoto findPhoto(Integer id);

    List<User> search(String param);
}
