package com.eeit162.FWBweb.zh.messenger.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eeit162.FWBweb.daka.login.Members;

@Controller
public class ChatMainController {


//	@GetMapping(value = "/chatmaincontroller")
//	public String getHome(Model model) {
//		
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG,Locale.getDefault());
//		
//		model.addAttribute("serverTime", dateFormat.format(new Date()));
//			
//		return "messenger/chathome";
//	}
	
	@GetMapping("/chat")
	public String getChat(Model model,HttpSession session) {
		Members sessionMember = (Members) session.getAttribute("member");
		
		if (sessionMember==null) {
			return "redirect:/members/login";
		}
		
		return "messenger/chat";
	}
	
}
