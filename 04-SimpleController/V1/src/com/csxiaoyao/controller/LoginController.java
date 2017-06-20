package com.csxiaoyao.controller;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

import com.csxiaoyao.utils.GetUrlAction;

/**
 * 
 * @ClassName: LoginController
 * @Description: 反射、内省
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年2月22日 下午8:12:42
 *
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
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
				System.out.println("查找到对应的action name");
				actionFlag = true;
				actionElement = actionNode.getParent();
				// 遍历di.xml
				List<Element> diNodes = diDoc.selectNodes("/*/bean/name");
				for (Element diNode : diNodes) {
					
					// 2-1 查找到对应的bean，查找依赖bean
					if (actionName.equals(diNode.getText())) {
						System.out.println("查找到对应的bean");
						beanFlag = true;
						Element beanElement = diNode.getParent();
						String beanClass = beanElement.element("class").getText();
						// 遍历bean中property
						List<Element> propertyElements = beanElement.elements("property");
						for (Element propertyElement : propertyElements) {
							String refName = propertyElement.elementText("name");
							String refClass = propertyElement.elementText("ref-class");//是否要判断能否找到
							// 根据ref-class遍历全部bean结点
							for (Element beanNode : diNodes) {
								String refBeanName = beanNode.getText();
								
								// 3-1 依赖其他bean
								if (refClass.equals(refBeanName)) {
									System.out.println("依赖其他bean");
									beanDependFlag = true;
									String refBeanClass = beanNode.getParent().elementText("class");
									// 先通过反射构造被依赖的bean实例，再构造依赖bean实例
									// 并通过属性的 setter 方法(Java 内省机制 Introspector)将被依赖的bean实例注入依赖bean实例
									// 3-1-1 首先封装参数到bean实例（未使用递归，约定依赖的依赖只有一层）
									try {
										List<Element> beanPropertyElements = beanNode.getParent().elements("property");
										
										Class bean = Class.forName(refBeanClass);
										Object instanceBean = bean.newInstance();
										// 内省
										BeanInfo beanInfo = Introspector.getBeanInfo(bean);
										PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
										if (proDescrtptors != null && proDescrtptors.length > 0) {
											for (Element ele : beanPropertyElements) {
												String elementText = ele.elementText("name");
												// 遍历内省的对象的每一个属性值，如果匹配就装配值
												for (PropertyDescriptor propDesc : proDescrtptors) {
													String name = propDesc.getName();
													Method methodSet = propDesc.getWriteMethod();
													if (name.equals(elementText)) {
														// 3-1-2 封装页面上的参数到bean
														System.out.println("完成封装页面上的参数到bean");
														methodSet.invoke(instanceBean, request.getParameter(elementText));
													}
												}
											}
										}
										// 已经封装完被依赖instanceBean，再次使用内省将被依赖的bean实例注入依赖bean实例
										// 3-1-3 首先反射出依赖bean（action依赖的bean，非bean依赖的bean）
										try {
											Class clazz = Class.forName(beanClass);
											Object instanceResult = clazz.newInstance();
											BeanInfo clazzInfo = Introspector.getBeanInfo(clazz);
											PropertyDescriptor[] clazzProDescrtptors = clazzInfo.getPropertyDescriptors();
											if (clazzProDescrtptors != null && clazzProDescrtptors.length > 0) {
												for (Element ele : propertyElements) {
													String ref_Name = propertyElement.elementText("name");
													for (PropertyDescriptor propDesc : proDescrtptors) {
														String name = propDesc.getName();
														Method methodSet = propDesc.getWriteMethod();
														if (name.equals(ref_Name)) {
															// 3-1-4 再次内省注入bean实例
															System.out.println("再次内省注入bean实例");
															methodSet.invoke(instanceResult, instanceBean);
														}
													}
												}
											}
											// 3-1-5 注入完成，调用controller中method方法
											System.out.println("注入完成，调用method");
											String methodName = actionElement.element("class").element("method").getText();
											Method method = clazz.getMethod(methodName, new Class[] { HttpServletRequest.class, HttpServletResponse.class });
											result = (String) method.invoke(instanceResult, request, response);
										} catch (Exception e) {
											e.printStackTrace();
										}
									} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException | IntrospectionException e) {
										e.printStackTrace();
									}
								}
							}
							
							// 3-2 不依赖其他bean
							if(beanDependFlag == false)	{
								System.out.println("不依赖其他bean");
								String methodName = actionElement.element("class").element("method").getText();
								try {
									Class clazz = Class.forName(beanClass);
									Method method = clazz.getMethod(methodName, new Class[] { HttpServletRequest.class, HttpServletResponse.class });
									result = (String) method.invoke(clazz.newInstance(), request, response);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
				// 2-2 未找到对应的bean，直接通过反射构造 Action 实例
				if (beanFlag == false) {
					System.out.println("未找到对应的bean");
					String className = actionElement.element("class").element("name").getText();
					String methodName = actionElement.element("class").element("method").getText();
					try {
						Class clazz = Class.forName(className);
						Method method = clazz.getMethod(methodName, new Class[] { HttpServletRequest.class, HttpServletResponse.class });
						result = (String) method.invoke(clazz.newInstance(), request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
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
