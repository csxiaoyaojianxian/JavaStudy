package com.csxiaoyao;
/**
 * 题目描述
请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 问题：
 * 1、StringBuffer概念
 */
public class nowcoder20170217替换空格 {
	public static String replaceSpace(StringBuffer str) {
    	for(int i = 0; i < str.length(); i++){
    		if(str.charAt(i) == ' '){
    			str.deleteCharAt(i);
    			str.insert(i, "%20");
    		}
    	}
		return str.toString();
    }
	public static void main(String[] args) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" sunshine studio ");
		System.out.println(replaceSpace(stringBuffer));
	}
}
