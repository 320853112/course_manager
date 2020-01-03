package com.denghuo.course_manage.model;

public class Teacher {
    private Integer id;

    private String name;

    private String college;

    private String password;

    public Teacher(Integer id, String name, String college, String password) {
        this.id = id;
        this.name = name;
        this.college = college;
        this.password = password;
    }
}
