package com.app.challenge.domain;

import com.restfb.types.Post.Comments;

public class FBPostData {

	private long postLikeCount;
	private long postCommentCount;
	private Comments comments;
	/**
	 * @return the postLikeCount
	 */
	public long getPostLikeCount() {
		return postLikeCount;
	}
	/**
	 * @param postLikeCount the postLikeCount to set
	 */
	public void setPostLikeCount(long postLikeCount) {
		this.postLikeCount = postLikeCount;
	}
	/**
	 * @return the postCommentCount
	 */
	public long getPostCommentCount() {
		return postCommentCount;
	}
	/**
	 * @param postCommentCount the postCommentCount to set
	 */
	public void setPostCommentCount(long postCommentCount) {
		this.postCommentCount = postCommentCount;
	}
	/**
	 * @return the comments
	 */
	public Comments getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(Comments comments) {
		this.comments = comments;
	}
	
	
	
}
