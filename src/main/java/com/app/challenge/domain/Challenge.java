package com.app.challenge.domain;

import java.io.Serializable;
import java.util.Date;

public class Challenge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2681868076818374734L;

	private int challengeId;
	private int creatorId;
	private int acceptorId;
	private String fcbkChlngId;
	private Date startDate;
	private Date endDate;
	private String status;
	private String challengeType;
	private String topic;
	private Player player1;
	private Player player2;
	
	public int getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(int challengeId) {
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

	public int getCreatorId() {
		return creatorId;
	}

	public int getAcceptorId() {
		return acceptorId;
	}

	public String getFcbkChlngId() {
		return fcbkChlngId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public void setAcceptorId(int acceptorId) {
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

	public String getTopic() {
		return topic;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

}
