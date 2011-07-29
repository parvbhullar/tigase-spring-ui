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

import com.ivyinfo.purview.bean.PurviewBean;
import com.ivyinfo.user.bean.UserBean;

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

		
		User user = new User();
		
		if("Adduser".equals(action)){
			try {
				JSONObject  jsonObj=new JSONObject();
				jsonObj.put("result", "success");
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("viewuser".equals(action)){
				String id=(String)request.getParameter("id");
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if("upd".equals(action)){
			String id=(String)request.getParameter("id");
			try {
			} catch (Exception e) {
			}
		}else if("Upduser".equals(action)){
			try {
				System.err.println("email:"+request.getParameter("email"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Deluser".equals(action)){
			String id=(String)request.getParameter("id");
			try {
				System.out.println("===========deleteUser==================");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Addcard".equals(action)){
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("viewusercard".equals(action)){
			String id=(String)request.getParameter("id");
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Updcard".equals(action)){
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("viewusermail".equals(action)){
			String id=(String)request.getParameter("id");
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("viewpersonal".equals(action)){
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Updusersteup".equals(action)){
//			String id=(String)request.getParameter("id");
			try {
//				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Updusermailsteup".equals(action)){
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("Updpersonalinfo".equals(action)){
			try {
				JSONObject  jsonObj=new JSONObject();
				jsonObj.put("result", "success");
				response.getWriter().print(jsonObj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			JSONObject jsonObj = null;
			try {
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
