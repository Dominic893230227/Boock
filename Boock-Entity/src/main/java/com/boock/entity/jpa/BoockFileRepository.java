package com.boock.entity.jpa;

import com.boock.entity.po.BoockFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoockFileRepository extends JpaRepository<BoockFile,String> {
    List<BoockFile> findByContentIdOrderByOrderNum(String contentId);
}
