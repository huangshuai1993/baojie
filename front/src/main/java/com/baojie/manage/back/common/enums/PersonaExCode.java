package com.baojie.manage.back.common.enums;

import com.baojie.manage.base.exception.IMessageCode;

public enum PersonaExCode implements IMessageCode {

	PERSONA_ISEXIST("120000", "角色已经存在"),
	PERSONA_ISNULL_EMPL("120001","员工角色关系不存在")
	;
	
	private String code;
    private String message;

    private PersonaExCode(String code, String message) {
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
