package com.csxiaoyao.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.csxiaoyao.bean.UserBean;
import com.csxiaoyao.dao.UserDao;

public class LogoutActionImpl implements LogoutAction{
	public LogoutActionImpl(){
		
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
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = request.getParameter("password");
		setUserBean(new UserBean(username, password));
		try {
			if (getUserDao().logout(userBean)) {
				session.removeAttribute("username");
				System.out.println("退出成功");
				return "success";
			} else {
				session.setAttribute("errorinfo", "退出失败");
				System.out.println("退出失败");
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
