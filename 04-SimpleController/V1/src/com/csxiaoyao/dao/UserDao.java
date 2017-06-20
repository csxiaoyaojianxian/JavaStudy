package com.csxiaoyao.dao;

import com.csxiaoyao.bean.UserBean;

public class UserDao {
	public boolean userLogin(UserBean userBean){
		if("sunshine".equals(userBean.getUsername()) && "19931128".equals(userBean.getPassword())){
			return true;
		}else{
			return false;
		}
	}
}
