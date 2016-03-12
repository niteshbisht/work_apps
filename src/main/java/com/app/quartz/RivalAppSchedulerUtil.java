package com.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class RivalAppSchedulerUtil {
	
	@Autowired
	private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	public static void computeStatsForChallenge(long challengeId){
		
	}

}
