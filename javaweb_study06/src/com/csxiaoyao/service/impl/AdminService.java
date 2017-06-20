package com.csxiaoyao.service.impl;

import com.csxiaoyao.dao.IAdminDao;
import com.csxiaoyao.entity.Admin;
import com.csxiaoyao.service.IAdminService;

public class AdminService implements IAdminService {
	
	// 注入dao  【这里一定要用接口接收】
	private IAdminDao adminDao; //JDK
	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}	

	@Override
	public Admin login(Admin admin) {
		return adminDao.findByAdmin(admin);
	}

	@Override
	public void register(Admin admin) {
		adminDao.save(admin);
	}

}