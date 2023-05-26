package com.eeit162.FWBweb.cheng.activity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eeit162.FWBweb.cheng.activity.bean.ActivityBean;

public interface ActivityDao extends JpaRepository<ActivityBean, Integer> {

	/*public List<ActivityBean> someSQL(String hql) {
		return null;
	}*/

}
