package com.eeit162.FWBweb.zh.messenger.config;


import com.sun.security.auth.UserPrincipal;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic","/user","/subscribe");
		registry.setUserDestinationPrefix("/user");
		registry.setApplicationDestinationPrefixes("/app");
		
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chatroom");
		registry.addEndpoint("/chatroom").withSockJS();
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		
		registration.interceptors(new ChannelInterceptor() {
			
			@Override
			public Message<?> preSend(Message<?> message,MessageChannel channel) {
				StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message,StompHeaderAccessor.class);
				System.out.println(accessor);
				
				if (StompCommand.CONNECT.equals(accessor.getCommand())) {
					
//					accessor.setUser(new Principal() {
//						
//						@Override
//						public String getName() {
//							return accessor.getSessionId();
//						}
//					});
					
					accessor.setUser(new UserPrincipal(accessor.getSessionId()));

				}
				
				
				return message;
			}
		
		});
		
		
	}
	
	
	
	
}
