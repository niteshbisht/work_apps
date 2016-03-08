package com.app.challenge.fbutil;

import java.util.HashMap;
import java.util.List;

import com.app.challenge.domain.FBPostData;
import com.restfb.Connection;
import com.restfb.types.Post;
import com.restfb.types.User;

public interface FacebookClientHandler {

	public boolean authenticateUser(String token);

	public User getUserDetails(String token);

	public Connection<User> getListOfUSers(String token);

	public String publishMessageTextToWall(String token, String text, boolean isLink);

	public String publishPhotoToWall(String token, String text,byte[] image, boolean isLink);

	public boolean deletePublishedObject(String token, String postID);

	public FBPostData getPostDataForLikeAndComment(String token, String postID);
}
