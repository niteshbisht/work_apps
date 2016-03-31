package com.app.challenge.event.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.app.challenge.event.vo.AllChallengeResponseVO;
import com.app.challenge.event.vo.AppResponseVO;
import com.app.challenge.event.vo.ChallengeAppResponseVO;
import com.app.challenge.event.vo.ChallengeVO;
import com.app.challenge.event.vo.CommentVO;
import com.app.challenge.event.vo.CommentsResponseVO;
import com.app.challenge.event.vo.LikeResponseVO;
import com.app.challenge.event.vo.RegisterResponseVO;
import com.app.challenge.event.vo.UserAccountVO;

@Path("/rivalService")
public interface EventService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/registerNewUser")
	public ChallengeAppResponseVO<AppResponseVO> registerNewUser(String token, String email);

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/registerNewDevice")
	ChallengeAppResponseVO<RegisterResponseVO> registerNewDevice(UserAccountVO userAccountVO);

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createNewChallenge")
	ChallengeAppResponseVO<AllChallengeResponseVO> createNewChallenge(ChallengeVO challengeVO);
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/acceptChallenge")
	ChallengeAppResponseVO<AllChallengeResponseVO> acceptChallenge(ChallengeVO challengeVO);
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/fetchAllChallenges")
	public ChallengeAppResponseVO<List<AllChallengeResponseVO>> fetchAllChallengesData(
			@QueryParam("challengeFrom") int challengeFrom);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/fetchMyChallenges")
	public ChallengeAppResponseVO<List<AllChallengeResponseVO>> fetchMyChallengesData(@QueryParam("userID") long userID,
			@QueryParam("challengeFrom") int challengeFrom);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/fetchActiveChallenges")
	public ChallengeAppResponseVO<List<AllChallengeResponseVO>> fetchActiveChallengesData(
			@QueryParam("challengeFrom") int challengeFrom);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateUserToken")
	public ChallengeAppResponseVO<AppResponseVO> updateUserToken(@QueryParam("uid") long uid,
			@QueryParam("userEmail") String userEmail,@QueryParam("token") String token,@QueryParam("userName") String userName);

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/submitComment")
	public ChallengeAppResponseVO<String> submitComment(CommentVO commentVO);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/submitLike")
	ChallengeAppResponseVO<LikeResponseVO> submitLike(@QueryParam("playerId") int playerId,@QueryParam("userId") int userId,@QueryParam("challengeId") int challengeId);
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/fetchComments")
	ChallengeAppResponseVO<List<CommentsResponseVO>> fetchComments(@QueryParam("userId") long userId,@QueryParam("challengeId") long challengeId);
	
	
	
}
