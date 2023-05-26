package com.eeit162.FWBweb.zh.messenger.model.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "message")
public class MessageBean {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "integer")
	private Integer id;
	
	@Column(name = "text",columnDefinition = "nvarchar(max)")
	private String message;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss EEEE", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yy-MM-dd HH:mm:ss")
	@Column(name = "addtime",columnDefinition = "datetime")
	private Date addtime;
	
	@Column(name = "readtime",columnDefinition = "datetime")
	private Date readtime;
	
	@Column(name = "messenger_id",columnDefinition = "integer")
	private Integer messengerId;
	
	@Column(name = "f_member_id",columnDefinition = "integer")
	private Integer fkMemberId;
	
	//ref member
//	private Member member;
	
	public MessageBean() {
	}
	
	
	
	public MessageBean(Integer messengerId, Integer fkMemberId) {
		super();
		this.messengerId = messengerId;
		this.fkMemberId = fkMemberId;
	}



	@PrePersist
	public void onCreate() {
		if (addtime == null) {
			addtime = new Date();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getReadtime() {
		return readtime;
	}

	public void setReadtime(Date readtime) {
		this.readtime = readtime;
	}

	public Integer getMessengerId() {
		return messengerId;
	}

	public void setMessengerId(Integer messengerId) {
		this.messengerId = messengerId;
	}



	public Integer getFkMemberId() {
		return fkMemberId;
	}

	public void setFkMemberId(Integer fkMemberId) {
		this.fkMemberId = fkMemberId;
	}

	@Override
	public String toString() {
		return "MessageBean [id=" + id + ", message=" + message + ", addtime=" + addtime + ", readtime=" + readtime
				+ ", messengerId=" + messengerId + ", fkMemberId=" + fkMemberId + "]";
	}
	

}
