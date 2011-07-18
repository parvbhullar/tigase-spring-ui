package com.ivyinfo.meeting.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class MeetingDetailListSysBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684272L;
	
	private int type;
	private String value;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
