package com.linkage.app.gqt.init.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrgTypeRowMapper implements RowMapper<OrgType>{
	
	/*
	ORGTYPEID	NUMBER(16)	N			组织机构类型ID
	ORGTYPEPARENTID	NUMBER(16)	N	0		组织机构类型的父类型ID 0:顶级类型
	ORGTYPENAME	VARCHAR2(50)	N			组织机构类型名称
	ORGTYPESTATE	NUMBER(1)	N	1		状态 0:无效 1:有效
	*/
	
	public OrgType mapRow(ResultSet rs, int arg1) throws SQLException {
		OrgType orgType=new OrgType();
		orgType.setOrgTypeId(rs.getLong("ORGTYPEID"));
		orgType.setOrgTypeParentId(rs.getLong("ORGTYPEPARENTID"));
		orgType.setOrgTypeName(rs.getString("ORGTYPENAME"));
		orgType.setOrgTypeState(rs.getInt("ORGTYPESTATE"));
		orgType.setLevel(rs.getInt("LEVEL"));
		orgType.setCategory(rs.getInt("CATEGORY"));
		return orgType;
	}
}
