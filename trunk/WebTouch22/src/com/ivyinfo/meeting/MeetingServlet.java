package com.ivyinfo.meeting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ivyinfo.communication.bean.CommunicationBean;
import com.ivyinfo.communication.services.CommunicationServices;
import com.ivyinfo.framework.common.time.TimeTools;
import com.ivyinfo.framework.service.sequence.ISequence;
import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.meeting.bean.MeetingDetailBean;
import com.ivyinfo.meeting.bean.MeetingReturnBean;
import com.ivyinfo.meeting.bean.MeetingReturnDetailBean;
import com.ivyinfo.meeting.services.MeetingServices;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;

/**
 * Servlet implementation class MeetingServlet
 */
public class MeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Meeting meeting = new Meeting();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MeetingServlet() {
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
		//获得用户信息！！以便查询用户安排会议
        HttpSession session = request.getSession();
        SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
        UserBean userBean = sessionUserBean.getUserBean();
        System.err.println("MeetingServlet logname:"+userBean.getLogname());
        System.err.println("MeetingServlet password:"+userBean.getPassword());
		
    	String action = request.getParameter("action");
    	
    	String err = "";
		String errreason = "";
		String returnerr = null;
		
    	if(action != null&&action.equals("addFixedMeeting")){ 		
    		returnerr = meeting.addFixedMeeting(request,response,sessionUserBean);
			
			if(returnerr == null){
				err = "error";
				errreason = "创建预约会议出错！";
			}else if(!"".equals(returnerr)){
				err = "error";
				errreason = returnerr;
			}else if("".equals(returnerr)){
				err = "success";
				errreason = "";
			}
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("result", err);
			jsonObj.put("reason", errreason);
			response.getWriter().print(jsonObj);
    	}else if(action!=null&&action.equals("getAttendee")){
			//每次创建，修改会议时，需要选择参与者，这里获得参与者
			getContact(request,response,sessionUserBean);
		}else{
    		
    	}
	}
	
	//显示所有用户
	public void getContact(HttpServletRequest request, HttpServletResponse response,SessionUserBean sessionUserBean) throws ServletException, IOException{
		JSONArray jsonObj = meeting.getContact(request, response, sessionUserBean);
		//设置字符编码
        response.setCharacterEncoding("UTF-8");
        // 返回json对象（通过PrintWriter输出）
        System.out.println(jsonObj.toString());
        response.getWriter().print(jsonObj);
	}
	
}
