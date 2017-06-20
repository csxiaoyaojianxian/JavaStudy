package com.csxiaoyao.dao;

import com.csxiaoyao.entity.Food;
import com.csxiaoyao.utils.PageBean;

/**
 * 菜品管理
 */
public interface IFoodDao {

	/**
	 * 分页且按条件查询所有的菜品
	 */
	void getAll(PageBean<Food> pb);
	
	/**
	 * 按条件统计菜品的总记录数
	 */
	int getTotalCount(PageBean<Food> pb);
	
	/**
	 * 根据id查询
	 */
	Food findById(int id);
}
