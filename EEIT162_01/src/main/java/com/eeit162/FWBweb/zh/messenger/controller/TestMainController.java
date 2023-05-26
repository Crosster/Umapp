package com.eeit162.FWBweb.zh.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eeit162.FWBweb.zh.messenger.model.bean.MessageBean;
import com.eeit162.FWBweb.zh.messenger.model.service.MessageService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class TestMainController {
	
	@GetMapping("/index/newpage")
	public String getInNewIndex() {
		return "page/newindex";
	}
	
	
}
