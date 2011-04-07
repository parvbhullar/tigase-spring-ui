package com.ivyinfo.im.db.conversion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;

import com.ivyinfo.communication.bean.CommunicationBean;
import com.ivyinfo.communication.services.CommunicationServices;
import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.im.controller.TrafficController;
import com.ivyinfo.im.listener.ChatListener;
import com.ivyinfo.im.meettouch.bean.MeetTouchMessage;
import com.ivyinfo.im.meettouch.services.IIMServices;
import com.ivyinfo.immessage.bean.IMMessageBean;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.user.services.UserServices;
import com.zy.im.client.service.ClientSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonConversion {
	
	
	public static String Heartbeat_OK_String_json() throws Exception{
		JSONObject  jsonObj=new JSONObject();
		
		jsonObj.put("stuts", "READY");
		
		return jsonObj.toString();
	}
	
	public static String Heartbeat_ERROR_String_json(String errorMessage) throws Exception{
		JSONObject  jsonObj=new JSONObject();
		
		jsonObj.put("stuts", "Heartbeat ERROR："+errorMessage);
		
		return jsonObj.toString();
	}
	
	public static String login_Pass_String_json(SessionUserBean sessionUserBean) throws Exception{
		try{
			JSONObject  jsonObj=new JSONObject();
			ClientSession cSession = sessionUserBean.getCsession();
			XMPPConnection conn = null;
			if(cSession != null){
				conn = cSession.getConnection();
				if(conn != null){
					//获得花名册
					Roster roster = conn.getRoster();
					ArrayList contacts = new ArrayList();
					RosterEntry en = null;
					
					//组织JSON数据，以便传入前台
					JSONArray nodes = new JSONArray();					
					//[{id:1, pId:0, name:"手机", ename:"Mobile", isParent:true,open:true},{ id:11, pId:1, name:"诺基亚", ename:"Nokia"},{ id:12, pId:1, name:"C6(音乐版)", ename:"C6(Music)"},{id:2, pId:0, name:"手机1", ename:"Mobile", isParent:true,open:true},{ id:21, pId:2, name:"诺基亚1", ename:"Nokia"},{ id:22, pId:2, name:"C6(音乐版)", ename:"C6(Music)"}];
					JSONObject cell = new JSONObject();
					cell.put("id", 1);
					cell.put("pId", 0);
					cell.put("name", " 我的好友");
					cell.put("isParent", true);
					cell.put("open", false);
					nodes.add(cell);					
					int j = 0;
					for (Iterator i=roster.getEntries().iterator(); i.hasNext(); ){
						en = (RosterEntry)i.next();
						cell = new JSONObject();
						cell.put("id", Integer.parseInt(""+1+j));
						cell.put("pId", 1);
						cell.put("name", en.getName());
						cell.put("userid", en.getUser());
						cell.put("loginname", en.getUser());
						nodes.add(cell);
						j++;
					}
					jsonObj.put("nodes", nodes);
					return jsonObj.toString();
				}
			}
			
			/*CommunicationServices communicationServices = (CommunicationServices)SpringContextUtil.getBean("communicationService"); 
			List attendeeList = communicationServices.AllIndex(0,0,sessionUserBean.getUserBean().getLogname(),null,null);
			JSONArray nodes = new JSONArray();
			
			//[{id:1, pId:0, name:"手机", ename:"Mobile", isParent:true,open:true},{ id:11, pId:1, name:"诺基亚", ename:"Nokia"},{ id:12, pId:1, name:"C6(音乐版)", ename:"C6(Music)"},{id:2, pId:0, name:"手机1", ename:"Mobile", isParent:true,open:true},{ id:21, pId:2, name:"诺基亚1", ename:"Nokia"},{ id:22, pId:2, name:"C6(音乐版)", ename:"C6(Music)"}];
			JSONObject cell = new JSONObject();
			cell.put("id", 1);
			cell.put("pId", 0);
			cell.put("name", " 我的好友");
			cell.put("isParent", true);
			cell.put("open", false);
			nodes.add(cell);
			for(int i=0; i<attendeeList.size(); i++){
				CommunicationBean communicationBean = (CommunicationBean) attendeeList.get(i);
				cell = new JSONObject();
				cell.put("id", Integer.parseInt(""+1+i));
				cell.put("pId", 1);
				cell.put("name", communicationBean.getName());
				cell.put("userid", communicationBean.getId());
				cell.put("loginname", communicationBean.getLogname());
				nodes.add(cell);
			}
	
			UserServices userServices = (UserServices) SpringContextUtil.getBean("userServices");
			List arrayList=userServices.AllIndex(0, 0,"2",sessionUserBean.getUserBean().getOrganizationid(),null,null);
			cell.put("id", 2);
			cell.put("pId", 0);
			cell.put("name", " 企业好友");
			cell.put("isParent", true);
			cell.put("open", true);
			nodes.add(cell);
			for(int i=0; i<arrayList.size(); i++){
				UserBean userBean =  (UserBean)arrayList.get(i);
				cell = new JSONObject();
				cell.put("id", Integer.parseInt(""+2+i));
				cell.put("pId", 2);
				cell.put("name", userBean.getNickname());
				cell.put("userid", userBean.getId());
				cell.put("loginname", userBean.getLogname());
				nodes.add(cell);
			}*/
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		return null;
	}
	
	public static String listener_haveMessage_String_json(String logname) throws Exception{
		JSONObject  jsonObj=new JSONObject();
		ArrayList unReadMessages = null;
		//读取消息
		ChatListener listener = TrafficController.getListener(logname);
		if(listener!=null){
			unReadMessages = (ArrayList) listener.getUnreadMessages();
			
			JSONArray nodes = new JSONArray();
			IMMessageBean immBean = null;
			//组织未读取信息格式
			if (unReadMessages != null && unReadMessages.size() > 0) {
				System.out.println("have message!!!!!");
				for(int i = 0;i < unReadMessages.size();i++){
					JSONObject cell = new JSONObject();
					immBean = (IMMessageBean)unReadMessages.get(i);
					String from = immBean.getSenduser();
					String to = immBean.getTouser();
					
					if(from.indexOf("/")>-1){
						int j = from.indexOf("/");
						from = from.substring(0, j);
					}
					cell.put("senduser", from);
					if(from.indexOf("@")>-1){
						int j = from.indexOf("@");
						from = from.substring(0, j);
					}	
					cell.put("touser", to);
					cell.put("sendtime", "2011-11-1 10:10:10");
					cell.put("message", immBean.getMessage());
					cell.put("nicesenduser", from);
					cell.put("nicetouser", to);
					nodes.add(cell);
				}
				jsonObj.put("nodes", nodes);
			}else
				jsonObj.put("nodes", null);
		}
		/*
        try{
        	MeetTouchMessage meetTouchmsg = new MeetTouchMessage();
	        //读取消息
	        IIMServices imServices = (IIMServices) SpringContextUtil.getBean("imServices");
	        
			meetTouchmsg.setTouser(logname);
			meetTouchmsg.setReadflag("0");
			List lsmeetTouchmsg = imServices.findMeetTouchMessageById(meetTouchmsg);
			JSONArray nodes = new JSONArray();
			if(lsmeetTouchmsg.size()>0){
				for(int i=0; i<lsmeetTouchmsg.size(); i++){
					JSONObject cell = new JSONObject();
					meetTouchmsg = (MeetTouchMessage)lsmeetTouchmsg.get(i);
					cell.put("senduser", meetTouchmsg.getSenduser());
					cell.put("touser", meetTouchmsg.getTouser());
					cell.put("sendtime", meetTouchmsg.getSendtime());
					cell.put("message", meetTouchmsg.getMessage());
					cell.put("nicesenduser", meetTouchmsg.getNicesenduser());
					cell.put("nicetouser", meetTouchmsg.getNicetouser());
					nodes.add(cell);
					
					
					meetTouchmsg.setReadtime(TimeTools.getString());
					meetTouchmsg.setReadflag("1");
					imServices.updateMeetTouchMessage(meetTouchmsg);
					
				}
				jsonObj.put("nodes", nodes);
			}else{
				jsonObj.put("nodes", null);
			}
			
        }catch(Exception ex){
        	
        }*/	
		return jsonObj.toString();
	}
	
	
	public static String login_Error_String_json() throws Exception{
		JSONObject  jsonObj=new JSONObject();
		
		jsonObj.put("stuts", "login ERROR");
		
		return jsonObj.toString();
	}
	
	public static String logout_Pass_String_json() throws Exception{
		JSONObject  jsonObj=new JSONObject();
		
		jsonObj.put("stuts", "BYE");
		
		return jsonObj.toString();
	}
	
	public static String logout_Error_String_json(String errorMessage) throws Exception{
		JSONObject  jsonObj=new JSONObject();
		
		jsonObj.put("stuts", "logout ERROR："+errorMessage);
		
		return jsonObj.toString();
	}
}
