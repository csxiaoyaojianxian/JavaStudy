package com.csxiaoyao.contactSys_web.util.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 
 * @ClassName: ShowIpTag
 * @Description: 自定义标签，显示IP地址
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年1月21日 下午2:15:08
 *
 */
/**
 * 1.继承SimpleTagSupport
 */
public class ShowIpTag extends SimpleTagSupport{
	private JspContext context;
	/**
	 * 传入pageContext
	 */
	@Override
	public void setJspContext(JspContext pc) {
		this.context = pc;
	}
	/**
	 * 2.覆盖doTag方法
	 */
	@Override
	public void doTag() throws JspException, IOException {
		//向浏览器输出客户的ip地址
		PageContext pageContext = (PageContext)context;
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		String host = request.getRemoteHost();
		JspWriter out = pageContext.getOut();
		out.write("客户端host:"+host+"(自定义标签)");
	}
}