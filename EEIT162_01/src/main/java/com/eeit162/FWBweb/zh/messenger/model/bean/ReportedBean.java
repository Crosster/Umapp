package com.eeit162.FWBweb.zh.messenger.model.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Reported")
public class ReportedBean {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",columnDefinition = "integer")
	private Integer id;
	
	@Column(name = "report_type",columnDefinition = "nvarchar(50)")
	private String reportType;
	
	@Column(name = "report_description",columnDefinition = "nvarchar(50)")
	private String reportDescription;
	
	@Column(name = "approval_state",columnDefinition = "integer")
	private Integer approvalState;
	
	@Column(name = "created_at",columnDefinition = "date")
	private Date created;
	
	@Column(name = "f_member_id",columnDefinition = "integer")
	private Integer FKMemberId;
	
	@Column(name = "f_reportedId",columnDefinition = "integer")
	private Integer FKReportedId;

//	private Member member
	
	public ReportedBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportDescription() {
		return reportDescription;
	}

	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}

	public Integer getApprovalState() {
		return approvalState;
	}

	public void setApprovalState(Integer approvalState) {
		this.approvalState = approvalState;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getFKMemberId() {
		return FKMemberId;
	}

	public void setFKMemberId(Integer fKMemberId) {
		FKMemberId = fKMemberId;
	}

	public Integer getFKReportedId() {
		return FKReportedId;
	}

	public void setFKReportedId(Integer fKReportedId) {
		FKReportedId = fKReportedId;
	}
	
	

}
