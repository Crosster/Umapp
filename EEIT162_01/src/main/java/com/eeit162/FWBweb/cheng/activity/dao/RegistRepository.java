package com.eeit162.FWBweb.cheng.activity.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eeit162.FWBweb.cheng.activity.bean.RegistBean;

public interface RegistRepository extends JpaRepository<RegistBean, Integer> {

	
	
}
