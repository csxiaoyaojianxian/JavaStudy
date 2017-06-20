package com.csxiaoyao.dao;

import com.csxiaoyao.entity.User;


public class UserDao {

	// 模拟登陆
	public User login(User user){
		if ("tom".equals(user.getName()) && "19931128".equals(user.getPwd()) ){
			// 登陆成功
			return user;
		}
		// 登陆失败
		return null;
	}
	
	// 模拟注册
	public void register(User user) {
		System.out.println("注册成功：用户，" + user.getName());
	}
}
