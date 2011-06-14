package com.ivyinfo.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;

@Controller
public class MailController{
	private static final Logger LOGGER = LoggerFactory.getLogger(MailServlet.class);
	


	/**
	 * 收件箱列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/mail/mailReceive.do",method = RequestMethod.POST)
	public void mailReceive(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		UserBean userBean=sessionUserBean.getUserBean();
		
		LOGGER.info("收件箱 session="+session+";sessionUserBean="+sessionUserBean);
		userBean=(UserBean)sessionUserBean.getUserBean();
		LOGGER.info("收件箱 userBean="+userBean);
		LOGGER.info("收件箱 userBean.getLogname()="+userBean.getLogname()+";userBean"+userBean.getName());
		
		String page1=(String)request.getParameter("page");
		String sidx=(String)request.getParameter("sidx");
		String sord=(String)request.getParameter("sord");
		
		if("".equals(page1))
			page1="1";
		List arrayList=new ArrayList();
		int intpage1=new Integer(page1).intValue();
		int totalCount=0;
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
        // 定义返回的数据类型：json，使用了json-lib
        JSONObject jsonObj = new JSONObject();
        // 根据jqGrid对JSON的数据格式要求给jsonObj赋值
        jsonObj.put("page", page1);                // 当前页
        jsonObj.put("records", totalCount);        // 总记录数
        // 定义rows，存放数据
        JSONArray rows = new JSONArray();
        for(int i=0;i<arrayList.size();i++)
        {
        	ReceiveMailBean receiveMailBean =  (ReceiveMailBean)arrayList.get(i);
                // 存放一条记录的对象
                JSONObject cell = new JSONObject();
                cell.put("id", receiveMailBean.getId());
                cell.put("state", receiveMailBean.getState());
                cell.put("sendname", receiveMailBean.getSendname());
                cell.put("subject", receiveMailBean.getSubject());
                cell.put("datetime", receiveMailBean.getDatetime());
                cell.put("filename", receiveMailBean.getFilename());
                cell.put("name", userBean.getLogname());
                // 将该记录放入rows中
                rows.add(cell);
        }
        // 将rows放入json对象中
        jsonObj.put("rows", rows);
        // 自控制台打印输出，以检验json对象生成是否正确
        
        
        // 返回json对象（通过PrintWriter输出）
        response.getWriter().print(jsonObj);
	}
	
	
	
	/**
	 * 查看邮件
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/mail/sendDetail.do",method = RequestMethod.POST)
	public void SendDetail(HttpServletRequest request, HttpServletResponse response) throws IOException{}
	
	/**
	 * 标记邮件为已读或未读
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/mail/receiveSign.do",method = RequestMethod.POST)
	public void receiveSign(HttpServletRequest request, HttpServletResponse response) throws IOException{}
	
	/**
	 * 删除邮件列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/mail/deleteList.do",method = RequestMethod.POST)
	public void deleteList(HttpServletRequest request, HttpServletResponse response) throws IOException{}
	
	/**
	 * 还原已删除邮件
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/mail/restoreDeleteMail.do",method = RequestMethod.POST)
	public void restoreDeleteMail(HttpServletRequest request, HttpServletResponse response) throws IOException{}
	
	/**
	 * 移动到已删除
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/mail/receiveRemove.do",method = RequestMethod.POST)
	public void receiveRemove(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		UserBean userBean=sessionUserBean.getUserBean();

		String[] selarrrow=((String)request.getParameter("selarrrow")).split(",");
		try {
			for(int i=0;i<selarrrow.length;i++)
			{
				LOGGER.info("收件箱邮件移动到  已删除  selarrrow="+selarrrow[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 例子方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/mail/test.do",method = RequestMethod.POST)
	public void test(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		UserBean userBean=sessionUserBean.getUserBean();
	
	}
}