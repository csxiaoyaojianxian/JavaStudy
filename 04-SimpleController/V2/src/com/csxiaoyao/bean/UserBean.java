package com.csxiaoyao.bean;

public class UserBean {
	private String username;
	private String password;
	
	public UserBean(){
		
	}
	public UserBean(String username){
		setUsername(username);
	}
	public UserBean(String username, String password){
		setUsername(username);
		setPassword(password);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(Object obj) {
		UserBean userBean = (UserBean) obj;
		if (username.equals(userBean.getUsername()) && password.equals(userBean.getPassword())) {
			return true;
		}
		return false;
	}
}