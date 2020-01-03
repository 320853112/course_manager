package com.denghuo.course_manage.service;

import com.denghuo.course_manage.model.Course;

import java.util.List;

public interface CourseService {
    boolean updateCourse(Course course);
    List<Course> getCourse(Course course);
    boolean deleteCourse(int id);
    boolean insertCourse(Course course);
}
