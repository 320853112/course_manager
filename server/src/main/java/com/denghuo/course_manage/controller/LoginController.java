package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.AOP.Access;
import com.denghuo.course_manage.model.StuToCourse;
import com.denghuo.course_manage.service.LoginService;
import com.denghuo.course_manage.service.SelectCourseService;
import com.denghuo.course_manage.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@Api(value = "LoginAPI",tags = "登录相关")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @ApiOperation("学生登录")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "stuId",value = "学生id", required = true, paramType = "header",dataType ="int"),
            @ApiImplicitParam( name = "password",value = "密码", required = true, paramType = "header",dataType ="String"),
    })
    @RequestMapping(value = "/stuLogin",method = RequestMethod.POST)
    public Object stuLogin(Integer stuId, String password){
        return Result.send(loginService.stuLogin(stuId,password));
    }

    @ApiOperation("老师登录")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "teacherId",value = "老师id", required = true, paramType = "header",dataType ="int"),
            @ApiImplicitParam( name = "password",value = "密码", required = true, paramType = "header",dataType ="String"),
    })
    @RequestMapping(value = "/teacherLogin",method = RequestMethod.POST)
    public Object teacherLogin(Integer teacherId, String password){
        return Result.send(loginService.teacherLogin(teacherId,password));
    }

    @ApiOperation("管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "username",value = "管理员用户名", required = true, paramType = "header",dataType ="String"),
            @ApiImplicitParam( name = "password",value = "密码", required = true, paramType = "header",dataType ="String"),
    })
    @RequestMapping(value = "/adminLogin",method = RequestMethod.POST)
    public Object adminLogin(@RequestHeader("username") String username, @RequestHeader("password") String password){
        return Result.send(loginService.adminLogin(username,password));
    }

    @ApiOperation("返回用户身份")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/getUserRole",method = RequestMethod.POST)
    public Object getUserRole(HttpSession session){
        return Result.send(loginService.getUserRole(session));
    }
}