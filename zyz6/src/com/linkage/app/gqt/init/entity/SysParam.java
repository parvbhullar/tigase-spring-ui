package com.linkage.app.gqt.init.entity;

import java.io.Serializable;

public class SysParam implements Serializable{
	
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
	
	private static final long serialVersionUID = 5238577269310236564L;
	private long paramId;
	private String paramCode;
	private String paramKey;
	private String paramValue;
	private String paramDesc;
	private String paramRemark;
	private int paramState;
	private int paramOrder;
	/** 组织id */
	private Long orgid;
	/** 组织名称 */
	private String orgname;
	/**
	 * 获取组织名称
	 * @return orgname 组织名称
	 */
	public String getOrgname() {
		return orgname;
	}
	/**
	 * 设置组织名称
	 * @param orgname 组织名称
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public long getParamId() {
		return paramId;
	}
	public void setParamId(long paramId) {
		this.paramId = paramId;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamDesc() {
		return paramDesc;
	}
	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}
	public String getParamRemark() {
		return paramRemark;
	}
	public void setParamRemark(String paramRemark) {
		this.paramRemark = paramRemark;
	}
	public int getParamState() {
		return paramState;
	}
	public void setParamState(int paramState) {
		this.paramState = paramState;
	}
	public int getParamOrder() {
		return paramOrder;
	}
	public void setParamOrder(int paramOrder) {
		this.paramOrder = paramOrder;
	}
	/**
	 * 获取组织id
	 * @return orgid 组织id
	 */
	public Long getOrgid() {
		return orgid;
	}
	/**
	 * 设置组织id
	 * @param orgid 组织id
	 */
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

}
