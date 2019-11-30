package com.denghuo.course_manage.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public Result customExceptionHandler(HttpServletRequest req, CustomException e){
        return Result.error(e);
    }

    /**
     * 处理空指针的异常
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e){
        return Result.error(MyExceptionEnum.NULL_POINTER);
    }


    /**
     * 处理其他异常
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, Exception e){
        return Result.error(MyExceptionEnum.UNKNOWN_ERROR);
    }
}
