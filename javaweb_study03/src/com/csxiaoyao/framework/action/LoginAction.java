package com.csxiaoyao.framework.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csxiaoyao.entity.User;
import com.csxiaoyao.service.UserService;

/**
 * Action表示动作类 1. 一个servlet对应一个action 2. action中负责处理具体的请求
 */
public class LoginAction {
	
	public Object execute(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		return null;
	}

	/**
	 * 处理登陆请求
	 */
	public Object login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri = null;

		// 1. 获取请求数据，封装
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);

		// 2. 调用Service
		UserService userService = new UserService();
		User userInfo = userService.login(user);
		// 3. 跳转
		if (userInfo == null) {
			// 登陆失败
//			request.getRequestDispatcher("/login.jsp").forward(request,
//					response);
//			uri = request.getRequestDispatcher("/login.jsp");
			uri = "loginFaild";   // loginFaild  = /login.jsp
		} else {
			// 登陆成功
			request.getSession().setAttribute("userInfo", userInfo);
			// 首页
//			response.sendRedirect(request.getContextPath() + "/index.jsp");
//			uri = "/index.jsp";
			uri = "loginSuccess";  // loginSuccess = /index.jsp
		}
		return uri;
	}
}
