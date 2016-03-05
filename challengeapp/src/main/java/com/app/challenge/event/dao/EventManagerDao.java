package com.app.challenge.event.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.challenge.constants.ChallengeConstants;
import com.app.challenge.domain.UserAccount;
import com.app.challenge.domain.UserToken;

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
		Map<String, Object> queryForMap = namedParameterJdbcTemplate
				.queryForMap("", paramMap);
		boolean result = null == queryForMap ? true : false;
		return result;
	}

	public UserToken checkUserExists(String emailId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ChallengeConstants.DB_EMAIL, emailId);
		UserToken userToken = namedParameterJdbcTemplate.queryForObject("",
				paramMap, UserToken.class);
		return userToken;
	}

	@Transactional(rollbackFor=SQLException.class)
	public String createNewUserId(String userEmail, String token) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ChallengeConstants.DB_EMAIL, userEmail);
		paramMap.put(ChallengeConstants.DB_TOKEN, token);
		paramMap.put(ChallengeConstants.DB_DATE, new Date());
		
		String sql = "insert into rival.user_tokens(fbtoken,createddate,useremail) values(:TOKEN,:DATE,:EMAIL)";
		try {
			namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (DataAccessException e) {
			throw new SQLException();
		} catch (Exception e) {
			throw new SQLException();
		}
		return "Success";
	}
	
	@Transactional(rollbackFor=SQLException.class)
	public String updateUserToken(String userEmail, String token) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ChallengeConstants.DB_EMAIL, userEmail);
		paramMap.put(ChallengeConstants.DB_TOKEN, token);
		paramMap.put(ChallengeConstants.DB_DATE, new Date());
		
		String sql = "update rival.user_tokens set fbtoken=:TOKEN,lastupdteddate=:DATE where useremail=:EMAIL";
		try {
			namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (DataAccessException e) {
			throw new SQLException();
		} catch (Exception e) {
			throw new SQLException();
		}
		return "Success";
	}
	
	@Transactional(rollbackFor=SQLException.class)
	public String registerDevice(UserAccount userAccount) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		/*paramMap.put(ChallengeConstants.DB_EMAIL, userEmail);
		paramMap.put(ChallengeConstants.DB_TOKEN, token);
		paramMap.put(ChallengeConstants.DB_DATE, new Date());*/
		paramMap.put(ChallengeConstants.DB_USERNAME, userAccount.getUserName());
		
		String sql = "update rival.user_tokens set fbtoken=:TOKEN,lastupdteddate=:DATE where useremail=:EMAIL";
		try {
			namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (DataAccessException e) {
			throw new SQLException();
		} catch (Exception e) {
			throw new SQLException();
		}
		return "Success";
	}
}
