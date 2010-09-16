package com.linkage.app.gqt.backstage.org.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.backstage.org.service.OrgService;
import com.linkage.app.gqt.init.entity.OrgType;
import com.linkage.app.gqt.init.service.InitService;
import com.linkage.app.gqt.purview.entity.User;
import com.linkage.framework.cache.CacheKey;

@Controller
@RequestMapping("/manage/org")
public class OrgController {
	final Logger logger = LoggerFactory.getLogger(OrgController.class);
	private OrgService orgService;
	private MemcachedClient memcachedClient;
	private InitService initService;
	
	@Autowired
	public OrgController(OrgService orgService,MemcachedClient memcachedClient,InitService initService){
		this.orgService=orgService;
		this.memcachedClient=memcachedClient;
		this.initService=initService;
	}
	
	/*
	 * 组织类型操作
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/orgtype.action")
	public ModelAndView orgtype(){
		List<OrgType> orgTypeList=(List<OrgType>)memcachedClient.get(CacheKey.ZYZ_ORGTYPE);
		if(orgTypeList==null){
			initService.initOrgType();
			orgTypeList=(List<OrgType>)memcachedClient.get(CacheKey.ZYZ_ORGTYPE);
		}
//		List<OrgType> _orgTypeList=new ArrayList<OrgType>();
//		for(OrgType orgType:orgTypeList){
//			if(orgType.getOrgTypeState()==1){
//				_orgTypeList.add(orgType);
//			}
//		}
		ModelAndView mvc=new ModelAndView("/manage/org/orgtype");
		mvc.addObject("orgTypeList", orgTypeList);
		return mvc;
	}
	
	@RequestMapping(value="/addtoporgtype.action")
	public void addTopOrgType(@RequestParam("orgTypeName") String orgTypeName,@RequestParam("orgTypeCategory") int orgTypeCategory,HttpServletResponse response)throws Exception{
		int i=this.orgService.addTopOrgType(orgTypeName,orgTypeCategory);
		if(i==1){
			memcachedClient.delete(CacheKey.ZYZ_ORGTYPE);
			logger.info("增加名称={}的顶级组织类型成功!",orgTypeName);
			response.getWriter().write("success");
		}else{
			logger.info("增加名称={}的顶级组织类型失败!",orgTypeName);
			response.getWriter().write("faile");
		}
	}
	
	@RequestMapping(value="/addorgtype.action")
	public void addOrgType(@RequestParam("orgTypeName") String orgTypeName,@RequestParam("orgTypeParentId") long orgTypeParentId,@RequestParam("orgTypeCategory") int orgTypeCategory,HttpServletResponse response)throws Exception{
		int i=this.orgService.addOrgType(orgTypeName, orgTypeParentId,orgTypeCategory);
		if(i==1){
			memcachedClient.delete(CacheKey.ZYZ_ORGTYPE);
			logger.info("增加名称={},父ID={}的组织类型成功!",orgTypeName,orgTypeParentId);
			response.getWriter().write("success");
		}else{
			logger.info("增加名称={},父ID={}的组织类型失败!",orgTypeName,orgTypeParentId);
			response.getWriter().write("faile");
		}
	}
	
	/*
	 * 删除时要注意两点1、是否要级联删除下级数据 2、该值被组织机构数据引用时不能删除
	 */
	@RequestMapping(value="/{id}/delorgtype.action")
	public ModelAndView delOrgType(@PathVariable("id") long orgTypeId){
		int i=this.orgService.delOrgTypeByOrgTypeId(orgTypeId);
		if(i==1){
			memcachedClient.delete(CacheKey.ZYZ_ORGTYPE);
			logger.info("删除ID={}的组织类型成功!",orgTypeId);
		}else{
			logger.info("删除ID={}的组织类型失败!",orgTypeId);
		}
		return new ModelAndView("redirect:/manage/org/orgtype.action");
	}
	
	@RequestMapping(value="/{orgTypeId}/{orgTypeState}/changeorgtype.action")
	public ModelAndView updateOrgTypeWithOrgTypeStateByOrgTypeId(@PathVariable("orgTypeId") long orgTypeId,@PathVariable("orgTypeState") int orgTypeState){
		int i=this.orgService.editOrgTypeWithOrgTypeStateByOrgTypeId(orgTypeId, orgTypeState);
		if(i==1){
			memcachedClient.delete(CacheKey.ZYZ_ORGTYPE);
			logger.info("修改ID={}的组织类型的状态为:{}成功!",orgTypeId,orgTypeState==0?"失效":"生效");
		}else{
			logger.info("修改ID={}的组织类型的状态为:{}失败!",orgTypeId,orgTypeState==0?"失效":"生效");
		}
		return new ModelAndView("redirect:/manage/org/orgtype.action");
	}
	
