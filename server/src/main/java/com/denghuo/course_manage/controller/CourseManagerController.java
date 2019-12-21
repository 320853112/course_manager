package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Api(value = "courseAPI",tags = "课程信息相关")
public class CourseManagerController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("修改课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "id",value = "管理员id", required = true, paramType = "query",dataType ="int"),
            @ApiImplicitParam( name = "username",value = "用户名", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "password",value = "密码", required = false, paramType = "query",dataType ="String")

    })
    public Object updateCourse(){
        
    }

}
