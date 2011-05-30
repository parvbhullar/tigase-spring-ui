package com.ivyinfo.webcall.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivyinfo.framework.common.file.ResouceLoader;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.im.client.ICientEngine;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.zy.im.client.service.ClientSession;
import com.zy.im.client.service.ClientThreadState;

public class LoginServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
	
	//private IMServices imServices = (IMServices) SpringContextUtil.getBean("IMServices");

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			LOGGER.debug("===into LoginServlet===post");
			request.setCharacterEncoding("utf-8");

			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding("utf-8");

			HttpSession session = request.getSession();
			SessionUserBean sessionUserBean = (SessionUserBean) session.getAttribute("sessionUserBean");
			
			UserBean userBean = new UserBean();
			userBean = sessionUserBean.getUserBean();
			//处理用户名！！
			String loginName = userBean.getLogname();
			int p = loginName.indexOf("@");
			if(p>-1)
				loginName = loginName.substring(0, p);
			
			System.out.println("loginName : " + loginName);
			
			if(true){
			
				ICientEngine clientEngineServices = (ICientEngine)SpringContextUtil.getBean("clientEngineServices"); 
				
				ResouceLoader resouceloader = new ResouceLoader();
				String imServerURL = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/httpInvoker.properties", "imServiceURL");
				String imServicePORT = resouceloader.getXMLUrl("/com/ivyinfo/framework/service/config/httpInvoker.properties", "imServicePORT");
				//登录
				ClientSession csession = clientEngineServices.Login(imServerURL, Integer.parseInt(imServicePORT), loginName, userBean.getPassword());
				String jsonObj = "";
				if(csession !=null && csession.getState() == ClientThreadState.CONNECTED){
					csession.setState(ClientThreadState.LISTENING);
					//添加聊天时的监听类和添加好友监听类
					if(csession.isListened()==false){
						clientEngineServices.addChatListener(loginName, csession);
						clientEngineServices.addRosterListener(loginName, csession);
					}
					
					session.setAttribute("sessionUserBean", sessionUserBean);
					
					//这里要组建好友数据			
				}else{
				}
				response.getWriter().print(jsonObj.toString());
			}
			
			

		} catch (Exception ex) {
			String jsonObj = "";
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().print(jsonObj.toString());
		}

	}
}
