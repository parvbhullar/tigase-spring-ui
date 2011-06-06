package com.ivyinfo.meeting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ivyinfo.communication.bean.CommunicationBean;
import com.ivyinfo.communication.services.CommunicationServices;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.meeting.bean.MeetingDetailBean;
import com.ivyinfo.meeting.bean.MeetingReturnDetailBean;
import com.ivyinfo.meeting.services.MeetingServices;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;

public class Meeting {
	private static final long serialVersionUID = 1L;
	private MeetingServices meetingServices = (MeetingServices) SpringContextUtil
	.getBean("meetingServices");
	private CommunicationServices communicationServices = (CommunicationServices)SpringContextUtil
	.getBean("communicationService"); 
	
	//添加固定会议
	public String addFixedMeeting(HttpServletRequest request, HttpServletResponse response,SessionUserBean sessionUserBean){
		System.out.println("addFixedMeeting");
		//获得参与者的名单,注意tokenName如果在使用插件时没有自定义，则是固定的！！！
//		String[] attend = request.getParameterValues("tokenName");
//		List attendees = new ArrayList();
//		HashMap hashmap = null;
//		if(attend!=null)
//			for(int i = 0;i < attend.length;i++){
//				hashmap = new HashMap();
//				String[] info = attend[i].split("\\|");
//				String name = info[0];
//				String email = info[1];
//				String phone = info[2];
//
//				hashmap.put("name", name);
//				hashmap.put("email", email);
//				hashmap.put("phone", phone);
//				attendees.add(hashmap);
//			}
		//参与者名单，此处为死值，以后修改
		List attendees = new ArrayList();
//		HashMap hashmap1 = new HashMap();
//		hashmap1.put("name", "cc");
//		hashmap1.put("email", "cc.163.com");
//		hashmap1.put("phone", "13951854967");
//		HashMap hashmap2 = new HashMap();
//		hashmap2.put("name", "tt");
//		hashmap2.put("email", "tt.163.com");
//		hashmap2.put("phone", "13951854067");
//		attendees.add(hashmap1);
//		attendees.add(hashmap2);
		
		String subject = request.getParameter("subject");
		String rpsw = request.getParameter("resurePasswd");
		String psw = request.getParameter("passwd");
		String endTime = request.getParameter("endTime");
		String startTime = request.getParameter("startTime");
		String timeZone = "45";//request.getParameter("timeZone1");
		//String confPattern = request.getParameter("confPattern1");
		String attendNum = request.getParameter("attendeeAmount");
		String allowedJoinTime = request.getParameter("allowedJoinTime");
		String describe = request.getParameter("agenda");
		String notOpen = request.getParameter("openType");
		String openType = "true" ;
		if(notOpen != null&&notOpen.equals("on"))
			openType = "false";
		else
			openType = "true";
		String mustLogin = request.getParameter("mustLogin");
		
		System.out.println("subject : " + subject);
		System.out.println("resurePsw : " + rpsw);
		System.out.println("psw : " +psw );
		System.out.println("endTime : "+ endTime);
		System.out.println("startTime : " + startTime);
		System.out.println("timeZone : " + timeZone);
		//System.out.println("confPattern : " + confPattern);
		System.out.println("attendNum : " + attendNum);
		System.out.println("allowedJoinTime : " + allowedJoinTime);
		System.out.println("describe : " + describe);
		System.out.println("openType : "+ openType);
		System.out.println("mustLogin : "+ mustLogin);
		
		MeetingDetailBean meetingDetailBean = new MeetingDetailBean();
		meetingDetailBean.setSubject(subject);
		meetingDetailBean.setPasswd(psw);
		meetingDetailBean.setStartTime(startTime);
		meetingDetailBean.setEndTime(endTime);
		//meetingDetailBean.setConferencePattern(confPattern);
		meetingDetailBean.setAttendeeAmount(attendNum);
		//meetingDetailBean.setHostName(host);
		meetingDetailBean.setAgenda(describe);
		meetingDetailBean.setOpenType(openType);
		meetingDetailBean.setAttendee(attendees);
		try {
			UserBean userBean = sessionUserBean.getUserBean();
			
			String returnerr = meetingServices.Addfix(meetingDetailBean,userBean);
			return returnerr;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//查看所有会议
	public JSONObject listMeeting(HttpServletRequest request, HttpServletResponse response,SessionUserBean sessionUserBean)throws ServletException, IOException{
		//显示会议列表,获取未开始的所有会议
		String page1=(String)request.getParameter("page");//未进行的会议的当前页
		String sidx=(String)request.getParameter("sidx");
		String sord=(String)request.getParameter("sord");
		//System.out.println("page : " + page1);
		if(page1 == null||page1.trim().equals(""))
			page1 = "1";
		int page = Integer.parseInt(page1);
		int totalPage = 0;
		int totalRecords = 0;
		int nextRecord = (page-1)*10;//用来翻页

		List meetingList = null;
		String logname = "";
		String username = "";
		try {
			UserBean userBean = sessionUserBean.getUserBean();
			
			logname = userBean.getLogname();
			username = userBean.getName();
			
			meetingList = meetingServices.MeetingList(userBean);
			totalRecords = meetingList.size();
			if(totalRecords%10 == 0)
				totalPage = totalRecords/10;
			else
				totalPage = totalRecords/10 + 1;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 // 定义返回的数据类型：json，使用了json-lib
        JSONObject jsonObj = new JSONObject();
        // 根据jqGrid对JSON的数据格式要求给jsonObj赋值
        jsonObj.put("page", page);                // 当前页
        jsonObj.put("total",totalPage);        // 总页数
        jsonObj.put("records", totalRecords);        // 总记录数
        
     // 定义rows，存放数据
        MeetingReturnDetailBean mrdbean ;
        JSONArray rows = new JSONArray();
        for(int i = nextRecord;i < nextRecord+10&&i < meetingList.size();i++){
        	mrdbean = (MeetingReturnDetailBean)meetingList.get(i);
        	
        	String hostname = mrdbean.getHostName();
        	System.err.println("hostname:"+hostname);
        	// 存放一条记录的对象
            JSONObject cell = new JSONObject();
            cell.put("confKey",mrdbean.getConfKey());
            cell.put("confPattern", mrdbean.getConfType());
            cell.put("startTime", mrdbean.getStartTime());
            cell.put("subject", mrdbean.getSubject());
            cell.put("host", hostname);
            cell.put("duringTime", mrdbean.getDuringTime());
            
//            System.out.println("confkey:"+mrdbean.getConfKey());
//            System.out.println("confPattern:"+ mrdbean.getConfType());
//            System.out.println("startTime:"+ mrdbean.getStartTime());
//            System.out.println("subject:"+ mrdbean.getSubject());
//            System.out.println("host:"+ hostname);
//            System.out.println("duringTime:"+ mrdbean.getDuringTime());
            if(hostname.equals(username)){
            	cell.put("operation", "开启");
            }else{
            	cell.put("operation", "加入");
            }
            rows.add(cell);
        }
        jsonObj.put("rows", rows);
        return jsonObj; 
        
	}
	
	//获得用户的通讯录，以便在创建会议，修改会议时自由选择
	public JSONArray getContact(HttpServletRequest request, HttpServletResponse response,SessionUserBean sessionUserBean){
		UserBean userBean=(UserBean)sessionUserBean.getUserBean();
		
		//这里设置User的登录名，需要修改
		String logname = userBean.getLogname();
		System.out.println("logname:" + logname);
		JSONArray jsonObj = new JSONArray();
		try {			
//			CommunicationBean communicationBean;
//			List attendeeList = communicationServices.AllIndex(logname,"");
//			for(int i = 0;i < attendeeList.size();i++){
//				communicationBean = (CommunicationBean) attendeeList.get(i);
//				String value = communicationBean.getName()+"|"+communicationBean.getEmail()+
//								"|" + communicationBean.getPhone();
//				JSONObject cell = new JSONObject();
//				cell.put("name", value);
//				cell.put("id", value);
//				jsonObj.add(cell);
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonObj=null;
		}
		return jsonObj;
	}

}
