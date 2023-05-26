package com.eeit162.FWBweb.zh.messenger.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eeit162.FWBweb.zh.messenger.model.bean.ReportedBean;

public interface ReportedDao extends JpaRepository<ReportedBean, Integer> {

}
