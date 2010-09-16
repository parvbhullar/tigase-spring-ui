package com.linkage.app.gqt.backstage.helping.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.linkage.app.gqt.backstage.sysparam.entity.Sysparam;

public class QueryTypeRowMapper implements RowMapper<Sysparam>{
	
	
	public Sysparam mapRow(ResultSet rs, int arg1) throws SQLException {
		Sysparam sysparam=new Sysparam();
		//参数ID
		sysparam.setParamid(rs.getLong("PARAMID"));
		
		// 参数序号
		sysparam.setParamorder(rs.getLong("PARAMORDER"));
		// 参数名称
		sysparam.setParamkey(rs.getString("PARAMKEY"));	
		
		// 参数分值
		sysparam.setParamvalue(rs.getString("PARAMVALUE"));

		return sysparam;
	}
	
}