package com.eeit162.FWBweb.hc.advertising.model.bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "advertiser")
public class Advertiser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "advertiser_id")
	private Integer advertiserId;

	@Column(name = "email", unique = true, nullable = false, length = 100)
	private String email;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Transient
	private String verificationCode;

	@Column(name = "reset_token")
	private String resetToken;

	@Column(name = "login_attempt")
	private int loginAttempt = 0;

	@Column(name = "brand", columnDefinition = "nvarchar(100)")
	private String brand;

	@Lob
	@Column(name = "logo", columnDefinition = "varbinary(max)")
	private byte[] logo;

	@Column(name = "company_name", columnDefinition = "nvarchar(100)")
	private String companyName;

	@Column(name = "unified_business_no", length = 20)
	private String unifiedBusinessNo;

	@Column(name = "contact", columnDefinition = "nvarchar(20)")
	private String contact;

	@Column(name = "phone", length = 20)
	private String phone;

	@Column(name = "address", columnDefinition = "nvarchar(100)")
	private String address;

	@Column(name = "enabled", columnDefinition = "bit")
	private Boolean enabled;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_time", columnDefinition = "datetime")
	private Date createdTime;

	@OneToMany(mappedBy = "advertiser", cascade = CascadeType.ALL)
	private List<Advertisement> advertisements;

	public Advertiser() {
	}

	public Integer getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(Integer advertiserId) {
		this.advertiserId = advertiserId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public int getLoginAttempt() {
		return loginAttempt;
	}

	public void setLoginAttempt(int loginAttempt) {
		this.loginAttempt = loginAttempt;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUnifiedBusinessNo() {
		return unifiedBusinessNo;
	}

	public void setUnifiedBusinessNo(String unifiedBusinessNo) {
		this.unifiedBusinessNo = unifiedBusinessNo;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public List<Advertisement> getAdvertisements() {
		return advertisements;
	}

	public void setAdvertisements(List<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

	@Override
	public String toString() {
		return "Advertiser [advertiserId=" + advertiserId + ", email=" + email + ", password=" + password + ", brand="
				+ brand + ", logo=" + Arrays.toString(logo) + ", companyName=" + companyName + ", unifiedBusinessNo="
				+ unifiedBusinessNo + ", contact=" + contact + ", phone=" + phone + ", address=" + address
				+ ", enabled=" + enabled + ", createdTime=" + createdTime + ", advertisements=" + advertisements + "]";
	}

}
