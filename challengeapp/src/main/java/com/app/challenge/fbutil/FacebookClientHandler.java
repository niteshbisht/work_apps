package com.app.challenge.fbutil;

import com.restfb.Connection;
import com.restfb.types.Post;
import com.restfb.types.User;

public interface FacebookClientHandler {

	public boolean authenticateUser(String token);

	public User getUserDetails(String token);

	public Connection<User> getListOfUSers(String token);

	public String publishMessageTextToWall(String token, String text, boolean isLink);

	public String publishPhotoToWall(String token, String text, boolean isLink);

	public boolean deletePublishedObject(String token, String postID);

	public Post getPostDataForLikeAndComment(String token, String postID);
}
