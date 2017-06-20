package com.csxiaoyao.mapping.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// javabean设计
public class User {
	private int userId;
	private String userName;
	// 一个用户，对应的多个地址
	private Set<String> address;
	private List<String> addressList = new ArrayList<String>(); 
	//private String[] addressArray; // 映射方式和list一样     <array name=""></array>
	private Map<String,String> addressMap = new HashMap<String, String>();

	public Map<String, String> getAddressMap() {
		return addressMap;
	}
	public void setAddressMap(Map<String, String> addressMap) {
		this.addressMap = addressMap;
	}
	public List<String> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Set<String> getAddress() {
		return address;
	}
	public void setAddress(Set<String> address) {
		this.address = address;
	}
}