package com.eeit162.FWBweb.cheng.activity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeit162.FWBweb.cheng.activity.bean.SupplierBean;
import com.eeit162.FWBweb.cheng.activity.dao.SupplierRepository;


@Service
public class SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	public List<SupplierBean> getAllSupplier() {
		return	supplierRepository.findAll();
	}

//	<!-- 05/03 -->: 透過 SupplierService 對 SQL 表格『suppliers』進行 CRUD
	// <!-- 04/26 -->: 設定 showSuppliersDomain.jsp 新增資料 功能 
	public void insertActivity(SupplierBean slBean) {
		supplierRepository.save(slBean);
	}
	
	// <!-- 04/26 -->: 設定 showSuppliersDomain.jsp 查尋資料 功能 
	public SupplierBean findById(Integer supplierId) {
		Optional<SupplierBean> oSupplierFBI = supplierRepository.findById(supplierId);
		
		if(oSupplierFBI.isPresent()) {
			return oSupplierFBI.get();
		}
		return null;
	}
	
	// <!-- 05/04 -->: 設定 ActivityShowPage.jsp 修改資料 功能 
	@Transactional 
	public SupplierBean updateById(Integer supplierId, String newAtBean) {
		Optional<SupplierBean> oSupplierUBI = supplierRepository.findById(supplierId);
		
		if (oSupplierUBI.isPresent()) {
			SupplierBean slBean = oSupplierUBI.get();
			slBean.setText(newAtBean);
			return slBean;
		}
		return null;
	}	
	
	// <!-- 04/26 -->: 設定 showSuppliersDomain.jsp 刪除資料 功能 
	public void deleteById(Integer supplierId) {
		supplierRepository.deleteById(supplierId);
	}

	
//	<!-- 05/03 -->: 隸屬 activirtShowPage.jsp 內建立	 showSuppliersDomain.jsp 註冊頁面 (未完成)
	
}
