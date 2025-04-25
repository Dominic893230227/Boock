package com.boock.entity.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bk_boock_file")
public class BoockFile {
    @Id
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "content_id")
    private String contentId;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "order_num")
    private Integer orderNum;
}
