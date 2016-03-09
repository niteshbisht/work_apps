package com.app.challenge.event.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.challenge.event.dao.EventManagerDao;
import com.app.challenge.event.manager.EventManager;
import com.app.challenge.event.vo.AppResponseVO;
import com.app.challenge.event.vo.ChallengeAppResponseVO;
import com.app.challenge.event.vo.AllChallengeResponseVO;
import com.app.challenge.event.vo.ChallengeVO;
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
	public ChallengeAppResponseVO<List<AllChallengeResponseVO>> fetchAllChallengesData(int challengeFrom) {
		ChallengeAppResponseVO<List<AllChallengeResponseVO>> response = null;
		try {
			response = new ChallengeAppResponseVO<List<AllChallengeResponseVO>>(eventManager.fetchAllChallenges(challengeFrom));
		} catch (Exception e) {
			response = new ChallengeAppResponseVO<List<AllChallengeResponseVO>>(true, e.getMessage());
		}
		return response;
	}

	@Override
	public ChallengeAppResponseVO<List<AllChallengeResponseVO>> fetchMyChallengesData(long userID, int challengeFrom) {
		ChallengeAppResponseVO<List<AllChallengeResponseVO>> response = null;
		try {
			response = new ChallengeAppResponseVO<List<AllChallengeResponseVO>>(
					eventManager.fetchMyChallenges(userID, challengeFrom));
		} catch (Exception e) {
			response = new ChallengeAppResponseVO<List<AllChallengeResponseVO>>(true, e.getMessage());
		}
		return response;
	}

	@Override
	public ChallengeAppResponseVO<List<AllChallengeResponseVO>> fetchActiveChallengesData(int challengeFrom) {
		ChallengeAppResponseVO<List<AllChallengeResponseVO>> response = null;
		try {
			response = new ChallengeAppResponseVO<List<AllChallengeResponseVO>>(
					eventManager.fetchActiveChallenges(challengeFrom));
		} catch (Exception e) {
			response = new ChallengeAppResponseVO<List<AllChallengeResponseVO>>(true, e.getMessage());
		}
		return response;
	}

	@Override
	public ChallengeAppResponseVO<AllChallengeResponseVO> createNewChallenge(ChallengeVO challengeVO) {
		
		ChallengeAppResponseVO<AllChallengeResponseVO> response = null;
		try {
			response = new ChallengeAppResponseVO<AllChallengeResponseVO>(
					eventManager.createNewChallenge(challengeVO,false));
		} catch (Exception e) {
			response = new ChallengeAppResponseVO<AllChallengeResponseVO>(true, e.getMessage());
		}
		return response;
		
	}

	@Override
	public ChallengeAppResponseVO<AppResponseVO> acceptChallenge(ChallengeVO challengeVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ChallengeAppResponseVO<AppResponseVO> updateUserToken(Long uid,String userEmail, String token) {
		ChallengeAppResponseVO<AppResponseVO> response = null;
		try{
			response = new ChallengeAppResponseVO<AppResponseVO>(eventManager.updateUserToken(uid, userEmail, token));
		}catch(SQLException sq){
			response = new ChallengeAppResponseVO<AppResponseVO>(true, sq.getMessage());
			return response;
		}catch (Exception e) {
			response = new ChallengeAppResponseVO<AppResponseVO>(true, e.getMessage());
			return response;
		}
		return response;
	}
}
