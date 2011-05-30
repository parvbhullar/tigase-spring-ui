package com.ivyinfo.im.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivyinfo.communication.services.CommunicationServices;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.im.client.ICientEngine;
import com.ivyinfo.im.db.conversion.JsonConversion;
import com.ivyinfo.mail.services.AuxiliaryMailServices;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.zy.im.client.service.ClientSession;
import com.zy.im.client.service.ClientThreadState;

public class LogoutServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);
	
	//private IMServices imServices = (IMServices) SpringContextUtil.getBean("IMServices");

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			LOGGER.debug("===into LogoutServlet===post");
			request.setCharacterEncoding("utf-8");

			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding("utf-8");

			HttpSession session = request.getSession();
			SessionUserBean sessionUserBean = (SessionUserBean) session.getAttribute("sessionUserBean");
			
			UserBean userBean = new UserBean();
			userBean = sessionUserBean.getUserBean();
			String loginName = userBean.getLogname();
			int p = loginName.indexOf("@");
			if(p>-1)
				loginName = loginName.substring(0, p);
			
			ICientEngine clientEngineServices = (ICientEngine)SpringContextUtil.getBean("clientEngineServices"); 
			
			String jsonObj = "";
			if(true){
				
			}else{
			}
			
			response.getWriter().print(jsonObj.toString());

		} catch (Exception ex) {
			String jsonObj = "";
			try {
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().print(jsonObj.toString());
		}

	}
}
