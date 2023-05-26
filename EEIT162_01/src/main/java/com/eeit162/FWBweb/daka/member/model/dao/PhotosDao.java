package com.eeit162.FWBweb.daka.member.model.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eeit162.FWBweb.daka.login.Members;
import com.eeit162.FWBweb.daka.member.model.beam.Photos;

public interface PhotosDao extends JpaRepository<Photos, Integer> {
 @Query(value = "select * from Photos where mId=:mId",nativeQuery = true)
	public Page<Photos> findPhotoByMemberId(@Param(value ="mId" ) Integer mId,Pageable pgb);
 

}
