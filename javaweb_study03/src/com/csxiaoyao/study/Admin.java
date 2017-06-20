package com.csxiaoyao.study;

import com.csxiaoyao.utils.Column;
import com.csxiaoyao.utils.Id;
import com.csxiaoyao.utils.Table;

// Admin=a_admin
@Table(tableName="a_admin")
public class Admin {

	@Id
	@Column(columnName = "a_id")
	private int id;
	
	@Column(columnName = "a_userName")
	private String userName;
	
	@Column(columnName = "a_pwd")
	private String pwd;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", pwd=" + pwd + ", userName=" + userName
				+ "]";
	}
	
}
