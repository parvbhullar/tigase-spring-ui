package com.xzb.oa.knowledge.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.UserService;
import org.eredlab.g4.arm.util.ArmConstants;
import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4RoleLimitis;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.rif.util.WebUtils;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

import com.xzb.oa.knowledge.service.KService;

/**
 * 知识管理系统
 * 
 * @since 2012-3-2
 * @see BaseAction
 */
public class KAction extends BaseAction {
	
	private UserService userService = (UserService) super.getService("userService");
	

	private KService kService = (KService) super.getService("kService");

	/**
	 * 目录方法开始
	 */
	
	/**
	 * 知识管理系统页面初始化
	 * @param
	 * @return
	 */
	public ActionForward kInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.removeSessionAttribute(request, "deptid");
		Dto inDto = new BaseDto();
		String deptid = super.getSessionContainer(request).getUserInfo().getDeptid();
		String person=request.getParameter("person");//person=1表示个人文档
		
		inDto.put("deptid", deptid);
		Dto outDto=kService.queryDirCount(inDto);
		if(outDto.getAsInteger("count")==1)
		{
			outDto = kService.queryDeptinfoByDeptid(inDto);
		}else{
			
			kService.initDir(inDto);
		}
		request.setAttribute("rootDirid", outDto.getAsString("dirid"));
		if(person.equals("1"))
		{
			request.setAttribute("rootDirname", "我的文档");
		}else{
			request.setAttribute("rootDirname", "公司文档");
		}
		//request.setAttribute("rootDirname", outDto.getAsString("dirname"));
		UserInfoVo userInfoVo = getSessionContainer(request).getUserInfo();
		request.setAttribute("login_account", userInfoVo.getAccount());
		request.setAttribute("loginuserid", userInfoVo.getUserid());
		request.setAttribute("person", person);
		return mapping.findForward("manageKView");
	}

	
	public ActionForward kShareInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.removeSessionAttribute(request, "deptid");
		Dto inDto = new BaseDto();
		String deptid = super.getSessionContainer(request).getUserInfo().getDeptid();
		
		inDto.put("deptid", deptid);
		Dto outDto=kService.queryDirCount(inDto);
		if(outDto.getAsInteger("count")==1)
		{
			outDto = kService.queryDeptinfoByDeptid(inDto);
		}else{
			
			kService.initDir(inDto);
		}
		request.setAttribute("rootDirid", outDto.getAsString("dirid"));
		
		request.setAttribute("rootDirname", "共享文档");
		
		//request.setAttribute("rootDirname", outDto.getAsString("dirname"));
		UserInfoVo userInfoVo = getSessionContainer(request).getUserInfo();
		request.setAttribute("login_account", userInfoVo.getAccount());
		request.setAttribute("loginuserid", userInfoVo.getUserid());
		return mapping.findForward("manageKShare");
	} 
	
	
	
