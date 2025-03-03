package com.boock.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Boock {
    private String id;
    private Integer userId;
    private String name;
    private String content;
    private Integer createBy;
    private LocalDateTime createTime;
}
