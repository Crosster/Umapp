package com.eeit162.FWBweb.zh.messenger.model.service;


import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eeit162.FWBweb.zh.messenger.model.bean.MessageBean;
import com.eeit162.FWBweb.zh.messenger.model.dao.MessageDao;

@Service
public class MessageService {
	
	@Autowired
	private MessageDao messageDao;
	
	public void addMessage(MessageBean messageBeam) {
		messageDao.save(messageBeam);
	}
	
	public List<MessageBean> showHistory(MessageBean messageBean) {
		
		return messageDao.findMessageHistoryBetweenUser(messageBean.getMessengerId(), messageBean.getFkMemberId());
	}
	
	public MessageBean showHistoryLatest(MessageBean messageBean) {
		return messageDao.findMessageHistoryBetweenUserLatest(messageBean.getMessengerId(), messageBean.getFkMemberId());
	}
	
	@Transactional
	public void updateReadTime(Date readTime,Integer id) {
		messageDao.updateMessageById(readTime, id);
	}

}
