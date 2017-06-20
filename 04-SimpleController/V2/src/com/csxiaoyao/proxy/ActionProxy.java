package com.csxiaoyao.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Element;

import com.csxiaoyao.action.LoginAction;
import com.csxiaoyao.action.LogoutAction;
import com.csxiaoyao.action.RegisterAction;
import com.csxiaoyao.utils.GetUrlAction;

public class ActionProxy implements InvocationHandler {
	private LoginAction loginAction;
	private LogoutAction logoutAction;
	private RegisterAction registerAction;
	private String reqeustName;
	private Boolean flag = false;// before中检查是否有日志拦截器
	private Class clazz;// 日志拦截器类名
	private String methodName;// 日志拦截器方法名
	private String sTime;
	private String eTime;
	private String outCome;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Element actionElement;

	public ActionProxy(LoginAction loginAction, HttpServletRequest request, HttpServletResponse response,
			Element actionElement, String reqeustName) {
		this.loginAction = loginAction;
		this.request = request;
		this.response = response;
		this.actionElement = actionElement;
		this.reqeustName = reqeustName;
	}
	public ActionProxy(LogoutAction logoutAction, HttpServletRequest request, HttpServletResponse response,
			Element actionElement, String reqeustName) {
		this.logoutAction = logoutAction;
		this.request = request;
		this.response = response;
		this.actionElement = actionElement;
		this.reqeustName = reqeustName;
	}
	public ActionProxy(RegisterAction registerAction, HttpServletRequest request, HttpServletResponse response,
			Element actionElement, String reqeustName) {
		this.registerAction = registerAction;
		this.request = request;
		this.response = response;
		this.actionElement = actionElement;
		this.reqeustName = reqeustName;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result = null;
		if ("login".equals(reqeustName)) {
			result = method.invoke(loginAction, args);
		} else if ("logout".equals(reqeustName)) {
			result = method.invoke(logoutAction, args);
		} else if ("register".equals(reqeustName)) {
			result = method.invoke(registerAction, args);
		}
		outCome = (String) result;
		after();
		return result;
	}
	
	public void before() {
		// 调用action开始时间
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		sTime = simpleDateFormat.format(date);
		// 根据controller传递的结点寻找拦截器<interceptor-ref>
		Element interceptor = actionElement.element("interceptor-ref");
		if (interceptor != null) {
			List<Element> names = interceptor.selectNodes("name");
			for (Element name : names) {
				if ("logWriter".equals(name.getText())) {
					// 有日志拦截器
					flag = true;
					// 找到拦截器所对应的类名
					Element parent = actionElement.getParent();
					List<Element> elements = parent.elements("interceptor");
					for (Element intercept : elements) {
						if ("logWriter".equals(intercept.element("name").getText())) {
							String className = intercept.element("class").element("name").getText();
							methodName = intercept.element("class").element("method").getText();
							// 反射
							try {
								clazz = Class.forName(className);
								Method method = clazz.getDeclaredMethod(methodName, new Class[] { String.class, String.class });
								method.invoke(clazz.newInstance(), reqeustName, sTime);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	public void after() throws Exception {
		// 结束时间
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		eTime = simpleDateFormat.format(date);
		String requestURI = request.getRequestURI();
		String reqeustName = GetUrlAction.getActionName(requestURI);
		if (flag) {// 如果有日志拦截器，追加记录
			Method method = clazz.getDeclaredMethod(methodName, new Class[] { String.class, String.class, int.class });
			method.invoke(clazz.newInstance(), eTime, outCome, 1);
		}

	}

}
