package com.csxiaoyao.lucene.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import com.csxiaoyao.lucene.bean.Article;

/**
 * 
 * @ClassName: LuceneOp
 * @Description: 增删改查索引库以及索引优化
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年2月1日 下午9:22:37
 */
public class LuceneCrud {
	/**
	 * 添加（普通）
	 * 默认满足10个cfs文本合并
	 * @return void
	 */
	@Test
	public static void add(Integer id, String title, String content) throws Exception{
		Article article = new Article(id, title, content);
		Document document = LuceneUtil.javabean2document(article);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);//核心
		//合并cfs文本
		//indexWriter.optimize();
		//设置合并因子，默认满足10个cfs文本一合并
		indexWriter.setMergeFactor(10);	
		indexWriter.close();
	}
	/**
	 * 添加（设置合并策略和评分）
	 * @return void
	 */
	@Test
	public static void add(Integer id, String title, String content, int number, float score) throws Exception{
		Article article = new Article(id, title, content);
		Document document = LuceneUtil.javabean2document(article);
		//人工设置该document的得分
		document.setBoost(score);
		
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
		indexWriter.addDocument(document);//核心
		//设置合并因子，默认满足10个cfs文本一合并
		indexWriter.setMergeFactor(number);
		
		indexWriter.close();
	}
	/**
	 * 添加（优化方法）
	 * 使用RAMDirectory，类似于内存索引库，能解决是的读取索引库文件的速度问题
	 * @throws Exception
	 */
	@Test
	public void addOptimize(Integer id, String title, String content) throws Exception{
		Article article = new Article(id, title, content);
		Document document = LuceneUtil.javabean2document(article);
		//硬盘索引库
		Directory fsDirectory = FSDirectory.open(new File("/index"));
		//内存索引库，因为硬盘索引库的内容要同步到内存索引库中
		Directory ramDirectory = new RAMDirectory(fsDirectory);	
		//指向硬盘索引库的字符流，true表示如果内存索引库中和硬盘索引库中的相同的document对象时，先删除硬盘索引库中的document对象，
		//再将内存索引库的document对象写入硬盘索引库中
		//反之是false，默认为false，这个boolean值写在硬盘字符流的构造器
		IndexWriter fsIndexWriter = new IndexWriter(fsDirectory,LuceneUtil.getAnalyzer(),true,LuceneUtil.getMaxFieldLength());
		//指向内存索引库的字符流
		IndexWriter ramIndexWriter = new IndexWriter(ramDirectory,LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
		//将document对象写入内存索引库
		ramIndexWriter.addDocument(document);
		ramIndexWriter.close();
		//将内存索引库的所有document对象同步到硬盘索引库中
		fsIndexWriter.addIndexesNoOptimize(ramDirectory);
		fsIndexWriter.close();
	}
	
	@Test
	public static void update(Integer id, String title, String content) throws Exception{
		Article newArticle = new Article(id, title, content);
		Document document = LuceneUtil.javabean2document(newArticle);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
		//更新document对象
		/*
		 * 参数一：term表示需要更新的document对象，id表示document对象中的id属性的值
		 * 参数二：新的document对象
		 */
		indexWriter.updateDocument(new Term("id",id.toString()),document);//核心
		indexWriter.close();
	}
	@Test
	public static void delete(Integer id) throws Exception{
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
		indexWriter.deleteDocuments(new Term("id",id.toString()));//核心
		indexWriter.close();
	}
	
	@Test
	public static void deleteAll() throws Exception{
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getAnalyzer(),LuceneUtil.getMaxFieldLength());
		indexWriter.deleteAll();//核心
		indexWriter.close();
	}
	/**
	 * @Title: findAllByKeywords
	 * @Description: 按关键词检索
	 * @param keywords 关键词
	 * @param count 检索数量
	 * @param isHighLight 是否关键词高亮
	 * @param isDesc 是否按照降序排列
	 */
	@Test
	public static List<Article> findAllByKeywords(String keywords, int count, boolean isHighLight) throws Exception{
		List<Article> articleList = new ArrayList<Article>();
		QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(),"content",LuceneUtil.getAnalyzer());
		Query query = queryParser.parse(keywords);
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
		/**
		 * 排序策略
		 */
		//  默认按得分度高低排序
		TopDocs topDocs = indexSearcher.search(query,count);
		//  创建排序对象
		//  参数一：id表示依据document对象中的哪个字段排序，例如：id
		//  参数二：SortField.INT表示document对象中该字段的类型，以常量方式书写
		//  参数三：true表示降序，类似于order by id desc，false表示升序，类似于order by id asc
		//		Sort sort = new Sort(
		//				new SortField("count",SortField.INT,true),
		//				new SortField("id",SortField.INT,false));	
//		Sort sort = new Sort(new SortField("id",SortField.INT,false));
//		TopDocs topDocs = indexSearcher.search(query,null,count,sort);
		/**
		 * 关键词高亮
		 */
		//格式对象
		Formatter formatter = new SimpleHTMLFormatter("<font color='red'>","</font>");
		//关键字对象
		Scorer scorer = new QueryScorer(query);
		//高亮对象
		Highlighter highlighter = new Highlighter(formatter,scorer);

		for(int i=0;i<topDocs.scoreDocs.length;i++){
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			int no = scoreDoc.doc;
			Document document = indexSearcher.doc(no);
			if(isHighLight == true){
				//关键字高亮
				String titleHighlighter = highlighter.getBestFragment(LuceneUtil.getAnalyzer(),"title",document.get("title"));
				String contentHighlighter = highlighter.getBestFragment(LuceneUtil.getAnalyzer(),"content",document.get("content"));		
				//将高亮后的结果再次封装到document对象中
				document.getField("title").setValue(titleHighlighter);
				document.getField("content").setValue(contentHighlighter);
			}
			Article article = (Article)LuceneUtil.document2javabean(document,Article.class);
			articleList.add(article);
		}
		indexSearcher.close();
		return articleList;
	}
}