package com.eeit162.FWBweb.hc.advertising.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eeit162.FWBweb.hc.advertising.model.bean.Plan;

public interface PlanDAO extends JpaRepository<Plan, Integer> {

}
