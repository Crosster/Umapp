package com.eeit162.FWBweb.zh.payment.model.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eeit162.FWBweb.daka.login.MembersService;
import com.eeit162.FWBweb.zh.payment.model.bean.MemberLevelOrderHistory;
import com.eeit162.FWBweb.zh.payment.model.dao.MemberLevelOrderHistoryDao;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@Service
public class MemberPayService {
	
	@Autowired
	MemberLevelOrderHistoryDao memberLevelOrderHistoryDao;
	
	@Autowired
	MembersService membersService;
	
	public String memberLevelPay(Integer price, Integer id) throws ParseException {
		
		String productName = "";
		
		if (price==150) {
			productName = "(1個月)";
		}
		else if (price==240) {
			productName = "(3個月)";
		}
		else {
			productName = "(6個月)";
		}
		
		String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0,20);
		
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String nowdate = sdFormat.format(new Date());
		AllInOne aio = new AllInOne("");
		AioCheckOutALL aioCheckOutALL = new AioCheckOutALL();
		aioCheckOutALL.setMerchantTradeNo(uuId);
		aioCheckOutALL.setMerchantTradeDate(nowdate);
		aioCheckOutALL.setTotalAmount(price.toString());
		aioCheckOutALL.setTradeDesc("交易描述：會員訂閱服務");
		aioCheckOutALL.setItemName("商品名稱：黃金會員"+productName);
		// ReturnURL付款完成會對這個網址request
		// 如果要用https要去用ngrok
		aioCheckOutALL.setReturnURL("http://不重要");
		// 付款完成會有按鈕的超連結
		String backURL = "http://localhost:8080/UM-app/member/level/payed/" + id;
		aioCheckOutALL.setClientBackURL(backURL);
		aioCheckOutALL.setNeedExtraPaidInfo("N");
		String htmlForm = aio.aioCheckOut(aioCheckOutALL, null);
		
		Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(nowdate);
		MemberLevelOrderHistory memberLevelOrderHistory = new MemberLevelOrderHistory(id,uuId,"黃金會員"+productName,date,price);
		
		memberLevelOrderHistoryDao.save(memberLevelOrderHistory);
		
		return htmlForm;
		
	}
	
	
	

}
