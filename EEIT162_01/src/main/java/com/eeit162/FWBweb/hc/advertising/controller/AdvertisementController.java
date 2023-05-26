package com.eeit162.FWBweb.hc.advertising.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eeit162.FWBweb.hc.advertising.model.bean.Advertisement;
import com.eeit162.FWBweb.hc.advertising.model.bean.AdvertisementRender;
import com.eeit162.FWBweb.hc.advertising.model.bean.Plan;
import com.eeit162.FWBweb.hc.advertising.service.AdvertisementService;
import com.eeit162.FWBweb.hc.advertising.service.ImageEncoder;
import com.eeit162.FWBweb.hc.advertising.service.PlanService;
import com.eeit162.FWBweb.hc.advertising.service.TimePeriods;

@Controller
@MultipartConfig
@RequestMapping("/advertisements")
public class AdvertisementController {

	@Autowired
	private AdvertisementService advertisementService;

	@Autowired
	private PlanService planService;

	// 渲染創建廣告表單
	@GetMapping("/create")
	public String showAddAdvertisementForm(Model model) {
		model.addAttribute("advertisement", new Advertisement());

		List<Plan> plans = planService.findAll();
		model.addAttribute("plans", plans);

		return "advertising/add_advertisement";
	}

	@GetMapping
	public String getAdvertisementsByAdvertiserId(HttpSession session, Model model) throws IOException {
		Integer advertiserId = (Integer) session.getAttribute("loggedInAdvertiser");
		List<Advertisement> advertisements = advertisementService.findByAdvertiserId(advertiserId);
		model.addAttribute("advertisements", advertisements);

		ImageEncoder imageEncoder = new ImageEncoder();
		Map<Integer, String> pictures = new HashMap<>();

		for (Advertisement advertisement : advertisements) {
			String picture = imageEncoder.encodeImage(advertisement.getPicture());
			pictures.put(advertisement.getAdvertisementId(), picture);
		}

		model.addAttribute("pictures", pictures);

		return "advertising/advertisements";
	}

	@PostMapping("/create")
	public String createAdvertisement(@ModelAttribute Advertisement advertisement,
			@RequestParam("pictureFile") MultipartFile pictureFile, @RequestParam("planId") Integer planId,
			@RequestParam("cost") Integer cost, BindingResult result, HttpSession session, Model model) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "advertising/add_advertisement";
		}

		try {
			byte[] pictureBytes = pictureFile.getBytes();
			advertisement.setPicture(pictureBytes);

			Integer advertiserId = (Integer) session.getAttribute("loggedInAdvertiser");

			advertisementService.create(advertisement, advertiserId, planId);

			String aioCheckOutALLForm = advertisementService.ecpayCheckout(cost);
			model.addAttribute("form", aioCheckOutALLForm);

			return "advertising/checkout";
		} catch (DataAccessException e) {
			model.addAttribute("errorMessage", "新增廣告失敗");
			return "advertising/add_advertisement";
		} catch (IOException e) {
			model.addAttribute("errorMessage", "讀取圖片失敗");
			return "advertising/add_advertisement";
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@GetMapping("/place")
	@ResponseBody
	public AdvertisementRender getAllAdvertisements(Model model) throws IOException {

		AdvertisementRender advertisementRender = new AdvertisementRender();
		List<Advertisement> allAdvertisements = advertisementService.findAll();
		List<Advertisement> activeAdvertisements = new ArrayList<>();

		// 只選出上架中且在投放時段的廣告
		LocalTime now = LocalTime.now();
		for (Advertisement ad : allAdvertisements) {
			if (ad.getStatus() == 1) {
				TimePeriods periods = new TimePeriods(ad.getPlan().getPeriod());
				if (periods.includes(now)) {
					activeAdvertisements.add(ad);
				}
			}
		}

		if (activeAdvertisements.isEmpty()) {
			advertisementRender.setErrors("目前沒有上架的廣告");
//			model.addAttribute("errorMessage", "目前沒有上架的廣告");
		} else {
			// 隨機選一個廣告
			Random random = new Random();
			Advertisement randomAd = activeAdvertisements.get(random.nextInt(activeAdvertisements.size()));
			System.out.println("廣告時段: " + randomAd.getPlan().getPeriod());
			// 將byte[]轉換為Base64字串
			ImageEncoder imageEncoder = new ImageEncoder();
			String picture = imageEncoder.encodeImage(randomAd.getPicture());

//			model.addAttribute("advertisement", randomAd);
//			model.addAttribute("picture", picture);
			advertisementRender.setImg(picture);
			advertisementRender.setUrl(randomAd.getUrl());

		}

		return advertisementRender;
	}
}
