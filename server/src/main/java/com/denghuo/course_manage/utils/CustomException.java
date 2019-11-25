package com.denghuo.course_manage.utils;

public class CustomException extends RuntimeException implements ExceptionInterface{
    private Integer errorCode;

    private String errorMsg;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public CustomException( Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CustomException(ExceptionInterface e) {
        this.errorCode = e.getErrorCode();
        this.errorMsg = e.getErrorMsg();
    }
}
