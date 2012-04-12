package org.eredlab.g4.arm.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.ParamService;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.json.JsonHelper;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

/**
 * 全局参数表管理Action
 * 
 * @author XiongChun
 * @since 2010-05-05
 * @see BaseAction
 */
public class ParamAction extends BaseAction{
	
	private ParamService paramService = (ParamService)super.getService("paramService");
	
	/**
	 * 页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("manageParamView");
	}
	
	/**
	 * 查询参数列表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryParamsForManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List paramList = g4Reader.queryForPage("Param.queryParamsForManage", dto);
		Integer pageCount = (Integer)g4Reader.queryForObject("Param.queryParamsForManageForPageCount", dto);
		String jsonString = JsonHelper.encodeList2PageJson(paramList, pageCount, null);
		write(jsonString, response);
		return mapping.findForward(null);
	}
	
	/**
	 * 保存参数信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward saveParamItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		paramService.saveParamItem(inDto);
		setOkTipMsg("参数数据新增成功", response);
		return mapping.findForward(null);
	}
	
	/**
	 * 删除参数信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteParamItems(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strChecked = request.getParameter("strChecked");
		Dto inDto = new BaseDto();
		inDto.put("strChecked", strChecked);
		paramService.deleteParamItem(inDto);
		setOkTipMsg("参数数据删除成功", response);
		return mapping.findForward(null);
	}
	
	/**
	 * 修改参数信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward updateParamItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		paramService.updateParamItem(inDto);
		Dto outDto = new BaseDto();
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "参数数据修改成功!");
		write(outDto.toJson(), response);
		return mapping.findForward(null);
	}
	
	/**
	 * 内存同步
	 * 
	 * @param
	 * @return
	 */
	public ActionForward synMemory(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
	    List paramList = g4Reader.queryForList("Resource.getParamList");
	    getServlet().getServletContext().removeAttribute("EAPARAMLIST");
	    getServlet().getServletContext().setAttribute("EAPARAMLIST", paramList);
		Dto outDto = new BaseDto();
		outDto.put("success", new Boolean(true));
		write(JsonHelper.encodeObject2Json(outDto), response);
		return mapping.findForward(null);
	}
}
