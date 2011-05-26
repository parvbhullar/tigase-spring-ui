package com.ivyinfo.meeting.services;

import java.util.List;
import java.util.Map;

import com.ivyinfo.meeting.bean.MeetingDetailSysBean;
import com.ivyinfo.meeting.bean.MeetingReturnSysBean;
import com.ivyinfo.meeting.util.MeetingSysUtil;
import com.ivyinfo.user.bean.UserBean;

public class MeetingSysServicesImpl implements MeetingSysServices{
	
	public List OrgIndex() throws Exception{
		MeetingSysUtil meetingsysUtil = new MeetingSysUtil();
		MeetingReturnSysBean MRSBean = meetingsysUtil.OrgIndex();
		
		String result = MRSBean.getResult();
		/**
		 * 判断返回值
		 */
		
		List list = MRSBean.getList();
		return list;
	}
	
	public Map AddOrg(MeetingDetailSysBean meetingdetailsysBean) throws Exception{
		MeetingSysUtil meetingsysUtil = new MeetingSysUtil();
		return meetingsysUtil.AddOrg(meetingdetailsysBean);
	}
	
	public MeetingReturnSysBean ListUpdOrg(String orgid) throws Exception{
		MeetingSysUtil meetingsysUtil = new MeetingSysUtil();
		MeetingReturnSysBean mrsBean = meetingsysUtil.ListUpdOrg(orgid);
		
		String result = mrsBean.getResult();
		/**
		 * 判断返回值
		 */
		
		return mrsBean;
	}
	
	public void UpdOrg(MeetingDetailSysBean meetingdetailsysBean) throws Exception{
		MeetingSysUtil meetingsysUtil = new MeetingSysUtil();
		meetingsysUtil.UpdOrg(meetingdetailsysBean);
	}
	
	public void ListDelOrg(String orgId) throws Exception{
		MeetingSysUtil meetingsysUtil = new MeetingSysUtil();
		meetingsysUtil.ListDelOrg(orgId);
	}
	
	public Map ListOrgRole(String orgId)throws Exception{
		MeetingSysUtil meetingsysUtil = new MeetingSysUtil();
		return meetingsysUtil.ListOrgRole(orgId);
	}
	
	public List MeetUserAll() throws Exception{
		MeetingSysUtil meetingsysUtil = new MeetingSysUtil();
		MeetingReturnSysBean mrsBean = meetingsysUtil.MeetUserAll();
		
		String result = mrsBean.getResult();
		/**
		 * 判断返回值
		 */
		
		List list = mrsBean.getList();
		return list;
	}
	
	public Map AddUser(MeetingDetailSysBean meetingdetailsysBean,UserBean suserBean) throws Exception{
		MeetingSysUtil meetingsysUtil = new MeetingSysUtil();
		Map map = meetingsysUtil.AddUser(meetingdetailsysBean,suserBean);
		return map;
	}
	
	public MeetingReturnSysBean ListUpdUser(String userid) throws Exception{
		MeetingSysUtil meetingsysUtil = new MeetingSysUtil();
		MeetingReturnSysBean mrsBean = meetingsysUtil.ListUpdUser(userid);
		
		String result = mrsBean.getResult();
		/**
		 * 判断返回值
		 */
		
		return mrsBean;
	}
	
	public Map UpdUser(MeetingDetailSysBean meetingdetailsysBean,UserBean suserBean) throws Exception{
		MeetingSysUtil meetingsysUtil = new MeetingSysUtil();
		Map map = meetingsysUtil.UpdUser(meetingdetailsysBean,suserBean);
		return map;
	}
	
	public Map ListDelUser(String userid,UserBean suserBean) throws Exception{
		MeetingSysUtil meetingsysUtil = new MeetingSysUtil();
		Map map = meetingsysUtil.ListDelUser(userid,suserBean);
		return map;
	}
}
