package com.eeit162.FWBweb.zh.payment.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eeit162.FWBweb.daka.login.Members;
import com.eeit162.FWBweb.daka.login.MembersService;
import com.eeit162.FWBweb.zh.payment.model.bean.MemberLevelOrderHistory;
import com.eeit162.FWBweb.zh.payment.model.service.MemberLevelOrderHistoryService;
import com.eeit162.FWBweb.zh.payment.model.service.MemberPayService;

@Controller
public class MemberPayController {

	@Autowired
	MemberPayService ecPayService;

	@Autowired
	MembersService membersService;

	@Autowired
	MemberLevelOrderHistoryService memberLevelOrderHistoryService;

	@GetMapping("/member/level/pay")
	public String memberLevelPayPage(HttpSession session) {
		Members sessionMember = (Members) session.getAttribute("member");

		if (sessionMember == null) {
			return "redirect:/members/login";
		}
		return "payment/memberLevelPayment";
	}

	@GetMapping("/member/level/orderlist")
	public String memberLevelOrderList(HttpSession session) {
		Members sessionMember = (Members) session.getAttribute("member");

		if (sessionMember == null) {
			return "redirect:/members/login";
		}
		return "payment/memberOrderList";
	}

	@GetMapping("/member/level/findall/{id}")
	@ResponseBody
	public List<MemberLevelOrderHistory> findAllListById(@PathVariable("id") Integer id) {
		return memberLevelOrderHistoryService.findAllByMemberId(id);
	}

	@GetMapping("/member/level/pay/{id}/{price}")
	public String goEcPayPage(@PathVariable("price") Integer price, @PathVariable("id") Integer id, Model model)
			throws ParseException {
		String payMentHtmlForm = ecPayService.memberLevelPay(price, id);
		model.addAttribute("payForm", payMentHtmlForm);
		return "payment/paying";
	}

	@GetMapping("/member/level/payed/{id}")
	public String updateMemberLevel(@PathVariable("id") Integer id, HttpSession session) {
		membersService.swichLevel(id, "2");
		Members members = (Members) session.getAttribute("member");
		members.setLevel("2");
		session.setAttribute("member", members);
		return "page/index";
	}

}
