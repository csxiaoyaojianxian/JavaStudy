package com.csxiaoyao.service.impl;

import com.csxiaoyao.dao.IFoodDao;
import com.csxiaoyao.entity.Food;
import com.csxiaoyao.factory.BeanFactory;
import com.csxiaoyao.service.IFoodService;
import com.csxiaoyao.utils.PageBean;

public class FoodService implements IFoodService {

	// 创建dao
	private IFoodDao foodDao = BeanFactory.getInstance("foodDao", IFoodDao.class);
	
	@Override
	public Food findById(int id) {
		try {
			return foodDao.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void getAll(PageBean<Food> pb) {
		try {
			foodDao.getAll(pb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
