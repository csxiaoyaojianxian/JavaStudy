package com.csxiaoyao.lucene.utils;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.csxiaoyao.lucene.bean.Article;

/**
 * 工具类
 */
public class LuceneUtil {
	
	private static Directory directory;
	private static Version version;
	private static Analyzer analyzer;
	private static MaxFieldLength maxFieldLength;
	
	static{
		try {
			directory =  FSDirectory.open(new File("/index"));
			version = Version.LUCENE_30;
			analyzer = new StandardAnalyzer(version);
			maxFieldLength = MaxFieldLength.LIMITED;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static Directory getDirectory() {
		return directory;
	}

	public static Version getVersion() {
		return version;
	}

	public static Analyzer getAnalyzer() {
		return analyzer;
	}

	public static MaxFieldLength getMaxFieldLength() {
		return maxFieldLength;
	}

	//不让外界new该帮助类
	private LuceneUtil(){}	
	
	//将JavaBean转成Document对象
	public static Document javabean2document(Object obj) throws Exception{
		//创建Docuemnt对象
		Document document = new Document();
		//获取obj引用的对象字节码
		Class clazz = obj.getClass();
		//通过对象字节码获取私有的属性
		java.lang.reflect.Field[] reflectFields = clazz.getDeclaredFields();
		//迭代
		for(java.lang.reflect.Field reflectField : reflectFields){
			//强力反射
			reflectField.setAccessible(true);
			//获取属性名，id/title/content
			String name = reflectField.getName();
			//人工拼接方法名
			String methodName = "get" + name.substring(0,1).toUpperCase()+name.substring(1);
			//获取方法，例如：getId()/getTitle()/getContent()
			Method method = clazz.getMethod(methodName,null);
			//执行方法
			String value = method.invoke(obj,null).toString();
			//加入到Document对象中去，这时javabean的属性与document对象的属性相同
			document.add(new Field(name,value,Store.YES,Index.ANALYZED));
		}
		//返回document对象
		return document;
	}
	
	//将Document对象转成JavaBean对象
	public static Object document2javabean(Document document,Class clazz) throws Exception{
		Object obj = clazz.newInstance();
		java.lang.reflect.Field[] reflectFields = clazz.getDeclaredFields();
		for(java.lang.reflect.Field reflectField : reflectFields){
			reflectField.setAccessible(true);
			String name = reflectField.getName();//id/title/content
			String value = document.get(name);//1/培训/传智是一家培训机构
			BeanUtils.setProperty(obj,name,value);//封装javabean对应的属性中去，通过setXxx()方法
		}
		return obj;
	}

	
}