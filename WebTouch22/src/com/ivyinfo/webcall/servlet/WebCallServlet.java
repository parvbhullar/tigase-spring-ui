package com.ivyinfo.webcall.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivyinfo.session.bean.SessionUserBean;

public class WebCallServlet extends HttpServlet{
	private static final Logger LOGGER = LoggerFactory.getLogger(WebCallServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setHeader("Pragma","No-cache");     
		response.setHeader("Cache-Control","no-cache");   
		response.setDateHeader("Expires",0); 
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action=(String)request.getParameter("action");
		action = (action == null)?"":action;
		LOGGER.info("action="+action);
		HttpSession session = request.getSession();
		SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		
		try{
			if("callnumber".equals(action)){
				WebCall webCall = new WebCall();
				String returnvalue = webCall.CallPhone(request,sessionUserBean.getUserBean());
				System.err.println("returnvalue:"+returnvalue);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