	@RequestMapping(value="/editorgtype.action")
	public void editOrgTypeWithOrgTypeNameByOrgTypeId(@RequestParam("orgTypeId") long orgTypeId,@RequestParam("orgTypeName") String orgTypeName,HttpServletResponse response)throws Exception{
		int i=this.orgService.editOrgTypeWithOrgTypeNameByOrgTypeId(orgTypeId, orgTypeName);
		if(i==1){
			memcachedClient.delete(CacheKey.ZYZ_ORGTYPE);
			logger.info("修改ID={}的组织类型的名称为:{}成功!",orgTypeId,orgTypeName);
			response.getWriter().write("success");
		}else{
			logger.info("修改ID={}的组织类型的名称为:{}失败!",orgTypeId,orgTypeName);
			response.getWriter().write("faile");
		}
	}
	
//	private List<Org> getSubOrgList(List<Org> list,long orgId){
//		if(list==null||list.size()==0){
//			return new ArrayList<Org>();
//		}else{
//			for(Org org:list){
//				if(org.getOrgId()==orgId){
//					int index=list.indexOf(org);
//					return list.subList(index, list.size());
//				}
//			}
//		}
//		return new ArrayList<Org>();
//	}
	
	/*
	 * 组织机构操作
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/org.action")
	public ModelAndView org(){
		User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(this.orgService.isNanTongUser(principal.getOrgId())){
			logger.info("南通的");
		}else{
			logger.info("不是南通的");
		}
		
		long currentOrgId=principal.getCurrentOrgId();
		List<Org> orgList=(List<Org>)memcachedClient.get(CacheKey.ZYZ_ORG);
		//List<Org> orgManageList=(List<Org>)memcachedClient.get(CacheKey.ZYZ_MANAGE_ORG);
		//if(orgList==null||orgManageList==null){
		if(orgList==null){
			initService.initOrg();
			orgList=(List<Org>)memcachedClient.get(CacheKey.ZYZ_ORG);
			//orgManageList=(List<Org>)memcachedClient.get(CacheKey.ZYZ_MANAGE_ORG);
		}
		if(currentOrgId==0||currentOrgId==320000){
			
		}else{
			long a=System.currentTimeMillis();
			orgList=orgService.getSubOrgList(orgList,new ArrayList<Org>(),currentOrgId);
			logger.info("迭代方法getSubOrgList耗时：{}秒",(System.currentTimeMillis()-a));
		}
		ModelAndView mvc=new ModelAndView("/manage/org/org");
		//List<OrgArea> orgAreaList=(List<OrgArea>)memcachedClient.get(CacheKey.ZYZ_ORGAREA);
		mvc.addObject("orgList", orgList);
		//mvc.addObject("orgList", orgManageList);
//		StringBuffer sb=new StringBuffer("[");
//		int count=0;
//		for(OrgArea orgArea:orgAreaList){
//			if(orgArea.getLevel()==count && orgArea.getAreaState()==1){
//				sb.append("},");
//				sb.append("{ name:\""+orgArea.getAreaName()+ "\", id:\""+orgArea.getAreaId()+"\",open:true");
//			}else if(orgArea.getLevel()>count && orgArea.getAreaState()==1){
//				if(count==0){
//					sb.append("{ name:\""+orgArea.getAreaName()+"\", id:\""+orgArea.getAreaId()+"\",open:true");
//				}else{
//					sb.append(",");
//					sb.append("nodes:[{name:\""+orgArea.getAreaName()+"\", id:\""+orgArea.getAreaId()+"\",open:true");
//				}
//			}else if(orgArea.getAreaState()==1){
//				for(int i =0; i < count - orgArea.getLevel(); i++){
//					sb.append("}]},");
//				}
//				sb.append("{ name:\""+orgArea.getAreaName()+"\", id:\""+orgArea.getAreaId()+"\",open:true");
//			}
//			count=orgArea.getLevel();
//		}
//		sb.append("}]");
//		sb.append("}]");
//		mvc.addObject("treeString", sb.toString());
//		logger.info(sb.toString());
		return mvc;
	}
	
	@RequestMapping(value="/addorg.action")
	public void addOrg(HttpServletRequest request,HttpServletResponse response)throws Exception{
		//List<OrgType> orgTypeList=(List<OrgType>)memcachedClient.get(CacheKey.ZYZ_ORGTYPE);
		//List<OrgArea> orgAreaList=(List<OrgArea>)memcachedClient.get(CacheKey.ZYZ_ORGAREA);
		Org org=new Org();
		BeanUtils.populate(org, request.getParameterMap());
		String i=this.orgService.addOrg(org);
		if(i!=null&&i.length()>1){
			memcachedClient.delete(CacheKey.ZYZ_ORG);
			//memcachedClient.delete(CacheKey.ZYZ_MANAGE_ORG);
			logger.info("增加名称={},父ID={}的组织机构成功!",org.getOrgName(),org.getParentOrgId());
			response.getWriter().write("success@"+i);
		}else{
			logger.info("增加名称={},父ID={}的组织类型失败!",org.getOrgName(),org.getParentOrgId());
			response.getWriter().write("faile");
		}
	}
	
	/*
	 * 删除时要注意两点1、是否要级联删除下级数据 2、该值被组织机构数据引用时不能删除
	 */
	@RequestMapping(value="/{id}/delorg.action")
	public ModelAndView delOrg(@PathVariable("id") long orgId){
		int i=this.orgService.delOrgByOrgId(orgId);
		if(i==1){
			memcachedClient.delete(CacheKey.ZYZ_ORG);
			//memcachedClient.delete(CacheKey.ZYZ_MANAGE_ORG);
			logger.info("删除ID={}的组织机构成功!",orgId);
		}else{
			logger.info("删除ID={}的组织机构失败!",orgId);
		}
		return new ModelAndView("redirect:/manage/org/org.action");
	}
	
