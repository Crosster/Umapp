package com.eeit162.FWBweb.zh.messenger.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eeit162.FWBweb.zh.messenger.model.bean.GameBean;

public interface GameDao extends JpaRepository<GameBean, Integer> {

}
