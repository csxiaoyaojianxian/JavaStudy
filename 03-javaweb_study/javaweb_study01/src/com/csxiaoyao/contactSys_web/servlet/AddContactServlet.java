package com.csxiaoyao.contactSys_web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csxiaoyao.contactSys_web.entity.Contact;
import com.csxiaoyao.contactSys_web.exception.NameRepeatException;
import com.csxiaoyao.contactSys_web.service.ContactService;
import com.csxiaoyao.contactSys_web.service.impl.ContactServiceImpl;
/**
 * 
 * @ClassName: AddContactServlet
 * @Description: 添加联系人的逻辑
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年1月21日 上午11:32:25
 *
 */
public class AddContactServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1.接收参数
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String qq = request.getParameter("qq");
		//2.封装成Contact对象
		Contact contact = new Contact();
		contact.setName(name);
		contact.setGender(gender);
		contact.setAge(Integer.parseInt(age));
		contact.setPhone(phone);
		contact.setEmail(email);
		contact.setQq(qq);
		//3.调用dao类的添加联系人的方法
		ContactService service = new ContactServiceImpl();
		try {
			service.addContact(contact);
		} catch (NameRepeatException e) {
			//处理自定义业务异常
			request.setAttribute("msg", e.getMessage());
			//转发可以传request域
			request.getRequestDispatcher("/addContact.jsp").forward(request, response);
			return;
		}
		//4.跳转到查询联系人的页面
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}