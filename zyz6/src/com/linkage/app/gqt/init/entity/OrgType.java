package com.linkage.app.gqt.init.entity;

import java.io.Serializable;

public class OrgType implements Serializable{

	private static final long serialVersionUID = 5646702905378560856L;
	
	/*
	ORGTYPEID	NUMBER(16)	N			组织机构类型ID
	ORGTYPEPARENTID	NUMBER(16)	N	0		组织机构类型的父类型ID 0:顶级类型
	ORGTYPENAME	VARCHAR2(50)	N			组织机构类型名称
	ORGTYPESTATE	NUMBER(1)	N	1		状态 0:无效 1:有效
	*/
	
	private long orgTypeId;
	private long orgTypeParentId;
	private String orgTypeName;
	private int orgTypeState;
	private int level;
	private int category;
	
	
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getOrgTypeId() {
		return orgTypeId;
	}
	public void setOrgTypeId(long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}
	public long getOrgTypeParentId() {
		return orgTypeParentId;
	}
	public void setOrgTypeParentId(long orgTypeParentId) {
		this.orgTypeParentId = orgTypeParentId;
	}
	public String getOrgTypeName() {
		return orgTypeName;
	}
	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}
	public int getOrgTypeState() {
		return orgTypeState;
	}
	public void setOrgTypeState(int orgTypeState) {
		this.orgTypeState = orgTypeState;
	}
	
}
