package com.denghuo.course_manage.controller;


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
public class SelectCourseController {

    @Autowired
    private SelectCourseService selectCourseService;

    @ApiOperation("选择课程")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "stuId",value = "学生id", required = true, paramType = "query",dataType ="int"),
            @ApiImplicitParam( name = "courseId",value = "课程id", required = true, paramType = "query",dataType ="int"),
    })
    @RequestMapping(value = "/selectCourse",method = RequestMethod.POST)
    public Object selectCourse(int stuId,int courseId){
        return Result.send(selectCourseService.pickCourse(stuId,courseId));
    }
}