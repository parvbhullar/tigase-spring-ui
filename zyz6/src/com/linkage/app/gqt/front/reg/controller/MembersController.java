package com.linkage.app.gqt.front.reg.controller;

import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.linkage.app.gqt.backstage.active.entity.Active;
import com.linkage.app.gqt.backstage.active.entity.IssueWorkSheet;
import com.linkage.app.gqt.backstage.active.service.ActiveService;
import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.backstage.members.service.PostulantService;
import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.backstage.org.service.OrgService;
import com.linkage.app.gqt.front.reg.entitys.Data;
import com.linkage.app.gqt.front.reg.entitys.Members;
import com.linkage.app.gqt.front.reg.service.MembersService;
import com.linkage.app.gqt.init.entity.OrgArea;
import com.linkage.app.gqt.init.entity.SysParam;
import com.linkage.framework.Constants;
import com.linkage.framework.cache.CacheKey;
import com.linkage.framework.page.CouponPageUtil;
import com.linkage.framework.util.JsonUtils;

@Controller
@RequestMapping("/app/admin/reg")
/**
 * 志愿者个人用户处理类
 */
public class MembersController {
	final Logger logger = LoggerFactory.getLogger(MembersController.class);
	
	private MembersService membersService;
	private MemcachedClient memcachedClient;
	private PostulantService postulantService;
	private ActiveService activeService;
	private OrgService orgService;
	
	
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
	
	@Autowired
	public void setPostulantService(PostulantService postulantService) {
		this.postulantService = postulantService;
	}
	
	@Autowired
	public void setActiveService(ActiveService activeService) {
		this.activeService = activeService;
	}
	
	@Autowired
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	/** 前台志愿者登陆*/
	@RequestMapping(value="/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Members members=new Members();
		BeanUtils.populate(members, request.getParameterMap());
		Postulant postulant=this.membersService.login(members);
		request.getSession().setAttribute("postulant", postulant);
		HashMap hashMap=(HashMap)memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		ModelAndView mvc=null;
		if(postulant==null)
		{
			mvc=new ModelAndView("redirect:index.action");
		}
		else
			mvc=new ModelAndView("redirect:/app/admin/reg/showactive.action");
		mvc.addObject("xl", hashMap.get("XL"));
		mvc.addObject("fwyx", hashMap.get("FWYX"));
		mvc.addObject("mz", hashMap.get("MZ"));
		mvc.addObject("zy", hashMap.get("ZY"));
		mvc.addObject("zjlx", hashMap.get("ZJLX"));
		mvc.addObject("ntfwyx", hashMap.get("NTFWYX"));
		mvc.addObject("ntfwyxorg", hashMap.get("NTFWYXORG"));
		mvc.addObject("jn", hashMap.get("JN"));
		return mvc;
	}
	
