package com.baojie.manage.back.common.enums;

import com.baojie.manage.base.exception.IMessageCode;

public enum EmployeeExCode implements IMessageCode{
    EMPLOYEE_NOT_FOUND("10000", "用户未找到"),
    PERSONA_NOT_FOUND("10002","角色信息未找到"),
	EMPLOYEE_ADD_FAIL("10003","添加员工失败");
    ;

    private String code;
    private String message;
    
    private EmployeeExCode(String code, String message){
        this.code = code;
        this.message = message;
    }
    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

}
