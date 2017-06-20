package com.csxiaoyao.contactSys_web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csxiaoyao.contactSys_web.entity.Contact;
import com.csxiaoyao.contactSys_web.service.ContactService;
import com.csxiaoyao.contactSys_web.service.impl.ContactServiceImpl;
/**
 * 
 * @ClassName: ListContactServlet
 * @Description: 显示所有联系人的逻辑
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年1月21日 上午11:45:31
 *
 */
public class ListContactServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		/**
		 * 第一部分：session验证登陆
		 * 判断session不为空且存在指定的属性才视为登录成功
		 * 从session域中获取会话数据
		 */
//		PrintWriter writer = response.getWriter();
//		String html = "";
		//1.得到session对象
		HttpSession session = request.getSession(false);
		if(session==null){
			//没有登录成功，跳转到登录页面
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		//2.取出会话数据
		String loginName = (String)session.getAttribute("loginName");
		if(loginName==null){
			//没有登录成功，跳转到登录页面
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
//		html = "<html><body>欢迎回来，"+loginName+"，<a href='"+request.getContextPath()+"/LogoutServlet'>退出</a></body></html>";
//		writer.write(html);
		request.setAttribute("loginName", loginName);
		/**
		 * 第二部分：cookies操作
		 */
		//获取当前时间，记录时间存cookies
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String curTime = format.format(new Date());
		//【1.取得cookie】
		Cookie[] cookies = request.getCookies();
		String lastTime = null;
		if(cookies!=null){
			//【2.遍历查找cookie】
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("lastTime")){
					lastTime = cookie.getValue();
				    //【3.更新cookie】
					cookie.setValue(curTime);
					cookie.setMaxAge(1*30*24*60*60);
					cookie.setPath("/");
					//【4.把更新后的cookie发送到浏览器】
					response.addCookie(cookie);
					break;
				}
			}
		}
		//第一次访问
		if(cookies==null || lastTime==null){
			//【1.创建Cookie对象】
			Cookie cookie = new Cookie("lastTime",curTime);
			//【2.设置cookie的有效路径】。默认有效路径在当前web应用下
//			cookie.setPath("/project");
			cookie.setPath("/");
			//【3.设置cookie的有效时间】
			cookie.setMaxAge(1*30*24*60*60); //保存一个月，单位为秒，从最后不调用cookie开始计算
			//cookie.setMaxAge(-1); //cookie保存在浏览器内存（会话cookie）,浏览器关闭即丢失
			//cookie.setMaxAge(0); //表示删除同名的cookie数据
			//【4.把cookie发送到浏览器保存】
			//通过响应头发送：set-cookie名称
			//response.setHeader("set-cookie", cookie.getName()+"="+cookie.getValue()+",lastTime="+curTime);
			//推荐使用这种方法，避免手动发送cookie信息
			response.addCookie(cookie);
		}
		//【删除cookie】
		/*Cookie cookie = new Cookie("lastTime","x");
		cookie.setMaxAge(0);//删除同名的cookie
		response.addCookie(cookie);*/
		request.setAttribute("lastTime", lastTime);
		/**
		 * 第三部分：读取数据展示
		 */
		//1.从xml中读取出联系人数据
		ContactService service = new ContactServiceImpl();
		List<Contact> list = service.findAll();
		//2.把结果保存到域对象中
		request.setAttribute("contacts", list);
		//3.跳转到jsp页面，转发
		request.getRequestDispatcher("/listContact.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
