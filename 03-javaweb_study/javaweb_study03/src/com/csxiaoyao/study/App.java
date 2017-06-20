package com.csxiaoyao.study;

import org.junit.Test;

public class App {

	@Test
	public void testDao() throws Exception {
		AdminDao adminDao = new AdminDao();
//		Admin admin = adminDao.findById(8);
//		System.out.println(admin);
		
		System.out.println(adminDao.findById(8));
		System.out.println(adminDao.getAll());
	}
}
