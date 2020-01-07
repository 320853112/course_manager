package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@Api(value = "studentAPI",tags = "配置相关")
public class ConfigController {
    @RequestMapping(value = "/restartFont",method = RequestMethod.POST)
    public Object restartFont(String key) throws Exception{
        if(!key.equals("denghuo")){
            return Result.error() ;
        }
        Process ps = Runtime.getRuntime().exec("sh /MyShell/restart_manage_front.sh");
        return Result.send();
    }

}
