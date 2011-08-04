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
public class ImController{   
    protected final Log logger = LogFactory.getLog(getClass());
    private UserServices userServices = (UserServices) SpringContextUtil
			.getBean("userServices");
    
	private HelloWorld helloWorldService = (HelloWorld) SpringContextUtil
	.getBean("helloWorldService");
  
    @RequestMapping("/imOnline")   
    public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	response.setContentType("text/html;charset=utf-8");
		PrintWriter   out   =   response.getWriter(); 
		String str="{'success': true,'buddies':[{'uid':'1165','id':'qinkun1234','nick':'qinkun1234','group':'好友'" +
				",'url':'css/im/images/noavatar_small.gif','status':'在线','presence':'online','show':'aavailable'," +
				"'history': [{'to': 'qinkun123','nick': 'qinkun1234','from': 'qinkun1234','style': ''," +
				"'body': 'to:qinkun1234','type': 'unicast','timestamp': '1298950163410'}]}]}";
    	try {
//    		String str=this.getTreeJson("010102");
//    		String str=this.getTreeJson("0103");
    		logger.info("str="+str);
            response.getWriter().print(str);
    	} catch (Exception e) {
		}
    }   
}