package com.ivyinfo.meeting.bean;

import java.util.List;

import com.ivyinfo.framework.service.base.BaseBean;

public class MeetingDetailBean extends BaseBean implements java.io.Serializable{
	private static final long serialVersionUID = -4433034043604684240L;
	
	private String subject;					//会议主题
	private String startTime;				//会议开始时间  yyyy-mm-ddTHH:mm:ss
	private String endTime;					//会议结束时间  yyyy-mm-ddTHH:mm:ss
	private String timeZoneId;				//时区ID  默认45（GMT+8）
	private String attendeeAmount;			//参加人数
	private String hostName;				//主持人账号
	private String creator;					//创建人帐号
	private String openType;				//是否公开  公开：true  不公开：false
	private String passwd;					//会议密码
	private String conferencePattern;		//会议模式  主持人模式：0  自由模式：1
	private String agenda;					//会议描述
	private String mailTemplateLocal;		//邮件模板语言  简体中文：zh_CN(默认)  繁体中文：zh_TW  英文：en_US
	private String beforehandTime;			//这个参数对主持人有效，允许主持人提前指定的时间开启会议  配置为允许提前加入，该参数才起作用，可选值为：0,15,30,45,60, 单位为分钟，0（null）表示不能提前加入
	private String webBaseUrl;				//Meeting所在服务器URL地址  例如：http://192.168.1.10:80
	
	private String startHourMinute;			//周期会议开始时间  HH:mm  例如：05:03
	private String endHourMinute;			//周期会议结束时间  HH:mm  例如：15:59
	private String effectiveTime;			//周期会议重复范围起  GMT  yyyy-mm-ddTHH:mm:ss
	private String expirationTime;			//周期会议重复范围止  GMT  yyyy-mm-ddTHH:mm:ss
	private String repeatTypeKey;			//周期会议定期模式  值是：DAILY，WEEKLY，MONTHLY
	private String repeatTypeValue;			//周期会议定期模式值  RepeatTypeKey=DAILY（输入正整数，表示每隔几天)，RepeatTypeKey=WEEKLY(输入7位0和1组成的字符窜，例 周一和周三，传的值就是：1010000)，RepeatTypeKey=MONTHLY(输入0<n<32正整数，表示每月几号)
	
	private String startDateStart;			//开始时间起始点  GMT yyyy-mm-ddTHH:mm:ss
	private String startDateEnd;			//开始时间结束点  GMT yyyy-mm-ddTHH:mm:ss
	
	private String confKey;					//会议号
	
	private boolean attendees;				//参加者集合
	private List attendee;					//参加者信息，内部包含<name>,<email>,<phone>
	
	private String displayName;
	private String meetingEmail;
	
	private String attendeeName;
	private String serverName;
	
	public String getAttendeeName() {
		return attendeeName;
	}

	public void setAttendeeName(String attendeeName) {
		this.attendeeName = attendeeName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMeetingEmail() {
		return meetingEmail;
	}

	public void setMeetingEmail(String meetingEmail) {
		this.meetingEmail = meetingEmail;
	}

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public List getAttendee() {
		return attendee;
	}

	public void setAttendee(List attendee) {
		this.attendee = attendee;
	}

	public String getAttendeeAmount() {
		return attendeeAmount;
	}

	public void setAttendeeAmount(String attendeeAmount) {
		this.attendeeAmount = attendeeAmount;
	}

	public String getBeforehandTime() {
		return beforehandTime;
	}

	public void setBeforehandTime(String beforehandTime) {
		this.beforehandTime = beforehandTime;
	}

	public String getConferencePattern() {
		return conferencePattern;
	}

	public void setConferencePattern(String conferencePattern) {
		this.conferencePattern = conferencePattern;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getMailTemplateLocal() {
		return mailTemplateLocal;
	}

	public void setMailTemplateLocal(String mailTemplateLocal) {
		this.mailTemplateLocal = mailTemplateLocal;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTimeZoneId() {
		return timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	public String getWebBaseUrl() {
		return webBaseUrl;
	}

	public void setWebBaseUrl(String webBaseUrl) {
		this.webBaseUrl = webBaseUrl;
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

	public String getStartDateStart() {
		return startDateStart;
	}

	public void setStartDateStart(String startDateStart) {
		this.startDateStart = startDateStart;
	}

	public String getStartDateEnd() {
		return startDateEnd;
	}

	public void setStartDateEnd(String startDateEnd) {
		this.startDateEnd = startDateEnd;
	}

	public boolean isAttendees() {
		return attendees;
	}

	public void setAttendees(boolean attendees) {
		this.attendees = attendees;
	}

	public String getConfKey() {
		return confKey;
	}

	public void setConfKey(String confKey) {
		this.confKey = confKey;
	}
	
}
