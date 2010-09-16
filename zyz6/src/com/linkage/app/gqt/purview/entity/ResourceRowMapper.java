package com.linkage.app.gqt.purview.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ResourceRowMapper implements RowMapper<Resource>{
	
	/*RESOURCEID	NUMBER(16)	N			
	RESOURCENAME	VARCHAR2(50)	N			
	RESOURCETYPE	NUMBER(1)	N			
	STRING	VARCHAR2(255)	N			
	DESCRIPTION	VARCHAR2(255)	Y			
	RESOURCESTATE	NUMBER(1)	Y*/
	
	public Resource mapRow(ResultSet rs, int arg1) throws SQLException {
		Resource resource=new Resource();
		resource.setResourceId(rs.getLong("RESOURCEID"));
		resource.setResourceName(rs.getString("RESOURCENAME"));
		resource.setResourceType(rs.getInt("RESOURCETYPE"));
		resource.setString(rs.getString("STRING"));
		resource.setDescription(rs.getString("DESCRIPTION"));
		resource.setResourceState(rs.getInt("RESOURCESTATE"));
		
		resource.setRoleId(rs.getLong("ROLEID"));
		resource.setRoleName(rs.getString("ROLENAME"));
		return resource;
	}
}
