package com.csxiaoyao.mapping.one2many;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App4_cascade {
	
	private static SessionFactory sf;
	static {
		sf = new Configuration()
			.configure()
			.addClass(Dept.class)   
			.addClass(Employee.class)   // 测试时候使用
			.buildSessionFactory();
	}
	// 级联保存
	@Test
	public void save() {
		Session session = sf.openSession();
		session.beginTransaction();
		// 部门对象
		Dept dept = new Dept();
		dept.setDeptName("财务部");
		// 员工对象
		Employee emp_zs = new Employee();
		emp_zs.setEmpName("张三");
		Employee emp_ls = new Employee();
		emp_ls.setEmpName("李四");
		// 关系
		dept.getEmps().add(emp_zs);
		dept.getEmps().add(emp_ls); 
		// 保存
//		session.save(emp_zs);
//		session.save(emp_ls);
		session.save(dept); // 需要设置级联保存； 保存部门，部门下所有的员工  
		
		session.getTransaction().commit();
		session.close();
	}

	// 级联删除
	@Test
	public void delete() {
		Session session = sf.openSession();
		session.beginTransaction();
		Dept dept = (Dept) session.get(Dept.class,7);
		session.delete(dept); // 级联删除
		session.getTransaction().commit();
		session.close();
	}
	@Test
	public void bak() {
		Session session = sf.openSession();
		session.beginTransaction();
		session.getTransaction().commit();
		session.close();
	}
}