package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.AOP.Access;
import com.denghuo.course_manage.model.Course;
import com.denghuo.course_manage.service.AdminService;
import com.denghuo.course_manage.service.CourseService;
import com.denghuo.course_manage.utils.Result;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.org.apache.xpath.internal.objects.XObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(value = "courseAPI",tags = "课程信息相关")
@Access(role = 3,comment = "管理员")
public class CourseManagerController {

    @Autowired
    private CourseService courseService;



    @ApiOperation("修改课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "category",value = "课程类别", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "name",value = "课程名称", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "timeWeek",value = "星期", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "credit",value = "学分", required = false, paramType = "query",dataType ="int"),
            @ApiImplicitParam( name = "teacher",value = "老师", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "surplus",value = "剩余量", required = false, paramType = "query",dataType ="String"),
    })
    @RequestMapping(value = "/updateCourse",method = RequestMethod.POST)
    public Object updateCourse(Course course){
        return Result.send(courseService.updateCourse(course));
    }

    @ApiOperation("删除课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "id",value = "课程id", required = true, paramType = "query",dataType ="String"),
    })
    @RequestMapping(value = "/deleteCourse",method = RequestMethod.POST)
    public Object deleteCourse(String id){
        return Result.send(courseService.deleteCourse(id));
    }

    @ApiOperation("查询课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "id",value = "课程id", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "category",value = "课程类别", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "name",value = "课程名称", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "timeWeek",value = "星期", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "credit",value = "学分", required = false, paramType = "query",dataType ="int"),
            @ApiImplicitParam( name = "teacher",value = "老师", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "surplus",value = "剩余量", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "startTime",value = "开始时间", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "endTime",value = "结束时间", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "checkType",value = "考察方式", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "studyType",value = "课程类型", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "pageNum",value = "页数", required = true, paramType = "query",dataType ="Integer"),
            @ApiImplicitParam( name = "pageSize",value = "每页记录数", required = true, paramType = "query",dataType ="Integer")
    })
    @RequestMapping(value = "/getCourse",method = RequestMethod.GET)
    public Object getCourse(Course course,Integer pageNum,Integer pageSize){
        return courseService.getCourse(course,pageNum,pageSize);
    }

    @ApiOperation("增加课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "category",value = "课程类别", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "name",value = "课程名称", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "timeWeek",value = "星期", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "credit",value = "学分", required = false, paramType = "query",dataType ="int"),
            @ApiImplicitParam( name = "teacher",value = "老师", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "surplus",value = "剩余量", required = false, paramType = "query",dataType ="String")
    })
    @JsonIgnoreProperties(value={"id"})
    @RequestMapping(value = "/insertCourse",method = RequestMethod.POST)
    public Object insertCourse(Course course){
        return Result.send(courseService.insertCourse(course));
    }
}
