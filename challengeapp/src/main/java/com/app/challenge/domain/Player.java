package com.app.challenge.domain;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable	{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2657956566609338830L;
	private int playerId;
	private int challengeId;
	private int userId;
	private String winStatus;
	private int fbLikeCounts;
	private byte[] playerImage;
	private String playerType;
	private List<String> playerInfo;
	
	private List<String> fbComments;
	

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

	public int getPlayerId() {
		return playerId;
	}

	public int getChallengeId() {
		return challengeId;
	}

	public int getUserId() {
		return userId;
	}

	public String getWinStatus() {
		return winStatus;
	}

	public int getFbLikeCounts() {
		return fbLikeCounts;
	}

	public byte[] getPlayerImage() {
		return playerImage;
	}

	public String getPlayerType() {
		return playerType;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public void setChallengeId(int challengeId) {
		this.challengeId = challengeId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setWinStatus(String winStatus) {
		this.winStatus = winStatus;
	}

	public void setFbLikeCounts(int fbLikeCounts) {
		this.fbLikeCounts = fbLikeCounts;
	}

	public void setPlayerImage(byte[] playerImage) {
		this.playerImage = playerImage;
	}

	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
}
