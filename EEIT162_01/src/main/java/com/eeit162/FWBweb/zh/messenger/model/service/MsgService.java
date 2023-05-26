package com.eeit162.FWBweb.zh.messenger.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.eeit162.FWBweb.zh.messenger.config.WebSocketSessions;
import com.eeit162.FWBweb.zh.messenger.model.bean.MessageBean;

@Service
public class MsgService {
	
	public static final String BROADCAST_DESTINATION = "/topic/messages";
	public static final String USER_TOPIC = "/subscribe";
	
	@Autowired
	private WebSocketSessions sessions;
	
	@Autowired
	private SimpMessagingTemplate template;
	
	public void sendMsgTo(String destination,Object msg) {
		template.convertAndSend(destination,msg);
	}
	
	
	public void sendMsgToSession(String sessionId,Object msg) {
		template.convertAndSendToUser(sessionId, USER_TOPIC, msg);
	}
	
	public void sendMsgToUser(String user, Object msg) {
		template.convertAndSendToUser(user, USER_TOPIC, msg);
	}
	
	public void sendMsgToUser(MessageBean messageBean) {
		template.convertAndSendToUser(messageBean.getFkMemberId().toString(), USER_TOPIC, messageBean);
		template.convertAndSendToUser(messageBean.getMessengerId().toString(), USER_TOPIC, messageBean);
		
	}
	
	public void broadcast(Object msg) {
		System.out.println(msg);
		sendMsgTo(BROADCAST_DESTINATION, msg);
	}
	
}
