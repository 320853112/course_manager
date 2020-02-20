package com.denghuo.course_manage.model;


import lombok.Data;

@Data
public class Course {
    private String id;
    private String category;
    private String name;
    private String timeWeek;
    private String credit;
    private String teacher;
    private Integer surplus;
    private String startTime;
    private String endTime;
    private String checkType;
    private String studyType;
    private String place;
    private String teacherId;

    public Course() {
    }
    public Course(String id) {
        this.id = id;
    }
}
