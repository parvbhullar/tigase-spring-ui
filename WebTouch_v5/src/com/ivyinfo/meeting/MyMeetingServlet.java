package com.ivyinfo.meeting;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.meeting.bean.MeetingDetailBean;
import com.ivyinfo.meeting.bean.MeetingReturnDetailBean;
import com.ivyinfo.meeting.services.MeetingServices;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;

/**
 * Servlet implementation class MyArrangeMeetingServlet
 */
public class MyMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetingServices meetingServices = (MeetingServices) SpringContextUtil
	.getBean("meetingServices");   
	private static Meeting meeting = new Meeting();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyMeetingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma","No-cache");     
		response.setHeader("Cache-Control","no-cache");   
		response.setDateHeader("Expires",0); 		
		request.setCharacterEncoding("utf-8");  
		String action = request.getParameter("action");
		System.out.println("my action:" + action);
		
		//获得用户信息！！以便查询用户安排会议
        HttpSession session = request.getSession();
        SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		if(action!=null&&action.equals("MyMeetingListNot")){
			listMeeting(request, response, sessionUserBean);
		}
		else if(action!=null&&action.equals("myInviteMeeting")){
			//这里是获得用户被邀请的会议，待修改
			//listMyArgMeeting(request,response);
		}
		else if(action!=null&&action.equals("myArrangeMeeting")){
			//这里获得用户自己安排的会议
			listMyArgMeeting(request,response,sessionUserBean);
		}
	}
	
	public void listMyArgMeeting(HttpServletRequest request, HttpServletResponse response,SessionUserBean sessionUserBean)throws ServletException, IOException{

	}
	
	//显示所有会议
	public void listMeeting(HttpServletRequest request, HttpServletResponse response,SessionUserBean sessionUserBean) throws ServletException, IOException{
		JSONObject jsonObj = meeting.listMeeting(request, response, sessionUserBean);
		//设置字符编码
        response.setCharacterEncoding("UTF-8");
        // 返回json对象（通过PrintWriter输出）
        System.out.println(jsonObj.toString());
        response.getWriter().print(jsonObj);
	}
}
