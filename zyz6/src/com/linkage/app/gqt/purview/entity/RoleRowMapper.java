package com.linkage.app.gqt.purview.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RoleRowMapper implements RowMapper<Role>{
	/*ROLEID	NUMBER(16)	N			
	ROLENAME	VARCHAR2(80)	N			
	DESCRIPTION	VARCHAR2(255)	N			
	ROLESTATE	NUMBER(1)	N	0		
	USERCOUNT	NUMBER(16)	N	1		
	ISSYSTEMROLE	NUMBER(1)	N	1*/
	
	public Role mapRow(ResultSet rs, int arg1) throws SQLException {
		Role role=new Role();
		role.setRoleId(rs.getLong("ROLEID"));
		role.setRoleName(rs.getString("ROLENAME"));
		role.setDescription(rs.getString("DESCRIPTION"));
		role.setRoleState(rs.getInt("ROLESTATE"));
		role.setUserCount(rs.getLong("USERCOUNT"));
		role.setIsSystemRole(rs.getInt("ISSYSTEMROLE"));
		role.setOrgId(rs.getLong("ORGID"));
		return role;
	}
}
