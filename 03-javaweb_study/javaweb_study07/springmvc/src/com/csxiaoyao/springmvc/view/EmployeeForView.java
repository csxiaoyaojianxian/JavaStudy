package com.csxiaoyao.springmvc.view;

import com.csxiaoyao.springmvc.bean.Employee;

// 供前台展示的员工信息
public class EmployeeForView {
	public static final int PRE_CODE = 100000;

	private int user_id;
	private String user_name;
	private String email;
	
	public EmployeeForView() {
	}

	public EmployeeForView(Employee employee) {
		this.formatForView(employee);
	}

	public void formatForView(Employee employee) {
		this.user_id = PRE_CODE + employee.getId();
		this.email = employee.getEmail();
		this.user_name = employee.getUserName();
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
