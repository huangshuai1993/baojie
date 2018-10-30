package com.baojie.manage.back.common.enums;

import com.baojie.manage.base.exception.IMessageCode;

public enum GeneralExCode implements IMessageCode {

	SYS_ERROR("00000", "系统异常"),
	USER_NOT_LOGIN("00001", "用户未登录"),
	NO_QUERY_RESULTS("00002", "没有查询到结果"),
	PARAME_NULL("00003", "参数为空")
	;
	
	private String code;
    private String message;

    private GeneralExCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
	
    public static GeneralExCode getEnum(String code) {
        for (GeneralExCode e : GeneralExCode.values()) {
            if (e.code().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
