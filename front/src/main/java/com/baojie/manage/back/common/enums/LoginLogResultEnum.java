package com.baojie.manage.back.common.enums;

public enum LoginLogResultEnum {
    DEFAULT(0, "默认值"), 
	NOTUSER(1, "用户不存在"), 
	ERRORPASSWORD(2,"密码错误"),
	DISABLE(3,"员工被禁用"),
	OK(4,"登录成功")
	;

	private int id;
	private String desc;

	private LoginLogResultEnum(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static LoginLogResultEnum getEnum(int id) {
        for (LoginLogResultEnum e : LoginLogResultEnum.values()) {
            if (id==e.getId()) {
                return e;
            }
        }
        return null;
    }
}
