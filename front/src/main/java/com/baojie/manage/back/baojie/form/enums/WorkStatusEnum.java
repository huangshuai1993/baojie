package com.baojie.manage.back.baojie.form.enums;

/**
 * 工作状态枚举
 * @author huangshuai
 *
 * @date 2018年12月12日
 */
public enum WorkStatusEnum {
	/**
	 */
	WORK(0,"在职"),
	WORK_NO(1,"离职")
	;
	
	
	private int code;
    
    private String name;

    private WorkStatusEnum(int code,String name) {
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
        for (WorkStatusEnum string : WorkStatusEnum.values()) {
        	  if(string.getCode() == code){
           	   		return true;
              }
        }
        return false;
    }
    
    public static String getName(int code) {
        for (WorkStatusEnum string : WorkStatusEnum.values()) {
            if(string.getCode() == code) {
                return string.getName();
            }
        }
        return null;
    }
}
