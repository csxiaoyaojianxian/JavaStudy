package com.csxiaoyao.framework.bean;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 加载配置文件, 封装所有的真个mystruts.xml
 */
public class ActionMappingManager {

	// 保存action的集合
	private Map<String,ActionMapping> allActions ;
	
	public ActionMappingManager(){
		allActions = new HashMap<String,ActionMapping>();
		// 初始化
		this.init();
	}
	
	/**
	 * 根据请求路径名称，返回Action的映射对象
	 * @param actionName   当前请求路径
	 * @return             返回配置文件中代表action节点的AcitonMapping对象
	 */
	public ActionMapping getActionMapping(String actionName) {
		if (actionName == null) {
			throw new RuntimeException("传入参数有误，请查看struts.xml配置的路径。");
		}
		
		ActionMapping actionMapping = allActions.get(actionName);
		if (actionMapping == null) {
			throw new RuntimeException("路径在struts.xml中找不到，请检查");
		}
		return actionMapping;
	}
	
	// 初始化allActions集合
	private void init() {
		/********DOM4J读取配置文件***********/
		try {
			// 1. 得到解析器
			SAXReader reader = new SAXReader();
			// 得到src/mystruts.xml  文件流
			InputStream inStream = this.getClass().getResourceAsStream("/mystruts.xml");
			// 2. 加载文件
			Document doc = reader.read(inStream);
			
			// 3. 获取根
			Element root = doc.getRootElement();
			
			// 4. 得到package节点
			Element ele_package = root.element("package");
			
			// 5. 得到package节点下，  所有的action子节点
			List<Element> listAction = ele_package.elements("action");
			
			// 6.遍历 ，封装
			for (Element ele_action : listAction) {
				// 6.1 封装一个ActionMapping对象
				ActionMapping actionMapping = new ActionMapping();
				actionMapping.setName(ele_action.attributeValue("name"));
				actionMapping.setClassName(ele_action.attributeValue("class"));
				actionMapping.setMethod(ele_action.attributeValue("method"));
				
				// 6.2 封装当前aciton节点下所有的结果视图
				Map<String,Result> results = new HashMap<String, Result>();
				
				// 得到当前action节点下所有的result子节点
				 Iterator<Element> it = ele_action.elementIterator("result");
				 while (it.hasNext()) {
					 // 当前迭代的每一个元素都是 <result...>
					 Element ele_result = it.next();
					 
					 // 封装对象
					 Result res = new Result();
					 res.setName(ele_result.attributeValue("name"));
					 res.setType(ele_result.attributeValue("type"));
					 res.setPage(ele_result.getTextTrim());
					 
					 // 添加到集合
					 results.put(res.getName(), res);
				 }
				
				// 设置到actionMapping中
				actionMapping.setResults(results);
				
				// 6.x actionMapping添加到map集合
				allActions.put(actionMapping.getName(), actionMapping);
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException("启动时候初始化错误",e);
		}
	}
}














