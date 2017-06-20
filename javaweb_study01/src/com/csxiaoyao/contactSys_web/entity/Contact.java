package com.csxiaoyao.contactSys_web.entity;
/**
 * 
 * @ClassName: Contact
 * @Description: TODO
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年1月21日 上午2:19:42
 *
 */
public class Contact {
	private String id;
	private String name;
	private String gender;
	private int age;
	private String phone;
	private String email;
	private String qq;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Override
	public String toString() {
		return "Contact [age=" + age + ", email=" + email + ", gender="
				+ gender + ", id=" + id + ", name=" + name + ", phone=" + phone
				+ ", qq=" + qq + "]";
	}
	
}
