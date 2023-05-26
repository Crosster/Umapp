package com.eeit162.FWBweb.daka.login;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eeit162.FWBweb.daka.member.model.Service.PhotosService;
import com.eeit162.FWBweb.daka.member.model.beam.Photos;
import com.eeit162.FWBweb.hc.advertising.model.bean.Advertisement;
import com.eeit162.FWBweb.hc.advertising.service.AdvertisementService;
import com.eeit162.FWBweb.hc.advertising.service.ImageEncoder;
import com.eeit162.FWBweb.hc.advertising.service.TimePeriods;

@Controller
@MultipartConfig
public class MembersController {
	@Autowired
	private MembersService membersService;

	@Autowired
	private MailService mailservice;
	@Autowired
	private PhotosService photosService;

	@Autowired
	private AdvertisementService advertisementService;

	// 跳頁 跳到登入畫面
	@GetMapping("/members/login")
	public String loginpage() {

		return "login/loginpag";
	}
	// 跳頁 跳到後台畫面
		@GetMapping("/members/adm")
		public String loginadm() {

			return "login/admin";
		}

	// 跳頁 跳到首頁
	@GetMapping("/")
	public String indexPage() {
		return "page/newindex";
	}

	// 登入
	@PostMapping("/members/login")
	public String login(HttpServletRequest request, Model model, HttpSession session) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		

