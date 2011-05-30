package com.ivyinfo.roleuserpurview.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class RoleUserPurviewBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684247L;
	
	/**
	 * 角色用户权限信息表  t_sys_role_user_purview
	 */
	private String roleid;				//角色id
	private String userid;				//用户id  多用户：<1>,<2>,<3>,
	private String purview;				//权限信息  多权限：qx0101,qx0102,qx0103,
	
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPurview() {
		return purview;
	}
	public void setPurview(String purview) {
		this.purview = purview;
	}
}
