package com.linkage.app.gqt.init.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FwyxParamRowMapper implements RowMapper<SysParam> {
	
	/*
	PARAMID	NUMBER(16)	N			ID
	PARAMCODE	VARCHAR2(30)	N			
	PARAMKEY	VARCHAR2(30)	N			系统参数键
	PARAMVALUE	VARCHAR2(50)	N			系统参数值
	PARAMSTATE	NUMBER(1)	N	1		系统参数状态[1=有效,0=无效]
	PARAMORDER	NUMBER(2)	Y			系统参数排序
	PARAMDESC	VARCHAR2(100)	Y			系统参数描述
	PARAMREMARK	VARCHAR2(100)	Y			系统参数备注
	*/
	public SysParam mapRow(ResultSet rs, int arg1) throws SQLException {
		SysParam sysParam=new SysParam();
		sysParam.setOrgid(rs.getLong("orgid"));
		sysParam.setParamKey(rs.getString("PARAMKEY"));
		sysParam.setParamValue(rs.getString("PARAMVALUE"));
		sysParam.setParamDesc(rs.getString("paramDesc"));
		sysParam.setOrgname(rs.getString("ORGNAME"));
		return sysParam;
	}
}
