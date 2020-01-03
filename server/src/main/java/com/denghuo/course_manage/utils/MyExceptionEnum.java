package com.denghuo.course_manage.utils;

public enum MyExceptionEnum implements ExceptionInterface{
    SUCCESS(200,"access success"),
    NOT_FIND(404,"resource not found"),
    REQUEST_FAIL(500,"request fail"),
    BAD_GATEWAY(502,"bad gateway"),
    UNKNOWN_ERROR(1001,"happen unknown error"),
    NULL_POINTER(1002,"find a null pointer"),
    ACCESS_FAIL(1003,"data access fail"),
    AUTHORITY_LACK(1004,"authority is lack"),
    PLEASE_LOGIN(1005,"please login first")
    ;


    private String errorMsg;
    private Integer errorCode;

    MyExceptionEnum(java.lang.Integer code, String desp) {
        this.errorMsg = desp;
        this.errorCode = code;
    }

    MyExceptionEnum(String errorMsg, Integer errorCode) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
