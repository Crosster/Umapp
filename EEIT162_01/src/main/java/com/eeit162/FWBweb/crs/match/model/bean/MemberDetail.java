package com.eeit162.FWBweb.crs.match.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.eeit162.FWBweb.daka.login.Members;

@Entity
@Table(name = "member_detail")
public class MemberDetail {
	public MemberDetail() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "integer")
	private Integer id;

//  sexual_orientation (VARCHAR) — 性向
//（例如：喜歡男性、喜歡女性）
	@Column(name = "sexual_orientation", columnDefinition = "nvarchar(10)")
	private String sexualOrientation;
//  body_type (VARCHAR) — 體型偏好
//（例如：偏瘦、普通、微肉感）
	@Column(name = "body_type", columnDefinition = "nvarchar(10)")
	private String bodyType;
	@Column(name = "education_level", columnDefinition = "nvarchar(10)")
	private String educationLevel;
//    smoking (VARCHAR) — 是否抽菸
//（例如：不抽、抽）
	@Column(name = "smoking", columnDefinition = "nvarchar(10)")
	private String smoking;
//    drinking (VARCHAR) — 是否接受對象抽菸
//（例如：可接受、不可接受）
	@Column(name = "smoking_accept", columnDefinition = "nvarchar(10)")
	private String smokingAccept;
//    min_age_preference (INT) — 理想對象至少滿幾歲
//（例如：18、19...、65）
	@Column(name = "min_age_preference", columnDefinition = "integer")
	private Integer minAgePreference;
//    max_age_preference (INT) — 理想對象最好不要超過幾歲
//（例如：18、19...、65）
	@Column(name = "max_age_preference", columnDefinition = "integer")
	private Integer maxAgePreference;

//    hobby (VARCHAR) — 興趣愛好
//    favorite_music (VARCHAR) — 喜愛的音樂類型
//    favorite_movie (VARCHAR) — 喜愛的電影類型

	@JoinColumn(name = "FK_mID", referencedColumnName = "id", nullable = false, unique = true)
	@OneToOne(fetch = FetchType.LAZY)
	private Members members;

	public void setMember(Members members) {
		this.members = members;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSexualOrientation() {
		return sexualOrientation;
	}

	public void setSexualOrientation(String sexualOrientation) {
		this.sexualOrientation = sexualOrientation;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getSmoking() {
		return smoking;
	}

	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}

	public String getSmokingAccept() {
		return smokingAccept;
	}

	public void setSmokingAccept(String smokingAccept) {
		this.smokingAccept = smokingAccept;
	}

	public Integer getMinAgePreference() {
		return minAgePreference;
	}

	public void setMinAgePreference(Integer minAgePreference) {
		this.minAgePreference = minAgePreference;
	}

	public Integer getMaxAgePreference() {
		return maxAgePreference;
	}

	public void setMaxAgePreference(Integer maxAgePreference) {
		this.maxAgePreference = maxAgePreference;
	}

	@Override
	public String toString() {
		return "MemberDetail [id=" + id + ", sexualOrientation=" + sexualOrientation + ", bodyType=" + bodyType
				+ ", educationLevel=" + educationLevel + ", smoking=" + smoking + ", smokingAccept=" + smokingAccept
				+ ", minAgePreference=" + minAgePreference + ", maxAgePreference=" + maxAgePreference + "]";
	}

}
