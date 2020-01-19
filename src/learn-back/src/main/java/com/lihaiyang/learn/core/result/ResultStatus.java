package com.lihaiyang.learn.core.result;


public enum ResultStatus {

    SUCCESS(200, "成功"),
    FAIL(-200, "失败"),
    FAIL_UNKNOWN_REASON(-201, "系统忙,请稍后再试"),
    AUTH_UNAUTHORIZED(-401, "请重新登录"),
    USERNAME_OR_PASSWORD_ERROR(-1001, "用户名或密码错误"),
    USER_NOT_FOUND(-1002, "用户不存在"),
    DATA_NOT_NULL(-1004, "字段不能为空"),
    USER_ALREADY_EXIST(-1005, "用户已存在"),
    USER_PASS_EDIT_FAIL(-1008, "密码不正确"),
    DATA_NOT_POWER(-4004, "无权操作");

    private int code;
    private String message;

    private ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
