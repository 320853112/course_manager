package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDAO {
    int updateCourse(Course course);
    List<Course> getCourse(Course course);
    int deleteCourse(int id);
    int insertCourse(Course course);
}
