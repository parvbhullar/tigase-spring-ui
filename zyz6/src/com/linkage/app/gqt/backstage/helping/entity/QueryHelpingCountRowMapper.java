package com.linkage.app.gqt.backstage.helping.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linkage.app.gqt.backstage.org.entitys.Org;

public class QueryHelpingCountRowMapper implements RowMapper<Org>{
	

	public Org mapRow(ResultSet rs, int arg1) throws SQLException {
		Org org=new Org();
		org.setOrgId(rs.getLong("ORGID"));
		org.setOrgName(rs.getString("ORGNAME"));
		org.setLevel(rs.getInt("lv"));
		org.setNum(rs.getInt("NUM"));
		org.setParentOrgId(rs.getLong("PARENTORGID"));
		return org;
	}
}
