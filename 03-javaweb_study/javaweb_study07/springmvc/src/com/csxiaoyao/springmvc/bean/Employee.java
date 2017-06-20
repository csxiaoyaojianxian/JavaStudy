package com.csxiaoyao.springmvc.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * @Entity 声明一个类为实体Bean
 * @Table(name = "xx")指定实体类映射的表,如果表名和实体类名一致,可以不指定
 */
@Entity
@Table(name = "t_employee")
public class Employee implements Serializable {
	private int id;
	private String email;					// 电子邮箱
	private String userName;				// 用户名
	private String password;				// 密码
	private Date createTime;				// 创建时间
	private Card card;                       // 身份证
	private Dept dept;                       // 部门
	/**
     * @Id 映射主键属性
     * @GeneratedValue —— 注解声明了主键的生成策略。该注解有如下属性
     * strategy 指定生成的策略,默认是GenerationType. AUTO
     *   GenerationType.AUTO 主键由程序控制
     *   GenerationType.TABLE 使用一个特定的数据库表格来保存主键
     *   GenerationType.IDENTITY 主键由数据库自动生成,主要是自动增长类型
     *   GenerationType.SEQUENCE 根据底层数据库的序列来生成主键，条件是数据库支持序列
     * generator 指定生成主键使用的生成器
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "email", nullable = true, length = 50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "user_name", nullable = false, length = 50)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "create_time", nullable = false)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
     * @OneToOne：一对一关联
     * cascade：级联,它可以有有五个值可选,分别是：
     *   CascadeType.PERSIST：级联新建
     *   CascadeType.REMOVE : 级联删除
     *   CascadeType.REFRESH：级联刷新
     *   CascadeType.MERGE  ： 级联更新
     *   CascadeType.ALL    ： 以上全部四项
     * @JoinColumn:主表外键字段
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cid")
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	/**
     * @ManyToOne：多对一
      * fetch = FetchType.LAZY,延迟加载策略,禁用懒加载可以用FetchType.EAGER
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "did")
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "id:"+id+" email:"+email+" userName:"+userName+" password:"+password+" createTime:"+createTime+
				" Card:"+ card+" Dept:" +dept; 
	}
}
