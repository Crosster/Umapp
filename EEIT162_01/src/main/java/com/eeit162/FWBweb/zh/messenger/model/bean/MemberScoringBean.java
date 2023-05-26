package com.eeit162.FWBweb.zh.messenger.model.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Memberscoring")
public class MemberScoringBean {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",columnDefinition = "integer")
	private Integer id;
	
	@Column(name = "scoring",columnDefinition = "integer")
	private Integer scoring;
	
	@Column(name = "created_at",columnDefinition = "date")
	private Date created;
	
	@Column(name = "f_member_id",columnDefinition = "integer")
	private Integer FKMemberId;
	
	@Column(name = "f_graded_id",columnDefinition = "integer")
	private Integer FKGradedId;
	
	//ref member
//	private Member member;

	public MemberScoringBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScoring() {
		return scoring;
	}

	public void setScoring(Integer scoring) {
		this.scoring = scoring;
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

	public Integer getFKGradedId() {
		return FKGradedId;
	}

	public void setFKGradedId(Integer fKGradedId) {
		FKGradedId = fKGradedId;
	}
	

	
	
	
	
}
