package com.csxiaoyao.interceptor;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class LogWriter {
	private Date date;
	private String actionName;
	static Element actionElement;
	static Document doc;

	static File file = new File("/log.xml");// D:/log.xml
	public static void log(String reqeustName, String sTime) throws DocumentException, Exception {
		// 配置文件路径
		SAXReader reader = new SAXReader();
		doc = reader.read(file);
		Element element = doc.getRootElement();
		actionElement = element.addElement("action");
		actionElement.addElement("name").setText(reqeustName);
		actionElement.addElement("s-time").setText(sTime);
		// 写xml
		OutputFormat format = OutputFormat.createPrettyPrint();
//		format.setEncoding("gb2312");
		XMLWriter writer = new XMLWriter(new FileWriter(file), format);
		writer.write(doc);
		writer.close();
	}

	// 追加记录
	public static void log(String eTime, String resultName, int i) throws DocumentException, Exception {
		actionElement.addElement("e-time").setText(eTime);
		actionElement.addElement("result").setText(resultName);
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(new FileWriter(file), format);
		writer.write(doc);
		writer.close();
	}
}
