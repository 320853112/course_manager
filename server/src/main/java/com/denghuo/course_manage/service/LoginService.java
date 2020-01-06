package com.denghuo.course_manage.service;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.http.HttpSession;

public interface LoginService {
    Boolean stuLogin(Integer stuId,String password);

    Boolean teacherLogin(Integer teacherId,String password);

    Boolean adminLogin(String username,String password);

    String getUserRole(HttpSession session);
}
