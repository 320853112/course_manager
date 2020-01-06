package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.AOP.Access;
import com.denghuo.course_manage.model.Teacher;
import com.denghuo.course_manage.service.LoginService;
import com.denghuo.course_manage.service.TeacherService;
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
    private TeacherService teacherService;


    @ApiOperation("查询老师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "教师id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "college", value = "学院", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = false, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/getTeacher", method = RequestMethod.POST)
    public Object getTeacher(Teacher teacher) {
        return Result.send(teacherService.getTeacher(teacher));
    }

    @ApiOperation("删除老师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "教师id", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/deleteTeacher", method = RequestMethod.POST)
    public Object deleteTeacher(Integer id) {
        return Result.send(teacherService.deleteTeacher(id));
    }

    @ApiOperation("更新老师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "教师id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "college", value = "学院", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = false, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/updateTeacher", method = RequestMethod.POST)
    public Object updateTeacher(Teacher teacher) {
        return Result.send(teacherService.updateTeacher(teacher));
    }

    @ApiOperation("插入老师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "教师id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "college", value = "学院", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = false, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/insertTeacher", method = RequestMethod.POST)
    public Object insertTeacher(Teacher teacher) {
        return Result.send(teacherService.insertTeacher(teacher));
    }
}