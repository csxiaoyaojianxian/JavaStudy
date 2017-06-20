package com.csxiaoyao.framework;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csxiaoyao.framework.bean.ActionMapping;
import com.csxiaoyao.framework.bean.ActionMappingManager;
import com.csxiaoyao.framework.bean.Result;

/**
 * 核心控制器,此项目只有这一个servlet
 * 1. 拦截所有的*.action为后缀的请求
 * 2. 请求:http://localhost:8080/mystruts/login.action
 * 		  http://localhost:8080/mystruts/register.action
 *
 */
public class ActionServlet extends HttpServlet{
	
	private ActionMappingManager actionMappingManager;
	
	// 只执行一次  (希望启动时候执行)
	@Override
	public void init() throws ServletException {
		System.out.println("ActionServlet.init()");
		actionMappingManager = new ActionMappingManager();
	}

	// http://localhost:8080/mystruts/login.action
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			// 1. 获取请求uri, 得到请求路径名称   【login】
			String uri = request.getRequestURI();
			// 得到 login
			String actionName=uri.substring(uri.lastIndexOf("/")+1, uri.indexOf(".action"));
			
			// 2. 根据路径名称，读取配置文件，得到类的全名   【cn..action.LoginAction】
			ActionMapping actionMapping = actionMappingManager.getActionMapping(actionName);
			String className = actionMapping.getClassName();
			
			// 当前请求的处理方法   【method="login"】
			String method = actionMapping.getMethod();
			
			// 3. 反射： 创建对象，调用方法； 获取方法返回的标记
			Class<?> clazz = Class.forName(className);
			Object obj = clazz.newInstance();  //LoginAction loginAction = new LoginAction();
			Method m = clazz.getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class );
			// 调用方法返回的标记
			String returnFlag =  (String) m.invoke(obj, request, response);
			
			// 4. 拿到标记，读取配置文件得到标记对应的页面 、 跳转类型
			Result result = actionMapping.getResults().get(returnFlag);
			// 类型
			String type = result.getType();
			// 页面
			String page = result.getPage();
			
			// 跳转
			if ("redirect".equals(type)) {
				response.sendRedirect(request.getContextPath() + page);
			} else {
				request.getRequestDispatcher(page).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
