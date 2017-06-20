package com.csxiaoyao.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csxiaoyao.bean.UserBean;
import com.csxiaoyao.dao.UserDao;

public class RegisterAction {
	public String register(HttpServletRequest request, HttpServletResponse response) {
		String info = request.getParameter("info");
		HttpSession session = request.getSession();
		session.setAttribute("info", "注册信息："+info);
		return "register";

	}
}
