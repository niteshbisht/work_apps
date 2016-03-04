package com.app.challenge.domain;

import java.io.Serializable;
import java.util.Date;

public class Challenge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2681868076818374734L;

	private int challengeId;
	private String socialToken;
	private String creatorId;
	private String acceptorId;
	private Date startDate;
	private Date endDate;

	public int getChallengeId() {
		return challengeId;
	}


	public String getSocialToken() {
		return socialToken;
	}

	public void setChallengeId(int challengeId) {
		this.challengeId = challengeId;
	}


	public void setSocialToken(String socialToken) {
		this.socialToken = socialToken;
	}


	public String getCreatorId() {
		return creatorId;
	}


	public Date getStartDate() {
		return startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getAcceptorId() {
		return acceptorId;
	}


	public void setAcceptorId(String acceptorId) {
		this.acceptorId = acceptorId;
	}

}
