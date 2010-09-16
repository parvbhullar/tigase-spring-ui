package com.linkage.app.gqt.backstage.helping.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linkage.app.gqt.backstage.helping.entity.Worksheet;

public class QueryWorksheetRowMapper implements RowMapper<Worksheet>{
	/*
	WORKSHEETID	NUMBER(16)
	WORKSHEETNO	VARCHAR2(20)
	H_NAME	VARCHAR2(40)
	H_TITLE	VARCHAR2(100)
	H_ADDRESS	VARCHAR2(50)
	H_AREAID	NUMBER(18)
	H_TYPEID	NUMBER(18)
	H_STARTDATE	DATE
	H_OVERDATE	DATE
	H_NOTE	VARCHAR2(1024)
	V_NAME	VARCHAR2(40)
	V_TEL	NUMBER(16)
	STATE	NUMBER(1)
	ADMINID	NUMBER(16)
	  */
	public Worksheet mapRow(ResultSet rs, int arg1) throws SQLException {
		Worksheet worksheet=new Worksheet();
		// 工单ID
		worksheet.setWorksheetid(rs.getLong("WORKSHEETID"));
		// 工单号
		worksheet.setWorksheetno(rs.getString("WORKSHEETNO"));	
		// 事由
		worksheet.setHTitle(rs.getString("H_TITLE"));
		// 开始日期
		worksheet.setHStartdate(rs.getString("H_STARTDATE"));
		// 结束日期
		worksheet.setHOverdate(rs.getString("H_OVERDATE"));
		// 状态
		worksheet.setState(rs.getLong("state"));
		return worksheet;
	}
}