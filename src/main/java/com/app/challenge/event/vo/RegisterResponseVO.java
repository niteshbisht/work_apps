package com.app.challenge.event.vo;

import java.io.Serializable;


public class RegisterResponseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -829441822604978707L;
	
	private long userId;
	private String userName;
	private long totalWinCount;
	private long totalLooseCount;
	private long activeCount;
	
	public long getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public long getTotalWinCount() {
		return totalWinCount;
	}
	public long getTotalLooseCount() {
		return totalLooseCount;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setTotalWinCount(long totalWinCount) {
		this.totalWinCount = totalWinCount;
	}
	public void setTotalLooseCount(long totalLooseCount) {
		this.totalLooseCount = totalLooseCount;
	}
	public long getActiveCount() {
		return activeCount;
	}
	public void setActiveCount(long activeCount) {
		this.activeCount = activeCount;
	}
	

}
