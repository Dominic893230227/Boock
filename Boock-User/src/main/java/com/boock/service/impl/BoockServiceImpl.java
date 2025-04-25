package com.boock.service.impl;

import com.boock.dao.BoockMapper;
import com.boock.dao.UserMapper;
import com.boock.entity.po.*;
import com.boock.entity.vo.BoockVo;
import com.boock.entity.vo.CommentVo;
import com.boock.entity.jpa.UserLevelRepository;
import com.boock.service.BoockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BoockServiceImpl implements BoockService {
    @Autowired
    private BoockMapper boockMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserLevelRepository userLevelRepository;

    @Override
    public Integer saveBoock(Boock boock) {
        boock.setId(UUID.randomUUID().toString().replace("-", ""));
        User user = userMapper.getUserById(boock.getUserId());
        boock.setName(user.getName());
        boock.setCreateTime(LocalDateTime.now());
        return boockMapper.add(boock);
    }

    @Override
    public List<BoockVo> getAllBoock() {
        List<Boock> boocks = boockMapper.getAllBoock();
        List<BoockVo> boockVos = new ArrayList<>();
        for (Boock boock : boocks) {
            BoockVo boockVo = new BoockVo();
            UserPhoto userPhoto = userMapper.findPhoto(boock.getUserId());
            boockVo.setBoock(boock);
            boockVo.setUserPhoto(userPhoto);

            List<CommentVo> listCommentVos = new ArrayList<>();
            List<Comment> listComment = boockMapper.getCommentByContentId(boock.getId());
            List<Comment> sortedComment = sortComment(listComment);
            for (Comment comment : sortedComment) {
                CommentVo commentVo = new CommentVo();
                UserPhoto commentPhoto = userMapper.findPhoto(comment.getUserId());
                commentVo.setComment(comment);
                commentVo.setUserPhoto(commentPhoto);
                listCommentVos.add(commentVo);
            }
            boockVo.setListCommentVo(listCommentVos);

            UserLevel userLevel = userLevelRepository.findByUserId(boock.getUserId());
            boockVo.setUserLevel(userLevel);

            boockVos.add(boockVo);
        }
        return boockVos;
    }

    @Override
    public List<BoockVo> getMyBoock(Integer userId) {
        List<Boock> boocks = boockMapper.getBoockById(userId);
        List<BoockVo> boockVos = new ArrayList<>();
        for (Boock boock : boocks) {
            BoockVo boockVo = new BoockVo();
            UserPhoto userPhoto = userMapper.findPhoto(boock.getUserId());
            boockVo.setBoock(boock);
            boockVo.setUserPhoto(userPhoto);

            List<CommentVo> listCommentVos = new ArrayList<>();
            List<Comment> listComment = boockMapper.getCommentByContentId(boock.getId());
            List<Comment> sortedComment = sortComment(listComment);
            for (Comment comment : sortedComment) {
                CommentVo commentVo = new CommentVo();
                UserPhoto commentPhoto = userMapper.findPhoto(comment.getUserId());
                commentVo.setComment(comment);
                commentVo.setUserPhoto(commentPhoto);
                listCommentVos.add(commentVo);
            }
            boockVo.setListCommentVo(listCommentVos);

            boockVos.add(boockVo);
        }
        return boockVos;
    }

    @Override
    public Integer addComment(Comment comment) {
        comment.setId(UUID.randomUUID().toString().replace("-", ""));
        comment.setCreateTime(LocalDateTime.now());
        User user = userMapper.getUserById(comment.getUserId());
        comment.setName(user.getName());
        Integer result = boockMapper.addComment(comment);
        return result;
    }

    @Override
    public Map<String,Object> search(String param) {
        Map<String, Object> result = new HashMap<>();

        List<Boock> boocks = boockMapper.search(param);
        List<BoockVo> boockVos = new ArrayList<>();
        for (Boock boock : boocks) {
            BoockVo boockVo = new BoockVo();
            UserPhoto userPhoto = userMapper.findPhoto(boock.getUserId());
            boockVo.setBoock(boock);
            boockVo.setUserPhoto(userPhoto);

            List<CommentVo> listCommentVos = new ArrayList<>();
            List<Comment> listComment = boockMapper.getCommentByContentId(boock.getId());
            List<Comment> sortedComment = sortComment(listComment);
            for (Comment comment : sortedComment) {
                CommentVo commentVo = new CommentVo();
                UserPhoto commentPhoto = userMapper.findPhoto(comment.getUserId());
                commentVo.setComment(comment);
                commentVo.setUserPhoto(commentPhoto);
                listCommentVos.add(commentVo);
            }
            boockVo.setListCommentVo(listCommentVos);

            boockVos.add(boockVo);
        }
        result.put("Boock",boockVos);

        List<User> userList = userMapper.search(param);
        for (User user : userList) {
            UserPhoto userPhoto = userMapper.findPhoto(user.getId());
        }
        result.put("User",userList);
        return result;
    }

    @Override
    public void deleteBoock(String boockId) {
        boockMapper.deleteBoock(boockId);
        boockMapper.deleteComment(boockId);
    }

    /***
     * 1.按父评论ID分组
     * 2.对根评论进行时间排序
     * 3.遍历根评论 然后将根评论的子评论加进去，再递归一手把子评论的子评论搞进去
     * @param listComment
     * @return
     */
    public List<Comment> sortComment(List<Comment> listComment) {
        List<Comment> sortedCommentList = new ArrayList<>(); // 创建一个ArrayList，用于存储根评论（没有父评论的评论）

        // 1. 将评论按父评论ID分组
        Map<String, List<Comment>> groupedComments = new HashMap<>(); // 创建一个HashMap，key为父评论ID，value为子评论列表



        for (Comment comment : listComment) { // 遍历输入的评论列表
            String replyToId = comment.getReplyToId(); // 获取当前评论的父评论ID
            if (replyToId == null) { // 如果父评论ID为空，则说明是根评论
                sortedCommentList.add(comment); // 将根评论添加到sortedCommentList列表中
            } else { // 如果父评论ID不为空，则说明是子评论
                if (!groupedComments.containsKey(replyToId)) { // 如果HashMap中不存在该父评论ID的key
                    groupedComments.put(replyToId, new ArrayList<>()); // 创建一个新的ArrayList，作为该父评论ID的value
                }
                groupedComments.get(replyToId).add(comment); // 将子评论添加到对应的父评论ID的value（子评论列表）中
            }
        }

        // 2. 对根评论按时间排序
        Collections.sort(sortedCommentList, new Comparator<Comment>() { // 使用Collections.sort()方法对根评论列表进行排序
            @Override
            public int compare(Comment c1, Comment c2) { // 实现Comparator接口的compare()方法，定义比较规则
                return c1.getCreateTime().compareTo(c2.getCreateTime()); // 按照评论的创建时间升序排序
            }
        });

        // 3. 递归添加子评论
        List<Comment> result = new ArrayList<>(); // 创建一个ArrayList，用于存储最终排序结果
        for (Comment rootComment : sortedCommentList) { // 遍历排序后的根评论列表
            result.add(rootComment); // 将根评论添加到结果列表中
            addChildComments(groupedComments, result, rootComment.getId()); // 递归调用addChildComments方法，添加子评论
        }

        return result; // 返回最终排序结果
    }

    private void addChildComments(Map<String, List<Comment>> groupedComments, List<Comment> result, String parentId) {
        if (groupedComments.containsKey(parentId)) { // 如果HashMap中存在该父评论ID的key
            List<Comment> childComments = groupedComments.get(parentId); // 获取该父评论ID对应的子评论列表

            Collections.sort(childComments, new Comparator<Comment>() { // 对子评论列表按时间排序
                @Override
                public int compare(Comment c1, Comment c2) { // 实现Comparator接口的compare()方法，定义比较规则
                    return c1.getCreateTime().compareTo(c2.getCreateTime()); // 按照评论的创建时间升序排序
                }
            });

            for (Comment childComment : childComments) { // 遍历排序后的子评论列表
                result.add(childComment); // 将子评论添加到结果列表中
                addChildComments(groupedComments, result, childComment.getId()); // 递归调用addChildComments方法，添加子评论的子评论
            }
        }
    }
}
