package com.denghuo.course_manage.service;

import com.denghuo.course_manage.model.Course;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CourseService {
    boolean updateCourse(Course course);
    Object getCourse(Course course, Integer pageNum, Integer pageSize, HttpSession session);
    boolean deleteCourse(String id);
    boolean insertCourse(Course course);
}
