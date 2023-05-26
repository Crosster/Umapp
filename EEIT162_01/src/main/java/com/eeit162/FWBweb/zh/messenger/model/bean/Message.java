package com.eeit162.FWBweb.zh.messenger.model.bean;

import java.util.Date;

public class Message {

	private Integer userId;

	private String text;
	
	private Integer reciverId;
	
	private String reciverName;
	
	private String messageTime;
	
	private String readTime;
	

	public Message() {
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public Integer getReciverId() {
		return reciverId;
	}



	public void setReciverId(Integer reciverId) {
		this.reciverId = reciverId;
	}



	public String getReciverName() {
		return reciverName;
	}



	public void setReciverName(String reciverName) {
		this.reciverName = reciverName;
	}



	public String getMessageTime() {
		return messageTime;
	}



	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}



	public String getReadTime() {
		return readTime;
	}



	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}



	

	

	
	

	
	
}
