package com.eeit162.FWBweb.zh.messenger.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class WebSocketSessions {

	private ConcurrentHashMap<String, String> sessionUsers = new ConcurrentHashMap<>();

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[WebSocketSessions] sessionUsers: " + sessionUsers.size();
	}

	public synchronized void registerSessionId(String user, String sessionId) {

		Assert.notNull(user, "user must not be null");
		Assert.notNull(sessionId, "sessionId must not be null");

		sessionUsers.put(sessionId, user);
	}
	
	public synchronized void registerSessionId(String user,String userId, String sessionId) {
		String userPack = user+","+userId;
		
		
		sessionUsers.put(sessionId, userPack);
	}

	public synchronized void removeSessionId(String sessionId) {

		Assert.notNull(sessionId, "sessionId must not be null");

		if (sessionUsers.containsKey(sessionId)) {
			sessionUsers.remove(sessionId);
		}

	}
	
	public ArrayList<String> getAllUsers() {
		return new ArrayList<>(sessionUsers.values());
	}
	
	public ArrayList<String> getAllSessionIds() {
		return new ArrayList<>(sessionUsers.keySet());
	}
	
	
	public ArrayList<String> getSessionIds(String user) {
		
		ArrayList<String> sessiongIds = new ArrayList<>();
		
		for (Map.Entry<String, String> entry : sessionUsers.entrySet()) {
			String userInMap = entry.getValue();
			
			if (userInMap.equals(user)) {
				sessiongIds.add(entry.getKey());
			}
			
		}
		
		return sessiongIds;
	}
	

}
