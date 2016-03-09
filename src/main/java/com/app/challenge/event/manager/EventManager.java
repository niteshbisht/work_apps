package com.app.challenge.event.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.challenge.domain.Challenge;
import com.app.challenge.domain.Player;
import com.app.challenge.domain.UserAccount;
import com.app.challenge.event.dao.EventManagerDao;
import com.app.challenge.event.vo.AppResponseVO;
import com.app.challenge.event.vo.ChallengeVO;
import com.app.challenge.event.vo.AllChallengeResponseVO;
import com.app.challenge.event.vo.UserAccountVO;
import com.app.challenge.fbutil.FBClientHandlerImpl;
import com.app.challenge.fbutil.FacebookClientHandler;

@Component
public class EventManager {

	@Autowired
	EventManagerDao eventManagerDao;

	@Autowired
	FacebookClientHandler fbClientHandler;

	public void createChallenge(Challenge challenge) {

	}

	@Transactional(rollbackFor = SQLException.class)
	public AppResponseVO registerNewUserId(String token, String email) throws SQLException {
		AppResponseVO response = new AppResponseVO();
		String responseMessage;
		try {
			responseMessage = eventManagerDao.createNewUserId(email, token);
		} catch (SQLException e) {
			throw e;
		}
		response.setResponseMessage(responseMessage);
		return response;
	}

	@Transactional(rollbackFor = SQLException.class)
	public AppResponseVO updateToken(String token, String email) throws SQLException {
		AppResponseVO response = new AppResponseVO();
		String responseMessage;
		try {
			responseMessage = eventManagerDao.createNewUserId(email, token);
		} catch (SQLException e) {
			throw e;
		}
		response.setResponseMessage(responseMessage);
		return response;
	}

	@Transactional(rollbackFor = SQLException.class)
	public AppResponseVO updateUserToken(Long uid,String userEmail, String token) throws SQLException {
		AppResponseVO response = new AppResponseVO();
		try {
			eventManagerDao.updateUserToken(uid, userEmail, token);
		} catch (Exception e) {
			throw new SQLException();
		}

		response.setResponseMessage("success");
		return response;
	}
	
	
	@Transactional(rollbackFor = SQLException.class)
	public AppResponseVO registerDevice(UserAccountVO userAccountVO) throws SQLException {
		AppResponseVO response = new AppResponseVO();
		UserAccount uac = new UserAccount();
		BeanUtils.copyProperties(userAccountVO, uac);
		String uid;
		try {
			uid = eventManagerDao.registerDevice(uac);
		} catch (Exception e) {
			throw new SQLException();
		}

		response.setResponseMessage("success");
		response.setUserID(Long.parseLong(uid));
		return response;
	}

	public List<AllChallengeResponseVO> fetchAllChallenges(int challengeFrom) {
		List<AllChallengeResponseVO> responseList = new ArrayList<AllChallengeResponseVO>();
		List<String> playerInfo = new ArrayList<String>();
		AllChallengeResponseVO responseVO = new AllChallengeResponseVO();
		List<String> comments = new ArrayList<String>();
		comments.add("no commments 1");
		comments.add("no commments 2");
		responseVO.setAcceptorId(123);
		responseVO.setChallengeId(111111);
		responseVO.setChallengeType("cricket");
		responseVO.setCreatorId(321);
		responseVO.setEndDate(new Date());
		responseVO.setStartDate(new Date());
		responseVO.setTopic("Who is the best cricketer of India?");
		responseVO.setStatus("Completed");
		responseVO.setFcbkChlngId("09909090909");
		Player player1 = new Player();
		player1.setChallengeId(111111);
		player1.setFbLikeCounts(1234);
		player1.setFbComments(comments);
		player1.setPlayerId(1);
		player1.setPlayerImage(null);
		playerInfo.add("nothing");
		player1.setPlayerInfo(playerInfo);
		player1.setWinStatus("1");
		player1.setUserId(123);
		player1.setPlayerType("cricketer");
		responseVO.setPlayer1(player1);

		Player player2 = new Player();
		player2.setChallengeId(111111);
		player2.setFbLikeCounts(1234);
		player2.setFbComments(comments);
		player2.setPlayerId(2);
		player2.setPlayerImage(null);
		playerInfo.add("nothing");
		player2.setPlayerInfo(playerInfo);
		player2.setWinStatus("0");
		player2.setUserId(123);
		player2.setPlayerType("cricketer");
		responseVO.setPlayer1(player1);
		responseVO.setPlayer2(player2);

		responseList.add(responseVO);
		return responseList;
	}

