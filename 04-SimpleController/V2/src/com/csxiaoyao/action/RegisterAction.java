package com.csxiaoyao.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csxiaoyao.bean.UserBean;
import com.csxiaoyao.dao.UserDao;

public interface RegisterAction {
	public String register(HttpServletRequest request, HttpServletResponse response);
}
