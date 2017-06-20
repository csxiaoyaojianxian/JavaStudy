package com.csxiaoyao.springmvc.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.csxiaoyao.springmvc.bean.Employee;
import com.csxiaoyao.springmvc.dao.IEmployeeDao;
import com.google.common.base.Preconditions;

@Repository("IEmployeeDao")
public class EmployeeDao extends AbstractHibernateDao<Employee> implements IEmployeeDao{

	public EmployeeDao() {
		super();
		setEntityType(Employee.class);
	}
	
	@Override
	public Set<Employee> findByName(String employeeName) {
		Set<Employee> result = null;
		String hql = "from Employee where userName = ?";
		List<Employee> list = createQuery(hql, employeeName).list();
		result = new HashSet<Employee>(list);
		return result;
	}
}