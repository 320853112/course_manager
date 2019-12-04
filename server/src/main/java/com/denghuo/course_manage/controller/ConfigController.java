package com.denghuo.course_manage.controller;

import com.denghuo.course_manage.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class ConfigController {
    @RequestMapping("/restartFont")
    public Object restartFont(){
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
}
