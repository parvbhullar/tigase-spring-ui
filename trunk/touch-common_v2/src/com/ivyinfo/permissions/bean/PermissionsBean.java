package com.ivyinfo.permissions.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class PermissionsBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684244L;
	
	/**
	 * 权限信息表  t_sys_permissions
	 */
	private String number;			//权限编号  最高级菜单：01 下级菜单：0101 再下级菜单：010101  以此类推
	private String name;			//权限名称
	private String remark;			//备注
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
}
