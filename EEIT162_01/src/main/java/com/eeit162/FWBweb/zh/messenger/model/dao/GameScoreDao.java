package com.eeit162.FWBweb.zh.messenger.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eeit162.FWBweb.zh.messenger.model.bean.GameScoreBean;

public interface GameScoreDao extends JpaRepository<GameScoreBean, Integer> {

}
