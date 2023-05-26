package com.eeit162.FWBweb.hc.advertising.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eeit162.FWBweb.hc.advertising.model.bean.Plan;
import com.eeit162.FWBweb.hc.advertising.model.dao.PlanDAO;

@Service
public class PlanService {

	@Autowired
	private PlanDAO planDAO;

	public List<Plan> findAll() {
		return planDAO.findAll();
	}
}
