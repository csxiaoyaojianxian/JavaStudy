package com.csxiaoyao.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csxiaoyao.bean.UserBean;
import com.csxiaoyao.dao.UserDao;

public class RegisterActionImpl implements RegisterAction{
	public RegisterActionImpl(){
		
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
	
	@Override
	public String register(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("注册" + username + "：" + password);
		setUserBean(new UserBean(username,password));
		
		HttpSession session = request.getSession();
		try {
			if (getUserDao().register(userBean) == true) {
				session.setAttribute("registersuccess", "注册成功");
				System.out.println("注册成功");
				return "success";
			} else {
				session.setAttribute("registererror", "用户已存在");
				System.out.println("注册失败");
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
