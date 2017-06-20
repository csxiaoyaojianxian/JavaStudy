package com.csxiaoyao.query;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

public class App_criteria {
	
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
	
	// 4)	Criteria 查询，
	@Test
	public void criteria() {
		Session session = sf.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Employee.class);
		// 构建条件
		criteria.add(Restrictions.eq("empId", 12));
//		criteria.add(Restrictions.idEq(12));  // 主键查询
		System.out.println(criteria.list());

		session.getTransaction().commit();
		session.close();
	}
	
	// 5)	SQLQuery， 本地SQL查询
	// 不能跨数据库平台： 如果该了数据库，sql语句有肯能要改。
	@Test
	public void sql() {
		Session session = sf.openSession();
		session.beginTransaction();
		
		SQLQuery q = session.createSQLQuery("SELECT * FROM t_Dept limit 5;")
			.addEntity(Dept.class);  // 也可以自动封装
		System.out.println(q.list());
		
		session.getTransaction().commit();
		session.close();
	}
}