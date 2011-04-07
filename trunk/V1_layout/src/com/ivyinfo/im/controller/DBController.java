package com.ivyinfo.im.controller;

import java.util.List;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.immessage.bean.IMMessageBean;
import com.ivyinfo.immessage.services.IMMessageServices;

public class DBController {
	private static IMMessageServices immServices = (IMMessageServices) SpringContextUtil.getBean("imMessageServices");
	
	//将信息保存到数据库中
	public static void push(String from,String fromNick,String to,String toNick,String message,String type){
		IMMessageBean immBean = new IMMessageBean();
		if(type.equals("send")){
			System.out.println("send!!");
			immBean.setMessage(message);
			immBean.setSenduser(from);
			immBean.setTouser(to);
			immBean.setSavename(from);
			if(fromNick==null||fromNick.equals(""))
				immBean.setNicesenduser(from);
			else
				immBean.setNicesenduser(fromNick);
			immBean.setNicetouser(toNick);
			immBean.setReadflag("1");
			immBean.setMessagetype("1");
			System.out.println("send message : sendUser : " + immBean.getSenduser() +" sendnickname : " + immBean.getNicesenduser()+ " toUser :" + immBean.getTouser() + " message : " + message + " toNickName : " + immBean.getNicetouser() );
		}else if(type.equals("recieve")){
			System.out.println("recieve!!!");
			int i = to.indexOf("@");
			if(i>-1)
				to = to.substring(0, i);
			i = toNick.indexOf("@");
			if(i>-1)
				toNick = toNick.substring(0, i);
			i = fromNick.indexOf("@");
			if(i>-1)
				fromNick = fromNick.substring(0,i);
			System.out.println("recieve message : sendUser : " + from +" sendnickname : " + fromNick+ " toUser :" + to + " message : " + message + " toNickName : " + toNick );
			immBean.setMessage(message);
			immBean.setSenduser(from);
			immBean.setTouser(to);
			immBean.setSavename(to);
			immBean.setNicesenduser(fromNick);
			immBean.setNicetouser(toNick);
			immBean.setReadflag("0");
			immBean.setMessagetype("2");
		}
		try {
			immServices.AddIMMessage(immBean);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("push compelte!!!");
	}
	
	//取出用户信息
	public static List fetchUserMessage(String user , String type){
		List list = null;
		if(type.equals("recieve")){
			try {
				//System.out.println("fetch User message user : " + user);
				list = immServices.AllIMMessage_ReadAndType(user, "2", "0");
				setDelivered(list,user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type.equals("send")){
			
		}
		if(list==null)
			System.out.println("list is null");	
		return list;
	}
	
	//标记为已读
	public static void setDelivered(List list,String user){
		if(list!=null&&list.size()>0){
			System.out.println("setRead!!!");
			IMMessageBean immBean = null;
			for(int i = 0;i < list.size();i++){
				immBean = (IMMessageBean) list.get(i);
				String id = immBean.getId();
				System.out.println("set read  text : " + immBean.getMessage());
				immBean.setId(id);
				immBean.setSavename(user);
				immBean.setReadflag("1");
				try {
					immServices.UpdIMMessage(immBean);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
