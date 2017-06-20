package com.csxiaoyao.compositeKey;

public class User {

	// 假设名字跟地址，不会重复
	private CompositeKeys keys;
	private int age;
	
	public CompositeKeys getKeys() {
		return keys;
	}
	public void setKeys(CompositeKeys keys) {
		this.keys = keys;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
