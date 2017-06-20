package com.csxiaoyao.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mysql.jdbc.Driver;

/**
 * 
 * @ClassName: Configuration
 * @Description: 解析or_mapping.xml文件
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年2月24日 上午11:08:50
 *
 */
public class Configuration {
	static File file = new File("/or_mapping.xml");// D:/or_mapping.xml
	private String url = null;
	private String user = null;
	private String password = null;
	private String driverClass = null;
	private Connection conn = null;

	private String beanName = null;
	private String tableName = null;
	private String tableId = null;

	private List<TableProperties> list = null;
	//静态代码块
	{
		try {
			init();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	// 初始化参数
	public void init() throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(file);
		List<Element> selectNodes = doc.selectNodes("/*/jdbc/*");
		for (Element element : selectNodes) {
			String name = element.element("name").getText();
			String value = element.element("value").getText();
			if ("driver_class".equals(name)) {
				driverClass = value;
			} else if ("url_path".equals(name)) {
				url = value;
			} else if ("db_username".equals(name)) {
				user = value;
			} else if ("db_userpassword".equals(name)) {
				password = value;
			}
		}
		List<Element> classNode = doc.selectNodes("/*/class/*");
		list = new ArrayList<TableProperties>();
		for (Element element : classNode) {
			String name = element.getName();
			if ("name".equals(name)) {
				beanName = element.getText();
			} else if ("table".equals(name)) {
				tableName = element.getText();
			} else if ("id".equals(name)) {
				// 默认一个
				tableId = element.element("name").getText();
			} else if ("property".equals(name)) {
				// 封装属性
				TableProperties tableProperties = new TableProperties();
				String properyName = element.element("name").getText();
				String properyColumn = element.element("column").getText();
				String properyType = element.element("type").getText();
				String properyLazy = element.element("lazy").getText();
				tableProperties.setColumn(properyColumn);
				tableProperties.setLazyType(properyLazy);
				tableProperties.setType(properyType);
				tableProperties.setName(properyName);
				list.add(tableProperties);
			}
		}
	}

	public Connection openDBConnection()
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		// 1.创建驱动程序类对象
		// Driver driver = new com.mysql.jdbc.Driver();
		// 2.设置用户名与密码
		Properties props = new Properties();
		props.setProperty("user", user);
		props.setProperty("password", password);
		// 3.连接数据库，返回连接对象
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		conn = driver.connect(url, props);
		return conn;
	}

	public boolean closeDBConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean createUserTable() throws SQLException {
		boolean result = false;
		Statement stat = conn.createStatement();
		String checkTable = "show tables like \"user\"";
		ResultSet resultSet = stat.executeQuery(checkTable);
		if (resultSet.next()) {
			System.out.println("表名已存在");
		} else {
			String sql = "create table user( userID bigint(20) PRIMARY key,username varchar(20) ,password varchar(20))";
			result = stat.execute(sql);
		}
		return result;
	}

	public String getBeanName() {
		return beanName;
	}
	public String getTableName() {
		return tableName;
	}
	public List<TableProperties> getList() {
		return list;
	}
}
class TableProperties {
	private String name;
	private String column;
	private String type;
	private String lazyType;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLazyType() {
		return lazyType;
	}
	public void setLazyType(String lazyType) {
		this.lazyType = lazyType;
	}
	@Override
	public String toString() {
		return "TableProperties [name=" + name + ", column=" + column + ", type=" + type + ", lazyType=" + lazyType + "]";
	}
}