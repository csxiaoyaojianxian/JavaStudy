package com.csxiaoyao.springmvc.util.textfilter;


// 文本过滤器接口，检查输入文本是否符合规则
public interface TextFilter {

	boolean isTextLegal(String text);
	
}
