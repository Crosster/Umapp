package com.eeit162.FWBweb.zh.messenger.model.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Gamescore")
public class GameScoreBean {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",columnDefinition = "integer")
	private Integer id;
	
	@Column(name = "score",columnDefinition = "integer")
	private Integer score;
	
	@Column(name = "created_at",columnDefinition = "date")
	private Date created;
	
	@Column(name = "f_member_id",columnDefinition = "integer")
	private Integer FKMemberId;
	
	@Column(name = "f_game_id",columnDefinition = "integer")
	private Integer FKGameId;
	
//	private Member member;
	
//	private GameBeam game;

	public GameScoreBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getFKMemberId() {
		return FKMemberId;
	}

	public void setFKMemberId(Integer fKMemberId) {
		FKMemberId = fKMemberId;
	}

	public Integer getFKGameId() {
		return FKGameId;
	}

	public void setFKGameId(Integer fKGameId) {
		FKGameId = fKGameId;
	}

//	public GameBeam getGame() {
//		return game;
//	}
//
//	public void setGame(GameBeam game) {
//		this.game = game;
//	}
	
	

}
