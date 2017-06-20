package com.csxiaoyao.contactSys_web.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.csxiaoyao.contactSys_web.dao.ContactDao;
import com.csxiaoyao.contactSys_web.entity.Contact;
import com.csxiaoyao.contactSys_web.util.Constants;
import com.csxiaoyao.contactSys_web.util.XMLUtil;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
/**
 * 
 * @ClassName: ContactDaoImpl
 * @Description: TODO
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年1月21日 上午11:25:44
 *
 */
public class ContactDaoImpl implements ContactDao {
	/**
	 * 添加联系人
	 * 【随机UUID】【xml操作】
	 */
	public void addContact(Contact contact) {
		try {
			Document doc = null;
			Element rootElem = null;
			if (!checkFileExist()) {
				/**
				 * 把contact对象保存到xml文件中
				 */
				// 如果没有xml文件，则创建xml文件
				doc = DocumentHelper.createDocument();
				// 创建根标签
				rootElem = doc.addElement("contactList");
			} else {
				// 如果有xml文件，则读取xml文件
				doc = XMLUtil.getDocument(Constants.XML_FILE_URI);
				// 如果有xml文件，读取根标签
				rootElem = doc.getRootElement();
			}
			// 添加contact标签
			/**
			 * <contact id="1"> 
			 * 		<name>sun</name> 
			 * 		<gender>男</gender>
			 * 		<age>24</age> 
			 * 		<phone>17186391724</phone> 
			 * 		<email>sunjianfeng@csxiaoyao.com</email>
			 * 		<qq>1724338257</qq> 
			 * </contact>
			 */
			Element contactElem = rootElem.addElement("contact");
			/**
			 * 由系统自动生成随机且唯一的ID值，赋值给联系人属性
			 * 【随机ID】
			 */
			String uuid = UUID.randomUUID().toString().replace("-", "");
			contactElem.addAttribute("id", uuid);
			contactElem.addElement("name").setText(contact.getName());
			contactElem.addElement("gender").setText(contact.getGender());
			contactElem.addElement("age").setText(Integer.toString(contact.getAge()));
			contactElem.addElement("phone").setText(contact.getPhone());
			contactElem.addElement("email").setText(contact.getEmail());
			contactElem.addElement("qq").setText(contact.getQq());
			// 把Document写出到xml文件
			XMLUtil.write2xml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 删除联系人
	 * 【xpath】
	 */
	public void deleteContact(String id) {
		try {
			// 1.读取xml文件
			Document doc = XMLUtil.getDocument(Constants.XML_FILE_URI);
			// 2.查询需要删除id的contact
			Element contactElem = (Element) doc.selectSingleNode("//contact[@id='" + id + "']");
			// 3.删除标签
			if (contactElem != null) {
				contactElem.detach();
			}
			// 4.把Document写出到xml文件
			XMLUtil.write2xml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查询所有联系人
	 */
	public List<Contact> findAll() {
		// 1.读取xml文件
		Document doc = XMLUtil.getDocument(Constants.XML_FILE_URI);
		// 2.创建List对象，存放结果
		List<Contact> list = new ArrayList<Contact>();
		// 3.读取contact标签
		List<Element> conList = (List<Element>) doc.selectNodes("//contact");
		for (Element e : conList) {
			// 创建Contact对象
			Contact c = new Contact();
			c.setId(e.attributeValue("id"));
			c.setName(e.elementText("name"));
			c.setGender(e.elementText("gender"));
			c.setAge(Integer.parseInt(e.elementText("age")));
			c.setPhone(e.elementText("phone"));
			c.setEmail(e.elementText("email"));
			c.setQq(e.elementText("qq"));
			// 把Contact放入list中
			list.add(c);
		}
		return list;
	}
	/**
	 * 根据编号查询联系人
	 */
	public Contact findById(String id) {
		Document doc = XMLUtil.getDocument(Constants.XML_FILE_URI);
		Element e = (Element) doc.selectSingleNode("//contact[@id='" + id + "']");
		Contact c = null;
		if (e != null) {
			// 创建Contact对象
			c = new Contact();
			c.setId(e.attributeValue("id"));
			c.setName(e.elementText("name"));
			c.setGender(e.elementText("gender"));
			c.setAge(Integer.parseInt(e.elementText("age")));
			c.setPhone(e.elementText("phone"));
			c.setEmail(e.elementText("email"));
			c.setQq(e.elementText("qq"));
		}
		return c;
	}
	/**
	 * 修改联系人
	 */
	public void updateContact(Contact contact) {
		/**
		 * 修改id值为2的联系人 
		 * 1）查询id值为2的contact标签 
		 * 2）修改contact标签的内容
		 */
		try {
			// 1.读取xml文件
			Document doc = XMLUtil.getDocument(Constants.XML_FILE_URI);
			// 2.根据id定位element
			Element contactElem = (Element) doc.selectSingleNode("//contact[@id='" + contact.getId() + "']");
			// 3.修改contact标签内容
			contactElem.element("name").setText(contact.getName());
			contactElem.element("gender").setText(contact.getGender());
			contactElem.element("age").setText(contact.getAge() + "");
			contactElem.element("phone").setText(contact.getPhone());
			contactElem.element("email").setText(contact.getEmail());
			contactElem.element("qq").setText(contact.getQq());
			// 4.把Document写出到xml文件
			XMLUtil.write2xml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查询姓名是否重复
	 * true：重复 false：不重复
	 */
	public boolean checkContact(String name) {
		// 如果文件不存在，直接返回false
		if (!checkFileExist()) {
			return false;
		}
		// 查询name标签的内容是否存在
		Document doc = XMLUtil.getDocument(Constants.XML_FILE_URI);
		Element nameElem = (Element) doc.selectSingleNode("//name[text()='" + name + "']");
		if (nameElem != null) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断文件是否存在
	 */
	public static boolean checkFileExist(){
		File file = new File(Constants.XML_FILE_URI);
		if(!file.exists()){
			return false;
		}else{
			return true;
		}
	}
}
