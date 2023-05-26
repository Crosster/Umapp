package com.eeit162.FWBweb.daka.member.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eeit162.FWBweb.daka.member.model.beam.Likes;

public interface LikesDao extends JpaRepository<Likes, Integer> {
	
	@Query(value = "from Likes WHERE (photos.id = :PID AND members.id = :MID)")
	List<Likes> findLikeByPidAndMid(@Param(value = "PID") Integer pid,@Param(value = "MID") Integer mid);
	
	
	

}
