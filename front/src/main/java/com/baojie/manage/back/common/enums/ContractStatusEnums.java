package com.baojie.manage.back.common.enums;

/**
 * 合同履行情况：1:正常履行、2:到期终止、3:变更、4：解除
 * 
 * @author huangshuai
 *
 * @date 2018年11月29日
 */
public enum ContractStatusEnums {
	NORMAL(1, "正常履行"), TERMINATION(2, "到期终止"), ALTERATION(3, "变更"), RELIEVE(4, "解除");

	private int code;
	private String name;

	private ContractStatusEnums(int code, String name) {
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
		for (ContractStatusEnums string : ContractStatusEnums.values()) {
			if (string.getCode() == code) {
				return true;
			}
		}
		return false;
	}

	public static String getName(int code) {
		for (ContractStatusEnums string : ContractStatusEnums.values()) {
			if (string.getCode() == code) {
				return string.getName();
			}
		}
		return null;
	}

}
