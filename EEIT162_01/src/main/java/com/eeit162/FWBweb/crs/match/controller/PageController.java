package com.eeit162.FWBweb.crs.match.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eeit162.FWBweb.daka.login.Members;

@Controller
@RequestMapping("/match")
public class PageController {
	@GetMapping("/mr")
	public String matchResultPage() {
		return "match/matchResult";
	}

	@GetMapping("/fl")
	public String friendListPage(HttpSession session) {
		Members sessionMember = (Members) session.getAttribute("member");
		if (sessionMember==null) {
			return "redirect:/members/login";
		}
		return "match/friendList";
	}

	@GetMapping("/sb")
	public String sideBarPage(HttpSession session) {
		Members sessionMember = (Members) session.getAttribute("member");
		if (sessionMember==null) {
			return "redirect:/members/login";
		}
		return "match/matchSideBar";
	}

	@GetMapping("/multiMatch")
	public String multiMatchPage(HttpSession session) {
		Members sessionMember = (Members) session.getAttribute("member");
		if (sessionMember==null) {
			return "redirect:/members/login";
		}
		return "match/multiMatchPage";
	}

	@GetMapping("/newmr")
	public String matchResultNewPage(HttpSession session) {
		Members sessionMember = (Members) session.getAttribute("member");
		if (sessionMember==null) {
			return "redirect:/members/login";
		}
		return "match/matchResultNew";
	}

	@GetMapping("/multiSearchIfBuy")
	public String multiSearchIfBuy(HttpSession session) {
		Members m = (Members) session.getAttribute("member");
		System.out.println(m.getLevel());
		if (m != null && "2".equals(m.getLevel())) {
			return "/match/multiMatchPage";
		}
		return "payment/memberLevelPayment";
	}
}
