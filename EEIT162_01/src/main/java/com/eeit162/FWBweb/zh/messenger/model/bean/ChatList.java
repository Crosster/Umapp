package com.eeit162.FWBweb.zh.messenger.model.bean;

public class ChatList {
	
	private Integer userId;
	
	private Integer friendId;
	
	private String friendName;
	
	private Message message;
		

	public ChatList() {
	}
	
	


	public ChatList(Integer userId, Integer friendId, Message message) {
		this.userId = userId;
		this.friendId = friendId;
		this.message = message;
	}




	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getFriendId() {
		return friendId;
	}


	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}


	public Message getMessage() {
		return message;
	}


	public void setMessage(Message message) {
		this.message = message;
	}




	public String getFriendName() {
		return friendName;
	}




	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	
	

}
