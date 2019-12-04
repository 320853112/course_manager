package com.denghuo.course_manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.denghuo.course_manage.dao")
@ComponentScan(value = {"springfox.documentation.swagger2","com.denghuo.course_manage"})
public class CourseManageApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(CourseManageApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CourseManageApplication.class);
    }
}
