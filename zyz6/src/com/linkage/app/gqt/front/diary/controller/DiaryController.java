package com.linkage.app.gqt.front.diary.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.linkage.app.gqt.front.diary.entitys.Diary;
import com.linkage.app.gqt.front.diary.service.DiaryService;
import com.linkage.framework.Constants;
import com.linkage.framework.cache.CacheKey;
import com.linkage.framework.page.CouponPageUtil;

@Controller
@RequestMapping("/app/admin/diary")
public class DiaryController {
	final Logger logger = LoggerFactory.getLogger(DiaryController.class);
	
	private DiaryService diaryService;
	private MemcachedClient memcachedClient;
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	@Autowired
	public void setDiaryService(DiaryService diaryService) {
		this.diaryService = diaryService;
	}
	
	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	
	/** 日志列表*/
	@RequestMapping(value="/show")
	public ModelAndView show(HttpServletRequest request,HttpServletResponse response) throws Exception {
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(Constants.EC_WEBPREFERENTIAL_MANAGE_SHOPS_SIZE);
		String pages = request.getParameter("page");
		int page = pages==null||pages.equals("") ? 1 : Integer.valueOf(pages);
		pageUtil.setCurPage(page);
//		pageUtil.setTotalRow(couponsList.size());
		page = page>pageUtil.getTotalPage()? 1:page;
		
		ModelAndView mvc=new ModelAndView("/app/admin/diary/showdiary");
		List<Diary> diaryList=this.diaryService.showDiary();
		mvc.addObject("diaryList", diaryList);
		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));
		return mvc;
	}
	
	/** 日志审核列表*/
	@RequestMapping(value="/showaudit")
	public ModelAndView showaudit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(Constants.EC_WEBPREFERENTIAL_MANAGE_SHOPS_SIZE);
		String pages = request.getParameter("page");
		int page = pages==null||pages.equals("") ? 1 : Integer.valueOf(pages);
		pageUtil.setCurPage(page);
//		pageUtil.setTotalRow(couponsList.size());
		page = page>pageUtil.getTotalPage()? 1:page;
		
		ModelAndView mvc=new ModelAndView("/app/admin/diary/showdiaryaudit");
		List<Diary> diaryList=this.diaryService.showDiary();
		mvc.addObject("diaryList", diaryList);
		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));
		return mvc;
	}
	
	/** 跳转到新增页面*/
	@RequestMapping(value="/add")
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mvc=new ModelAndView("/app/admin/diary/creatediary");
		List<Diary> diaryList=this.diaryService.showDiary();
		mvc.addObject("diaryList", diaryList);
		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));
		return mvc;
	}
	
	/** 跳转到查看页面*/
	@RequestMapping(value="/view")
	public ModelAndView view(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String recid=(String)request.getParameter("recid");
		ModelAndView mvc=new ModelAndView("/app/admin/diary/viewdiary");
		Diary diary=this.diaryService.getDiary(new Long(recid).longValue());
		List<Diary> diaryReplyList=this.diaryService.showReplyDiary(new Long(recid).longValue());
		mvc.addObject("diary", diary);
		mvc.addObject("diaryReplyList", diaryReplyList);
		return mvc;
	}
	
	/** 创建日志*/
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Diary diary=new Diary();
		BeanUtils.populate(diary, request.getParameterMap());
		diary.setSrcrecid(0l);
		this.diaryService.saveDiary(diary);
		return new ModelAndView("redirect:/app/admin/diary/show.action");
	}
	
	/** 回复日志*/
	@RequestMapping(value="/reply",method=RequestMethod.POST)
	public ModelAndView reply(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Diary diary=new Diary();
		BeanUtils.populate(diary, request.getParameterMap());
		String srcrecid=(String)request.getParameter("srcrecid");
		diary.setSrcrecid(new Long(srcrecid).longValue());
		this.diaryService.saveDiary(diary);
		return new ModelAndView("redirect:/app/admin/diary/show.action");
	}
	
	/** 审核日志*/
	@RequestMapping(value="/auditDiary",method=RequestMethod.POST)
	@ResponseBody
	public String auditDiary(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Diary diary=new Diary();
		BeanUtils.populate(diary, request.getParameterMap());
		return this.diaryService.auditDiary(diary)+"";
	}
}