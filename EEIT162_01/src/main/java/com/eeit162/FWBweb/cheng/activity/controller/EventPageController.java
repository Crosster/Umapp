package com.eeit162.FWBweb.cheng.activity.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eeit162.FWBweb.cheng.activity.bean.ActivityBean;
import com.eeit162.FWBweb.cheng.activity.service.ActivityService;

@Controller
public class EventPageController {
	
	@Autowired
	private ActivityService activityService;

	@GetMapping("/activity/eventpage")
	public String viewEventPage(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "keyword",defaultValue = "")String keyword, Model model) {
		 PageRequest pageRequest = PageRequest.of(page-1, 6);
//		 Page<ActivityBean> pageObject = activityService.findAllActivity(pageRequest);
		 Page<ActivityBean> pageObject = activityService.findActivityByKeyword(keyword, pageRequest);
		 System.out.println(pageObject.getContent().size());
		 boolean emtpyResults = false;
		 if (pageObject.getContent().size() <= 0) {
			 pageObject = activityService.findAllActivity(pageRequest);
			 emtpyResults = true;
		 }
		 
		List<ActivityBean> activityList = activityService.findAllActivity();
		model.addAttribute("activityList", activityList);
		model.addAttribute("pageObject", pageObject);
		model.addAttribute("emtpyResults", emtpyResults);
		
		return "activity/activityShowPage";
	}
	
	
	@GetMapping("/activity/backendManage")
	public String backendManage(Model model) {
		List<ActivityBean> activityList = activityService.findAllActivity();
		model.addAttribute("activityList", activityList);
		
		
		return "activity/backendActivity";
	}
	
	
	@GetMapping("/activity/updateActivityPage/{activityId}")
	public String updateActivityPage(@PathVariable("activityId")Integer activityId,Model model) {
		ActivityBean ActivityBean = activityService.findActivityById(activityId);
		
		String startDate = ActivityBean.getActivityStartUpTime().toString().substring(0, 10);
		String endDate = ActivityBean.getActivityEndTime().toString().substring(0, 10);
		
		
		model.addAttribute("ActivityBean", ActivityBean);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		
		return "activity/updateActivity";
	}
	
	
	@PostMapping("/activity/updateActivity")
	public String updateActivity(@RequestParam(value = "activityId")Integer activityId,@RequestParam("activityStartUpTime")String activityStartUpTime,@RequestParam("activityEndTime")String activityEndTime,
			@RequestParam("activityName")String activityName,@RequestParam("activityContent")String activityContent,@RequestParam("activityFee")Integer activityFee,
			@RequestParam("activityLink")String activityLink,@RequestParam("activityAd")MultipartFile activityAd,@RequestParam("activityType")String activityType
			) {
		try {
			ActivityBean activityBean = new ActivityBean();
			activityBean.setActivityId(activityId);
			Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(activityStartUpTime);
			Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(activityEndTime);
			activityBean.setActivityStartUpTime(startDate);
			activityBean.setActivityEndTime(endDate);
			activityBean.setActivityName(activityName);
			activityBean.setActivityContent(activityContent);
			activityBean.setActivityFee(activityFee);
			activityBean.setActivityLink(activityLink);
			activityBean.setActivityType(activityType);
			activityBean.setActivityAd(activityAd.getBytes());
			activityService.updateActivity(activityBean);
			
			
			return "redirect:/activity/backendManage";
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/activity/backendManage";
	}
	
	@GetMapping("/activity/delete/{activityId}")
	public String delete(@PathVariable("activityId")Integer activityId) {
		activityService.deleteById(activityId);
		
		
		return "redirect:/activity/backendManage";
	}
	
	@GetMapping("/activity/insertPage")
	public String insertPage() {
		
		
		return "activity/insertActivityPage";
	}
	
	@PostMapping("/activity/insertActivity")
	public String insertActivity(@RequestParam("activityStartUpTime")String activityStartUpTime,@RequestParam("activityEndTime")String activityEndTime,
			@RequestParam("activityName")String activityName,@RequestParam("activityContent")String activityContent,@RequestParam("activityFee")Integer activityFee,
			@RequestParam("activityLink")String activityLink,@RequestParam("activityAd")MultipartFile activityAd,@RequestParam("activityType")String activityType
			) {
		try {
			ActivityBean activityBean = new ActivityBean();
			Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(activityStartUpTime);
			Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(activityEndTime);
			activityBean.setActivityStartUpTime(startDate);
			activityBean.setActivityEndTime(endDate);
			activityBean.setActivityName(activityName);
			activityBean.setActivityContent(activityContent);
			activityBean.setActivityFee(activityFee);
			activityBean.setActivityLink(activityLink);
			activityBean.setActivityType(activityType);
			activityBean.setActivityAd(activityAd.getBytes());
			
			activityService.insertActivity(activityBean);
			
			return "redirect:/activity/backendManage";
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/activity/backendManage";
	}
	
	@GetMapping("/activity/loginBackendPage")
	public String loginBackendPage() {
		return "activity/loginBackend";
	}
	
	@PostMapping("/activity/checkLoginBackend")
	public String checkLoginBackend(@RequestParam("account")String account,@RequestParam("password")String password,Model model) {
		boolean isAdmin = false;
		if("GibsonCheng".equals(account) && "12345".equals(password)) {
			isAdmin = true;
		}
		if(isAdmin) {
			return "redirect:/activity/backendManage";
		}
		
		
		model.addAttribute("isAdmin", false);
		return "activity/loginBackend";
		
	}
	
}
