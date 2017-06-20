package com.csxiaoyao.springmvc.util.textfilter;


public class SimpleTextFilter implements TextFilter {

	@Override
	public boolean isTextLegal(String text) {
		return text != null && !text.equals("");
	}
	
}
