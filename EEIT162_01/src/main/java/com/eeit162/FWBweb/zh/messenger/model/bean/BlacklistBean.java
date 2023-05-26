package com.eeit162.FWBweb.zh.messenger.model.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Blacklist")
public class BlacklistBean {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",columnDefinition = "integer")
	private Integer id;
	
	@Column(name = "created_at",columnDefinition = "date")
	private Date created;
	
	@Column(name = "f_member_id",columnDefinition = "integer")
	private Integer FKMemberId;
	
	
	@Column(name = "f_ban_id",columnDefinition = "integer")
	private Integer FKBanId;
	
	//ref member
//	private Member member;

	public BlacklistBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getFKMemberId() {
		return FKMemberId;
	}

	public void setFKMemberId(Integer fKMemberId) {
		FKMemberId = fKMemberId;
	}

	public Integer getFKBanId() {
		return FKBanId;
	}

	public void setFKBanId(Integer fKBanId) {
		FKBanId = fKBanId;
	}
	
	

}
