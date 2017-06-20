package com.csxiaoyao.service;

import java.util.List;

import com.csxiaoyao.entity.Dept;

public interface IDeptService {

	List<Dept> getAll();
	Dept findById(int id);
}