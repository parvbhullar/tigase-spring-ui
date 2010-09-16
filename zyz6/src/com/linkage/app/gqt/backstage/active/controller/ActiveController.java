package com.linkage.app.gqt.backstage.active.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.linkage.app.gqt.backstage.active.entity.Active;
import com.linkage.app.gqt.backstage.active.entity.IssueWorkSheet;
import com.linkage.app.gqt.backstage.active.entity.TiMt;
import com.linkage.app.gqt.backstage.active.entity.TiMthis;
import com.linkage.app.gqt.backstage.active.service.ActiveService;
import com.linkage.app.gqt.backstage.members.entity.Postulant;
import com.linkage.app.gqt.backstage.org.entitys.Org;
import com.linkage.app.gqt.backstage.org.service.OrgService;
import com.linkage.app.gqt.init.entity.SysParam;
import com.linkage.app.gqt.purview.entity.User;
import com.linkage.framework.Constants;
import com.linkage.framework.cache.CacheKey;
import com.linkage.framework.page.CouponPageUtil;
import com.linkage.framework.util.ExcelUtil;
/**
 * 功能描述: 活动的增、删、改、查
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/manage/active")
public class ActiveController {
	final Logger logger = LoggerFactory.getLogger(ActiveController.class);
	
	private ActiveService activeService;
	private OrgService orgService;
	private MemcachedClient memcachedClient;

	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	@Autowired
	public void setActiveService(ActiveService activeService) {
		this.activeService = activeService;
	}
	
	@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	@Autowired
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}
	/** 活动列表展示 */
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
		// 获取登录用户信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 当前的组织ID
		long currentOrgId = ((User)principal).getCurrentOrgId();
		if(selectOrgId == -1){
			selectOrgId = currentOrgId;
		}
		// 查询活动总数
		int total = this.activeService.countActive(selectOrgId);
		// 生成分页工具栏
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(page);
		pageUtil.setTotalRow(total);
		page = page > pageUtil.getTotalPage()? 1:page;
		
		int start = (page - 1) * size;
		int end = start + size;
		// 查询当前页活动
		List<Active> activeList=this.activeService.showActive(selectOrgId, start, end);
		// 从缓存中获取组织列表
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		// 获取当前组织信息
		currentOrg = getOrg(orgList,currentOrgId);
		// 选择组织 信息
		Org selectOrg = getOrg(orgList,selectOrgId);
		// 手动匹配 组织树（多选）
		subOrgList = getSubOrgList(orgList, subOrgList, currentOrgId);
		// 选择组织 组织树（单选）
		selOrgList = getSubOrgList(orgList, selOrgList, currentOrgId);
		// 根节点
		province="0_"+currentOrgId;
		
		ModelAndView mvc=new ModelAndView("/manage/active/showactive");
		mvc.addObject("province",province);		
		mvc.addObject("activeList", activeList);
		mvc.addObject("subOrgList", subOrgList);
		mvc.addObject("selOrgList", selOrgList);
		// 当前组织ID
		mvc.addObject("currentOrgId", currentOrgId);
		// 当前组织名称
		mvc.addObject("currentOrgName",currentOrg.getOrgName());
		// 选择的组织ID
		mvc.addObject("selectOrgId", selectOrgId);
		// 选择的组织名称
		mvc.addObject("selectOrgName", selectOrg.getOrgName());
		// 分页工具栏
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		return mvc;
	}
	
	/** 跳转到发布活动画面 */
	@RequestMapping(value="/toIssueActive")
	public ModelAndView toIssueActive() throws Exception {
		// 获取登录用户的组织信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 当前的组织ID
		long currentOrgId = ((User)principal).getCurrentOrgId();
		// 获取当前组织机构类型
		Org org = this.orgService.getOrgType(currentOrgId);
		List<SysParam> fwyxList = null;
		if(this.orgService.isNanTongUser(currentOrgId)){
			// 南通
			if(null != org){
				if(100 == org.getOrgTypeId()){
					fwyxList = (List<SysParam>)this.activeService.findIntention(320600, org.getOrgTypeId());
				}else if(200 == org.getOrgTypeId()){
					fwyxList = (List<SysParam>)this.activeService.findIntention(currentOrgId, org.getOrgTypeId());
				}
				int fwyxsize = 0;
				if(null != fwyxList){
					fwyxsize = fwyxList.size();
				}
				SysParam fwyx = null;
				for(int i = 0; i < fwyxsize; i++){
					fwyx = fwyxList.get(i);
					fwyx.setParamKey(fwyx.getParamKey() + "(" + fwyx.getParamDesc() + ")");
				}
			}
		}else if(currentOrgId == 320000){
			// 江苏省
			fwyxList = (List<SysParam>)this.activeService.findIntention(currentOrgId, org.getOrgTypeId());
		}else{
			// 江苏省其他的12个地级市
			Org temp = this.orgService.getAreaIdByOrgId(currentOrgId);
			fwyxList = (List<SysParam>)this.activeService.findIntention(temp.getOrgId(), org.getOrgTypeId());
		}				
		ModelAndView mvc=new ModelAndView("/manage/active/issueActive");
		mvc.addObject("fwyxList", fwyxList);
		return mvc;
	}
	
	/** 保存发布活动 */
	@RequestMapping(value="/saveIssueActive")
	public ModelAndView saveIssueActive(HttpServletRequest request,HttpServletResponse response,@RequestParam("enclosure") MultipartFile file) throws Exception {
		Active active = new Active();
		// 获取表单数据
		BeanUtils.populate(active, request.getParameterMap());
		// 处理上传附件
		if (!file.isEmpty()) {
			long fileNameWithotExtend=System.currentTimeMillis();
            byte[] bytes = file.getBytes();
            if(bytes!=null&&bytes.length>0){
            	String tempFileName=file.getOriginalFilename();
            	String fileExtend=tempFileName.substring(tempFileName.lastIndexOf("."));
            	String fileName=fileNameWithotExtend+"_path"+fileExtend;
            	String fileDir=request.getSession().getServletContext().getRealPath("/upload/active/");
            	createFileDir(fileDir);
            	String filePath=fileDir+"/"+fileName;
            	String enclosurePath="/upload/active/"+fileName;
            	FileOutputStream fos = new FileOutputStream(filePath);
            	fos.write(bytes);
            	fos.close();
            	active.setPath(enclosurePath);
            }
		}
		// 获取当前组织信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 组织ID
		long orgId = ((User)principal).getCurrentOrgId();
		// 从缓存中获取组织列表
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		Org org = getOrg(orgList,orgId);
		// 组织名称
		String orgName = org.getOrgName();
		active.setOrgid(orgId);
		active.setOrgname(orgName);
		// 保存发布的活动信息
		int updateNum = this.activeService.insertActive(active);
		ModelAndView mav=new ModelAndView("/manage/active/result");
		// 返回结果 result 1:成功 0:失败
		mav.addObject("result", updateNum);
		return mav;
	}
	
	/** 跳转到修改发布活动画面 */
	@RequestMapping(value="/toEditActive")
	public ModelAndView toEditActive(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 活动ID
		long actId = Long.parseLong(request.getParameter("actId"));
		Active active = this.activeService.findActiveById(actId);
		// 查询该活动
		ModelAndView mav=new ModelAndView("/manage/active/editActive");
		mav.addObject(active);
		return mav;
	}
	
	/** 跳转到活动详情画面 */
	@RequestMapping(value="/toActiveDetail")
	public ModelAndView toActiveDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 活动ID
		long actId = Long.parseLong(request.getParameter("actId"));
		Active active = this.activeService.findActiveById(actId);
		// 服务意向
		String intention = active.getIntention();
		// 获取登录用户的组织信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 当前的组织ID
		long currentOrgId = ((User)principal).getCurrentOrgId();
		// 获取当前组织机构类型
		Org org = this.orgService.getOrgType(currentOrgId);
		List<SysParam> fwyxList = null;
		if(this.orgService.isNanTongUser(currentOrgId)){
			// 南通
			if(null != org){
				if(100 == org.getOrgTypeId()){
					fwyxList = (List<SysParam>)this.activeService.findIntention(320600, org.getOrgTypeId());
				}else if(200 == org.getOrgTypeId()){
					fwyxList = (List<SysParam>)this.activeService.findIntention(currentOrgId, org.getOrgTypeId());
				}
				int fwyxsize = 0;
				if(null != fwyxList){
					fwyxsize = fwyxList.size();
				}
				SysParam fwyx = null;
				for(int i = 0; i < fwyxsize; i++){
					fwyx = fwyxList.get(i);
					fwyx.setParamKey(fwyx.getParamKey() + "(" + fwyx.getParamDesc() + ")");
				}
			}
		}else if(currentOrgId == 320000){
			fwyxList = (List<SysParam>)this.activeService.findIntention(currentOrgId, org.getOrgTypeId());
		}else{
			// 江苏省其他的12个地级市
			Org temp = this.orgService.getAreaIdByOrgId(currentOrgId);
			fwyxList = (List<SysParam>)this.activeService.findIntention(temp.getOrgId(), org.getOrgTypeId());
		}
		if(!"0".equals(intention)){
			int fwyxsize = 0;
			if(null != fwyxList){
				fwyxsize = fwyxList.size();
			}
			SysParam fwyx = null;
			for(int i = 0; i < fwyxsize; i++){
				fwyx = fwyxList.get(i);
				if(active.getIntention().equals(fwyx.getParamValue())){
					intention = fwyx.getParamKey();
				}
			}
			active.setIntention(intention);
		}else{
			active.setIntention("全部");
		}
		// 查询该活动
		ModelAndView mav=new ModelAndView("/manage/active/activeDetail");
		mav.addObject(active);
		return mav;
	}
	
	/** 推送活动 */
	@RequestMapping(value="/TSActive.action",method=RequestMethod.POST)
	public String TSActive(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 活动ID
		long actId = Long.parseLong(request.getParameter("actId"));
		this.activeService.tsActive(actId, "0");
		return null;
	}
	
	/** 结束活动 */
	@RequestMapping(value="/finishActive.action",method=RequestMethod.POST)
	public String finishActive(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 活动ID
		long actId = Long.parseLong(request.getParameter("actId"));
		// 获取活动的  积分/小时
		// 从缓存中获取服务意向列表
		Map sysparamMap =(HashMap) memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		List hd2jf = (List<SysParam>)sysparamMap.get("HD2JF");
		int point = 0;
		if(null != hd2jf && hd2jf.size() > 0){
			SysParam param = (SysParam)hd2jf.get(0);
			point = Integer.parseInt(param.getParamValue());
		}
		this.activeService.finishActive(actId, point);
		return null;
	}
	
	/** 删除活动 */
	@RequestMapping(value="/deleteActive.action",method=RequestMethod.POST)
	public String deleteActive(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 活动ID
		long actId = Long.parseLong(request.getParameter("actId"));
		this.activeService.deleteActive(actId);
		return null;
	}
	
	/** 手动匹配、发送短信 */
	@RequestMapping(value="/handMatch",method=RequestMethod.POST)
	public String handMatch(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 活动ID
		long actId = Long.parseLong(request.getParameter("actId"));
		// 选择组织的服务意向
		String intentions = request.getParameter("intention");
		// 待发送的短信
		String message = request.getParameter("message");
		
		// 获取登录用户的组织信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 当前的组织ID
		long currentOrgId = ((User)principal).getCurrentOrgId();
		
		// 停止推送
		this.activeService.tsActive(actId, "1");
		// 获取该活动所需要的总人数
		Active active = this.activeService.findActiveById(actId);
		int total = active.getActnum();
		int ratio = active.getRatio();		
		// 获取该活动的工单数
		int num = this.activeService.countWorksheetByActid(actId);
		BigDecimal b1 = new BigDecimal(ratio);	
		BigDecimal b2 = new BigDecimal(100);
		total = total - num;
		BigDecimal b3 = new BigDecimal(total);		
		num = (b3.multiply(b1.divide(b2).add(new BigDecimal(1)))).intValue();
		
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
		if(num > 0){
			// 获取志愿者信息
			List<Postulant> postulantList = this.activeService.findMemberInfoList(num, currentOrgId, intentions, actId);
			int len = 0;
			if(null != postulantList){
				len = postulantList.size();
			}
			// 插入工单信息
			ArrayList<IssueWorkSheet> issueWorkSheetList=new ArrayList<IssueWorkSheet>();			
			// 发送短信
			ArrayList<TiMt> tiMtList=new ArrayList<TiMt>();
			ArrayList<TiMthis> tiMthisList=new ArrayList<TiMthis>();		
			for(int i = 0; i < len; i++){
				Postulant postulant = postulantList.get(i);
				// *******工单信息*********
				IssueWorkSheet issueWorkSheet = new IssueWorkSheet();
				// 活动ID
				issueWorkSheet.setActid(actId);
				// 组织ID
				issueWorkSheet.setOrgid(postulant.getOrgid());
				// 组织名称
				issueWorkSheet.setOrgname(postulant.getOrgname());
				// 志愿者ID
				issueWorkSheet.setVolunteerid(postulant.getId());
				// 志愿者姓名
				issueWorkSheet.setVolunteername(postulant.getName());
				// 手机
				issueWorkSheet.setDn(postulant.getDn());
				// 证件类型
				issueWorkSheet.setCredtype(postulant.getCredtype());
				// 证件号码
				issueWorkSheet.setCredcode(postulant.getCredcode());
				// 服务意向
				issueWorkSheet.setIntention(postulant.getIntention());
				// 社区ID
				issueWorkSheet.setCommunityid(postulant.getCommunityid());
				// 社区名称
				issueWorkSheet.setCommunityname(postulant.getCommunityname());
				
				issueWorkSheetList.add(issueWorkSheet);
				
				TiMt tiMt=new TiMt();
				tiMt.setDstId(postulant.getDn());
				tiMt.setSrcId(timtParam.get("SRCID") + "1" + String.valueOf(actId));
				tiMt.setMsgContent(message);
				tiMtList.add(tiMt);
			}

			this.activeService.sendMessage(issueWorkSheetList, tiMtList, timtParam);
		}		
		return null;
	}
	
	/** 查询工单信息 */
	@RequestMapping(value="/queryIssueWorkSheet")
	public ModelAndView queryIssueWorkSheet(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 活动ID
		long actId = Long.parseLong(request.getParameter("actId"));
		// 获取该活动信息
		Active active = this.activeService.findActiveById(actId);
		// 获取分页信息
		String pagevar = request.getParameter("page");
		int page = pagevar==null||pagevar.equals("") ? 1 : Integer.valueOf(pagevar);
		int size = Constants.EC_WEBPREFERENTIAL_MANAGE_SHOPS_SIZE;
		// 查询该活动工单的总件数
		int total = this.activeService.countMemberByActid(actId);
		// 查询该活动已确认工单件数
		int confirmedNum = this.activeService.countConfirmedMemberByActid(actId);
		// 生成分页工具栏
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(page);
		pageUtil.setTotalRow(total);
		page = page > pageUtil.getTotalPage()? 1:page;
		
		int start = (page - 1) * size;
		int end = start + size;
		
		// 查询该活动的所有工单
		List<IssueWorkSheet> issueWorkSheetList = this.activeService.findMemberByActid(actId, start, end);		
		// 查询该活动
		ModelAndView mav=new ModelAndView("/manage/active/queryIssueWorkSheet");
		mav.addObject("issueWorkSheetList", issueWorkSheetList);
		// 分页工具栏
		mav.addObject("toolNav",pageUtil.getToolsMenu());
		mav.addObject("actId",actId);
		// Excel导出是否可用
		String btnExcelStatus = "";
		if(0 == total){
			btnExcelStatus = "disabled";
		}
		// 获取缓存里的证件类型
		Map hashMap=(HashMap)memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		mav.addObject("zjlx", hashMap.get("ZJLX"));
		// Excel导出按钮状态
		mav.addObject("btnExcelStatus",btnExcelStatus);
		// 活动信息		
		mav.addObject("active", active);
		// 工单总件数
		mav.addObject("total", total);
		// 已确认工单数
		mav.addObject("confirmedNum", confirmedNum);
		return mav;
	}
	
	/** 确认工单 */
	@RequestMapping(value="/doConfirm.action",method=RequestMethod.POST)
	public String doConfirm(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 工单ID
		long worksheetId = Long.parseLong(request.getParameter("worksheetId"));
		this.activeService.doConfirm(worksheetId);
		return null;
	}
	
	/** 导出Excel */
	@RequestMapping(value="/excelExport")
	public ModelAndView excelExport(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		// 活动ID
		long actId = Long.parseLong(request.getParameter("actId"));
		// 证件类型
		Map sysparamMap =(HashMap) memcachedClient.get(CacheKey.ZYZ_SYSPARAM);
		List zjlxList = (List<SysParam>)sysparamMap.get("ZJLX");
		// 查询该活动的所有工单
		List<IssueWorkSheet> issueWorkSheetList = this.activeService.findAllMemberByActid(actId);
		// 服务端生成的文件
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    // 格式化日期(产生文件名)
	    String filename = sdf.format(date);
		File f = new File(request.getRealPath("") + File.separator + filename + ".xls");
		f.createNewFile();
		// 标题数组
		String title[] = {"组织名称","志愿者姓名","手机号码","证件类型","证件号码","社区名称","状态"};
		// 导出内容生成
		int len = 0;
		if(null != issueWorkSheetList){
			len = issueWorkSheetList.size();
		}
		IssueWorkSheet issueWorkSheet = new IssueWorkSheet();
		List excelExportList = new ArrayList();
		SysParam param = null;
		for(int i = 0; i < len; i++){
			List<String> tempList = new ArrayList<String>();
			issueWorkSheet = issueWorkSheetList.get(i);
			// 组织名称
			tempList.add(issueWorkSheet.getOrgname());
			// 志愿者姓名
			tempList.add(issueWorkSheet.getVolunteername());
			// 手机
			tempList.add(issueWorkSheet.getDn());
			// 证件类型
			if(null != zjlxList && zjlxList.size() > 0){
				int size = zjlxList.size();
				for(int j = 0; j < size; j++){
					param = (SysParam)zjlxList.get(j);
					if(issueWorkSheet.getCredtype().equals(param.getParamValue())){
						tempList.add(param.getParamKey());
						break;
					}
				}				
			}			
			// 证件号码
			tempList.add(issueWorkSheet.getCredcode());
			// 社区名称
			tempList.add(issueWorkSheet.getCommunityname());
			// 状态
			if("0".equals(issueWorkSheet.getStatus())){
				tempList.add("未确认");
			}else if("1".equals(issueWorkSheet.getStatus())){
				tempList.add("短信确认");
			}else if("2".equals(issueWorkSheet.getStatus())){
				tempList.add("电话确认");
			}else if("3".equals(issueWorkSheet.getStatus())){
				tempList.add("用户确认");
			}
			excelExportList.add(tempList);
		}
		// 生成excel文件(保存在服务器机上)
		try {
			ExcelUtil.writeExcel(new FileOutputStream(f), title, excelExportList);
		} catch (Exception e) {	   
			e.printStackTrace();
		}		  
		// 导出文件(下载到客户机上,并删除服务器机上的excel文件)
//		ExcelUtil.exportFile(response, f, false);
		ModelAndView mav=new ModelAndView("/manage/active/excelExport");
		request.setAttribute("downloadfile", f);
		
		return mav;
	}
	
	/** 跳转到活动统计画面 */
	@RequestMapping(value="/initActiveCount")
	public ModelAndView initActiveCount() throws Exception {
		ModelAndView mvc=new ModelAndView("/manage/active/activeCount");
		return mvc;
	}
	
	/*
	 * 活动统计
	 */
	@RequestMapping(value="/activeCount.action")
	public ModelAndView activeCount(HttpServletRequest request,HttpServletResponse response){
		// 开始日期
		String startdate = (String)request.getParameter("startdate");
		// 结束日期
		String enddate = (String)request.getParameter("enddate");
		
		User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(this.orgService.isNanTongUser(principal.getOrgId())){
			logger.info("南通的");
		}else{
			logger.info("不是南通的");
		}
		// 当前用户的组织ID
		long currentOrgId=principal.getCurrentOrgId();
		// 查询统计树		
		List<Active> orgActiveNum =
				(List<Active>)this.activeService.createActiveCountTree(currentOrgId, startdate, enddate);
		
		ModelAndView mvc=new ModelAndView("/manage/active/activeCount");
		mvc.addObject("orgActiveNum", orgActiveNum);
		mvc.addObject("startdate", startdate);
		mvc.addObject("enddate", enddate);
		return mvc;
	}
	
	/** 某组织下的活动列表 */
	@RequestMapping(value="/findActiveList")
	public ModelAndView findActiveList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 组织ID
		Long orgid = Long.parseLong((String)request.getParameter("orgid"));
		// 开始日期
		String startdate = (String)request.getParameter("startdate");
		// 结束日期
		String enddate = (String)request.getParameter("enddate");
		// 从缓存中获取组织列表
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		// 选择组织 信息
		Org org = getOrg(orgList,orgid);
		// 获取分页信息
		String pagevar = request.getParameter("page");
		int page = pagevar==null||pagevar.equals("") ? 1 : Integer.valueOf(pagevar);
		int size = Constants.EC_WEBPREFERENTIAL_MANAGE_SHOPS_SIZE;
		// 查询活动总数
		int total = this.activeService.countActiveList(orgid, startdate, enddate);
		// 生成分页工具栏
		CouponPageUtil pageUtil = new CouponPageUtil();
		pageUtil.setPageSize(size);
		pageUtil.setCurPage(page);
		pageUtil.setTotalRow(total);
		page = page > pageUtil.getTotalPage()? 1:page;
		
		int start = (page - 1) * size;
		int end = start + size;
		// 查询当前页活动
		List<Active> activeList=this.activeService.findActiveList(orgid, startdate, enddate, start, end);
	
		ModelAndView mvc=new ModelAndView("/manage/active/activeList");
		mvc.addObject("activeList", activeList);
		// 选择的组织名称
		mvc.addObject("orgName", org.getOrgName());
		mvc.addObject("startdate", startdate);
		mvc.addObject("enddate", enddate);
		// 分页工具栏
		mvc.addObject("toolNav",pageUtil.getToolsMenu());
		return mvc;
	}
	
	/**选择组织 组织树*/
	@RequestMapping(value="/loadTree")
	@ResponseBody
	public void loadTree(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		// 获取登录用户信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 当前的组织ID
		long currentOrgId = ((User)principal).getCurrentOrgId();
		// 从缓存中获取组织列表
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		List<Org> newList=new ArrayList();
		PrintWriter pw = response.getWriter();
		pw.print(orgService.getAllSubOrgJson(orgList, newList, currentOrgId,1,3));
	}
	
	/**手工匹配 组织树*/
	@RequestMapping(value="/loadChkTree")
	@ResponseBody
	public void loadChkTree(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		// 当前活动的服务意向
		long intention = Long.parseLong((String)request.getParameter("intention"));
		// 获取登录用户信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// 当前的组织ID
		long currentOrgId = ((User)principal).getCurrentOrgId();
		// 从缓存中获取组织列表
		List<Org> orgList=(List<Org>) memcachedClient.get(CacheKey.ZYZ_ORG);
		// 判断该服务意向对应的组织ID的类型
		int size = orgList.size();
		Org org = null;
		// 该服务意向对应的组织ID
		long intention_orgid = 0;
		long intention_orgType = 100;
		for(int i = 0; i < size; i++){
			org = orgList.get(i);
			if(intention == org.getBelongOrgId()){
				intention_orgType = org.getOrgTypeId();
				intention_orgid = org.getOrgId();
			}
		}
		List<Org> newList=new ArrayList();
		PrintWriter pw = response.getWriter();
		if(intention_orgType == 200){
			currentOrgId = intention_orgid;
		}
		pw.print(orgService.getAllSubOrgJson(orgList, newList, currentOrgId, 1, 3));
		
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
	
	/**
	 * 过滤组织
	 * @param orgList
	 * @param currentOrgId
	 * @return
	 */
	public List<Org> getOrgs(List<Org> orgList,long currentOrgId){
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
	public Org getOrg(List<Org> orgList,long currentOrgId){
		Org org = null;
		List<Org> tempOrgList=new ArrayList<Org>();
		Org o;
		for(int i=0;i<orgList.size();i++){
			o=(Org)orgList.get(i);
				if(o.getOrgId()==currentOrgId)
					tempOrgList.add((Org)orgList.get(i));
		}
		if(null != tempOrgList && tempOrgList.size() > 0){
			org = (Org)tempOrgList.get(0);
		}
		return org;
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
}

