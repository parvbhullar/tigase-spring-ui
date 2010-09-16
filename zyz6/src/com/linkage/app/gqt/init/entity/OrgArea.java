package com.linkage.app.gqt.init.entity;

import java.io.Serializable;

public class OrgArea implements Serializable{
	
	/*
	AREAID	NUMBER(16)	N			区域ID
	AREAPARENTID	NUMBER(16)	N			区域父ID 0:表示顶级区域ID
	AREANAME	VARCHAR2(50)	N			区域名称
	AREALEVEL	VARCHAR2(50)	N			区域等级
	AREASTATE	NUMBER(1)	N	1		区域状态 0:无效 1:有效
	*/

	private static final long serialVersionUID = -6078428907425493039L;
	private long areaId;
	private long areaParentId;
	private String areaName;
	private String areaLevel;
	private int areaState;
	private int level;
	public long getAreaId() {
		return areaId;
	}
	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}
	public long getAreaParentId() {
		return areaParentId;
	}
	public void setAreaParentId(long areaParentId) {
		this.areaParentId = areaParentId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}
	public int getAreaState() {
		return areaState;
	}
	public void setAreaState(int areaState) {
		this.areaState = areaState;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
