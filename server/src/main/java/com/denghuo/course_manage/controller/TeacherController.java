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

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@RestController
@Api(value = "teacherAPI",tags = "老师相关")
@Access(role = 2,comment = "老师")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    @ApiOperation("查询老师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "教师id", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "college", value = "学院", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam( name = "pageNum",value = "页数", required = true, paramType = "query",dataType ="Integer"),
            @ApiImplicitParam( name = "pageSize",value = "每页记录数", required = true, paramType = "query",dataType ="Integer")
    })
    @RequestMapping(value = "/getTeacher", method = RequestMethod.GET)
    public Object getTeacher(Teacher teacher, Integer pageNum, Integer pageSize, HttpSession session) {
        return teacherService.getTeacher(teacher,pageNum,pageSize,session);
    }

    @ApiOperation("删除老师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "教师id", required = true, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/deleteTeacher", method = RequestMethod.POST)
    public Object deleteTeacher(Integer id) {
        return Result.send(teacherService.deleteTeacher(id));
    }

    @ApiOperation("更新老师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "教师id", required = true, paramType = "query", dataType = "String"),
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
            @ApiImplicitParam(name = "id", value = "教师id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "姓名", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "college", value = "学院", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = false, paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/insertTeacher", method = RequestMethod.POST)
    public Object insertTeacher(Teacher teacher) {
        return Result.send(teacherService.insertTeacher(teacher));
    }

    @ApiOperation("获取自己所教授的课程")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "pageNum",value = "页数", required = true, paramType = "query",dataType ="Integer"),
            @ApiImplicitParam( name = "pageSize",value = "每页记录数", required = true, paramType = "query",dataType ="Integer")
    })
    @RequestMapping(value = "/getTeacherCourse", method = RequestMethod.GET)
    public Object getTeacherCourse(Integer pageNum,Integer pageSize, HttpSession session) {
        return teacherService.getTeacherCourse(pageNum,pageSize, session);
    }

    @ApiOperation("获取自己所教授的课程的学生")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "courseName",value = "课程名", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "pageNum",value = "页数", required = true, paramType = "query",dataType ="Integer"),
            @ApiImplicitParam( name = "pageSize",value = "每页记录数", required = true, paramType = "query",dataType ="Integer")
    })
    @RequestMapping(value = "/getStuByCourse", method = RequestMethod.GET)
    public Object getStuByCourse(String teacherId ,String courseName,Integer pageNum,Integer pageSize) {
        return teacherService.getStuByCourse(teacherId,courseName,pageNum,pageSize);
    }
}