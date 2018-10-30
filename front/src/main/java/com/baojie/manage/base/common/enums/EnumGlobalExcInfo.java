package com.baojie.manage.base.common.enums;

import com.baojie.manage.base.exception.IMessageCode;

public enum EnumGlobalExcInfo implements IMessageCode {

	GLOBAL_OPERATION_ERROR("600001", "操作失败");

	private String code;
	private String message;

	private EnumGlobalExcInfo(String code, String message) {
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
