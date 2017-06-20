package com.csxiaoyao.springmvc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csxiaoyao.springmvc.dao.IDeptDao;
import com.csxiaoyao.springmvc.service.IDeptService;


@Service("DeptService")
public class DeptService implements IDeptService {
	@Resource
	private IDeptDao deptDao;
	
	
	
}
