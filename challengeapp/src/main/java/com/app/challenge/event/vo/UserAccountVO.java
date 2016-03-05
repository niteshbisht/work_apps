package com.app.challenge.event.vo;

import java.sql.Blob;
import java.util.Date;

public class UserAccountVO {
	private int id;
	private String userName;
	private String userEmail;
	private String deviceId;
	private String deviceType;
	private int totalChallenges;
	private int totalWins;
	private Date createdDate;
	private Date lastUpdatedDate;
	private Blob playerImage;

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public int getTotalChallenges() {
		return totalChallenges;
	}

	public int getTotalWins() {
		return totalWins;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public Blob getPlayerImage() {
		return playerImage;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public void setTotalChallenges(int totalChallenges) {
		this.totalChallenges = totalChallenges;
	}

	public void setTotalWins(int totalWins) {
		this.totalWins = totalWins;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public void setPlayerImage(Blob playerImage) {
		this.playerImage = playerImage;
	}

}
