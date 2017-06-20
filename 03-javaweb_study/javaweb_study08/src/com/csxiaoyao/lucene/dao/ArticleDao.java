package com.csxiaoyao.lucene.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.csxiaoyao.lucene.bean.Article;
import com.csxiaoyao.lucene.utils.LuceneUtil;

/**
 * 持久层
 */
public class ArticleDao {
	/**
	 * 根据关键字，获取总记录数
	 * @return 总记录数
	 */
	public int getAllRecord(String keywords) throws Exception{
		QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(),"content",LuceneUtil.getAnalyzer());
		Query query = queryParser.parse(keywords);
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
		//返回前两条记录
		TopDocs topDocs = indexSearcher.search(query,2);
		indexSearcher.close();
		//返回符合条件的真实总记录数，不受2的影响
		return topDocs.totalHits;
		//返回符合条件的总记录数，受2的影响
		//return topDocs.scoreDocs.length;
	}
	/**
	 * 根据关键字，批量查询记录
	 * @param start 从第几条记录的索引号开始查询，索引号从0开始
	 * @param size  最多查询几条记录，不满足最多数目时，以实际为准
	 * @return 集合
	 */
	public List<Article> findAll(String keywords,int start,int size) throws Exception{
		List<Article> articleList = new ArrayList<Article>();
		
		QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(),"content",LuceneUtil.getAnalyzer());
		Query query = queryParser.parse(keywords);
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
		TopDocs topDocs = indexSearcher.search(query,100);
		//小技巧
		int middle = Math.min(start+size,topDocs.totalHits);
		for(int i=start;i<middle;i++){
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			int no = scoreDoc.doc;
			Document document = indexSearcher.doc(no);
			Article article = (Article) LuceneUtil.document2javabean(document,Article.class);
			articleList.add(article);
		}
		indexSearcher.close();
		return articleList;
	}
}