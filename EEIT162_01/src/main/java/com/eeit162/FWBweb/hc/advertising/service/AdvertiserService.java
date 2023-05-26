package com.eeit162.FWBweb.hc.advertising.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.eeit162.FWBweb.hc.advertising.model.bean.Advertiser;
import com.eeit162.FWBweb.hc.advertising.model.dao.AdvertiserDAO;

@Service
public class AdvertiserService {

	@Autowired
	private AdvertiserDAO advertiserDAO;

	@Autowired
	private JavaMailSender mailSender;

	private static final int MAX_LOGIN_ATTEMPT = 5;

	// 註冊並寄出驗證碼
	public Advertiser register(Advertiser advertiser, HttpSession session) throws Exception {
		String email = advertiser.getEmail();
		String password = advertiser.getPassword();

		// 檢查信箱是否為空
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("信箱未填寫");
		}

		// 檢查密碼是否為空
		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("密碼未填寫");
		}

		// 檢查信箱格式是否正確
		if (!isValidEmail(email)) {
			throw new IllegalArgumentException("信箱格式不正確");
		}

		// 檢查信箱是否已經存在
		if (advertiserDAO.findByEmail(email) != null) {
			throw new IllegalArgumentException("信箱已經被使用");
		}

		// 檢查密碼是否足夠複雜
		if (!isValidPassword(password)) {
			throw new IllegalArgumentException("密碼長度至少為8，且包含至少一個大寫字母、小寫字母和數字");
		}

		// 如果以上檢查皆通過，生成一個驗證碼並儲存在session中
		String verificationCode = generateVerificationCode();
		session.setAttribute("verificationCode", verificationCode);

		// 將驗證碼發送到用戶的信箱
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("註冊驗證碼");
		message.setText("以下是您的驗證碼: " + verificationCode);
		mailSender.send(message);

		// 創建新的廣告商，帳戶預設為未啟用
		Advertiser newAdvertiser = new Advertiser();
		newAdvertiser.setEmail(email);
		newAdvertiser.setPassword(password);
		newAdvertiser.setEnabled(false);
		session.setAttribute("advertiser", newAdvertiser); // 把廣告商存到session供後續驗證使用

		System.out.println("advertiser: " + session.getAttribute("advertiser"));

		return advertiserDAO.save(newAdvertiser);
	}

	// 檢查驗證碼
	public void verify(String code, HttpSession session) throws IOException {
		// 從session中獲取驗證碼
		String savedCode = (String) session.getAttribute("verificationCode");

		if (code.equals(savedCode)) {
			// 從session中獲取廣告商
			Advertiser newAdvertiser = (Advertiser) session.getAttribute("advertiser");
			newAdvertiser.setEnabled(true); // 啟用帳號
			newAdvertiser.setCreatedTime(new Date()); // 紀錄註冊時間
			// 設定預設圖片
			Path path = Paths.get("src/main/resources/no_image.png");
			byte[] defaultImage = Files.readAllBytes(path);
			newAdvertiser.setLogo(defaultImage);
			advertiserDAO.save(newAdvertiser);
		} else {
			throw new IllegalArgumentException("驗證碼不正確");
		}
	}

	// 登入
	public Advertiser login(Advertiser advertiser) throws Exception {
		String email = advertiser.getEmail();
		String password = advertiser.getPassword();

		Advertiser loggedInAdvertiser = advertiserDAO.findByEmail(email);

		// 檢查信箱是否為空
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("信箱未填寫");
		}

		// 檢查密碼是否為空
		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("密碼未填寫");
		}

		// 檢查信箱是否存在
		if (loggedInAdvertiser == null) {
			throw new IllegalArgumentException("找不到此用戶");
		}

		if (loggedInAdvertiser.getPassword().equals(password)) {
			// 登入成功
			loggedInAdvertiser.setLoginAttempt(0);
		} else {
			// 登入失敗
			loggedInAdvertiser.setLoginAttempt(loggedInAdvertiser.getLoginAttempt() + 1);
			advertiserDAO.save(loggedInAdvertiser);

			// 登入機會剩下一次
			if (loggedInAdvertiser.getLoginAttempt() == MAX_LOGIN_ATTEMPT - 1) {
				throw new FailedLoginException("只剩下一次登入機會，失敗的話您的帳號將被鎖定。");
			}

			// 嘗試登入次數超過限制
			if (loggedInAdvertiser.getLoginAttempt() >= MAX_LOGIN_ATTEMPT) {
				loggedInAdvertiser.setEnabled(false);
				advertiserDAO.save(loggedInAdvertiser);
				throw new FailedLoginException("由於登入失敗次數過多，您的帳號已被鎖定，請聯繫我們。");
			}

			throw new IllegalArgumentException("密碼不正確");
		}

		// 檢查帳號是否被停權
		if (!loggedInAdvertiser.getEnabled()) {
			throw new IllegalArgumentException("此帳號已遭停權");
		}

		return advertiserDAO.save(loggedInAdvertiser);
	}

	// 更改密碼
	public void changePassword(Integer id, String oldPassword, String newPassword, String confirmNewPassword) {

		Advertiser advertiser = advertiserDAO.findById(id).get();

		// 檢查用戶是否存在
		if (advertiser == null) {
			throw new NoSuchElementException("找不到此用戶，ID: " + id);
		}

		// 檢查舊密碼是否正確
		if (!oldPassword.equals(advertiser.getPassword())) {
			throw new IllegalArgumentException("舊密碼不正確");
		}

		// 檢查新密碼是否和舊密碼相同
		if (newPassword.equals(oldPassword)) {
			throw new IllegalArgumentException("新密碼不得和舊密碼相同");
		}

		// 檢查密碼是否足夠複雜
		if (!isValidPassword(newPassword)) {
			throw new IllegalArgumentException("密碼長度至少為8，且包含至少一個大寫字母、小寫字母和數字");
		}

		// 雙重確認新密碼
		if (!newPassword.equals(confirmNewPassword)) {
			throw new IllegalArgumentException("第二次輸入的密碼不相同");
		}

		advertiser.setPassword(newPassword);
		advertiserDAO.save(advertiser);
	}

	// 忘記密碼
	// 請求密碼重置
	public void requestPasswordReset(String email) {
		Advertiser advertiser = advertiserDAO.findByEmail(email);

		// 檢查用戶是否存在
		if (advertiser == null) {
			throw new NoSuchElementException("找不到此用戶");
		}

		// 檢查帳號是否被停權
		if (!advertiser.getEnabled()) {
			throw new IllegalArgumentException("此帳號已遭停權");
		}

		String token = UUID.randomUUID().toString(); // 生成一個隨機token
		advertiser.setResetToken(token);
		advertiserDAO.save(advertiser); // 保存token到資料庫

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(advertiser.getEmail());
		message.setSubject("密碼重置請求");
		message.setText(
				"要重置您的密碼，請點擊以下連結:\n" + "http://localhost:8080/UM-app/advertisers/reset_password?token=" + token); // 將token添加到URL中

		mailSender.send(message); // 寄送email
	}

	// 重置密碼
	public void resetPassword(String token, String newPassword, String confirmNewPassword) {
		Advertiser advertiser = advertiserDAO.findByResetToken(token);
		if (advertiser == null) {
			throw new NoSuchElementException("找不到此token: " + token);
		}

		// *在實際應用中，還應該檢查token是否已過期

		// 檢查密碼是否足夠複雜
		if (!isValidPassword(newPassword)) {
			throw new IllegalArgumentException("密碼長度至少為8，且包含至少一個大寫字母、小寫字母和數字");
		}

		// 雙重確認新密碼
		if (!newPassword.equals(confirmNewPassword)) {
			throw new IllegalArgumentException("第二次輸入的密碼不相同");
		}

		advertiser.setPassword(newPassword); // 更新密碼
		advertiser.setResetToken(null); // 清除token
		advertiserDAO.save(advertiser);
	}

	// 查詢所有廣告商
	public List<Advertiser> findAll() {
		return advertiserDAO.findAll();
	}

	// 根據ID查詢廣告商
	public Advertiser findById(Integer id) {
		return advertiserDAO.findById(id).get();
	}

	// 更新廣告商資料
	public void update(Advertiser advertiser) {

		Advertiser originalAdvertiser = advertiserDAO.findById(advertiser.getAdvertiserId()).get();

		if (originalAdvertiser == null) {
			throw new IllegalArgumentException("找不到此用戶");
		}

		originalAdvertiser.setEmail(advertiser.getEmail());
		originalAdvertiser.setPassword(advertiser.getPassword());
		originalAdvertiser.setBrand(advertiser.getBrand());
		originalAdvertiser.setLogo(advertiser.getLogo());
		originalAdvertiser.setCompanyName(advertiser.getCompanyName());
		originalAdvertiser.setUnifiedBusinessNo(advertiser.getUnifiedBusinessNo());
		originalAdvertiser.setContact(advertiser.getContact());
		originalAdvertiser.setPhone(advertiser.getPhone());
		originalAdvertiser.setAddress(advertiser.getAddress());

		advertiserDAO.save(originalAdvertiser);
	}

	// 根據ID刪除廣告商
	public void delete(Integer id) {
		advertiserDAO.deleteById(id);
	}

	// 驗證信箱是否符合格式
	public boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null) {
			return false;
		}
		return pat.matcher(email).matches();
	}

	// 驗證密碼是否足夠複雜
	public boolean isValidPassword(String password) {
		// 密碼長度至少8個字
		if (password.length() < 8) {
			return false;
		}

		// 密碼至少包含一個數字
		if (!password.matches(".*[0-9].*")) {
			return false;
		}

		// 密碼至少包含一個小寫字母
		if (!password.matches(".*[a-z].*")) {
			return false;
		}

		// 密碼至少包含一個大寫字母
		if (!password.matches(".*[A-Z].*")) {
			return false;
		}

		return true;
	}

	// 生成隨機六位數驗證碼
	public String generateVerificationCode() {
		return String.valueOf((int) ((Math.random() * 899999) + 100000));
	}

}