/**
 * 
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward dirTreeInitShare(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto dto = new BaseDto();
		String nodeid = request.getParameter("node");
		String loginuserid=request.getParameter("loginuserid");
		dto.put("parentid", nodeid);
		dto.put("loginuserid", loginuserid);
		Dto outDto = kService.queryDirItems_(dto);
		write(outDto.getAsString("jsonString"), response);
		return mapping.findForward(null);
	}
		
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wdInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("manageWorkdiary");
	}

	public ActionForward dirTreeInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto dto = new BaseDto();
		String nodeid = request.getParameter("node");
		String person=request.getParameter("person");
		String loginuserid=request.getParameter("loginuserid");
		dto.put("parentid", nodeid);
		if(person.equals("1"))
		{
			dto.put("loginuserid", loginuserid);
			dto.put("dirMold", "02");
		}else{
			dto.put("dirMold", "01");
		}
		
		Dto outDto = kService.queryDirItems(dto);
		write(outDto.getAsString("jsonString"), response);
		return mapping.findForward(null);
	}
	

	/**
	 * 保存目录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveDirItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		String baseFile=request.getSession().getServletContext().getRealPath("");
		inDto.put("baseFile", baseFile);
		Dto outDto = kService.saveDirItem(inDto);
		
		if(outDto.getAsString("success").equals("1"))
		{
			//kService.createDir(inDto);已经改为新建文档的时候建立文件夹
			setOkTipMsg("目录增加成功", response);
		}else
		{
			setOkTipMsg("同级目录下已经有同名目录了", response);
		}
		
		return mapping.findForward(null);
	}
	
	/**
	 * 修改目录名称
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateDirItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		String baseFile=request.getSession().getServletContext().getRealPath("");
		inDto.put("baseFile", baseFile);
		kService.updateDirItem(inDto);
		setOkTipMsg("用户数据修改成功", response);
		return mapping.findForward(null);
	}
	
	
	/**
	 * 修改用户
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateDocItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		String releaseTime=G4Utils.getCurrentTime();
		inDto.put("updateTime", releaseTime);
		kService.updateDocItem(inDto);
		setOkTipMsg("文档数据修改成功", response);
		return mapping.findForward(null);
	}
	
	
	/**
	 * 删除目录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteDirItems(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String dirid = request.getParameter("dirid");
		String parentid=request.getParameter("parentid");
		//String baseFile=request.getSession().getServletContext().getRealPath("");
		//Dto fileDto=new BaseDto();
		//String filepath="";
		Dto inDto = new BaseDto();
		Dto outDto = new BaseDto();
		inDto.put("dirId", dirid);
		inDto.put("parentDirId", parentid);
		//inDto.put("baseFile", baseFile);
		//fileDto=kService.getDirItems(inDto);
		//filepath=fileDto.getAsString("filepath");
		//inDto.put("filepath", filepath);
		outDto=kService.deleteDirItems(inDto);
		if(outDto.getAsString("success").equals("0"))
		{
			setOkTipMsg("此目录下有下级文档，请先删除文档才能删除此目录", response);
		}else if(outDto.getAsString("success").equals("2"))
		{
			setOkTipMsg("此目录是系统目录，不能删除", response);
		}
		
		else
		{
			setOkTipMsg("删除成功", response);
		}
		return mapping.findForward(null);
	}
	
	
	/**
	 * 目录方法结束
	 */
	
	/**
	 * 文档操作开始
	 */
	public ActionForward saveDocItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);	
		String releaseTime=G4Utils.getCurrentTime();
		String typeOfdoc=request.getParameter("typeOfDoc");
		inDto.put("releaseTime", releaseTime);
		inDto.put("updateTime", releaseTime);
		inDto.put("typeOfDoc", typeOfdoc);
		inDto.put("docAuthor", super.getSessionContainer(request).getUserInfo().getUsername());
		inDto.put("userid", super.getSessionContainer(request).getUserInfo().getUserid());
		inDto.put("docAuthorDep", super.getSessionContainer(request).getUserInfo().getDeptid());
		kService.saveDocItem(inDto);
		setOkTipMsg("文档数据新增成功", response);
		return mapping.findForward(null);
	}
	
	/**
	 * 删除用户
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteDocItems(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strChecked = request.getParameter("strChecked");
		Dto inDto = new BaseDto();
		inDto.put("strChecked", strChecked);
		kService.deleteDocItems(inDto);
		setOkTipMsg("文档数据删除成功", response);
		return mapping.findForward(null);
	}
	
	/**
	 * 查询文档列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryDocsForManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		String dirId = request.getParameter("dirId");
		String person=request.getParameter("person");
		String loginuserid=request.getParameter("loginuserid");
		if (G4Utils.isNotEmpty(dirId)) {
			setSessionAttribute(request, "dirId", dirId);
		}
		if(!G4Utils.isEmpty(person))
		if(G4Utils.isEmpty(dirId) && person.equals("1"))
		{
			dto.put("dirId", loginuserid);
		}
		if(!G4Utils.isEmpty(person))
		{
			dto.put("typeOfDoc", person);
		}
		
		
		dto.put("usertype", ArmConstants.USERTYPE_ADMIN);
		UserInfoVo userInfoVo = getSessionContainer(request).getUserInfo();
		if (WebUtils.getParamValue("DEFAULT_ADMIN_ACCOUNT", request).equals(userInfoVo.getAccount())) {
			dto.remove("usertype");
		}
		if (WebUtils.getParamValue("DEFAULT_DEVELOP_ACCOUNT", request).equals(userInfoVo.getAccount())) {
			dto.remove("usertype");
		}
		List userList = g4Reader.queryForPage("K.queryDocsForManage", dto);
		for(int i=0;i<userList.size();i++)
		{
			Dto d=(BaseDto)userList.get(i);	
			if(!G4Utils.isEmpty(d.get("doccontent")))
			{
				byte[] b=(byte[])d.get("doccontent");				
				String a=new String(b,"utf-8");
				d.put("doccontent", a);
			}
		}
		Integer pageCount = (Integer) g4Reader.queryForObject("K.queryDocsForManageForPageCount", dto);
		String jsonString = JsonHelper.encodeList2PageJson(userList, pageCount, null);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	
	
	/**
	 * 共享文档
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryDocsForManage_(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		String dirId = request.getParameter("dirId");
		String share=request.getParameter("share");
		if(G4Utils.isEmpty(dirId))
			return mapping.findForward(null);
		if (G4Utils.isNotEmpty(dirId)) {
			setSessionAttribute(request, "dirId", dirId);
		}
		dto.put("usertype", ArmConstants.USERTYPE_ADMIN);
		UserInfoVo userInfoVo = getSessionContainer(request).getUserInfo();
		if (WebUtils.getParamValue("DEFAULT_ADMIN_ACCOUNT", request).equals(userInfoVo.getAccount())) {
			dto.remove("usertype");
		}
		if (WebUtils.getParamValue("DEFAULT_DEVELOP_ACCOUNT", request).equals(userInfoVo.getAccount())) {
			dto.remove("usertype");
		}
		List userList = g4Reader.queryForPage("K.queryDocsForManageShare", dto);
		Dto o=new BaseDto();
		for(int i=0;i<userList.size();i++)
		{
			o=(BaseDto)userList.get(i);
			o.put("loginuserid", o.getAsString("userid"));
			o.put("id", o.getAsString("docid"));
		}
		
		Dto dep=new BaseDto();
		dep=kService.queryUserdep(dto);
		String deptid=dep.getAsString("deptid");
		dto.put("deptid", deptid);
		dto.put("moduleType", "1");//文档的权限标志位为1
		G4RoleLimitis g1=new G4RoleLimitis();
		List newDeptList=g1.FilterRoleLimitis(userList,dto);
		if(!G4Utils.isEmpty(newDeptList))
		{
			for(int i=0;i<newDeptList.size();i++)
			{
				Dto d=(BaseDto)newDeptList.get(i);	
				if(!G4Utils.isEmpty(d.get("doccontent")))
				{
					byte[] b=(byte[])d.get("doccontent");				
					String a=new String(b,"utf-8");
					d.put("doccontent", a);
				}
			}
		}
		
		Integer pageCount = (Integer) g4Reader.queryForObject("K.queryDocsForManageForPageCount", dto);
		String jsonString = JsonHelper.encodeList2PageJson(newDeptList, newDeptList.size(), null);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	/**
	 * 取得当前目录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getDirItems(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String dirId=(String)request.getParameter("dirId");
		String attaId=(String)request.getParameter("attaId");		
		Dto pDto=new BaseDto();
		pDto.put("dirId", dirId);
		Dto dto=new BaseDto();
		String tempAttaId="";
		if(""!=attaId){
			int i=attaId.length();
			tempAttaId=attaId.substring(1, i-1);
		}
		Dto fileDto = (BaseDto) g4Reader.queryForObject("Demo.queryFileByFileID", tempAttaId);
		dto.put("docName", fileDto.get("filename"));
		dto.put("filepath", fileDto.get("dirname"));
		super.write(dto.toJson(), response);
		return mapping.findForward(null);
	}
	
	
	public ActionForward deleteAtt(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String id=(String)request.getParameter("attaId");
		String attaId=id.substring(1,id.length()-1); 
		Dto pDto=new BaseDto();
		pDto.put("attid", attaId);
		Dto outDto=kService.deleteAtt(pDto);
		super.write(outDto.toJson(), response);
		return mapping.findForward(null);
	}
	
	
	/**
	 * 文档操作结束
	 */
	/**
	 * 权限控制start
	 */
	public ActionForward saveLimitis(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		String docId = request.getParameter("docId");
		String moduleType=request.getParameter("moduleType");
		//String peopleType=request.getParameter("peopleType");
		String fields=request.getParameter("fields");
		String fieldsName=request.getParameter("fieldsName");
		//String limitisType=request.getParameter("limitisType");//部门为1，人员为2
		dto.put("docId", docId);	
		dto.put("fields", fields);
		dto.put("fieldsName", fieldsName);
		dto.put("moduleType", moduleType);
		kService.saveLimitis(dto);
		setOkTipMsg("数据保存成功", response);
		return mapping.findForward(null);
	}
	
	
	
	public ActionForward saveLimitisDept(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		String docId = request.getParameter("docId");
		String moduleType=request.getParameter("moduleType");
		//String peopleType=request.getParameter("peopleType");
		String fields=request.getParameter("fields");
		String fieldsName=request.getParameter("fieldsName");
		//String limitisType=request.getParameter("limitisType");//部门为1，人员为2
		dto.put("docId", docId);	
		dto.put("fields", fields);
		dto.put("fieldsName", fieldsName);
		dto.put("moduleType", moduleType);
		kService.saveLimitisDept(dto);
		setOkTipMsg("数据保存成功", response);
		return mapping.findForward(null);
	}
	
	
	
	public ActionForward queryLimitis(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		Dto outDto=new BaseDto();
		String docId = request.getParameter("docId");
		String depType=request.getParameter("depType");
		String peopleType=request.getParameter("peopleType");
		dto.put("docId", docId);	
		dto.put("depType", depType);
		dto.put("peopleType", peopleType);
		//dto.put("fields", fields);
		outDto=kService.queryLimitis(dto);
		if(!G4Utils.isEmpty(outDto.getAsString("jsonString")))
		write(outDto.getAsString("jsonString"), response);
		return mapping.findForward(null);
	}
	
	public ActionForward queryLimitis_(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		Dto outDto=new BaseDto();
		String docId = request.getParameter("docId");
		String moduleType=request.getParameter("moduleType");
		String DoclimitisType=request.getParameter("DoclimitisType");
		dto.put("docId", docId);	
		dto.put("moduleType_", moduleType);
		dto.put("DoclimitisType", DoclimitisType);
		//dto.put("fields", fields);
		outDto=kService.queryLimitis_(dto);
		if(!G4Utils.isEmpty(outDto.getAsString("jsonString")))
		write(outDto.getAsString("jsonString"), response);
		return mapping.findForward(null);
	}
	
	/**
	 * 权限控制end
	 */
	
	/**
	 * 目录共享设置start
	 */
	public ActionForward updateShareType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		Dto outDto=new BaseDto();
		String type = request.getParameter("shareType");//共享类型
		String dirId=request.getParameter("dirId");//目录id
		
		
		
		if(G4Utils.isEmpty(type))
		{
			type="0";
		}
			dto.put("dirId", dirId);
			dto.put("type", type);
			kService.updateShareType(dto);
			
			setOkTipMsg("共享成功", response);
		
		
		
		return mapping.findForward(null);
	}
	
	
	
	/**
	 * 目录共享设置end
	 */
	
	public ActionForward calendars(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String str="{'calendars':[{'id':1,'title':'Home','color':2}]}";
		JSONObject jSONObject=new JSONObject();
		jSONObject.put("id", 1);
		jSONObject.put("title", "home");
		jSONObject.put("color", 2);
		JSONArray jSONOArray=new JSONArray();
		jSONOArray.add(0,jSONObject);
		
		jSONObject=new JSONObject();
		jSONObject.put("calendars", jSONOArray);
		super.write(jSONObject.toString(), response);
		return mapping.findForward(null);
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String str="{'calendars':[{'id':1,'title':'Home','color':2}]}";
		JSONObject jSONObject=new JSONObject();
		jSONObject.put("id", 1002);
		jSONObject.put("cid", 2);
		jSONObject.put("start", "2012-04-05T11:30:00-07:00");
		jSONObject.put("end", "2012-04-05T13:00:00-07:00");
		jSONObject.put("title", "日程设计文档");
		jSONObject.put("loc", "Chuy's");
		jSONObject.put("url", "www.163.com");
		jSONObject.put("notes", "Order the queso");
		JSONArray jSONOArray=new JSONArray();
		jSONOArray.add(0,jSONObject);
		jSONObject=new JSONObject();
		jSONObject.put("data", jSONOArray);
		jSONObject.put("success", "true");
		jSONObject.put("message", "load data");
		super.write(jSONObject.toString(), response);
		return mapping.findForward(null);
	}
	
