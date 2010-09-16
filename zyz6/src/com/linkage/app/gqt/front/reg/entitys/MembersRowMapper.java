package com.linkage.app.gqt.front.reg.entitys;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MembersRowMapper implements RowMapper<Members> {
	public Members mapRow(ResultSet rs, int arg1) throws SQLException {
	Members members=new Members();
	members.setUuid(rs.getLong("uuid"));
	members.setUsername(rs.getString("username"));
	members.setName(rs.getString("name"));
	members.setNickname(rs.getString("nickname"));
	members.setPassword(rs.getString("password"));
	members.setCellphone(rs.getString("cellphone"));
	members.setEmail(rs.getString("email"));
	members.setSex(rs.getBoolean("sex"));
	members.setBirthday(rs.getDate("birthday"));
	members.setEdulevel(rs.getString("edulevel"));
	members.setProfession(rs.getString("profession"));
	members.setHobby(rs.getString("hobby"));
	members.setLocation(rs.getString("location"));
	members.setIntention(rs.getString("intention"));
//	members.setServetimes(rs.getString("servitimes"));
	members.setVolunorgid(rs.getLong("VOLUNORGID"));
//	members.setCredcode(rs.getString("credcoed"));
//	members.setCredtype(rs.getString("credtype"));
//	members.setIsvalid(rs.getBoolean("isvalid"));
	members.setIsverify(rs.getInt("isverify"));
//	members.setVerifydesc(rs.getString("verifydesc"));
//	members.setCreatetime(rs.getDate("createtime"));
//	members.setRegip(rs.getString("regip"));
//	members.setRegdate(rs.getDate("regdate"));
//	members.setLastloginip(rs.getString("lastloginip"));
//	members.setTotalpoints(rs.getLong("totalpoints"));
//	members.setLastlognumbertime(rs.getDate("lastlognumbertime"));
	return members;
}}

