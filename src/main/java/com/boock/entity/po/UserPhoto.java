package com.boock.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPhoto {
    private String uuid;
    private Integer userId;
    private String path;
    private String fileName;
}
