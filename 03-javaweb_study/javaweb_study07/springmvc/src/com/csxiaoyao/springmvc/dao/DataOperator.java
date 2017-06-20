package com.csxiaoyao.springmvc.dao;

import java.io.Serializable;
import java.util.List;

// 数据持久化处理接口
public interface DataOperator<T extends Serializable> {

	T findOne(final int id);

	List<T> findAll();

	List<T> findByHql(String hql);

	void create(final T entity);

	void update(final T entity);

	void delete(final T entity);

	void deleteById(final int entityId);

}
