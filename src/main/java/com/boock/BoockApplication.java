package com.boock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.boock.dao")
public class BoockApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoockApplication.class,args);
    }
}
