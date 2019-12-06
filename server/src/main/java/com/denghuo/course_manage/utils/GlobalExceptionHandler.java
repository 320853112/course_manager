package com.denghuo.course_manage.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Log log = LogFactory.getLog(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public Result customExceptionHandler(HttpServletRequest req, CustomException e){
        //打印异常信息
        log.debug(e.getErrorMsg());
        return Result.error(e);
    }

    /**
     * 处理空指针的异常
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e){
        e.printStackTrace();
        return Result.error(MyExceptionEnum.NULL_POINTER);
    }


    /**
     * 处理其他异常
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, Exception e){
        e.printStackTrace();
        return Result.error(MyExceptionEnum.UNKNOWN_ERROR);
    }
}
