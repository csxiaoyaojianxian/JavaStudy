package com.csxiaoyao.springmvc.response;

// 返回数据集
public class ResponseSet {

	protected int code;
	protected String message;
	
	public ResponseSet(){
		
	}
	public ResponseSet(int code, String message){
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
