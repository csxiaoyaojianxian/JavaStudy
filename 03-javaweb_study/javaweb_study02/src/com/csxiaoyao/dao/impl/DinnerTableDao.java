package com.csxiaoyao.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.csxiaoyao.dao.IDinnerTableDao;
import com.csxiaoyao.entity.DinnerTable;
import com.csxiaoyao.entity.TableStatus;
import com.csxiaoyao.utils.JdbcUtils;

public class DinnerTableDao implements IDinnerTableDao{

	@Override
	public DinnerTable findById(int id) {
		String sql = "select * from dinnerTable where id=?";
		try {
			return JdbcUtils.getQuerrRunner().query(sql, new BeanHandler<DinnerTable>(DinnerTable.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> findByStatus(TableStatus ts) {
		String sql = "select * from dinnerTable where tableStatus=?";
//		int status = -1;
		// 判断		
//		if (ts == TableStatus.Free) {
//			status = 0;   // 未预定状态
//		} else {
//			status = 1;
//		}
		try {
			return JdbcUtils.getQuerrRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class), ts.ordinal());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
