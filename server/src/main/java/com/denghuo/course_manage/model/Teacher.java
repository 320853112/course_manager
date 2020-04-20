package com.denghuo.course_manage.model;

import com.denghuo.course_manage.VO.TeacherVO;
import lombok.Data;

@Data
public class Teacher {
    private String id;

    private String name;

    private String college;

    private String password;


    public Teacher() {
    }

    public Teacher(String id, String name, String college, String password) {
        this.id = id;
        this.name = name;
        this.college = college;
        this.password = password;
    }

    public TeacherVO toTeacherVO(Object userId){
        TeacherVO teacherVO = new TeacherVO();
        teacherVO.setId(id);
        teacherVO.setName(name);
        teacherVO.setCollege(college);
        teacherVO.setPassword(password);
        teacherVO.setUserId(userId);
        return teacherVO;
    }
}
