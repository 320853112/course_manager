package com.denghuo.course_manage.service.serviceimpl;

import com.denghuo.course_manage.dao.LoginDAO;
import com.denghuo.course_manage.model.Admin;
import com.denghuo.course_manage.model.Student;
import com.denghuo.course_manage.model.Teacher;
import com.denghuo.course_manage.service.LoginService;
import com.denghuo.course_manage.utils.CustomException;
import com.denghuo.course_manage.utils.MD5util;
import com.denghuo.course_manage.utils.MyExceptionEnum;
import com.denghuo.course_manage.utils.Role_Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDAO loginDAO;

    @Autowired
    private HttpSession session;

    @Override
    public Boolean stuLogin(Integer stuId, String password) {
        password = MD5util.getMD5String(password);
        Student student = loginDAO.stuLogin(stuId, password);
        if(student==null){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        session.setAttribute("roleNum", Role_Data.student.getRoleNum());
        session.setAttribute("roleName", Role_Data.student.getRoleName());
        session.setAttribute("userName", student.getName());
        session.setAttribute("userId", student.getId());
        return true;
    }

    @Override
    public Boolean teacherLogin(Integer teacherId, String password) {
        password = MD5util.getMD5String(password);
        Teacher teacher = loginDAO.teacherLogin(teacherId, password);
        if(teacher==null){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        session.setAttribute("roleNum", Role_Data.teacher.getRoleNum());
        session.setAttribute("roleName", Role_Data.teacher.getRoleName());
        session.setAttribute("userName", teacher.getName());
        session.setAttribute("userId", teacher.getId());
        return true;
    }

    @Override
    public Boolean adminLogin(String username, String password) {
        password = MD5util.getMD5String(password);
        Admin admin = loginDAO.adminLogin(username, password);
        if(admin==null){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        session.setAttribute("roleNum", Role_Data.admin.getRoleNum());
        session.setAttribute("roleName", Role_Data.admin.getRoleName());
        session.setAttribute("userName", admin.getUsername());
        session.setAttribute("userId", admin.getId());
        return true;
    }

    @Override
    public HashMap getUserRole(HttpSession session) {
        String roleName = (String)session.getAttribute("roleName");
        String userName = (String)session.getAttribute("userName");
        if(roleName ==null) {
            throw new CustomException(MyExceptionEnum.PLEASE_LOGIN);
        }
        HashMap resMap = new HashMap();
        resMap.put("roleName",roleName);
        resMap.put("userName",userName);
        return resMap;
    }
}
