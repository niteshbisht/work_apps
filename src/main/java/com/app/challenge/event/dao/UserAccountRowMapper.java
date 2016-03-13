package com.app.challenge.event.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.challenge.domain.UserAccount;
import com.app.challenge.fbutil.Base64;

public class UserAccountRowMapper implements RowMapper<UserAccount>{

	@Override
	public UserAccount mapRow(ResultSet rs, int arg1) throws SQLException {
		UserAccount userAccount = new UserAccount();
		userAccount.setId(rs.getInt("id"));
		userAccount.setUserName(rs.getString("username"));
		userAccount.setCreatedDate(rs.getDate("createddate"));
		byte[] userImage = rs.getBytes("userimage");
		String userImageString = Base64.encodeToString(userImage, 0);
		userAccount.setUserImage(userImageString);
		userAccount.setDeviceId(rs.getString("deviceid"));
		userAccount.setStatus(rs.getString("status"));
		userAccount.setTotalChallenges(rs.getInt("totalchallenges"));
		return userAccount;
	}

	/*
	 id              | int(11)      | NO   | PRI | NULL              | auto_increment              |
| username        | varchar(500) | NO   |     | NULL              |                             |
| useremail       | varchar(500) | NO   | UNI | NULL              |                             |
| devicetype      | varchar(45)  | NO   |     | NULL              |                             |
| deviceid        | varchar(250) | NO   |     | NULL              |                             |
| totalchallenges | int(11)      | YES  |     | NULL              |                             |
| totalwins       | int(11)      | YES  |     | NULL              |                             |
| createddate     | timestamp    | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
| lastupdateddate | timestamp    | YES  |     | NULL              |                             |
| userimage       | blob         | YES  |     | NULL              |                             |
| status
	 */
}
