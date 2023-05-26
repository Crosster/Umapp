package com.eeit162.FWBweb.cheng.activity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eeit162.FWBweb.cheng.activity.bean.SupplierBean;
import com.eeit162.FWBweb.cheng.activity.dao.SupplierRepository;
import com.eeit162.FWBweb.cheng.activity.service.SupplierService;

@RestController
public class SupplierController {

	@Autowired
	private SupplierRepository sDao;
	
	@Autowired
	private SupplierService sService;
	
	
//// 此為使用 Hibernate 呼叫 SQL 全部資料
//	@Autowired
//	private SupplierRepository supplierRepository;
	
	@GetMapping("/activity/findAllSupplier")
	public SupplierBean findAllSupplier() {
		return (SupplierBean) sService.getAllSupplier();
	}
	

// 外部 suplier 端 → 新增資訊 //
	// @RequestBody List<SupplierBean> sList
	@GetMapping("/activity/addSupplier_1") // 找不到更好的新增方法
	public SupplierBean supplierAdd() {
		SupplierBean adds = new SupplierBean();
//		adds.setSupplierName("XXXXX");
//		adds.setSupplierIndustryFields("XXXXX");
//		adds.setSupplierBusinessType("XXXXX");
//		adds.setSupplierCapital("10000");
//		adds.setSupplierAddress("XXXXX");
//		adds.setSupplierPhoneNumber("29322214");
//		adds.setSupplierDateCollaboration("20201031");
//		adds.setSupplierDateCollaborationExpire("20231031");
//		adds.setSupplierContractType("XXXXX");
//		adds.setSupplierLogo(null);
		
		SupplierBean reAdds = sDao.save(adds);
		return reAdds;
	}	
	
	
		// 單筆增加 寫法
	@PostMapping("/activity/addSupplier_2")
	public SupplierBean supplierAdd(@RequestBody SupplierBean adds) {
		return sDao.save(adds);
	}
	
		// 多筆增加 寫法
	@PostMapping("/activity/addSupplier_3")
	public List<SupplierBean> supplierAdd(@RequestBody List<SupplierBean> sList){
		return sDao.saveAll(sList);
	}
	
