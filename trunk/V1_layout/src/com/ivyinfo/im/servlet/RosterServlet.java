package com.ivyinfo.im.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jivesoftware.smack.packet.Presence;

import com.ivyinfo.im.controller.TrafficController;
import com.ivyinfo.im.listener.ChatListener;
import com.ivyinfo.im.listener.RosterListener;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.zy.im.client.service.ClientSession;
import com.zy.im.client.service.ClientThreadState;

/**
 * Servlet implementation class RosterServlet
 */
public class RosterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RosterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			request.setCharacterEncoding("utf-8");		
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding("utf-8");
			
			String to = null;
			
			HttpSession session = request.getSession();
			SessionUserBean sessionUserBean = (SessionUserBean) session.getAttribute("sessionUserBean");
	
			UserBean userBean = new UserBean();
			userBean = sessionUserBean.getUserBean();
			//处理用户名！！
			String loginName = userBean.getLogname();
			int p = loginName.indexOf("@");
			if(p>-1)
				loginName = loginName.substring(0, p);
			
			if(sessionUserBean!=null&&sessionUserBean.getCsession()!=null){
				ClientSession csession = sessionUserBean.getCsession();
				if(csession.getState()==ClientThreadState.LISTENING){
					RosterListener listener = TrafficController.getRosterListener(loginName);
					if(listener!=null){
						listener.addRoster(to);
					}
				}
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
	}

}
