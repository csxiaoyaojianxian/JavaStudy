package com.csxiaoyao.lucene.utils;

import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 测试Lucene内置和第三方分词器的分词效果
 */
public class TestAnalyzer {
	private static void testAnalyzer(Analyzer analyzer, String text) throws Exception {
		System.out.println("当前使用的分词器：" + analyzer.getClass());
		TokenStream tokenStream = analyzer.tokenStream("content",new StringReader(text));
		tokenStream.addAttribute(TermAttribute.class);
		while (tokenStream.incrementToken()) {
			TermAttribute termAttribute = tokenStream.getAttribute(TermAttribute.class);
			System.out.println(termAttribute.term());
		}
	}
	
	public static void main(String[] args) throws Exception{
		//Lucene内置的分词器
		testAnalyzer(new StandardAnalyzer(LuceneUtil.getVersion()),"上海自来水来自海上");
		testAnalyzer(new FrenchAnalyzer(LuceneUtil.getVersion()),"上海自来水来自海上");
		testAnalyzer(new RussianAnalyzer(LuceneUtil.getVersion()),"上海自来水来自海上");
		testAnalyzer(new ChineseAnalyzer(),"上海自来水来自海上");
		testAnalyzer(new CJKAnalyzer(LuceneUtil.getVersion()),"上海自来水来自海上");
		testAnalyzer(new CJKAnalyzer(LuceneUtil.getVersion()),"上海自来水来自海上");
		testAnalyzer(new FrenchAnalyzer(LuceneUtil.getVersion()),"上海自来水来自海上");
		//IKAnalyzer
		testAnalyzer(new IKAnalyzer(),"上海自来水来自海上");
		
	}
}