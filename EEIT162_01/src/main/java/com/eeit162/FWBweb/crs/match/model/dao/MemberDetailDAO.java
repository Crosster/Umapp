package com.eeit162.FWBweb.crs.match.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eeit162.FWBweb.crs.match.model.bean.MemberDetail;

public interface MemberDetailDAO extends JpaRepository<MemberDetail, Integer> {
//	//修改
//	@Modifying
//	@Query(value = "update MemberDetail md SET WHERE md.members.id = :memberId")
//	int updateMemberDetail(@Param(value = "memberId") Integer mid);
	
	@Modifying
	@Query("UPDATE MemberDetail md SET md.sexualOrientation = :sexualOrientation, md.bodyType = :bodyType, md.smoking = :smoking, md.smokingAccept = :smokingAccept, md.minAgePreference = :minAgePreference, md.maxAgePreference = :maxAgePreference WHERE md.members.id = :memberId")
	int updateMemberDetail(@Param("sexualOrientation") String sexualOrientation, @Param("bodyType") String bodyType, @Param("smoking") String smoking, @Param("smokingAccept") String smokingAccept, @Param("minAgePreference") Integer minAgePreference, @Param("maxAgePreference") Integer maxAgePreference, @Param("memberId") Integer memberId);

	//刪除
    @Modifying
	@Query(value = "delete MemberDetail md WHERE md.members.id = :memberId")
	int deleteMemberDetail(@Param(value = "memberId") Integer mid);
}
