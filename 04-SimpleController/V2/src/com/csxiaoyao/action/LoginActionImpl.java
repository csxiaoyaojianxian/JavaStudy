package com.csxiaoyao.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csxiaoyao.bean.UserBean;
import com.csxiaoyao.dao.UserDao;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class LoginActionImpl implements LoginAction{
	
	public LoginActionImpl(){
		
	}
	
	private UserBean userBean = new UserBean();
	private UserDao userDao = new UserDao();
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		setUserBean(new UserBean(username, password));

		HttpSession session = request.getSession();
		try {
			if (getUserDao().login(userBean)) {
				System.out.println("登陆成功");
				session.setAttribute("username", username);
				return "success";
			} else {
				session.setAttribute("errorinfo", "登录失败");
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
