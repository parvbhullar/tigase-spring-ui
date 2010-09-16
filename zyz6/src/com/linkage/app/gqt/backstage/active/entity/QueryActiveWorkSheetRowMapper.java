package com.linkage.app.gqt.backstage.active.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class QueryActiveWorkSheetRowMapper implements RowMapper<Active>{
	/*
	ACTID        NUMBER(8) not null,
	  ACTTITLE     VARCHAR2(128) not null,
	  ACTDESC      VARCHAR2(1024) not null,
	  ACTTYPE      INTEGER,
	  STARTDATE    DATE not null,
	  ENDDATE      DATE not null,
	  HOSTID       NUMBER(8) not null,
	  HOST         VARCHAR2(128),
	  DEFAULTPOINT NUMBER(8,2) default 0,
	  ACTADDR      VARCHAR2(128),
	  ACTSTATUS    INTEGER default 1,
	  SENDTIME     DATE
	  */
	public Active mapRow(ResultSet rs, int arg1) throws SQLException {
		Active active=new Active();
		// 活动ID
		active.setActid(rs.getLong("actid"));
		// 活动标题
		active.setActtitle(rs.getString("acttitle"));
		// 开始日期
		active.setStartdate(rs.getString("startdate"));
		// 结束日期
		active.setEnddate(rs.getString("enddate"));
		// 服务意向
		active.setIntention(rs.getString("intention"));
		// 组织名称
		active.setOrgname(rs.getString("orgname"));
		// 活动状态
		active.setActstatus(rs.getLong("actstatus"));
		// 推送状态
		active.setTsstatus(rs.getString("tsstatus"));
		//工单状态
		active.setStatus(rs.getString("status"));
		return active;
	}
}
