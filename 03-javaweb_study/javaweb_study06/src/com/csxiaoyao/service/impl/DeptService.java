package com.csxiaoyao.service.impl;

import java.util.List;

import com.csxiaoyao.dao.IDeptDao;
import com.csxiaoyao.entity.Dept;
import com.csxiaoyao.service.IDeptService;

public class DeptService implements IDeptService {
	
	private IDeptDao deptDao;
	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}

	@Override
	public Dept findById(int id) {
		return deptDao.findById(id);
	}

	@Override
	public List<Dept> getAll() {
		return deptDao.getAll();
	}

}
