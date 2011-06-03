package com.ivyinfo.mail;

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

import org.eredlab.g4.arm.service.OrganizationService;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.demo.esb.httpinvoker.HelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.bean.MailUtilBean;
import com.ivyinfo.mail.services.AuxiliaryMailServices;
import com.ivyinfo.mail.services.SaveMailServices;
import com.ivyinfo.mail.services.SendMailServices;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.webdisk.services.IWebDiskServices;

public class MailServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(MailServlet.class);
	private AuxiliaryMailServices auxiliaryMailServices = (AuxiliaryMailServices) SpringContextUtil
			.getBean("auxiliaryMailServices");
	
	private SendMailServices sendMailServices = (SendMailServices) SpringContextUtil
	.getBean("sendMailServices");
	
	private IWebDiskServices iWebDiskServices= (IWebDiskServices) SpringContextUtil
	.getBean("webDiskService");
	
	private SaveMailServices saveMailServices = (SaveMailServices) SpringContextUtil
	.getBean("saveMailServices");
	
	private HelloWorld helloWorldService = (HelloWorld) SpringContextUtil
	.getBean("helloWorldService");
	
	private OrganizationService organizationService = (OrganizationService) SpringContextUtil
	.getBean("organizationService");
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("doPost");
		response.setCharacterEncoding("UTF-8");
		//有问题的格式
		String str="{'success':true,'connection':{'ticket':'b355d57df7131bb9ecfc|qinkun123','domain':'dzx.webim20.cn','server':'http:webim20.cn:8000packets'},'buddies':[{'uid':'1165','id':'qinkun1234','nick':'qinkun1234','group':'\u5176\u4ed6','url':'home.php?mod=space&uid=1165','status':'','presence':'online','show':'aavailable'}],'rooms':[],'server_time':1301045089521.5,'user':{'uid':'1166','id':'qinkun123','nick':'qinkun123',''show':'aavailable','url':'home.php?mod=space&uid=1166','status':''},'new_messages':[]}";
		//没问题的格式
		str="{'success': true,'buddies':[{'uid':'1165','id':'qinkun1234','nick':'qinkun1234','group':'其他','url':'css/im/images/noavatar_small.gif','status':'在线','presence':'online','show':'aavailable','history': [{'to': 'qinkun123','nick': 'qinkun1234','from': 'qinkun1234','style': '','body': 'to:qinkun1234','type': 'unicast','timestamp': '1298950163410'}]}]}";

		
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
			Mail organization=new Mail();
		
			if("online".equals(action)){
				LOGGER.info("=========online=============");
				try {
					response.getWriter().print(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("notifications".equals(action)){
				LOGGER.info("=========notifications=============");
					try {
						response.getWriter().print(str);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}else if("status".equals(action)){
				LOGGER.info("=========status=============");
				try {
					response.getWriter().print("ok");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("history".equals(action)){
				LOGGER.info("=========history=============");
				try {
					str="[{'to': 'qinkun1234','nick': 'qinkun123','from': 'qinkun123','style': '','body': '1','type': 'unicast','timestamp': '1301198364506.6'}]";
					response.getWriter().print(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("Delorg".equals(action)){
				LOGGER.info("=========Delorg=============");
				String id=(String)request.getParameter("id");
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("setpermissions".equals(action)){
				LOGGER.info("=========setpermissions=============");
				String orgid=(String)request.getParameter("id");
				JSONObject jsonObj = null;
				try {
					
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				LOGGER.info("=========NULL=============");
				JSONObject jsonObj = null;
				try {
					// 设置字符编码
		            response.setCharacterEncoding("UTF-8");
		            // 返回json对象（通过PrintWriter输出）
		            JSONArray jSONArray=new JSONArray();
//		            for(int i=0;i<5;i++){
//		            	JSONObject jSONObject=new JSONObject();
//			            jSONObject.put("data", "node"+i);
//			            JSONObject tempJSONObject=new JSONObject();
//			            tempJSONObject.put("id", i);
//			            jSONObject.put("attr", tempJSONObject);
//			            jSONArray.add(jSONObject);
//		            }
		            //response.getWriter().print("[{'attr':{'id':'node_2','rel':'drive'},'data':'C:','state':'closed'},{'attr':{'id':'node_6','rel':'drive'},'data':'D:','state':''}]");
		            helloWorldService.sayHello("qk");
		            List menuList=helloWorldService.queryBalanceInfoLimitRownum(12);
		            for(int i=0;i<menuList.size();i++){
		            	JSONObject jSONObject=new JSONObject();
			            jSONObject.put("data",((BaseDto)menuList.get(i)).get("text"));
			            JSONObject tempJSONObject=new JSONObject();
			            tempJSONObject.put("id", i);
			            jSONObject.put("attr", tempJSONObject);
			            jSONArray.add(jSONObject);
		            }
//		            Dto inDto = new BaseDto(); 
//		            inDto.put("parentid", "001");
//		            organizationService.queryDeptItems(inDto);
		            response.getWriter().print(jSONArray.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("doPost");
		response.setCharacterEncoding("UTF-8");
		String str="{'success':true,'connection':{'ticket':'b355d57df7131bb9ecfc|qinkun123','domain':'dzx.webim20.cn','server':'http:webim20.cn:8000packets'},'buddies':[{'uid':'1165','id':'qinkun1234','nick':'qinkun1234','group':'\u5176\u4ed6','url':'home.php?mod=space&uid=1165','status':'\u6709\u6ca1\u6709\u7528IM \u804a\u5929\u7684\u6d4b\u8bd5\u7684\u52a0\u6211\uff01\uff01','presence':'online','show':'aavailable'}],'rooms':[],'server_time':1301045089521.5,'user':{'uid':'1166','id':'qinkun123','nick':'qinkun123',''show':'aavailable','url':'home.php?mod=space&uid=1166','status':''},'new_messages':[]}";

		
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
			Mail organization=new Mail();
		
			if("online".equals(action)){
				LOGGER.info("=========online=============");
				try {
					response.getWriter().print(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("notifications".equals(action)){
				LOGGER.info("=========notifications=============");
					try {
						response.getWriter().print(str);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}else if("status".equals(action)){
				LOGGER.info("=========status=============");
				try {
					response.getWriter().print("ok");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("message".equals(action)){
				LOGGER.info("=========message=============");
				try {
					response.getWriter().print("ok");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("history".equals(action)){
				LOGGER.info("=========history=============");
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if("setpermissions".equals(action)){
				LOGGER.info("=========setpermissions=============");
				String orgid=(String)request.getParameter("id");
				JSONObject jsonObj = null;
				try {
					
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
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

	/**
	 * 查看邮件
	 * @param request
	 * @param response
	 */
	private void view(HttpServletRequest request, HttpServletResponse response){
		String to=(String)request.getParameter("to")+";";
		String subject=(String)request.getParameter("subject");
		String content=(String)request.getParameter("content");
		MailUtilBean mailUtilBean=new MailUtilBean();
		try {
			List list=new ArrayList<String>();
			list.add(to);
			//mailUtilBean.setFrom("qinkun1234@163.com");
			mailUtilBean.setFrom(" ");
			mailUtilBean.setTo(list);
			mailUtilBean.setSubject(subject);
			mailUtilBean.setContent(content);
			//mailUtilBean.setToname("qinkun1234@163.com;");
			mailUtilBean.setToname(" ");
			sendMailServices.SendMail(mailUtilBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
