package com.eeit162.FWBweb.cheng.activity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.eeit162.FWBweb.cheng.activity.bean.RegistBean;
import com.eeit162.FWBweb.cheng.activity.dao.RegistRepository;

@Controller
public class RegistController {

	@Autowired
	private RegistRepository rDao;

	// 外部client端 → 新增資訊 //
	@PostMapping("/regist/Crud")
	public List<RegistBean> add(@RequestBody List<RegistBean> rList) {
		return rDao.saveAll(rList);
	}

	// 外部client端 → 查詢資料 //
	@GetMapping("/Regist/findById")
	public RegistBean findregistBeanById(@RequestParam("id") Integer id) {
		Optional<RegistBean> rReaserch = rDao.findById(id);

		if (rReaserch.isPresent()) {
			return rReaserch.get();
		}

		return null;
	}

	// 外部client端 → 資料分頁 //
	@GetMapping("/Regist/multiPage")
	public List<RegistBean> findByPage(@RequestParam(name = "rPage") Integer rNumber) {
		PageRequest rPgb = PageRequest.of(rNumber - 1, 6, Sort.Direction.ASC, "id");
		Page<RegistBean> rPage = rDao.findAll(rPgb);

		return rPage.getContent();
	}



}
	





