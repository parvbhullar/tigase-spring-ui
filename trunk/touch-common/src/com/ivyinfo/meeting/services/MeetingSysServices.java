package com.ivyinfo.meeting.services;

import java.util.List;
import java.util.Map;

import com.ivyinfo.framework.service.base.IBaseService;
import com.ivyinfo.meeting.bean.MeetingDetailSysBean;
import com.ivyinfo.meeting.bean.MeetingReturnSysBean;
import com.ivyinfo.user.bean.UserBean;

public interface MeetingSysServices extends IBaseService{
	public List MeetUserAll() throws Exception;
	
	public List OrgIndex() throws Exception;
	
	public Map AddOrg(MeetingDetailSysBean meetingdetailsysBean) throws Exception;
	
	public MeetingReturnSysBean ListUpdOrg(String orgid) throws Exception;
	
	public void UpdOrg(MeetingDetailSysBean meetingdetailsysBean) throws Exception;
	
	public void ListDelOrg(String orgId) throws Exception;
	
	public Map ListOrgRole(String orgId)throws Exception;
	
	public Map AddUser(MeetingDetailSysBean meetingdetailsysBean,UserBean suserBean) throws Exception;
	
	public MeetingReturnSysBean ListUpdUser(String userid) throws Exception;
	
	public Map UpdUser(MeetingDetailSysBean meetingdetailsysBean,UserBean suserBean) throws Exception;
	
	public Map ListDelUser(String userid,UserBean suserBean) throws Exception;
}
