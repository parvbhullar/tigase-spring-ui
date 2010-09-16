package com.linkage.app.gqt.backstage.log.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.linkage.app.gqt.backstage.log.service.LogService;
import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.purview.entity.User;
import com.linkage.framework.page.CouponPageUtil;


@Controller
@RequestMapping("/manage/log")
public class LogController {
	final Logger logger = LoggerFactory.getLogger(LogController.class);
	private int pagesize=10;
	
	private LogService logService;
	private MemcachedClient memcachedClient;
	
	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	
	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	/** 显示 */
	@RequestMapping(value="/loglist")
	public ModelAndView showactive(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mvc=new ModelAndView("/manage/log/loglist");
		String page = request.getParameter("page");
		page = page==null?"1":page;
		String typename = request.getParameter("typename");
		String loginname = request.getParameter("loginname");
		String nickname = request.getParameter("nickname");
		
		Map args = new HashMap();
		args.put("page", page);
		args.put("pagesize", pagesize);
		if(typename!=null && !typename.equals(""))
		{
			args.put("typename", typename);
			mvc.addObject("typename", typename);
		}
		if(loginname!=null && !loginname.equals(""))
		{
			args.put("loginname", loginname);
			mvc.addObject("loginname", loginname);
		}
		if(nickname!=null && !nickname.equals(""))
		{
			args.put("nickname", nickname);
			mvc.addObject("nickname", nickname);
		}
		args.put("page", Integer.valueOf(page));
		args.put("pagesize", pagesize);
		
		
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(pagesize);
		pageUtil.setCurPage(Integer.valueOf(page));
		pageUtil.setTotalRow(this.logService.getLogTotal(args));
		mvc.addObject("logs", this.logService.getLogList(args));
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		mvc.addObject("page",page);
		
		return mvc;
	}
	
	/*
	 * 保存日志
	 */
	@RequestMapping(value="/insert.action", method=RequestMethod.POST)
	public ModelAndView insert(@RequestParam("tid") long tid,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long userid = ((User)principal).getUserId();
		String ip = request.getRemoteAddr();
		this.logService.insert(tid, userid, ip);
		return null;
	}
	
}
