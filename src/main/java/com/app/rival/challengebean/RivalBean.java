package com.app.rival.challengebean;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class RivalBean implements Serializable {

	/**
	 * @param namedParameterJdbcTemplate
	 */
	public RivalBean(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 3918644339511054084L;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void execute(){
		
	}
}
