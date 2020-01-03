package com.denghuo.course_manage.model;


public class Course {
    private Integer id;
    private String category;
    private String name;
    private String time_week;
    private String credit;
    private String teacher;
    private Integer surplus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime_week() {
        return time_week;
    }

    public void setTime_week(String time_week) {
        this.time_week = time_week;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getSurplus() {
        return surplus;
    }

    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }
}
