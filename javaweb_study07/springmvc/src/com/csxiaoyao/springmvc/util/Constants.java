package com.csxiaoyao.springmvc.util;

// 静态常量
public class Constants {
	
	public static final String WEB_TOKEN = "web";
	
	// 性别，男性为0，女性为1
	public static final int GENDER_MALE = 0;
	public static final int GENDER_FEMALE = 1;
	
	// 关键词分隔符
	public static final char KEYWORD_SEPARATOR = ',';

	// 请求成功响应信息
	public static final int RESP_CODE_SUCCESS = 200;
	public static final String RESP_MSG_SUCCESS = "成功";
	// 异常响应信息
	public static final int EXEP_CODE_UNKNOWN = 900;
	public static final String EXEP_MSG_UNKNOWN = "未知错误";
	public static final int EXEP_CODE_TOKEN_FAILED = 101;
	public static final String EXEP_MSG_TOKEN_FAILED = "用户鉴权失败";
	public static final int EXEP_CODE_USER_EXIST = 102;
	public static final String EXEP_MSG_USER_EXIST = "用户已存在";
	public static final int EXEP_CODE_USER_UNFOUND = 103;
	public static final String EXEP_MSG_USER_UNFOUND = "用户不存在";
	public static final int EXEP_CODE_INCORRECT_PASSWORD = 104;
	public static final String EXEP_MSG_INCORRECT_PASSWORD = "密码错误";
	public static final int EXEP_CODE_ILLEGAL_INPUT_PHONE = 105;
	public static final String EXEP_MSG_ILLEGAL_INPUT_PHONE = "手机号输入为空或非法";
	public static final int EXEP_CODE_ILLEGAL_INPUT_PWD = 106;
	public static final String EXEP_MSG_ILLEGAL_INPUT_PWD = "密码输入为空或非法";
	public static final int EXEP_CODE_ILLEGAL_INPUT_NAME = 107;
	public static final String EXEP_MSG_ILLEGAL_INPUT_NAME = "用户名输入为空或非法";

}
