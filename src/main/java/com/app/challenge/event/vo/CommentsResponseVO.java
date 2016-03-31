/**
 * 
 */
package com.app.challenge.event.vo;

import java.util.Date;

/**
 * @author Vikash Sharma
 *
 */
public class CommentsResponseVO {

	private long challengeId;
	private String comment;
	private Date commentDate;
	private String userName;
	private String userImage;

	/**
	 * @return the challengeId
	 */
	public long getChallengeId() {
		return challengeId;
	}

	/**
	 * @param challengeId
	 *            the challengeId to set
	 */
	public void setChallengeId(long challengeId) {
		this.challengeId = challengeId;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the commentDate
	 */
	public Date getCommentDate() {
		return commentDate;
	}

	/**
	 * @param commentDate
	 *            the commentDate to set
	 */
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userImage
	 */
	public String getUserImage() {
		return userImage;
	}

	/**
	 * @param userImage
	 *            the userImage to set
	 */
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

}
