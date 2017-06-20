package com.csxiaoyao.service.impl;

import java.util.List;

import com.csxiaoyao.dao.IDinnerTableDao;
import com.csxiaoyao.entity.DinnerTable;
import com.csxiaoyao.entity.TableStatus;
import com.csxiaoyao.factory.BeanFactory;
import com.csxiaoyao.service.IDinnerTableService;

public class DinnerTableService implements IDinnerTableService {

	// 调用的Dao, 通常工厂创建实例
	private IDinnerTableDao dinnerTableDao = BeanFactory.getInstance(
			"dinnerTableDao", IDinnerTableDao.class);

	@Override
	public DinnerTable findById(int id) {
		try {
			return dinnerTableDao.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> findNoUseTable() {
		try {
			// 调用dao的根据状态查询的方法，查询没有预定的餐桌
			return dinnerTableDao.findByStatus(TableStatus.Free);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
