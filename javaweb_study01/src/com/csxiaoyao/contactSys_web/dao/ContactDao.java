package com.csxiaoyao.contactSys_web.dao;

import com.csxiaoyao.contactSys_web.entity.Contact;

import java.util.List;
/**
 * 
 * @ClassName: ContactDao
 * @Description: TODO
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年1月21日 上午2:22:10
 *
 */
public interface ContactDao {
	public void addContact(Contact contact);  //添加联系人
	public void updateContact(Contact contact);  //修改联系人
	public void deleteContact(String id);  //删除联系人
	public List<Contact> findAll();  //查询所有联系人
	public Contact findById(String id);  //根据编号查询联系人
	public boolean checkContact(String name);  //根据姓名查询是否重复
}
