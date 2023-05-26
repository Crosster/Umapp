package com.eeit162.FWBweb.crs.match.credit;

import java.util.UUID;

import org.springframework.stereotype.Service;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@Service
public class EcPayService {
//		AioCheckOutALL有5種支付方法
//	    payment_conf.xml已經被我修改成只有信用卡付款
//	   <IgnorePayment>
//	   <!-- <Method>Credit</Method>-->
//	        <Method>WebATM</Method>
//	        <Method>ATM</Method>
//	        <Method>CVS</Method>
//	        <Method>BARCODE</Method>
//	    </IgnorePayment>
	
	public String ecpayCheckout() {
		// new一個AllInOne物件
		// 然後開始塞KEY+VALUE
		String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);

		AllInOne all = new AllInOne("");

		AioCheckOutALL obj = new AioCheckOutALL();
		obj.setMerchantTradeNo(uuId);
		obj.setMerchantTradeDate("2023/05/16 08:05:23");
		obj.setTotalAmount("500");
		obj.setTradeDesc("交易描述：會員訂閱服務");
		obj.setItemName("商品名稱：黃金會員(1個月)");
		// ReturnURL付款完成會對這個網址request
		// 如果要用https要去用ngrok
		obj.setReturnURL("http://不重要");
		// 付款完成會有按鈕的超連結
		obj.setClientBackURL("http://localhost:8080/UM-app/");
		obj.setNeedExtraPaidInfo("N");

		// 經由aioCheckOut處理，產生訂單Html form
		String htmlForm = all.aioCheckOut(obj, null);

		return htmlForm;
	}
}