	public List<AllChallengeResponseVO> fetchMyChallenges(long userID, int challengeFrom) {

		List<AllChallengeResponseVO> responseList = new ArrayList<AllChallengeResponseVO>();
		List<String> playerInfo = new ArrayList<String>();
		AllChallengeResponseVO responseVO = new AllChallengeResponseVO();
		List<String> comments = new ArrayList<String>();
		comments.add("no commments 1");
		comments.add("no commments 2");
		responseVO.setAcceptorId(123);
		responseVO.setChallengeId(111111);
		responseVO.setChallengeType("cricket");
		responseVO.setCreatorId(321);
		responseVO.setEndDate(new Date());
		responseVO.setStartDate(new Date());
		responseVO.setTopic("Who is the best cricketer of India?");
		responseVO.setStatus("Open");
		responseVO.setFcbkChlngId("09909090909");
		Player player1 = new Player();
		player1.setChallengeId(111111);
		player1.setFbLikeCounts(1234);
		player1.setFbComments(comments);
		player1.setPlayerId(1);
		player1.setPlayerImage(null);
		playerInfo.add("nothing");
		player1.setPlayerInfo(playerInfo);
		player1.setWinStatus("-1");
		player1.setUserId(123);
		player1.setPlayerType("cricketer");
		responseVO.setPlayer1(player1);
		responseVO.setPlayer2(player1);

		responseList.add(responseVO);
		return responseList;
	}

	public List<AllChallengeResponseVO> fetchActiveChallenges(int challengeFrom) {
		List<AllChallengeResponseVO> responseList = new ArrayList<AllChallengeResponseVO>();
		List<String> playerInfo = new ArrayList<String>();
		AllChallengeResponseVO responseVO = new AllChallengeResponseVO();
		List<String> comments = new ArrayList<String>();
		comments.add("no commments 1");
		comments.add("no commments 2");
		responseVO.setAcceptorId(123);
		responseVO.setChallengeId(111111);
		responseVO.setChallengeType("cricket");
		responseVO.setCreatorId(321);
		responseVO.setEndDate(new Date());
		responseVO.setStartDate(new Date());
		responseVO.setTopic("Who is the best cricketer of India?");
		responseVO.setStatus("In Progress");
		responseVO.setFcbkChlngId("09909090909");
		Player player1 = new Player();
		player1.setChallengeId(111111);
		player1.setFbLikeCounts(1234);
		player1.setFbComments(comments);
		player1.setPlayerId(1);
		player1.setPlayerImage(null);
		playerInfo.add("nothing");
		player1.setPlayerInfo(playerInfo);
		player1.setWinStatus("1");
		player1.setUserId(123);
		player1.setPlayerType("cricketer");
		responseVO.setPlayer1(player1);
		responseVO.setPlayer2(player1);

		responseList.add(responseVO);
		return responseList;
	}

	public AllChallengeResponseVO createNewChallenge(ChallengeVO challengeVO, boolean isAcceptor) {
		AllChallengeResponseVO responseVO = new AllChallengeResponseVO();

		long userId = challengeVO.getUserID();
		String fbUserId = challengeVO.getFbUserID();
		byte[] playerImage = challengeVO.getPlayerImage();
		String fbPostID = null;
		if (playerImage != null && fbUserId != null) {
			// fbPostID = fbClientHandler.publishPhotoToWall(fbUserId,
			// "",playerImage, false);

		}

		try {
			eventManagerDao.createNewChallenge(challengeVO, fbPostID, isAcceptor);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return responseVO;
	}

	public AllChallengeResponseVO acceptChallenge(ChallengeVO challengeVO, boolean isAcceptor) {
		AllChallengeResponseVO responseVO = new AllChallengeResponseVO();

		long userId = challengeVO.getUserID();
		String fbUserId = challengeVO.getFbUserID();
		byte[] playerImage = challengeVO.getPlayerImage();
		String fbPostID = null;
		if (playerImage != null && fbUserId != null) {
			// fbPostID = fbClientHandler.publishPhotoToWall(fbUserId,
			// "",playerImage, false);

		}

		try {
			eventManagerDao.updateAcceptedChallenge(challengeVO, fbPostID, isAcceptor);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return responseVO;
	}

}
