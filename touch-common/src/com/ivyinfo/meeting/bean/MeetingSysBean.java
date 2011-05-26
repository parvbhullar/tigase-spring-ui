package com.ivyinfo.meeting.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class MeetingSysBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684270L;
	
	private String action;			//相关Service的action名称
	private String service;			//要访问的 Service名称
	private String type;			//访问的集成类型，XML集成服务都是“XML”
	private String siteName;		//site名称
	private String userName;		//可登陆会议系统的用户名称
	private String password;		//可登陆系统的用户对应密码
	
	private MeetingDetailSysBean MDSBean;
	
	public MeetingDetailSysBean getMDSBean() {
		return MDSBean;
	}

	public void setMDSBean(MeetingDetailSysBean mDSBean) {
		MDSBean = mDSBean;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
