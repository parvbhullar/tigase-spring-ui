package com.ivyinfo.meeting.services;

import java.util.List;
import java.util.Map;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.meeting.bean.MeetingDetailBean;
import com.ivyinfo.meeting.bean.MeetingReturnBean;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;

public interface MeetingServices extends IBaseService{
	public List MeetingList(UserBean userBean) throws Exception;
	
	public MeetingReturnBean ListView(String confkey,UserBean userBean) throws Exception;
	
	public String Addres(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception;
	
	public String Addins(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception;
	
	public String Addfix(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception;
	
	public String Addreg(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception;
	
	public void Updres(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception;
	
	public void Updfix(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception;
	
	public void Updreg(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception;
	
	public void ListDel(String confkey) throws Exception;
	
	public String ListStart(MeetingDetailBean meetingDetailBean) throws Exception;
	
	public MeetingReturnBean GetMeetingMessage(String confKey,UserBean userBean)throws Exception;//获得某个会议的信息
	
	public Map GetStartParameter(MeetingDetailBean meetingDetailBean,UserBean userBean)throws Exception;
	
	public List GetArrangeMeeting(MeetingDetailBean meetingDetailBean,UserBean userBean)throws Exception;
}