	/**
	 * 首页
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mvc=new ModelAndView("redirect:../../../app/index.jsp");
		mvc.addObject("login", "false");
		return mvc;
	}
	
	/** 进入新增 第一步*/
	@RequestMapping(value="/register")
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mvc=new ModelAndView("/app/admin/reg/register");
		mvc.addObject("orgtype", memcachedClient.get(CacheKey.ZYZ_ORGTYPE));
		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));
		return mvc;
	}
	
	/** jstree*/
	@RequestMapping(value="/tree")
	public ModelAndView tree(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mvc=new ModelAndView("/app/admin/reg/tree");
		mvc.addObject("orgtype", memcachedClient.get(CacheKey.ZYZ_ORGTYPE));
		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));
		return mvc;
	}
	
	/** jstree data*/
	@RequestMapping(value="/treedata")
	@ResponseBody
	public void treedata(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		// 从缓存中获取组织列表
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		List<Org> newList=new ArrayList();
//		orgService.getSubOrgList(orgList, newList, new Long("320600").longValue());
//		
//		String s="[ { \"data\" : \"A node\", \"children\" : [ { \"data\" : \"Only child\", \"state\" : \"closed\" } ], \"state\" : \"open\" }, \"Ajax node\" ]";
//		JSONArray jSONAaary=new JSONArray();
//		for(int i=0;i<newList.size();i++)
//		{
//			String str1=new String(((Org)newList.get(i)).getOrgName()); 
//			JSONObject obj2=new JSONObject();
//			obj2.put("data", str1);
//			jSONAaary.put(obj2);
//		}
		PrintWriter pw = response.getWriter();
	    //pw.print(jSONAaary.toString());
		pw.print(orgService.getSubOrgListJson(orgList, newList, new Long("320600").longValue()));
	}
	
	/** jqgrid3.8*/
	@RequestMapping(value="/jqgrid")
	public ModelAndView jqgrid(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mvc=new ModelAndView("/app/admin/reg/jqgrid");
		mvc.addObject("orgtype", memcachedClient.get(CacheKey.ZYZ_ORGTYPE));
		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		mvc.addObject("orgList", orgList);
		return mvc;
	}
	
	/** jqgrid data*/
	@RequestMapping(value="/jqgriddata")
	@ResponseBody
	public void jqgriddata(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		// 从缓存中获取组织列表
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		List<Org> newList=new ArrayList();
		orgService.getSubOrgList(orgList, newList, new Long("410682").longValue());
		Data data=new Data();
		List list = new ArrayList();
		data.setId(1);
		data.setInvdate("2007-10-03");
		data.setName("test2");
		data.setNote("note2");
		data.setAmount(300l);
		data.setTax(200l);
		data.setTotal(2000l);
		list.add(data);
		JsonUtils.listToJson(list);
        System.out.println(JsonUtils.listToJson(list));   
		PrintWriter pw = response.getWriter();
	    pw.print(JsonUtils.listToJson(list));
	}
	
	/** 取得所有组织树形节点*/
	@RequestMapping(value="/treedataAllSubOrg")
	@ResponseBody
	public void treedataAllSubOrg(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		// 从缓存中获取组织列表
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		List<Org> newList=new ArrayList();
		PrintWriter pw = response.getWriter();
		String orgId=request.getParameter("orgId");
		String s="";
//		 s=orgService.getAllSubOrgJson(orgList, newList, new Long("320600").longValue(),1,3);
		 s=orgService.getAllSubOrgJson(orgList, newList, new Long(orgId).longValue(),1,3);//南通市崇川区
//		s=orgService.getAllSubOrgJson(orgList, newList, new Long("410666").longValue(),1,3);//城东街道
////		s=orgService.getAllSubOrgJson(orgList, newList, new Long("410682").longValue(),1,3);//友谊社区
		
//		String s=orgService.getAllSubOrgJson(orgList, newList, new Long("1").longValue(),1,3);
//		String s=orgService.getAllSubOrgJson(orgList, newList, new Long("10043").longValue(),1,2);
//		System.out.println(s);
		pw.print(s);
	}

	/** 进入新增 */
	@RequestMapping(value="/register2")
	public ModelAndView register2(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Org org=new Org();
		ModelAndView mvc=null;
		String regorgid=request.getParameter("regorg");
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		List<Org> newList=new ArrayList<Org>();
		List<Org> tempList=new ArrayList<Org>();
		if(regorgid!=null)
		{
			for(int i=0;i<orgList.size();i++)
			{
				if(regorgid.equals(((Org)orgList.get(i)).getOrgId()+""))
				{
					org=(Org)orgList.get(i);
				}
			}
			mvc=new ModelAndView("/app/admin/reg/register3");
		}
		else
		{
			mvc=new ModelAndView("/app/admin/reg/registernt");
			for(int i=0;i<orgList.size();i++)
			{
				if(((Org)orgList.get(i)).getOrgId()==320600)
					{
						tempList.add((Org)orgList.get(i));
					}
			}
			//orgService.getSubOrgListReg(orgList, newList, 320600);
			
		}
		HashMap hashMap=(HashMap)memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		mvc.addObject("org", org);
		mvc.addObject("xl", hashMap.get("XL"));
		mvc.addObject("fwyx", hashMap.get("FWYX"));
		mvc.addObject("mz", hashMap.get("MZ"));
		mvc.addObject("zy", hashMap.get("ZY"));
		mvc.addObject("zjlx", hashMap.get("ZJLX"));
		mvc.addObject("ntfwyx", hashMap.get("NTFWYX"));
		mvc.addObject("zyzntfwyx", memcachedClient.get(CacheKey.ZYZ_NT_FWYX));
		mvc.addObject("ntfwyxorg", hashMap.get("NTFWYXORG"));
		mvc.addObject("jn", hashMap.get("JN"));
		mvc.addObject("tempList", tempList);
		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));
		return mvc;
	}
	
	/** 检查密码*/
	@RequestMapping(value="/checkPassword")
	@ResponseBody
	public String checkPassword(Members members,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String oldpassword=request.getParameter("oldpassword");
		Postulant postulant=(Postulant)request.getSession().getAttribute("postulant");
		postulant.setPassword(oldpassword);
		int i=this.membersService.checkPassword(postulant);
		if(1==i)
			return "true";
		else
			return "false";
	}
	
	/** 更新密码*/
	@RequestMapping(value="/updatePassword")
	public ModelAndView updatePassword(Members members,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Postulant temppostulant=(Postulant)request.getSession().getAttribute("postulant");
		Postulant postulant=new Postulant();
		BeanUtils.populate(postulant, request.getParameterMap());
		temppostulant.setPassword(postulant.getPassword());
		int i=this.membersService.updatePassword(temppostulant);
		return new ModelAndView("redirect:/app/admin/reg/edit.action?dn="+postulant.getDn());
	}
	
	
	/** 检查用户名是否重复*/
	@RequestMapping(value="/checkLoginname")
	@ResponseBody
	public String checkLoginname(Members members,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String username=request.getParameter("loginName");
		int i=this.membersService.checkUsername(username);
		if(1==i)
			return "false";
		else
			return "true";
	}
	
	/** 检查手机号码是否重复*/
	@RequestMapping(value="/checkDn")
	@ResponseBody
	public String checkDn(Members members,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Postulant postulant=new Postulant();
		BeanUtils.populate(postulant, request.getParameterMap());
		String dn=request.getParameter("dn");
		String uuid=request.getParameter("id");
		int i=0;
		if("".equals(uuid)||uuid==null)
		{
			i=this.membersService.checkDn(dn);
		}
		else//编辑状态检查手机号码是否重复
		{
			i=this.membersService.checkDn(dn,uuid);
		}
			
		if(1==i)
			return "false";
		else
			return "true";
	}
	
	/** 检查身份证是否重复*/
	@RequestMapping(value="/checkCredcode")
	@ResponseBody
	public String checkCredcode(Members members,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String credcode=request.getParameter("credcode");
		String id=request.getParameter("id");
		int i=0;
		if("".equals(id)||id==null)
		{
			i=this.membersService.checkCredcode(credcode);
		}
		else//编辑状态检查身份证是否重复
		{
			i=this.membersService.checkCredcode(credcode, id);
		}
			
		if(1==i)
			return "false";
		else
			return "true";
	}
	
	/**
	 * zhiy
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/create")
	public ModelAndView create(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		Postulant postulant=new Postulant();
		BeanUtils.populate(postulant, request.getParameterMap());
		postulant.setRegip(request.getRemoteAddr());
		//String[] time = request.getParameterValues("servetimes");
		String[] fwyx = request.getParameterValues("intention");
//		String servetime="";
		String jn="";
		String intention = "";
//		if(time!=null)
//		{
//			for (int i = 0; i < time.length; i++) {
//				servetime += time[i];
//				if(i+1<time.length)
//					servetime += ",";
//			}
//		}
		String[] jnArr = request.getParameterValues("jn");
		if(jnArr!=null)
			{
				for (int i = 0; i < jnArr.length; i++) {
					jn += jnArr[i];
					if(i+1<jnArr.length)
						jn += ",";
				}
			}
		postulant.setJn(jn);
		if(intention!=null)
		{
			for (int i = 0; i < fwyx.length; i++) {
				intention += fwyx[i];
				if(i+1<fwyx.length)
					intention += ",";
			}
		}
		postulant.setIntention(intention);
		//postulant.setServetimes(servetime);
		String bir = request.getParameter("birthday1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		postulant.setBirthday(sdf.parse(bir));
		int i=this.postulantService.insertFornt(postulant);
		request.getSession().setAttribute("postulant", postulant);
		return new ModelAndView("redirect:/app/admin/reg/edit.action?dn="+postulant.getDn()+"&reg="+i);
	}
	
	/** 编辑 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Org org=new Org();
		HashMap hashMap=(HashMap)memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		List<Org> tempList=new ArrayList<Org>();
		String regorgid=request.getParameter("regorg");
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		List<Org> newList=new ArrayList<Org>();
		if(regorgid!=null)
		{
			for(int i=0;i<orgList.size();i++)
			{
				if(regorgid.equals(((Org)orgList.get(i)).getOrgId()+""))
				{
					org=(Org)orgList.get(i);
				}
			}
		}
		else
		{
			for(int i=0;i<orgList.size();i++)
			{
				if(((Org)orgList.get(i)).getOrgId()==320600)
					{
						tempList.add((Org)orgList.get(i));
					}
			}
			//orgService.getSubOrgListReg(orgList, newList, 320600);
			
		}
		
		/**
		 * 取社区的上级组织开始
		 */
		Postulant postulant=(Postulant)request.getSession().getAttribute("postulant");
		postulant=this.membersService.getMembersByDn(postulant.getDn());
		String street=this.orgService.getUpOrg(orgList, postulant.getCommunityid()).getOrgId()+"";
		String area=this.orgService.getUpOrg(orgList, new Long(street).longValue()).getOrgId()+"";
		/**
		 * 取社区的上级组织结束
		 */
		ModelAndView mvc=null;
		mvc=new ModelAndView("/app/admin/reg/admin");
		mvc.addObject("postulant", postulant);
		mvc.addObject("xl", hashMap.get("XL"));
		mvc.addObject("fwyx", hashMap.get("FWYX"));
		mvc.addObject("mz", hashMap.get("MZ"));
		mvc.addObject("zy", hashMap.get("ZY"));
		mvc.addObject("zjlx", hashMap.get("ZJLX"));
