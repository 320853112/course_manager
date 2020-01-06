package com.denghuo.course_manage.model;

public class Teacher {
    private Integer id;

    private String name;

    private String college;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Teacher(Integer id, String name, String college, String password) {
        this.id = id;
        this.name = name;
        this.college = college;
        this.password = password;
    }
}
