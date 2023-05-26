package com.eeit162.FWBweb.hc.advertising.controller;

import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eeit162.FWBweb.hc.advertising.model.bean.Advertiser;
import com.eeit162.FWBweb.hc.advertising.model.bean.PasswordForm;
import com.eeit162.FWBweb.hc.advertising.service.AdvertiserService;
import com.eeit162.FWBweb.hc.advertising.service.ImageEncoder;

@Controller
@MultipartConfig
@RequestMapping("/advertisers")
public class AdvertiserController {

	@Autowired
	private AdvertiserService advertiserService;

	// 渲染註冊頁面
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("advertiser", new Advertiser());
		return "advertising/register";
	}

	// 渲染驗證頁面
	@GetMapping("/verify")
	public String showVerificationPage() {
		return "advertising/verification";
	}

	// 渲染登入頁面
	@GetMapping("/login")
	public String showLoginPage(Model model) {
		model.addAttribute("advertiser", new Advertiser());
		return "advertising/login";
	}

	// 渲染個人資訊頁面
	@GetMapping("/profile")
	public String showProfilePage(Model model, HttpSession session, HttpServletResponse response) throws IOException {
		// 禁止瀏覽器緩存頁面
		response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader(HttpHeaders.PRAGMA, "no-cache"); // HTTP 1.0
		response.setHeader(HttpHeaders.EXPIRES, "0"); // Proxies

		Integer advertiserId = (Integer) session.getAttribute("loggedInAdvertiser");
		if (advertiserId == null) {
			return "redirect:/advertisers/login";
		}

		Advertiser advertiser = advertiserService.findById(advertiserId);
		model.addAttribute("advertiser", advertiser);

		ImageEncoder imageEncoder = new ImageEncoder();
		String logo = imageEncoder.encodeImage(advertiser.getLogo());
		model.addAttribute("logo", logo);
		return "advertising/profile";
	}

	// 渲染更改密碼頁面
	@GetMapping("/change_password")
	public String showChangePasswordPage(Model model, HttpSession session, HttpServletResponse response) {
		// 禁止瀏覽器緩存頁面
		response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader(HttpHeaders.PRAGMA, "no-cache"); // HTTP 1.0
		response.setHeader(HttpHeaders.EXPIRES, "0"); // Proxies

		Integer advertiserId = (Integer) session.getAttribute("loggedInAdvertiser");
		if (advertiserId == null) {
			return "redirect:/advertisers/login";
		}

		model.addAttribute("passwordForm", new PasswordForm());
		return "advertising/change_password";
	}

	// 渲染要求密碼重置頁面
	@GetMapping("/request_password_reset")
	public String showRequestPasswordResetPage() {
		return "advertising/request_password_reset";
	}

	// 渲染密碼重置頁面
	@GetMapping("reset_password")
	public String showResetPasswordPage() {
		return "advertising/reset_password";
	}

	// 渲染更新頁面
	@GetMapping("/update")
	public String showUpdateForm(Model model, HttpSession session) throws IOException {
		// 從session中獲取已登入的廣告商ID
		Integer advertiserId = (Integer) session.getAttribute("loggedInAdvertiser");

		// 通過ID從資料庫中獲取廣告商資訊
		Advertiser advertiser = advertiserService.findById(advertiserId);

		// 將廣告商物件添加到模型中，使其可以在視圖中訪問
		model.addAttribute("advertiser", advertiser);

		ImageEncoder imageEncoder = new ImageEncoder();
		String logo = imageEncoder.encodeImage(advertiser.getLogo());
		model.addAttribute("logo", logo);

		// 返回視圖的名稱
		return "advertising/update";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("advertiser") Advertiser advertiser, BindingResult result, Model model,
			HttpSession session) {

		if (result.hasErrors()) {
			return "advertising/register";
		}

		try {
			advertiserService.register(advertiser, session);
			return "redirect:/advertisers/verify";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "advertising/register";
		}
	}

	@PostMapping("/verify")
	public String verify(@RequestParam("code") String code, HttpSession session, Model model) {

		try {
			advertiserService.verify(code, session);
			return "redirect:/advertisers/login";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "advertising/verification"; // 如果驗證碼不正確，返回驗證碼輸入頁面
		}
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("advertiser") Advertiser advertiser, HttpSession session, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			return "advertising/login";
		}

		try {
			Advertiser loggedInAdvertiser = advertiserService.login(advertiser);
			// 將登入者的ID存在session中
			session.setAttribute("loggedInAdvertiser", loggedInAdvertiser.getAdvertiserId());
			return "redirect:/advertisers/profile";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "advertising/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 清除session
		session.invalidate();

		return "redirect:/advertisers/login";
	}

	@PostMapping("/change_password")
	public String changePassword(@ModelAttribute("passwordForm") PasswordForm passwordForm,
			@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
			@RequestParam("confirmNewPassword") String confirmNewPassword, Model model, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			return "advertising/change_password";
		}

		try {
			Integer advertiserId = (Integer) session.getAttribute("loggedInAdvertiser");
			advertiserService.changePassword(advertiserId, oldPassword, newPassword, confirmNewPassword);
			model.addAttribute("successMessage", "密碼修改成功");
			return "advertising/change_password"; // 留在當前頁面並顯示成功訊息
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "advertising/change_password"; // 留在當前頁面並顯示錯誤訊息
		}
	}

	@PostMapping("/request_password_reset")
	public String requestPasswordReset(@RequestParam("email") String email, Model model) {
		try {
			advertiserService.requestPasswordReset(email);
			return "advertising/password_reset_requested"; // 顯示"密碼重置請求已發送"的頁面
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "advertising/request_password_reset";
		}
	}

	@PostMapping("/reset_password")
	public String resetPassword(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword,
			@RequestParam("confirmNewPassword") String confirmNewPassword, Model model) {
		try {
			advertiserService.resetPassword(token, newPassword, confirmNewPassword);
			return "advertising/password_reset_success"; // 顯示"密碼已成功重置"的頁面
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "advertising/reset_password";
		}
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("formAdvertiser") Advertiser formAdvertiser,
			@RequestParam("logoFile") MultipartFile logoFile, BindingResult result, HttpSession session, Model model) {

		if (result.hasErrors()) {
			return "advertising/update";
		}

		try {
			Integer advertiserId = (Integer) session.getAttribute("loggedInAdvertiser");
			Advertiser existingAdvertiser = advertiserService.findById(advertiserId);

//			model.addAttribute("advertiser", existingAdvertiser);

			// 將表單提交的數據複製到現有的廣告商對象上，除了logo
			BeanUtils.copyProperties(formAdvertiser, existingAdvertiser, "logo");

			// 如果有上傳logo，插入新的logo
			if (!logoFile.isEmpty()) {
				byte[] logoBytes = logoFile.getBytes();
				existingAdvertiser.setLogo(logoBytes);
			}

			// 更新廣告商資料
			advertiserService.update(existingAdvertiser);

			// 返回到個人資料頁面
			return "redirect:/advertisers/profile";

		} catch (Exception e) {
			// 如果有任何錯誤，返回到更新頁面並顯示錯誤訊息
			model.addAttribute("errorMessage", e.getMessage());
			return "advertising/update";
		}

	}

	@GetMapping("/delete")
	public String delete(HttpSession session) {
		Integer advertiserId = (Integer) session.getAttribute("loggedInAdvertiser");
		advertiserService.delete(advertiserId);
		session.invalidate();
		return "redirect:/advertisers/login";
	}

}
