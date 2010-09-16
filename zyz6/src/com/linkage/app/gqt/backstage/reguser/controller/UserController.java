package com.linkage.app.gqt.backstage.reguser.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.front.reg.entitys.Members;
import com.linkage.app.gqt.front.reg.service.MembersService;
import com.linkage.app.gqt.init.entity.OrgArea;
import com.linkage.app.gqt.webpreferential.coupons.controller.CouponsController;
import com.linkage.framework.cache.CacheKey;

@Controller
@RequestMapping("/manage/reguser")
public class UserController {
	//默认多列排序,example: username desc,createTime asc
	final Logger logger = LoggerFactory.getLogger(UserController.class);
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private MembersService membersService;
	private MemcachedClient memcachedClient;
	
	private final String LIST_ACTION = "redirect:/zyzmembers";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	@Autowired
	public void setMembersService(MembersService membersService) {
		this.membersService = membersService;
	}
	
	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	
	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
	}
	   
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute
	public void init(ModelMap model) {
		model.put("now", new java.sql.Timestamp(System.currentTimeMillis()));
	}
	
	/** 显示 */
	@RequestMapping(value="/show")
	public ModelAndView show() throws Exception {
		List<Members> membersList=this.membersService.showMembers();
		ModelAndView mvc=new ModelAndView("/manage/reguser/show");
		mvc.addObject("membersList", membersList);
		return mvc;
	}
	
	/** 删除 */
	@RequestMapping(value = "/del.action")
	public ModelAndView del(ModelMap model,Members members,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String uuid=request.getParameter("uuid");
		this.membersService.delMembers(new Integer(uuid).intValue());
		List<Members> membersList=this.membersService.showMembers();
		ModelAndView mvc=new ModelAndView("/manage/reguser/show");
		mvc.addObject("membersList", membersList);
		return mvc;
	}
	
	
	/** 进入新增 第一步*/
	@RequestMapping(value="/audit")
	@ResponseBody
	public String audit(ModelMap model,Members members,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String uuid=request.getParameter("uuid");
		String isverify=request.getParameter("isverify");
		return ""+this.membersService.updateMembers(new Integer(uuid).intValue(), new Integer(isverify).intValue());
	}

//	/** 进入新增 */
//	@RequestMapping(value="/register2")
//	public ModelAndView _new(ModelMap model,Members members,HttpServletRequest request,HttpServletResponse response) throws Exception {
//		model.addAttribute("members",members);
//		HashMap hashMap=(HashMap)memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
//		ModelAndView mvc=new ModelAndView("/app/admin/reg/register2");
//		mvc.addObject("xl", hashMap.get("XL"));
//		mvc.addObject("fwyx", hashMap.get("FWYX"));
//		mvc.addObject("mz", hashMap.get("MZ"));
//		mvc.addObject("zy", hashMap.get("ZY"));
//		mvc.addObject("zjlx", hashMap.get("ZJLX"));
//		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));
//		return mvc;
//	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/create")
	public ModelAndView create(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String username=request.getParameter("username")==null?"":(String)request.getParameter("username");
		String name=request.getParameter("name")==null?"":(String)request.getParameter("name");
		String nickname=request.getParameter("nickname")==null?"":(String)request.getParameter("nickname");
		String password=request.getParameter("password")==null?"":(String)request.getParameter("password");
		String cellphone=request.getParameter("cellphone")==null?"":(String)request.getParameter("cellphone");
		String email=request.getParameter("email")==null?"":(String)request.getParameter("email");
		String sex=request.getParameter("sex")==null?"":(String)request.getParameter("sex");
		String birthday=request.getParameter("birthday")==null?"":(String)request.getParameter("birthday");
		String edulevel=request.getParameter("edulevel")==null?"":(String)request.getParameter("edulevel");
		String profession=request.getParameter("profession")==null?"":(String)request.getParameter("profession");
		String hobby=request.getParameter("hobby")==null?"":(String)request.getParameter("hobby");
		String location=request.getParameter("location")==null?"":(String)request.getParameter("location");
		String intention=request.getParameter("intention")==null?"":(String)request.getParameter("intention");
		String servetimes=request.getParameter("servetimes")==null?"":(String)request.getParameter("servetimes");
		String volunorgid=request.getParameter("volunorgid")==null?"":(String)request.getParameter("volunorgid");
		String credcode=request.getParameter("credcode")==null?"":(String)request.getParameter("credcode");
		String credtype=request.getParameter("credtype")==null?"":(String)request.getParameter("credtype");
		String isvalid=request.getParameter("isvalid")==null?"":(String)request.getParameter("isvalid");
		String isverify=request.getParameter("isverify")==null?"":(String)request.getParameter("isverify");
		String verifydesc=request.getParameter("verifydesc")==null?"":(String)request.getParameter("verifydesc");
		String createtime=request.getParameter("createtime")==null?"":(String)request.getParameter("createtime");
		String regip=request.getRemoteAddr();
		
		String regdate=request.getParameter("regdate")==null?"":(String)request.getParameter("regdate");
		String lastloginip=request.getParameter("lastloginip")==null?"":(String)request.getParameter("lastloginip");
		String totalpoints=request.getParameter("totalpoints")==null?"":(String)request.getParameter("totalpoints");
		String lastlognumbertime=request.getParameter("lastlognumbertime")==null?"":(String)request.getParameter("lastlognumbertime");
		int id=this.membersService.insertMember(username, name, nickname, password, cellphone, email, sex, birthday, edulevel, profession, hobby, location, intention, servetimes, volunorgid, credcode, credtype, isvalid, isverify, verifydesc, createtime, regip, regdate, lastloginip, totalpoints, lastlognumbertime);
		Members members=new Members();
		members.setName(username);
		members.setPassword(password);
		Postulant postulant=this.membersService.login(members);
		request.getSession().setAttribute("postulant", postulant);
		//int id=this.membersService.insertMember(members.getUsername(), members.getName(), members.getNickname(), members.getPassword(), members.getCellphone(), members.getEmail(), members.getSex(), members.getBirthday(), members.getEdulevel(), members.getProfession(), members.getHobby(), members.getLocation(), members.getIntention(), members.getServetimes(), members.getVolunorgid(), members.getCredcode(), members.getCredtype(), members.getIsvalid(), members.getIsverify(), members.getVerifydesc(), members.getCreatetime(), members.getRegip(), members.getRegdate(), members.getLastloginip(), members.getTotalpoints(), members.getLastlognumbertime());
		return new ModelAndView("redirect:/app/admin/reg/edit.action");
	}
	
//	/** 编辑 */
//	@RequestMapping(value="/edit")
//	public ModelAndView edit() throws Exception {
//		HashMap hashMap=(HashMap)memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
//		ModelAndView mvc=new ModelAndView("/app/admin/reg/register2");
//		mvc.addObject("xl", hashMap.get("XL"));
//		mvc.addObject("fwyx", hashMap.get("FWYX"));
//		mvc.addObject("mz", hashMap.get("MZ"));
//		mvc.addObject("zy", hashMap.get("ZY"));
//		mvc.addObject("zjlx", hashMap.get("ZJLX"));
//		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));
//		return mvc;
//	}
}

