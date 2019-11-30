package com.denghuo.course_manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.denghuo.course_manage.dao")
//@ComponentScan("springfox.documentation.swagger2")
public class CourseManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseManageApplication.class, args);
    }

}
