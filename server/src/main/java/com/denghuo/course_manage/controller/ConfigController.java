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
    public Object restartFont(String key){
        if(!key.equals("denghuo")){
            return Result.error() ;
        }
        ProcessBuilder pb = new ProcessBuilder("/MyShell/restart_manage_front.sh");

        int runningStatus = 0;
        try {
            Process p = pb.start();
            try {
                runningStatus = p.waitFor();
            } catch (InterruptedException e) {
            }

        } catch (IOException e) {
        }
        if (runningStatus != 0) {
            return Result.error() ;
        }
        return Result.send();
    }

    @RequestMapping(value = "/testAPI",method = RequestMethod.POST)
    public Object testAPI(String key){
        ProcessBuilder pb = new ProcessBuilder("/MyShell/test.sh");

        int runningStatus = 0;
        try {
            Process p = pb.start();
            try {
                runningStatus = p.waitFor();
            } catch (InterruptedException e) {
            }

        } catch (IOException e) {
        }
        if (runningStatus != 0) {
            return Result.error() ;
        }
        return Result.send();
    }
}
