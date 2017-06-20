package com.csxiaoyao.springmvc.service;

import com.csxiaoyao.springmvc.bean.Employee;
import com.csxiaoyao.springmvc.util.exception.BusinessException;

public interface IEmployeeService {
	public Employee findById(int id) throws BusinessException;
	public void add(Employee employee) throws BusinessException;
}
