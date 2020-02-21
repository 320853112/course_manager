package com.denghuo.course_manage.model;

import com.denghuo.course_manage.VO.StudentVO;
import lombok.Data;

@Data
public class Student {
    private String id;
    private String name;
    private String college;
    private String major;
    private String className;
    private String password;
    private String gender;



    public Student() {
    }

    public Student(String id) {
        this.id = id;
    }

    public StudentVO toStudentVO(Object userId) {
        StudentVO studentVO = new StudentVO();
        studentVO.setUserId(userId);
        studentVO.setId(id);
        studentVO.setName(name);
        studentVO.setCollege(college);
        studentVO.setMajor(major);
        studentVO.setClassName(className);
        studentVO.setPassword(password);
        studentVO.setGender(gender);
        return studentVO;
    }
}
