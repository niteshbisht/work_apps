package com.app.challenge.event.dao;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

@Repository("eventManagerDao")
public class EventManagerDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Transactional
	public void createEvent() {

	}

	public boolean userExists(String emailId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ChallengeConstants.DB_EMAIL, emailId);
		Map<String, Object> queryForMap = namedParameterJdbcTemplate.queryForMap("", paramMap);
		boolean result = null == queryForMap ? true : false;
		return result;
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
	public String updateUserToken(Long uid,String userEmail, String token) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ChallengeConstants.DB_EMAIL, userEmail);
		paramMap.put(ChallengeConstants.DB_TOKEN, token);
		paramMap.put(ChallengeConstants.DB_DATE, new Date());
		paramMap.put("uid", uid);
		String sql = "update rivals.user_tokens set fbtoken=:TOKEN,lastupdteddate=:DATE,useremail=:EMAIL where uid=:uid";
		try {
			namedParameterJdbcTemplate.update(sql, paramMap);
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
	public String registerDevice(UserAccount userAccount) throws SQLException {
		byte[] bytes = userAccount.getUserImage().getBytes();
		ByteArrayInputStream baos = new ByteArrayInputStream(bytes);
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ChallengeConstants.DB_DEVICE_ID, userAccount.getDeviceId());
		paramMap.put(ChallengeConstants.DB_DEVICE_TYPE,
				userAccount.getDeviceType());
		paramMap.put(ChallengeConstants.DB_PLAYER_IMAGE, baos);
		paramMap.put(ChallengeConstants.DB_USERNAME, userAccount.getUserName());
		paramMap.put(ChallengeConstants.DB_EMAIL, userAccount.getUserEmail());
		paramMap.put(ChallengeConstants.DB_CREATED_DATE, new Date());
		paramMap.put(ChallengeConstants.DB_TOKEN, userAccount.getUserToken()
				.getFcbkToken());
		paramMap.put(ChallengeConstants.DB_DATE, new Date());
		paramMap.put(ChallengeConstants.DB_STATUS, userAccount.getStatus());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "SELECT EXISTS(select * from rivals.user_account where useremail=:EMAIL)";
		Boolean userExists = false;
		String uid = null;
		try {
			userExists = namedParameterJdbcTemplate.queryForObject(sql,
					paramMap, Boolean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (userExists.booleanValue()) {
			// update block
			try {
				sql = "update rivals.user_account set deviceid=:DEVICEID, devicetype=:DEVICETYPE, userimage=:PLAYERIMAGE,lastupdateddate=:DATE";
				namedParameterJdbcTemplate.update(sql, paramMap);
				sql = "select id from rivals.user_account where useremail=:EMAIL";
				Long userId = namedParameterJdbcTemplate.queryForObject(sql,
						paramMap, Long.class);
				uid = Long.toString(userId);
				paramMap.put("USER_ID", userId);
				sql = "update rivals.user_tokens set fbtoken=:TOKEN, lastupdteddate=:DATE where uid=:USER_ID";
				namedParameterJdbcTemplate.update(sql, paramMap);
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException();
			}
		} else {
			sql = "insert into rivals.user_account(deviceid,devicetype,userimage,username,useremail,createddate) values(:DEVICEID,:DEVICETYPE,:PLAYERIMAGE,:USERNAME,:EMAIL,:CREATED_DATE)";

			try {
				SqlParameterSource paramSource = new MapSqlParameterSource(
						paramMap);
				namedParameterJdbcTemplate.update(sql, paramSource, keyHolder);
				Map<String, Object> keys = keyHolder.getKeys();
				Long id = (Long) keys.get("GENERATED_KEY");
				uid = id.toString();
				paramMap.put(ChallengeConstants.DB_UID, id);
				sql = "insert into rivals.user_tokens(uid,fbtoken,createddate,useremail) values(:UID,:TOKEN,:DATE,:EMAIL)";
				namedParameterJdbcTemplate.update(sql, paramMap);
			} catch (DataAccessException e) {
				e.printStackTrace();
				throw new SQLException();
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException();
			}
		}
		return uid;
	}

	@Transactional(rollbackFor = SQLException.class)
	public long createNewChallenge(ChallengeVO challengeVO, String fbChallengeID, boolean isAcceptor)
			throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put(ChallengeConstants.DB_CREATOR_UID, challengeVO.getUserID());
		if (!isAcceptor)
			paramMap.put(ChallengeConstants.DB_ACCEPTOR_UID, null);

		paramMap.put(ChallengeConstants.DB_FB_CHALLENGE_ID, fbChallengeID);
		paramMap.put(ChallengeConstants.DB_START_TIME, 0L);
		paramMap.put(ChallengeConstants.DB_STATUS, "created");
		paramMap.put(ChallengeConstants.DB_CREATED_DATE, new Date());
		paramMap.put(ChallengeConstants.DB_CHALLENGE_TYPE, challengeVO.getChallengeType());
		paramMap.put(ChallengeConstants.DB_END_TIME, 0L);
		String sql = "INSERT INTO rivals.challenges(creatoruid,acceptoruid,fbchallengeid,starttime,status,createddate,challengetype,endtime) VALUES(:creatoruid,:acceptoruid,:fbchallengeid,:starttime,:status,:createddate,:challengetype,:endtime)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		try {
			SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
			namedParameterJdbcTemplate.update(sql, paramSource, keyHolder);
			Map<String, Object> keys = keyHolder.getKeys();
			Long id = (Long) keys.get("GENERATED_KEY");
			paramMap.put(ChallengeConstants.DB_CHALLENGE_ID, id);
			paramMap.put(ChallengeConstants.DB_UID, challengeVO.getUserID());
			paramMap.put(ChallengeConstants.DB_WIN_STATUS, null);
			paramMap.put(ChallengeConstants.DB_FB_LIKES, 0);
			paramMap.put(ChallengeConstants.DB_PLAYERS_IMAGE, challengeVO.getPlayerImage());
			paramMap.put(ChallengeConstants.DB_PLAYER_NAME, challengeVO.getPlayerName());
			StringBuffer sb = new StringBuffer();
			sb.append(
					"INSERT INTO rivals.player_challenge_mapping(challengeID,uid,winstatus,fblikes,player_image,playertype,player_name) VALUES(:challengeID,:uid,:winstatus,:fblikes,:player_image,:playertype,:player_name");
			String[] playerInfoAr = challengeVO.getPlayerInfo();
			if (playerInfoAr != null) {
				for (int i = 0; i < playerInfoAr.length; i++) {
					sb.append(", :playerinfo" + i);
					paramMap.put("playerinfo" + i, playerInfoAr[i]);
				}
			}

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

	@Transactional(rollbackFor = SQLException.class)
	public long updateAcceptedChallenge(ChallengeVO challengeVO, String fbChallengeID, boolean isAcceptor)
			throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put(ChallengeConstants.DB_ACCEPTOR_UID, challengeVO.getUserID());

		paramMap.put(ChallengeConstants.DB_FB_CHALLENGE_ID, fbChallengeID);
		paramMap.put(ChallengeConstants.DB_START_TIME, new Date());
		paramMap.put(ChallengeConstants.DB_STATUS, "started");
		paramMap.put(ChallengeConstants.DB_CREATED_DATE, new Date());
		paramMap.put(ChallengeConstants.DB_END_TIME, 0L);
		paramMap.put(ChallengeConstants.DB_CHALLENGE_ID, challengeVO.getChallengeId());
		String sql = "UPDATE table rivals.challenges set acceptoruid = :ACCEPTORUID, starttime = :STARTTIME,status = :STATUS, ENDTIME = :ENDTIME WHERE challengeid = :CHALLENGEID";

		try {
			SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
			namedParameterJdbcTemplate.update(sql, paramSource);
			paramMap.put(ChallengeConstants.DB_CHALLENGE_ID, challengeVO.getChallengeId());
			paramMap.put(ChallengeConstants.DB_UID, challengeVO.getUserID());
			paramMap.put(ChallengeConstants.DB_WIN_STATUS, null);
			paramMap.put(ChallengeConstants.DB_FB_LIKES, 0);
			paramMap.put(ChallengeConstants.DB_PLAYERS_IMAGE, challengeVO.getPlayerImage());
			paramMap.put(ChallengeConstants.DB_PLAYER_NAME, challengeVO.getPlayerName());
			StringBuffer sb = new StringBuffer();
			sb.append(
					"INSERT INTO rivals.player_challenge_mapping(challengeID,uid,winstatus,fblikes,player_image,playertype,player_name) VALUES(:challengeID,:uid,:winstatus,:fblikes,:player_image,:playertype,:player_name");
			String[] playerInfoAr = challengeVO.getPlayerInfo();
			if (playerInfoAr != null) {
				for (int i = 0; i < playerInfoAr.length; i++) {
					sb.append(", :playerinfo" + i);
					paramMap.put("playerinfo" + i, playerInfoAr[i]);
				}
			}

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

	public List<ChallengeDomain> fetchAllChallenges(long challengeID,String status) throws SQLException {
		List<ChallengeDomain> rows = new ArrayList<>();;
	String sql = null;
	if(challengeID==0)
		 sql = "select * from rivals.challenge where status='"+status+"' LIMIT 20 ORDER BY challengeid DESC";
	else
		sql = "select * from rivals.challenge where status='"+status+"' AND challengeid < "+challengeID+"LIMIT 20 ORDER BY challengeid DESC";
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
	

	
	public List<ChallengeDomain> fetchMyChallenges(long challengeID,long uid) throws SQLException {
		List<ChallengeDomain> rows = new ArrayList<>();;
	String sql = null;
	if(challengeID==0)
		 sql = "select * from rivals.challenge where creatoruid="+uid+" OR acceptoruid="+uid+" LIMIT 20 ORDER BY challengeid DESC";
	else
		sql = "select * from rivals.challenge where creatoruid="+uid+" OR acceptoruid="+uid+" AND challengeid < "+challengeID+"LIMIT 20 ORDER BY challengeid DESC";
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
	
	
	
	public List<Player> fetchPlayersOfChallenges(Long challengeid) throws SQLException {
		List<Player> rows = new ArrayList<>();
		String sql = "select * from rivals.player_challenge_mapping where challengeID = "+challengeid;
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
	
	
	
}
