package com.csxiaoyao.springmvc.util.textfilter;


import java.util.regex.Pattern;

public class PhoneNumFilter implements TextFilter {

	@Override
	public boolean isTextLegal(String text) {
		// 保证输入的手机号为11位数字（正则表达式：^[0-9]{11}$）
		return text != null && Pattern.compile("^[0-9]{11}$").matcher(text).matches();
	}

}
