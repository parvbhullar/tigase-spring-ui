package com.linkage.app.gqt.backstage.org.entitys;

import java.io.Serializable;

public class Org implements Serializable{

	/*
	ORGID	NUMBER(16)	N			
	ORGNAME	VARCHAR2(50)	N			机构全名
	ORGSHORTNAME	VARCHAR2(50)	Y			机构简称
	PARENTORGID	NUMBER(16)	N	0		父机构 0:顶级机构
	ORGSTATE	NUMBER(1)	N			机构状态 0:无效 1:有效
	ORGADDRESS	VARCHAR2(128)	Y			机构地址
	ORGTEL	VARCHAR2(32)	Y			机构电话
	ORGCONTACTOR	VARCHAR2(32)	Y			机构联系人
	ORGZIPCODE	VARCHAR2(10)	Y			邮政编码
	ORGTYPEID	NUMBER(16)	N			机构类型(关联ZYZ_ORGTYPE)
	MANAGEORGID	NUMBER(16)	N			服务机构的管理机构ID(管理机构为其自身ID,服务机构填写归口的管理机构的ID)
	ORGSUBTYPEID	NUMBER(16)	N			机构子类型 (关联ZYZ_ORGTYPE)
	REGABLE	NUMBER(1)	N	1		是否允许用户自主注册 0:不允许 1:允许
	ORGLEVEL	NUMBER(1)	N			机构层级(目前为4级,省、市、县(区)、乡镇(街道)) (关联ZYZ_ORGAREA)
	BELONGORGID	NUMBER(16)	N			机构归属 (关联ZYZ_ORGAREA)
	*/
	
	private static final long serialVersionUID = 556867332034973953L;
	
	private long orgId;
	private String orgName;
	private String orgShotrName;
	private long parentOrgId;
	private int orgState;
	private String orgAddress;
	private String orgTel;
	private String orgContactor;
	private String orgZipCode;
	private long orgTypeId;
	private long manageOrgId;
	private long orgSubTypeId;
	private int regAble;
	private int orgLevel;
	private long belongOrgId;
	private int level;
	private int num;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgShotrName() {
		return orgShotrName;
	}
	public void setOrgShotrName(String orgShotrName) {
		this.orgShotrName = orgShotrName;
	}
	public long getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public int getOrgState() {
		return orgState;
	}
	public void setOrgState(int orgState) {
		this.orgState = orgState;
	}
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	public String getOrgTel() {
		return orgTel;
	}
	public void setOrgTel(String orgTel) {
		this.orgTel = orgTel;
	}
	public String getOrgContactor() {
		return orgContactor;
	}
	public void setOrgContactor(String orgContactor) {
		this.orgContactor = orgContactor;
	}
	public String getOrgZipCode() {
		return orgZipCode;
	}
	public void setOrgZipCode(String orgZipCode) {
		this.orgZipCode = orgZipCode;
	}
	public long getOrgTypeId() {
		return orgTypeId;
	}
	public void setOrgTypeId(long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}
	public long getManageOrgId() {
		return manageOrgId;
	}
	public void setManageOrgId(long manageOrgId) {
		this.manageOrgId = manageOrgId;
	}
	public long getOrgSubTypeId() {
		return orgSubTypeId;
	}
	public void setOrgSubTypeId(long orgSubTypeId) {
		this.orgSubTypeId = orgSubTypeId;
	}
	public int getRegAble() {
		return regAble;
	}
	public void setRegAble(int regAble) {
		this.regAble = regAble;
	}
	public int getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(int orgLevel) {
		this.orgLevel = orgLevel;
	}
	public long getBelongOrgId() {
		return belongOrgId;
	}
	public void setBelongOrgId(long belongOrgId) {
		this.belongOrgId = belongOrgId;
	}
	
	
}
