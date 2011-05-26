package com.ivyinfo.login.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class LoginBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684278L;
	
	private String state;				//成功：SUCCESS   错误：ERROR
	private String errmessage;			//如果是ERROR返回错误原因
	private String userid;				//如果是SUCCESS返回用户id
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getErrmessage() {
		return errmessage;
	}
	public void setErrmessage(String errmessage) {
		this.errmessage = errmessage;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
