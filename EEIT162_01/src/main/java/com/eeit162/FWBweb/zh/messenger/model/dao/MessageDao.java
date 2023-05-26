package com.eeit162.FWBweb.zh.messenger.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eeit162.FWBweb.zh.messenger.model.bean.MessageBean;

public interface MessageDao extends JpaRepository<MessageBean, Integer> {
	
	@Query(value = "select * from Message where (messenger_id = :messengerId and f_member_id = :fkMemberId) or (messenger_id = :fkMemberId and f_member_id = :messengerId)",nativeQuery = true)
	List<MessageBean> findMessageHistoryBetweenUser(@Param("messengerId") Integer messengerId,@Param("fkMemberId") Integer fkMemberId);
	
	@Query(value = "select TOP 1 * from Message where (messenger_id = :messengerId and f_member_id = :fkMemberId) or (messenger_id = :fkMemberId and f_member_id = :messengerId) order by id desc",nativeQuery = true)
	MessageBean findMessageHistoryBetweenUserLatest(@Param("messengerId") Integer messengerId,@Param("fkMemberId") Integer fkMemberId);
	
	@Modifying
	@Query(value = "update Message SET readtime = :readTime where id = :id",nativeQuery = true)
	Integer updateMessageById(@Param("readTime") Date readTime,@Param("id") Integer id);
	
}
