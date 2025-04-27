package com.boock.entity.vo;


import com.boock.entity.po.Boock;
import com.boock.entity.po.BoockFile;
import com.boock.entity.po.UserLevel;
import com.boock.entity.po.UserPhoto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoockVo {
    private Boock boock;
    private UserPhoto userPhoto;
    private List<CommentVo> listCommentVo;
    private UserLevel userLevel;
    private List<BoockFile> boockFile;
}
