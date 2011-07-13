package com.ivyinfo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ivyinfo.mail.bean.ReceiveMailBean;
import com.ivyinfo.session.bean.SessionUserBean;
import com.ivyinfo.user.bean.UserBean;
import com.ivyinfo.util.Constant;
import com.ivyinfo.util.Page;
  
@Controller  
public class UserController{   
    protected final Log logger = LogFactory.getLog(getClass());   
  
    /**
     * 保存用户
     * @return
     */
    @RequestMapping("/user/saveUserItem")   
    public ModelAndView saveUserItem(HttpServletRequest request, HttpServletResponse response){   
    	
        logger.info("Return View="+request.getParameter("logname"));   
        return new ModelAndView("layout_tree.jsp");   
    }   
    
}