package com.example.blogs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.blogs.mapper")
public class BlogsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogsApplication.class, args);
    }

}