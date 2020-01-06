package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Student;
import com.denghuo.course_manage.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDAO {

    List<Teacher> getTeacher(Teacher teacher);

    int insertTeacher(Teacher teacher);

    int deleteTeacher(Integer id);

    int updateTeacher(Teacher teacher);
}
