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
	private boolean newChallenge;
	private String playerImage;
	private String playerType;
	private String playerName;
	private String[] playerInfo;
	private String topic;
	private boolean openChallenge;
	private String fbUserID;
	private String duration;
	private String challengeType;
	private long challengeId;
	private String gameType;
	private String acceptorEmailId;
	private String fbUserName;
	
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
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
	public String getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/**
	 * @return the isOpenChallenge
	 */
	public boolean isOpenChallenge() {
		return openChallenge;
	}
	/**
	 * @param isOpenChallenge the isOpenChallenge to set
	 */
	public void setOpenChallenge(boolean isOpenChallenge) {
		this.openChallenge = isOpenChallenge;
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
		return newChallenge;
	}
	/**
	 * @param isNewChallenge the isNewChallenge to set
	 */
	public void setNewChallenge(boolean isNewChallenge) {
		this.newChallenge = isNewChallenge;
	}
	/**
	 * @return the playerImage
	 */
	public String getPlayerImage() {
		return playerImage;
	}
	/**
	 * @param playerImage the playerImage to set
	 */
	public void setPlayerImage(String playerImage) {
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
	public String getAcceptorEmailId() {
		return acceptorEmailId;
	}
	public void setAcceptorEmailId(String acceptorEmailId) {
		this.acceptorEmailId = acceptorEmailId;
	}
	public String getFbUserName() {
		return fbUserName;
	}
	public void setFbUserName(String fbUserName) {
		this.fbUserName = fbUserName;
	}
	
	
}
