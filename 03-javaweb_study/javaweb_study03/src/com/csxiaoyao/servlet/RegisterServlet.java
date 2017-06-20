package com.csxiaoyao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csxiaoyao.entity.User;
import com.csxiaoyao.framework.action.LoginAction;
import com.csxiaoyao.framework.action.RegisterAction;
import com.csxiaoyao.service.UserService;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RegisterAction registerAction = new RegisterAction();
		Object uri = registerAction.register(request, response);
		
		// 配置文件---》jsp
		
		// 跳转
		if (uri instanceof String) {
			response.sendRedirect(request.getContextPath() + uri.toString());
		} else {
			((RequestDispatcher)uri).forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
