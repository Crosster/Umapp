package com.eeit162.FWBweb.hc.advertising.model.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "click")
public class Click {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "click_id")
	private Integer clickId;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_time", columnDefinition = "datetime")
	private Date createdTime;

//	@ManyToOne
//	@JoinColumn(name = "fk_member_id", referencedColumnName = "member_id")
//	private Member member;

	@ManyToOne
	@JoinColumn(name = "fk_advertisement_id", referencedColumnName = "advertisement_id")
	private Advertisement advertisement;

	public Click() {
	}

	public Integer getClickId() {
		return clickId;
	}

	public void setClickId(Integer clickId) {
		this.clickId = clickId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	@Override
	public String toString() {
		return "Click [clickId=" + clickId + ", createdTime=" + createdTime + ", advertisement=" + advertisement + "]";
	}

}
