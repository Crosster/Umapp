package com.eeit162.FWBweb.zh.messenger.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import com.eeit162.FWBweb.zh.messenger.config.WebSocketSessions;
import com.eeit162.FWBweb.zh.messenger.controller.ChatController;
import com.eeit162.FWBweb.zh.messenger.model.service.MsgService;
import com.eeit162.FWBweb.zh.messenger.util.LogHelper;

@Component
public class STOMPConnectEventListener implements ApplicationListener<SessionConnectedEvent> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WebSocketSessions sessions;
	
	
	@Override
	public void onApplicationEvent(SessionConnectedEvent event) {
		System.out.println("connect listener");
		MessageHeaderAccessor messageHeaderAccessor = MessageHeaderAccessor.getAccessor(event.getMessage().getHeaders(),MessageHeaderAccessor.class);
		StompHeaderAccessor stompHeaderAccessor = MessageHeaderAccessor.getAccessor((Message<?>) messageHeaderAccessor.getHeader("simpConnectMessage"),StompHeaderAccessor.class);	
		String user = stompHeaderAccessor.getNativeHeader("user").get(0);
		String userId = stompHeaderAccessor.getNativeHeader("userId").get(0);
		System.out.println("userID: "+userId);
		String sessionId = stompHeaderAccessor.getSessionId();
		sessions.registerSessionId(user,userId, sessionId);
//		sessions.registerSessionId(user, sessionId);
		LogHelper.logInfo(logger, "user login, user:{}, sessionId:{}", user, sessionId);
        LogHelper.logInfo(logger, sessions.toString());
	}

	
	
}
