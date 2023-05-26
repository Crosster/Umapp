package com.eeit162.FWBweb.zh.messenger.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.eeit162.FWBweb.zh.messenger.config.WebSocketSessions;
import com.eeit162.FWBweb.zh.messenger.util.LogHelper;

@Component
public class STOMPDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private WebSocketSessions sessions;

	@Override
	public void onApplicationEvent(SessionDisconnectEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		System.out.println(accessor);
		String sessionId = accessor.getSessionId();
		
		sessions.removeSessionId(sessionId);
		LogHelper.logInfo(logger, "user logout, sessionId:{}", sessionId);
		LogHelper.logInfo(logger, sessions.toString());

	}

}