		// 表格資料 查詢功能
	@GetMapping("/activity/addSupplier_4")
	public SupplierBean findSupplierByID(@RequestParam("supplier_id") Integer id) {
		Optional<SupplierBean> ops = sDao.findById(id);
		
		if(ops.isPresent()){
			return ops.get();
		}
		return null;
	}


// 內部 主控檯端 → 設定 分頁功能。
	@GetMapping("/activity/MultiPage_Supplier")
	public List<SupplierBean> findByPage(@RequestParam(name = "sPage") Integer sPageNumber) {
		Pageable sPgb = PageRequest.of(sPageNumber-1, 6, Sort.Direction.ASC, "supplierId");
		Page<SupplierBean> sPage = sDao.findAll(sPgb);

		return sPage.getContent();
	}


// 利用 SupplierRepository 進行 資料查詢
	@GetMapping("/activity/findSupplierName")	
	public List<SupplierBean> findSupplierByName(@RequestParam(name="sName")String sName) {
		return sDao.findSupplierByName(sName);
	}
	
// 拓過 SupplierRepository 進行 修改
	@PutMapping("/activity/updateSupplierName")
	public String updateSupplierNameById(@RequestParam("sName") String newSupplierName, @RequestParam("sId")Integer newSupplierId) {
		
		Integer reSupplier = sDao.updateSupplierNameById(newSupplierName, newSupplierId);
		System.out.println("res:" + reSupplier);
		if(reSupplier != 0) {
		return "ok";
	}
	return "沒有這筆資料";
	}	
	
}
		
		
//// 外部suplier端 → 查詢資料 //
//	@GetMapping("/Supplier/findById")
//	public SupplierBean findsupplierBeanById(@RequestParam("id") Integer id) {
//		Optional<SupplierBean> sReaserch = sDao.findById(id);
//
//		if (sReaserch.isPresent()) {
//			return sReaserch.get();
//		}
//		return null;
//	}
//
//// 外部suplier端 → 資料分頁 //
//	@GetMapping("/Supplier/multi-Page")
//	public List<SupplierBean> findByPage(@RequestParam(name = "sPage") Integer sNumber) {
//		PageRequest sPgb = PageRequest.of(sNumber - 1, 6, Sort.Direction.ASC, "id");
//		Page<SupplierBean> sPage = sDao.findAll(sPgb);
//
//		return sPage.getContent();
//	}

	
//// 外部 suplier 端 → 資料 多條件查詢 //
//		// 供應商 名稱 查詢
//	@GetMapping("/Supplier/multi-search/")
//	public List<SupplierBean> fidSuplierByName(@RequestParam(name = "sName") String name) {
//		return sDao.fidSuplierByName(name);
//	}
//
//		// 供應商 產業別 查詢
//	@GetMapping("/Supplier/multi-search")
//	public List<SupplierBean> fidSuplierByIndustryFields(@RequestParam(name = "sIndustryFields") String name) {
//		return sDao.fidSuplierByIndustryFields(name);
//	}
//	
//		// 供應商 資本額 查詢
//	@GetMapping("/Supplier/multi-search")
//	public List<SupplierBean> fidSuplierBusinessType(@RequestParam(name = "sBusinessType") String name) {
//		return sDao.findBySupplierBusinessType(name);
//	}
//	
//		// 供應商 資本額 查詢
//	@GetMapping("/Supplier/multi-search")
//	public List<SupplierBean> fidSuplierByCapital(@RequestParam(name = "sCapital") String name) {
//		return sDao.fidSuplierByCapital(name);
//	}
//
//		// 供應商 營業地址 查詢
//	@GetMapping("/Supplier/multi-search")
//	public List<SupplierBean> fidSuplierByAddress(@RequestParam(name = "sAddress") String name) {
//		return sDao.fidSuplierByAddress(name);
//	}
//
//		// 供應商 營業電話 查詢
//	@GetMapping("/Supplier/multi-search")
//	public List<SupplierBean> fidSuplierByPhoneNumber(@RequestParam(name = "sPhoneNumber") String name) {
//		return sDao.fidSuplierByPhoneNumber(name);
//	}
//
//		// 供應商 合約類型 查詢
//	@GetMapping("/Supplier/multi-search")
//	public List<SupplierBean> fidSuplierByContractType(@RequestParam(name = "sDsteCollaborationExpire") String name) {
//		return sDao.fidSuplierByContractType(name);
//	}
//
//		// 供應商 合作起始日期 查詢
//	@GetMapping("/Supplier/multi-search")
//	public List<SupplierBean> fidSuplierByDateCollaboration(@RequestParam(name = "sDsteCollaboration") String name) {
//		return sDao.fidSuplierByDateCollaboration(name);
//	}
//
//		// 供應商 合作結束日期 查詢
//	@GetMapping("/Supplier/multi-search")
//	public List<SupplierBean> fidSuplierByDateCollaborationExpire(@RequestParam(name = "sDsteCollaborationExpire") String name) {
//		return sDao.fidSuplierByDateCollaborationExpire(name);
//	}
//	
//// 內部 suplier 主控台 → 資料修改
//	@PutMapping("/Supplier/multi-modify")
//	public String updateNameById(@RequestParam("supplier_name") String newName, @RequestParam("supplier_id") Integer id) {
//		Integer resName = sDao.updateNameById(newName, id);
//		if (resName != 0) {
//			return "ok";			
//		}
//		return "not found anything~!!!";
//	}
	
