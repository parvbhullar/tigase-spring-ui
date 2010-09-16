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

import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.backstage.members.service.PostulantService;
import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.backstage.org.service.OrgService;
import com.linkage.app.gqt.front.reg.service.MembersService;
import com.linkage.app.gqt.purview.entity.User;
import com.linkage.framework.cache.CacheKey;
import com.linkage.framework.page.CouponPageUtil;
import com.linkage.framework.util.ExcelUtil;


/**
 * 
 * 后台人员管理-志愿者管理
 *
 * @author jiale.wang
 *
 * @create on 2009-8-11 上午11:28:45
 *
 * @version v1.0
 *
 * @Copyright (c) 2009 by linkaged.
 *
 * @see
 */

@Controller
@RequestMapping("/manage/postulant")
public class PostulantController {
	final Logger logger = LoggerFactory.getLogger(PostulantController.class);
	
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

	/** 显示 */
	@RequestMapping(value="/showpostulant")
	public ModelAndView showpostulant(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mvc=new ModelAndView("/manage/members/postulant/showpostulant");
		String page = request.getParameter("page");
		page = page==null?"1":page;
		String s_username = request.getParameter("username1");
		String s_name = request.getParameter("name1");
		String credcode = request.getParameter("credcode");
		String state = request.getParameter("state");
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
		if(state!=null && !state.equals(""))
		{
			args.put("state", state);
			mvc.addObject("state", state);
		}
		args.put("page", Integer.valueOf(page));
		args.put("pagesize", pagesize);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		long currentOrgId = ((User)principal).getCurrentOrgId();
		if(this.orgService.isNanTongUser(currentOrgId)){
			
		}
		args.put("oid", currentOrgId);
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
		
		//List<Members> membersList=this.membersService.showMembers();
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		
		
		Org org=getOrg(orgList,currentOrgId);
		long parentOrgId=org.getParentOrgId();
		String currentOrgName=org.getOrgName();
		
		List<Org> subOrgList = new ArrayList<Org>();
		
		// 获取当前组织信息
		Org currentOrg = getOrg(orgList,currentOrgId);
		// 手动匹配 组织树（多选）
		subOrgList = getSubOrgList(orgList, subOrgList,currentOrg);
		// 根节点
		
		String province="0_";
		if(currentOrg.getOrgTypeId()==200)
		{
			province += currentOrg.getParentOrgId();
		}
		else
		{
			province += currentOrg.getOrgId();
		}
		
		HashMap hashMap=(HashMap)memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		ArrayList map1 = (ArrayList)memcachedClient.get(CacheKey.ZYZ_NT_FWYX);
		mvc.addObject("xl", hashMap.get("XL"));
		mvc.addObject("fwyx", map1);
		mvc.addObject("fwsj", hashMap.get("FWSJ"));
		mvc.addObject("zy", hashMap.get("ZY"));
		mvc.addObject("zjlx", hashMap.get("ZJLX"));
		mvc.addObject("ntfwyx", hashMap.get("NTFWYX"));
		mvc.addObject("zyzntfwyx", memcachedClient.get(CacheKey.ZYZ_NT_FWYX));
		mvc.addObject("jn", hashMap.get("JN"));
		mvc.addObject("mz", hashMap.get("MZ"));
		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));
		mvc.addObject("province",province);
		mvc.addObject("currentOrgName",currentOrgName);
		mvc.addObject("postulantList", this.postulantService.getPostulantList(args));
		mvc.addObject("province",province);		

		mvc.addObject("subOrgList", subOrgList);
		mvc.addObject("selOrgList", subOrgList);
		// 当前组织ID
		mvc.addObject("currentOrgId", currentOrgId);
		// 当前组织名称
		mvc.addObject("currentOrgName",currentOrg.getOrgName());
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(pagesize);
		pageUtil.setCurPage(Integer.valueOf(page));
		pageUtil.setTotalRow(this.postulantService.getPostulantTotal(args));
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		mvc.addObject("page",page);
		return mvc;
	}
	/** 显示 */
	@RequestMapping(value="/showpostulanthis")
	public ModelAndView showpostulanthis(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mvc=new ModelAndView("/manage/members/postulant/showpostulanthis");
		
		Map args = new HashMap();
		args.put("page", 1);
		args.put("pagesize", 100);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		long currentOrgId = ((User)principal).getCurrentOrgId();
		args.put("oid", currentOrgId);
		
		mvc.addObject("list",this.postulantService.getPostlantHisList(args));
		return mvc;
	}
	
	/*
	 * 增加操作-提交表单
	 */
	@RequestMapping(value="/savePostulant.action", method=RequestMethod.POST)
	public ModelAndView savePostulant(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Postulant postulant=new Postulant();
		BeanUtils.populate(postulant, request.getParameterMap());
		postulant.setRegip(request.getRemoteAddr());
		String[] jns = request.getParameterValues("jn1");
		
		//String[] fwyx = request.getParameterValues("intention");
		String jn="";
		if(jns!=null)
		{
			for (int i = 0; i < jns.length; i++) {
				jn += jns[i];
				if(i+1<jns.length)
					jn += ",";
			}
		}
//		if(intention!=null&&!intention.equals(""))
//		{
//			for (int i = 0; i < fwyx.length; i++) {
//				intention += fwyx[i];
//				if(i+1<fwyx.length)
//					intention += ",";
//			}
//		}
		postulant.setJn(jn);
//		postulant.setIntention(intention);
//		postulant.setServetimes(1);
		String bir = request.getParameter("birthday1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(bir!=null && !bir.equals("")){
			
			postulant.setBirthday(sdf.parse(bir));
		}
		
		this.postulantService.insertBack(postulant);
//		this.postulantService.insertFornt(postulant);
		
		return new ModelAndView("redirect:/manage/postulant/showpostulant.action");
	}
	
	/*
	 * 更新操作-修改表单
	 */
	@RequestMapping(value="/updatePostulant.action", method=RequestMethod.POST)
	public ModelAndView updatePostulant(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Postulant postulant=new Postulant();
		BeanUtils.populate(postulant, request.getParameterMap());
		
		String bir = request.getParameter("birthday1");
		if(bir!=null && !bir.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			postulant.setBirthday(sdf.parse(bir));
		}
		
		String[] jns = request.getParameterValues("jn1");
		
		//String[] fwyx = request.getParameterValues("intention");
		String jn="";
		if(jns!=null)
		{
			for (int i = 0; i < jns.length; i++) {
				jn += jns[i];
				if(i+1<jns.length)
					jn += ",";
			}
		}
		postulant.setJn(jn);
		this.postulantService.update(postulant);
		
		return new ModelAndView("redirect:/manage/postulant/showpostulant.action");
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
	 * 删除操作-根据ID单个删除
	 */
	@RequestMapping(value="/delete.action")
	public ModelAndView delete(@RequestParam("uid") long id,@RequestParam("page") String page) {
		this.postulantService.deleteById(id);
		ModelAndView mvc=new ModelAndView("redirect:/manage/postulant/showpostulant.action");
		mvc.addObject("page",page);
		return mvc;
	}
	/*
	 * 查看
	 */
	@RequestMapping(value="/view.action")
	public ModelAndView view(@RequestParam("uid") long id) {
		Postulant postulant = this.postulantService.selectById(id);
		ModelAndView mvc=new ModelAndView("/manage/members/postulant/view");
		HashMap hashMap=(HashMap)memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		mvc.addObject("xl", hashMap.get("XL"));
		mvc.addObject("fwyx", hashMap.get("FWYX"));
		mvc.addObject("fwsj", hashMap.get("FWSJ"));
		mvc.addObject("zy", hashMap.get("ZY"));
		mvc.addObject("zjlx", hashMap.get("ZJLX"));
		mvc.addObject("ntfwyx", hashMap.get("NTFWYX"));
		mvc.addObject("jn", hashMap.get("JN"));
		mvc.addObject("mz", hashMap.get("MZ"));
		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));

		mvc.addObject("postulant",postulant);
		mvc.addObject("jns",postulant.getJn()==null?"0":postulant.getJn().split(","));
		return mvc;
	}
	/*
	 * 修改
	 */
	@RequestMapping(value="/edit.action")
	public ModelAndView delete(@RequestParam("uid") long id) {
		Postulant postulant = this.postulantService.selectById(id);
		ModelAndView mvc=new ModelAndView("/manage/members/postulant/edit");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		long currentOrgId = ((User)principal).getCurrentOrgId();
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
		
		//List<Members> membersList=this.membersService.showMembers();
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
		ArrayList map1 = (ArrayList)memcachedClient.get(CacheKey.ZYZ_NT_FWYX);
		mvc.addObject("xl", hashMap.get("XL"));
		mvc.addObject("fwyx", hashMap.get("FWYX"));
		mvc.addObject("fwsj", hashMap.get("FWSJ"));
		mvc.addObject("zy", hashMap.get("ZY"));
		mvc.addObject("zjlx", hashMap.get("ZJLX"));
		mvc.addObject("ntfwyx", hashMap.get("NTFWYX"));
		mvc.addObject("jn", hashMap.get("JN"));
		mvc.addObject("mz", hashMap.get("MZ"));
		mvc.addObject("orgarea", memcachedClient.get(CacheKey.ZYZ_ORGAREA));
		mvc.addObject("province",province);		

		mvc.addObject("subOrgList", subOrgList);
		mvc.addObject("selOrgList", subOrgList);
		// 当前组织ID
		mvc.addObject("currentOrgId", currentOrgId);
		// 当前组织名称
		mvc.addObject("currentOrgName",currentOrg.getOrgName());
		mvc.addObject("postulant",postulant);
		mvc.addObject("jns",postulant.getJn()==null?"0":postulant.getJn().split(","));
		return mvc;
	}
	/*
	 * 修改
	 */
	@RequestMapping(value="/verify.action")
	public ModelAndView verify(@RequestParam("uid") long id,@RequestParam("verify") int vid,@RequestParam("page") int page) {
		this.postulantService.verify(id,vid);
		ModelAndView mvc=new ModelAndView("redirect:/manage/postulant/showpostulant.action");
		mvc.addObject("page",page);
		return mvc;
	}
	/*
	 * 更新状态
	 */
	@RequestMapping(value="/verify1.action")
	@ResponseBody
	public String verify1(@RequestParam("uid") long id,@RequestParam("verify") int vid) {
		int r = this.postulantService.verify1(id,vid);
		return r+"";
	}
	/*
	 * 注销
	 */
	@RequestMapping(value="/logoutagree.action")
	@ResponseBody
	public String logoutagree(HttpServletRequest request,HttpServletResponse response) {
		int r = this.postulantService.logout(Long.valueOf(request.getParameter("id")));
		
		return r+"";
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
			this.postulantService.batchDelete(_list);
		}
		return new ModelAndView("redirect:/manage/postulant/showpostulant.action");
	}
	/*
	 * 修改验证
	 */
	@RequestMapping(value="/validate", method=RequestMethod.POST)
	@ResponseBody
	public String validate(HttpServletRequest request,HttpServletResponse response,@RequestParam("dn") String dn,@RequestParam("credcode") String credcode){
		//logger.info(allId);
		int[] r = this.postulantService.validate(dn, credcode);
		if(r[0]==0&&r[1]==0){
			return "0";
		}
		else if(r[0]>0&&r[1]==0)
		{
			return "1";
		}
		else if(r[1]>0&&r[0]==0)
		{
			return "2";
		}else
		{
			return "3";
		}
		
	}
	/*
	 * 增加保存验证
	 */
	@RequestMapping(value="/validate1", method=RequestMethod.POST)
	@ResponseBody
	public String validate1(HttpServletRequest request,HttpServletResponse response,@RequestParam("dn") String dn,@RequestParam("credcode") String credcode){
		//logger.info(allId);
		int[] r = this.postulantService.validate(dn, credcode);
		if(r[0]==0&&r[1]==0){
			return "0";
		}
		else if(r[0]>0)
		{
			return "1";
		}
		else if(r[1]>0)
		{
			return "2";
		}else
		{
			return "3";
		}
		
	}
	/*
	 * 模板下载
	 */
	@RequestMapping(value="/downtemplate")
	public ModelAndView exptemplate(HttpServletRequest request,HttpServletResponse response) throws Exception {
		File f = new File(request.getSession().getServletContext().getRealPath("/manage/members/postulant/")+"/postulant.xls");
		ModelAndView mvc=new ModelAndView("/manage/members/postulant/exp");
		request.setAttribute("downloadfile", f);
		return mvc;
	}
	
	/*
	 * 导入志愿者
	 */
	@RequestMapping(value="/imppostulant", method=RequestMethod.POST)
	public ModelAndView imppostulant(HttpServletRequest request,HttpServletResponse response,@RequestParam("impfile") MultipartFile file) throws Exception {
		String communityid = request.getParameter("communityid");
		String communityname = request.getParameter("communityname");
		String intention = request.getParameter("intention");
		String orgid = request.getParameter("orgid");
		String orgname = request.getParameter("orgname");
		String[] jns = request.getParameterValues("jn2");
		
		//String[] fwyx = request.getParameterValues("intention");
		String jn="";
		if(jns!=null)
		{
			for (int i = 0; i < jns.length; i++) {
				jn += jns[i];
				if(i+1<jns.length)
					jn += ",";
			}
		}
		
		List errList = new ArrayList();
		int success = 0;
		int[] res=new int[]{};
		if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            if(bytes!=null&&bytes.length>0){
            	String tempFileName=file.getOriginalFilename();
            	long fileNameWithotExtend=System.currentTimeMillis();
            	String fileExtend=tempFileName.substring(tempFileName.lastIndexOf("."));
            	String fileName=fileNameWithotExtend+fileExtend;
            	String fileDir=request.getSession().getServletContext().getRealPath("/upload/tempfile/importppostulant/");
            	//String filePath=request.getSession().getServletContext().getRealPath("/upload/webpreferential/coupons/")+"/"+fileName;
            	createFileDir(fileDir);
            	String filePath=fileDir+"/"+fileName;
            	String logoPath="/upload/webpreferential/coupons"+"/"+fileName;
            	
            	FileOutputStream fos = new FileOutputStream(filePath);
            	fos.write(bytes);
            	fos.close();
            	List<Object[]> list = ExcelUtil.readExcel(filePath);
            	if(null!=list&&list.size()>0)
            	{
            		for (int i = 0; i < list.size(); i++) {
						int r = this.postulantService.importData(list.get(i), Long.valueOf(communityid), intention, Long.valueOf(orgid), orgname, communityname,jn);
						if(r>0)
						{
							success++;
						}
						else
						{
							Postulant p1 = new Postulant();
							p1.setDn((String)list.get(i)[3]);
							p1.setCredcode((String)list.get(i)[9]);
							errList.add(p1);
						}
					}
            	}
            	//批量导入
            	//res = this.postulantService.batchinsert(list,Long.valueOf(communityid),communityname,Long.valueOf(orgid),orgname,intention);
            	//coupon.setPic(logoPath);
            }
		}
		ModelAndView mvc=new ModelAndView("/manage/members/postulant/success");
		mvc.addObject("success",success);
		mvc.addObject("errList",errList);
		return mvc;
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
	
	private List<Org> getSubOrgList(List<Org> list,List<Org> newList,Org currOrg){
		Org orgTemp;
		if(list==null||list.size()==0){
			return new ArrayList<Org>();
		}else if(newList==null){
			newList=new ArrayList<Org>();
		}else{
			for(Org org:list){
				if(org.getOrgId()==currOrg.getOrgId()){
					orgTemp=new Org();
					orgTemp.setOrgName(org.getParentOrgId()+"_"+org.getOrgId());
					orgTemp.setOrgContactor(org.getOrgName()+";");
					orgTemp.setOrgTypeId(org.getOrgTypeId());
					newList.add(orgTemp);
				}
				if(currOrg.getOrgTypeId()==200){
					if(org.getParentOrgId()==currOrg.getParentOrgId()){
						getSubOrgList(list,newList,org.getOrgId());
					}
				}else
				{
					if(org.getParentOrgId()==currOrg.getOrgId()){
						getSubOrgList(list,newList,org.getOrgId());
					}
				}
			}
		}
		return newList;
	}
	
}
