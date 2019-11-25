package com.denghuo.course_manage.model;


public class Course {
    private Integer id;
    private String category;
    private String name;
    private String time_week;
    private String time_data;
    private String time_day;
    private String score;
    private String teacher;
    private String surplus;

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

    public String getTime_data() {
        return time_data;
    }

    public void setTime_data(String time_data) {
        this.time_data = time_data;
    }

    public String getTime_day() {
        return time_day;
    }

    public void setTime_day(String time_day) {
        this.time_day = time_day;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSurplus() {
        return surplus;
    }

    public void setSurplus(String surplus) {
        this.surplus = surplus;
    }
}