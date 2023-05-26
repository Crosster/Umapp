package com.eeit162.FWBweb.cheng.activity.bean;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "regist")
public class RegistBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "regist_id")
	private Integer registId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "regist_create_at")
	private Date registCreateAt;
	
	@Column(name = "regist_status")
	private String registStatus;
	
	@Column(name = "regist_payment")
	private Integer registPayment;
	
	@Column(name = "regist_join_check")
	private Integer registJoinCheck;
	
// FK：
	// private activity activity_id;
	
// FK：
	// private members id;
	
	public RegistBean() {
	}

	public Integer getRegist_id() {
		return registId;
	}

	public void setRegist_id(Integer regist_id) {
		this.registId = regist_id;
	}

	public Date getRegist_create_at() {
		return registCreateAt;
	}

	public void setRegist_create_at(Date regist_create_at) {
		this.registCreateAt = regist_create_at;
	}

	public String getRegist_status() {
		return registStatus;
	}

	public void setRegist_status(String regist_status) {
		this.registStatus = regist_status;
	}

	public Integer getRegist_payment() {
		return registPayment;
	}

	public void setRegist_payment(Integer regist_payment) {
		this.registPayment = regist_payment;
	}

	public Integer getRegist_join_check() {
		return registJoinCheck;
	}

	public void setRegist_join_check(Integer regist_join_check) {
		this.registJoinCheck = regist_join_check;
	}
	
}
