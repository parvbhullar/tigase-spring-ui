package com.linkage.app.gqt.backstage.helping.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linkage.app.gqt.backstage.members.entity.Assist;

public class QueryAssistRowMapper implements RowMapper<Assist>{
	
	
	public Assist mapRow(ResultSet rs, int arg1) throws SQLException {
		Assist assist=new Assist();
		//志愿者ID
		assist.setAid(rs.getLong("AID"));
		
		//志愿者姓名
		assist.setName(rs.getString("NAME"));
		
		//志愿者身份证
		assist.setCredcode(rs.getString("CREDCODE"));
		
		//地址
		assist.setAddress(rs.getString("ADDRESS"));
		
		// 注册机构ID
		assist.setVolunorgid(rs.getLong("VOLUNORGID"));
		
		// 注册机构名称
		assist.setVolunorgname(rs.getString("VOLUNORGNAME"));
		return assist;
	}
	
}