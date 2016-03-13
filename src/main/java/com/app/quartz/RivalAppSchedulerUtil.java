package com.app.quartz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.app.challenge.fbutil.FacebookClientHandler;

public class RivalAppSchedulerUtil {
	
	
	public static void computeStatsForChallenge(long challengeId, NamedParameterJdbcTemplate namedParameterJdbcTemplate,FacebookClientHandler facebookClientHandler){
		String sql = "select * from rivals.user_account";
		try{
			SqlParameterSource paramMap = new MapSqlParameterSource();
			List<Map<String, Object>> queryForList = namedParameterJdbcTemplate.queryForList(sql, paramMap);
			for (Map<String, Object> map : queryForList) {
				System.out.println(map);
			}
		}catch(Exception e){
			
		}
	}

}
