package com.linkage.app.gqt.backstage.members.controller;

import java.io.File;
import java.io.FileOutputStream;
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

import com.linkage.app.gqt.backstage.active.entity.Active;
import com.linkage.app.gqt.backstage.members.entity.Assist;
import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.backstage.members.service.AssistService;
import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.front.reg.entitys.Members;
import com.linkage.app.gqt.front.reg.service.MembersService;
import com.linkage.app.gqt.purview.entity.User;
import com.linkage.framework.cache.CacheKey;
import com.linkage.framework.page.CouponPageUtil;
import com.linkage.framework.util.ExcelUtil;



/**
 * 后台人员管理-帮扶对象管理
 *
 *
 * @author jiale.wang
 *
 * @create on 2009-8-11 上午11:31:19
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */

@Controller
@RequestMapping("/manage/assist")
public class AssistController {
	final Logger logger = LoggerFactory.getLogger(AssistController.class);
	
	private int pagesize = 10;
	
	private AssistService assistService;
	private MembersService membersService;
	private MemcachedClient memcachedClient;
	
	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	
	@Autowired
	public void setMembersService(MembersService membersService) {
		this.membersService = membersService;
	}
	
	@Autowired
	public void setAssistService(AssistService assistService) {
		this.assistService = assistService;
	}


