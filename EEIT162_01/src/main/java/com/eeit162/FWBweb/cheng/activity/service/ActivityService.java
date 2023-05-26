package com.eeit162.FWBweb.cheng.activity.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.eeit162.FWBweb.cheng.activity.bean.ActivityBean;
import com.eeit162.FWBweb.cheng.activity.dao.ActivityRepository;

@Service
public class ActivityService {
	
	@Autowired
	private ActivityRepository atDao;
	
	@Modifying
	@Transactional
	public void updateActivity(ActivityBean newActivity) {
		Optional<ActivityBean> op = atDao.findById(newActivity.getActivityId());
		ActivityBean oldActivity = null;
		if(!op.isEmpty()) {
			oldActivity = op.get();
			if(newActivity.getActivityAd().length == 0) {
				newActivity.setActivityAd(oldActivity.getActivityAd());
			}
		}
		if(oldActivity != null) {
			atDao.save(newActivity);
		}
		
	}

	public void insertActivity(ActivityBean newActivity) {
		atDao.save(newActivity);
	}
		
	public ActivityBean findActivityById(Integer activityId) {
		Optional<ActivityBean> opA = atDao.findById(activityId);
		if (opA.isPresent()) {
			return opA.get();
		}
		return null;
	}
	

	public void deleteById(Integer activityId) {
		atDao.deleteById(activityId);
	}
	
	public List<ActivityBean> findAllActivity() {
		return atDao.findAll();
	}
	
	public Page<ActivityBean> findAllActivity(PageRequest pageRequest) {
		return atDao.findAll(pageRequest);
	}
	
	public Page<ActivityBean> findActivityByKeyword(String keyword, PageRequest pageRequest){
		return atDao.findActivityByKeyword(keyword, pageRequest);
	}

}
