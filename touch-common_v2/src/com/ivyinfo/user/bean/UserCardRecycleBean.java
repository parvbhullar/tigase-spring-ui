package com.ivyinfo.user.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class UserCardRecycleBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684291L;
	
	private String username;
	private String cardnumber;
	private String timestemp;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getTimestemp() {
		return timestemp;
	}
	public void setTimestemp(String timestemp) {
		this.timestemp = timestemp;
	}
}
