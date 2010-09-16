package com.linkage.app.gqt.purview.entity;

import java.io.Serializable;

public class Role implements Serializable{
	
	/*ROLEID	NUMBER(16)	N			
	ROLENAME	VARCHAR2(80)	N			
	DESCRIPTION	VARCHAR2(255)	N			
	ROLESTATE	NUMBER(1)	N	0		
	USERCOUNT	NUMBER(16)	N	1		
	ISSYSTEMROLE	NUMBER(1)	N	1*/
	
	private static final long serialVersionUID = 644119259692176294L;
	private long roleId;
	private String roleName;
	private String description;
	private int roleState;
	private long userCount;
	private int isSystemRole;
	
	private long orgId;//用户角色所在的组织
	
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRoleState() {
		return roleState;
	}
	public void setRoleState(int roleState) {
		this.roleState = roleState;
	}
	public long getUserCount() {
		return userCount;
	}
	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}
	public int getIsSystemRole() {
		return isSystemRole;
	}
	public void setIsSystemRole(int isSystemRole) {
		this.isSystemRole = isSystemRole;
	}
	
	

}
