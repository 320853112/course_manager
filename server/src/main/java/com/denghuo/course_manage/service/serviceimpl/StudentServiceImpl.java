package com.denghuo.course_manage.service.serviceimpl;

import com.denghuo.course_manage.dao.StudentDAO;
import com.denghuo.course_manage.model.Student;
import com.denghuo.course_manage.service.StudentService;
import com.denghuo.course_manage.utils.MD5util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Override
    public Boolean insertStuInfo(Student student) {
        if(student.getPassword()==null){
            student.setPassword(MD5util.getMD5String(student.getId().toString()));
        }else{
            student.setPassword(MD5util.getMD5String(student.getPassword()));
        }
        studentDAO.insertStuInfo(student);
        return true;
    }
}
