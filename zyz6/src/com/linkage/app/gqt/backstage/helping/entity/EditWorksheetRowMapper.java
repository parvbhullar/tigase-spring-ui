package com.linkage.app.gqt.backstage.helping.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linkage.app.gqt.backstage.active.entity.Active;


public class EditWorksheetRowMapper implements RowMapper<Worksheet>{

	public Worksheet mapRow(ResultSet rs, int arg1) throws SQLException {
		Worksheet worksheet=new Worksheet();
		// 工单ID
		worksheet.setWorksheetid(rs.getLong("WORKSHEETID"));
		// 帮扶者姓名
		worksheet.setHName(rs.getString("H_NAME"));
		// 帮扶事由
		worksheet.setHTitle(rs.getString("H_TITLE"));
		
		// 被帮扶者地点
		worksheet.setHAddress(rs.getString("H_ADDRESS"));
		
		// 所属区域ID
		worksheet.setHAreaid(rs.getLong("H_AREAID"));
		
		// 所属分类ID
		worksheet.setHTypeid(Long.parseLong(rs.getString("H_TYPEID")));
		
		// 开始时间
		worksheet.setHStartdate(rs.getString("H_STARTDATE"));
		
		// 结束时间	
		worksheet.setHOverdate(rs.getString("H_OVERDATE"));
		
		// 简介
		worksheet.setHNote(rs.getString("H_NOTE"));
		
		//志愿者姓名
		worksheet.setVName(rs.getString("V_NAME"));
		
		//志愿者联系电话
		worksheet.setVTel(rs.getLong("V_TEL"));
		
		//志愿者所属区域ID
		worksheet.setVAreaid(rs.getLong("V_AREAID"));
		
		//志愿者ID
		worksheet.setId(rs.getLong("V_ID"));
		
		
		return worksheet;
	}
}