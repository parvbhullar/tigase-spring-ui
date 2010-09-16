package com.linkage.app.gqt.backstage.members.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PostulantHisRowMapper implements RowMapper<PostulantHis>{

	public PostulantHis mapRow(ResultSet rs, int arg1) throws SQLException {
		PostulantHis postulant = new PostulantHis();
		postulant.setId(rs.getLong("ID"));
		postulant.setName(rs.getString("NAME"));
		postulant.setDn(rs.getString("DN"));
		postulant.setCommunityid(rs.getLong("COMMUNITYID"));
		postulant.setCommunityname(rs.getString("COMMUNITYNAME"));
		postulant.setOrgid(rs.getLong("ORGID"));
		postulant.setOrgname(rs.getString("ORGNAME"));
		postulant.setCredcode(rs.getString("CREDCODE"));
		postulant.setOutresion(rs.getString("OUTRESION"));
		postulant.setOutdate(rs.getTimestamp("OUTDATE"));
		return postulant;
	}

}
