package com.denghuo.course_manage.service.serviceimpl;

import com.denghuo.course_manage.dao.LoginDAO;
import com.denghuo.course_manage.service.LoginService;
import com.denghuo.course_manage.utils.CustomException;
import com.denghuo.course_manage.utils.MD5util;
import com.denghuo.course_manage.utils.MyExceptionEnum;
import com.denghuo.course_manage.utils.Role_Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDAO loginDAO;

    @Autowired
    private HttpSession session;

    @Override
    public Boolean stuLogin(Integer stuId, String password) {
        password = MD5util.getMD5String(password);
        if(loginDAO.stuLogin(stuId, password)==null){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        session.setAttribute("role", Role_Data.student.getRoleNum());
        return true;
    }

    @Override
    public Boolean teacherLogin(Integer teacherId, String password) {
        password = MD5util.getMD5String(password);
        if(loginDAO.teacherLogin(teacherId, password)==null){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        session.setAttribute("role", Role_Data.teacher.getRoleNum());
        return true;
    }

    @Override
    public Boolean adminLogin(String username, String password) {
        password = MD5util.getMD5String(password);
        if(loginDAO.adminLogin(username, password)==null){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        session.setAttribute("role", Role_Data.admin.getRoleNum());
        return true;
    }
}
