package com.app.challenge.event.vo;

public class UserRegisterationVO {

	private String fbToken;
	private String email;

	private String deviceToken;
	private String deviceType;

	/**
	 * @return the fbToken
	 */
	public String getFbToken() {
		return fbToken;
	}

	/**
	 * @param fbToken
	 *            the fbToken to set
	 */
	public void setFbToken(String fbToken) {
		this.fbToken = fbToken;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the deviceToken
	 */
	public String getDeviceToken() {
		return deviceToken;
	}

	/**
	 * @param deviceToken
	 *            the deviceToken to set
	 */
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType
	 *            the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

}
