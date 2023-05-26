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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "advertisement")
public class Advertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "advertisement_id")
	private Integer advertisementId;

	@Column(name = "title", columnDefinition = "nvarchar(100)", nullable = false)
	private String title;

	@Lob
	@Column(name = "picture", columnDefinition = "varbinary(max)")
	private byte[] picture;

	@Column(name = "url", columnDefinition = "nvarchar(max)")
	private String url;

	@Column(name = "category", columnDefinition = "nvarchar(20)")
	private String category;

	@Column(name = "target_audience_age", length = 20)
	private String targetAudienceAge;

	@Column(name = "target_audience_sex", columnDefinition = "nvarchar(20)")
	private String targetAudienceSex;

	@Column(name = "status")
	private Integer status;

	@Transient
	private boolean manualOverride;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", columnDefinition = "datetime")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", columnDefinition = "datetime")
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_time", columnDefinition = "datetime")
	private Date createdTime;

	@ManyToOne
	@JoinColumn(name = "fk_advertiser_id", referencedColumnName = "advertiser_id")
	private Advertiser advertiser;

	@ManyToOne
	@JoinColumn(name = "fk_plan_id", referencedColumnName = "plan_id")
	private Plan plan;

	@OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL)
	private List<Click> clicks;

	public Advertisement() {
	}

	public Integer getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(Integer advertisementId) {
		this.advertisementId = advertisementId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTargetAudienceAge() {
		return targetAudienceAge;
	}

	public void setTargetAudienceAge(String targetAudienceAge) {
		this.targetAudienceAge = targetAudienceAge;
	}

	public String getTargetAudienceSex() {
		return targetAudienceSex;
	}

	public void setTargetAudienceSex(String targetAudienceSex) {
		this.targetAudienceSex = targetAudienceSex;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean isManualOverride() {
		return manualOverride;
	}

	public void setManualOverride(boolean manualOverride) {
		this.manualOverride = manualOverride;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

//	public Advertiser getAdvertiser() {
//		return advertiser;
//	}

	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

//	public List<Click> getClicks() {
//		return clicks;
//	}

	public void setClicks(List<Click> clicks) {
		this.clicks = clicks;
	}

	@Override
	public String toString() {
		return "Advertisement [advertisementId=" + advertisementId + ", title=" + title + ", picture="
				+ Arrays.toString(picture) + ", url=" + url + ", category=" + category + ", targetAudienceAge="
				+ targetAudienceAge + ", targetAudienceSex=" + targetAudienceSex + ", status=" + status + ", startDate="
				+ startDate + ", endDate=" + endDate + ", createdTime=" + createdTime + ", advertiser=" + advertiser
				+ ", plan=" + plan + ", clicks=" + clicks + "]";
	}

}
