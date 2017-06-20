package com.csxiaoyao.service;

import com.csxiaoyao.entity.Admin;

public interface IAdminService {

	void register(Admin admin);
	Admin login(Admin admin);

}