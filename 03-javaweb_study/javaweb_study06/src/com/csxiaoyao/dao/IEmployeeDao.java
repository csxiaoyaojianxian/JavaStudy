package com.csxiaoyao.dao;

import java.util.List;

import com.csxiaoyao.entity.Employee;

public interface IEmployeeDao {
	void save( Employee employee);
	void update( Employee employee );
	void delete( int id );
	Employee findById( int id );
	List<Employee> getAll();
	List<Employee> getAll( String employeeName );
}
