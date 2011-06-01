package com.ivyinfo.system;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.Mapping;

import com.ivyinfo.session.bean.SessionUserBean;

/**
 * Servlet implementation class RegisterOrganizationServlet
 */
public class RegisterOrganizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServlet.class);  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Pragma","No-cache");     
		response.setHeader("Cache-Control","no-cache");   
		response.setDateHeader("Expires",0); 
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action=(String)request.getParameter("action");
		action = (action == null)?"":action;
		LOGGER.info("action="+action);
		
		try{
			Organization organization = new Organization();
			JSONObject  jsonObj=new JSONObject();
			if("RegisterOrg".equals(action)){
				LOGGER.info("=========RegisterOrg=============");
				try {
					organization.RegisterOrg(request);
					jsonObj.put("result", "success");
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.getWriter().print(jsonObj.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
