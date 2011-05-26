package com.ivyinfo.webcall.api.bean;

import java.util.List;

import com.ivyinfo.framework.service.base.BaseBean;

public class CallReturnBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684283L;
	
	private String Result;				//结果  0----成功  1----失败
	private String Reason;				//失败原因  1 卡号段不存在  2 帐户余额不足  3 计费方案号不存在  4 呼叫被过负荷限制  5 系统计费异常  1001 系统通用异常
	
	private String IdentitySequence;	//流水号  YYYYMMDDHHMMSSXXXXX  XXXXX从1开始，排满99999后重新循环
	private String UserCardNo;			//卡号
	private String Caller;				//用户使用主叫号码
	private String Called;				//被叫号码
	
	private String UsedFee;				//通话费用
	private String TalkingTime;			//通话时长
	private String Status;				//状态
	
	private List Cdrinfo;				//话单详细信息
	
	public List getCdrinfo() {
		return Cdrinfo;
	}
	public void setCdrinfo(List cdrinfo) {
		Cdrinfo = cdrinfo;
	}
	public String getUsedFee() {
		return UsedFee;
	}
	public void setUsedFee(String usedFee) {
		UsedFee = usedFee;
	}
	public String getTalkingTime() {
		return TalkingTime;
	}
	public void setTalkingTime(String talkingTime) {
		TalkingTime = talkingTime;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getIdentitySequence() {
		return IdentitySequence;
	}
	public void setIdentitySequence(String identitySequence) {
		IdentitySequence = identitySequence;
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
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
}
