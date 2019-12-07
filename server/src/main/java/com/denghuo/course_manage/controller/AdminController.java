package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.model.Admin;
import com.denghuo.course_manage.service.AdminService;
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
@Api(value = "adminAPI",tags = "管理员相关")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("修改管理员信息")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "id",value = "管理员id", required = true, paramType = "query",dataType ="int"),
            @ApiImplicitParam( name = "username",value = "用户名", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "password",value = "密码", required = false, paramType = "query",dataType ="String")

    })
    @RequestMapping(value = "/updateAdmin",method = RequestMethod.POST)
    public Object updateAdmin(Admin admin){
        adminService.updateAdmin(admin);
        return Result.send();
    }

    @ApiOperation("添加管理员")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "username",value = "用户名", required = false, paramType = "query",dataType ="String"),
            @ApiImplicitParam( name = "password",value = "密码", required = false, paramType = "query",dataType ="String")

    })
    @RequestMapping(value = "/insertAdmin",method = RequestMethod.POST)
    public Object insertAdmin(String username,String password){
        adminService.insertAdmin(username,password);
        return Result.send();
    }

    @ApiOperation("查询管理员")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "username",value = "用户名", required = true, paramType = "query",dataType ="String"),

    })
    @RequestMapping(value = "/getAdmin",method = RequestMethod.POST)
    public Object getAdmin(String username){
        return Result.send(adminService.getAdmin(username));
    }

    @ApiOperation("删除管理员")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "id",value = "用户名", required = true, paramType = "query",dataType ="int"),

    })
    @RequestMapping(value = "/deleteAdmin",method = RequestMethod.POST)
    public Object deleteAdmin(Integer id){
        adminService.deleteAdmin(id);
        return Result.send();
    }
}
