package com.linkage.app.gqt.backstage.helping.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linkage.app.gqt.backstage.members.entity.Postulant;

public class QueryMemberRowMapper implements RowMapper<Postulant>{
	
	
	public Postulant mapRow(ResultSet rs, int arg1) throws SQLException {
		Postulant postulant=new Postulant();
		//志愿者ID
		postulant.setId(rs.getLong("ID"));
		
		//志愿者姓名
		postulant.setName(rs.getString("NAME"));
		//志愿者电话
		postulant.setDn(rs.getString("DN"));
		// 注册社区ID
		postulant.setCommunityid(rs.getLong("COMMUNITYID"));
		
		// 注册社区名称
		postulant.setCommunityname(rs.getString("COMMUNITYNAME"));
		return postulant;
	}
	
}