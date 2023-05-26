package com.eeit162.FWBweb.daka.member.model.beam;

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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.eeit162.FWBweb.daka.login.Members;


@Entity
@Table(name = "likes")
public class Likes {
	
	

	public Likes( Date created_at, Members members, Photos photos) {
		
		this.created_at = created_at;
		this.members = members;
		this.photos = photos;
	}

	public Likes() {
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer lid;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@Column(name = "created_at")
	private Date created_at;
	
	@JoinColumn(name = "l_mid",referencedColumnName = "id",nullable = false)
	@ManyToOne
	private Members members;
	
	@JoinColumn(name = "l_pid",referencedColumnName = "pid",nullable = false)
	@ManyToOne
	private Photos photos;
	
	
	private String likeStatus;
	
	@Transient
	private Integer l_mid;
	
	
	@Column(name = "liked")
	private String liked;
	
	

	public Integer getL_mid() {
		return l_mid;
	}

	public void setL_mid(Integer l_mid) {
		this.l_mid = l_mid;
	}

	public String getLikeStatus() {
		return likeStatus;
	}

	public void setLikeStatus(String likeStatus) {
		this.likeStatus = likeStatus;
	}

	public String isLiked() {
		return liked;
	}

	public void setLiked(String liked) {
		this.liked = liked;
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Members getMembers() {
		return members;
	}

	public void setMembers(Members members) {
		this.members = members;
	}

	public Photos getPhotos() {
		return photos;
	}

	public void setPhotos(Photos photos) {
		this.photos = photos;
	}

//	@Override
//	public String toString() {
//		return "Likes [lid=" + lid + ", created_at=" + created_at + ", members=" + members + ", photos=" + photos + "]";
//	}


}
