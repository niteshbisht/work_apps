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
import com.app.challenge.event.vo.ChallengeAppVO;
import com.app.challenge.event.vo.UserAccountVO;

@Component
public class EventManager {

	@Autowired
	EventManagerDao eventManagerDao;

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
	public AppResponseVO registerDevice(UserAccountVO userAccountVO) throws SQLException {
		AppResponseVO response = new AppResponseVO();
		String responseMessage = null;
		UserAccount uac = new UserAccount();
		BeanUtils.copyProperties(userAccountVO, uac);
		try {
			eventManagerDao.registerDevice(uac);
		} catch (Exception e) {
			throw new SQLException();
		}

		response.setResponseMessage(responseMessage);
		return response;
	}

	public List<ChallengeAppVO> fetchAllChallenges(int challengeFrom) {
		List<ChallengeAppVO> responseList = new ArrayList<>();
		ChallengeAppVO responseVO = new ChallengeAppVO();
		responseList.add(responseVO);
		return responseList;
	}

	public List<ChallengeAppVO> fetchMyChallenges(long userID, int challengeFrom) {

		List<ChallengeAppVO> responseList = new ArrayList<>();
		ChallengeAppVO responseVO = new ChallengeAppVO();
		responseList.add(responseVO);
		return responseList;
	}

	public List<ChallengeAppVO> fetchActiveChallenges(int challengeFrom) {
		List<ChallengeAppVO> responseList = new ArrayList<ChallengeAppVO>();
		List<String> playerInfo = new ArrayList<String>();
		ChallengeAppVO responseVO = new ChallengeAppVO();

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
		player1.setFcbkLikeCounts(1234);
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
}
