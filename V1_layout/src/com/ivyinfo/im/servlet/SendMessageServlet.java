package com.ivyinfo.im.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.im.client.ICientEngine;
import com.ivyinfo.im.controller.TrafficController;
import com.ivyinfo.im.db.conversion.JsonConversion;
import com.ivyinfo.im.listener.ChatSender;
import com.ivyinfo.im.meettouch.bean.MeetTouchMessage;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.zy.im.client.service.ClientSession;
import com.zy.im.client.service.ClientThreadState;
import com.zy.im.client.service.MessageType;
import com.zy.im.core.Message;

public class SendMessageServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(SendMessageServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			request.setCharacterEncoding("utf-8");

			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding("utf-8");
			
			String toNiceUserName = request.getParameter("to");
			String toUserid = request.getParameter("userid");
			String toMessage = request.getParameter("message");
			
			HttpSession session = request.getSession();
			SessionUserBean sessionUserBean = (SessionUserBean) session.getAttribute("sessionUserBean");

			UserBean userBean = new UserBean();
			userBean = sessionUserBean.getUserBean();
			//处理用户名！！
			String loginName = userBean.getLogname();
			int p = loginName.indexOf("@");
			if(p>-1)
				loginName = loginName.substring(0, p);
			
			String jsonObj = "";
			
			if(sessionUserBean != null && sessionUserBean.getCsession() != null){
				ClientSession csession = sessionUserBean.getCsession();
			
				if(csession.getState() == ClientThreadState.LISTENING){
					//ICientEngine clientEngineServices = (ICientEngine)SpringContextUtil.getBean("clientEngineServices"); 
					ChatSender chatSender = TrafficController.getSender(loginName);
					MeetTouchMessage mtm = new MeetTouchMessage();
					mtm.setTouser(toUserid);
					mtm.setNicetouser(toNiceUserName);
					
					mtm.setMessage(toMessage);
					mtm.setSenduser(loginName);
					mtm.setNicesenduser(sessionUserBean.getUserBean().getNickname());
					mtm.setSendtime(TimeTools.getString());
					mtm.setMessagetype(MessageType.IM_MEETING_TOUCH);
					
					Message msg = new Message();
			        msg.setCommand(mtm);
					
					//发送信息
					chatSender.sendMsg(msg);
					csession.setState(ClientThreadState.LISTENING);
					jsonObj = JsonConversion.Heartbeat_OK_String_json();
				}else{
					jsonObj = JsonConversion.Heartbeat_ERROR_String_json("ClientThreadState is "+csession.getState());
				}
			}else{
				//throw new Exception("未登陆");
			}
			response.getWriter().print(jsonObj.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
