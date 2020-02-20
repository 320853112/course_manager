package com.denghuo.course_manage.service;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface LoginService {
    Boolean stuLogin(String stuId,String password);

    Boolean teacherLogin(String teacherId,String password);

    Boolean adminLogin(String username,String password);

    HashMap getUserRole(HttpSession session);
}
