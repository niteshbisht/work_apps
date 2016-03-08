/**
   * Entry point. You must provide a single argument on the command line: a valid Graph API access token. In order for
   * publishing to succeed, you must use an access token for an application that has been granted stream_publish and
   * create_event rights.
   * 
   */
package com.app.challenge.fbutil;

import org.springframework.stereotype.Component;

import com.app.challenge.constants.ChallengeConstants;
import com.app.challenge.domain.FBPostData;
import com.restfb.BinaryAttachment;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.User;

/**
 * @author Vikash Sharma
 *
 */
@Component
public class FBClientHandlerImpl implements FacebookClientHandler {

	FacebookClient facebookClient = null;
	FacebookType publishResponse = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.app.challenge.fbutil.FacebookClientHandler#authenticateUser(java.lang
	 * .String)
	 */
	@Override
	public boolean authenticateUser(String token) {
		try {
			facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);
		} catch (FacebookOAuthException foe) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.app.challenge.fbutil.FacebookClientHandler#getUserDetails(java.lang.
	 * String)
	 */
	@Override
	public User getUserDetails(String token) {
		try {
			facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);
			User user = facebookClient.fetchObject(ChallengeConstants.FB_USER, User.class);
			return user;
		} catch (Exception e) {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.app.challenge.fbutil.FacebookClientHandler#getListOfUSers(java.lang.
	 * String)
	 */
	@Override
	public Connection<User> getListOfUSers(String token) {
		try {
			facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);
			Connection<User> myFriends = facebookClient.fetchConnection(
					ChallengeConstants.FB_USER + ChallengeConstants.FB_POST_SEPARATOR + ChallengeConstants.FB_FRIENDS,
					User.class);
			return myFriends;
		} catch (Exception e) {

		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.app.challenge.fbutil.FacebookClientHandler#
	 * getPostDataForLikeAndComment(java.lang.String)
	 */
	@Override
	public FBPostData getPostDataForLikeAndComment(String token, String postID) {
		FBPostData postData = new FBPostData();

		try {
			facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);

			// Some Post from the GoT Fanpage with likes and comments total
			// count
			Post post = facebookClient.fetchObject(postID, Post.class,
					Parameter.with(ChallengeConstants.FB_FIELDS, "from,to,likes.summary(true),comments.summary(true)"));

			postData.setPostLikeCount(post.getLikesCount());

			postData.setPostCommentCount(post.getComments().getTotalCount());

			postData.setComments(post.getComments());
		} catch (Exception e) {

		}

		return postData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.app.challenge.fbutil.FacebookClientHandler#publishMessageTextToWall(
	 * java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public String publishMessageTextToWall(String token, String text, boolean isLink) {
		try {
			facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);

			publishResponse = facebookClient.publish(
					ChallengeConstants.FB_USER + ChallengeConstants.FB_POST_SEPARATOR + ChallengeConstants.FB_POST,
					FacebookType.class, Parameter.with("link", text));

			return publishResponse.getId();
		} catch (Exception e) {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.app.challenge.fbutil.FacebookClientHandler#publishPhotoToWall(java.
	 * lang.String, java.lang.String, boolean)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public String publishPhotoToWall(String token, String text,byte[] image, boolean isLink) {
		try {
			facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);
			// BinaryAttachment.with("test.png", imageAsBytes, "image/png"),
			publishResponse = facebookClient.publish(
					ChallengeConstants.FB_USER + ChallengeConstants.FB_POST_SEPARATOR + ChallengeConstants.FB_PHOTO,
					FacebookType.class,
					BinaryAttachment.with("rivals.png", getClass().getResourceAsStream("/rivals.png")),
					Parameter.with(ChallengeConstants.FB_MSG, "Rivalry Started"));

			return publishResponse.getId();
		} catch (Exception e) {

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.app.challenge.fbutil.FacebookClientHandler#deletePublishedObject(java
	 * .lang.String)
	 */
	@Override
	public boolean deletePublishedObject(String token, String postID) {
		try {
			facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);
			return facebookClient.deleteObject(postID);
		} catch (Exception e) {

		}
		return false;
	}

}
