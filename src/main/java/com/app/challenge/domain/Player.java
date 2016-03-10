package com.app.challenge.domain;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable	{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2657956566609338830L;
	private long playerId;
	private long challengeId;
	private long userId;
	private String winStatus;
	private long fbLikeCounts;
	private String playerImage;
	
	private String playerType;
	private String playerName;
	private List<String> playerInfo;
	
	private List<String> fbComments;
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
	 * @return the fbComments
	 */
	public List<String> getFbComments() {
		return fbComments;
	}

	/**
	 * @param fbComments the fbComments to set
	 */
	public void setFbComments(List<String> fbComments) {
		this.fbComments = fbComments;
	}

	/**
	 * @return the playerInfo
	 */
	public List<String> getPlayerInfo() {
		return playerInfo;
	}

	/**
	 * @param playerInfo the playerInfo to set
	 */
	public void setPlayerInfo(List<String> playerInfo) {
		this.playerInfo = playerInfo;
	}

	public long getPlayerId() {
		return playerId;
	}

	public long getChallengeId() {
		return challengeId;
	}

	public long getUserId() {
		return userId;
	}

	public String getWinStatus() {
		return winStatus;
	}

	public long getFbLikeCounts() {
		return fbLikeCounts;
	}

	public String getPlayerImage() {
		return playerImage;
	}

	public String getPlayerType() {
		return playerType;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public void setChallengeId(long challengeId) {
		this.challengeId = challengeId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setWinStatus(String winStatus) {
		this.winStatus = winStatus;
	}

	public void setFbLikeCounts(long fbLikeCounts) {
		this.fbLikeCounts = fbLikeCounts;
	}

	public void setPlayerImage(String playerImage) {
		this.playerImage = playerImage;
	}

	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
}
