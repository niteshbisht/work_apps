/**
   * Entry point. You must provide a single argument on the command line: a valid Graph API access token. In order for
   * publishing to succeed, you must use an access token for an application that has been granted stream_publish and
   * create_event rights.
   * 
   */
package com.app.challenge.fbutil;

import static java.lang.System.out;

import org.springframework.stereotype.Component;

import com.app.challenge.constants.ChallengeConstants;
import com.restfb.BinaryAttachment;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
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
	/* (non-Javadoc)
	 * @see com.app.challenge.fbutil.FacebookClientHandler#authenticateUser(java.lang.String)
	 */
	@Override
	public boolean authenticateUser(String token) {
		facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);
		return false;
	}

	/* (non-Javadoc)
	 * @see com.app.challenge.fbutil.FacebookClientHandler#getUserDetails(java.lang.String)
	 */
	@Override
	public User getUserDetails(String token) {
		facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);
		User user = facebookClient.fetchObject(ChallengeConstants.FB_USER, User.class);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.app.challenge.fbutil.FacebookClientHandler#getListOfUSers(java.lang.String)
	 */
	@Override
	public Connection<User> getListOfUSers(String token) {
		facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);
		Connection<User> myFriends = facebookClient.fetchConnection(ChallengeConstants.FB_USER+ChallengeConstants.FB_POST_SEPARATOR+ChallengeConstants.FB_FRIENDS, User.class);
		return myFriends;
	}

	/* (non-Javadoc)
	 * @see com.app.challenge.fbutil.FacebookClientHandler#getPostDataForLikeAndComment(java.lang.String)
	 */
	@Override
	public Post getPostDataForLikeAndComment(String token,String postID) {
		facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);
		
		// Some Post from the GoT Fanpage with likes and comments total count
		Post post = facebookClient.fetchObject(postID,
		  Post.class,
		  Parameter.with("fields", "from,to,likes.summary(true),comments.summary(true)"));

		out.println("Likes count: " + post.getLikesCount());
		out.println("Likes count (from Likes): " + post.getLikes().getTotalCount());
		out.println("Comments count: " + post.getComments().getTotalCount());
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.app.challenge.fbutil.FacebookClientHandler#publishMessageTextToWall(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public String publishMessageTextToWall(String token, String text, boolean isLink) {
		facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);
		out.println("* Feed publishing *");

		publishResponse =
	        facebookClient.publish(ChallengeConstants.FB_USER+ChallengeConstants.FB_POST_SEPARATOR+ChallengeConstants.FB_POST, FacebookType.class, Parameter.with("link",text));

	    out.println("Published message ID: " + publishResponse.getId());
	    return publishResponse.getId();
	}

	/* (non-Javadoc)
	 * @see com.app.challenge.fbutil.FacebookClientHandler#publishPhotoToWall(java.lang.String, java.lang.String, boolean)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public String publishPhotoToWall(String token, String text, boolean isLink) {
		facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);
		//BinaryAttachment.with("test.png", imageAsBytes, "image/png"),
		publishResponse = facebookClient.publish(ChallengeConstants.FB_USER+ChallengeConstants.FB_POST_SEPARATOR+ChallengeConstants.FB_PHOTO, FacebookType.class,
			      BinaryAttachment.with("rivals.png", getClass().getResourceAsStream("/rivals.png")),
			      Parameter.with("message", "Rivalry Started"));

			    out.println("Published photo ID: " + publishResponse.getId());
			    return publishResponse.getId();

	}

	/* (non-Javadoc)
	 * @see com.app.challenge.fbutil.FacebookClientHandler#deletePublishedObject(java.lang.String)
	 */
	@Override
	public boolean deletePublishedObject(String token,String postID) {
		facebookClient = new DefaultFacebookClient(token, Version.VERSION_2_5);
		out.println("* Object deletion *");
		return facebookClient.deleteObject(postID);
	}

}
