package com.eeit162.FWBweb.cheng.activity.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
public class SupplierBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_id")
	private Integer supplierId;
	
	@Column(name = "supplier_name")
	private String supplierName;
	
	@Column(name = "supplier_industry_fields")
	private String supplierIndustryFields;
	
	@Column(name = "supplier_business_type")
	private String supplierBusinessType;

	@Column(name = "supplier_capital")
	private String supplierCapital;
	
	@Column(name = "supplier_address")
	private String supplierAddress;
	
	@Column(name = "supplier_phoneNumber")
	private String supplierPhoneNumber;
	
	@Column(name = "supplier_date_collaboration")
	private String supplierDateCollaboration;
	
	@Column(name = "supplier_date_collaboration_expire")
	private String supplierDateCollaborationExpire;
	
	@Column(name = "supplier_contract_type")
	private String supplierContractType;
	
	@Column(name ="supplier_logo", columnDefinition = "varbinary(MAX)")
	private byte[] supplierLogo;
	
	public SupplierBean() {
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierIndustryFields() {
		return supplierIndustryFields;
	}

	public void setSupplierIndustryFields(String supplierIndustryFields) {
		this.supplierIndustryFields = supplierIndustryFields;
	}

	public String getSupplierBusinessType() {
		return supplierBusinessType;
	}

	public void setSupplierBusinessType(String supplierBusinessType) {
		this.supplierBusinessType = supplierBusinessType;
	}

	public String getSupplierCapital() {
		return supplierCapital;
	}

	public void setSupplierCapital(String supplierCapital) {
		this.supplierCapital = supplierCapital;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierPhoneNumber() {
		return supplierPhoneNumber;
	}

	public void setSupplierPhoneNumber(String supplierPhoneNumber) {
		this.supplierPhoneNumber = supplierPhoneNumber;
	}

	public String getSupplierDateCollaboration() {
		return supplierDateCollaboration;
	}

	public void setSupplierDateCollaboration(String supplierDateCollaboration) {
		this.supplierDateCollaboration = supplierDateCollaboration;
	}

	public String getSupplierDateCollaborationExpire() {
		return supplierDateCollaborationExpire;
	}

	public void setSupplierDateCollaborationExpire(String supplierDateCollaborationExpire) {
		this.supplierDateCollaborationExpire = supplierDateCollaborationExpire;
	}

	public String getSupplierContractType() {
		return supplierContractType;
	}

	public void setSupplierContractType(String supplierContractType) {
		this.supplierContractType = supplierContractType;
	}

	public byte[] getSupplierLogo() {
		return supplierLogo;
	}

	public void setSupplierLogo(byte[] supplierLogo) {
		this.supplierLogo = supplierLogo;
	}

	// <!-- 05/04 -->: 設定 showSuppliersDomain.jsp 修改資料 功能時，與 老師 做的方法不同。 
	public void setText(String newAtBean) {
	}

	
	
}
