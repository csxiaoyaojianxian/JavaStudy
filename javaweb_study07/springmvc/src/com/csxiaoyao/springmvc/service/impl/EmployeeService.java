package com.csxiaoyao.springmvc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csxiaoyao.springmvc.bean.Employee;
import com.csxiaoyao.springmvc.dao.IEmployeeDao;
import com.csxiaoyao.springmvc.service.IEmployeeService;
import com.csxiaoyao.springmvc.util.exception.BusinessException;

@Service("EmployeeService")
public class EmployeeService implements IEmployeeService {
	@Resource
	private IEmployeeDao employeeDao;
	
	public Employee findById(int id) throws BusinessException{
		Employee employee = employeeDao.findOne(id);
		return employee;
	}

	@Override
	public void add(Employee employee) throws BusinessException {
		employeeDao.create(employee);
	}
}
