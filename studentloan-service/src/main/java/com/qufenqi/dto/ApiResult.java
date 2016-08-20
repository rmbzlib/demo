package com.qufenqi.dto;


/**
 * Created by suzunshou on 16/8/20.
 */
public class ApiResult {
    private String code;
    private String message;
    private Object datas;

    public ApiResult() {
    }

    public ApiResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResult(String code, String message, Object datas) {
        this.code = code;
        this.message = message;
        this.datas = datas;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public static ApiResult build(String code, String message) {
        return new ApiResult(code, message);
    }

    public static ApiResult build(String code, String message, Object datas) {
        return new ApiResult(code, message, datas);
    }
}
