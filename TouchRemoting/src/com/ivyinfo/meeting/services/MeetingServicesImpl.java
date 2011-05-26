package com.ivyinfo.meeting.services;

import java.util.List;
import java.util.Map;

import com.ivyinfo.meeting.bean.MeetingDetailBean;
import com.ivyinfo.meeting.bean.MeetingReturnBean;
import com.ivyinfo.meeting.util.MeetingUtil;
import com.ivyinfo.meeting.util.StartMeeting;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;

public class MeetingServicesImpl implements MeetingServices{
	
	public List MeetingList(UserBean userBean) throws Exception{
		MeetingUtil meetingutil = new MeetingUtil();
		MeetingReturnBean mrbean = meetingutil.getMeetingMessage(userBean);
		String result = mrbean.getResult();
		//判断返回是否成功
		List list = mrbean.getList();
		return list;
	}
	
	public MeetingReturnBean ListView(String confkey,UserBean userBean) throws Exception{
		MeetingUtil meetingutil = new MeetingUtil();
		return meetingutil.getMeetingViewMessage(confkey,userBean);
	}
	
	public String Addres(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		MeetingUtil meetingutil = new MeetingUtil();
		return meetingutil.SubmitMeeting(meetingDetailBean,userBean);
	}
	
	public String Addins(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		MeetingUtil meetingutil = new MeetingUtil();
		return meetingutil.InstantMeeting(meetingDetailBean,userBean);
	}
	
	public String Addfix(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		MeetingUtil meetingutil = new MeetingUtil();
		return meetingutil.FixedMeeting(meetingDetailBean,userBean);
	}
	
	public String Addreg(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		MeetingUtil meetingutil = new MeetingUtil();
		return meetingutil.RegularMeeting(meetingDetailBean,userBean);
	}
	
	
	public void Updres(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		MeetingUtil meetingutil = new MeetingUtil();
		meetingutil.UpdMeeting(meetingDetailBean,userBean);
	}
	
	public void Updfix(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		MeetingUtil meetingutil = new MeetingUtil();
		meetingutil.UpdFixedMeeting(meetingDetailBean,userBean);
	}
	
	public void Updreg(MeetingDetailBean meetingDetailBean,UserBean userBean) throws Exception{
		MeetingUtil meetingutil = new MeetingUtil();
		meetingutil.UpdRegularMeeting(meetingDetailBean,userBean);
	}
	
	public void ListDel(String confkey) throws Exception{
		MeetingUtil meetingutil = new MeetingUtil();
		meetingutil.DelMeeting(confkey);
	}
	
	public String ListStart(MeetingDetailBean meetingDetailBean) throws Exception{
		StartMeeting startMeeting = new StartMeeting();
		return startMeeting.StartMeetingUrl(meetingDetailBean);
	}

	public MeetingReturnBean GetMeetingMessage(String confKey,UserBean userBean) throws Exception {
		// TODO Auto-generated method stub
		MeetingUtil meetingutil = new MeetingUtil();
		//MeetingReturnBean mrBean = meetingutil.getUnfinishMeetingMsg(confKey);
		MeetingReturnBean mrBean = meetingutil.getMeetingViewMessage(confKey,userBean);
		return mrBean;
	}

	public Map GetStartParameter(MeetingDetailBean meetingDetailBean,UserBean userBean)
			throws Exception {
		// TODO Auto-generated method stub
		MeetingUtil meetingutil = new MeetingUtil();
		Map map = meetingutil.getStartMeetingParameter(meetingDetailBean,userBean);
		return map;
	}
	
	public List GetArrangeMeeting(MeetingDetailBean meetingDetailBean,UserBean userBean)
			throws Exception{
		MeetingUtil meetingUtil = new MeetingUtil();
		Map map = meetingUtil.getPrivateMeetingMessage(meetingDetailBean, userBean);
		List list = null;
		if("SUCCESS".equals(map.get("result"))){
			MeetingReturnBean mrbean = (MeetingReturnBean)map.get("reason");
			list = mrbean.getList();
			System.err.println("MeetingServicesImpl list.size():"+list.size());
		}else if("FAILURE".equals(map.get("result"))){
			String errmessage = String.valueOf(map.get("reason"));
			System.err.println("MeetingServicesImpl errmessage:"+errmessage);
			
			throw new Exception(errmessage);
		}
		return list;
	}
}
