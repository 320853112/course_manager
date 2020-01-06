package com.denghuo.course_manage.service;

import com.denghuo.course_manage.model.Teacher;

import java.util.List;

public interface TeacherService {

    boolean updateTeacher(Teacher teacher);
    List<Teacher> getTeacher(Teacher teacher);
    boolean deleteTeacher(Integer id);
    boolean insertTeacher(Teacher teacher);
}
