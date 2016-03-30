package com.app.challenge.event.dao;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.jdbc.core.RowMapper;

import com.app.challenge.domain.Player;
import com.app.challenge.event.vo.CommentsResponseVO;
import com.app.challenge.fbutil.Base64;

public class CommentsRowMapper implements RowMapper<CommentsResponseVO> {

	@Override
	public CommentsResponseVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CommentsResponseVO cvo = new CommentsResponseVO();
		

		Blob blob = rs.getBlob("userimage");

		int blobLength = (int) blob.length();
		byte[] blobAsBytes = blob.getBytes(1, blobLength);
		cvo.setUserImage(new String(Base64.encode(blobAsBytes, 0)));
		cvo.setChallengeId(rs.getLong("challengeid"));
		cvo.setComment(rs.getString("comment"));
		cvo.setCommentDate(rs.getTimestamp("createddate"));
		cvo.setUserName(rs.getString("username"));
		
		return cvo;
	}

}
