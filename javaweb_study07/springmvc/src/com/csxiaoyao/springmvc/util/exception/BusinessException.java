package com.csxiaoyao.springmvc.util.exception;

import com.csxiaoyao.springmvc.util.Constants;

// 业务异常，包含异常码和异常信息
public class BusinessException extends Exception {

	private int code;
	private String msg;

	public BusinessException() {
		this.code = Constants.EXEP_CODE_UNKNOWN;
		this.msg = Constants.EXEP_MSG_UNKNOWN;
	}

	public BusinessException(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
