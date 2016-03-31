package com.app.challenge.event.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ChallengeRowMapper implements RowMapper<ChallengeDomain>
{

	@Override
	public ChallengeDomain mapRow(ResultSet rs, int rowNum) throws SQLException {
		ChallengeDomain challenge = new ChallengeDomain();
		challenge.setChallengeId(rs.getLong("challengeid"));
		challenge.setCreatorId(rs.getLong("creatoruid"));
		challenge.setAcceptorId(rs.getLong("acceptoruid"));
		challenge.setTopic(rs.getString("topic"));
		challenge.setStatus(rs.getString("wstatus"));
		challenge.setFcbkChlngId(rs.getString("fbchallengeid"));
		challenge.setChallengeType(rs.getString("challengetype"));
		challenge.setGameType(rs.getString("gametype")==null?"":rs.getString("gametype"));
		challenge.setEndDate(rs.getTimestamp("endtime"));
		challenge.setFbUserName(rs.getString("fbusername"));
		challenge.setAcceptorName(rs.getString("acceptorname"));
		challenge.setCreatorName(rs.getString("creatorname"));
		challenge.setWinnerId(rs.getInt("winnerId"));
		return challenge;
	}
	
}