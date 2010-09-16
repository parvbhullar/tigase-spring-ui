package com.linkage.app.gqt.backstage.members.entity;

import java.util.Date;

public class PostulantHis {
	private long id;
	
	private String name;
	
	private String dn;//手机号码
	
	private long communityid;//社区ID
	
	private long orgid;//机构id
	private String orgname;//机构名称

	private String credcode;//证件号码
	
	private String communityname;//社区名称
	
	private String outresion;//退出原因
	private Date outdate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public long getCommunityid() {
		return communityid;
	}
	public void setCommunityid(long communityid) {
		this.communityid = communityid;
	}
	public long getOrgid() {
		return orgid;
	}
	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getCredcode() {
		return credcode;
	}
	public void setCredcode(String credcode) {
		this.credcode = credcode;
	}
	public String getCommunityname() {
		return communityname;
	}
	public void setCommunityname(String communityname) {
		this.communityname = communityname;
	}
	public String getOutresion() {
		return outresion;
	}
	public void setOutresion(String outresion) {
		this.outresion = outresion;
	}
	public Date getOutdate() {
		return outdate;
	}
	public void setOutdate(Date outdate) {
		this.outdate = outdate;
	}
	
	
}
