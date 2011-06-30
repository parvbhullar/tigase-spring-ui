package com.ivyinfo.purview.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class PurviewBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684245L;
	
	private String organizationid;						//机构id
	private String organizationname;					//机构名称
	private String roleid;								//角色id
	private String rolename;							//角色名称
	private String deptid;								//部门id
	private String deptname;							//部门名称
	private String userid;								//用户id
	private String username;							//用户姓名
	private String purview;								//权限
	
	private String meetorgid;
	
	public String getMeetorgid() {
		return meetorgid;
	}
	public void setMeetorgid(String meetorgid) {
		this.meetorgid = meetorgid;
	}
	public String getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}
	public String getOrganizationname() {
		return organizationname;
	}
	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPurview() {
		return purview;
	}
	public void setPurview(String purview) {
		this.purview = purview;
	}
}
