package com.eeit162.FWBweb.zh.messenger.model.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Keyword")
public class KeywordBean {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",columnDefinition = "integer")
	private Integer id;
	
	@Column(name = "text",columnDefinition = "nvarchar(50)")
	private String text;
	
	@Column(name = "created_at",columnDefinition = "date")
	private Date created;

	public KeywordBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	

}
