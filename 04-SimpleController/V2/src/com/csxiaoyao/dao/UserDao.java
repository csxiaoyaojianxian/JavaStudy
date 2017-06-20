package com.csxiaoyao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.csxiaoyao.bean.UserBean;
import com.mysql.jdbc.Driver;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class UserDao {
	Conversation conversation = new Conversation();
	public UserBean queryUser(UserBean u) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		return conversation.queryUser(u);
	}

	public boolean insertUser(UserBean u) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		return conversation.insertUser(u);
	}

	public boolean updateUser(UserBean u) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		return conversation.updateUser(u);
	}

	public boolean deleteUser(UserBean u) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		return conversation.deleteUser(u);
	}
	
	
	public boolean register(UserBean u) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println("调用userDao register");
		return insertUser(u);
	}
	public boolean login(UserBean u) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println("调用userDao login");
		UserBean queryUser = queryUser(u);
		if (queryUser != null) {
			return true;
		}
		return false;
	}
	public boolean logout(UserBean u) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println("调用userDao logout");
		System.out.println("从session中取user：" + u.getUsername() + ":" + u.getPassword());
		UserBean queryUser = queryUser(u);
		if (queryUser != null) {
			return true;
		}
		return false;
	}
}
