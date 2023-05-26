package com.eeit162.FWBweb.hc.advertising.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eeit162.FWBweb.hc.advertising.model.bean.Advertisement;

public interface AdvertisementDAO extends JpaRepository<Advertisement, Integer> {

	public List<Advertisement> findAll();

	public List<Advertisement> findByAdvertiserAdvertiserId(Integer advertiserId);
}
