package com.ivyinfo.user.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class UserPreBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684288L;
	
	private String id;				//id
	private String username;		//用户登录名
	private String keyword;			//参数键值
	private String prefvalue;		//参数值
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getPrefvalue() {
		return prefvalue;
	}
	public void setPrefvalue(String prefValue) {
		prefvalue = prefValue;
	}
}
