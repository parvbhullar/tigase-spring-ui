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

public class UserServlet extends HttpServlet{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServlet.class);
	private Object reqeuest;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setHeader("Pragma","No-cache");     
		response.setHeader("Cache-Control","no-cache");   
		response.setDateHeader("Expires",0); 
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action=(String)request.getParameter("action");
		LOGGER.info("action="+action);
		
		//获得用户信息！！以便查询用户安排会议
        HttpSession session = request.getSession();
        SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
        UserBean suserBean = sessionUserBean.getUserBean();
        PurviewBean purviewBean = sessionUserBean.getPurviewBean();
        System.err.println("MeetingServlet logname:"+suserBean.getLogname());
        System.err.println("MeetingServlet password:"+suserBean.getPassword());
		
		User user = new User();
		
		if("Adduser".equals(action)){
			try {
				user.Adduser(request,suserBean,purviewBean.getOrganizationid(),purviewBean);
				JSONObject  jsonObj=new JSONObject();
				jsonObj.put("result", "success");
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("viewuser".equals(action)){
				String id=(String)request.getParameter("id");
				try {
					String jsonObj = user.Viewuser(id);
					
					response.getWriter().print(jsonObj.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if("upd".equals(action)){
			String id=(String)request.getParameter("id");
			try {
				String jsonObj = user.Viewuser(id);
				System.err.println("jsonObj:"+jsonObj.toString());
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Upduser".equals(action)){
			try {
				System.err.println("email:"+request.getParameter("email"));
				user.Upduser(request,suserBean,purviewBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Deluser".equals(action)){
			String id=(String)request.getParameter("id");
			try {
				System.out.println("===========deleteUser==================");
				String jsonObj = user.Deluser(id,suserBean);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Addcard".equals(action)){
			try {
				user.Addcard(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("viewusercard".equals(action)){
			String id=(String)request.getParameter("id");
			try {
				String jsonObj = user.viewusercard(id);				
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Updcard".equals(action)){
			try {
				user.Updcard(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("viewusermail".equals(action)){
			String id=(String)request.getParameter("id");
			try {
				String jsonObj = user.viewusermail(id);				
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("viewpersonal".equals(action)){
			String id=suserBean.getId();
			try {
				String jsonObj = user.viewusermail(id);
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Updusersteup".equals(action)){
//			String id=(String)request.getParameter("id");
			try {
				user.Updusersteup(request,sessionUserBean,session);				
//				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Updusermailsteup".equals(action)){
			try {
				user.Updusermailsteup(request,sessionUserBean,session);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Updpersonalinfo".equals(action)){
			try {
				user.Updpersonalinfo(request,suserBean,purviewBean);
				JSONObject  jsonObj=new JSONObject();
				jsonObj.put("result", "success");
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			JSONObject jsonObj = null;
			try {
				jsonObj = user.getUserList(request,suserBean);				
				// 设置字符编码
	            response.setCharacterEncoding("UTF-8");
	            // 返回json对象（通过PrintWriter输出）
	            response.getWriter().print(jsonObj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
