package com.csxiaoyao.contactSys_web.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.csxiaoyao.contactSys_web.dao.ContactDao;
import com.csxiaoyao.contactSys_web.dao.impl.ContactDaoImpl;
import com.csxiaoyao.contactSys_web.entity.Contact;
/**
 * 
 * @ClassName: TestContactOperatorImpl
 * @Description: 联系人操作实现类的测试类
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年1月21日 上午11:31:35
 *
 */
public class TestContactOperatorImpl {
	ContactDao operator = null;
	/**
	 * 初始化对象实例
	 */
	@Before
	public void init(){
		operator = new ContactDaoImpl();
	}

	@Test
	public void testAddContact(){
		Contact contact = new Contact();
		//contact.setId("1");
		contact.setName("sunshine");
		contact.setGender("男");
		contact.setAge(24);
		contact.setPhone("17186391724");
		contact.setEmail("sunjianfeng@csxiaoyao.com");
		contact.setQq("1724338257");
		operator.addContact(contact);
	}
	
	@Test
	public void testUpdateContact(){
		Contact contact = new Contact();
		contact.setId("1"); //修改的ID
		contact.setName("sun");
		contact.setGender("男");
		contact.setAge(25);
		contact.setPhone("15189736013");
		contact.setEmail("1724338257@qq.com");
		contact.setQq("1724338257");
		operator.updateContact(contact);
	}
	
	@Test
	public void testDeleteContact(){
		operator.deleteContact("1");
	}
	
	@Test
	public void testFindAll(){
		List<Contact> list = operator.findAll();
		for (Contact contact : list) {
			System.out.println(contact);
		}
	}
	
	@Test
	public void testFindById(){
		Contact contact = operator.findById("1");
		System.out.println(contact);
	}
	
	@Test
	public void testCheckContact(){
		System.out.println(operator.checkContact("sun"));
	}
}
