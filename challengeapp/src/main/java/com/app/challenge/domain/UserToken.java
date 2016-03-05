package com.app.challenge.domain;

import java.io.Serializable;
import java.util.Date;

public class UserToken implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4490260240001293182L;
	private int userId;
	private String fcbkToken;
	private Date createdDate;
	private Date updatedDate;
	public int getUserId() {
		return userId;
	}
	public String getFcbkToken() {
		return fcbkToken;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setFcbkToken(String fcbkToken) {
		this.fcbkToken = fcbkToken;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	
}
