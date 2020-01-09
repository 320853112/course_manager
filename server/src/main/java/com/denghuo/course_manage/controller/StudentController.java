package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.AOP.Access;
import com.denghuo.course_manage.DTO.CourseScoreDTO;
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
@Access(role = 1,comment = "学生")
public class StudentController {
    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/getStuInfo",method = RequestMethod.GET)
    @ApiOperation("获得学生信息")
    @ApiImplicitParams({
        @ApiImplicitParam( name = "id",value = "学号", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "name",value = "名称", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "college",value = "学院", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "major",value = "专业", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "class_name",value = "班级名称", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "pageNum",value = "页数", required = true, paramType = "query",dataType ="Integer"),
        @ApiImplicitParam( name = "pageSize",value = "每页记录数", required = true, paramType = "query",dataType ="Integer")
    })
    @Access(role = 3,comment = "学生")
    public Object getStuInfo(Student student ,Integer pageNum,Integer pageSize){
        return Result.send(studentService.getStuInfo(student,pageNum,pageSize));
    }

    @RequestMapping(value = "/updateStuInfo",method = RequestMethod.POST)
    @ApiOperation("修改学生信息")
    @ApiImplicitParams({
        @ApiImplicitParam( name = "id",value = "学生id", required = true, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "name",value = "名称", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "college",value = "学院", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "major",value = "专业", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "class_name",value = "班级名称", required = false, paramType = "query",dataType ="String"),
        @ApiImplicitParam( name = "password",value = "密码", required = false, paramType = "query",dataType ="String")
    })
        public Object updateStuInfo(Student student ){
        return Result.send(studentDAO.updateStuInfo(student));
    }

    @RequestMapping(value = "/deleteStuInfo",method = RequestMethod.POST)
    @ApiOperation("删除学生信息")
    @ApiImplicitParams(
            @ApiImplicitParam( name = "id",value = "学生id", required = true, paramType = "query",dataType ="int")
    )
    public Object deleteStuInfo(Integer id ){
        return Result.send(studentDAO.deleteStuInfo(id));
    }

    @RequestMapping(value = "/getStuCourse",method = RequestMethod.GET)
    @ApiOperation("查询学生已选择的课程")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "id",value = "学生id", required = true, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "startTime",value = "开始时间", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "name",value = "课程名称", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "category",value = "课程性质", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "pageNum",value = "页数", required = true, paramType = "query",dataType ="Integer"),
            @ApiImplicitParam( name = "pageSize",value = "每页记录数", required = true, paramType = "query",dataType ="Integer")
    })
    public Object getStuCourse(CourseScoreDTO courseScoreDTO,Integer pageNum, Integer pageSize){
        return studentService.getStuCourse(courseScoreDTO,pageNum,pageSize);
    }

    @RequestMapping(value = "/insertStuInfo",method = RequestMethod.POST)
    @ApiOperation("添加学生信息")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "id",value = "学生id", required = true, paramType = "query",dataType ="String"),
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
