package com.eeit162.FWBweb.hc.advertising.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eeit162.FWBweb.hc.advertising.model.bean.Advertiser;

public interface AdvertiserDAO extends JpaRepository<Advertiser, Integer> {

	public Advertiser findByEmail(String email);

	public Advertiser findByEmailAndPassword(String email, String password);
	
	public Advertiser findByResetToken(String resetToken);

}
