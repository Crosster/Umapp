package com.eeit162.FWBweb.daka.member.model.beam;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.eeit162.FWBweb.daka.login.Members;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Photos")
public class Photos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	
	@Column(name = "photousername",columnDefinition = "nvarchar(20)")
	private String photousername;
	
	@Column(name = "title",columnDefinition = "nvarchar(20)")
	private String title;
	
	@Column( name = "text",columnDefinition = "nvarchar(100)")
	private String text;
	
	@Column(name = "picture",columnDefinition = "varbinary(MAX)")
	private byte[] picture;
	
	@Transient
	private MultipartFile pictureContent;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "created_at")
	private Date created_at;
	
	
	@JsonIgnoreProperties({"photos"})
	@JoinColumn(name = "mId",referencedColumnName = "id")
	@ManyToOne
	private Members members;
	
	@JsonIgnore
	@OneToMany(mappedBy = "photos",cascade = CascadeType.ALL)
	private List<Likes> likes;

	public Photos() {
		
	}
	
	

	public String getPhotousername() {
		return photousername;
	}



	public void setPhotousername(String photousername) {
		this.photousername = photousername;
	}



	public MultipartFile getPictureContent() {
		return pictureContent;
	}



	public void setPictureContent(MultipartFile pictureContent) {
		this.pictureContent = pictureContent;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
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

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}



	@Override
	public String toString() {
		return "Photos [pid=" + pid + ", photousername=" + photousername + ", title=" + title + ", text=" + text
				+ ", picture=" + Arrays.toString(picture) + ", pictureContent=" + pictureContent + ", created_at="
				+ created_at + ", members=" + members + ", likes=" + likes + "]";
	}
	

}