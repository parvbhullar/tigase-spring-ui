package com.ivyinfo.webcall.api.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class CallSendBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684282L;
	
	private String UserCardNo;			//卡号
	private String Caller;				//主叫号码
	private String Called;				//被叫号码
	private int FeeTotal;				//帐户余额
	
	private String IdentitySequence;	//流水号
	
	private String StartTime;			//开始时间（YYYYMMDD） 如：20110101
	private String EndTime;				//结束时间（YYYYMMDD） 如：20110105
	
	private String soapenv;				//地址信息
	private String ctd;					//地址信息
	
	public String getSoapenv() {
		return soapenv;
	}
	public void setSoapenv(String soapenv) {
		this.soapenv = soapenv;
	}
	public String getCtd() {
		return ctd;
	}
	public void setCtd(String ctd) {
		this.ctd = ctd;
	}
	public String getUserCardNo() {
		return UserCardNo;
	}
	public void setUserCardNo(String userCardNo) {
		UserCardNo = userCardNo;
	}
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
	public int getFeeTotal() {
		return FeeTotal;
	}
	public void setFeeTotal(int feeTotal) {
		FeeTotal = feeTotal;
	}
	public String getIdentitySequence() {
		return IdentitySequence;
	}
	public void setIdentitySequence(String identitySequence) {
		IdentitySequence = identitySequence;
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
}
