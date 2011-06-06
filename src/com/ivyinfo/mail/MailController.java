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

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.bean.DeleteMailBean;
import com.ivyinfo.mail.bean.FileBean;
import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.mail.bean.SendMailBean;
import com.ivyinfo.mail.services.AuxiliaryMailServices;
import com.ivyinfo.mail.services.SendMailServices;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.util.Constant;
import com.ivyinfo.util.Page;
import com.ivyinfo.webdisk.bean.DiskBean;
import com.ivyinfo.webdisk.services.IWebDiskServices;

@Controller
public class MailController{
	private static final Logger LOGGER = LoggerFactory.getLogger(MailServlet.class);
	
	private AuxiliaryMailServices auxiliaryMailServices = (AuxiliaryMailServices) SpringContextUtil
	.getBean("auxiliaryMailServices");

	private SendMailServices sendMailServices = (SendMailServices) SpringContextUtil
	.getBean("sendMailServices");

	private IWebDiskServices iWebDiskServices= (IWebDiskServices) SpringContextUtil
	.getBean("webDiskService");
	

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
		Page page=new Page(Constant.PAGE_SIZE_10);
		
		if("".equals(page1))
			page1="1";
		List arrayList=new ArrayList();
		int intpage1=new Integer(page1).intValue();
		int totalCount=0;
		try {
			totalCount=auxiliaryMailServices.ReceiveListCount(userBean.getLogname());
			page.setPageNo(new Integer(page1).intValue());
			page.setTotalCount(totalCount);
			arrayList=auxiliaryMailServices.ReceiveList(page.getStart(), page.getEnd(), userBean.getLogname(),sidx,sord);
//			purviewServices.ValidationLogin(logname, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
        // 定义返回的数据类型：json，使用了json-lib
        JSONObject jsonObj = new JSONObject();
        // 根据jqGrid对JSON的数据格式要求给jsonObj赋值
        jsonObj.put("page", page1);                // 当前页
        jsonObj.put("total",page.getTotalPages());        // 总页数
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
        LOGGER.info("\n sidx="+sidx+";sord="+sord+
        		"\n;(page.getStart()=="+page.getStart()+";;page.getEnd()="+page.getEnd()+";;page.getTotalPages()="+page.getTotalPages()+";totalCount="+totalCount+";page1="+page1+";arrayList.size()="+arrayList.size()+
        		";\n要返回的json对象：\n" + jsonObj.toString());
        // 设置字符编码
        
        // 返回json对象（通过PrintWriter输出）
        response.getWriter().print(jsonObj);
	}
	
