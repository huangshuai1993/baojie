package com.baojie.manage.base.exception;

public class BizException extends Exception implements IMessageCode {

	private static final long serialVersionUID = -2257306174497157173L;

	private static final String DEFAULT_ERRCODE = "00000";

	private String errCode;

	public BizException(IMessageCode messageCode) {
		super(messageCode.message());
		setErrCode(messageCode);
	}

	public BizException(IMessageCode messageCode, Throwable cause) {
		super(messageCode.message(), cause);
		setErrCode(messageCode);
	}

	public BizException(String code, String message) {
		super(message);
		setErrCode(code);
	}

	public BizException(String message) {
		super(message);
		setErrCode(DEFAULT_ERRCODE);
	}

	private void setErrCode(IMessageCode messageCode) {
		setErrCode(messageCode.code());
	}

	private void setErrCode(String code) {
		this.errCode = code;
	}

	public String code() {
		return errCode;
	}

	public String message() {
		return getMessage();
	}
}
