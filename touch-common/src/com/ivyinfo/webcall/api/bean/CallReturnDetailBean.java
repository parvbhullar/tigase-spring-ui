package com.ivyinfo.webcall.api.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class CallReturnDetailBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684284L;
	
	private String Caller;			//主叫号码
	private String Called;			//被叫号码
	private String StartTime;		//开始时间（YYYYMMDD）
	private String EndTime;			//结束时间（YYYYMMDD）
	private String Fee;				//费用
	private String CallTime;		//通话时长（单位：秒）
	
	public String getCaller() {
		return Caller;
	}
	public void setCaller(String caller) {
		Caller = caller;
	}
	public String getCalled() {
		return Called;
	}
	public void setCalled(String called) {
		Called = called;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public String getFee() {
		return Fee;
	}
	public void setFee(String fee) {
		Fee = fee;
	}
	public String getCallTime() {
		return CallTime;
	}
	public void setCallTime(String callTime) {
		CallTime = callTime;
	}
}
