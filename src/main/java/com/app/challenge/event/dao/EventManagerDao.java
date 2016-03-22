package com.app.challenge.event.dao;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.challenge.constants.ChallengeConstants;
import com.app.challenge.domain.Player;
import com.app.challenge.domain.UserAccount;
import com.app.challenge.domain.UserToken;
import com.app.challenge.event.vo.ChallengeVO;
import com.app.challenge.event.vo.RegisterResponseVO;
import com.app.challenge.fbutil.Base64;

@Transactional(rollbackFor = SQLException.class)
@Repository("eventManagerDao")
public class EventManagerDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Transactional(rollbackFor = DataAccessException.class)
	public void createEvent(long challengeId) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("challengeId", challengeId);
		String challengeSql = "select * from challenges where challengeid=" + challengeId;
		Map<String, Object> queryForMap = namedParameterJdbcTemplate.queryForMap(challengeSql, params);
		Long creatorUid = ((Integer) queryForMap.get("creatoruid")).longValue();
		String duration = ((String) queryForMap.get("duration"));
		params.put("creatorId", creatorUid);
		params.put("createdDate", new Date());
		params.put("expiryindays", duration);
		SqlParameterSource paramMap = new MapSqlParameterSource(params);
		String sql = "insert into rivals.scheduler(challengeid,creatoruid,createddate,expiryindays) values(:challengeId,:creatorId,:createdDate,:expiryindays)";
		try {
			namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public boolean userExists(String emailId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ChallengeConstants.DB_EMAIL, emailId);
		String sql = "SELECT EXISTS(select * from rivals.user_account where useremail=:EMAIL)";
		Boolean exists = false;
		try {
			exists = namedParameterJdbcTemplate.queryForObject(sql, paramMap, Boolean.class);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exists;
	}

	public UserToken checkUserExists(String emailId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ChallengeConstants.DB_EMAIL, emailId);
		UserToken userToken = namedParameterJdbcTemplate.queryForObject("", paramMap, UserToken.class);
		return userToken;
	}

	@Transactional(rollbackFor = SQLException.class)
	public String createNewUserId(String userEmail, String token) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ChallengeConstants.DB_EMAIL, userEmail);
		paramMap.put(ChallengeConstants.DB_TOKEN, token);
		paramMap.put(ChallengeConstants.DB_DATE, new Date());

		String sql = "insert into rivals.user_tokens(fbtoken,createddate,useremail) values(:TOKEN,:DATE,:EMAIL)";
		try {
			namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (DataAccessException e) {
			throw new SQLException();
		} catch (Exception e) {
			throw new SQLException();
		}
		return "Success";
	}

	@Transactional(rollbackFor = SQLException.class)
	public String updateUserToken(Long uid, String userEmail, String token, String userName) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ChallengeConstants.DB_EMAIL, userEmail);
		paramMap.put(ChallengeConstants.DB_TOKEN, token);
		paramMap.put(ChallengeConstants.DB_DATE, new Date());
		paramMap.put("uid", uid);
		paramMap.put("username", userName);
		String sql = "update rivals.user_tokens set fbtoken=:TOKEN,lastupdateddate=:DATE,useremail=:EMAIL where uid=:uid";
		String sqlUsername = "update rivals.user_account set username=:username where id=:uid";
		try {
			namedParameterJdbcTemplate.update(sql, paramMap);
			namedParameterJdbcTemplate.update(sqlUsername, paramMap);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new SQLException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}
		return "Success";
	}

	@Transactional(rollbackFor = SQLException.class)
	public RegisterResponseVO registerDevice(UserAccount userAccount) throws SQLException {
		RegisterResponseVO response = new RegisterResponseVO();
		byte[] bytes = Base64.decode(userAccount.getUserImage(), 0);// userAccount.getUserImage().getBytes();
		ByteArrayInputStream baos = new ByteArrayInputStream(bytes);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ChallengeConstants.DB_DEVICE_ID, userAccount.getDeviceId());
		paramMap.put(ChallengeConstants.DB_DEVICE_TYPE, userAccount.getDeviceType());
		paramMap.put(ChallengeConstants.DB_PLAYER_IMAGE, baos);
		paramMap.put(ChallengeConstants.DB_USERNAME, userAccount.getUserName());
		paramMap.put(ChallengeConstants.DB_EMAIL, userAccount.getUserEmail());
		paramMap.put(ChallengeConstants.DB_CREATED_DATE, new Date());
		paramMap.put(ChallengeConstants.DB_TOKEN, userAccount.getUserToken().getFcbkToken());
		paramMap.put(ChallengeConstants.DB_DATE, new Date());
		paramMap.put(ChallengeConstants.DB_STATUS, userAccount.getStatus());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "SELECT EXISTS(select * from rivals.user_account where useremail=:EMAIL)";
		Boolean userExists = false;
		try {
			userExists = namedParameterJdbcTemplate.queryForObject(sql, paramMap, Boolean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (userExists.booleanValue()) {
			// update block
			try {
				sql = "update rivals.user_account set deviceid=:DEVICEID, devicetype=:DEVICETYPE, userimage=:PLAYERIMAGE,lastupdateddate=:DATE";
				namedParameterJdbcTemplate.update(sql, paramMap);
				sql = "select id from rivals.user_account where useremail=:EMAIL";
				Long userId = namedParameterJdbcTemplate.queryForObject(sql, paramMap, Long.class);
				paramMap.put("USER_ID", userId);
				sql = "update rivals.user_tokens set fbtoken=:TOKEN, lastupdateddate=:DATE where uid=:USER_ID";
				namedParameterJdbcTemplate.update(sql, paramMap);
				String gatherDataSql = "select * from rivals.user_account where id=:USER_ID";

				Map<String, Object> queryForMap = namedParameterJdbcTemplate.queryForMap(gatherDataSql, paramMap);
				String username = (String) queryForMap.get("username");
				Long totalChallenges = (Long) ((Integer) queryForMap.get("totalchallenges")).longValue();
				Long totalwins = (Long) ((Integer) queryForMap.get("totalwins")).longValue();
				Long totalLoseCount = totalChallenges.longValue() - totalwins.longValue();
				response.setTotalLooseCount(totalLoseCount);
				response.setTotalWinCount(totalwins);
				response.setUserId(userId);
				response.setUserName(username);
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException();
			}
		} else {
			sql = "insert into rivals.user_account(deviceid,devicetype,userimage,username,useremail,createddate,status,totalchallenges,totalwins) values(:DEVICEID,:DEVICETYPE,:PLAYERIMAGE,:USERNAME,:EMAIL,:CREATED_DATE,:STATUS,0,0)";

			try {
				SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
				namedParameterJdbcTemplate.update(sql, paramSource, keyHolder);
				Map<String, Object> keys = keyHolder.getKeys();
				Long id = (Long) keys.get("GENERATED_KEY");
				paramMap.put(ChallengeConstants.DB_UID, id);
				sql = "insert into rivals.user_tokens(uid,fbtoken,createddate,useremail) values(:UID,:TOKEN,:DATE,:EMAIL)";
				namedParameterJdbcTemplate.update(sql, paramMap);
				String gatherDataSql = "select * from rivals.user_account where id=:UID";
				Map<String, Object> queryForMap = namedParameterJdbcTemplate.queryForMap(gatherDataSql, paramMap);
				String username = (String) queryForMap.get("username");
				Long totalChallenges = ((Integer) queryForMap.get("totalchallenges")).longValue();
				Long totalwins = ((Integer) queryForMap.get("totalwins")).longValue();
				Long totalLoseCount = totalChallenges.longValue() - totalwins.longValue();
				response.setTotalLooseCount(totalLoseCount);
				response.setTotalWinCount(totalwins);
				response.setUserId(id);
				response.setUserName(username);
				return response;
			} catch (DataAccessException e) {
				e.printStackTrace();
				throw new SQLException();
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException();
			}
		}
	}

	@Transactional(rollbackFor = SQLException.class)
	public long createNewChallenge(ChallengeVO challengeVO, String fbChallengeID, boolean isAcceptor)
			throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		byte[] bytes = Base64.decode(challengeVO.getPlayerImage(), 0);
		paramMap.put(ChallengeConstants.DB_CREATOR_UID, challengeVO.getUserID());
		/*
		 * if (!isAcceptor) paramMap.put(ChallengeConstants.DB_ACCEPTOR_UID,
		 * null);
		 */
		if (!challengeVO.isOpenChallenge()) {
			paramMap.put(ChallengeConstants.DB_ACCEPTOR_MAIL_ID, challengeVO.getAcceptorEmailId());
		}
		paramMap.put(ChallengeConstants.DB_FB_CHALLENGE_ID, fbChallengeID);
		paramMap.put(ChallengeConstants.DB_START_TIME, new Date());
		paramMap.put(ChallengeConstants.DB_STATUS, challengeVO.getChallengeType());
		paramMap.put(ChallengeConstants.DB_CREATED_DATE, new Date());
		paramMap.put(ChallengeConstants.DB_CHALLENGE_TYPE, challengeVO.getChallengeType());
		paramMap.put(ChallengeConstants.DB_END_TIME, new Date());
		paramMap.put(ChallengeConstants.DB_DURATION, challengeVO.getDuration());
		paramMap.put(ChallengeConstants.DB_TOPIC, challengeVO.getTopic());
		paramMap.put(ChallengeConstants.DB_GAME_TYPE, challengeVO.getGameType());
		String sql = null;
		if (!challengeVO.isOpenChallenge())
			sql = "INSERT INTO rivals.challenges(creatoruid,fbchallengeid,starttime,wstatus,createddate,topic,challengetype,endtime,duration,gametype,acceptorfbmailid) VALUES(:CREATORUID,:FBCHALLENGEID,:STARTTIME,:STATUS,:CREATED_DATE,:TOPIC,:CHALLENGETYPE,:ENDTIME,:DURATION,:GAMETYPE,:ACCEPTORMAILID)";
		else
			sql = "INSERT INTO rivals.challenges(creatoruid,fbchallengeid,starttime,wstatus,createddate,topic,challengetype,endtime,duration,gametype) VALUES(:CREATORUID,:FBCHALLENGEID,:STARTTIME,:STATUS,:CREATED_DATE,:TOPIC,:CHALLENGETYPE,:ENDTIME,:DURATION,:GAMETYPE)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		long challengeID = 0L;

		try {
			SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
			namedParameterJdbcTemplate.update(sql, paramSource, keyHolder);
			Map<String, Object> keys = keyHolder.getKeys();
			challengeID = (Long) keys.get("GENERATED_KEY");
			paramMap.put(ChallengeConstants.DB_CHALLENGE_ID, challengeID);
			paramMap.put(ChallengeConstants.DB_UID, challengeVO.getUserID());
			paramMap.put(ChallengeConstants.DB_WIN_STATUS, "blank");
			paramMap.put(ChallengeConstants.DB_FB_LIKES, 0);
			paramMap.put(ChallengeConstants.DB_PLAYERS_IMAGE, bytes);
			paramMap.put(ChallengeConstants.DB_PLAYER_NAME, challengeVO.getPlayerName());
			paramMap.put(ChallengeConstants.DB_PLAYER_TYPE, challengeVO.getPlayerType());
			StringBuffer insideQuery = new StringBuffer();
			StringBuffer afterQuery = new StringBuffer();
			StringBuffer sb = new StringBuffer();
			sb.append(
					"INSERT INTO rivals.player_challenge_mapping(challengeID,uid,winstatus,fblikes,player_image,playertype,player_name");
			String[] playerInfoAr = challengeVO.getPlayerInfo();
			if (playerInfoAr != null) {
				for (int i = 0; i < playerInfoAr.length; i++) {
					insideQuery.append(",playerinfo" + (i + 1));
					afterQuery.append(", :playerinfo" + (i + 1));
					paramMap.put("playerinfo" + (i + 1), playerInfoAr[i]);
				}

			}
			sb.append(insideQuery);
			sb.append(")  VALUES(:CHALLENGEID,:UID,:WINSTATUS,:FBLIKES,:PLAYER_IMAGE,:PLAYERTYPE,:PLAYER_NAME");
			sb.append(afterQuery);
			sb.append(")");
			sql = sb.toString();

			namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (DataAccessException e) {
			throw new SQLException();
		} catch (Exception e) {
			throw new SQLException();
		}

		return challengeID;
	}

	@Transactional(rollbackFor = SQLException.class)
	public long updateAcceptedChallenge(ChallengeVO challengeVO, String fbChallengeID, boolean isAcceptor)
			throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put(ChallengeConstants.DB_ACCEPTOR_UID, challengeVO.getUserID());

		paramMap.put(ChallengeConstants.DB_FB_CHALLENGE_ID, fbChallengeID);
		paramMap.put(ChallengeConstants.DB_START_TIME, new Date());
		paramMap.put(ChallengeConstants.DB_STATUS, "INPROGRESS");
		paramMap.put(ChallengeConstants.DB_CREATED_DATE, new Date());
		paramMap.put(ChallengeConstants.DB_END_TIME, 0L);
		paramMap.put(ChallengeConstants.DB_CHALLENGE_ID, challengeVO.getChallengeId());
		String sql = "UPDATE rivals.challenges set acceptoruid = :ACCEPTORUID, fbchallengeAcceptorID = :FBCHALLENGEID, starttime = :STARTTIME, wstatus = :STATUS, ENDTIME = :ENDTIME WHERE challengeid = :CHALLENGEID";

		try {
			SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
			namedParameterJdbcTemplate.update(sql, paramSource);
			paramMap.put(ChallengeConstants.DB_CHALLENGE_ID, challengeVO.getChallengeId());
			paramMap.put(ChallengeConstants.DB_UID, challengeVO.getUserID());
			paramMap.put(ChallengeConstants.DB_WIN_STATUS, null);
			paramMap.put(ChallengeConstants.DB_FB_LIKES, 0);
			paramMap.put(ChallengeConstants.DB_PLAYERS_IMAGE, challengeVO.getPlayerImage());
			paramMap.put(ChallengeConstants.DB_PLAYER_NAME, challengeVO.getPlayerName());
			paramMap.put(ChallengeConstants.DB_PLAYER_TYPE, challengeVO.getPlayerType());
			StringBuffer insideQuery = new StringBuffer();
			StringBuffer afterQuery = new StringBuffer();
			StringBuffer sb = new StringBuffer();
			sb.append(
					"INSERT INTO rivals.player_challenge_mapping(challengeID,uid,winstatus,fblikes,player_image,playertype,player_name");
			String[] playerInfoAr = challengeVO.getPlayerInfo();
			if (playerInfoAr != null) {
				for (int i = 0; i < playerInfoAr.length; i++) {
					insideQuery.append(",playerinfo" + (i + 1));
					afterQuery.append(", :playerinfo" + (i + 1));
					paramMap.put("playerinfo" + (i + 1), playerInfoAr[i]);
				}

			}
			sb.append(insideQuery);
			sb.append(")  VALUES(:CHALLENGEID,:UID,:WINSTATUS,:FBLIKES,:PLAYER_IMAGE,:PLAYERTYPE,:PLAYER_NAME");
			sb.append(afterQuery);
			sb.append(")");
			sql = sb.toString();

			namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (DataAccessException e) {
			throw new SQLException();
		} catch (Exception e) {
			throw new SQLException();
		}

		return 0;
	}

	/*
	 * public void updateSchedullerTables(String duration,long challengeid){
	 * namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
	 * }
	 */

	public List<ChallengeDomain> fetchAllChallenges(long challengeID, String status) throws SQLException {
		return fetchAllChallenges(challengeID, status, false);
	}

	public List<ChallengeDomain> fetchAllChallenges(long challengeID, String status, boolean fetchall)
			throws SQLException {
		List<ChallengeDomain> rows = new ArrayList<>();
		String sql = null;

		if (fetchall) {
			sql = "select * from rivals.challenges";
		} else {
			if (challengeID == 0)
				sql = "select * from rivals.challenges where wstatus='OPEN' OR wstatus='FRIEND' OR wstatus = 'INPROGRESS' ORDER BY challengeid DESC LIMIT 20";
			else
				sql = "select * from rivals.challenges where wstatus='OPEN' OR wstatus='FRIEND' OR wstatus = 'INPROGRESS' AND challengeid < "
						+ challengeID + " ORDER BY challengeid DESC LIMIT 20";
		}
		try {
			rows = namedParameterJdbcTemplate.query(sql, new ChallengeRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new SQLException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}
		return rows;
	}

	public List<ChallengeDomain> fetchMyChallenges(long challengeID, long uid) throws SQLException {
		List<ChallengeDomain> rows = new ArrayList<>();
		String sql = null;
		if (challengeID == 0)
			sql = "select * from rivals.challenges where creatoruid=" + uid + " OR acceptoruid=" + uid
					+ "  OR wstatus = 'OPEN' or wstatus = 'open' ORDER BY challengeid DESC LIMIT 20";
		else
			sql = "select * from rivals.challenges where creatoruid=" + uid + " OR acceptoruid=" + uid
					+ " AND challengeid < " + challengeID + " OR wstatus = 'OPEN' or wstatus = 'open' ORDER BY challengeid DESC LIMIT 20";
		try {
			rows = namedParameterJdbcTemplate.query(sql, new ChallengeRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new SQLException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}
		return rows;
	}

	public Map<Long, UserAccount> fetchUserDetails(List<Long> uids) throws SQLException {
		List<UserAccount> rowsUAC = new ArrayList<>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		HashMap<Long, UserAccount> userAccountDetailMap = new HashMap<>();
		paramMap.put("userIdList", uids);
		String sqlForUserId = "select * from rivals.user_account where id in(:userIdList)";
		try {
			rowsUAC = namedParameterJdbcTemplate.query(sqlForUserId, paramMap,new UserAccountRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new SQLException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}
		for (UserAccount userAccount : rowsUAC) {
			long id = userAccount.getId();
			userAccountDetailMap.put(id, userAccount);
		}
		return userAccountDetailMap;
	}

	public List<Player> fetchPlayersOfChallenges(Long challengeid) throws SQLException {
		List<Player> rows = new ArrayList<>();
		String sql = "select * from rivals.player_challenge_mapping where challengeID = " + challengeid;
		try {
			rows = namedParameterJdbcTemplate.query(sql, new PlayeMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new SQLException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		}
		return rows;
	}

	public String getDurationForChallengId(long challengeId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("challengeId", challengeId);
		String sql = "select expiryindays from rivals.scheduller where challengeId = :challengeId";
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, paramMap, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int updateEndDate(long challengeId,Date endDate) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("challengeId", challengeId);
		paramMap.put("endDate", endDate);
		String sql = "update rivals.challenges set endtime=:endDate where challengeId = :challengeId";
		try {
			return namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
