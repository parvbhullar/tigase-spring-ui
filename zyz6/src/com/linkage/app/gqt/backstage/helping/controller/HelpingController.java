package com.linkage.app.gqt.backstage.helping.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.linkage.app.gqt.backstage.members.entity.Assist;

import com.linkage.app.gqt.backstage.helping.controller.HelpingController;

import com.linkage.app.gqt.backstage.helping.entity.Worksheet;
import com.linkage.app.gqt.backstage.members.entity.Postulant;

import com.linkage.app.gqt.backstage.helping.service.HelpingService;
import com.linkage.app.gqt.backstage.log.service.LogService;

import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.backstage.org.service.OrgService;

import com.linkage.app.gqt.backstage.sysparam.entity.Sysparam;


import com.linkage.app.gqt.front.reg.entitys.Members;

import com.linkage.app.gqt.front.reg.service.MembersService;
import com.linkage.app.gqt.init.entity.SysParam;
import com.linkage.app.gqt.purview.entity.User;

import com.linkage.framework.Constants;
import com.linkage.framework.cache.CacheKey;
import com.linkage.framework.page.CouponPageUtil;


@Controller
@RequestMapping("/manage/helping") 
public class HelpingController {
	final Logger logger = LoggerFactory.getLogger(HelpingController.class);
	
	private HelpingService helpingService;
	private MembersService membersService;
	private MemcachedClient memcachedClient;
	private LogService logService;
	private OrgService orgService;
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	@Autowired
	public void setHelpingService(HelpingService helpingService) {
		this.helpingService = helpingService;
	}
	
	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	
	@Autowired
	public void setMembersService(MembersService membersService) {
		this.membersService = membersService;
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	@Autowired
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}	
	
	
	
	
	/** 工单列表展示 */
	@RequestMapping(value="/showWorksheet")
	public ModelAndView showWorksheet(HttpServletRequest request,HttpServletResponse response) throws Exception {	
		
		// 获取分页信息
		String pagevar = request.getParameter("page");
		int page = pagevar==null||pagevar.equals("") ? 1 : Integer.valueOf(pagevar);
		int size = Constants.EC_WEBPREFERENTIAL_MANAGE_SHOPS_SIZE;
		
		// 获取登录用户信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		long currentOrgId = ((User)principal).getCurrentOrgId();
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		
		// 查询活动总数
		int total = this.helpingService.countWorksheet(currentOrgId);
		// 生成分页工具栏
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(page);
		pageUtil.setTotalRow(total);
		page = page > pageUtil.getTotalPage()? 1:page;
		
		int start = (page - 1) * size;
		int end = start + size;
		// 查询当前页活动
		List<Worksheet> worksheetList=this.helpingService.showWorkSheet(start, end , currentOrgId);		
		
		
		ModelAndView mvc=new ModelAndView("/manage/helping/showWorksheet");
		mvc.addObject("worksheetList",worksheetList);
		// 分页工具栏
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		return mvc;
	}
	
	/** 援助列表展示 */
	@RequestMapping(value="/showWorksheet2")
	public ModelAndView showWorksheet2(HttpServletRequest request,HttpServletResponse response) throws Exception {	
		
		// 获取分页信息
		String pagevar = request.getParameter("page");
		int page = pagevar==null||pagevar.equals("") ? 1 : Integer.valueOf(pagevar);
		int size = Constants.EC_WEBPREFERENTIAL_MANAGE_SHOPS_SIZE;
		
		// 获取登录用户信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		long currentOrgId = ((User)principal).getCurrentOrgId();
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		
		// 查询活动总数
		int total = this.helpingService.countWorksheet2(currentOrgId);
		// 生成分页工具栏
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(page);
		pageUtil.setTotalRow(total);
		page = page > pageUtil.getTotalPage()? 1:page;
		
		int start = (page - 1) * size;
		int end = start + size;
		// 查询当前页活动
		List<Worksheet> worksheetList=this.helpingService.showWorkSheet2(start, end , currentOrgId);		
		
		
		ModelAndView mvc=new ModelAndView("/manage/helping/showWorksheet2");
		mvc.addObject("worksheetList",worksheetList);
		// 分页工具栏
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		return mvc;
	}
	
