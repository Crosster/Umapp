package com.eeit162.FWBweb.cheng.activity.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.eeit162.FWBweb.cheng.activity.bean.SupplierBean;


public interface SupplierRepository extends JpaRepository<SupplierBean, Integer>{
	
	// 利用 SupplierRepository 進行 supplier table 進行資料查詢
	@Query(value="from SupplierBean where supplierName = :sName")
	public List<SupplierBean> findSupplierByName(@Param(value="sName") String sName);
	
	
	// native query
	@Query(value="select * from SupplierBean where supplierName = :sName", nativeQuery = true)
	public List<SupplierBean> findSupplierByName2(@Param(value="sName") String sName);
	
	
// 資料 修改功能 設定
	@Transactional 
	@Modifying
	@Query(value="update SupplierBean set supplierName = :sName where supplierId = :sId")
	Integer updateSupplierNameById(@Param(value="sName") String sName, @Param(value="sId")Integer sId);

	
	
	
	
//	// 多條件查詢 → 設定
//		// 廠商 名稱
//	@Query(value="from supplier where supplier_name = ?1")
//	List<SupplierBean> fidSuplierByName(String supplier_name);
//	
//		// 廠商 行業別
//	@Query(value="from supplier where supplier_industry_fields = ?1")
//	List<SupplierBean> fidSuplierByIndustryFields(String supplier_industry_fields);
//	
//		// 廠商 資本額
//	@Query(value="from supplier where supplier_capital = ?1")
//	List<SupplierBean> fidSuplierByCapital(String supplier_capital);
//		
//		// 廠商 營業地址
//	@Query(value="from supplier where supplier_address = ?1")
//	List<SupplierBean> fidSuplierByAddress(String supplier_address);
//		
//		// 廠商 營業電話
//	@Query(value="from supplier where supplier_phoneNumber = ?1")
//	List<SupplierBean> fidSuplierByPhoneNumber(String supplier_phoneNumber);
//		
//		// 廠商 合約類型
//	@Query(value="from supplier where supplier_contract_type = ?1")
//	List<SupplierBean> fidSuplierByContractType(String supplier_contract_type);
//		
//		// 廠商 合作起始日期
//	@Query(value="from supplier where supplier_date_collaboration = ?1")
//	List<SupplierBean> fidSuplierByDateCollaboration(String supplier_date_collaboration);
//		
//		// 廠商 合作結束日期
//	@Query(value="from supplier where supplier_date_collaboration_expire = ?1")
//	List<SupplierBean> fidSuplierByDateCollaborationExpire(String supplier_date_collaboration_expire);
//
//
//
//	// 內部 主控台 → 廠商多元資料修改 (需使用 HiberNet 設定)
//	@Transactional
//	@Modifying
//	@Query(value="update supplier set supplier_name = :supplier_name where supplier_id = :supplier_id")
//	Integer updateNameById(@Param(value="supplier_name") String name, @Param(value="supplier_id") Integer id);
//	
//		// 內部 主控台 → 資料查詢
//	List<SupplierBean> findBySupplierName(String supplier_name);
//	List<SupplierBean> findBySupplierIndustryFields(String supplier_industry_fields);
//	List<SupplierBean> findBySupplierBusinessType(String supplier_business_type);
//	List<SupplierBean> findBySupplierCapital(Integer supplier_capital);
//	List<SupplierBean> findBySupplierAddress(String supplier_address);
//	List<SupplierBean> findBySupplierPhoneNumber(Integer supplier_phoneNumber);
//	List<SupplierBean> findBySupplierContractType(String supplier_contract_type);
//	List<SupplierBean> findBySupplierCollaoracitonBegin(Integer supplier_date_collaboration);
//	List<SupplierBean> findBySupplierCollaoracitonEnd(Integer supplier_date_collaboration_expire);
//	
//		// 多條件查詢 → 建議改用 Hibernet 寫
//
//	@Transactional
//	@Modifying
//	@Query(value="update supplier set supplier_industry_fields = :supplier_industry_fields where supplier_id = :supplier_id")
//	Integer updateIndustryFiledsById(@Param(value="supplier_industry_fields") String industryFields, @Param(value="supplier_id") Integer id);
//
//	@Transactional
//	@Modifying
//	@Query(value="update supplier set supplier_business_type = :supplier_business_type where supplier_id = :supplier_id")
//	Integer updateBusinessTypeById(@Param(value="supplier_business_type") String BusinessType, @Param(value="supplier_id") Integer id);
//	
//	@Transactional
//	@Modifying
//	@Query(value="update supplier set supplier_capital = :supplier_capital where supplier_id = :supplier_id")
//	Integer updateCapitalById(@Param(value="supplier_capital") Integer newCapital, @Param(value="supplier_id") Integer id);
//	
//	@Transactional
//	@Modifying
//	@Query(value="update supplier set supplier_address = :supplier_address where supplier_id = :supplier_id")
//	Integer updateAdressById(@Param(value="supplier_address") String newAdress, @Param(value="supplier_id") Integer id);
//	
//	@Transactional
//	@Modifying
//	@Query(value="update supplier set supplier_phoneNumber = :supplier_phoneNumber where supplier_id = :supplier_id")
//	Integer updatePhoneNumberById(@Param(value="supplier_phoneNumber") Integer newPhoneNumber, @Param(value="supplier_id") Integer id);
//	
//	@Transactional
//	@Modifying
//	@Query(value="update supplier set supplier_contract_type = :supplier_contract_type where supplier_id = :supplier_id")
//	Integer updateContractTypeById(@Param(value="supplier_contract_type") String newContractType, @Param(value="supplier_id") Integer id);
//	
//	@Transactional
//	@Modifying
//	@Query(value="update supplier set supplier_date_collaboration = :supplier_date_collaboration where supplier_id = :supplier_id")
//	Integer updatenewCollaborationBeginById(@Param(value="supplier_date_collaboration") String newCollaborationBegin, @Param(value="supplier_id") Integer id);
//	
//	@Transactional
//	@Modifying
//	@Query(value="update supplier set supplier_date_collaboration_expire = :supplier_date_collaboration_expire where supplier_id = :supplier_id")
//	Integer updatenewCollaborationEndById(@Param(value="supplier_date_collaboration_expire") String newCollaborationEnd, @Param(value="supplier_id") Integer id);
//	
	
//	@PersistenceContext
//	private EntityManager emSupplier;
//
////	public List<supplierBean> someSQL(String hql){
////		TypedQuery<supplierBean> query = emSupplier.createQuery(hql, supplierBean.class);
////		return query.getResultList();
////	}
//	
//	public List<SupplierBean> someSQL2(String hql){
//		Query querySpplier = emSupplier.createNativeQuery(hql, SupplierBean.class);
//		return querySpplier.getResultList();
//	}


}
