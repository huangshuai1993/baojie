package com.baojie.manage.back.common.enums;

public enum ContractTypeEnums {
	SERVICE(0, "服务");

	private int code;
	private String name;

	private ContractTypeEnums(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static boolean exist(int code) {
		for (ContractTypeEnums string : ContractTypeEnums.values()) {
			if (string.getCode() == code) {
				return true;
			}
		}
		return false;
	}

	public static String getName(int code) {
		for (ContractTypeEnums string : ContractTypeEnums.values()) {
			if (string.getCode() == code) {
				return string.getName();
			}
		}
		return null;
	}


}
