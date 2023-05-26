package com.eeit162.FWBweb.cheng.activity.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.eeit162.FWBweb.cheng.activity.bean.ActivityBean;

public interface ActivityRepository extends JpaRepository<ActivityBean, Integer>{
	

	
	
// 透過 ActivityRepository 對 Activity table 進行資料查詢
	@Query(value="from ActivityBean where activityName = :aName")
	public List<ActivityBean> findActivityByName(@Param(value="aName") String aName);
	
// 資料 修改功能 設定
	@Transactional 
	@Modifying
	@Query(value="update ActivityBean set activityName = :aName where activityId = :aId")
	Integer updateActivityNameById(@Param(value="aName") String aName, @Param(value="aId")Integer aId);

// 資料 搜索功能	 設定
	@Query(value = "select * from activity\r\n"
			+ "where activity_name LIKE CONCAT('%', ?1,'%') or activity_content LIKE CONCAT('%', ?1,'%') or  activity_type LIKE CONCAT('%', ?1,'%')", nativeQuery = true)
	public Page<ActivityBean> findActivityByKeyword(String keyword, PageRequest pageRequest);

}
