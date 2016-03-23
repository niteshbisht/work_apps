package com.app.challenge.event.dao;

import java.util.Date;
import java.util.List;

public class ChallengeDomain {

	@SuppressWarnings("unused")
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
	private String topic;
	private String fbUserName;
	private String creatorName;
	private String acceptorName;
	private List<String> comments;
	
	/**
	 * @return the challengeId
	 */
	public long getChallengeId() {
		return challengeId;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	/**
	 * @param challengeId the challengeId to set
	 */
	public void setChallengeId(long challengeId) {
		this.challengeId = challengeId;
	}
	/**
	 * @return the creatorId
	 */
	public long getCreatorId() {
		return creatorId;
	}
	/**
	 * @param creatorId the creatorId to set
	 */
	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * @return the acceptorId
	 */
	public long getAcceptorId() {
		return acceptorId;
	}
	/**
	 * @param acceptorId the acceptorId to set
	 */
	public void setAcceptorId(long acceptorId) {
		this.acceptorId = acceptorId;
	}
	/**
	 * @return the fcbkChlngId
	 */
	public String getFcbkChlngId() {
		return fcbkChlngId;
	}
	/**
	 * @param fcbkChlngId the fcbkChlngId to set
	 */
	public void setFcbkChlngId(String fcbkChlngId) {
		this.fcbkChlngId = fcbkChlngId;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the challengeType
	 */
	public String getChallengeType() {
		return challengeType;
	}
	/**
	 * @param challengeType the challengeType to set
	 */
	public void setChallengeType(String challengeType) {
		this.challengeType = challengeType;
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
	public String getFbUserName() {
		return fbUserName;
	}
	public void setFbUserName(String fbUserName) {
		this.fbUserName = fbUserName;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getAcceptorName() {
		return acceptorName;
	}
	public void setAcceptorName(String acceptorName) {
		this.acceptorName = acceptorName;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	
	
}
