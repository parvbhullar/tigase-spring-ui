package com.ivyinfo.meeting.bean;

import java.util.List;

import com.ivyinfo.framework.service.base.BaseBean;

public class MeetingReturnBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684241L;
	
	private String result;		//调用结果。成功则为“SUCCESS”，失败则为“FAILURE”
	private String ciURL;		//启动会议的CI url，siteId=1&dt=GMT必须带上, 例如：http://192.168.1.72/conference/ci.action?siteId=1&dt=GMT
	private String token;		//参数加密字串
	
	private String confKey;					//会议号
	private String attendeeAmount;			//参加人数
	private String conferencePattern;		//会议模式  主持人模式：0  自由模式：1
	private String conferenceType;			//会议类型  预约会议：0  即时会议：1  固定会议：2  周期会议：3
	private String duration;				//会议持续时间  单位：分钟
	private String startHourMinute;			//周期会议开始时间  HH:mm  例如：05:03
	private String endHourMinute;			//周期会议结束时间  HH:mm  例如：15:59
	private String startTime;				//会议开始时间  GMT yyyy-mm-ddTHH:mm:ss
	private String endTime;					//会议结束时间  GMT yyyy-mm-ddTHH:mm:ss
	private String effectiveTime;			//周期会议重复范围起  GMT yyyy-mm-ddTHH:mm:ss
	private String expirationTime;			//周期会议重复范围止  GMT yyyy-mm-ddTHH:mm:ss
	private String hostName;				//主持人账号
	private String openType;				//是否公开  公开：true  不公开：false
	private String repeatTypeKey;			//周期会议定期模式  值是：DAILY ，WEEKLY，MONTHLY
	private String repeatTypeValue;			//周期会议定期模式值  RepeatTypeKey=DAILY（输入正整数，表示每隔几天)，RepeatTypeKey=WEEKLY(输入7位0和1组成的字符窜，例 周一和周三，传的值就是：1010000)，RepeatTypeKey=MONTHLY(输入0<n<32正整数，表示每月几号)
	private String subject;					//会议主题
	private String agenda;					//会议描述
	
	private List list;
	
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getCiURL() {
		return ciURL;
	}
	public void setCiURL(String ciURL) {
		this.ciURL = ciURL;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getConfKey() {
		return confKey;
	}
	public void setConfKey(String confKey) {
		this.confKey = confKey;
	}
	public String getAttendeeAmount() {
		return attendeeAmount;
	}
	public void setAttendeeAmount(String attendeeAmount) {
		this.attendeeAmount = attendeeAmount;
	}
	public String getConferencePattern() {
		if("0".equals(conferencePattern)){
			conferencePattern = "主持人模式";
		}
		if("1".equals(conferencePattern)){
			conferencePattern = "自由模式";
		}
		return conferencePattern;
	}
	public void setConferencePattern(String conferencePattern) {
		this.conferencePattern = conferencePattern;
	}
	public String getConferenceType() {
		if("0".equals(conferenceType)){
			conferenceType = "预约会议";
		}
		if("1".equals(conferenceType)){
			conferenceType = "即时会议";
		}
		if("2".equals(conferenceType)){
			conferenceType = "固定会议";
		}
		if("3".equals(conferenceType)){
			conferenceType = "周期会议";
		}
		return conferenceType;
	}
	public void setConferenceType(String conferenceType) {
		this.conferenceType = conferenceType;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getStartHourMinute() {
		return startHourMinute;
	}
	public void setStartHourMinute(String startHourMinute) {
		this.startHourMinute = startHourMinute;
	}
	public String getEndHourMinute() {
		return endHourMinute;
	}
	public void setEndHourMinute(String endHourMinute) {
		this.endHourMinute = endHourMinute;
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
	public String getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public String getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getOpenType() {
		if("true".equals(openType)){
			openType = "公开";
		}
		if("false".equals(openType)){
			openType = "不公开";
		}
		return openType;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	public String getRepeatTypeKey() {
		return repeatTypeKey;
	}
	public void setRepeatTypeKey(String repeatTypeKey) {
		this.repeatTypeKey = repeatTypeKey;
	}
	public String getRepeatTypeValue() {
		return repeatTypeValue;
	}
	public void setRepeatTypeValue(String repeatTypeValue) {
		this.repeatTypeValue = repeatTypeValue;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAgenda() {
		return agenda;
	}
	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
	
}
