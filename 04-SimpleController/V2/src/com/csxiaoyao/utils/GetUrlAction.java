package com.csxiaoyao.utils;

public class GetUrlAction {
	
	public static String getActionName(String url){
		String[] split = url.split("/");
		String actionName = split[split.length-1];
		return actionName.substring(0, actionName.lastIndexOf("."));
	}

}
