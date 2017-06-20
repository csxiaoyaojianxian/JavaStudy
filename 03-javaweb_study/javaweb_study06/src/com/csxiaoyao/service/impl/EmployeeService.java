package com.csxiaoyao.service.impl;

import java.util.List;

import com.csxiaoyao.dao.IEmployeeDao;
import com.csxiaoyao.entity.Employee;
import com.csxiaoyao.service.IEmployeeService;

public class EmployeeService implements IEmployeeService {
	
	// 容器注入
	private IEmployeeDao  employeeDao;
	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public void delete(int id) {
		employeeDao.delete(id);
	}

	@Override
	public void deleteMany(int[] ids) {
		if (ids != null && ids.length >0) {
			for (int id : ids){
				delete(id);
			}
		}
	}

	@Override
	public Employee findById(int id) {
		return employeeDao.findById(id);
	}

	@Override
	public List<Employee> getAll() {
		return employeeDao.getAll();
	}

	@Override
	public List<Employee> getAll(String employeeName) {
		return employeeDao.getAll(employeeName);
	}

	@Override
	public void save(Employee emp) {
		employeeDao.save(emp);
	}

	@Override
	public void update(Employee emp) {
		employeeDao.update(emp);
	}

}
