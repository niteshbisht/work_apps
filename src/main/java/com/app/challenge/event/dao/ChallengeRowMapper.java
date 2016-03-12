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
		challenge.setEndDate(rs.getTimestamp("endtime"));
		return challenge;
	}
	
}