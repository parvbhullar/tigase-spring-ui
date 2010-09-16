package com.linkage.app.gqt.purview.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class UserRowMapper implements RowMapper<User>{
	
	/*USERID	NUMBER(16)	N			
	LOGINNAME	VARCHAR2(20)	N			
	LOGINPASS	VARCHAR2(20)	N			
	NICKNAME	VARCHAR2(40)	Y			
	DN	VARCHAR2(13)	Y			
	SEX	NUMBER(1)	Y	1		
	EMAIL	VARCHAR2(50)	Y			
	STATE	NUMBER(1)	Y	1		
	ORGID	NUMBER(16)	N			
	CREATEDATE	DATE	N	SYSDATE		
	UPDATEDATE	DATE	Y	SYSDATE*/
	
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user=new User();
		user.setUserId(rs.getLong("USERID"));
		user.setPassword(rs.getString("LOGINPASS"));
		user.setUsername(rs.getString("LOGINNAME"));
		user.setNickName(rs.getString("NICKNAME"));
		user.setDn(rs.getString("DN"));
		user.setSex(rs.getInt("SEX"));
		user.setEmail(rs.getString("EMAIL"));
		user.setState(rs.getInt("STATE"));
		user.setOrgId(rs.getLong("ORGID"));
		user.setCreateDate(rs.getDate("CREATEDATE"));
		user.setUpdateDate(rs.getDate("UPDATEDATE"));
		return user;
	}
}