	/** 跳转到发布工单画面 */
	@RequestMapping(value="/addWorksheet")
	public ModelAndView addWorksheet() throws Exception {		
		List<Sysparam> typeList=this.helpingService.showType();
		
		// 获取登录用户信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		long currentOrgId = ((User)principal).getCurrentOrgId();
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}		
		// 获取工单ID
		long worksheetid = this.helpingService.getWorksheetid();
		ModelAndView mvc=new ModelAndView("/manage/helping/addWorksheet");
		mvc.addObject("typeList",typeList);
		mvc.addObject("currentOrgId", currentOrgId);
		mvc.addObject("username",username);
		mvc.addObject("worksheetid",worksheetid);
		
		return mvc;
	}

	/** 跳转到志愿者画面 */
	@RequestMapping(value="/showassist")
	public ModelAndView showassist(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		// 获取分页信息
		String pagevar = request.getParameter("page");
		int page = pagevar==null||pagevar.equals("") ? 1 : Integer.valueOf(pagevar);
		int size = Constants.EC_WEBPREFERENTIAL_MANAGE_SHOPS_SIZE;
		
		long id = Long.parseLong(request.getParameter("typeId"));
		
		
		// 查询活动总数
		int total = this.helpingService.countPostulant(id);
		// 生成分页工具栏
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(page);
		pageUtil.setTotalRow(total);
		page = page > pageUtil.getTotalPage()? 1:page;
		
		int start = (page - 1) * size;
		int end = start + size;
		
		int sysValue = this.helpingService.querySysValue(id);
		
		// 获取登录用户信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		long currentOrgId = ((User)principal).getCurrentOrgId();

		
		List<Postulant> postulant= this.helpingService.showMembers(start, end, id, currentOrgId);
		
		ModelAndView mvc=new ModelAndView("/manage/helping/showassist");
		mvc.addObject("id",id);
		mvc.addObject("sysValue",sysValue);
		mvc.addObject("postulant",postulant);
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		return mvc;
	}	
	

	/** 跳转到被帮扶者画面 */
	@RequestMapping(value="/assist")
	public ModelAndView assist(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		
		// 获取登录用户信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long currentOrgId = ((User)principal).getCurrentOrgId();
		
		String name = request.getParameter("aname");
		
		
		List<Assist> assist= this.helpingService.showAssist(currentOrgId, name);
		
		ModelAndView mvc=new ModelAndView("/manage/helping/assist");
		mvc.addObject("assist",assist);
		return mvc;
	}		
	
	
	/** 保存发布工单 */
	@RequestMapping(value="/saveWorksheet")
	public ModelAndView saveWorksheet(HttpServletRequest request,HttpServletResponse response) throws Exception {
			Worksheet worksheet = new Worksheet();
		// 获取表单数据
		BeanUtils.populate(worksheet, request.getParameterMap());

		// 保存发布的活动信息
		int updateNum = this.helpingService.insertWorksheet(worksheet);
		ModelAndView mav=new ModelAndView("/manage/helping/result");
		// 返回结果 result 1:成功 0:失败
		mav.addObject("result", updateNum);
		return mav;
	}
	

	/** 跳转到修改发布工单画面 */
	@RequestMapping(value="/editWorksheet")
	public ModelAndView toEditActive(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//志愿者列表
		List<Sysparam> typeList=this.helpingService.showType();
		// 活动ID
		long id = Long.parseLong(request.getParameter("id"));
		Worksheet worksheet = this.helpingService.findWorksheetById(id);
		// 查询该活动
		ModelAndView mvc=new ModelAndView("/manage/helping/editWorksheet");
		
		// 获取登录用户信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		long currentOrgId = ((User)principal).getCurrentOrgId();
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}		
		mvc.addObject("typeList",typeList);
		mvc.addObject("currentOrgId", currentOrgId);
		mvc.addObject("username",username);		
		mvc.addObject("worksheetid",id);	
		mvc.addObject(worksheet);
		return mvc;
	}
	
	/** 发动短信 */
	@RequestMapping(value="/sendMessage.action",method=RequestMethod.POST)
	public String insertMt(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 帮扶活动ID
		long worksheetid = Long.parseLong(request.getParameter("worksheetid"));
		//号码
		String dn = request.getParameter("dn");
		String message = request.getParameter("message");
		
		
		// 从缓存中获取短信发送参数
		Map sysparamMap =(HashMap) memcachedClient.get(CacheKey.ZYZ_SYSPARAM);

		List<SysParam> timtList = (List<SysParam>)sysparamMap.get("TIMT");
		int timtsize = 0;
		if(null != timtList){
			timtsize = timtList.size();
		}
		Map timtParam = new HashMap();
		SysParam timt = null;
		for(int i = 0; i < timtsize; i++){
			timt = timtList.get(i);
			timtParam.put(timt.getParamKey(), timt.getParamValue());
		}
		
		this.logService.insertMt(timtParam.get("SRCID")+ "2" + String.valueOf(worksheetid),dn, message,timtParam);

		return null;
	}	
	
	/** 结束工单 */
	@RequestMapping(value="/finishWorksheet.action",method=RequestMethod.POST)
	public String finishWorksheet(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 活动ID
		long id = Long.parseLong(request.getParameter("id"));
		this.helpingService.finishWorksheet(id);
		
		//当前工单积分
		long point = this.helpingService.queryWorksheetValue(id);
		
		//志愿者id
		long vid = this.helpingService.queryMemberId(id);
		
		//志愿者当前积分
		long allPoint = this.helpingService.queryMemberSysValue(vid);
		
		
		// 从缓存中获取服务意向列表
		Map sysparamMap =(HashMap) memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		List hd2jf = (List<SysParam>)sysparamMap.get("HD2JF");
		if(null != hd2jf && hd2jf.size() > 0){
			SysParam param = (SysParam)hd2jf.get(0);
			point = point * Integer.parseInt(param.getParamValue());
		}
		
		
		//总积分
		long all = point + allPoint;
		
		//更新志愿者积分
		this.helpingService.updateMemberValue(vid,all);
		
		return null;
	}	
	
	/** 删除工单 */
	@RequestMapping(value="/deleteWorksheet.action",method=RequestMethod.POST)
	public String deleteWorksheet(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 活动ID
		long id = Long.parseLong(request.getParameter("id"));
		this.helpingService.delWorksheet(id);
		return null;
	}	
	
	
	/** 跳转到活动详情画面 */
	@RequestMapping(value="/worksheetDetail")
	public ModelAndView worksheetDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 活动ID
		long id = Long.parseLong(request.getParameter("id"));
		Worksheet worksheet = this.helpingService.findWorksheetById(id);
		// 查询该活动
		ModelAndView mav=new ModelAndView("/manage/helping/detail");
		mav.addObject(worksheet);
		return mav;
	}	
	
	
	/**
	 * 过滤组织
	 * @param orgList
	 * @param currentOrgId
	 * @return
	 */
	public List<Org> getOrgs(List<Org> orgList,long currentOrgId)
	{
		List<Org> tempOrgList=new ArrayList<Org>();
		Org o;
		for(int i=0;i<orgList.size();i++)
		{
			o=(Org)orgList.get(i);
				if(o.getParentOrgId()==currentOrgId)
					tempOrgList.add((Org)orgList.get(i));
		}
		return tempOrgList;
	}
	
	/**
	 * 过滤组织
	 * @param orgList
	 * @param currentOrgId
	 * @return
	 */
	public Org getOrg(List<Org> orgList,long currentOrgId)
	{
		List<Org> tempOrgList=new ArrayList<Org>();
		Org o;
		for(int i=0;i<orgList.size();i++)
		{
			o=(Org)orgList.get(i);
				if(o.getOrgId()==currentOrgId)
					tempOrgList.add((Org)orgList.get(i));
		}
		return (Org)tempOrgList.get(0);
	}	
	
	
	/**
	 * 生成多选树结构
	 * @param list
	 * @param newList
	 * @param orgId
	 * @return
	 */
	private List<Org> getSubOrgList(List<Org> list,List<Org> newList,long orgId){
		Org orgTemp;
		if(list==null||list.size()==0){
			return new ArrayList<Org>();
		}else if(newList==null){
			newList=new ArrayList<Org>();
		}else{
			for(Org org:list){
				if(org.getOrgId()==orgId){
					orgTemp=new Org();
					orgTemp.setOrgName(org.getParentOrgId()+"_"+org.getOrgId());
					orgTemp.setOrgContactor(org.getOrgName()+";");
					orgTemp.setOrgId(org.getOrgId());
					orgTemp.setOrgTypeId(org.getOrgTypeId());
					newList.add(orgTemp);
				}
				if(org.getParentOrgId()==orgId){
					getSubOrgList(list,newList,org.getOrgId());
				}
			}
		}
		return newList;
	}	
	
	
	
	
	
	/** 援助次数提示展示 */
	@RequestMapping(value="/showCount")
	public ModelAndView showCount(HttpServletRequest request,HttpServletResponse response) throws Exception {	
		// 获取登录用户信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		long currentOrgId = ((User)principal).getCurrentOrgId();

		// 查询活动总数
		int total = this.helpingService.countWorksheet2(currentOrgId);

		ModelAndView mvc=new ModelAndView("/manage/helping/showCount");
		mvc.addObject("total",total);
		return mvc;
	}	
	
	
	
	
	
	
	
	
	/**  */
	@RequestMapping(value="/helpingCount")
	public ModelAndView helpingCount(HttpServletRequest request,HttpServletResponse response) throws Exception {	
		// 获取登录用户信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		long currentOrgId = ((User)principal).getCurrentOrgId();

		ModelAndView mvc=new ModelAndView("/manage/helping/helpingCount");
		
		if (request.getParameter("startDate") != null ){
		
			String startDate = request.getParameter("startDate").trim();
			String overDate = request.getParameter("overDate").trim();

			// 查询当前记录
			List<Org> orgList = this.helpingService.helpingCount(currentOrgId, startDate, overDate);		
			mvc.addObject("overDate",overDate);
			mvc.addObject("startDate",startDate);
			mvc.addObject("orgList",orgList);

		}
		
		return mvc;
	}		
	
	
	
	/** 某组织下的活动列表 */
	@RequestMapping(value="/findHelpingList")
	public ModelAndView findHelpingList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 组织ID
		Long orgid = Long.parseLong((String)request.getParameter("orgid"));
		// 开始日期
		String startdate = (String)request.getParameter("startdate");
		// 结束日期
		String enddate = (String)request.getParameter("overdate");
		// 从缓存中获取组织列表
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		// 选择组织 信息
		Org org = getOrg(orgList,orgid);
		// 获取分页信息
		String pagevar = request.getParameter("page");
		int page = pagevar==null||pagevar.equals("") ? 1 : Integer.valueOf(pagevar);
		int size = Constants.EC_WEBPREFERENTIAL_MANAGE_SHOPS_SIZE;
		// 查询活动总数
		int total = this.helpingService.countHelpingList(orgid, startdate, enddate);
		// 生成分页工具栏
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(page);
		pageUtil.setTotalRow(total);
		page = page > pageUtil.getTotalPage()? 1:page;
		
		int start = (page - 1) * size;
		int end = start + size;
		// 查询当前页活动
		List<Worksheet> helpingList=this.helpingService.findHelpingList(orgid, startdate, enddate, start, end);
		
		ModelAndView mvc=new ModelAndView("/manage/helping/findHelpingList");
		mvc.addObject("helpingList", helpingList);
		// 选择的组织名称
		mvc.addObject("orgName", org.getOrgName());
		mvc.addObject("startdate", startdate);
		mvc.addObject("enddate", enddate);
		// 分页工具栏
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		return mvc;
	}
	

	
	
	
	
}
