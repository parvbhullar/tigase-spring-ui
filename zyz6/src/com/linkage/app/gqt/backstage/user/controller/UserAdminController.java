 package com.linkage.app.gqt.backstage.user.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.backstage.members.service.PostulantService;
import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.backstage.org.service.OrgService;
import com.linkage.app.gqt.front.reg.entitys.Members;
import com.linkage.app.gqt.front.reg.service.MembersService;
import com.linkage.app.gqt.purview.entity.User;
import com.linkage.framework.cache.CacheKey;
import com.linkage.framework.page.CouponPageUtil;
import com.linkage.framework.util.ExcelUtil;


/**
 * 
 * 后台用户管理
 *
 *
 * @create on 2009-9-7 上午11:28:45
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */

@Controller
@RequestMapping("/manage/user")
public class UserAdminController {
	final Logger logger = LoggerFactory.getLogger(UserAdminController.class);
	
	private int pagesize = 10;

	
	private PostulantService postulantService;
	private MembersService membersService;
	private MemcachedClient memcachedClient;
	private OrgService orgService;
	
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	
	@Autowired
	public void setMembersService(MembersService membersService) {
		this.membersService = membersService;
	}
	
	@Autowired
	public void setPostulantService(PostulantService postulantService) {
		this.postulantService = postulantService;
	}
	@Autowired
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	/** 修改密码 */
	@RequestMapping(value="/changePassword")
	public ModelAndView changePassword(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mvc=new ModelAndView("/manage/user/changePassword");
		return mvc;
	}
	
	/** 检查密码*/
	@RequestMapping(value="/checkPassword")
	@ResponseBody
	public String checkPassword(Members members,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String oldpassword=request.getParameter("oldpassword");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user=((User)principal);
		user.setPassword(oldpassword);
		int i=this.membersService.checkUserPassword(user);
		if(1==i)
			return "true";
		else
			return "false";
	}
	
	/** 更新密码*/
	@RequestMapping(value="/updatePassword")
	public void updatePassword(Members members,HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		String password=request.getParameter("password");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user=((User)principal);
		user.setPassword(password);
		int i=this.membersService.updateUserPassword(user);
		String s="";
		if(1==i)
			s= "修改密码成功";
		else
			s= "修改密码失败";
		PrintWriter pw = response.getWriter();
		pw.print(s);
	}
}
