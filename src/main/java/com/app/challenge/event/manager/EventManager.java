package com.app.challenge.event.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.challenge.domain.Challenge;
import com.app.challenge.domain.Player;
import com.app.challenge.domain.UserAccount;
import com.app.challenge.event.dao.ChallengeDomain;
import com.app.challenge.event.dao.EventManagerDao;
import com.app.challenge.event.vo.AllChallengeResponseVO;
import com.app.challenge.event.vo.AppResponseVO;
import com.app.challenge.event.vo.ChallengeVO;
import com.app.challenge.event.vo.UserAccountVO;
import com.app.challenge.fbutil.FacebookClientHandler;
import com.app.quartz.RivalScheduledJob;
import com.app.quartz.RivalsAppScheduler;

@Component
public class EventManager {

	@Autowired
	private EventManagerDao eventManagerDao;

	@Autowired
	private FacebookClientHandler fbClientHandler;

	@Autowired
	private RivalsAppScheduler rivalsAppScheduler;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	RivalScheduledJob rivalScheduledJob;
	
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
	public AppResponseVO updateUserToken(Long uid, String userEmail, String token) throws SQLException {
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
		AllChallengeResponseVO responseVO = new AllChallengeResponseVO();
		List<ChallengeDomain> challenges = new ArrayList<>();
		try {
			challenges = eventManagerDao.fetchAllChallenges(challengeFrom, "COMPLETED");
			List<Player> players = null;
			Player player = null;
			if (challenges != null) {
				Iterator<ChallengeDomain> iter = challenges.iterator();
				ChallengeDomain domain = null;

				while (iter.hasNext()) {
					responseVO = new AllChallengeResponseVO();
					domain = iter.next();
					long id = domain.getChallengeId();
					BeanUtils.copyProperties(domain, responseVO);
					players = eventManagerDao.fetchPlayersOfChallenges(id);

					if (players != null && !players.isEmpty()) {

						Iterator<Player> iters = players.iterator();

						while (iters.hasNext()) {
							player = iters.next();
							responseVO.setPlayer1(player);
						}

					}
					responseList.add(responseVO);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return responseList;
	}

	public List<AllChallengeResponseVO> fetchMyChallenges(long userID, int challengeFrom) {

		List<AllChallengeResponseVO> responseList = new ArrayList<AllChallengeResponseVO>();
		AllChallengeResponseVO responseVO = new AllChallengeResponseVO();
		List<ChallengeDomain> challenges = new ArrayList<>();
		try {
			challenges = eventManagerDao.fetchMyChallenges(challengeFrom, userID);
			List<Player> players = null;
			Player player = null;
			if (challenges != null) {
				Iterator<ChallengeDomain> iter = challenges.iterator();
				ChallengeDomain domain = null;

				while (iter.hasNext()) {
					responseVO = new AllChallengeResponseVO();
					domain = iter.next();
					long id = domain.getChallengeId();
					BeanUtils.copyProperties(domain, responseVO);
					players = eventManagerDao.fetchPlayersOfChallenges(id);

					if (players != null && !players.isEmpty()) {

						Iterator<Player> iters = players.iterator();

						while (iters.hasNext()) {
							player = iters.next();
							responseVO.setPlayer1(player);
						}

					}
					responseList.add(responseVO);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return responseList;
	}

	public List<AllChallengeResponseVO> fetchActiveChallenges(int challengeFrom) {

		List<AllChallengeResponseVO> responseList = new ArrayList<AllChallengeResponseVO>();
		AllChallengeResponseVO responseVO = new AllChallengeResponseVO();
		List<ChallengeDomain> challenges = new ArrayList<>();
		try {
			challenges = eventManagerDao.fetchAllChallenges(challengeFrom, "INPROGRESS");
			List<Player> players = null;
			Player player = null;
			if (challenges != null) {
				Iterator<ChallengeDomain> iter = challenges.iterator();
				ChallengeDomain domain = null;

				while (iter.hasNext()) {
					responseVO = new AllChallengeResponseVO();
					domain = iter.next();
					long id = domain.getChallengeId();
					BeanUtils.copyProperties(domain, responseVO);
					players = eventManagerDao.fetchPlayersOfChallenges(id);

					if (players != null && !players.isEmpty()) {

						Iterator<Player> iters = players.iterator();

						while (iters.hasNext()) {
							player = iters.next();
							responseVO.setPlayer1(player);
						}

					}
					responseList.add(responseVO);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return responseList;
	}

	public AllChallengeResponseVO createNewChallenge(ChallengeVO challengeVO, boolean isAcceptor) {
		AllChallengeResponseVO responseVO = new AllChallengeResponseVO();

		long userId = challengeVO.getUserID();
		String fbUserId = challengeVO.getFbUserID();
		String playerImage = challengeVO.getPlayerImage();
		String fbPostID = null;
		if (playerImage != null && fbUserId != null) {
			fbPostID = fbClientHandler.publishPhotoToWall(fbUserId, "Rivalry Started", playerImage, false);
		}
		long challengeId = 0L;
		try {
			challengeId = eventManagerDao.createNewChallenge(challengeVO, fbPostID, isAcceptor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (challengeId > 0L) {
			String jobName = Long.toString(challengeId);
			String group = "rivalApp";
			String duration = challengeVO.getDuration();
			StringTokenizer strToken = new StringTokenizer(duration, ":");
			int i = 1;
			int days = 0;
			int hours = 0;
			int minutes = 0;
			try {
				while (strToken.hasMoreTokens()) {

					switch (i) {

					case 1:
						days = Integer.parseInt(strToken.nextToken());
						break;
					case 2:
						hours = Integer.parseInt(strToken.nextToken());
						hours = hours < 24 ? hours : hours - (hours - 24);
						break;
					case 3:
						minutes = Integer.parseInt(strToken.nextToken());
						minutes = minutes < 60 ? minutes : minutes
								- (minutes - 60);
						break;
					}
					i++;
				}
			} catch (NumberFormatException ne) {
				ne.printStackTrace();
			}

			Calendar cl = Calendar.getInstance();
			cl.add(Calendar.DATE, days);
			cl.add(Calendar.HOUR, hours);
			cl.add(Calendar.MINUTE, minutes);
			Date when = cl.getTime();
			rivalsAppScheduler.scheduleInvocation(jobName, group, when, rivalScheduledJob);
			responseVO.setChallengeId(challengeId);
			responseVO.setCreatorId(userId);
		}
		return responseVO;
	}

	public AllChallengeResponseVO acceptChallenge(ChallengeVO challengeVO, boolean isAcceptor) {
		AllChallengeResponseVO responseVO = new AllChallengeResponseVO();

		long userId = challengeVO.getUserID();
		String fbUserId = challengeVO.getFbUserID();
		String playerImage = challengeVO.getPlayerImage();

		String fbPostID = null;
		if (playerImage != null && fbUserId != null) {
			fbPostID = fbClientHandler.publishPhotoToWall(fbUserId, challengeVO.getTopic(), playerImage, false);
		}
		try {
			eventManagerDao.updateAcceptedChallenge(challengeVO, fbPostID, isAcceptor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		responseVO.setStatus("INPROGRESS");
		responseVO.setCreatorId(userId);
		return responseVO;
	}

}
