package com.xzb.oa.schedule.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.UserService;
import org.eredlab.g4.arm.vo.UserInfoVo;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.ccl.util.G4Constants;
import org.eredlab.g4.ccl.util.G4RoleLimitis;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

import com.xzb.oa.knowledge.service.KService;
import com.xzb.oa.schedule.service.SService;

/**
 * 日程管理系统
 * 
 * @since 2012-3-2
 * @see BaseAction
 */
public class SAction extends BaseAction {
	
	private UserService userService = (UserService) super.getService("userService");

	private SService sService = (SService) super.getService("sService");
	
	private KService kService = (KService) super.getService("kService");

	/**
	 * 我的日程管理主界面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward managerScheduleInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("manageSView");
	}
	
	/**
	 * 查询我的日程
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryScheduleList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userid = getSessionContainer(request).getUserInfo().getUserid();
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("schefounder", userid);
		List list = g4Reader.queryForPage("S.queryScheduleList", dto);
		Integer countInteger = (Integer) g4Reader.queryForObject("S.countScheduleList", dto);
		String jsonString = JsonHelper.encodeList2PageJson(list, countInteger, G4Constants.FORMAT_Date);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	
	/**
	 * 保存我的日程
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveScheItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userid = getSessionContainer(request).getUserInfo().getUserid();
		
		CommonActionForm aForm = (CommonActionForm) form;
		
		Dto inDto = aForm.getParamAsDto(request);
		
		inDto.put("schefounder", userid);
		
		if(G4Utils.isEmpty(inDto.getAsString("exppeopleId")))
		{
			inDto.put("exppeopleId", userid);  //pDto.getAsString("exppeopleId")
		}
		
		
		String remind = request.getParameter("remind");
		
		
		if (null == remind || remind.equals("")) {
			inDto.put("remind", "1");
		}
		Dto outDto = sService.saveScheItem(inDto);
		outDto.put("msg", "日程新增成功");
		outDto.put("success", new Boolean(true));
		String jsonString = JsonHelper.encodeObject2Json(outDto);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	
	/**
	 * 修改我的日程
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateScheItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		String remind = request.getParameter("remind");
		if (null == remind || remind.equals("")) {
			inDto.put("remind", "1");
		}
		sService.updateScheItem(inDto);
		setOkTipMsg("日程数据修改成功", response);
		return mapping.findForward(null);
	}
	
	/**
	 * 批量修改我的日程
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteScheItems(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strChecked = request.getParameter("strChecked");
		String updatemode = request.getParameter("updatemode");
		Dto inDto = new BaseDto();
		inDto.put("strChecked", strChecked);
		inDto.put("updatemode", updatemode);
		sService.deleteScheItems(inDto);
		setOkTipMsg("日程操作成功", response);
		return mapping.findForward(null);
	}
	
	/**
	 * 查询日程主界面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward managerAllScheduleInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("manageAllSView");
	}
	
	/**
	 * 查询日程
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryAllScheduleList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userid = getSessionContainer(request).getUserInfo().getUserid();
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List list = g4Reader.queryForPage("S.queryAllScheduleList", dto);
		for(int i=0;i<list.size();i++)
		{
			Dto b=new BaseDto();
			b=(BaseDto)list.get(i);
			b.put("loginuserid", b.getAsString("execf"));
			b.put("id", b.getAsString("scheid"));
		}
		//
		Dto dep=new BaseDto();
		dep=kService.queryUserdep(dto);
		String deptid=dep.getAsString("deptid");
		dto.put("moduleType", "2");
		dto.put("deptid", deptid);
		
		G4RoleLimitis g1=new G4RoleLimitis();
		List newDeptList=g1.FilterRoleLimitis(list,dto);
		//
		//Integer countInteger = (Integer) g4Reader.queryForObject("S.countAllScheduleList", dto);
		Integer countInteger = 0;
		if(null != newDeptList)
		{
			countInteger = newDeptList.size();
		}
		String jsonString = JsonHelper.encodeList2PageJson(newDeptList, countInteger, G4Constants.FORMAT_Date);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	
	
	/**
	 * 查询下属日程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryLowerScheduleList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		
		String userid=request.getParameter("userid");
		dto.put("userid", userid);
		
		
		List list = g4Reader.queryForPage("S.queryLowerScheduleList", dto);
		
		
		//
		Integer countInteger = (Integer) g4Reader.queryForObject("S.countAllScheduleList", dto);
		
		String jsonString = JsonHelper.encodeList2PageJson(list, countInteger, G4Constants.FORMAT_Date);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	
	/**
	 * 我的日程日历界面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward manageCalenderInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("manageCalenderView");
	}
	
	/**
	 * 查询日程日历
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward querySchedules(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userid = getSessionContainer(request).getUserInfo().getUserid();
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		dto.put("schefounder", userid);
		List list = g4Reader.queryForList("S.querySchedules", dto);
		Integer countInteger = (Integer) g4Reader.queryForObject("S.countSchedules", dto);
		String jsonString = "{" + JsonHelper.encodeList2PageJson1(list, countInteger, G4Constants.FORMAT_Date);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	
	
	public ActionForward updateShareType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto dto = aForm.getParamAsDto(request);
		Dto outDto=new BaseDto();
		String type = request.getParameter("shareType");//共享类型
		String sId=request.getParameter("sId");//目录id
		
		
		
		if(G4Utils.isEmpty(type))
		{
			type="0";
		}
			dto.put("sId", sId);
			dto.put("type", type);
			sService.updateShareType(dto);
			
			setOkTipMsg("共享成功", response);
		
		
		
		return mapping.findForward(null);
	}
	
	/**
	 * 下级日程查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lowerCalenderInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfoVo userInfoVo = getSessionContainer(request).getUserInfo();
		request.setAttribute("userid", userInfoVo.getUserid());
		
		request.setAttribute("username", "下级成员");
		
		request.setAttribute("login_account", userInfoVo.getAccount());
		return mapping.findForward("lowerCalenderView");
	}
	
	
	public ActionForward lowerTree(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto dto = new BaseDto();
		String nodeid = request.getParameter("node");
		
		dto.put("parentid", nodeid);

		Dto outDto = sService.queryLower(dto);
		write(outDto.getAsString("jsonString"), response);
		return mapping.findForward(null);
	}

}
