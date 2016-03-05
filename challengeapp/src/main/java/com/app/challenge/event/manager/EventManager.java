package com.app.challenge.event.manager;

import java.sql.SQLException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.challenge.domain.Challenge;
import com.app.challenge.domain.UserAccount;
import com.app.challenge.event.dao.EventManagerDao;
import com.app.challenge.event.vo.AppResponseVO;
import com.app.challenge.event.vo.UserAccountVO;

@Component
public class EventManager {

	@Autowired
	EventManagerDao eventManagerDao;
	
	public void createChallenge(Challenge challenge){
		
	}
	
	@Transactional(rollbackFor=SQLException.class)
	public AppResponseVO registerNewUserId(String token, String email) throws SQLException{
		AppResponseVO response = new AppResponseVO();
		String responseMessage;
		try {
			responseMessage=eventManagerDao.createNewUserId(email, token);
		} catch (SQLException e) {
			throw e;
		}
		response.setResponseMessage(responseMessage);
		return response;
	}
	
	@Transactional(rollbackFor=SQLException.class)
	public AppResponseVO updateToken(String token, String email) throws SQLException{
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
	
	@Transactional(rollbackFor=SQLException.class)
	public AppResponseVO registerDevice(UserAccountVO userAccountVO) throws SQLException{
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
	
	
}
