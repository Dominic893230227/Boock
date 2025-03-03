package com.boock.service;

import com.boock.entity.po.Boock;
import com.boock.entity.po.Comment;
import com.boock.entity.vo.BoockVo;

import java.util.List;
import java.util.Map;

public interface BoockService {
    Integer saveBoock(Boock boock);

    List<BoockVo> getAllBoock();

    List<BoockVo> getMyBoock(Integer userId);

    Integer addComment(Comment comment);

    Map<String,Object> search(String param);

}
