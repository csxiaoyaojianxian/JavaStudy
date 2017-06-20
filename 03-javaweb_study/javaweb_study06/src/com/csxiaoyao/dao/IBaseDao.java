package com.csxiaoyao.dao;

import java.util.List;

public interface IBaseDao<T> {

	void save(T emp);
	void update(T emp);
	void delete(int id);
	T findById(int id);
	List<T> getAll();

}