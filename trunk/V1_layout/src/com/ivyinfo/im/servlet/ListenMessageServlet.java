package com.ivyinfo.im.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.sequence.ISequence;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.im.client.ICientEngine;
import com.ivyinfo.im.db.conversion.JsonConversion;
import com.ivyinfo.im.demo.bean.TestMessage;
import com.ivyinfo.im.meettouch.bean.MeetTouchMessage;
import com.ivyinfo.im.meettouch.services.IIMServices;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.zy.im.client.message.listener.MessageParserException;
import com.zy.im.client.service.ClientSession;
import com.zy.im.client.service.ClientThreadState;
import com.zy.im.core.GenericCommand;
import com.zy.im.core.Message;

public class ListenMessageServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(ListenMessageServlet.class);

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

			HttpSession session = request.getSession();
			SessionUserBean sessionUserBean = (SessionUserBean) session.getAttribute("sessionUserBean");

			String jsonObj = "";
			
			UserBean userBean = new UserBean();
			userBean = sessionUserBean.getUserBean();
			//处理用户名！！
			String loginName = userBean.getLogname();
			int p = loginName.indexOf("@");
			if(p>-1)
				loginName = loginName.substring(0, p);

			if (sessionUserBean != null && sessionUserBean.getCsession() != null) {
				ClientSession csession = sessionUserBean.getCsession();

				if (csession.getState() == ClientThreadState.LISTENING) {
					jsonObj = JsonConversion.listener_haveMessage_String_json(loginName);
				} else {
					jsonObj = JsonConversion.Heartbeat_ERROR_String_json("ClientThreadState is "
									+ csession.getState());
				}
				
			} else {
				// throw new Exception("未登陆");
				jsonObj = JsonConversion.Heartbeat_ERROR_String_json("");
			}
			
			response.getWriter().print(jsonObj.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
