package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.dao.StudentDAO;
import com.denghuo.course_manage.model.Student;
import com.denghuo.course_manage.service.StudentService;
import com.denghuo.course_manage.utils.CustomException;
import com.denghuo.course_manage.utils.MD5util;
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

    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/getStuInfo",method = RequestMethod.GET)
    @ApiOperation("获得学生信息")
    @ApiImplicitParams({
        @ApiImplicitParam( name = "id",value = "学号", required = false, paramType = "query",dataType ="int"),
        @ApiImplicitParam( name = "name",value = "名称", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "college",value = "学院", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "major",value = "专业", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "class_name",value = "班级名称", required = false, paramType = "query",dataType ="String")
    })
    public Object getStuInfo(Student student ){
        return Result.send(studentDAO.getStuInfo(student));
    }

    @RequestMapping(value = "/updateStuInfo",method = RequestMethod.GET)
    @ApiOperation("修改学生信息")
    @ApiImplicitParams({
        @ApiImplicitParam( name = "id",value = "学生id", required = true, paramType = "query",dataType ="int"),
        @ApiImplicitParam( name = "name",value = "名称", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "college",value = "学院", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "major",value = "专业", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "class_name",value = "班级名称", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "password",value = "密码", required = false, paramType = "query",dataType ="String")
    })
        public Object updateStuInfo(Student student ){
        return Result.send(studentDAO.updateStuInfo(student));
    }

    @RequestMapping(value = "/deleteStuInfo",method = RequestMethod.GET)
    @ApiOperation("删除学生信息")
    @ApiImplicitParams(
            @ApiImplicitParam( name = "id",value = "学生id", required = true, paramType = "query",dataType ="int")
    )
    public Object deleteStuInfo(Integer id ){
        return Result.send(studentDAO.deleteStuInfo(id));
    }

    @RequestMapping(value = "/insertStuInfo",method = RequestMethod.GET)
    @ApiOperation("添加学生信息")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "id",value = "学生id", required = true, paramType = "query",dataType ="int"),
            @ApiImplicitParam( name = "name",value = "名称", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "college",value = "学院", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "major",value = "专业", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "class_name",value = "班级名称", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "password",value = "密码", required = false, paramType = "query",dataType ="String")
    })
    public Object insertStuInfo(Student student ){
        return Result.send(studentService.insertStuInfo(student));
    }
}
