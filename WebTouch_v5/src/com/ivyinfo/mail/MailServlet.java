package com.ivyinfo.mail;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.demo.esb.httpinvoker.HelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivyinfo.framework.service.server.SpringContextUtil;

public class MailServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(MailServlet.class);
	
	private HelloWorld helloWorldService = (HelloWorld) SpringContextUtil
	.getBean("helloWorldService");
	
	

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
					//jsonObj = organization.getOrgPurview();
					
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
//		            helloWorldService.sayHello("qk");
		            List menuList=helloWorldService.queryBalanceInfoLimitRownum(12);
		            for(int i=0;i<menuList.size();i++){
		            	JSONObject jSONObject=new JSONObject();
		            	BaseDto dto=(BaseDto)menuList.get(i);
			            jSONObject.put("data",dto.get("text"));
			            if("0".equals(dto.get("leaf")))
			            	jSONObject.put("children","");
			            JSONObject tempJSONObject=new JSONObject();
			            tempJSONObject.put("id", i);
			            
			            tempJSONObject.put("leaf", dto.get("leaf"));
			            jSONObject.put("attr", tempJSONObject);
			            jSONArray.add(jSONObject);
		            }
//		            Dto inDto = new BaseDto(); 
//		            inDto.put("parentid", "001");
//		            organizationService.queryDeptItems(inDto);
//		            response.getWriter().print(jSONArray.toString());
//		            str=jSONArray.toString();
		            LOGGER.info("str="+str);
//		            str=this.querySubTree("01");
		            str=this.getTreeJson("010102");
		            
		            LOGGER.info("str="+str);
		            response.getWriter().print(str);
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
//					jsonObj = organization.getOrgPurview();
					
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
	 * 
	 * @return
	 */
	private String querySubTree(String nodeid){
		JSONArray jSONArray=new JSONArray();
		Dto paramDto=new BaseDto();
		paramDto.put("menuid", nodeid);
		List menuList=helloWorldService.queryMenuItems(paramDto);
        for(int i=0;i<menuList.size();i++){
        	JSONObject jSONObject=new JSONObject();
        	BaseDto dto=(BaseDto)menuList.get(i);
            jSONObject.put("data",dto.get("text"));
            LOGGER.info((String)dto.get("text"));
            if("0".equals(dto.get("leaf")))
            {
            	this.queryChildTree((String)dto.get("id"));
            	jSONObject.put("children","['Child 1', 'Child 2' ]");
            }
            
            	
            JSONObject tempJSONObject=new JSONObject();
            tempJSONObject.put("id", dto.get("id"));
            tempJSONObject.put("leaf", dto.get("leaf"));
            jSONObject.put("attr", tempJSONObject);
            jSONArray.add(jSONObject);
        }
        return jSONArray.toString();
	}
	
	private String queryChildTree(String nodeid){
		JSONArray jSONArray=new JSONArray();
		Dto paramDto=new BaseDto();
		paramDto.put("parentid", nodeid);
		List menuList=helloWorldService.queryMenuItems(paramDto);
        for(int i=0;i<menuList.size();i++){
        	JSONObject jSONObject=new JSONObject();
        	BaseDto dto=(BaseDto)menuList.get(i);
            jSONObject.put("data",dto.get("text"));
            LOGGER.info((String)dto.get("text"));
            if("0".equals(dto.get("leaf")))
            {

            	this.queryChildTree((String)dto.get("id"));
            	jSONObject.put("children","['Child 1', 'Child 2' ]");
            
            }
            JSONObject tempJSONObject=new JSONObject();
            tempJSONObject.put("id", dto.get("id"));
            tempJSONObject.put("leaf", dto.get("leaf"));
            jSONObject.put("attr", tempJSONObject);
            jSONArray.add(jSONObject);
        }
        return jSONArray.toString();
	}
	
	/**
	 * 
	 * @param nodeid
	 * @return
	 */
	private String getTreeJson(String nodeid){
		StringBuffer sb2=new StringBuffer("[{");
		StringBuffer sb=new StringBuffer("[{");
		Dto paramDto=new BaseDto();
		paramDto.put("parentid", nodeid);
		List menuList=helloWorldService.queryMenuItems(paramDto);
		for(int i=0;i<menuList.size();i++){
        	JSONObject jSONObject=new JSONObject();
        	BaseDto dto=(BaseDto)menuList.get(i);

			if(i==0)
			{
				sb.append("\"data\"").append(":\"").append(dto.get("text")).append("\"");
				sb.append(",\"attr\" : { \"id\" : \"").append(dto.get("id")).append("\",\"alt\":\"").append(dto.get("id")).append("\"}}");
				
			}
			else
			{
				sb.append(",{\"data\"").append(":\"").append(dto.get("text")).append("\"");
				sb.append(",\"attr\" : { \"id\" : \"").append(dto.get("id")).append("\",\"alt\":\"").append(dto.get("id")).append("\"}}");
				
			}
        }
		if(menuList.size()>1)
		{
				sb.append("]");
		}
		paramDto=new BaseDto();
		paramDto.put("menuid", nodeid);
		menuList=helloWorldService.queryMenuItems(paramDto);
		BaseDto dto=(BaseDto)menuList.get(0);

		sb2.append("\"data\"").append(":\"").append(dto.get("text")).append("\",").append("\"attr\" : { \"id\" : \"").append(dto.get("id")).append("\",\"alt\":\"").append(dto.get("id")).append("\"},").append("\"children\"").append(":").append(sb.toString());
		sb2.append(",\"state\" : \"open\" }]");
	
		return sb2.toString();
	}
	
	private String getChildrenJson(String nodeid){
		
		return "";
	}
}
