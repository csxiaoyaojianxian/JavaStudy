package com.csxiaoyao.service;

import java.util.List;

import com.csxiaoyao.entity.DinnerTable;

public interface IDinnerTableService {

	/**
	 * 查询所有未预定的餐桌
	 */
	List<DinnerTable> findNoUseTable();
	
	/**
	 * 根据主键查询
	 */
	DinnerTable findById(int id);
}
