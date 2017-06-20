package com.csxiaoyao.mapping.collection;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App {
	
	private static SessionFactory sf;
	static {
		sf = new Configuration()
			.configure()
			.addClass(User.class) 
			.buildSessionFactory();
	}

	// 保存set
	@Test
	public void testSaveSet() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();
		//-- 保存
		Set<String> addressSet = new HashSet<String>();
		addressSet.add("江苏");
		addressSet.add("北京");
		// 用户对象
		User user = new User();
		user.setUserName("Sun");
		user.setAddress(addressSet);
		// 保存
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
	// 保存list/map
	@Test
	public void testSaveList() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();
		User user = new User();
		user.setUserName("Tom");
//		// 用户对象  --  list
//		user.getAddressList().add("江苏");
//		user.getAddressList().add("北京");
//		// 保存
//		session.save(user);
		
		// 用户对象  --  Map
		user.getAddressMap().put("A0001", "江苏");
		user.getAddressMap().put("A0002", "北京");
		
		// 保存
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
	// 获取
	@Test
	public void testGet() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();
		// 获取id为3的用户数据
		User user = (User) session.get(User.class, 3); // 及时加载
		System.out.println(user.getUserId());
		System.out.println(user.getUserName());
		// 当查询用户，同时可以获取用户关联的list集合的数据 (因为有正确映射)
		// 当使用到集合数据的使用，才向数据库发送执行的sql语句  (懒加载)
		System.out.println(user.getAddressList());
		
		session.getTransaction().commit();
		session.close();
	}
}










