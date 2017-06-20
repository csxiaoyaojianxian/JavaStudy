package com.csxiaoyao.query;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App_hql {
	
	private static SessionFactory sf;
	static {
		sf = new Configuration()
			.configure()
			.addClass(Dept.class)   
			.addClass(Employee.class)   // 测试时候使用
			.buildSessionFactory();
	}

	/*
	 *  1)	Get/load主键查询
		2)	对象导航查询
		3)	HQL查询，  Hibernate Query language  hibernate 提供的面向对象的查询语言。
		4)	Criteria 查询，   完全面向对象的查询（Query By Criteria  ,QBC）
		5)	SQLQuery， 本地SQL查询

	 */
	@Test
	public void all() {
		
		Session session = sf.openSession();
		session.beginTransaction();
		
		// (1) 主键查询
		Dept dept1 =  (Dept) session.get(Dept.class, 12);
		Dept dept2 =  (Dept) session.load(Dept.class, 12);
		
		// (2) 对象导航查询
		Dept dept3 =  (Dept) session.get(Dept.class, 12);
		System.out.println(dept3.getDeptName());
		System.out.println(dept3.getEmps());
		
		// (3) HQL查询
		// 注意：使用hql查询的时候 auto-import="true" 要设置true，
		// 如果是false，写hql的时候，要指定类的全名
		
		// a. 查询全部列
		Query q1 = session.createQuery("from Dept");  //OK
		//Query q1 = session.createQuery("select * from Dept");  // 错误，不支持*
		Query q2 = session.createQuery("select d from Dept d");  // OK
		System.out.println(q2.list());

		// b. 查询指定的列  【返回对象数据Object[] 】
		Query q3 = session.createQuery("select d.deptId,d.deptName from Dept d");  
		System.out.println(q3.list());
		
		// c. 查询指定的列, 自动封装为对象  【必须要提供带参数构造器】
		Query q4 = session.createQuery("select new Dept(d.deptId,d.deptName) from Dept d");  
		System.out.println(q4.list());
		
		// d. 条件查询: 一个条件/多个条件and or/between and/模糊查询
		// 	条件查询： 占位符
		Query q5 = session.createQuery("from Dept d where deptName=?");
		q5.setString(0, "财务部");
		q5.setParameter(0, "财务部");
		System.out.println(q5.list());
		
		// 	条件查询： 命名参数
		Query q6 = session.createQuery("from Dept d where deptId=:myId or deptName=:name");
		q6.setParameter("myId", 12);
		q6.setParameter("name", "财务部");
		System.out.println(q6.list());
		
		// 	条件查询： 范围
		Query q7 = session.createQuery("from Dept d where deptId between ? and ?");
		q7.setParameter(0, 1);
		q7.setParameter(1, 20);
		System.out.println(q7.list());
		
		// 	条件查询： 模糊
		Query q8 = session.createQuery("from Dept d where deptName like ?");
		q8.setString(0, "%部%");
		System.out.println(q8.list());

		// e. 聚合函数统计
		Query q9 = session.createQuery("select count(*) from Dept");
		Long num = (Long) q9.uniqueResult();
		System.out.println(num);
		
		// f. 分组查询
		//-- 统计t_employee表中，每个部门的人数
		// 数据库写法：SELECT dept_id,COUNT(*) FROM t_employee GROUP BY dept_id;
		// HQL写法
		Query q = session.createQuery("select e.dept, count(*) from Employee e group by e.dept");
		System.out.println(q.list());
		
		session.getTransaction().commit();
		session.close();
	}
	
	// g. 连接查询
	@Test
	public void join() {
		Session session = sf.openSession();
		session.beginTransaction();
		
		//		内连接 【映射已经配置好了关系，关联的时候，直接写对象的属性即可】
		Query q1 = session.createQuery("from Dept d inner join d.emps");
		
		//		左外连接
		Query q2 = session.createQuery("from Dept d left join d.emps");

		//		右外连接
		Query q3 = session.createQuery("from Employee e right join e.dept");
		q3.list();
		
		session.getTransaction().commit();
		session.close();
	}
	
	// g. 连接查询 - 迫切连接
	@Test
	public void fetch() {
		Session session = sf.openSession();
		session.beginTransaction();
		//		迫切内连接 【使用fetch, 会把右表的数据，填充到左表对象中！】
		Query q1 = session.createQuery("from Dept d inner join fetch d.emps");
		
		//		迫切左外连接
		Query q2 = session.createQuery("from Dept d left join fetch d.emps");
		q2.list();
		
		session.getTransaction().commit();
		session.close();
	}
	
	// HQL查询优化
	@Test
	public void hql_other() {
		Session session = sf.openSession();
		session.beginTransaction();
		
		// HQL 放到映射文件中
		Query q = session.getNamedQuery("getAllDept");
		q.setParameter(0, 10);
		System.out.println(q.list());
		
		session.getTransaction().commit();
		session.close();
	}
}