package com.eeit162.FWBweb.zh.messenger.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eeit162.FWBweb.zh.messenger.model.bean.BlacklistBean;

public interface BlacklistDao extends JpaRepository<BlacklistBean, Integer> {

}
