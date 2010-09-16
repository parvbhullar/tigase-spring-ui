package com.linkage.app.gqt.backstage.org.entitys;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrgTypeIDRowMapper implements RowMapper<Org>{
	
	/*
	ORGTYPEID	NUMBER(16)	N			机构类型(关联ZYZ_ORGTYPE)
	*/
	public Org mapRow(ResultSet rs, int arg1) throws SQLException {
		Org org=new Org();
		org.setOrgTypeId(rs.getLong("ORGTYPEID"));
		return org;
	}
}
