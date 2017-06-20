package com.csxiaoyao.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.csxiaoyao.utils.HibernateUtils;

public class EmployeeDaoImpl implements IEmployeeDao{
	/**
	 * 抽取到HibernateUtils中
	 */
	/*private static SessionFactory sf;
	static {
		//1. 创建配置管理类对象
		Configuration config = new Configuration();
		// 加载配置文件  (默认加载src/hibernate.cfg.xml)
		config.configure();
		//2. 根据加载的配置管理类对象，创建SessionFactory工厂对象
		sf = config.buildSessionFactory();
	}
	// 创建Session对象
	public static Session getSession(){
		return sf.openSession();
	}*/
	@Override
	public void save(Employee emp) {
		Session session = null;
		Transaction tx = null;
		try {
			// 根据session工厂(已经封装在HibernateUtils中)，创建session对象 (代表一个会话，与数据库连接的会话)
			session = HibernateUtils.getSession();
			// 开启事务
			tx = session.beginTransaction();
			//【save】
			session.save(emp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 提交事务
			tx.commit();
			// 关闭
			session.close();
		}
	}
	@Override
	public void update(Employee emp) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			//【update】
			//session.update(emp);
			// 【saveOrUpdate】
			// 注意：若设置主键，存在则update，不存在报错；若未设置主键，直接插入
			session.saveOrUpdate(emp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tx.commit();
			session.close();
		}
	}
	@Override
	public void delete(Serializable id) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			// 先根据id查询对象，再判断删除
			Object obj = session.get(Employee.class, id);
			if (obj != null) {
				//【delete】
				session.delete(obj);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tx.commit();
			session.close();
		}
	}
	@Override
	public Employee findById(Serializable id) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			//【get】主键查询
			return (Employee) session.get(Employee.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tx.commit();
			session.close();
		}
	}
	@Override
	public List<Employee> getAll() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			// 【HQL】查询
			Query q = session.createQuery("from Employee");
			return q.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tx.commit();
			session.close();
		}
	}
	@Override
	public List<Employee> getAll(String employeeName) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			Query q =session.createQuery("from Employee where empName=?");
			// 注意：参数索引从0开始
			q.setParameter(0, employeeName);
			// 执行查询
			return q.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tx.commit();
			session.close();
		}
	}
	@Override
	public List<Employee> getAll(int index, int count) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			Query q = session.createQuery("from Employee");
			// 设置分页参数
			q.setFirstResult(index);  // 查询的其实行 
			q.setMaxResults(count);  // 查询返回的行数
			// 执行查询
			List<Employee> list = q.list();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tx.commit();
			session.close();
		}
	}
}