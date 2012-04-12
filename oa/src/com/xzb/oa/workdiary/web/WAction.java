package com.xzb.oa.workdiary.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.UserService;
import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.rif.web.BaseAction;

import com.xzb.oa.workdiary.service.WService;

/**
 * 知识管理系统
 * 
 * @since 2012-3-2
 * @see BaseAction
 */
public class WAction extends BaseAction {
	
	private UserService userService = (UserService) super.getService("userService");
	

	private WService wService = (WService) super.getService("wService");

	public ActionForward wdInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("manageWorkdiary");
	}
	
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

		Dto pDto=new BaseDto();
		UserInfoVo userInfoVo = getSessionContainer(request).getUserInfo();
		pDto.put("userid", userInfoVo.getUserid());
		Dto dto=wService.queryWorkdiaryItems(pDto);
		List list=(List)dto.get("workdiaryList");
		
//		 JSONArray.fromObject(list);
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
		jSONObject.put("data", JSONArray.fromObject(list));
		jSONObject.put("success", "true");
		jSONObject.put("message", "load data");
		super.write(jSONObject.toString(), response);
		return mapping.findForward(null);
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String str="{'success':true,'message':'Loaded data','data':[{'id':1002,'cid':2,'start':'2012-04-05T11:30:00-07:00','end':'2012-04-05T13:00:00-07:00','title':'Lunch with Matt','loc':'Chuy's','url':'http:chuys.com','notes':'Order the queso'},{'id':1003,'cid':3,'start':'2012-04-05T15:00:00-07:00','end':'2012-04-05T15:00:00-07:00','title':'Project due'},{'id':1004,'cid':1,'start':'2012-04-05T00:00:00-07:00','end':'2012-04-05T00:00:00-07:00','title':'Sarah's birthday','ad':true,'notes':'Need to get a gift'},{'id':1005,'cid':2,'start':'2012-03-24T00:00:00-07:00','end':'2012-04-14T23:59:59-07:00','title':'A long one...','ad':true},{'id':1006,'cid':3,'start':'2012-04-10T00:00:00-07:00','end':'2012-04-11T23:59:59-07:00','title':'School holiday'},{'id':1007,'cid':1,'start':'2012-04-05T09:00:00-07:00','end':'2012-04-05T09:30:00-07:00','title':'Haircut','notes':'Get cash on the way','rem':60},{'id':1009,'cid':2,'start':'2012-04-03T13:00:00-07:00','end':'2012-04-03T18:00:00-07:00','title':'Board meeting','loc':'ABC Inc.','rem':60},{'id':1010,'cid':3,'start':'2012-04-03T00:00:00-07:00','end':'2012-04-07T23:59:59-07:00','title':'Jenny's final exams','ad':true},{'id':1011,'cid':1,'start':'2012-04-07T19:00:00-07:00','end':'2012-04-07T23:00:00-07:00','title':'Movie night','note':'Don't forget the tickets!','rem':60}]}";
//		super.write(str, response);
		String data=request.getParameter("data");
		Dto pDto=JsonHelper.parseSingleJson2Dto(data);
		UserInfoVo userInfoVo = getSessionContainer(request).getUserInfo();
		pDto.put("userid", userInfoVo.getUserid());
		
		JSONObject jSONObject=new JSONObject();
		jSONObject.put("start", pDto.get("start"));
		jSONObject.put("end", pDto.get("end"));
		jSONObject.put("ad", pDto.get("ad"));
		jSONObject.put("cid", pDto.get("cid"));
		jSONObject.put("title", pDto.get("title"));
		jSONObject.put("id", "1020");
		JSONObject tempJSONObject=new JSONObject();
		tempJSONObject.put("data", jSONObject);
		tempJSONObject.put("success", true);
		tempJSONObject.put("message", "创建成功");
		wService.saveWorkdiary(pDto);
		super.write(tempJSONObject.toString(), response);
		return mapping.findForward(null);
	}
	
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