//	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		String str="{'success':true,'message':'Loaded data','data':[{'id':1002,'cid':2,'start':'2012-04-05T11:30:00-07:00','end':'2012-04-05T13:00:00-07:00','title':'Lunch with Matt','loc':'Chuy's','url':'http:chuys.com','notes':'Order the queso'},{'id':1003,'cid':3,'start':'2012-04-05T15:00:00-07:00','end':'2012-04-05T15:00:00-07:00','title':'Project due'},{'id':1004,'cid':1,'start':'2012-04-05T00:00:00-07:00','end':'2012-04-05T00:00:00-07:00','title':'Sarah's birthday','ad':true,'notes':'Need to get a gift'},{'id':1005,'cid':2,'start':'2012-03-24T00:00:00-07:00','end':'2012-04-14T23:59:59-07:00','title':'A long one...','ad':true},{'id':1006,'cid':3,'start':'2012-04-10T00:00:00-07:00','end':'2012-04-11T23:59:59-07:00','title':'School holiday'},{'id':1007,'cid':1,'start':'2012-04-05T09:00:00-07:00','end':'2012-04-05T09:30:00-07:00','title':'Haircut','notes':'Get cash on the way','rem':60},{'id':1009,'cid':2,'start':'2012-04-03T13:00:00-07:00','end':'2012-04-03T18:00:00-07:00','title':'Board meeting','loc':'ABC Inc.','rem':60},{'id':1010,'cid':3,'start':'2012-04-03T00:00:00-07:00','end':'2012-04-07T23:59:59-07:00','title':'Jenny's final exams','ad':true},{'id':1011,'cid':1,'start':'2012-04-07T19:00:00-07:00','end':'2012-04-07T23:00:00-07:00','title':'Movie night','note':'Don't forget the tickets!','rem':60}]}";
////		super.write(str, response);
//		JSONObject jSONObject=new JSONObject();
//		jSONObject.put("start", "2012-04-06T00:00:00");
//		jSONObject.put("end", "2012-04-06T01:00:00");
//		jSONObject.put("ad", "false");
//		jSONObject.put("cid", "1");
//		jSONObject.put("title", "新增的日志");
//		jSONObject.put("id", "1020");
//		JSONObject tempJSONObject=new JSONObject();
//		tempJSONObject.put("data", jSONObject);
//		tempJSONObject.put("success", true);
//		tempJSONObject.put("message", "创建成功");
//		super.write(tempJSONObject.toString(), response);
//		return mapping.findForward(null);
//	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String str="{'success':true,'message':'Loaded data','data':[{'id':1002,'cid':2,'start':'2012-04-05T11:30:00-07:00','end':'2012-04-05T13:00:00-07:00','title':'Lunch with Matt','loc':'Chuy's','url':'http:chuys.com','notes':'Order the queso'},{'id':1003,'cid':3,'start':'2012-04-05T15:00:00-07:00','end':'2012-04-05T15:00:00-07:00','title':'Project due'},{'id':1004,'cid':1,'start':'2012-04-05T00:00:00-07:00','end':'2012-04-05T00:00:00-07:00','title':'Sarah's birthday','ad':true,'notes':'Need to get a gift'},{'id':1005,'cid':2,'start':'2012-03-24T00:00:00-07:00','end':'2012-04-14T23:59:59-07:00','title':'A long one...','ad':true},{'id':1006,'cid':3,'start':'2012-04-10T00:00:00-07:00','end':'2012-04-11T23:59:59-07:00','title':'School holiday'},{'id':1007,'cid':1,'start':'2012-04-05T09:00:00-07:00','end':'2012-04-05T09:30:00-07:00','title':'Haircut','notes':'Get cash on the way','rem':60},{'id':1009,'cid':2,'start':'2012-04-03T13:00:00-07:00','end':'2012-04-03T18:00:00-07:00','title':'Board meeting','loc':'ABC Inc.','rem':60},{'id':1010,'cid':3,'start':'2012-04-03T00:00:00-07:00','end':'2012-04-07T23:59:59-07:00','title':'Jenny's final exams','ad':true},{'id':1011,'cid':1,'start':'2012-04-07T19:00:00-07:00','end':'2012-04-07T23:00:00-07:00','title':'Movie night','note':'Don't forget the tickets!','rem':60}]}";
//		super.write(str, response);
		return mapping.findForward(null);
	}
	
	public ActionForward destroy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String str="{'success':true,'message':'Loaded data','data':[{'id':1002,'cid':2,'start':'2012-04-05T11:30:00-07:00','end':'2012-04-05T13:00:00-07:00','title':'Lunch with Matt','loc':'Chuy's','url':'http:chuys.com','notes':'Order the queso'},{'id':1003,'cid':3,'start':'2012-04-05T15:00:00-07:00','end':'2012-04-05T15:00:00-07:00','title':'Project due'},{'id':1004,'cid':1,'start':'2012-04-05T00:00:00-07:00','end':'2012-04-05T00:00:00-07:00','title':'Sarah's birthday','ad':true,'notes':'Need to get a gift'},{'id':1005,'cid':2,'start':'2012-03-24T00:00:00-07:00','end':'2012-04-14T23:59:59-07:00','title':'A long one...','ad':true},{'id':1006,'cid':3,'start':'2012-04-10T00:00:00-07:00','end':'2012-04-11T23:59:59-07:00','title':'School holiday'},{'id':1007,'cid':1,'start':'2012-04-05T09:00:00-07:00','end':'2012-04-05T09:30:00-07:00','title':'Haircut','notes':'Get cash on the way','rem':60},{'id':1009,'cid':2,'start':'2012-04-03T13:00:00-07:00','end':'2012-04-03T18:00:00-07:00','title':'Board meeting','loc':'ABC Inc.','rem':60},{'id':1010,'cid':3,'start':'2012-04-03T00:00:00-07:00','end':'2012-04-07T23:59:59-07:00','title':'Jenny's final exams','ad':true},{'id':1011,'cid':1,'start':'2012-04-07T19:00:00-07:00','end':'2012-04-07T23:00:00-07:00','title':'Movie night','note':'Don't forget the tickets!','rem':60}]}";
//		super.write(str, response);
		return mapping.findForward(null);
	}
}
