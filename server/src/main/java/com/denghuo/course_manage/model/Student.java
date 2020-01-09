package com.denghuo.course_manage.model;

import lombok.Data;

@Data
public class Student {
    private String id;
    private String name;
    private String college;
    private String major;
    private String className;
    private String password;



    public Student() {
    }

    public Student(String id) {
        this.id = id;
    }
}
