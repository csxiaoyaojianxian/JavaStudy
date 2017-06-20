package com.csxiaoyao.springmvc.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_card")
public class Card implements Serializable{
	private int cardId;
    private String  cardNumber;
    private Employee  employee;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "card_id", unique = true, nullable = false)
    public Integer getCardId() {
        return cardId;
    }
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
    @Column(name = "card_number", unique = true )
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
     * @OneToOne：一对一关联
     * mappedBy = "card"：意思是说这里的一对一配置参考了card,调用了Employee类的getCard()
     * card又是什么呢?card是Employee类中的getCard()，注意不是Employee类中的card属性
     * Person类中的OneToOne配置就是在getCard()方法上面配的
     * 如果Person类中的getCard()方法改成getIdCard()，其他不变的话,
     * 这里就要写成：mappedBy = "idCard"
     */
    @OneToOne(mappedBy = "card")
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
