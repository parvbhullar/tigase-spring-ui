package com.linkage.app.gqt.backstage.active.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linkage.app.gqt.backstage.members.entity.Postulant;

public class QueryPostulantRowMapper implements RowMapper<Postulant>{
	public Postulant mapRow(ResultSet rs, int arg1) throws SQLException {
		Postulant postulant=new Postulant();
		// 志愿者ID
		postulant.setId(rs.getLong("ID"));
		// 志愿者姓名
		postulant.setName(rs.getString("NAME"));
		// 手机号码
		postulant.setDn(rs.getString("DN"));
		// 社区ID
		postulant.setCommunityid(rs.getLong("communityid"));
		// 社区名称
		postulant.setCommunityname(rs.getString("COMMUNITYNAME"));
		// 服务意向
		postulant.setIntention(rs.getString("INTENTION"));
		// 组织ID
		postulant.setOrgid(rs.getLong("ORGID"));
		// 组织名称
		postulant.setOrgname(rs.getString("ORGNAME"));
		// 证件号码
		postulant.setCredcode(rs.getString("CREDCODE"));
		// 证件类型
		postulant.setCredtype(rs.getString("CREDTYPE"));

		return postulant;
	}
}
