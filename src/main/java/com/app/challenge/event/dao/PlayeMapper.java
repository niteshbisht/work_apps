package com.app.challenge.event.dao;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.jdbc.core.RowMapper;

import com.app.challenge.domain.Player;
import com.app.challenge.fbutil.Base64;

public class PlayeMapper implements RowMapper<Player> {

	@Override
	public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
		Player player = new Player();
		player.setPlayerId(rs.getLong("playerid"));

		Blob blob = rs.getBlob("player_image");

		int blobLength = (int) blob.length();
		byte[] blobAsBytes = blob.getBytes(1, blobLength);
		player.setPlayerImage(new String(Base64.encode(blobAsBytes, 0)));
		String[] playerInfo = new String[10];
		String playerinfo = null;

		for (int i = 0; i < 10; i++) {
			playerinfo = rs.getString("playerinfo" + (i + 1));
			if (playerinfo == null) {
				playerInfo[i] = "";
			} else {
				playerInfo[i] = playerinfo;
			}
		}
		player.setPlayerInfo(Arrays.asList(playerInfo));
		player.setFbComments(new ArrayList<String>());
		player.setFbLikeCounts(rs.getLong("fblikes"));
		player.setUserId(rs.getLong("uid"));
		player.setWinStatus(rs.getString("winstatus"));
		player.setPlayerName(rs.getString("player_name"));
		return player;
	}

}
