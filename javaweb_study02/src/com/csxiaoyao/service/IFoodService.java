package com.csxiaoyao.service;

import com.csxiaoyao.entity.Food;
import com.csxiaoyao.utils.PageBean;

public interface IFoodService {

	/**
	 * 主键查询
	 */
	Food findById(int id);

	/**
	 * 分页查询
	 */
	void getAll(PageBean<Food> pb);
}
