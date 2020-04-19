package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.DTO.LoginIdDTO;
import com.denghuo.course_manage.DTO.LoginUserDTO;
import com.denghuo.course_manage.service.LoginService;
import com.denghuo.course_manage.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
@Api(value = "LoginAPI",tags = "登录相关")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @ApiOperation("学生登录")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "loginIdDTO",value = "用户名和密码的json", required = true, paramType = "body", dataType = "LoginIdDTO")
    })
    @RequestMapping(value = "/stuLogin",method = RequestMethod.POST)
    public  Object stuLogin(@RequestBody LoginIdDTO loginIdDTO){
        String stuId = loginIdDTO.getId();
        String password = loginIdDTO.getPassword();
        return Result.send(loginService.stuLogin(stuId,password));
    }

    @ApiOperation("老师登录")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "loginIdDTO",value = "用户名和密码的json", required = true, paramType = "body", dataType = "LoginIdDTO")
    })
    @RequestMapping(value = "/teacherLogin",method = RequestMethod.POST)
    public Object teacherLogin(@RequestBody LoginIdDTO loginIdDTO, HttpServletResponse response){
        String teacherId = loginIdDTO.getId();
        String password = loginIdDTO.getPassword();
        return Result.send(loginService.teacherLogin(teacherId,password,response));
    }

    @ApiOperation("管理员登录")
    @ApiImplicitParams({
//            @ApiImplicitParam( name = "loginDTO",value = "用户名和密码的json", required = true, paramType = "query", dataType = "LoginUserDTO")
    })
    @RequestMapping(value = "/adminLogin",method = RequestMethod.POST)
    public Object adminLogin( @RequestBody  LoginUserDTO loginDTO){
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        return Result.send(loginService.adminLogin(username,password));
    }

    @ApiOperation("返回用户身份")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/getUserRole",method = RequestMethod.GET)
    public Object getUserRole(HttpSession session){
        return Result.send(loginService.getUserRole(session));
    }
}