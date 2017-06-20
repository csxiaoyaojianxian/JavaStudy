package com.csxiaoyao.compositeKey;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
/**
 * 
 * @ClassName: App
 * @Description: 配置文件自动加载和复合主键映射实现
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年1月27日 下午8:47:45
 *
 */
public class App {
	private static SessionFactory sf;
	static  {		
		// 创建sf对象
		sf = new Configuration()
			.configure()
			.addClass(User.class)  //（测试） 会自动加载映射文件：User.hbm.xml
			.buildSessionFactory();
	}
	//1. 保存对象
	@Test
	public void testSave() throws Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		// 对象
		CompositeKeys keys = new CompositeKeys();
		keys.setAddress("USTC");
		keys.setUserName("孙");
		User user = new User();
		user.setAge(20);
		user.setKeys(keys);
		// 保存
		session.save(user);
		tx.commit();
		session.close();
	}
	@Test
	public void testGet() throws Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		//构建主键再查询
		CompositeKeys keys = new CompositeKeys();
		keys.setAddress("USTC");
		keys.setUserName("孙");
		// 主键查询
		User user = (User) session.get(User.class, keys);
		// 测试输出
		if (user != null){
			System.out.println(user.getKeys().getUserName());
			System.out.println(user.getKeys().getAddress());
			System.out.println(user.getAge());
		}
		tx.commit();
		session.close();
	}
}