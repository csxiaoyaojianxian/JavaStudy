package com.csxiaoyao.service;

import java.util.List;

import com.csxiaoyao.entity.FoodType;

/**
 * 3. 菜系模块业务逻辑层，接口设计
 *
 */
public interface IFoodTypeService {
	/**
	 * 添加
	 */
	void save(FoodType foodType);
	
	/**
	 * 更新
	 */
	void update(FoodType foodType);
	
	/**
	 * 删除
	 */
	void delete(int id);
	
	/**
	 * 根据主键查询
	 */
	FoodType findById(int id);
	
	/**
	 * 查询全部
	 */
	List<FoodType> getAll();
	
	/**
	 * 根据菜系名称查询
	 */
	List<FoodType> getAll(String typeName);
}