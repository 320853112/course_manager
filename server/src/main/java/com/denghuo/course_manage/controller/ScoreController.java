package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.AOP.Access;
import com.denghuo.course_manage.model.StuToCourse;
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
@Api(value = "ScoreAPI",tags = "分数相关")
@Access(role = 2,comment = "老师")
public class ScoreController {

    @Autowired
    private SelectCourseService selectCourseService;

    @ApiOperation("给课程打分")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "stuId",value = "学生id", required = true, paramType = "query",dataType ="int"),
            @ApiImplicitParam( name = "courseId",value = "课程id", required = true, paramType = "query",dataType ="int"),
            @ApiImplicitParam( name = "score",value = "分数", required = true, paramType = "query",dataType ="int"),
    })
    @RequestMapping(value = "/setScoreByStu",method = RequestMethod.POST)
    public Object setScoreByStu(StuToCourse stuToCourse){
        return Result.send(selectCourseService.setScoreByStu(stuToCourse));
    }
}