package com.linkage.app.gqt.backstage.members.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class PostulantRowMapper implements RowMapper<Postulant>{
	public Postulant mapRow(ResultSet rs, int arg1) throws SQLException {
		Postulant postulant=new Postulant();
		postulant.setId(rs.getLong("ID"));
		postulant.setLoginName(rs.getString("LOGINNAME"));
		postulant.setName(rs.getString("NAME"));
		//postulant.setNickName(rs.getString("NICKNAME"));
		postulant.setPassword(rs.getString("PASSWORD"));
		postulant.setDn(rs.getString("DN"));
		postulant.setEmail(rs.getString("EMAIL"));
		postulant.setSex(rs.getInt("SEX"));
		postulant.setBirthday(rs.getDate("BIRTHDAY"));
		postulant.setEducation(rs.getString("EDUCATION"));
		postulant.setProfession(rs.getString("PROFESSION"));
		postulant.setHobby(rs.getString("HOBBY"));
		postulant.setCommunityid(rs.getLong("COMMUNITYID"));
		postulant.setCommunityname(rs.getString("COMMUNITYNAME"));
		postulant.setIntention(rs.getString("INTENTION"));
		postulant.setServetimes(rs.getString("SERVETIMES"));
		postulant.setOrgid(rs.getLong("ORGID"));
		postulant.setOrgname(rs.getString("ORGNAME"));
		postulant.setCredcode(rs.getString("CREDCODE"));
		postulant.setCredtype(rs.getString("CREDTYPE"));
		postulant.setIsvalid(rs.getInt("ISVALID"));
		postulant.setIsverify(rs.getInt("ISVERIFY"));
		postulant.setVerifydesc(rs.getString("VERIFYDESC"));
		postulant.setCreatetime(rs.getDate("CREATETIME"));
		postulant.setRegip(rs.getString("REGIP"));
		postulant.setRegdate(rs.getDate("REGDATE"));
		postulant.setLastloginip(rs.getString("LASTLOGINIP"));
		postulant.setTotalpoints(rs.getDouble("TOTALPOINTS"));
		//postulant.setLastlognumbertime(rs.getDate("LASTLOGNUMBERTIME"));
		
		postulant.setDeletetime(rs.getTimestamp("DELEETTIME"));
		postulant.setPhone(rs.getString("PHONE"));
		postulant.setAddress(rs.getString("ADDRESS"));
		postulant.setJn(rs.getString("JN"));
		postulant.setNation(rs.getString("NATION"));
		postulant.setOutresion(rs.getString("OUTRESION"));
		return postulant;
	}
}
