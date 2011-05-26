package com.ivyinfo.meeting.bean;

import com.ivyinfo.framework.service.base.BaseBean;

public class MeetingReturnDetailBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684242L;
	
	private String confKey;				//会议号
	private String subject;				//会议主题
	private String hostName;			//主持人账号
	private String startTime;			//会议开始时间
	private String endTime;				//会议结束时间
	private String status;				//会议状态  0 ：未开始  1：已开始  2：已结束
	private String duringTime;			//会议持续时间  单位：分钟
	private String confType;			//会议类型  预约会议：0  即时会议：1  固定会议：2  周期会议：3
	
	
	public String getConfKey() {
		return confKey;
	}
	public void setConfKey(String confKey) {
		this.confKey = confKey;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		if("0".equals(status)){
			status = "未开始";
		}
		if("1".equals(status)){
			status = "已开始";
		}
		if("2".equals(status)){
			status = "已结束";
		}
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDuringTime() {
		return duringTime;
	}
	public void setDuringTime(String duringTime) {
		this.duringTime = duringTime;
	}
	public String getConfType() {
		if("0".equals(confType)){
			confType = "预约会议";
		}
		if("1".equals(confType)){
			confType = "即时会议";
		}
		if("2".equals(confType)){
			confType = "固定会议";
		}
		if("3".equals(confType)){
			confType = "周期会议";
		}
		return confType;
	}
	public void setConfType(String confType) {
		this.confType = confType;
	}
}