//	@PutMapping("/Supplier/multi-modify")
//	public String updateIndustryFiledsById(@RequestParam("supplier_industry_fields") String newIndustryFileds, @RequestParam("supplier_id") Integer id) {
//		Integer resIndustryFields = sDao.updateIndustryFiledsById(newIndustryFileds, id);
//		if (resIndustryFields != 0) {
//			return "ok";			
//		}
//		return "not found anything~!!!";
//	}
//	@PutMapping("/Supplier/multi-modify")
//	public String updateCapitalById(@RequestParam("supplier_capital") Integer newCapital, @RequestParam("supplier_id") Integer id) {
//		Integer resCapital = sDao.updateCapitalById(newCapital, id);
//		if (resCapital != 0) {
//			return "ok";			
//		}
//		return "not found anything~!!!";
//	}
//
//
//// 外部 客戶端 全部資料查詢
//		// 用 名字 查詢
//	@GetMapping("/customer/findBySupplierName")
//	public List<SupplierBean> supplierNameQuery(@RequestParam("supplier_name") String newName) {
//		return sDao.findBySupplierName(newName);
//	}
//		// 用 產業類別 查詢
//	@GetMapping("/customer/findBySupplierIndustryFields")
//	public List<SupplierBean> supplierIndustryFieldsQuery(@RequestParam("supplier_industry_fields") String newIndustryFields) {
//		return sDao.findBySupplierIndustryFields(newIndustryFields);
//	}
//		// 用 資本額 查詢
//	@GetMapping("/customer/findBySupplierCapital")
//	public List<SupplierBean> supplierCapitalQuery(@RequestParam("supplier_capital") Integer newCapital) {
//		return sDao.findBySupplierCapital(newCapital);
//	}
//		// 用 營業地址 查詢
//	@GetMapping("/customer/findBySupplierAdress")
//	public List<SupplierBean> supplierAdressQuery(@RequestParam("supplier_address") String newAdress) {
//		return sDao.findBySupplierAddress(newAdress);
//	}
//		// 用 營業電話 查詢
//	@GetMapping("/customer/findBySupplierPhoneNumber")
//	public List<SupplierBean> supplierPhoneNumberQuery(@RequestParam("supplier_phoneNumber") Integer newPhoneNumber) {
//		return sDao.findBySupplierPhoneNumber(newPhoneNumber);
//	}
//		// 用 合約類型 查詢
//	@GetMapping("/customer/findBySupplierContractType")
//	public List<SupplierBean> supplierContractTypeQuery(@RequestParam("supplier_contract_type") String newContractType) {
//		return sDao.findBySupplierContractType(newContractType);
//	}
//		//  用 合做起始日期 查詢
//	@GetMapping("/customer/findByCollaborationBegin")
//	public List<SupplierBean> supplierCollaborationBeginQuery(@RequestParam("supplier_date_collaboration") Integer newCollaborationBigen) {
//		return sDao.findBySupplierCollaoracitonBegin(newCollaborationBigen);
//	}
//		//  用 合做結束日期 查詢
//	@GetMapping("/customer/findBySupplierCollaborationEnd")
//	public List<SupplierBean> supplierCollaborationEndQuery(@RequestParam("supplier_date_collaboration_expire") Integer newCollaborationEnd) {
//		return sDao.findBySupplierCollaoracitonEnd(newCollaborationEnd);
//	}
//	
//	
//// /外部 客戶端 → 全部資料查詢 寫法<Hibernate>
//	@GetMapping("/customer/printSupplierByHibernat")
//	public List<SupplierBean> printAllDataQuery(){
//		String hql = "select * from supplier";
//		return nativeSDao.someSQL2(hql);
//	}
//
//	
//// 隸屬 activirtShowPage 之附屬網頁 showSuppliersDomain.jsp『供應商專區』 設定   // 影片名稱『EEIT162 Spring Boot Day 3-1』影片進度『0:19:16』
//		@GetMapping("/Activity/showSuppliers")
//		public String showSuppliersDomain() {
//			return "/branchPages/showSuppliersDomain";
//		}	

		
	
	