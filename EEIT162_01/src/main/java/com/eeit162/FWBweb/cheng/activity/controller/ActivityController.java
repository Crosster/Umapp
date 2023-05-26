package com.eeit162.FWBweb.cheng.activity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eeit162.FWBweb.cheng.activity.bean.ActivityBean;
import com.eeit162.FWBweb.cheng.activity.dao.ActivityDao;
import com.eeit162.FWBweb.cheng.activity.dao.ActivityRepository;
import com.eeit162.FWBweb.cheng.activity.service.ActivityService;

@Controller
public class ActivityController {

	@Autowired
	private ActivityRepository aDao;
	
//	@Autowired
//	private ActivityDao nativeActivityDao;
	
	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private ActivityService aSerivce;
	
	@GetMapping("/activity/showImg/{activityId}")
	@ResponseBody
	public ResponseEntity<byte[]> showImg(@PathVariable("activityId") Integer activityId) {
		ActivityBean activityBean = aSerivce.findActivityById(activityId);
		byte[] img =  activityBean.getActivityAd();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		
		
		
		return new ResponseEntity<byte[]>(img,headers,HttpStatus.OK);
	}
	
	@GetMapping("/activity/addActPage/{ID}")
	@ResponseBody
	public ActivityBean addActivittyPage(Model model,@PathVariable("ID")Integer id) {
		//ActivityBean actg = new ActivityBean();
		//model.addAttribute("activity", actg);
		ActivityBean target = activityDao.findById(id).orElseThrow(()->new IllegalArgumentException("invalid param"));;
		
		return target;
	}
	
	@PostMapping("/activity/findAll")
	@ResponseBody
	public List<ActivityBean> findAllActivity() {		
		return aSerivce.findAllActivity();
	}
	
	
// 內部 主控台 → 新增資訊
	@GetMapping("/activity/addActivity_1")
	@ResponseBody
	public ActivityBean activityAdd() {
		ActivityBean adda = new ActivityBean();
		ActivityBean resAdda = aDao.save(adda);
		
		return resAdda;
	}
	
		// 單筆增加 寫法
	@PostMapping("/activity/addActivity_2")
	@ResponseBody
	public ActivityBean activityAdd(@RequestBody ActivityBean adda) {
		aDao.save(adda);
		return adda;
	}

		// 多筆增加 寫法
	@PostMapping("/activity/addActivity_3")
	public List<ActivityBean> activityAdd(@RequestBody List<ActivityBean> aList){
	return aDao.saveAll(aList);
	}
	
		// 表格資料 查詢功能
	@GetMapping("/activity/addActivity_4")
	public ActivityBean findSupplierByID(@RequestParam("activity_id") Integer id) {
		Optional<ActivityBean> opa = aDao.findById(id);
		
		if(opa.isPresent()){
			return opa.get();
		}
		return null;
	}
	
	
// 外部client端 → 資料分頁
	@GetMapping("/activity/MultiPage_Activity")
	public List<ActivityBean> findByPage(@RequestParam(name = "aPage") Integer aPageNumber) {
		Pageable aPgb = PageRequest.of(aPageNumber-1, 6, Sort.Direction.ASC, "activityId");
		Page<ActivityBean> aPage = aDao.findAll(aPgb);

		return aPage.getContent();
	}

	
// 透過 ActivityRepository 對 Activity table 進行資料查詢
	@GetMapping("/activity/findActivityName")	
	public List<ActivityBean> findActivityName(@RequestParam(name="aName")String aNameSearch) {
		return aDao.findActivityByName(aNameSearch);
	}


// 透過 ActivityRepository 進行 修改
	@PutMapping("/activity/updateActivityName")
	public String updateActivityNameById(@RequestParam("aName") String newActivityName, @RequestParam("aId")Integer newActivityId) {
		Integer reActivity = aDao.updateActivityNameById(newActivityName, newActivityId);
		System.out.println("res:" + reActivity);
		if (reActivity != 0) {
			return "ok";
		}
		return "沒有這筆資料";
	}

}
	

	
	
