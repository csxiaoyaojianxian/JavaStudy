package com.csxiaoyao.dao;

import com.csxiaoyao.entity.Admin;

/**
 * 
 * @ClassName: IAdminDao
 * @Description: 管理员模块dao接口
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年1月29日 下午11:43:11
 *
 */
public interface IAdminDao {
	void save(Admin admin);
	Admin findByAdmin(Admin admin);
}
