package com.eeit162.FWBweb.zh.payment.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eeit162.FWBweb.zh.payment.model.bean.MemberLevelOrderHistory;

public interface MemberLevelOrderHistoryDao extends JpaRepository<MemberLevelOrderHistory, Integer> {
	
	@Query(value = "select * from memberOrderList where memberId = :memberId",nativeQuery = true)
	List<MemberLevelOrderHistory> findAllOrderListByMemberId(@Param("memberId") Integer memberId);
}
