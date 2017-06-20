package com.csxiaoyao.lucene.service;

import java.util.List;

import com.csxiaoyao.lucene.bean.Article;
import com.csxiaoyao.lucene.bean.Page;
import com.csxiaoyao.lucene.dao.ArticleDao;

/**
 * 业务层
 */
public class ArticleService {
	//持久层
	private ArticleDao articleDao = new ArticleDao();
	/**
	 * 根据关键字和页号，查询内容
	 */
	public Page show(String keywords,int currPageNO) throws Exception{
		Page page = new Page();
		
		//封装当前页号
		page.setCurrPageNO(currPageNO);
		
		//封装总记录数
		int allRecordNO = articleDao.getAllRecord(keywords);
		page.setAllRecordNO(allRecordNO);
		
		//封装总页数
		int allPageNO = 0;
		if(page.getAllRecordNO() % page.getPerPageSize() == 0){
			allPageNO = page.getAllRecordNO() / page.getPerPageSize();
		}else{
			allPageNO = page.getAllRecordNO() / page.getPerPageSize() + 1;
		}
		page.setAllPageNO(allPageNO);
		
		//封装内容
		int size = page.getPerPageSize();
		int start = (page.getCurrPageNO()-1) * size;
		List<Article> articleList = articleDao.findAll(keywords,start,size);
		page.setArticleList(articleList);
		
		return page;
	}
	
	//测试
	public static void main(String[] args) throws Exception{
		ArticleService test = new ArticleService();
		Page page = test.show("博客",6);
		
		System.out.println(page.getCurrPageNO());
		System.out.println(page.getPerPageSize());
		System.out.println(page.getAllRecordNO());
		System.out.println(page.getAllPageNO());
		for(Article a : page.getArticleList()){
			System.out.println(a);
		}
	}
}