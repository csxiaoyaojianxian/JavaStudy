package com.csxiaoyao.service;

import com.csxiaoyao.dao.UserDao;
import com.csxiaoyao.entity.User;


public class UserService {
	
	private UserDao ud = new UserDao();

	// 模拟登陆
	public User login(User user){
		return ud.login(user);
	}
	
	// 模拟注册
	public void register(User user) {
		ud.register(user);
	}
}
