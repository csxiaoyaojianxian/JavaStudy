package com.csxiaoyao.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static SessionFactory sf;
	static {
		/*
		//1. 创建配置管理类对象
		Configuration config = new Configuration();
		// 加载配置文件  (默认加载src/hibernate.cfg.xml)
		config.configure();
		//2. 根据加载的配置管理类对象，创建SessionFactory工厂对象
		sf = config.buildSessionFactory();
		*/
		// 【简化】加载主配置文件, 并创建Session的工厂
		sf = new Configuration().configure().buildSessionFactory();
	}
	// 创建Session对象
	public static Session getSession(){
		return sf.openSession();
	}
}