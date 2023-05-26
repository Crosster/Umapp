package com.eeit162.FWBweb.crs.match.model.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Entity
//@Table(name = "match_member")
public class Member {
//	public Member() {
//	}
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id",columnDefinition = "integer")
//	private Integer id;
//	@Column(name = "name",columnDefinition = "nvarchar(10)")
//	private String name;
//	@Column(name = "height",columnDefinition = "integer")
//	private Integer height;
//	@Column(name = "weight",columnDefinition = "integer")
//	private Integer weight;
//
////	加入@JsonIgnore會導致json會忽略memberDetail物件，不利於ajax開發，但是用model就沒這個問題
////	@JsonIgnore
//	@OneToOne(mappedBy = "member",cascade = CascadeType.ALL)
//	private MemberDetail memberDetail;
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Integer getHeight() {
//		return height;
//	}
//
//	public void setHeight(Integer height) {
//		this.height = height;
//	}
//
//	public Integer getWeight() {
//		return weight;
//	}
//
//	public void setWeight(Integer weight) {
//		this.weight = weight;
//	}
//
//	public MemberDetail getMemberDetail() {
//		return memberDetail;
//	}
//
//	public void setMemberDetail(MemberDetail memberDetail) {
//		this.memberDetail = memberDetail;
//	}
//
//	@Override
//	public String toString() {
//		return "Member [id=" + id + ", height=" + height + ", weight=" + weight + ", memberDetail=" + memberDetail
//				+ "]";
//	}
//	
//	
}
