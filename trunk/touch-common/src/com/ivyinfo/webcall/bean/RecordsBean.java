package com.ivyinfo.webcall.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class RecordsBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684260L;
	
	private String callee;
	private String starttime;
	private String endtime;
	
	public String getCallee() {
		return callee;
	}
	public void setCallee(String callee) {
		this.callee = callee;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
}
