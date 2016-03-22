package com.app.challenge.event.vo;

public class CommentVO {

	private long userId;
	private long challengeId;
	private String userName;
	private String comment;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getChallengeId() {
		return challengeId;
	}
	public void setChallengeId(long challengeId) {
		this.challengeId = challengeId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
