package com.qufenqi.enums;

/**
 * Created by suzunshou on 16/8/20.
 */
public enum EAlipay {
    NOT_NULL("500","not null","不能为空"),
    SUCCESS("10000", "success", "业务处理成功"),
    UNKNOWN_ERROR("20000", "unknown_error", "服务不可用"),
    INVALID_AUTH_TOKEN("20001", "invalid-auth-token", "授权权限不足"),
    NOT_CERTIFIED("20002","not certified","没有通过实名认证"),
    NOT_ID_AUTH("20003","not id auth","没有身份证认证"),
    NOT_STUDENT_CERTIFIED("20004","not student_certified","不是学生"),
    GRATUATED("20005","gratuated","已经毕业");

    private String code;
    private String msg;
    private String chineseMessage;

    EAlipay(String code, String msg, String chineseMessage) {
        this.code = code;
        this.msg = msg;
        this.chineseMessage = chineseMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getChineseMessage() {
        return chineseMessage;
    }

    public void setChineseMessage(String chineseMessage) {
        this.chineseMessage = chineseMessage;
    }
}
