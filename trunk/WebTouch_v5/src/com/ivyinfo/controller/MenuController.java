package com.ivyinfo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.njdt.gg.ccl.datastructure.Dto;
import org.njdt.gg.ccl.datastructure.impl.BaseDto;
import org.njdt.gg.demo.esb.httpinvoker.HelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ivyinfo.framework.service.server.SpringContextUtil;
import com.ivyinfo.mail.MailServlet;
import com.ivyinfo.user.services.UserServices;
import com.ivyinfo.util.Constant;
import com.ivyinfo.util.Page;
  
@Controller  
public class MenuController{   
    protected final Log logger = LogFactory.getLog(getClass());
    private UserServices userServices = (UserServices) SpringContextUtil
			.getBean("userServices");
    
	private HelloWorld helloWorldService = (HelloWorld) SpringContextUtil
	.getBean("helloWorldService");
  
    @RequestMapping("/menuTree")   
    public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	response.setContentType("text/html;charset=utf-8");
		PrintWriter   out   =   response.getWriter(); 
		
    	try {
//    		String str=this.getTreeJson("010102");
    		String str=this.getTreeJson("0103");
    		logger.info("str="+str);
            response.getWriter().print(str);
    	} catch (Exception e) {
		}
    }   
    
    @RequestMapping("/menuTreeTest")   
    public void menuTreeTest(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	response.setContentType("text/html;charset=utf-8");
		PrintWriter   out   =   response.getWriter(); 
		String parentid=(String)request.getParameter("id");
    	try {
//    		String str=this.getTreeJson("010102");
    		
    		String str=this.getTreeJson(parentid);
    		logger.info("parentid="+parentid);
    		logger.info("str="+str);
//    		str="[{'attr':{'id':'node_2','rel':'drive'},'data':'C:','state':'closed'},{'attr':{'id':'node_6','rel':'drive'},'data':'D:','state':''}]";
            response.getWriter().print(str);
    	} catch (Exception e) {
		}
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
        	String leaf=(String)dto.get("leaf");
        	String state="closed";
        	if("1".equals(leaf))
        		state="";
			if(i==0)
			{
				sb.append("\"data\"").append(":\"").append(dto.get("text"))
				.append("\",\"state\":\"").append(state).append("\"")
				.append(",\"attr\" : { \"id\" : \"").append(dto.get("id"))
				.append("\",\"rel\":\"").append("folder").append("")
				.append("\",\"alt\":\"").append(dto.get("request")).append("\"}}");
			}
			else
			{
				sb.append(",{\"data\"").append(":\"").append(dto.get("text"))
				.append("\",\"state\":\"").append(state).append("\"")
				.append(",\"attr\" : { \"id\" : \"").append(dto.get("id"))
				.append("\",\"rel\":\"").append("default").append("")
				.append("\",\"alt\":\"").append(dto.get("request")).append("\"}}");
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
}