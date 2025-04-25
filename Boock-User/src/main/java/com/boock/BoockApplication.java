package com.boock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("com.boock.dao")
@MapperScan("com.boock.mapper")
public class BoockApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoockApplication.class,args);
    }
}
