package com.eeit162.FWBweb.hc.advertising.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.eeit162.FWBweb.hc.advertising.model.bean.Advertisement;
import com.eeit162.FWBweb.hc.advertising.model.bean.Advertiser;
import com.eeit162.FWBweb.hc.advertising.model.bean.Plan;
import com.eeit162.FWBweb.hc.advertising.model.dao.AdvertisementDAO;
import com.eeit162.FWBweb.hc.advertising.model.dao.AdvertiserDAO;
import com.eeit162.FWBweb.hc.advertising.model.dao.PlanDAO;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@Service
public class AdvertisementService {

	@Autowired
	private AdvertisementDAO advertisementDAO;

	@Autowired
	private AdvertiserDAO advertiserDAO;

	@Autowired
	private PlanDAO planDAO;

	// 獲取所有的廣告
	public List<Advertisement> findAll() {
		return advertisementDAO.findAll();
	}

	// 根據廣告商ID獲取廣告
	public List<Advertisement> findByAdvertiserId(Integer advertiserId) {
		return advertisementDAO.findByAdvertiserAdvertiserId(advertiserId);
	}

	// 新增廣告
	public void create(Advertisement advertisement, Integer advertiserId, Integer planId) {

		Advertisement newAdvertisement = new Advertisement();
		newAdvertisement.setTitle(advertisement.getTitle());
		newAdvertisement.setPicture(advertisement.getPicture());
		newAdvertisement.setUrl(advertisement.getUrl());
		newAdvertisement.setStartDate(advertisement.getStartDate());
		newAdvertisement.setEndDate(advertisement.getEndDate());

		Date now = new Date();
		if (advertisement.getStartDate().before(now) && advertisement.getEndDate().after(now)) {
			newAdvertisement.setStatus(1); // 上架
		} else {
			newAdvertisement.setStatus(2); // 下架
		}

		newAdvertisement.setCreatedTime(now);

		Advertiser advertiser = advertiserDAO.findById(advertiserId).get();
		Plan plan = planDAO.findById(planId).get();
		newAdvertisement.setAdvertiser(advertiser);
		newAdvertisement.setPlan(plan);

		advertisementDAO.save(newAdvertisement);
	}

	// 每日定時更新廣告狀態
	@Scheduled(cron = "0 0 0 * * ?") // 每日凌晨0點執行
	public void updateAdvertisementStatus() {
		List<Advertisement> advertisements = advertisementDAO.findAll();
		Date now = new Date();

		for (Advertisement advertisement : advertisements) {
			if (advertisement.getStartDate().before(now) && advertisement.getEndDate().after(now)) {
				advertisement.setStatus(1); // 上架
			} else {
				advertisement.setStatus(2); // 下架
			}
			advertisementDAO.save(advertisement);
		}
	}

	// 串接綠界
	public String ecpayCheckout(Integer cost) {
		// new一個AllInOne物件
		// 然後開始塞KEY+VALUE
		String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String nowdate = sdFormat.format(new Date());

		AllInOne all = new AllInOne("");

		AioCheckOutALL obj = new AioCheckOutALL();
		obj.setMerchantTradeNo(uuId);
		obj.setMerchantTradeDate(nowdate);
		obj.setTotalAmount(cost.toString());
		obj.setTradeDesc("交易描述：廣告投放服務");
		obj.setItemName("商品名稱：廣告");
		// ReturnURL付款完成會對這個網址request
		// 如果要用https要去用ngrok
		obj.setReturnURL("http://不重要");
		// 付款完成會有按鈕的超連結
		obj.setClientBackURL("http://localhost:8080/UM-app/advertisements");
		obj.setNeedExtraPaidInfo("N");

		// 經由aioCheckOut處理，產生訂單Html form
		String htmlForm = all.aioCheckOut(obj, null);

		return htmlForm;
	}

}
