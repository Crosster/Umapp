package com.eeit162.FWBweb.cheng.activity.bean;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "activity")
public class ActivityBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activity_id")
	private Integer activityId;
	
	@Column(name = "activity_type")
	private String activityType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	@Column(name = "activity_start_up_time")
	private Date activityStartUpTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	@Column(name = "activity_end_up_time")
	private Date activityEndTime;
	
	@Column(name = "activity_name")
	private String activityName;
	
	@Column(name = "activity_content")
	private String activityContent;
	
	@Column(name = "activity_fee")
	private Integer activityFee;
	
	@Column(name ="activity_ad", columnDefinition = "varbinary(MAX)")
	private byte[] activityAd;
	
	@Column(name ="activity_link")
	private String activityLink;
	
	
	
// FK：
	// private supplier supplier_id;
	
	public ActivityBean() {
	}
	
	
	// 設定 各活動 → 起始 & 結束 時間。
	@PrePersist 
	public void onActivityCreate() {
		if (activityStartUpTime == null) {
			activityStartUpTime = new Date(0); 
		} if (activityEndTime == null) {
			activityEndTime = new Date(1);
		}
	}


	public Integer getActivityId() {
		return activityId;
	}


	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}


	public String getActivityType() {
		return activityType;
	}


	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}


	public Date getActivityStartUpTime() {
		return activityStartUpTime;
	}


	public void setActivityStartUpTime(Date activityStartUpTime) {
		this.activityStartUpTime = activityStartUpTime;
	}


	public Date getActivityEndTime() {
		return activityEndTime;
	}


	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}


	public String getActivityName() {
		return activityName;
	}


	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}


	public String getActivityContent() {
		return activityContent;
	}


	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}


	public Integer getActivityFee() {
		return activityFee;
	}


	public void setActivityFee(Integer activityFee) {
		this.activityFee = activityFee;
	}


	public byte[] getActivityAd() {
		return activityAd;
	}


	public void setActivityAd(byte[] activityAd) {
		this.activityAd = activityAd;
	}


	public String getActivityLink() {
		return activityLink;
	}


	public void setActivityLink(String activityLink) {
		this.activityLink = activityLink;
	}

}
