package com.eeit162.FWBweb.daka.login;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.eeit162.FWBweb.crs.match.model.bean.MemberDetail;
import com.eeit162.FWBweb.daka.member.model.beam.Likes;
import com.eeit162.FWBweb.daka.member.model.beam.Photos;

@Table(name = "members")
@Entity
public class Members {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	


	@Column(name = "password")
	private String password;

	@Column(name = "level", columnDefinition = " nvarchar(10)")
	private String level;

	@Column(name = "username", columnDefinition = " nvarchar(10)")
	private String username;


	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "gender")
	private String gender;

	@Column(name = "address", columnDefinition = " nvarchar(50)")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "photo", columnDefinition = "varbinary(MAX)")
	private byte[] photo;
	
	@Transient
	private MultipartFile photoContent;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	@Column(name = "created_at")
	private Date created_at;
	
	
	
	@PrePersist
    public void onCreat() {
        if(created_at==null) {
            created_at=new Date();
        }
        if(lastLogindAt==null) {
            lastLogindAt=new Date();
        }
    }

    @PreUpdate
    public void onUpdate() {
        lastLogindAt = new Date();
    }

	
	@OneToOne(mappedBy = "members",cascade = CascadeType.ALL)
    private MemberDetail memberDetail;
    @Column(name = "last_login_at")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogindAt;
    public MemberDetail getMemberDetail() {
        return memberDetail;
    }
    public void setMemberDetail(MemberDetail memberDetail) {
        this.memberDetail = memberDetail;
    }
    public Date getLastLogindAt() {
        return lastLogindAt;
    }
    public void setLastLogindAt(Date lastLogindAt) {
        this.lastLogindAt = lastLogindAt;
    }
	
	
	
	
	
	
	
	
	
	
	
	@Column(name = "status", columnDefinition = " nvarchar(20)")
	private String status;
	
	@Column(name = "code",columnDefinition = " nvarchar(100)")
	private String code;

	@Column(name = "phone")
	private Integer  phone;
	@Column(name = "height")
	private Integer height;

	@Column(name = "weight")
	private Integer weight;

	@Column(name = "job", columnDefinition = " nvarchar(50)")
	private String job;
	
	@OneToMany(mappedBy = "members",cascade = CascadeType.ALL)
	private List<Photos> photos;
	
	
	@OneToMany(mappedBy = "members",cascade = CascadeType.ALL)
	private List<Likes> likes;
	

	public Members() {

	}
	

	public MultipartFile getPhotoContent() {
		return photoContent;
	}


	public void setPhotoContent(MultipartFile photoContent) {
		this.photoContent = photoContent;
	}


	public List<Photos> getPhotos() {
		return photos;
	}


	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}


	public List<Likes> getLikes() {
		return likes;
	}


	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}


	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public String getAccount() {
//		return account;
//	}
//
//	public void setAccount(String account) {
//		this.account = account;
//	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}



	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getVerificationcode() {
		return code;
	}


	public void setVerificationcode(String code) {
		this.code = code;
	}





	

	

	
	

}
