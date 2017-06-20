package com.csxiaoyao.contactSys_web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//接收参数
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		/**
		 * request、response对象操作
		 */
		//仅用作测试，当输入姓名sun时返回404状态码
		if("sun".equals(userName)){
			//得到referer头
			String referer = request.getHeader("referer");
			System.out.println("referer="+referer);//referer=http://localhost:8080/javaweb_study01/login.html
			//判断非法链接
			if(referer==null || !referer.contains("/login.html")){
				response.getWriter().write("当前是非法链接");
			}			
			//【request对象】
			System.out.println("请求方式："+request.getMethod());//请求方式
			System.out.println("URI:"+request.getRequestURI());///javaweb_study01/LoginServlet
			System.out.println("URL:"+request.getRequestURL());//http://localhost:8080/javaweb_study01/LoginServlet
			System.out.println("http协议版本："+request.getProtocol());//http协议
			//【通过response对象改变响应信息】
//			response.setStatus(404);//修改状态码
//			response.sendError(404); // 发送404的状态码+404的错误页面
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			request.getRequestDispatcher("/error.jsp").forward(request,response);
			return;
			//【修改响应头】
//			response.setHeader("server", "SUNSHINE");//修改响应头服务器类型
			//【请求重定向】
//			response.setStatus(302);//发送一个302状态码
//			response.setHeader("location", request.getContextPath()+"/ListContactServlet"); //location的响应头
			//简化写法
//			response.sendRedirect(request.getContextPath()+"/ListContactServlet");
			//定时刷新
//			response.setHeader("refresh", "10"); //每隔10秒刷新次页面
			//response.setHeader("refresh", "3;url=/"+request.getContextPath()+"/login.html");//隔3秒之后跳转
			//【输出实体内容】
			//response.getWriter().write("getWriter()"); //字符内容。
			//response.getOutputStream().write("getOutputStream()".getBytes());//字节内容
			//【设置响应实体内容编码】
			//response.setCharacterEncoding("utf-8");
			//response.setContentType("text/html;charset=utf-8");	
		}
		/**
		 * session 操作
		 */
		//判断逻辑
		if("sunshine".equals(userName) && "19931128".equals(userPwd)){
			//登录成功，把用户数据保存session对象中
			//1.创建session对象
			HttpSession session = request.getSession();
			/* 
			//session的其他操作
			//得到session编号
			System.out.println("id="+session.getId());
			//修改session的有效时间，默认30分钟
			session.setMaxInactiveInterval(20);
			// 手动发送一个硬盘保存的cookie给浏览器	 
			Cookie c = new Cookie("JSESSIONID",session.getId());
			c.setMaxAge(60*60);
			response.addCookie(c);
			*/
			//2.把数据保存到session域中
			session.setAttribute("loginName", userName);
			//3.跳转到用户主页
			response.sendRedirect(request.getContextPath()+"/ListContactServlet");
		}else{
			//登录失败
			//请求重定向
			response.sendRedirect(request.getContextPath()+"/fail.html");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
