package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.dao.StudentDAO;
import com.denghuo.course_manage.utils.CustomException;
import com.denghuo.course_manage.utils.MyExceptionEnum;
import com.denghuo.course_manage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentDAO studentDAO;


    @RequestMapping("/getStuInfo")
    public Object sendMessage(Integer id ){
//        throw  new Exception();
        int a = 1/0;
        return Result.send(studentDAO.getStuInfo(id));
    }

}
