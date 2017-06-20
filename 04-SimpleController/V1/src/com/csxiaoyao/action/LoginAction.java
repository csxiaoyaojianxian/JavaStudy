package com.csxiaoyao.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csxiaoyao.bean.UserBean;
import com.csxiaoyao.dao.UserDao;

public class LoginAction {
	
	public LoginAction(){
		
	}
	
	private UserBean userBean;
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserBean userBean = new UserBean(username, password);
		
		UserDao userDao = new UserDao();
		HttpSession session = request.getSession();
		if (userDao.userLogin(userBean)) {
			session.setAttribute("username", username);
			return "success";
		} else {
			session.setAttribute("errorinfo", "登录失败");
			return "fail";
		}

	}
}
