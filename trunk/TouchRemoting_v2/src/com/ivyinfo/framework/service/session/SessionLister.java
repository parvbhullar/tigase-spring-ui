package com.ivyinfo.framework.service.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionLister implements HttpSessionAttributeListener,HttpSessionListener { 

	/* 
	* 当Session 创建时自动调用 
	*/ 
	public void sessionCreated(HttpSessionEvent arg0) { 
//	Session创建 
		HttpSession session = arg0.getSession(); 
		String userid = (String)session.getAttribute("m_strUserId");
		String machineid = (String)session.getAttribute("m_strMachineid");
		System.err.println("====sessionCreated=====userid:"+userid);
	} 

	/* 
	* 当Session 失失效时自动调用 
	*/ 
	public void sessionDestroyed(HttpSessionEvent arg0) { 
//	Session失效 
		HttpSession session = arg0.getSession(); 
		String userid = (String)session.getAttribute("m_strUserId");
		String machineid = (String)session.getAttribute("m_strMachineid");
		System.err.println("=====sessionDestroyed====userid:"+userid+"====machineid:"+machineid+"===sessionid:"+session.getId());
//		if(userid != null && !"".equals(userid)){
//			UserCookie uc = new UserCookie();
//			uc.del_usercount(userid, machineid, null);
//		}
	}

	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		//System.err.println("====attributeAdded====");
		
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		//System.err.println("====attributeRemoved====");
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		//System.err.println("====attributeReplaced====");
	} 

}
