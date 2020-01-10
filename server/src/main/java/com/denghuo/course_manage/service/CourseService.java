package com.denghuo.course_manage.service;

import com.denghuo.course_manage.model.Course;

import java.util.List;

public interface CourseService {
    boolean updateCourse(Course course);
    Object getCourse(Course course,Integer pageNum,Integer pageSize);
    boolean deleteCourse(String id);
    boolean insertCourse(Course course);
}
