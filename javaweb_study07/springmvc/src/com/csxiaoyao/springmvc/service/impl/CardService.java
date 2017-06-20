package com.csxiaoyao.springmvc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csxiaoyao.springmvc.dao.ICardDao;
import com.csxiaoyao.springmvc.service.ICardService;

@Service("CardService")
public class CardService implements ICardService {
	@Resource
	private ICardDao cardDao;
	
	
	
	
}
