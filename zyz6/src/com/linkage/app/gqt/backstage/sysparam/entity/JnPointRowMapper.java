package com.linkage.app.gqt.backstage.sysparam.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class JnPointRowMapper implements RowMapper<JnPoint>{

	public JnPoint mapRow(ResultSet rs, int arg1) throws SQLException {
		JnPoint point = new JnPoint();
		point.setId(rs.getLong("ID"));
		point.setParamid(rs.getLong("PARAMID"));
		point.setParamkey(rs.getString("PARAMKEY"));
		point.setPoint(rs.getLong("POINT"));
		return point;
	}

}
