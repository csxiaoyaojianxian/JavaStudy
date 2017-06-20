package com.csxiaoyao.study;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.ResultSetHandler;

import com.csxiaoyao.utils.JdbcUtils;
import com.csxiaoyao.utils.Column;
import com.csxiaoyao.utils.Id;
import com.csxiaoyao.utils.Table;

/**
 * 解决优化的问题：
 * 	  1. 当数据库表名与类名不一致、 
 *    2. 字段与属性不一样、
 *    3. 主键不叫id 
 *    
 */
public class BaseDao<T> {
	
	// 当前运行类的类型
	private Class<T> clazz;
	// 表名
	private String tableName;
	// 主键
	private String id_primary;

	// 拿到当前运行类的参数化类型中实际的类型  ( BaseDao<Admin> ,  Admin.class)
	public BaseDao(){
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) type;
		Type[] types = pt.getActualTypeArguments();
		clazz = (Class<T>) types[0];
		
		//已经拿到：  Admin.class
		
		/*******1. 获取表名*******/
		Table table = clazz.getAnnotation(Table.class);
		tableName = table.tableName();
		
		/*******2. 获取主键字段*******/
		//获取当前运行类的所有字段、遍历、获取每一个字段上的id注解
		Field[] fs = clazz.getDeclaredFields();
		for (Field f : fs) {
			
			// 设置强制访问
			f.setAccessible(true);
			
			// 获取每一个字段上的id注解
			Id anno_id = f.getAnnotation(Id.class);
			
			// 判断
			if (anno_id != null) {
				// 如果字段上有id注解，当前字段(field)是主键； 再获取字段名称
				Column column = f.getAnnotation(Column.class);
				// 主键
				id_primary = column.columnName();
				// 跳出循环
				break;
			}
		}
		
		System.out.println("表：" + tableName);
		System.out.println("主键：" + id_primary);
	}
	
	
	public T findById(int id){
		try {
			String sql = "select * from " + tableName + " where " + id_primary +"=?";
			/*
			 * DbUtils的已经封装好的工具类：BeanHandler?   属性=字段
			 */
			return JdbcUtils.getQuerrRunner().query(sql, new BeanHandler<T>(clazz), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<T> getAll(){
		try {
			String sql = "select * from " + tableName;
			return JdbcUtils.getQuerrRunner().query(sql, new BeanListHandler<T>(clazz));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}

/**
 * 自定义结果集：封装单个Bean对象
 */
class BeanHandler<T> implements ResultSetHandler<T>{
	// 保存传入的要封装的类的字节码
	private Class<T> clazz;
	public BeanHandler(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	// 封装结果集的方法
	@Override
	public T handle(ResultSet rs) throws SQLException {
		try {
			// 创建要封装的对象  ‘1’
			T t = clazz.newInstance(); 
			// 向下读一行
			if (rs.next()) {
				
				// a. 获取类的所有的Field字段数组
				Field[] fs = clazz.getDeclaredFields();
				
				// b. 遍历， 得到每一个字段类型：Field
				for (Field f : fs) {
				
					// c. 获取”属性名称“
					String fieldName = f.getName();
					
					// e. 获取Field字段上注解   【@Column(columnName = "a_userName")】
					Column column =  f.getAnnotation(Column.class);
					
					// f. ”字段名“
					String columnName = column.columnName();        // 数据库中字段 a_userName
					
					// g. 字段值
					Object columnValue = rs.getObject(columnName);
					
					// 设置（BeanUtils组件）
					BeanUtils.copyProperty(t, fieldName, columnValue);
				}
			}
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}


/**
 * 自定义结果集：封装多个Bean对象到List集合
 */
class BeanListHandler<T> implements ResultSetHandler<List<T>>{
	
	// 要封装的单个对象
	private Class<T> clazz;
	public BeanListHandler(Class<T> clazz){
		this.clazz = clazz;
	}

	// 把从数据库查询到的没一行记录，封装为一个对象，再提交到list集合， 返回List<T>
	@Override
	public List<T> handle(ResultSet rs) throws SQLException {
		List<T> list = new ArrayList<T>();
		try {
			// 向下读一行
			while (rs.next()) {
				
				// 创建要封装的对象  ‘1’
				T t = clazz.newInstance(); 
				
				// a. 获取类的所有的Field字段数组
				Field[] fs = clazz.getDeclaredFields();
				
				// b. 遍历， 得到每一个字段类型：Field
				for (Field f : fs) {
				
					// c. 获取”属性名称“
					String fieldName = f.getName();
					
					// e. 获取Field字段上注解   【@Column(columnName = "a_userName")】
					Column column =  f.getAnnotation(Column.class);
					
					// f. ”字段名“
					String columnName = column.columnName();        // 数据库中字段 a_userName
					
					// g. 字段值
					Object columnValue = rs.getObject(columnName);
					
					// 设置（BeanUtils组件）
					BeanUtils.copyProperty(t, fieldName, columnValue);
				}
				// 对象添加到集合
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}