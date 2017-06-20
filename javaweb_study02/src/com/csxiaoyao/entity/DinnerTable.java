package com.csxiaoyao.entity;

import java.util.Date;

public class DinnerTable {

	private int id;// PRIMARY KEY AUTO_INCREMENT, -- 餐桌主键
	private String tableName;// VARCHAR(20), -- 餐桌名
	private int tableStatus;// INT DEFAULT 0, -- 餐桌状态：0，空闲； 1，预定
	private Date orderDate;// DATETIME
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(int tableStatus) {
		this.tableStatus = tableStatus;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}	
}
