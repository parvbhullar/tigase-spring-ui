package com.linkage.app.gqt.backstage.log.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LogRowMapper implements RowMapper<ZyzLog>{

	public ZyzLog mapRow(ResultSet rs, int arg1) throws SQLException {
		ZyzLog log = new ZyzLog();
		log.setLogid(rs.getLong("LID"));
		log.setTypeid(rs.getLong("TID"));
		log.setUserid(rs.getLong("USERID"));
		log.setTname(rs.getString("name"));
		log.setDate(rs.getTimestamp("LOGDATE"));
		log.setTdesc(rs.getString("DESCRIPTION"));
		log.setLogip(rs.getString("LOGIP"));
		log.setLoginname(rs.getString("LOGINNAME"));
		log.setNickname(rs.getString("NICKNAME"));
		return log;
	}

}
