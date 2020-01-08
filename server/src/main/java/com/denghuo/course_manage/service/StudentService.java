package com.denghuo.course_manage.service;

import com.denghuo.course_manage.model.Course;
import com.denghuo.course_manage.model.Student;

import java.util.List;

public interface StudentService {
    Boolean insertStuInfo(Student student);

    List<Student> getStuInfo(Student student,Integer pageNum,Integer pageSize);

    List<Course> getStuCourse(Integer stuId);
}
