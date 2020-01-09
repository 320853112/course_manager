package com.denghuo.course_manage.model;

import lombok.Data;

@Data
public class Teacher {
    private String id;

    private String name;

    private String college;

    private String password;



    public Teacher(String id, String name, String college, String password) {
        this.id = id;
        this.name = name;
        this.college = college;
        this.password = password;
    }
}