//		mvc.addObject("ntfwyx", hashMap.get("NTFWYX"));
		mvc.addObject("zyzntfwyx", memcachedClient.get(CacheKey.ZYZ_NT_FWYX));
		mvc.addObject("ntfwyxorg", hashMap.get("NTFWYXORG"));
		mvc.addObject("jn", hashMap.get("JN"));
		mvc.addObject("tempList", tempList);
		mvc.addObject("street", street);
		mvc.addObject("area", area);
		mvc.addObject("reg", request.getParameter("reg"));
		return mvc;
	}
	
	/** 修改密码 */
	@RequestMapping(value="/change")
	public ModelAndView change(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mvc=new ModelAndView("/app/admin/reg/change");
		return mvc;
	}
	
	/** 个人参数 */
	@RequestMapping(value="/personParam")
	public ModelAndView personParam(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Postulant postulant=(Postulant)request.getSession().getAttribute("postulant");
		postulant=this.membersService.getMembersByDn(postulant.getDn());
		HashMap hashMap=(HashMap)memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		//将积分转换为小时
		List<SysParam> list=(List<SysParam>)hashMap.get("HD2JF");
		SysParam sysParam=(SysParam)list.get(0);
		String HD2JF=sysParam.getParamValue();
		double HD=postulant.getTotalpoints()/(new Long(HD2JF).longValue());
		postulant.setCommunityid(new Long((long) HD).longValue());
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumFractionDigits(0);
		double points=postulant.getTotalpoints();
		postulant.setOrgid(new Long(format.format(points)).longValue());
		
		ModelAndView mvc=new ModelAndView("/app/admin/reg/personParam");
		mvc.addObject("postulant", postulant);
		return mvc;
	}
	
	/** 更新志愿者个人参数 */
	@RequestMapping(value="/updatePersonParam")
	public void updatePersonParam(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		Postulant temppostulant=(Postulant)request.getSession().getAttribute("postulant");
		Postulant postulant=new Postulant();
		BeanUtils.populate(postulant, request.getParameterMap());
		temppostulant.setIsverify(postulant.getIsverify());
		temppostulant.setServetimes(postulant.getServetimes());
		int i=this.membersService.updatePersonParam(temppostulant);
		PrintWriter pw = response.getWriter();
		pw.print(i);
	}

	/*
	 * 更新操作-修改表单
	 */
	@RequestMapping(value="/update.action", method=RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Postulant postulant=new Postulant();
		BeanUtils.populate(postulant, request.getParameterMap());
		
		String bir = request.getParameter("birthday1");
		if(bir!=null && !bir.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			postulant.setBirthday(sdf.parse(bir));
		}
		String jn="";
		String[] jnArr = request.getParameterValues("jn");
		if(jnArr!=null)
			{
				for (int i = 0; i < jnArr.length; i++) {
					jn += jnArr[i];
					if(i+1<jnArr.length)
						jn += ",";
				}
				postulant.setJn(jn);
			}
		this.postulantService.updateFront(postulant);
		postulant=this.membersService.getMembers(postulant.getId());
		request.getSession().removeAttribute("postulant");
		request.getSession().setAttribute("postulant", postulant);
		return new ModelAndView("redirect:edit.action");
	}
	
	/** 列出所有活动 */
	@RequestMapping(value="/showactive")
	public ModelAndView showactive(HttpServletRequest request,HttpServletResponse response) throws Exception {

		// 选择组织按钮选中的组织ID
		long selectOrgId = -1;
		if(null != request.getParameter("selectOrgId")){
			selectOrgId = Long.parseLong((String)request.getParameter("selectOrgId"));
		}
		String province = "";
		// 手动匹配 组织树（多选）
		List<Org> subOrgList = new ArrayList<Org>();
		// 选择组织 组织树（单选）
		List<Org> selOrgList = new ArrayList<Org>();
		// 当前组织信息
		Org currentOrg = null;
		// 获取分页信息
		String pagevar = request.getParameter("page");
		int page = pagevar==null||pagevar.equals("") ? 1 : Integer.valueOf(pagevar);
		int size = Constants.EC_WEBPREFERENTIAL_MANAGE_SHOPS_SIZE;
		// 查询活动总数
		int total = this.activeService.countActive1(320600);
		// 生成分页工具栏
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(page);
		pageUtil.setTotalRow(total);
		page = page > pageUtil.getTotalPage()? 1:page;
		
		int start = (page - 1) * size;
		int end = start + size;
		// 查询当前用户所能看到的参加和未参加的活动
		Postulant postulant=(Postulant)request.getSession().getAttribute("postulant");
		List<Active> activeList=this.activeService.showActive1(320600, start, end,0,postulant.getId());
		
		ModelAndView mvc=new ModelAndView("/app/admin/reg/showactive");
	//	mvc.addObject("mList",);
		long pid = this.membersService.getMembersByDn(postulant.getDn()).getId();
		mvc.addObject("activeList", formatList(activeList,this.membersService.getIssueWorkSheetByVolunteerid(pid)));
		// 分页工具栏
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		mvc.addObject("postulant",postulant);
		mvc.addObject("page",page);
		mvc.addObject("size",size);
		return mvc;
	}
	
	/** 注销 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mvc=new ModelAndView("/app/admin/reg/logout");
		HashMap hashMap=(HashMap)memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		
		mvc.addObject("resion", hashMap.get("RESION"));
		return mvc;
	}
	
	/** 注销提交 */
	@RequestMapping(value="/savelogout")
	public ModelAndView savelogout(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String[] resions = request.getParameterValues("resion");
		
		//String[] fwyx = request.getParameterValues("intention");
		String resion="";
		if(resions!=null)
		{
			for (int i = 0; i < resions.length; i++) {
				resion += resions[i];
				if(i+1<resions.length)
					resion += ",";
			}
		}
		Postulant postulant=(Postulant)request.getSession().getAttribute("postulant");
		if(postulant.getId()==0)
		{
			postulant = this.membersService.getMembersByDn(postulant.getDn());
		}
		this.membersService.logout(postulant.getId(), resion);
		ModelAndView mvc=new ModelAndView("redirect:/app/index.jsp");
		
		return mvc;
	}

		
	
	/** 列出所有活动 */
	@RequestMapping(value="/showrecord")
	public ModelAndView showrecord(HttpServletRequest request,HttpServletResponse response) throws Exception {

		// 选择组织按钮选中的组织ID
		long selectOrgId = -1;
		if(null != request.getParameter("selectOrgId")){
			selectOrgId = Long.parseLong((String)request.getParameter("selectOrgId"));
		}
		String province = "";
		// 手动匹配 组织树（多选）
		List<Org> subOrgList = new ArrayList<Org>();
		// 选择组织 组织树（单选）
		List<Org> selOrgList = new ArrayList<Org>();
		// 当前组织信息
		Org currentOrg = null;
		// 获取分页信息
		String pagevar = request.getParameter("page");
		int page = pagevar==null||pagevar.equals("") ? 1 : Integer.valueOf(pagevar);
		int size = Constants.EC_WEBPREFERENTIAL_MANAGE_SHOPS_SIZE;
		Postulant postulant=(Postulant)request.getSession().getAttribute("postulant");
		// 查询活动总数
		int total = this.activeService.countRecord(postulant.getId());
		// 生成分页工具栏
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(page);
		pageUtil.setTotalRow(total);
		page = page > pageUtil.getTotalPage()? 1:page;
		
		int start = (page - 1) * size;
		int end = start + size;
		// 查询当前用户所能看到的参加和未参加的活动
		
		List<Active> activeList=this.activeService.showRecord(postulant.getId());
		
		ModelAndView mvc=new ModelAndView("/app/admin/reg/showrecord");
		mvc.addObject("activeList", activeList);
		// 分页工具栏
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		return mvc;
	}
	//判断用户是否已经加入活动
	private List<Active> formatList(List<Active> list,List<IssueWorkSheet> list1)
	{
		boolean join = false;
		for (Active active : list) {
			if(list1!=null&&list1.size()>0)
			{
				for (IssueWorkSheet issue : list1) {
					if(active.getActid().longValue()==issue.getActid().longValue()){
						join = true;
					}
				}
				active.setJoin(join);
				join=false;
			}
		}
		return list;
	}
	
	/** 加载社区 */
	@RequestMapping("/loadCommunity")
	@ResponseBody
	public void loadCommunity(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//response.setContentType("text/html;charset=utf-8");
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		String communityid=request.getParameter("communityid");
		StringBuffer sb=new StringBuffer("");
		for(int i=0;i<orgList.size();i++)
		{
			Org o=(Org)orgList.get(i);
			if((o.getOrgId()+"").equals(communityid))
				sb.append("<option label="+o.getOrgName()+" value="+o.getOrgId()+">"+o.getOrgName()+"</option>");
		}
		PrintWriter pw = response.getWriter();
	    pw.print(sb.toString());
	}
	
	/** 加载子社区 */
	@RequestMapping("/loadSubCommunity")
	@ResponseBody
	public void loadSubCommunity(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//response.setContentType("text/html;charset=utf-8");
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		String communityid=request.getParameter("communityid");
		List<Org> list=new ArrayList<Org>();
		this.orgService.getSubOrgListCommunity( orgList,list, new Long(communityid).longValue());
		StringBuffer sb=new StringBuffer("");
		for(int i=0;i<list.size();i++)
		{
			Org o=(Org)list.get(i);
				sb.append("<option label='"+o.getOrgName()+"' value='"+o.getOrgId()+"'>"+o.getOrgName()+"</option>");
				
		}
		PrintWriter pw = response.getWriter();
	    pw.print(sb.toString());
	}
	
	/** 城市联动框 */
	@RequestMapping("/load")
	@ResponseBody
	public void load(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//response.setContentType("text/html;charset=utf-8");
		HashMap hashMap=(HashMap)memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		List<OrgArea> listOrgAreaTemp=new ArrayList<OrgArea>();
		ArrayList<OrgArea> listOrgArea=(ArrayList<OrgArea>)memcachedClient.get(CacheKey.ZYZ_ORGAREA);
		String areaid=request.getParameter("areaid");
		StringBuffer sb=new StringBuffer("<option value=''>请选择</option>");
		for(int i=0;i<listOrgArea.size();i++)
		{
			OrgArea o=(OrgArea)listOrgArea.get(i);
			if(o.getAreaParentId()==new Integer(areaid).intValue())
			{
				listOrgAreaTemp.add(o);
				o.getAreaName();
				o.getAreaId();
				sb.append("<option label="+o.getAreaName()+" value="+o.getAreaId()+">"+o.getAreaName()+"</option>");
				
			}
		}
		PrintWriter pw = response.getWriter();
	    pw.print(sb.toString());
	}
	
	/** 根据区域查找所属注册机构 */
	@RequestMapping("/loadAreaOrg")
	@ResponseBody
	public void loadAreaOrg(Members members,HttpServletRequest request,HttpServletResponse response) throws Exception {
		int locareid=new Integer(request.getParameter("locareid")).intValue();
		List<Org> listOrg=(List<Org>)memcachedClient.get(CacheKey.ZYZ_ORG);
		StringBuffer sb=new StringBuffer("<option value=''>请选择</option>");
		for(int i=0;i<listOrg.size();i++)
		{
			Org o=(Org)listOrg.get(i);
				if(o.getOrgState()==1&&o.getBelongOrgId()==locareid)
				sb.append("<option label="+o.getOrgName()+" value="+o.getOrgId()+">"+o.getOrgName()+"</option>");
		}
		PrintWriter pw = response.getWriter();
		pw.print(sb.toString());
	    pw.print(sb.toString());
	}
	
	/** 根据区域查找所属注册机构 */
	@RequestMapping("/confirmActive")
	public ModelAndView confirmActive(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String activeId=request.getParameter("activeId");
		String dn=request.getParameter("dn");
		Postulant postulant=this.membersService.getMembersByDn(dn);//this.postulantService.selectById(new Long(id).longValue());
		
		int i=this.activeService.insertIssueWorkSheet(postulant, new Long(activeId).longValue());
		return new ModelAndView("redirect:showactive.action");
	}
	
	
}

