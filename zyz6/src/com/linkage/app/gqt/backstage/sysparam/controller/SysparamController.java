package com.linkage.app.gqt.backstage.sysparam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.backstage.org.service.OrgService;
import com.linkage.app.gqt.backstage.sysparam.entity.Sysparam;
import com.linkage.app.gqt.backstage.sysparam.service.SysparamService;
import com.linkage.app.gqt.init.service.InitService;
import com.linkage.app.gqt.purview.entity.User;
import com.linkage.framework.cache.CacheKey;
import com.linkage.framework.page.CouponPageUtil;

@Controller
@RequestMapping("/manage/sysparam")
public class SysparamController {
	final Logger logger = LoggerFactory.getLogger(SysparamController.class);
	
	private SysparamService sysparamService;
	
	private MemcachedClient memcachedClient;
	
	private OrgService orgService;
	
	private InitService initService;
	
	@Autowired
	public void setSysparamService(SysparamService sysparamService) {
		this.sysparamService = sysparamService;
	}
	
	
	@Autowired
	public void setInitService(InitService initService) {
		this.initService = initService;
	}



	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	
	@Autowired
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	/** 显示所有的系统参数 */
	@RequestMapping(value="/showsysparam")
	public ModelAndView showsysparam(HttpServletRequest request,HttpServletResponse response) throws Exception {
		CouponPageUtil pageUtil=new CouponPageUtil();
		String pagevar = request.getParameter("page");
		int curPage = pagevar==null||pagevar.equals("") ? 1 : Integer.valueOf(pagevar);
		pageUtil.setCurPage(curPage);
		String paramcode= request.getParameter("paramcode");
		List<Sysparam> sysparamList;
		ModelAndView mvc=new ModelAndView();
		if("".equals(paramcode)||paramcode==null)
		{
			sysparamList=this.sysparamService.showSysparam(pageUtil);
			mvc=new ModelAndView("/manage/sysparam/show");
		}
		else
		{
			sysparamList=this.sysparamService.showSysparamByParamcode(pageUtil,paramcode);
			mvc=new ModelAndView("/manage/sysparam/showparamcode");
			
			// 获取登录用户信息
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username="";
			long currentOrgId = ((User)principal).getCurrentOrgId();
			if (principal instanceof UserDetails) {
				  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}

			// 查询组织列表
			List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
			List<Org> newList=new ArrayList();
			// 获取当前组织信息
			Org org=getOrg(orgList,currentOrgId);
			String currentOrgName=org.getOrgName();
			// 获取当前组织的第一级子节点
			getSubOrgList(orgList, newList, currentOrgId);
			String province="0_"+currentOrgId;
			mvc.addObject("province",province);
			mvc.addObject("newList",newList);
			mvc.addObject("currentOrgName",currentOrgName);
		}
		mvc.addObject("sysparamList", sysparamList);
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		return mvc;
	}
	
	/** 显示所有的系统参数 */
	@RequestMapping(value="/showjnsysparam")
	public ModelAndView showjnsysparam(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String paramkey= request.getParameter("paramkey");
		String paramvalue= request.getParameter("paramvalue");
		List<Sysparam> sysparamList;
		ModelAndView mvc=new ModelAndView();
		Map args = new HashMap();
		if(paramkey!=null&&!paramkey.equals(""))
		{
			args.put("paramkey", paramkey);
		}
		if(paramvalue!=null&&!paramvalue.equals(""))
		{
			args.put("paramvalue", paramvalue);
		}
		sysparamList=this.sysparamService.showJnSysparam(args);
		mvc=new ModelAndView("/manage/sysparam/paramjncode");
		
		
		
		mvc.addObject("paramkey",paramkey);
		mvc.addObject("paramvalue",paramvalue);
	
		mvc.addObject("sysparamList", sysparamList);
	
		return mvc;
	}
	
