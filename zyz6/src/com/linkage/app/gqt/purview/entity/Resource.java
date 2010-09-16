package com.linkage.app.gqt.purview.entity;

import java.io.Serializable;

public class Resource implements Serializable{
	
	private static final long serialVersionUID = -5568941120597849171L;
	/*RESOURCEID	NUMBER(16)	N			
	RESOURCENAME	VARCHAR2(50)	N			
	RESOURCETYPE	NUMBER(1)	N			
	STRING	VARCHAR2(255)	N			
	DESCRIPTION	VARCHAR2(255)	Y			
	RESOURCESTATE	NUMBER(1)	Y*/
	
	private long resourceId;
	private String resourceName;
	private int resourceType;
	private String string;
	private String description;
	private int resourceState;
	
	private long roleId;
	private String roleName;
	
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
	public long getResourceId() {
		return resourceId;
	}
	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public int getResourceType() {
		return resourceType;
	}
	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getResourceState() {
		return resourceState;
	}
	public void setResourceState(int resourceState) {
		this.resourceState = resourceState;
	}
	
}
