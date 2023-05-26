package com.eeit162.FWBweb.crs.match.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.eeit162.FWBweb.crs.match.model.bean.Friend;

public interface FriendDAO extends JpaRepository<Friend, Integer> {

	// HQL瞄準java的bean的名字
	// 原生Sql瞄準資料庫中欄位的名字
	
	//查詢
	@Query(value = "from Friend WHERE member1.id = :memberId AND status = :status")
	List<Friend> findByMid1AndStatus(@Param(value = "memberId") Integer mid1,@Param(value = "status") String status);	
	@Query(value = "from Friend WHERE member2.id = :memberId AND status = :status")
	List<Friend> findByMid2AndStatus(@Param(value = "memberId") Integer mid2,@Param(value = "status") String status);	
//	@Query(value = "select * from friend WHERE FK_member1_id = :memberId AND status = :status",nativeQuery = true)
//	List<Friend> findByIdAndStatus(@Param(value = "memberId") Integer mid,@Param(value = "status") String status);	
	
	@Query(value = "select * from Friend WHERE (FK_member1_id = :memberId AND status = :status) or (FK_member2_id = :memberId AND status = :status)",nativeQuery = true)
	List<Friend> findAllFriendList(@Param("memberId") Integer memberId,@Param("status") String status);
	
//	修改
//	@Transactional
	@Modifying
	@Query(value = "update Friend SET status = :status WHERE (member1.id = :memberId1 AND member2.id = :memberId2)")
	int updateFriend(@Param(value = "status") String status,@Param(value = "memberId1") Integer mid1,@Param(value = "memberId2") Integer mid2);
	
	//刪除好友
	@Modifying
	@Query(value = "delete Friend WHERE (member1.id = :memberId1 AND member2.id = :memberId2) OR (member1.id = :memberId2 AND member2.id = :memberId1)")
	int deleteFriend(@Param(value = "memberId1") Integer mid1, @Param(value = "memberId2") Integer mid2);
	
}
