package com.denghuo.course_manage.service;

import com.denghuo.course_manage.model.Teacher;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface TeacherService {

    boolean updateTeacher(Teacher teacher);
    Object getTeacher(Teacher teacher,Integer pageNum,Integer pageSize, HttpSession session);
    boolean deleteTeacher(Integer id);
    boolean insertTeacher(Teacher teacher);
}
