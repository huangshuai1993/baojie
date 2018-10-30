package com.baojie.manage.back.common.enums;

import com.baojie.manage.base.exception.IMessageCode;

public enum ExampleExCode implements IMessageCode {

	EXAMPLE_NOT_FOUND("10000", "例示信息未找到"),
	CONVERT_FORM_ERR("20001" + "205", "转换form对象失败"),
	CONVERT_DTO_ERR("20001" + "204", "转换DTO对象失败"),
	ID_IS_NULL("20001" + "101", "id参数为空"),
	;
	
	private String code;
    private String message;

    private ExampleExCode(String code, String message) {
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
