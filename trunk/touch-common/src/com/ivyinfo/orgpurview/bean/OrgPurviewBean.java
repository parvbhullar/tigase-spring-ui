package com.ivyinfo.orgpurview.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class OrgPurviewBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684277L;
	
	private String orgid;					//机构id
	private String pnumber;					//权限编号
	private String remark1;					//备注1
	private String remark2;					//备注2
	private String timestemp;				//时间戳
	private String orgname;					//机构名称
	private String pname;					//权限名称
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getPnumber() {
		return pnumber;
	}
	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getTimestemp() {
		return timestemp;
	}
	public void setTimestemp(String timestemp) {
		this.timestemp = timestemp;
	}
}
