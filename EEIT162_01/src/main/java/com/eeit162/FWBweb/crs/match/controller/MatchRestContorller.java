package com.eeit162.FWBweb.crs.match.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eeit162.FWBweb.crs.match.service.MemberService;
import com.eeit162.FWBweb.daka.login.Members;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/api")
public class MatchRestContorller {
	@Autowired
	private MemberService mService;

//	@GetMapping("/GetMatchResult")
//	public ResponseEntity<Page<Members>> getMembers(@RequestParam(name = "page", defaultValue = "1") Integer pageNumber,
//			@RequestParam(name = "count", defaultValue = "6") Integer count) {
//
//		Page<Members> members = mService.memberPage(pageNumber, count);
//		for (Members m : members.getContent()) {
//			m.getMemberDetail();
//			System.out.println(m.getMemberDetail());
//		}
//		System.out.println(members.getContent());
//
//		return ResponseEntity.ok(members);
//	}

	@PostMapping("/multiSearch")
	public List<Members> multiSearch(@RequestBody String json) throws ParseException {
		System.out.println(json);
		Gson gson = new Gson();
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		System.out.println(jsonObj);
		return mService.dynamicFind(jsonObj);
	}

	@PostMapping("/multiSearchPage")
	public PagedListHolder<Members> multiSearchPage(@RequestBody String json) throws ParseException {
		System.out.println("multiSearchPage start");
		// 預設值
		Integer page = 1;
		Integer count = 10;

		Gson gson = new Gson();
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		System.out.println(jsonObj);
		List<Members> mList = mService.dynamicFind(jsonObj);

		PagedListHolder<Members> plh = new PagedListHolder<Members>(mList);
		if (jsonObj.get("page") != null && jsonObj.get("page").getAsInt() != 0) {
			page = jsonObj.get("page").getAsInt();
		}
		if (jsonObj.get("count") != null && jsonObj.get("count").getAsInt() != 0) {
			count = jsonObj.get("count").getAsInt();
		}
		plh.setPage(page - 1);
		plh.setPageSize(count);
		System.out.println("multiSearchPage end");
		return plh;
	}

}
