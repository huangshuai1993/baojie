package com.baojie.manage.back.common.enums;

public enum ContractTypeDetailEnums {
	ZIJIE(0, "自接"), WAIBAO(1, "外包");

	private int code;
	private String name;

	private ContractTypeDetailEnums(int code, String name) {
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
		for (ContractTypeDetailEnums string : ContractTypeDetailEnums.values()) {
			if (string.getCode() == code) {
				return true;
			}
		}
		return false;
	}

	public static String getName(int code) {
		for (ContractTypeDetailEnums string : ContractTypeDetailEnums.values()) {
			if (string.getCode() == code) {
				return string.getName();
			}
		}
		return null;
	}

}
