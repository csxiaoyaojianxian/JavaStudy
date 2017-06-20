package com.csxiaoyao.controller;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.csxiaoyao.action.LoginAction;
import com.csxiaoyao.action.LoginActionImpl;
import com.csxiaoyao.action.LogoutAction;
import com.csxiaoyao.action.LogoutActionImpl;
import com.csxiaoyao.action.RegisterAction;
import com.csxiaoyao.action.RegisterActionImpl;
import com.csxiaoyao.proxy.ActionProxy;
import com.csxiaoyao.utils.GetUrlAction;

/**
 * 
 * @ClassName: LoginController
 * @Description: 反射、内省
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年2月22日 下午8:12:42
 *
 */
public class LogController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 配置文件路径
		String basePath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/";
		String diPath = basePath +"di.xml" ;
		String controllerPath = basePath + "controller.xml";
		// 获取请求action名
		String requestURI = request.getRequestURI();
		String requestName = GetUrlAction.getActionName(requestURI);
		// 记录返回结果
		String result = null;
		// 记录检索action结果 配置信息
		Element actionElement = null;
		
		// 解析controller.xml和di.xml
		SAXReader controllerReader = new SAXReader(), diReader = new SAXReader();
		Document controllerDoc = null, diDoc = null;
		try {
			controllerDoc = controllerReader.read(new File(controllerPath));
			diDoc = diReader.read(new File(diPath));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		// 是否查找到对应action
		boolean actionFlag = false;
		// 是否查找到对应bean
		boolean beanFlag = false;
		// 是否依赖其他bean
		boolean beanDependFlag = false;
		
		// 遍历action name
		List<Element> actionNodes = controllerDoc.selectNodes("/*/action/name");
		for (Element actionNode : actionNodes) {
			String actionName = actionNode.getText();
			// 1-1 查找到对应的action name
			if (actionName.equals(requestName)) {
				actionFlag = true;
				System.out.println("查找到对应的action name");
				actionElement = actionNode.getParent();
				// 代理
				if ("login".equals(actionName)) {
					LoginAction loginAction = new LoginActionImpl();
					InvocationHandler handler = new ActionProxy(loginAction, request, response, actionElement, requestName);
					LoginAction proxy = (LoginAction) Proxy.newProxyInstance(loginAction.getClass().getClassLoader(), loginAction.getClass().getInterfaces(),handler);
					result = proxy.login(request, response);
				} else if ("logout".equals(actionName)) {
					LogoutAction logoutAction = new LogoutActionImpl();
					InvocationHandler handler = new ActionProxy(logoutAction, request, response, actionElement, requestName);
					LogoutAction proxy = (LogoutAction) Proxy.newProxyInstance(logoutAction.getClass().getClassLoader(), logoutAction.getClass().getInterfaces(), handler);
					result = proxy.logout(request, response);
				} else if ("register".equals(actionName)) {
					RegisterAction registerAction = new RegisterActionImpl();
					InvocationHandler handler = new ActionProxy(registerAction, request, response, actionElement, requestName);
					RegisterAction proxy = (RegisterAction) Proxy.newProxyInstance(registerAction.getClass().getClassLoader(), registerAction.getClass().getInterfaces(), handler);
					result = proxy.register(request, response);
				}
			}
		}
		// 1-2 未找到对应的action name
		if(actionFlag == false){
			System.out.println("未找到对应的action name");
			HttpSession session = request.getSession();
			session.setAttribute("errorinfo", "无法识别该请求");
			response.sendRedirect("./pages/fail.jsp");
		}
		
		/**
		 * 视图解析器
		 */
		if (actionElement != null) {
			List<Element> elements = actionElement.elements("result");
			for (Element element : elements) {
				// 根据result解析
				String resultName = element.element("name").getText();
				String resultType = element.element("type").getText();
				String resultValue = element.element("value").getText();
				// 找出对应的result，再根据转发方式跳转
				if (resultName.equals(result)) {
					if (resultType.equals("forward")) {
						request.getRequestDispatcher("./" + resultValue).forward(request, response);
					} else if (resultType.equals("redirect")) {
						response.sendRedirect("./" + resultValue);
					}
				}
			}
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
