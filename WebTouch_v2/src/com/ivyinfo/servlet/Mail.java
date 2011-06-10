package com.ivyinfo.servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ivyinfo.mail.bean.MailUtilBean;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;

public class Mail extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(Mail.class);
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			String json="[{'attr':{'id':'node_2','rel':'drive'},'data':'C:','state':'closed'},{'attr':{'id':'node_6','rel':'drive'},'data':'D:','state':''}]";
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String action=(String)request.getParameter("action");
		if("SendMail".equals(action))
		{
			
			String to=(String)request.getParameter("to")+";";
			String subject=(String)request.getParameter("subject");
			String content=(String)request.getParameter("content");
			MailUtilBean mailUtilBean=new MailUtilBean();
			try {
				String json="[{'attr':{'id':'node_2','rel':'drive'},'data':'C:','state':'closed'},{'attr':{'id':'node_6','rel':'drive'},'data':'D:','state':''}]";
				response.getWriter().print(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			HttpSession session = request.getSession();
			SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
			UserBean userBean=(UserBean)sessionUserBean.getUserBean();
			List arrayList=new ArrayList();
			
			try {
				
				
//				purviewServices.ValidationLogin(logname, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONArray jsonArray = new JSONArray();
			StringBuffer jsonStr=new StringBuffer();
				Gson gson = new Gson();
				
				Type targetType = new TypeToken<List<ReceiveMailBean>>(){}.getType();
				String sUserList1 = "";
				 LOGGER.info(" arrayList.size()="+ arrayList.size()+";sUserList1="+sUserList1);
			   for (int i = 0; i < arrayList.size(); i++) {
				   ReceiveMailBean receiveMailBean =  (ReceiveMailBean)arrayList.get(i);
//				   System.out.println("i="+i+";sessionUserBean="+sessionUserBean+arrayList.size()+";邮件数用户名:"+UserBean.getName());
			      if(i==0)
			      jsonStr.append("'cell': ['"+receiveMailBean.getSendname()+"','"+receiveMailBean.getSubject()+"','"+receiveMailBean.getDatetime()+"','20.0','120.0','备注 1']},");
			      else
			      jsonStr.append("{'cell': ['"+receiveMailBean.getSendname()+"','"+receiveMailBean.getSubject()+"','"+receiveMailBean.getDatetime()+"','20.0','120.0','备注 1']},");
//			      jsonObj.put("cell", "["+emp.getSendname()+","+emp.getSubject()+","+","+emp.getDatetime()+","+emp.getLevel()+"]");
//			      jsonArray.add(jsonObj);
			   }
//			   String jsonStr = jsonArray.toString();
//			   System.out.println("jsonStr="+jsonStr);
			response.setCharacterEncoding("utf-8");
			String json="{'total': 2,'page': 1,'records': 13,'rows': [{";
//			String json="{'records': 13,'rows': [{" +
////			String json="{'rows': [{" +
//					"'cell': ['1','2007-10-01','101.0','20.0','120.0','备注 1']}," +
//					"{'cell': ['2','2007-10-01','102.0','20.0','120.0','note 1']},"+
//					"{'cell': ['3','2007-10-01','103.0','20.0','120.0','note 1']},"+
//					"{'cell': ['4','2007-10-01','104.0','20.0','120.0','note 1']},"+
//					"{'cell': ['5','2007-10-01','105.0','20.0','120.0','note 1']},"+
//					"{'cell': ['6','2007-10-01','106.0','20.0','120.0','note 1']},"+
//					"{'cell': ['7','2007-10-01','107.0','20.0','120.0','note 1']},"+
//					"{'cell': ['8','2007-10-01','108.0','20.0','120.0','note 1']},"+
//					"{'cell': ['9','2007-10-01','109.0','20.0','120.0','note 1']},"+
//					"{'cell': ['10','2007-10-01','110.0','20.0','120.0','note 1']},"+
//					"{'cell': ['11','2007-10-01','111.0','20.0','120.0','note 1']},"+
//					"{'cell': ['12','2007-10-01','112.0','20.0','120.0','note 1']},"+
//					"{'cell': ['13','2007-10-06','103.0','20.0','120.0','null']}]}";
			System.out.println(json+jsonStr.toString().substring(0,(jsonStr.length()-1))+"]}");
			response.getWriter().print(json+jsonStr.toString().substring(0,(jsonStr.length()-1))+"]}");
//			response.getWriter().print(json);
		}
	}
}