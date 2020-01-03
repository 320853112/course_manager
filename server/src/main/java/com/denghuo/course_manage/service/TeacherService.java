package com.denghuo.course_manage.service;

import com.denghuo.course_manage.model.Student;

public interface TeacherService {

    Student getStuInfo(Student student);
    Student updateStuInfo(Student student);
    Student deleteStuInfo(Integer id);
    Student insertStuInfo(Student student);
}
