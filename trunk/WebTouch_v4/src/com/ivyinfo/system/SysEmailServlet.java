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

public class SysEmailServlet extends HttpServlet{
	private static final Logger LOGGER = LoggerFactory.getLogger(SysEmailServlet.class);
	
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
			SysEmail sysEmail = new SysEmail();
		
			if("AddSysMail".equals(action)){
				LOGGER.info("=========Addorg=============");
				try {
					sysEmail.AddSysMail(request);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("ViewSysMail".equals(action)){
				LOGGER.info("=========view=============");
					String id=(String)request.getParameter("id");
					try {
						String jsonObj = sysEmail.ViewSysMail(id);
						
						response.getWriter().print(jsonObj.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
			}else if("UpdSysMail".equals(action)){
				LOGGER.info("=========Updorg=============");
				try {
					sysEmail.UpdSysMail(request);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("DelSysMail".equals(action)){
				LOGGER.info("=========Delorg=============");
				String id=(String)request.getParameter("id");
				try {
					sysEmail.DelSysMail(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				LOGGER.info("=========NULL=============");
				JSONObject jsonObj = null;
				try {
					jsonObj = sysEmail.getMailList(request);
					
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
