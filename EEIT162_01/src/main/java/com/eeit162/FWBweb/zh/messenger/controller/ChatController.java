package com.eeit162.FWBweb.zh.messenger.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eeit162.FWBweb.crs.match.model.bean.Friend;
import com.eeit162.FWBweb.crs.match.service.FriendService;
import com.eeit162.FWBweb.daka.login.Members;
import com.eeit162.FWBweb.zh.messenger.config.WebSocketSessions;
import com.eeit162.FWBweb.zh.messenger.model.bean.ChatList;
import com.eeit162.FWBweb.zh.messenger.model.bean.Message;
import com.eeit162.FWBweb.zh.messenger.model.bean.MessageBean;
import com.eeit162.FWBweb.zh.messenger.model.bean.OutputMessage;
import com.eeit162.FWBweb.zh.messenger.model.service.MessageService;
import com.eeit162.FWBweb.zh.messenger.model.service.MsgService;

@Controller
public class ChatController {

	@Autowired
	private MsgService msgService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private WebSocketSessions sessions;
	
	@Autowired
	private FriendService friendService;
	
//	@MessageMapping("/chat")
//	@SendTo("/topic/messages")
//	public OutputMessage send(final Message message) throws Exception{
//		
//		final String time = new Date().toString();
//		return new OutputMessage(time, message);
//	}
	
	
	@MessageMapping("/singlechat2")
	public MessageBean sendMessage(@RequestBody MessageBean messageBean) {
		messageBean.onCreate();
		messageService.addMessage(messageBean);
		msgService.sendMsgToUser(messageBean);
		return messageBean;
	}
	
	@PostMapping("/chat/sendimage")
	public void sendingImage(@RequestBody MessageBean messageBean) {
		messageBean.onCreate();
		messageService.addMessage(messageBean);
		msgService.sendMsgToUser(messageBean);
	}

	@PostMapping("/chat/broadcastusers")
	@ResponseBody
	public ArrayList<String> broadcastConnectionUsers(@RequestBody String text) {
		msgService.broadcast(sessions.getAllUsers());
		return sessions.getAllUsers();
	}
	
	@PostMapping("/chat/history")
	@ResponseBody
	public List<MessageBean> showHistory(@RequestBody MessageBean messageBean) {
		List<MessageBean> messageBeanList = messageService.showHistory(messageBean);
		List<MessageBean> messageBeansOutput = new ArrayList<>();
		for (MessageBean message : messageBeanList) {
			if (message.getMessengerId()==messageBean.getMessengerId()) {				
				message.setReadtime(new Date());
				messageService.updateReadTime(new Date(), message.getId());
			}
			messageBeansOutput.add(message);
		}
		
		return messageBeansOutput;
	}
	
	@PostMapping("/chat/history/latest")
	@ResponseBody
	public MessageBean showLatest(@RequestBody MessageBean messageBean) {
		return messageService.showHistoryLatest(messageBean);
	}
	
	@GetMapping("/chat/chatlist/{id}")
	@ResponseBody
	public List<ChatList> showChatListis(@PathVariable("id") int id) {
		
		List<Friend> friendList = friendService.findAllFriendList(id);
		List<ChatList> chatLists = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		for (Friend friend : friendList) {
			ChatList chatList = new ChatList();
			Integer member1Id = Integer.valueOf(friend.getMember1().getId());
			Integer member2Id = Integer.valueOf(friend.getMember2().getId());
			chatList.setMessage(new Message());
			
			chatList.setUserId(id);
			if (id!=member1Id) {
				chatList.setFriendId(member1Id);
				chatList.setFriendName(friend.getMember1().getUsername());
			}else {
				chatList.setFriendId(member2Id);
				chatList.setFriendName(friend.getMember2().getUsername());
			}
			
			MessageBean messageBean = messageService.showHistoryLatest(new MessageBean(friend.getMember1().getId(), friend.getMember2().getId()));
			
			if(messageBean != null) {
				chatList.getMessage().setUserId(messageBean.getMessengerId());
				chatList.getMessage().setReciverId(messageBean.getFkMemberId());
				chatList.getMessage().setMessageTime(simpleDateFormat.format(messageBean.getAddtime()));
				chatList.getMessage().setText(messageBean.getMessage());
				
				
				if (messageBean.getFkMemberId()==member1Id) {
					chatList.getMessage().setReciverName(friend.getMember1().getUsername());
				}
				else {
					
					chatList.getMessage().setReciverName(friend.getMember2().getUsername());
				}
			}
			

			
			chatLists.add(chatList);
			
		}
		
		
		
		return chatLists;
	}
	
	@GetMapping("/chat/history/latest/{id}")
	@ResponseBody
	public List<Message> showLatest(@PathVariable("id") Integer id) {
		List<Message> messageList = new ArrayList<Message>();
		List<Friend> friendList = friendService.findAllFriendList(id);
		for (Friend friend : friendList) {
			System.out.println("member 1:" + friend.getMember1().getUsername()+", Id:"+friend.getMember1().getId());
			System.out.println("member 2:" + friend.getMember2().getUsername()+", Id:"+friend.getMember2().getId());
			
			MessageBean messageBean = messageService.showHistoryLatest(new MessageBean(friend.getMember1().getId(),friend.getMember2().getId()));
			Message message = new Message();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			if(messageBean!=null) {
				String dte= simpleDateFormat.format(messageBean.getAddtime());
				String readTime = "";
				if (messageBean.getReadtime()!=null) {
					readTime = simpleDateFormat.format(messageBean.getReadtime());
				}
				message.setUserId(id);
				message.setMessageTime(dte);
				message.setText(messageBean.getMessage());
				message.setReciverId(messageBean.getFkMemberId());
				message.setReadTime(readTime);
			}
			
			if (messageBean.getFkMemberId() == friend.getMember1().getId()) {
				message.setReciverName(friend.getMember1().getUsername());
			}else {
				message.setReciverName(friend.getMember2().getUsername());
			}
			messageList.add(message);
		}
		
		return messageList;
	}
	
	@PostMapping("/chat/getimage")
	public MessageBean getImage(@RequestBody MessageBean messageBean) {
		messageBean.onCreate();
		messageService.addMessage(messageBean);
		msgService.sendMsgToUser(messageBean);		
		return messageBean;
	}
	
	@PostMapping("/chat/friend")
	@ResponseBody
	public List<Friend> findFriendChat() {
		
		return friendService.findInviteFriendByMemberId(2, "已確認");
	}

}
