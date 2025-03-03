package com.boock.dao;

import com.boock.entity.po.Boock;
import com.boock.entity.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoockMapper {

    Integer add(@Param("boock") Boock boock);

    List<Boock> getAllBoock();

    List<Boock> getBoockById(Integer userId);

    Integer addComment(@Param("comment") Comment comment);

    List<Comment> getCommentByContentId(String contentId);

    List<Boock> search(String param);
}
