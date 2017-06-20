package com.csxiaoyao.mapping.many2many;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;
public class App1_save {
	
	private static SessionFactory sf;
	static {
		sf = new Configuration()
			.configure()
			.addClass(Project.class)   
			.addClass(Developer.class)   // 测试时候使用
			.buildSessionFactory();
	}

	// 1. 多对多，保存 【只能通过一方维护另外一方，不能重复维护】
	@Test
	public void save() {
		Session session = sf.openSession();
		session.beginTransaction();
		// 创建项目对象
		Project prj_ds = new Project();
		prj_ds.setPrj_name("电商系统");
		Project prj_oa = new Project();
		prj_oa.setPrj_name("OA系统");
		// 创建员工对象
		Developer dev_zs = new Developer();
		dev_zs.setD_name("张三");
		Developer dev_ls = new Developer();
		dev_ls.setD_name("李四");
		Developer dev_ww = new Developer();
		dev_ww.setD_name("王五");
		// 关系 【项目方】
		prj_ds.getDevelopers().add(dev_zs);
		prj_ds.getDevelopers().add(dev_ls);
		prj_oa.getDevelopers().add(dev_ls);
		prj_oa.getDevelopers().add(dev_ww);
		// 保存
//		session.save(dev_cj);
//		session.save(dev_wc);
//		session.save(dev_lz);
		session.save(prj_ds);
		session.save(prj_oa);   // 必须要设置级联保存 
		
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