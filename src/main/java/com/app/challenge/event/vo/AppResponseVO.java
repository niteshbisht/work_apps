package com.app.challenge.event.vo;

import java.io.Serializable;

public class AppResponseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4018035861965775031L;
	private long userID;
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

	private String responseMessage;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
}
