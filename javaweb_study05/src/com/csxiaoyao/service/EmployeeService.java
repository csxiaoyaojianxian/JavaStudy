package com.csxiaoyao.service;

import java.io.Serializable;

import com.csxiaoyao.dao.EmployeeDao;
import com.csxiaoyao.entity.Employee;

public class EmployeeService {	
	// IOC注入
	private EmployeeDao employeeDao;
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	/**
	 * 查询
	 */
	public Employee findById(Serializable id) {
		Employee emp = employeeDao.findById(id);
		return emp;
	}
}