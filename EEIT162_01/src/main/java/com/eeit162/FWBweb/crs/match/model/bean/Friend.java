package com.eeit162.FWBweb.crs.match.model.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import com.eeit162.FWBweb.daka.login.Members;

@Entity
@Table(name = "friend"
, uniqueConstraints = {@UniqueConstraint(columnNames = { "FK_member1_id", "FK_member2_id" })}
		)
public class Friend {
	public Friend() {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "integer")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "FK_member1_id", referencedColumnName = "id", nullable = false)
	private Members member1;

	@ManyToOne
	@JoinColumn(name = "FK_member2_id", referencedColumnName = "id", nullable = false)
	private Members member2;
	
	//已確認、未審核、拒絕
	@Column(name = "status",columnDefinition = "nvarchar(10)", nullable = false)
	private String status;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatedAt;

	@PrePersist
	public void onCreated() {
		if (createdAt == null) {
			createdAt = new Date();
		}
		if (updatedAt == null) {
			updatedAt = new Date();
		}
	}
	
	@PreUpdate
	public void onUpdate() {
	    updatedAt = new Date();
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Members getMember1() {
		return member1;
	}

	public void setMember1(Members member1) {
		this.member1 = member1;
	}

	public Members getMember2() {
		return member2;
	}

	public void setMember2(Members member2) {
		this.member2 = member2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
