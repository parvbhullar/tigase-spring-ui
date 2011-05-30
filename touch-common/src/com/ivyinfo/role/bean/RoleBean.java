package com.ivyinfo.role.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class RoleBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684246L;
	
	/**
	 * 角色信息表  t_sys_role
	 */
	private String id;					//id
	private String name;				//角色名
	private String remark;				//备注
	private String organizationid;		//机构id
	private String upduserid;			//修改用户id
	private String timestemp;			//修改时间
	private String dirtyflag;			//脏标记
	
	public String getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUpduserid() {
		return upduserid;
	}
	public void setUpduserid(String upduserid) {
		this.upduserid = upduserid;
	}
	public String getTimestemp() {
		return timestemp;
	}
	public void setTimestemp(String timestemp) {
		this.timestemp = timestemp;
	}
	public String getDirtyflag() {
		return dirtyflag;
	}
	public void setDirtyflag(String dirtyflag) {
		this.dirtyflag = dirtyflag;
	}
}
