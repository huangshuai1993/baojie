package com.baojie.manage.back.baojie.form.enums;

/**
 * 物料类型枚举
 * @author huangshuai
 *
 * @date 2018年12月12日
 */
public enum MaterialTypeEnum {
	/**
	 */
	CONSUMABLES(0,"消耗资产"),
	FIXEDASSETS(1,"固定资产")
	;
	
	
	private int code;
    
    private String name;

    private MaterialTypeEnum(int code,String name) {
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
        for (MaterialTypeEnum string : MaterialTypeEnum.values()) {
        	  if(string.getCode() == code){
           	   		return true;
              }
        }
        return false;
    }
    
    public static String getName(int code) {
        for (MaterialTypeEnum string : MaterialTypeEnum.values()) {
            if(string.getCode() == code) {
                return string.getName();
            }
        }
        return null;
    }
}
