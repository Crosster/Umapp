package com.eeit162.FWBweb.zh.payment.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eeit162.FWBweb.zh.payment.model.bean.MemberLevelOrderHistory;
import com.eeit162.FWBweb.zh.payment.model.dao.MemberLevelOrderHistoryDao;

@Service
public class MemberLevelOrderHistoryService {
	
	@Autowired
	private MemberLevelOrderHistoryDao memberLevelOrderHistoryDao;
	
	
	public void createOrderList(MemberLevelOrderHistory memberLevelOrderHistory) {
		memberLevelOrderHistoryDao.save(memberLevelOrderHistory);
	}
	
	public List<MemberLevelOrderHistory> findAllByMemberId(Integer id) {
		return memberLevelOrderHistoryDao.findAllOrderListByMemberId(id);
	}

}
