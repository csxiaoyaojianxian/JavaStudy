package com.csxiaoyao.mapping.many2many;
import java.util.HashSet;
import java.util.Set;

/**
 * 开发人员
 */
public class Developer {
	private int d_id;
	private String d_name;
	// 开发人员，参数的多个项目
	private Set<Project> projects = new HashSet<Project>();

	public int getD_id() {
		return d_id;
	}
	public void setD_id(int dId) {
		d_id = dId;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String dName) {
		d_name = dName;
	}
	public Set<Project> getProjects() {
		return projects;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
}
