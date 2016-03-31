package com.app.quartz;

import java.util.HashMap;
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
		HashMap<String, Object> params = new HashMap<>();
		params.put("challengeid", challengeId);
		String updateChallengeStatus ="update rivals.challenges set wstatus='COMPLETED' where challengeid=:challengeid";
		long fblikesCount = 0;
		long msgCounts = 0;
		try{
			String sql2 ="";
			SqlParameterSource paramMap = new MapSqlParameterSource(params);
			namedParameterJdbcTemplate.update(updateChallengeStatus, params);
			String sqlLikeCreatorCount = "select count(*) from rivals.likes where playerId=(select playerId from rivals.player_challenge_mapping pcm where pcm.uid=(select rc.creatoruid from rivals.challenges rc where rc.challengeid=:challengeid) and pcm.challengeId=:challengeid)";
			String sqlLikeForAcceptorCount = "select count(*) from rivals.likes where playerId=(select playerId from rivals.player_challenge_mapping pcm where pcm.uid=(select rc.acceptoruid from rivals.challenges rc where rc.challengeid=:challengeid) and pcm.challengeId=:challengeid)";
			long creatorLikeCount = namedParameterJdbcTemplate.queryForObject(sqlLikeCreatorCount, paramMap, Integer.class);
			long acceptorLikeCount = namedParameterJdbcTemplate.queryForObject(sqlLikeForAcceptorCount, paramMap, Integer.class);
			
			if(creatorLikeCount>acceptorLikeCount){
				sql = "update rivals.challenges set winnerId=creatoruid where challengeid=:challengeid";
				sql2 = "update rivals.player_challenge_mapping pcm set winstatus = 'WON' where pcm.uid=(select rc.creatoruid from rivals.challenges rc where rc.challengeid=:challengeid) and pcm.challengeId=:challengeid";
			}else if(creatorLikeCount<acceptorLikeCount){
				sql = "update rivals.challenges set winnerId=acceptoruid where challengeid=:challengeid";
				sql2 = "update rivals.player_challenge_mapping pcm set winstatus = 'WON'  where pcm.uid=(select rc.acceptoruid from rivals.challenges rc where rc.challengeid=:challengeid) and pcm.challengeId=:challengeid";
			}else{
				sql = "update rivals.challenges set winnerId=0 where challengeid=:challengeid";
			}
			
			namedParameterJdbcTemplate.update(sql, paramMap);
			if(!sql2.isEmpty()){
				namedParameterJdbcTemplate.update(sql2, paramMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
