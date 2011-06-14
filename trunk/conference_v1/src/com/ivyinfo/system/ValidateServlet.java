package com.ivyinfo.system;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidateServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Validate valid=new Validate();
		response.setHeader("Pragma","No-cache");     
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires",0); 
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action=(String)request.getParameter("action");
		LOGGER.info("action="+action);
		if("validorgcreate".equals(action)){
			try {
				String jsonObj=valid.validOrgCreate(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validorgupdate".equals(action)){
			try {
				String jsonObj=valid.validOrgUpdate(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validusercreate".equals(action)){
			try {
				String jsonObj=valid.validUserCreate(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validuserupdate".equals(action)){
			try {
				String jsonObj=valid.validUserUpdate(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validpersonal".equals(action)){
			try {
				String jsonObj=valid.validPersonal(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validorgname".equals(action)){
			try {
				String jsonObj=valid.validOrgName(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validorglogname".equals(action)){
			try {
				String jsonObj=valid.validOrgLogname(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validusername".equals(action)){
			try {
				String jsonObj=valid.validUserName(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validuserlogname".equals(action)){
			try {
				String jsonObj=valid.validUserLogname(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validpassword".equals(action)){
			try {
				String jsonObj=valid.validPassword(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validresurepassword".equals(action)){
			try {
				String jsonObj=valid.validResurePassword(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validemail".equals(action)){
			try {
				String jsonObj=valid.validEmail(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validphone".equals(action)){
			try {
				String jsonObj=valid.validPhone(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validmobilephone".equals(action)){
			try {
				String jsonObj=valid.validMobilePhone(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validfax".equals(action)){
			try {
				String jsonObj=valid.validFax(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("validpostcode".equals(action)){
			try {
				String jsonObj=valid.validPostcode(request);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
