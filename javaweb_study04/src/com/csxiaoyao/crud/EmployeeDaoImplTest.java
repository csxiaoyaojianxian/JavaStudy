package com.csxiaoyao.crud;

import java.util.Date;

import org.junit.Test;

public class EmployeeDaoImplTest {
	
	private EmployeeDaoImpl empDao = new EmployeeDaoImpl();

	@Test
	public void testSave() {
		Employee employee = new Employee();
		employee.setEmpId(7);//设置自增长，此字段不起效
		employee.setEmpName("孙");
		employee.setWorkDate(new Date());
		empDao.save(employee);
	}
	@Test
	public void testUpdate() {
		Employee employee = new Employee();
//		employee.setEmpId(7);
		employee.setEmpName("sunshine");
		employee.setWorkDate(new Date());
		empDao.update(employee);
	}
	@Test
	public void testDelete() {
		empDao.delete(4);
	}
	@Test
	public void testFindById() {
		System.out.println(empDao.findById(1));
	}

	@Test
	public void testGetAll() {
		System.out.println(empDao.getAll());
	}

	@Test
	public void testGetAllString() {
		System.out.println(empDao.getAll("孙"));
	}

	@Test
	public void testGetAllIntInt() {
		System.out.println(empDao.getAll(4, 2));
	}
}