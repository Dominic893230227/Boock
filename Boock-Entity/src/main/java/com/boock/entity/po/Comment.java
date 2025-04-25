package com.boock.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private String id;
    private String contentId;
    private Integer userId;
    private String name;
    private String comment;
    private Integer createBy;
    private LocalDateTime createTime;
    private String replyToId;
    private Integer replyToUserId;
    private String replyToName;
}
