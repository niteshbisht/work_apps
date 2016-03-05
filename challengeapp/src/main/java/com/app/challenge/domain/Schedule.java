package com.app.challenge.domain;

import java.io.Serializable;
import java.util.Date;

public class Schedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5769039944335591878L;

	private int scheduleId;
	private int challengeId;
	private int creatorUid;
	private Date createdDate;
	private String expiryTime;

	public int getScheduleId() {
		return scheduleId;
	}

	public int getChallengeId() {
		return challengeId;
	}

	public int getCreatorUid() {
		return creatorUid;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getExpiryTime() {
		return expiryTime;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public void setChallengeId(int challengeId) {
		this.challengeId = challengeId;
	}

	public void setCreatorUid(int creatorUid) {
		this.creatorUid = creatorUid;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}
}
