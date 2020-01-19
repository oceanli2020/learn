package com.lihaiyang.learn.core.result;


import lombok.Data;

@Data
public class Result {
    private int code = 200;
    private String message = "成功";
    private Object data;

    public Result() {
    }

    public Result(ResultStatus resultStatus, Object data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    public static Result ofSuccess(Object data) {
        return new Result(ResultStatus.SUCCESS, data);
    }




}
