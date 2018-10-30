package com.baojie.manage.back.common.enums;

import com.baojie.manage.base.exception.IMessageCode;

public enum LoginExCode implements IMessageCode {
    NOTUSER("1", "用户不存在"), 
    ERRORPASSWORD("2","密码错误"),
    DISABLE("3","员工被禁用")
    ;
    private String code;
    private String message;

    private LoginExCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

}
