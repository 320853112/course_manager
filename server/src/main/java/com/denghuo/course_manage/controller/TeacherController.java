package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.AOP.Access;
import com.denghuo.course_manage.model.Teacher;
import com.denghuo.course_manage.service.LoginService;
import com.denghuo.course_manage.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "teacherAPI",tags = "老师相关")
@Access(role = 2,comment = "老师")
public class TeacherController {

    @Autowired
    private LoginService loginService;


    @ApiOperation("查询老师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuId", value = "学生id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/getTeacher", method = RequestMethod.POST)
    public Object stuLogin(Teacher teacher) {
        return Result.send(loginService.stuLogin(stuId, password));
    }
}