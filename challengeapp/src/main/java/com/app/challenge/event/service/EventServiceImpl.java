package com.app.challenge.event.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.challenge.event.dao.EventManagerDao;
import com.app.challenge.event.manager.EventManager;
import com.app.challenge.event.vo.AppResponseVO;
import com.app.challenge.event.vo.ChallengeAppResponseVO;
import com.app.challenge.event.vo.ChallengeAppVO;
import com.app.challenge.event.vo.UserAccountVO;

@Service("rivalService")
public class EventServiceImpl implements EventService {

	@Autowired
	private EventManagerDao eventManagerDao;

	@Autowired
	private EventManager eventManager;

	@Override
	public ChallengeAppResponseVO<AppResponseVO> registerNewUser(String token, String email) {
		ChallengeAppResponseVO<AppResponseVO> response = null;
		// validation block for userExists
		if (eventManagerDao.userExists(email)) {

		} else {
			try {

				AppResponseVO registerNewUserId = eventManager.registerNewUserId(token, email);
				response = new ChallengeAppResponseVO<AppResponseVO>(registerNewUserId);
			} catch (SQLException e) {
				response = new ChallengeAppResponseVO<AppResponseVO>(true, "exception in registering userId");
			}
		}

		return response; // Response.ok("registered").build();
	}

	@Override
	public ChallengeAppResponseVO<AppResponseVO> registerNewDevice(UserAccountVO userAccountVO) {
		try {
			eventManager.registerDevice(userAccountVO);
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public ChallengeAppResponseVO<List<ChallengeAppVO>> fetchAllChallengesData(int challengeFrom) {
		ChallengeAppResponseVO<List<ChallengeAppVO>> response = null;
		try {
			response = new ChallengeAppResponseVO<List<ChallengeAppVO>>(eventManager.fetchAllChallenges(challengeFrom));
		} catch (Exception e) {
			response = new ChallengeAppResponseVO<List<ChallengeAppVO>>(true, e.getMessage());
		}
		return response;
	}

	@Override
	public ChallengeAppResponseVO<List<ChallengeAppVO>> fetchMyChallengesData(long userID, int challengeFrom) {
		ChallengeAppResponseVO<List<ChallengeAppVO>> response = null;
		try {
			response = new ChallengeAppResponseVO<List<ChallengeAppVO>>(
					eventManager.fetchMyChallenges(userID, challengeFrom));
		} catch (Exception e) {
			response = new ChallengeAppResponseVO<List<ChallengeAppVO>>(true, e.getMessage());
		}
		return response;
	}

	@Override
	public ChallengeAppResponseVO<List<ChallengeAppVO>> fetchActiveChallengesData(int challengeFrom) {
		ChallengeAppResponseVO<List<ChallengeAppVO>> response = null;
		try {
			response = new ChallengeAppResponseVO<List<ChallengeAppVO>>(
					eventManager.fetchActiveChallenges(challengeFrom));
		} catch (Exception e) {
			response = new ChallengeAppResponseVO<List<ChallengeAppVO>>(true, e.getMessage());
		}
		return response;
	}
}
