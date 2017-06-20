package com.csxiaoyao.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class DDL {
	// 自动建表
	@Test
	public void testCreate() throws Exception {
		// 创建配置管理类对象
		Configuration config = new Configuration();
		// 加载主配置文件
		config.configure();
		// 创建工具类对象
		SchemaExport export = new SchemaExport(config);
		// 建表
		// 第一个参数： 是否在控制台打印建表语句
		// 第二个参数： 是否执行脚本
		export.create(true, true);
	}
}