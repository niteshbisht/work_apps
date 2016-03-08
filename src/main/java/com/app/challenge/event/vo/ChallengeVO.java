/**
 * 
 */
package com.app.challenge.event.vo;

/**
 * @author Vikash Sharma
 *
 */
public class ChallengeVO {

	private long userID;
	private boolean isNewChallenge;
	private byte[] playerImage;
	private String playerType;
	private String playerName;
	private String[] playerInfo;
	private String topic;
	private boolean isOpenChallenge;
	private String fbUserID;
	private long duration;
	private String challengeType;
	private long challengeId;
	
	/**
	 * @return the challengeId
	 */
	public long getChallengeId() {
		return challengeId;
	}
	/**
	 * @param challengeId the challengeId to set
	 */
	public void setChallengeId(long challengeId) {
		this.challengeId = challengeId;
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
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}
	/**
	 * @return the isOpenChallenge
	 */
	public boolean isOpenChallenge() {
		return isOpenChallenge;
	}
	/**
	 * @param isOpenChallenge the isOpenChallenge to set
	 */
	public void setOpenChallenge(boolean isOpenChallenge) {
		this.isOpenChallenge = isOpenChallenge;
	}
	/**
	 * @return the fbUserID
	 */
	public String getFbUserID() {
		return fbUserID;
	}
	/**
	 * @param fbUserID the fbUserID to set
	 */
	public void setFbUserID(String fbUserID) {
		this.fbUserID = fbUserID;
	}
	/**
	 * @return the userID
	 */
	public long getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(long userID) {
		this.userID = userID;
	}
	/**
	 * @return the isNewChallenge
	 */
	public boolean isNewChallenge() {
		return isNewChallenge;
	}
	/**
	 * @param isNewChallenge the isNewChallenge to set
	 */
	public void setNewChallenge(boolean isNewChallenge) {
		this.isNewChallenge = isNewChallenge;
	}
	/**
	 * @return the playerImage
	 */
	public byte[] getPlayerImage() {
		return playerImage;
	}
	/**
	 * @param playerImage the playerImage to set
	 */
	public void setPlayerImage(byte[] playerImage) {
		this.playerImage = playerImage;
	}
	/**
	 * @return the playerType
	 */
	public String getPlayerType() {
		return playerType;
	}
	/**
	 * @param playerType the playerType to set
	 */
	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}
	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	/**
	 * @return the playerInfo
	 */
	public String[] getPlayerInfo() {
		return playerInfo;
	}
	/**
	 * @param playerInfo the playerInfo to set
	 */
	public void setPlayerInfo(String[] playerInfo) {
		this.playerInfo = playerInfo;
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
	
	
}
