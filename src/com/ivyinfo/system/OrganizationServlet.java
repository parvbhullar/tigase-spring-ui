package com.ivyinfo.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivyinfo.framework.common.validate.ValidatePattern;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.bean.FileBean;
import com.ivyinfo.mail.bean.MailUtilBean;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.organization.bean.OrganizationBean;
import com.ivyinfo.organization.services.OrganizationServices;
import com.ivyinfo.purview.bean.PurviewBean;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.util.Constant;
import com.ivyinfo.util.Page;

public class OrganizationServlet extends HttpServlet{
	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServlet.class);
	
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
			Organization organization = new Organization();
		
			if("Addorg".equals(action)){
				LOGGER.info("=========Addorg=============");
				try {
					organization.AddOrg(request,sessionUserBean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("view".equals(action)){
				LOGGER.info("=========view=============");
					String id=(String)request.getParameter("id");
					try {
						String jsonObj = organization.View(id);
						
						response.getWriter().print(jsonObj.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
			}else if("upd".equals(action)){
				LOGGER.info("=========upd=============");
				String id=(String)request.getParameter("id");
				try {
					String jsonObj = organization.View(id);
					System.err.println("jsonObj:"+jsonObj.toString());
					
					response.getWriter().print(jsonObj.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("Updorg".equals(action)){
				LOGGER.info("=========Updorg=============");
				try {
					organization.Updorg(request,sessionUserBean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("Delorg".equals(action)){
				LOGGER.info("=========Delorg=============");
				String id=(String)request.getParameter("id");
				try {
					organization.Delorg(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("setpermissions".equals(action)){
				LOGGER.info("=========setpermissions=============");
				String orgid=(String)request.getParameter("id");
				JSONObject jsonObj = null;
				try {
					jsonObj = organization.SetPermissions(orgid);
					
					// 设置字符编码
		            response.setCharacterEncoding("UTF-8");
		            // 返回json对象（通过PrintWriter输出）
		            response.getWriter().print(jsonObj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("getorgpurview".equals(action)){
				LOGGER.info("=========getorgpurview=============");
				JSONArray jsonObj = null;
				try {
					jsonObj = organization.getOrgPurview();
					
					// 设置字符编码
		            response.setCharacterEncoding("UTF-8");
		            // 返回json对象（通过PrintWriter输出）
		            response.getWriter().print(jsonObj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("Addpurview".equals(action)){
				LOGGER.info("=========Addpurview=============");
				try {
					organization.Addpurview(request);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				LOGGER.info("=========NULL=============");
				JSONObject jsonObj = null;
				try {
					jsonObj = organization.getOrgList(request);
					
					// 设置字符编码
		            response.setCharacterEncoding("UTF-8");
		            // 返回json对象（通过PrintWriter输出）
		            response.getWriter().print(jsonObj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
