package com.denghuo.course_manage.model;


import lombok.Data;

@Data
public class Course {
    private String id;
    private String category;
    private String name;
    private String time_week;
    private String credit;
    private String teacher;
    private Integer surplus;
    private String start_time;
    private String end_time;
    private String check_type;
    private String study_type;

    public Course() {
    }
    public Course(String id) {
        this.id = id;
    }
}