	/** 创建系统参数 */
	@RequestMapping(value="/create")
	@ResponseBody
	public String create(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Sysparam sysparam=new Sysparam();
		BeanUtils.populate(sysparam, request.getParameterMap());
		return this.sysparamService.insertSysparam(sysparam)+"";
	}
	/** 创建系统参数 */
	@RequestMapping(value="/createjn")
	@ResponseBody
	public String createjn(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Sysparam sysparam=new Sysparam();
		BeanUtils.populate(sysparam, request.getParameterMap());
		int r = this.sysparamService.insertJnSysparam(sysparam);
		resetmemcache();
		return r+"";
	}
	//重置缓存
	private void resetmemcache()
	{
		//清空缓存
		memcachedClient.delete(CacheKey.ZYZ_SYSPARAM);
		//初始化缓存
		this.initService.initSysParam();
	}
	/** 编辑系统参数 */
	@RequestMapping(value="/getSysParam")
	public ModelAndView getSysParam(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Sysparam sysparam=new Sysparam();
		
		ModelAndView mvc=new ModelAndView("/manage/sysparam/edit");
		
		String paramid = request.getParameter("paramid");
		sysparam.setParamid(Long.valueOf(paramid));
		//sysparam=this.sysparamService.edit(sysparam);
		
		mvc.addObject("sysparam", this.sysparamService.edit(sysparam));
		return mvc;
	}
	/** 更新系统参数 */
	@RequestMapping(value="/update")
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Sysparam sysparam=new Sysparam();
		BeanUtils.populate(sysparam, request.getParameterMap());
		this.sysparamService.updateSysparam(sysparam);
		resetmemcache();
		return new ModelAndView("redirect:showjnsysparam.action");
	}
	
	/** 验证value是否重复 */
	@RequestMapping(value="/validateValue")
	@ResponseBody
	public String validateValue(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String value = request.getParameter("value");
		return this.sysparamService.validateSysparamValue(value)+"";
	}
	
	/** 匹配服务意向 */
	@RequestMapping(value="/match")
	@ResponseBody
	public String match(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Sysparam sysparam=new Sysparam();
		String oid = request.getParameter("oid");
		String paramorder = request.getParameter("paramorder");
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		Org org = getOrg(orgList, Long.valueOf(oid));
		sysparam.setParamcode("NTFWYXORG");
		sysparam.setParamkey(org.getOrgName());
		sysparam.setParamvalue(oid);
		sysparam.setParamstate(1l);
		sysparam.setParamorder(Long.valueOf(paramorder));
		sysparam.setParamdesc("服务意向组织");
		return this.sysparamService.insertSysparam(sysparam)+"";
	}
	
	/** 审核系统参数 */
	@RequestMapping(value="/audit")
	@ResponseBody
	public String audit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Sysparam sysparam=new Sysparam();
		BeanUtils.populate(sysparam, request.getParameterMap());
		int r = this.sysparamService.audit(sysparam);
		resetmemcache();
		return r+"";
	}
	
	/** 审核系统参数 */
	@RequestMapping(value="/delete")
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Sysparam sysparam=new Sysparam();
		BeanUtils.populate(sysparam, request.getParameterMap());
		this.sysparamService.delete(sysparam);
		resetmemcache();
		return new ModelAndView("redirect:showsysparam.action");
	}
	/** 删除系统参数 */
	@RequestMapping(value="/jndelete")
	public ModelAndView jndelete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Sysparam sysparam=new Sysparam();
		BeanUtils.populate(sysparam, request.getParameterMap());
		this.sysparamService.delete(sysparam);
		resetmemcache();
		return new ModelAndView("redirect:showjnsysparam.action");
	}
	
	/** 编辑系统参数 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Sysparam sysparam=new Sysparam();
		BeanUtils.populate(sysparam, request.getParameterMap());
		sysparam=this.sysparamService.edit(sysparam);
		return new ModelAndView("redirect:showsysparam.action");
	}
	
	
	
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
					orgTemp.setOrgContactor("ctrl:radio;checked:0;T:"+org.getOrgName()+";");
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
		for(int i=0;i<orgList.size();i++){
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
}

