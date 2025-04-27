package com.boock.entity.vo;

import com.boock.entity.po.User;
import com.boock.entity.po.UserPhoto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private User user;
    private UserPhoto userPhoto;
}
