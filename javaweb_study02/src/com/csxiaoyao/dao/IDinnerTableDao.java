package com.csxiaoyao.dao;

import java.util.List;

import com.csxiaoyao.entity.DinnerTable;
import com.csxiaoyao.entity.TableStatus;

public interface IDinnerTableDao {

	/**
	 * 根据预定状态查询
	 */
	List<DinnerTable> findByStatus(TableStatus ts);

	/**
	 * 主键查询
	 */
	DinnerTable findById(int id);

}
