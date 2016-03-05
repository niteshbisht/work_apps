package com.app.challenge.event.vo;

import java.io.Serializable;

public class AppResponseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4018035861965775031L;

	private String responseMessage;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}
