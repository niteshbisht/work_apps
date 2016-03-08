/**
 * 
 */
package com.app.challenge.event.vo;

import com.app.challenge.domain.Player;

/**
 * @author rainbow
 *
 */
public class AcceptChallegeVO {

	private long userID;
	private long challengeID;
	private Player player;
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
	 * @return the challengeID
	 */
	public long getChallengeID() {
		return challengeID;
	}
	/**
	 * @param challengeID the challengeID to set
	 */
	public void setChallengeID(long challengeID) {
		this.challengeID = challengeID;
	}
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

}