	/**
	 * 查看邮件 收件箱详细信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/mail/receiveDetail.do",method = RequestMethod.POST)
	public void ReceiveDetail(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		UserBean userBean=sessionUserBean.getUserBean();
		
		String id=(String)request.getParameter("id");
		LOGGER.info("id="+id+";userBean="+userBean);
		try {
			ReceiveMailBean receiveMailBean=auxiliaryMailServices.ReceiveDetail(new Integer(id).intValue(), userBean.getLogname());
			List list=auxiliaryMailServices.AllFile(id, userBean.getLogname());
			
			receiveMailBean.getFilename();
			JSONObject  jsonObj=new JSONObject();
			jsonObj.put("sendname", receiveMailBean.getSendname());
			jsonObj.put("receivename", receiveMailBean.getReceivename());
			jsonObj.put("content", receiveMailBean.getContent());
			jsonObj.put("subject", receiveMailBean.getSubject());
			
			JSONArray rows = new JSONArray();
			for(int i=0;i<list.size();i++)
            {
				FileBean fileBean =  (FileBean)list.get(i);
				DiskBean diskBean=new DiskBean();
                    // 存放一条记录的对象
                    JSONObject cell = new JSONObject();
                    cell.put("id", fileBean.getId());
                    cell.put("mailId", fileBean.getMailid());
                    cell.put("filename", fileBean.getFilename());
                    cell.put("filepath", fileBean.getFilepath());
                    //fileBean.getFilepath().indexOf("/");
//                    diskBean.setFtpid(ftpid);
                    diskBean.setFtpid(fileBean.getFilepath());
//                    iWebDiskServices.download(diskBean);
                    // 将该记录放入rows中
                    rows.add(cell);
            }
			jsonObj.put("rows", rows);
			LOGGER.info("邮件 view"+receiveMailBean.getSendname()+"\n;gson="+jsonObj.toString()+"\n list.size()="+list.size());
			response.getWriter().print(jsonObj.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查看邮件
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/mail/sendDetail.do",method = RequestMethod.POST)
	public void SendDetail(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		UserBean userBean=sessionUserBean.getUserBean();
		
		String id=(String)request.getParameter("id");
		LOGGER.info("id="+id+";userBean="+userBean);
		try {
			SendMailBean receiveMailBean=auxiliaryMailServices.SendDetail(new Integer(id).intValue(), userBean.getLogname());
			List list=auxiliaryMailServices.AllFile(id, userBean.getLogname());
			
			receiveMailBean.getFilename();
			JSONObject  jsonObj=new JSONObject();
			jsonObj.put("sendname", receiveMailBean.getSendname());
			jsonObj.put("receivename", receiveMailBean.getReceivename());
			jsonObj.put("content", receiveMailBean.getContent());
			jsonObj.put("subject", receiveMailBean.getSubject());
			
			JSONArray rows = new JSONArray();
			for(int i=0;i<list.size();i++)
            {
				FileBean fileBean =  (FileBean)list.get(i);
				DiskBean diskBean=new DiskBean();
                    // 存放一条记录的对象
                    JSONObject cell = new JSONObject();
                    cell.put("id", fileBean.getId());
                    cell.put("mailId", fileBean.getMailid());
                    cell.put("filename", fileBean.getFilename());
                    cell.put("filepath", fileBean.getFilepath());
                    //fileBean.getFilepath().indexOf("/");
//                    diskBean.setFtpid(ftpid);
                    diskBean.setFtpid(fileBean.getFilepath());
//                    iWebDiskServices.download(diskBean);
                    // 将该记录放入rows中
                    rows.add(cell);
            }
			jsonObj.put("rows", rows);
			LOGGER.info("邮件 view"+receiveMailBean.getSendname()+"\n;gson="+jsonObj.toString()+"\n list.size()="+list.size());
			response.getWriter().print(jsonObj.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 标记邮件为已读或未读
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/mail/receiveSign.do",method = RequestMethod.POST)
	public void receiveSign(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		UserBean userBean=sessionUserBean.getUserBean();
	
		String[] selarrrow=((String)request.getParameter("selarrrow")).split(",");
		String state=((String)request.getParameter("state"));
		try {
			
			for(int i=0;i<selarrrow.length;i++)
			{
				LOGGER.info("修改收件箱标记（已读未读） action=;selarrrow="+selarrrow[i]+";更新状态为:"+state);
				auxiliaryMailServices.ReceiveSign(new Integer(selarrrow[i]).intValue(), userBean.getLogname(),state);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除邮件列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/mail/deleteList.do",method = RequestMethod.POST)
	public void deleteList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		UserBean userBean=sessionUserBean.getUserBean();
		
		LOGGER.info("已删除邮件列表session="+session);
		userBean=(UserBean)sessionUserBean.getUserBean();
		String page1=(String)request.getParameter("page");
		String sidx=(String)request.getParameter("sidx");
		String sord=(String)request.getParameter("sord");
		Page page=new Page(Constant.PAGE_SIZE_10);
		
		if("".equals(page1))
			page1="1";
		List arrayList=new ArrayList();
		int intpage1=new Integer(page1).intValue();
		int totalCount=0;
		try {
			totalCount=auxiliaryMailServices.DeleteListCount(userBean.getLogname());
			page.setPageNo(new Integer(page1).intValue());
			page.setTotalCount(totalCount);
			arrayList=auxiliaryMailServices.DeleteList(page.getStart(), page.getEnd(), userBean.getLogname(),sidx,sord);
//			purviewServices.ValidationLogin(logname, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
        // 定义返回的数据类型：json，使用了json-lib
        JSONObject jsonObj = new JSONObject();
        // 根据jqGrid对JSON的数据格式要求给jsonObj赋值
        jsonObj.put("page", page1);                // 当前页
        jsonObj.put("total",page.getTotalPages());        // 总页数
        jsonObj.put("records", totalCount);        // 总记录数
        // 定义rows，存放数据
        JSONArray rows = new JSONArray();
        for(int i=0;i<arrayList.size();i++)
        {
        	DeleteMailBean receiveMailBean =  (DeleteMailBean)arrayList.get(i);
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
        LOGGER.info("\n sidx="+sidx+";sord="+sord+
        		"\n;(page.getStart()=="+page.getStart()+";;page.getEnd()="+page.getEnd()+";;page.getTotalPages()="+page.getTotalPages()+";totalCount="+totalCount+";page1="+page1+";arrayList.size()="+arrayList.size()+
        		";\n要返回的json对象：\n" + jsonObj.toString());
        // 设置字符编码
        response.setCharacterEncoding("UTF-8");
        // 返回json对象（通过PrintWriter输出）
        response.getWriter().print(jsonObj);
	}
	
	/**
	 * 还原已删除邮件
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/mail/restoreDeleteMail.do",method = RequestMethod.POST)
	public void restoreDeleteMail(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SessionUserBean sessionUserBean=(SessionUserBean)session.getAttribute("sessionUserBean");
		UserBean userBean=sessionUserBean.getUserBean();
		
		String[] selarrrow=((String)request.getParameter("selarrrow")).split(",");
		try {
			
			for(int i=0;i<selarrrow.length;i++)
			{
				LOGGER.info("还原    已删除  邮件;selarrrow="+selarrrow[i]);
				auxiliaryMailServices.RestoreDeleteMail(new Integer(selarrrow[i]).intValue(), userBean.getLogname());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
				auxiliaryMailServices.ReceiveRemove(new Integer(selarrrow[i]).intValue(), userBean.getLogname());
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