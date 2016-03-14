package com.app.challenge.event.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.challenge.event.dao.EventManagerDao;
import com.app.challenge.event.manager.EventManager;
import com.app.challenge.event.vo.AllChallengeResponseVO;
import com.app.challenge.event.vo.AppResponseVO;
import com.app.challenge.event.vo.ChallengeAppResponseVO;
import com.app.challenge.event.vo.ChallengeVO;
import com.app.challenge.event.vo.RegisterResponseVO;
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
	public ChallengeAppResponseVO<RegisterResponseVO> registerNewDevice(UserAccountVO userAccountVO) {
		RegisterResponseVO registerDevice = null;
		ChallengeAppResponseVO< RegisterResponseVO> response;
		try {
			registerDevice = eventManager.registerDevice(userAccountVO);
		} catch (Exception e) {
			response = new ChallengeAppResponseVO<>(true, e.getMessage());
		}
		response = new ChallengeAppResponseVO<RegisterResponseVO>(registerDevice);
		return response;
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
	public ChallengeAppResponseVO<AllChallengeResponseVO> acceptChallenge(ChallengeVO challengeVO) {
		ChallengeAppResponseVO<AllChallengeResponseVO> response = null;
		try {
			response = new ChallengeAppResponseVO<AllChallengeResponseVO>(
					eventManager.acceptChallenge(challengeVO,false));
		} catch (Exception e) {
			response = new ChallengeAppResponseVO<AllChallengeResponseVO>(true, e.getMessage());
		}
		return response;
	}
	
	@Override
	public ChallengeAppResponseVO<AppResponseVO> updateUserToken(long uid,String userEmail, String token,String userName) {
		ChallengeAppResponseVO<AppResponseVO> response = null;
		try{
			response = new ChallengeAppResponseVO<AppResponseVO>(eventManager.updateUserToken(uid, userEmail, token,userName));
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
