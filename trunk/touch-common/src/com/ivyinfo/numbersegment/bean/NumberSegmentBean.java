package com.ivyinfo.numbersegment.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class NumberSegmentBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684290L;
	
	private String id;					//id
	private String startseg;			//号码开始段
	private String middleseg;			//号码焦点段
	private String endseg;				//号码结束段
	private String state;				//状态  1：正在使用中，2：号码段已用完
	private String orgid;				//机构id
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStartseg() {
		return startseg;
	}
	public void setStartseg(String startseg) {
		this.startseg = startseg;
	}
	public String getMiddleseg() {
		return middleseg;
	}
	public void setMiddleseg(String middleseg) {
		this.middleseg = middleseg;
	}
	public String getEndseg() {
		return endseg;
	}
	public void setEndseg(String endseg) {
		this.endseg = endseg;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
}
