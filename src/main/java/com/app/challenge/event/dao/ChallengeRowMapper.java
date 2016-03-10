package com.app.challenge.event.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ChallengeRowMapper implements RowMapper<ChallengeDomain>
{

	
	@Override
	public ChallengeDomain mapRow(ResultSet rs, int rowNum) throws SQLException {
		ChallengeDomain challenge = new ChallengeDomain();
		challenge.setChallengeId(rs.getLong(""));
		challenge.setCreatorId(rs.getLong(""));
		challenge.setAcceptorId(rs.getLong(""));
		challenge.setTopic(rs.getString(""));
		challenge.setStatus(rs.getString(""));
		challenge.setFcbkChlngId(rs.getString(""));
		challenge.setChallengeType(rs.getString(""));
		challenge.setEndDate(rs.getTimestamp(""));
		return challenge;
	}
	
}