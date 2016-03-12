package com.app.challenge.event.vo;

import java.io.Serializable;
import java.util.Date;

import com.app.challenge.domain.Player;

public class AllChallengeResponseVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3470535156522698068L;
	private long challengeId;
	private long creatorId;
	private long acceptorId;
	private String fcbkChlngId;
	private Date startDate;
	private Date endDate;
	private String status;
	private String challengeType;
	private String gameType;
	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	private String topic;
	private Player player1;
	private Player player2;

	/**
	 * @return the player1
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * @param player1 the player1 to set
	 */
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	/**
	 * @return the player2
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * @param player2 the player2 to set
	 */
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	public long getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(long challengeId) {
		this.challengeId = challengeId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public long getAcceptorId() {
		return acceptorId;
	}

	public String getFcbkChlngId() {
		return fcbkChlngId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public void setAcceptorId(long acceptorId) {
		this.acceptorId = acceptorId;
	}

	public void setFcbkChlngId(String fcbkChlngId) {
		this.fcbkChlngId = fcbkChlngId;
	}

	public String getChallengeType() {
		return challengeType;
	}

	public void setChallengeType(String challengeType) {
		this.challengeType = challengeType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
