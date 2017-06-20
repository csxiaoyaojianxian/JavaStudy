package com.csxiaoyao.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.csxiaoyao.entity.Employee;

public class EmployeeDao {

	// 注入SessionFactory对象
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 查询
	 */
	public Employee findById(Serializable id) {
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, id);
	}
}