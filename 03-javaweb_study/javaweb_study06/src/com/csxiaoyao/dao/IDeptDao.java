package com.csxiaoyao.dao;

import java.util.List;

import com.csxiaoyao.entity.Dept;
/**
 * 
 * @ClassName: IDeptDao
 * @Description: 部门模块dao接口设计
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年1月29日 下午11:48:07
 *
 */
public interface IDeptDao {
	List<Dept> getAll();
	Dept findById( int id );
}
