package com.app.challenge.domain;

import java.io.Serializable;
import java.sql.Blob;
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
	private int fcbkLikeCounts;
	private Blob playerImage;
	private String playerType;
	private List<String> playerInfo;

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

	public int getFcbkLikeCounts() {
		return fcbkLikeCounts;
	}

	public Blob getPlayerImage() {
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

	public void setFcbkLikeCounts(int fcbkLikeCounts) {
		this.fcbkLikeCounts = fcbkLikeCounts;
	}

	public void setPlayerImage(Blob playerImage) {
		this.playerImage = playerImage;
	}

	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
}
