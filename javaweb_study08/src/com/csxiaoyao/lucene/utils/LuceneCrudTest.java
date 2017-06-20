package com.csxiaoyao.lucene.utils;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.csxiaoyao.lucene.bean.Article;

public class LuceneCrudTest {

	@Test
	public void test() throws Exception {
		/**
		 * 创建索引库
		 */
		LuceneCrud.add(1, "博客", "禅林阆苑是CS逍遥剑仙的个人博客");
		LuceneCrud.add(2, "博客", "网址为：http://www.csxiaoyao.com");
		LuceneCrud.add(3, "名称", "公司的名称是SUNSHINE STUDIO");
		LuceneCrud.add(4, "昵称", "我在博客使用的昵称为CS逍遥剑仙");
		LuceneCrud.add(5, "博客", "CS逍遥剑仙的个人博客是禅林阆苑");
		/**
		 * 根据关键字从索引库中查询符合条件的数据
		 */
		List<Article> articleList = LuceneCrud.findAllByKeywords("博客", 3, true);
		for(Article a : articleList){
			System.out.println( a );
		}
	}

}