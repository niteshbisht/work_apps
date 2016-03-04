package com.app.challenge.event.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class EventManagerDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	
	public void createEvent(){
		
	}
}
