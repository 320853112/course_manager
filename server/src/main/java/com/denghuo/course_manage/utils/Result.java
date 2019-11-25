package com.denghuo.course_manage.utils;


public class Result {
    private boolean status = true;
    private Integer code = 200;
    private String message = "request_success";
    private Object data;

    public static Result send(){
        return new Result();
    }

    public static Result send(Object data){
        Result result = new Result();
        result.setData(data);
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setStatus(false);
        result.setCode(MyExceptionEnum.REQUEST_FAIL.getErrorCode());
        result.setMessage(MyExceptionEnum.REQUEST_FAIL.getErrorMsg());
        return result;
    }

    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setStatus(false);
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static Result error(ExceptionInterface e) {
        Result result = new Result();
        result.setStatus(false);
        result.setCode(e.getErrorCode());
        result.setMessage(e.getErrorMsg());
        return result;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
