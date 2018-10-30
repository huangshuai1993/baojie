package com.baojie.manage.base.common;

import java.io.Serializable;

import com.baojie.manage.base.exception.IMessageCode;

public class Response<T> implements Serializable {
	private static final long serialVersionUID = 2318866198328783813L;

	/**
	 * 状态值
	 */
	private String code = "0";

	/**
	 * 错误信息
	 */
	private String info;

	/**
	 * 验证码
	 */
	private String nbCode;

	/**
	 * 数据
	 */
	private T data;

	public Response() {
	}

	public Response(T result) {
		this.setData(result);
	}

	public Response(String status, String message) {
		this.setCode(status);
		this.setInfo(message);
	}

	public Response(IMessageCode messagecode) {
		this.setCode(messagecode.code());
		this.setInfo(messagecode.message());
	}

	public void setMessage(IMessageCode messagecode) {
		this.setCode(messagecode.code());
		this.setInfo(messagecode.message());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getNbCode() {
		return nbCode;
	}

	public void setNbCode(String nbCode) {
		this.nbCode = nbCode;
	}
}
