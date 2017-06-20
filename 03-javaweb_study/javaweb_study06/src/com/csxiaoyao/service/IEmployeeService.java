package com.csxiaoyao.service;

import java.util.List;

import com.csxiaoyao.entity.Employee;

public interface IEmployeeService {

	void save(Employee emp);
	void update(Employee emp);
	Employee findById(int id);
	List<Employee> getAll();
	List<Employee> getAll(String employeeName);
	void delete(int id);
	/**
	 *  删除多个员工
	 */
	void deleteMany(int[] ids);

}