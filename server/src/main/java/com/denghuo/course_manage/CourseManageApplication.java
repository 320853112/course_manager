package com.denghuo.course_manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.denghuo.course_manage.dao")
public class CourseManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseManageApplication.class, args);
    }

}
