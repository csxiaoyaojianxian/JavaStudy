package com.csxiaoyao.springmvc.dao.impl;

import org.springframework.stereotype.Repository;

import com.csxiaoyao.springmvc.bean.Card;
import com.csxiaoyao.springmvc.dao.ICardDao;

@Repository("ICardDao")
public class CardDao extends AbstractHibernateDao<Card> implements ICardDao{
	public CardDao() {
		super();
		setEntityType(Card.class);
	}
}
