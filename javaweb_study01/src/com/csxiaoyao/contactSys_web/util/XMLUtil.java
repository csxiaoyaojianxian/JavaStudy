package com.csxiaoyao.contactSys_web.util;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.csxiaoyao.contactSys_web.util.Constants;
/**
 * 
 * @ClassName: XMLUtil
 * @Description: TODO
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年1月21日 上午10:24:25
 *
 */
public class XMLUtil {
	/**
	 * 读取xml文档
	 */
	public static Document getDocument(String file){
		try {
			Document doc = new SAXReader().read(new File(file));
			return doc;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 写出到xml文档
	 */
	public static void write2xml(Document doc){
		try {
			FileOutputStream out = new FileOutputStream(Constants.XML_FILE_URI);
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(out,format);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
