package com.csxiaoyao.contactSys_web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 安全退出：
		 * 	删除session对象中指定的loginName属性
		 */
		//1.得到session对象
		HttpSession session = request.getSession(false);
		if(session!=null){
			//2.删除属性
			session.removeAttribute("loginName");
			//手动销毁
//			session.invalidate();//手动销毁
		}
		//3.回登录页
		response.sendRedirect(request.getContextPath()+"/login.html");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}