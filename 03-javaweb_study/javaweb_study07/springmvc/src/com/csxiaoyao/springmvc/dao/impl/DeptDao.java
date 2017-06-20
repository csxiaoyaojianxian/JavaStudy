package com.csxiaoyao.springmvc.dao.impl;

import org.springframework.stereotype.Repository;

import com.csxiaoyao.springmvc.bean.Dept;
import com.csxiaoyao.springmvc.dao.IDeptDao;

@Repository("IDeptDao")
public class DeptDao extends AbstractHibernateDao<Dept> implements IDeptDao{
	public DeptDao() {
		super();
		setEntityType(Dept.class);
	}
}