		try {
			Members member = membersService.login(email, password);

			if (member != null) {

				// 將成功登入者的資料存儲到session中
				session.setAttribute("member", member);
				session.setAttribute("logingetId", true);

				// 如果用戶是管理員(level=4)，則返回 true，否則返回 false
				if (member.getLevel().equals("4")) {
					return "redirect:/members/adm";
				}

			} else {
				session.setAttribute("logingetId", false);
			}

			return "page/newindex";
		} catch (FailedLoginException e) {
			session.setAttribute("error", "true");
			return "login/loginpag";
		}

	}

	// 跳頁 進行登出
	@GetMapping("/members/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";

	}

	// 跳頁 進行註冊會員
	@GetMapping("/members/addmember")
	public String addmember(Model model) {
		Members members = new Members();
		model.addAttribute("logingetId", members);
		return "login/addmember";
	}

	// 進行註冊會員+信箱驗證
	@PostMapping("/members/addmember")
	public String addmember(@ModelAttribute Members mem, @RequestParam(required = false) MultipartFile photoContent,
			HttpSession session, Model model) throws IOException, javax.mail.MessagingException {

		mem.setLevel("1");

		if (membersService.isAccountExist(mem.getEmail())) {
			model.addAttribute("errorMessage", "該信箱已被註冊，請輸入另一個信箱");
			return "login/addmember";
		}

		if (photoContent != null && !photoContent.isEmpty()) {
			try (BufferedInputStream bis = new BufferedInputStream(photoContent.getInputStream())) {
				mem.setPhoto(bis.readAllBytes());
			}
		} else {
			try (InputStream is = getClass().getResourceAsStream("/user_A_01.png");
					BufferedInputStream bis = new BufferedInputStream(is)) {
				mem.setPhoto(bis.readAllBytes());
			}
		}

		String code = membersService.generateVerificationCode();

		mem.setStatus("未驗證");
		mem.setVerificationcode(code);
		membersService.insertMember(mem);

		String verificationLink = "http://localhost:8080/UM-app/verify?code=" + code;
	    String emailContent = "請點擊以下連結完成信箱驗證：<br><a href='" + verificationLink + "'>" + verificationLink + "</a>";
	     
	    mailservice.sendmail(mem.getEmail(), "email驗證", emailContent);
	    
		return  "redirect:/";
		
				}
	@GetMapping("/verify")
	public String verify(@RequestParam("code") String verificationCode, Model model) {
		Members members = membersService.getMemberByVerificationCode(verificationCode);

		if (members != null && members.getStatus().equals("未驗證")) {
			members.setStatus("已驗證");
			membersService.updateMember3(members.getId(), members.getStatus());

			model.addAttribute("message", "您的email已成功驗證！");
		} else {
			// 设置验证失败的消息
			model.addAttribute("message", "驗證連結无效或已过期！");
		}
		return "login/verificationResultPage";
	}

	// 圖片顯示
	@GetMapping(value = "/getphoto", produces = "image/*")
	@ResponseBody
	public byte[] getMemberPhotoById(@RequestParam(defaultValue = "0") Integer id) throws IOException {
		byte[] memberPhoto = membersService.getMemberPhotoByID2(id);
		if (memberPhoto == null) {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(ResourceUtils.getFile("classpath:\\no_image.png")));
			memberPhoto = bis.readAllBytes();
			bis.close();
		}
		return memberPhoto;
	}

	// 會員個人資料跳頁
	@GetMapping("members/date")
	public String membersDate(@RequestParam(required = false) Integer id, Model model, HttpSession session) {
		Members members = null;
		// 取得session中的member
		Members sessionMember = (Members) session.getAttribute("member");
		if (sessionMember != null) {
			members = membersService.findMembersById(sessionMember.getId());
		}
		if (members == null) {
			return "redirect:/members/login";
		}
		model.addAttribute("members", members);
		return "login/membersDate";
	}
	
	
	//可以跳頁到別人的個人資料頁面	
		@GetMapping("members/date/{id}")
		public String membersDateId(@PathVariable("id") Integer id, Model model, HttpSession session) {
			Members members = membersService.findMembersById(id);
			model.addAttribute("members", members);
			return "login/OtherMembersDate";
		}

	// 會員資料修改
	@PostMapping(value = "members/date", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String updateMember(@ModelAttribute("members") Members mem,
			@RequestParam(name = "photoContent", required = false) MultipartFile photoContent,
			@RequestParam("birthday") String birthdayStr, HttpSession session, Model model)
			throws IOException, ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = format.parse(birthdayStr);
		mem.setBirthday(birthday);

		if (photoContent != null && !photoContent.isEmpty()) {
			membersService.updateMember(mem.getId(), mem.getUsername(), mem.getEmail(), photoContent.getBytes(),
					mem.getGender(), mem.getAddress(), mem.getHeight(), mem.getWeight(), mem.getJob(), mem.getPhone(),
					mem.getBirthday());

			try {

				BufferedInputStream bis = new BufferedInputStream(photoContent.getInputStream());
				mem.setPhoto(bis.readAllBytes());
				bis.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		model.addAttribute("members", mem);
		membersService.updateMember2(mem.getId(), mem.getUsername(), mem.getEmail(), mem.getGender(), mem.getAddress(),
				mem.getHeight(), mem.getWeight(), mem.getJob(), mem.getPhone(), mem.getBirthday());

		return "redirect:/members/date";

	}

	// 顯示圖片2
	@GetMapping("/showphoto/{id}")
	public ResponseEntity<byte[]> showphoto(@PathVariable Integer id) {
		Members photo = membersService.findMemberPhotoById(id);
		byte[] photoFile = photo.getPhoto();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);

		return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);

	}

	// 在後台顯示所有會員
	@GetMapping("members/allmembers")
	public String showmembers(@RequestParam(name = "show", defaultValue = "1") Integer page, Model model,
			HttpSession session) {

		Page<Members> memberspage = membersService.findByPage(page);
		model.addAttribute("page", memberspage);
		return "login/allmemberdate";
	}

	// 跳頁到後臺修改會員資料頁面
	@GetMapping("members/admupmemberdate/{nowPage}")
	public String upmembers(@RequestParam("id") Integer id, Model model, @PathVariable("nowPage") Integer nowPage) {
		Members memup = membersService.findMemberPhotoById(id);
		model.addAttribute("members", memup);
		model.addAttribute("nowPage", nowPage);
		return "login/admMembersupdate";
	}

	// 後臺修改會員資料
	@PostMapping(value = "members/admupmemberdate/{nowPage}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String updateMember2(@ModelAttribute("members") Members mem,
			@RequestParam(name = "photoContent", required = false) MultipartFile photoContent,
			@RequestParam("birthday") String birthdayStr, @RequestParam("nowPage") Integer nowPage, HttpSession session,
			Model model) throws IOException, ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = format.parse(birthdayStr);
		mem.setBirthday(birthday);

		if (photoContent != null && !photoContent.isEmpty()) {
			membersService.updateMember(mem.getId(), mem.getUsername(), mem.getEmail(), photoContent.getBytes(),
					mem.getGender(), mem.getAddress(), mem.getHeight(), mem.getWeight(), mem.getJob(), mem.getPhone(),
					mem.getBirthday());

			try {

				BufferedInputStream bis = new BufferedInputStream(photoContent.getInputStream());
				mem.setPhoto(bis.readAllBytes());
				bis.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		model.addAttribute("members", mem);
		membersService.updateMember2(mem.getId(), mem.getUsername(), mem.getEmail(), mem.getGender(), mem.getAddress(),
				mem.getHeight(), mem.getWeight(), mem.getJob(), mem.getPhone(), mem.getBirthday());

		return "redirect:/members/allmembers?show=" + nowPage;

	}

	// 停權
	// 跳頁到後臺停權會員資料頁面
	@GetMapping("members/stopmemberdate/{nowPage}")
	public String stopmembers(@RequestParam("id") Integer id, Model model, @PathVariable("nowPage") Integer nowPage) {
		Members memup = membersService.findMemberPhotoById(id);
		model.addAttribute("members", memup);
		model.addAttribute("nowPage", nowPage);
		return "login/stopmemberdate";
	}

	// 後臺停權會員資料頁面
	@PostMapping("members/stopmemberdate/{nowPage}")
	public String swichLevel(@ModelAttribute("members") Members mem, HttpSession session, Model model,
			@RequestParam("nowPage") Integer nowPage) {
		membersService.swichLevel(mem.getId(), mem.getLevel());

		return "redirect:/members/allmembers?show=" + nowPage;
	}

	// 後臺貼文查詢
	@GetMapping("members/showmembersphotoshow")
	public String showMembersPhotos(@RequestParam(name = "show", defaultValue = "1") Integer page, Model model,
			HttpSession session) {
		Page<Photos> photospage = photosService.findByPage(page);
		model.addAttribute("page", photospage);
		return "login/showmembersphotoshow";
	}

	// 後台跳頁到圖片編輯
	@GetMapping("/members/upmemberspicture/{nowphPage}")
	public String upmemberspicture(@RequestParam("pid") Integer pid, Model model,
			@PathVariable("nowphPage") Integer nowphPage) {
		Photos upp = photosService.findById(pid);
		model.addAttribute("Photos", upp);
		model.addAttribute("nowphPage", nowphPage);
		return "login/upmemberspicture";

	}

	// 後台圖片編輯
	@PostMapping(value = "/members/upmemberspicture/{nowphPage}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String trueupmemberspicture(@ModelAttribute("Photos") Photos phts,
			@RequestParam(name = "pictureContent", required = false) MultipartFile pictureContent, HttpSession session,
			@RequestParam("nowphPage") Integer nowphPage) throws IOException {

		if (pictureContent != null && !pictureContent.isEmpty()) {
			photosService.updatePhotos(phts.getPid(), phts.getTitle(), phts.getText(), pictureContent.getBytes());

			try {
				BufferedInputStream bis = new BufferedInputStream(pictureContent.getInputStream());
				phts.setPicture(bis.readAllBytes());
				bis.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		photosService.updatePhotos2(phts.getPid(), phts.getTitle(), phts.getText());

		phts = photosService.getPhotos(phts.getPid());
		session.setAttribute("photo", phts);

		return "redirect:/members/showmembersphotoshow?show=" + nowphPage;
	}

	// 後臺刪除貼文
	@DeleteMapping("/members/admindeleteMembers/{nowphPage}")
	public String deletePhotos(@RequestParam("pid") Integer pid, @PathVariable("nowphPage") Integer nowphPage) {
		photosService.deleteById(pid);
		return "redirect:/members/showmembersphotoshow?show=" + nowphPage;
	}

}
