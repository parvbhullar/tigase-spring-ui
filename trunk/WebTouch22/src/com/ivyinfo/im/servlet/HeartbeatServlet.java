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

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.im.client.ICientEngine;
import com.ivyinfo.im.db.conversion.JsonConversion;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.zy.im.client.service.ClientSession;
import com.zy.im.client.service.ClientThreadState;

public class HeartbeatServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HeartbeatServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		/*try {
			request.setCharacterEncoding("utf-8");

			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-ftgf", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding("utf-8");

			HttpSession session = request.getSession();
			SessionUserBean sessionUserBean = (SessionUserBean) session.getAttribute("sessionUserBean");

			String jsonObj = "";
			
			if(sessionUserBean != null && sessionUserBean.getCsession() != null){
				ClientSession csession = sessionUserBean.getCsession();
			
				if(csession.getState() == ClientThreadState.LISTENING){
					ICientEngine clientEngineServices = (ICientEngine)SpringContextUtil.getBean("clientEngineServices"); 
					clientEngineServices.readyMsg(csession);
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
		}*/

	}
}
