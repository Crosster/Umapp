package com.eeit162.FWBweb.crs.match.credit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EcPayController {
	@Autowired
	EcPayService ecService;

	@GetMapping("/ecpayPage")
	public String getEcPage(Model model) {
		// 得到訂單的Html form，然後依靠EL表達式渲染出來(${form})
		String aioCheckOutALLForm = ecService.ecpayCheckout();
		model.addAttribute("form", aioCheckOutALLForm);
		return "match/CreditPage";
	}
}
