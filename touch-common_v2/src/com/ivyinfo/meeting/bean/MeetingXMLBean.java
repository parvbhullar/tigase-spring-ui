package com.ivyinfo.meeting.bean;

public interface MeetingXMLBean {
	/**
	 * xml节点
	 */
	public static final String MESSAGE = "Message";
	public static final String HEADER = "header";
	public static final String BODY = "body";
	public static final String ATTENDEES = "attendees";
	public static final String ATTENDEE = "attendee";
	public static final String MEETING = "meeting";
	
	/**
	 * 发送xml，邀请参加者
	 */
	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	
	/**
	 * 发送xml头文件
	 */
	public static final String ACTION = "action";
	public static final String SERVICE = "service";
	public static final String TYPE = "type";
	public static final String SITENAME = "siteName";
	public static final String USERNAME = "userName";
	public static final String PASSWORD = "password";
	
	/**
	 * 发送xml参数
	 */
	public static final String SUBJECT = "subject";
	public static final String STARTTIME = "startTime";
	public static final String ENDTIME = "endTime";
	public static final String TIMEZONEID = "timeZoneId";
	public static final String ATTAMOUNT = "attendeeAmount";
	public static final String HOSTNAME = "hostName";
	public static final String CREATOR = "creator";
	public static final String OPENTYPE = "openType";
	public static final String PASSWD = "passwd";
	public static final String CONPATTERN = "conferencePattern";
	public static final String AGENDA = "agenda";
	public static final String MAILLOCAL = "mailTemplateLocal";
	public static final String BEFORETIME = "beforehandTime";
	public static final String WEBBASEURL = "webBaseUrl";
	
	public static final String STARTHM = "startHourMinute";
	public static final String ENDHM = "endHourMinute";
	public static final String EFFTIME = "effectiveTime";
	public static final String EXPTIME = "expirationTime";
	public static final String REPTYPEKEY = "repeatTypeKey";
	public static final String REPTYPEVALUE = "repeatTypeValue";
	
	public static final String STARTDATESTART = "startDateStart";
	public static final String STARTDATEEND = "startDateEnd";
	
	public static final String CONFKEY = "confKey";
	
	public static final String ATTENDEENAME = "attendeeName";
	public static final String SERVERNAME = "serverName";
	
	/**
	 * 返回xml参数
	 */
	public static final String RESULT = "result";
	public static final String CIURL = "ciURL";
	public static final String TOKEN = "token";
	
	public static final String RCONKEY = "confKey";
	public static final String RSUBJECT = "subject";
	public static final String RHOSTNAME = "hostName";
	public static final String RSTARTTIME = "startTime";
	public static final String RENDTIME = "endTime";
	public static final String RSTATUS = "status";
	public static final String RDURINGTIME = "duringTime";
	public static final String RCONFTYPE = "confType";
	
	public static final String RATTAMOUNT = "attendeeAmount";
	public static final String RCONFPATTERN = "conferencePattern";
	public static final String RCONTYPE = "conferenceType";
	public static final String RDURATION = "duration";
	public static final String RSTARTHM = "startHourMinute";
	public static final String RENDHM = "endHourMinute";
	public static final String REFFTIME = "effectiveTime";
	public static final String REXPTIME = "expirationTime";
	public static final String ROPENTYPE = "openType";
	public static final String RREPTYPEKEY = "repeatTypeKey";
	public static final String RREPTYPEVALUE = "repeatTypeValue";
	public static final String RAGENDA = "agenda";
	
	public static final String MEETINGPWD = "meetingPwd";
	public static final String DISPLAYNAME = "displayName";
	public static final String MEETINGEMAIL = "meetingEmail";
}
