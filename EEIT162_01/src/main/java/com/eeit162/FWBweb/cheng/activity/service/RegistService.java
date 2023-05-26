package com.eeit162.FWBweb.cheng.activity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eeit162.FWBweb.cheng.activity.dao.RegistRepository;


@Service
public class RegistService<MemberAccountVO> {

		@Autowired
		private RegistRepository rgDao;
		
}
