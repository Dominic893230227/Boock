package com.boock.entity.vo;


import com.boock.entity.po.Comment;
import com.boock.entity.po.UserPhoto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    private Comment comment;
    private UserPhoto userPhoto;

}