	@RequestMapping(value="/{orgId}/{orgState}/changeorg.action")
	public ModelAndView editOrgWithOrgStateByOrgId(@PathVariable("orgId") long orgId,@PathVariable("orgState") int orgState){
		int i=this.orgService.editOrgWithOrgStateByOrgId(orgId, orgState);
		if(i==1){
			memcachedClient.delete(CacheKey.ZYZ_ORG);
			//memcachedClient.delete(CacheKey.ZYZ_MANAGE_ORG);
			logger.info("修改ID={}的组织机构的状态为:{}成功!",orgId,orgState==0?"失效":"生效");
		}else{
			logger.info("修改ID={}的组织机构的状态为:{}失败!",orgId,orgState==0?"失效":"生效");
		}
		return new ModelAndView("redirect:/manage/org/org.action");
	}
	
	@RequestMapping(value="/editorg.action")
	public void editOrgByOrgId(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Org org=new Org();
		BeanUtils.populate(org, request.getParameterMap());
		int i=this.orgService.editOrgByOrgId(org);
		if(i==1){
			memcachedClient.delete(CacheKey.ZYZ_ORG);
			//memcachedClient.delete(CacheKey.ZYZ_MANAGE_ORG);
			logger.info("修改ID={},名称={}的组织机构成功!",org.getOrgId(),org.getOrgName());
			response.getWriter().write("success");
		}else{
			logger.info("修改ID={},名称={}的组织机构失败!",org.getOrgId(),org.getOrgName());
			response.getWriter().write("faile");
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getTopOrgType.action")
	public void getTopOrgType (HttpServletRequest request,HttpServletResponse response)throws Exception{
		List<OrgType> orgTypeList=(List<OrgType>)memcachedClient.get(CacheKey.ZYZ_ORGTYPE);
		StringBuffer sb=new StringBuffer("[");
		int count=0;
		for(OrgType orgType:orgTypeList){
			if(orgType.getLevel()==1 && orgType.getOrgTypeState()==1){
				if(count>0){sb.append(",");}
				sb.append("{\"orgTypeId\":\""+orgType.getOrgTypeId()+"\",\"orgTypeName\":\""+orgType.getOrgTypeName()+"\"}");
				count++;
			}
		}
		sb.append("]");
		logger.info(sb.toString());
		response.getWriter().write(sb.toString());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getSubOrgTypeByOrgTypeId.action")
	public void getSubOrgTypeByOrgTypeId (@RequestParam("orgTypeId") long orgTypeId,HttpServletRequest request,HttpServletResponse response)throws Exception{
		List<OrgType> orgTypeList=(List<OrgType>)memcachedClient.get(CacheKey.ZYZ_ORGTYPE);
		StringBuffer sb=new StringBuffer("[");
		int count=0;
		for(OrgType orgType:orgTypeList){
			if(orgType.getOrgTypeParentId()==orgTypeId&& orgType.getOrgTypeState()==1){
				if(count>0){sb.append(",");}
				sb.append("{\"orgTypeId\":\""+orgType.getOrgTypeId()+"\",\"orgTypeName\":\""+orgType.getOrgTypeName()+"\"}");
				count++;
			}
		}
		sb.append("]");
		logger.info(sb.toString());
		response.getWriter().write(sb.toString());
	}
}
