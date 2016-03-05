package com.app.challenge.event.vo;

import java.io.Serializable;
import java.util.Date;

public class ChallengeAppVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3470535156522698068L;
	private int challengeId;
	private int creatorId;
	private int acceptorId;
	private String fcbkChlngId;
	private Date startDate;
	private Date endDate;
	private String status;
	private String challengeType;

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
}
