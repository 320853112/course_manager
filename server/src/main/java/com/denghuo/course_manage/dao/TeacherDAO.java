package com.denghuo.course_manage.dao;

import com.denghuo.course_manage.model.Student;
import com.denghuo.course_manage.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDAO {

    Teacher getTeacher(Teacher);

}
