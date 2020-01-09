package com.denghuo.course_manage.controller;


import com.denghuo.course_manage.AOP.Access;
import com.denghuo.course_manage.service.SelectCourseService;
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
@Api(value = "SelectCourseAPI",tags = "选课相关")
@Access(role = 1,comment = "学生")
public class SelectCourseController {

    @Autowired
    private SelectCourseService selectCourseService;

    @ApiOperation("选择课程")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "stuId",value = "学生id", required = true, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "courseId",value = "课程id", required = true, paramType = "query",dataType ="String"),
    })
    @RequestMapping(value = "/selectCourse",method = RequestMethod.POST)
    public Object selectCourse(String stuId,String courseId){
        return Result.send(selectCourseService.pickCourse(stuId,courseId));
    }
}