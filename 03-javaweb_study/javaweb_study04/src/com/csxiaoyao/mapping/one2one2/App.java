package com.csxiaoyao.mapping.one2one2;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App {
	
	private static SessionFactory sf;
	static {
		sf = new Configuration()
			.configure()
			.addClass(IdCard.class)   
			.addClass(User.class)   // 测试时候使用
			.buildSessionFactory();
	}

	@Test
	public void getSave() {
		
		Session session = sf.openSession();
		session.beginTransaction();
		
		// 用户
		User user = new User();
		user.setUserName("Jack");
		// 身份证
		IdCard idCard = new IdCard();
		idCard.setCardNum("441202XXX");
		idCard.setPlace("广州XXX");
		// 关系
		idCard.setUser(user);
		
		// ----保存----
		session.save(idCard);
		
		session.getTransaction().commit();
		session.close();
		
	}
	
}
