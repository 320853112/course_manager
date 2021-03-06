package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDAO {
    int updateCourse(Course course);
    List<Course> getCourse(@Param("course")Course course, @Param("startNum") Integer startNum, @Param("pageSize")Integer pageSize);
    Double getCourseTotal(Course course);
    int deleteCourse(String id);
    int insertCourse(Course course);
    Course existCourse(String id);
}