	/** 显示 */
	@RequestMapping(value="/showassist")
	public ModelAndView showactive(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mvc=new ModelAndView("/manage/members/assist/showassist");
		String page = request.getParameter("page");
		page = page==null?"1":page;
		String s_username = request.getParameter("username1");
		String s_name = request.getParameter("name1");
		String credcode = request.getParameter("credcode");
		
		Map args = new HashMap();
		args.put("page", page);
		args.put("pagesize", pagesize);
		if(s_username!=null && !s_username.equals(""))
		{
			args.put("s_username", s_username);
			mvc.addObject("s_username", s_username);
		}
		if(s_name!=null && !s_name.equals(""))
		{
			args.put("s_name", s_name);
			mvc.addObject("s_name", s_name);
		}
		if(credcode!=null && !credcode.equals(""))
		{
			args.put("credcode", credcode);
			mvc.addObject("credcode", credcode);
		}
		args.put("page", Integer.valueOf(page));
		args.put("pagesize", pagesize);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		long currentOrgId = ((User)principal).getCurrentOrgId();
		args.put("oid", currentOrgId);
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
		
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		
		Org org=getOrg(orgList,currentOrgId);
		long parentOrgId=org.getParentOrgId();
		String currentOrgName=org.getOrgName();
		
		List<Org> subOrgList = new ArrayList<Org>();
		
		// 获取当前组织信息
		Org currentOrg = getOrg(orgList,currentOrgId);
		// 手动匹配 组织树（多选）
		subOrgList = getSubOrgList(orgList, subOrgList, currentOrgId);
		// 根节点
		String province="0_"+currentOrgId;
		
		this.assistService.getAssistList(args);
		
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(pagesize);
		pageUtil.setCurPage(Integer.valueOf(page));
		pageUtil.setTotalRow(this.assistService.getAssistTotal(args));
		mvc.addObject("assistList", this.assistService.getAssistList(args));
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		mvc.addObject("page",page);
		
		HashMap hashMap=(HashMap)memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		mvc.addObject("xl", hashMap.get("XL"));
		mvc.addObject("fwyx", hashMap.get("FWYX"));
		mvc.addObject("fwsj", hashMap.get("FWSJ"));
		mvc.addObject("zy", hashMap.get("ZY"));
		mvc.addObject("zjlx", hashMap.get("ZJLX"));
		mvc.addObject("ntfwyx", hashMap.get("NTFWYX"));
		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));
		mvc.addObject("province",province);
		mvc.addObject("currentOrgName",currentOrgName);
		//mvc.addObject("postulantList", this.postulantService.getPostulantList(args));
		mvc.addObject("province",province);		

		mvc.addObject("subOrgList", subOrgList);
		mvc.addObject("selOrgList", subOrgList);
		// 当前组织ID
		mvc.addObject("currentOrgId", currentOrgId);
		// 当前组织名称
		mvc.addObject("currentOrgName",currentOrg.getOrgName());
		//mvc.addObject("treeListM", treeListM);
		mvc.addObject("province",province);
		mvc.addObject("currentOrgName",currentOrgName);
		
		return mvc;
	}
	
	/*
	 * 导入志愿者
	 */
	@RequestMapping(value="/impassist", method=RequestMethod.POST)
	public ModelAndView imppostulant(HttpServletRequest request,HttpServletResponse response,@RequestParam("impfile") MultipartFile file) throws Exception {
		String volunorgid = request.getParameter("volunorgid");
		String volunorgname = request.getParameter("volunorgname");
		int[] res=new int[]{};
		if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            if(bytes!=null&&bytes.length>0){
            	String tempFileName=file.getOriginalFilename();
            	long fileNameWithotExtend=System.currentTimeMillis();
            	String fileExtend=tempFileName.substring(tempFileName.lastIndexOf("."));
            	String fileName=fileNameWithotExtend+fileExtend;
            	String fileDir=request.getSession().getServletContext().getRealPath("/upload/tempfile/importassist/");
            	//String filePath=request.getSession().getServletContext().getRealPath("/upload/webpreferential/coupons/")+"/"+fileName;
            	createFileDir(fileDir);
            	String filePath=fileDir+"/"+fileName;
            	//String logoPath="/upload/webpreferential/coupons"+"/"+fileName;
            	
            	FileOutputStream fos = new FileOutputStream(filePath);
            	fos.write(bytes);
            	fos.close();
            	List<Object[]> list = ExcelUtil.readExcel(filePath);
            	res = this.assistService.batchinsert(list,Long.valueOf(volunorgid),volunorgname);
            	//coupon.setPic(logoPath);
            }
		}
		ModelAndView mvc=new ModelAndView("/manage/members/assist/success");
		mvc.addObject("msg","共导入成功"+res.length+"条数据");
		return mvc;
	}
	
	/*
	 * 模板下载
	 */
	@RequestMapping(value="/downtemplate")
	public ModelAndView exptemplate(HttpServletRequest request,HttpServletResponse response) throws Exception {
		File f = new File(request.getSession().getServletContext().getRealPath("/manage/members/assist/")+"/assist.xls");
		ModelAndView mvc=new ModelAndView("/manage/members/assist/exp");
		request.setAttribute("downloadfile", f);
		return mvc;
	}
	
	
	/*
	 * 辅助方法
	 * 递归创建文件夹
	 */
	private void createFileDir(String path)throws Exception{
		File dirFile = new File(path);
		boolean bFile = dirFile.exists();
		if( bFile == true ){
			logger.info("文件路径[{}]存在!",path);
		} else {
			logger.info("文件路径[{}]不存在,需要创建!",path);
			bFile = dirFile.mkdirs();
			logger.info("文件路径[{}]创建成功!",path);
		}
	}
	
	/*
	 * 增加操作-提交表单
	 */
	@RequestMapping(value="/saveAssist.action", method=RequestMethod.POST)
	public ModelAndView saveAssist(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Assist assist=new Assist();
		BeanUtils.populate(assist, request.getParameterMap());
		
		String bir = request.getParameter("birthday1");
		if(bir!=null&&!bir.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			assist.setBirthday(sdf.parse(bir));
		}
		else
		{
			assist.setBirthday(null);
		}
		this.assistService.insert(assist);
		
		return new ModelAndView("redirect:/manage/assist/showassist.action");
	}
	
	/*
	 * 更新操作-修改表单
	 */
	@RequestMapping(value="/updateAssist.action", method=RequestMethod.POST)
	public ModelAndView updateAssist(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Assist assist=new Assist();
		BeanUtils.populate(assist, request.getParameterMap());
		
		String bir = request.getParameter("birthday1");
		if(bir!=null&&!bir.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			assist.setBirthday(sdf.parse(bir));
		}
		else
		{
			assist.setBirthday(null);
		}
		this.assistService.updateAssist(assist);
		
		return new ModelAndView("redirect:/manage/assist/showassist.action");
	}
	
	/*
	 * 删除操作-根据ID单个删除
	 */
	@RequestMapping(value="/delete.action")
	public ModelAndView delete(@RequestParam("aid") long id,@RequestParam("page") String page) {
		int i = this.assistService.deleteById(id);
		ModelAndView mvc=new ModelAndView("redirect:/manage/assist/showassist.action");
		mvc.addObject("page",page);
		return mvc;
	}
	/*
	 * 查看
	 */
	@RequestMapping(value="/view.action")
	public ModelAndView view(@RequestParam("aid") long id) {
		Assist assist = this.assistService.selectById(id);
		ModelAndView mvc=new ModelAndView("/manage/members/assist/view");
		mvc.addObject("assist",assist);
		return mvc;
	}
	/*
	 * 修改
	 */
	@RequestMapping(value="/edit.action")
	public ModelAndView edit(@RequestParam("aid") long id) {
		Assist assist = this.assistService.selectById(id);
		ModelAndView mvc=new ModelAndView("/manage/members/assist/edit");
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		long currentOrgId = ((User)principal).getCurrentOrgId();
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
		
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		
		Org org=getOrg(orgList,currentOrgId);
		long parentOrgId=org.getParentOrgId();
		String currentOrgName=org.getOrgName();
		
		List<Org> subOrgList = new ArrayList<Org>();
		
		// 获取当前组织信息
		Org currentOrg = getOrg(orgList,currentOrgId);
		// 手动匹配 组织树（多选）
		subOrgList = getSubOrgList(orgList, subOrgList, currentOrgId);
		// 根节点
		String province="0_"+currentOrgId;
		HashMap hashMap=(HashMap)memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		mvc.addObject("xl", hashMap.get("XL"));
		mvc.addObject("fwyx", hashMap.get("FWYX"));
		mvc.addObject("fwsj", hashMap.get("FWSJ"));
		mvc.addObject("zy", hashMap.get("ZY"));
		mvc.addObject("zjlx", hashMap.get("ZJLX"));
		mvc.addObject("ntfwyx", hashMap.get("NTFWYX"));
		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));
		mvc.addObject("province",province);
		mvc.addObject("currentOrgName",currentOrgName);
		//mvc.addObject("postulantList", this.postulantService.getPostulantList(args));
		mvc.addObject("province",province);		

		mvc.addObject("subOrgList", subOrgList);
		mvc.addObject("selOrgList", subOrgList);
		// 当前组织ID
		mvc.addObject("currentOrgId", currentOrgId);
		// 当前组织名称
		mvc.addObject("currentOrgName",currentOrg.getOrgName());
		//mvc.addObject("treeListM", treeListM);
		mvc.addObject("province",province);
		mvc.addObject("currentOrgName",currentOrgName);
		mvc.addObject("assist",assist);
		return mvc;
	}
	/*
	 * 修改
	 */
	@RequestMapping(value="/verify.action")
	public ModelAndView verify(@RequestParam("aid") long id,@RequestParam("verify") int vid,@RequestParam("page") int page) {
		this.assistService.verify(id,vid);
		ModelAndView mvc=new ModelAndView("redirect:/manage/assist/showassist.action");
		mvc.addObject("page",page);
		return mvc;
	}
	/*
	 * 删除操作-批量删除
	 */
	@RequestMapping(value="/dels.action")
	public ModelAndView batchDelete(HttpServletRequest request,HttpServletResponse response,@RequestParam("allId") String allId){
		//logger.info(allId);
		if(allId!=null&&allId.length()>0){
			String[] items=allId.split(",");
			List<String> list=Arrays.asList(items);
			List<Long> _list=new ArrayList<Long>();
			for(String s : list){
				_list.add(Long.valueOf(s));
			}
			this.assistService.batchDelete(_list);
		}
		return new ModelAndView("redirect:/manage/assist/showassist.action");
	}
	
	/** 改变状态 */
	@RequestMapping(value = "/changeState.action")
	@ResponseBody
	public String changeState(HttpServletRequest request,HttpServletResponse response) {
		
		//int i=this.activeService.changeState(new Integer(status).intValue(), new Integer(actid).intValue());
		return "1";
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
}
