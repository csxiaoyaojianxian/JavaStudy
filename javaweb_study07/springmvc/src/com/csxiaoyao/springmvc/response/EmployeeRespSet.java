package com.csxiaoyao.springmvc.response;

import com.csxiaoyao.springmvc.view.EmployeeForView;

public class EmployeeRespSet extends ResponseSet {
	private EmployeeForView employeeForView;
	
	public EmployeeRespSet(){
		super();
	}
	public EmployeeRespSet(int code, String message){
		super(code, message);
	}

	public EmployeeForView getEmployee() {
		return employeeForView;
	}

	public void setEmployee(EmployeeForView employeeForView) {
		this.employeeForView = employeeForView;
	}
	

}
