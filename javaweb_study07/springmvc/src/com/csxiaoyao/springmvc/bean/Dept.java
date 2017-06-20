package com.csxiaoyao.springmvc.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_dept")
public class Dept implements Serializable{
	private int deptId;
    private String  deptName;
    private Set<Employee> employees;
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dept_id", unique = true, nullable = false)
    public int getDeptId() {
        return deptId;
    }
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	@Column(name = "dept_name")
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
    /**
     * @OneToMany 与 OneToOne相似，也用mappedBy,命名策略参考Employee与Card
     */
    @OneToMany(mappedBy = "dept")
    public Set<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}
