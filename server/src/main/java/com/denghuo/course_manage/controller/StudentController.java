package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.dao.StudentDAO;
import com.denghuo.course_manage.utils.CustomException;
import com.denghuo.course_manage.utils.MyExceptionEnum;
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
@Api(value = "studentAPI",tags = "学生相关")
public class StudentController {
    @Autowired
    private StudentDAO studentDAO;


    @RequestMapping(value = "/getStuInfo",method = RequestMethod.GET)
    @ApiOperation("到达商品的首页")
    @ApiImplicitParams(
            @ApiImplicitParam( name = "id",value = "学生id", required = true, paramType = "query",dataType ="int")
    )
    public Object sendMessage(Integer id ){
        System.out.println("ok");
        return Result.send(studentDAO.getStuInfo(id));
    }

}
