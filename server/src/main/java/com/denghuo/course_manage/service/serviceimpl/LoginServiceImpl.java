package com.denghuo.course_manage.service.serviceimpl;

import com.denghuo.course_manage.DTO.StudentDTO;
import com.denghuo.course_manage.dao.AdminDAO;
import com.denghuo.course_manage.dao.LoginDAO;
import com.denghuo.course_manage.dao.StudentDAO;
import com.denghuo.course_manage.dao.TeacherDAO;
import com.denghuo.course_manage.model.Admin;
import com.denghuo.course_manage.model.Student;
import com.denghuo.course_manage.model.Teacher;
import com.denghuo.course_manage.service.LoginService;
import com.denghuo.course_manage.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDAO loginDAO;

    @Autowired
    private HttpSession session;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private AdminDAO adminDAO;


    @Override
    public Boolean stuLogin(String stuId, String password) {
        password = MD5util.getMD5String(password);
        Student student = loginDAO.stuLogin(stuId, password);
        if(student==null){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        session.setAttribute("roleNum", RoleData.student.getRoleNum());
        session.setAttribute("roleName", RoleData.student.getRoleName());
        session.setAttribute("userName", student.getName());
        session.setAttribute("userId", student.getId());
        return true;
    }

    @Override
    public Boolean teacherLogin(String teacherId, String password, HttpServletResponse response) {
        password = MD5util.getMD5String(password);
        Teacher teacher = loginDAO.teacherLogin(teacherId, password);
        if(teacher==null){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        session.setAttribute("roleNum", RoleData.teacher.getRoleNum());
        session.setAttribute("roleName", RoleData.teacher.getRoleName());
        session.setAttribute("userName", teacher.getName());
        session.setAttribute("userId", teacher.getId());
        response.addCookie(new Cookie("teacherId",teacherId));
        return true;
    }

    @Override
    public Boolean adminLogin(String username, String password) {
        password = MD5util.getMD5String(password);
        Admin admin = loginDAO.adminLogin(username, password);
        if(admin==null){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        session.setAttribute("roleNum", RoleData.admin.getRoleNum());
        session.setAttribute("roleName", RoleData.admin.getRoleName());
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

    @Override
    public Boolean resetPassword(HttpSession session, String userType, String userId, String newPassword) {
        Integer res = 0;
        switch (userType){
            case "admin":
                Admin admin = new Admin();
                admin.setId(Integer.parseInt(userId));
                admin.setPassword(MD5util.getMD5String(newPassword));
                res= adminDAO.updateAdmin(admin);
                break;
            case "teacher":
                Teacher teacher = new Teacher();
                teacher.setId(userId);
                teacher.setPassword(MD5util.getMD5String(newPassword));
                res= teacherDAO.updateTeacher(teacher);
                break;
            case "student":
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(userId);
                studentDTO.setPassword(MD5util.getMD5String(newPassword));
                res= studentDAO.updateStuInfo(studentDTO);
                break;
        }
        if(res!=1){
            throw new CustomException(MyExceptionEnum.ACCESS_FAIL);
        }
        return true;
    }
}
