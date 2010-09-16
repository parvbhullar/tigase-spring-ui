package com.linkage.app.gqt.front.diary.entitys;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/*
 * 
 *RECID        NUMBER(10) not null,
  TITLE        VARCHAR2(128),
  CONTENT      VARCHAR2(1024),
  SENDUID      NUMBER(10),
  SENDUNAME    VARCHAR2(32),
  SENDNICKNAME VARCHAR2(32),
  SENDTIME     DATE,
  SRCRECID     NUMBER(10)
 */
public class DiaryRowMapper implements RowMapper<Diary> {
	public Diary mapRow(ResultSet rs, int arg1) throws SQLException {
	Diary diary=new Diary();
	diary.setRecid(rs.getLong("RECID"));
	diary.setTitle(rs.getString("TITLE"));
	diary.setContent(rs.getString("CONTENT"));
	diary.setSenduid(rs.getLong("SENDUID"));
	diary.setSenduname(rs.getString("SENDUNAME"));
	diary.setSendnickname(rs.getString("SENDNICKNAME"));
	diary.setSendtime(rs.getDate("SENDTIME"));
	diary.setSrcrecid(rs.getLong("SRCRECID"));
	diary.setIsverify(rs.getString("ISVERIFY"));
	return diary;
}}