package com.baojie.manage.base.exception;

public class DaoException extends RuntimeException implements IMessageCode {

	private static final long serialVersionUID = -4770191447529986129L;

	private static final String DEFAULT_ERRCODE = "10000";

	private String errCode;

	public DaoException(IMessageCode messageCode) {
		super(messageCode.message());
		setErrCode(messageCode);
	}

	public DaoException(String message) {
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
