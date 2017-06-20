package com.csxiaoyao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.csxiaoyao.bean.UserBean;

public class Conversation {
	Configuration configuration = new Configuration();
	String beanName = configuration.getBeanName();
	String tableName = configuration.getTableName();
	// 根据一个属性值查找对象
	public <T, V> UserBean getUser(T t, V v) throws Exception {
		UserBean query = null;
		List<TableProperties> list = configuration.getList();
		StringBuffer search = new StringBuffer();
		// 遍历查询条件，将name转换成column
		for (TableProperties tableProperties : list) {
			if (tableProperties.getName().equals(t)) {
				String column = tableProperties.getColumn();
				search.append(column);
				search.append(",");
			}
		}
		String searchResult = search.substring(0, search.length() - 1);
		Connection conn = configuration.openDBConnection();
		Statement stat = conn.createStatement();
		// 组装sql
		String sql = "select " + searchResult + " from " + tableName + " where " + searchResult + "='" + v + "'";
		System.out.println(sql);
		ResultSet result = stat.executeQuery(sql);
		// 封装数据到bean
		if (beanName.equals("UserBean")) {
			// 假设结果只有一个
			while (result.next()) {
				query = new UserBean();
				// 遍历属性表，找出非懒加载属性
				for (TableProperties tableProperties : list) {
					String lazyType = tableProperties.getLazyType();
					if ("true".equals(lazyType)) { // 如果该属性lazy加载，跳过
						continue;
					}
					String column = tableProperties.getColumn();
					if ("username".equals(column) || "password".equals(column)) {
						query.setUsername(result.getString(tableProperties.getName()));
					}
				}
			}
			configuration.closeDBConnection();
		}
		return query;
	}
	
	public UserBean queryUser(UserBean u) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println("queryUser:" + u.getUsername() + ":" + u.getPassword());
		Connection conn = configuration.openDBConnection();
		Statement stat = conn.createStatement();
		ResultSet result = stat.executeQuery("select * from "+tableName);
		UserBean query = null;
		while (result.next()) {
			if (u.getUsername().equals(result.getString("username")) && u.getPassword().equals(result.getString("password"))) {
				query = new UserBean(u.getUsername(),u.getPassword());
				break;
			}
		}
		configuration.closeDBConnection();
		return query;
	}

	public boolean insertUser(UserBean u) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Connection conn = configuration.openDBConnection();
		UserDao userDao = new UserDao();
		UserBean query = userDao.queryUser(u);
		if (query != null) {
			if (u.equals(query)) {
				System.out.println("用户已存在，插入失败");
				return false;
			}
		}
		Statement stat = conn.createStatement();
		String sql = "insert into "+tableName+"(username,password) values( '" +  u.getUsername() + "', '" +  u.getPassword() + "')";
		stat.executeUpdate(sql);
		configuration.closeDBConnection();
		return true;
	}

	public boolean updateUser(UserBean u) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Connection conn = configuration.openDBConnection();
		Statement stat = conn.createStatement();
		String sql = "update "+tableName+" set password='" + u.getPassword() + "'where username='" + u.getUsername() + "'";
		int executeUpdate = stat.executeUpdate(sql);
		configuration.closeDBConnection();
		return true;
	}

	public boolean deleteUser(UserBean u) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Connection conn = configuration.openDBConnection();
		Statement stat = conn.createStatement();
		String sql = "delete from "+tableName+" where username='" + u.getUsername() + "'";
		boolean execute = stat.execute(sql);
		configuration.closeDBConnection();
		return execute;
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
