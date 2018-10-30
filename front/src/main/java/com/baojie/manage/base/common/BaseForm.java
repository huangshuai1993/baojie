package com.baojie.manage.base.common;

import java.io.Serializable;

import com.baojie.manage.base.exception.IMessageCode;

public abstract class BaseForm implements Serializable {

	private static final long serialVersionUID = -1993108182582839759L;

	/**
	 * 状态值
	 */
	private String code = "0";

	/**
	 * 错误信息
	 */
	private String msg = "ok";

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setMessage(IMessageCode messagecode) {
		this.setCode(messagecode.code());
		this.setMsg(messagecode.message());
	}

	public void setMessage(String status, String message) {
		this.setCode(status);
		this.setMsg(message);
	}
}
