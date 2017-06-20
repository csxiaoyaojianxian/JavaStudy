package com.csxiaoyao.springmvc.dao;

import java.util.Set;

import com.csxiaoyao.springmvc.bean.Employee;

public interface IEmployeeDao extends DataOperator<Employee> {
	// 根据姓名查询（可能有重名）
	Set<Employee> findByName(String employeeName);
	
}
