package com.eeit162.FWBweb.zh.payment.model.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "memberOrderList")
public class MemberLevelOrderHistory {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "integer")
	private Integer id;
	
	@Column(name = "memberId", columnDefinition = "integer")
	private Integer memberId;
	
	@Column(name = "orderNumber", columnDefinition = "nvarchar(200)")
	private String orderNumber;
	
	@Column(name = "orderName", columnDefinition = "nvarchar(200)")
	private String orderName;
	
	@Column(name = "orderDate", columnDefinition = "datetime")
	private Date orderDate;
	
	@Column(name = "orderPrice", columnDefinition = "integer")
	private Integer orderPrice;

	public MemberLevelOrderHistory() {
	}

	public MemberLevelOrderHistory(Integer memberId, String orderNumber, String orderName, Date orderDate,
			Integer orderPrice) {
		this.memberId = memberId;
		this.orderNumber = orderNumber;
		this.orderName = orderName;
		this.orderDate = orderDate;
		this.orderPrice = orderPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Integer orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	
	

}
