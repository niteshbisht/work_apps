package com.app.challenge.event.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("eventManagerDao")
public class EventManagerDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	
	@Transactional
	public void createEvent(){
		
	}
}
