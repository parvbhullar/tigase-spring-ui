package org.eredlab.g4.demo.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;
import org.njdt.gg.ccl.datastructure.Dto;
import org.njdt.gg.ccl.datastructure.impl.BaseDto;
import org.njdt.gg.ccl.json.JsonHelper;
import org.njdt.gg.ccl.util.G4Utils;
import org.njdt.gg.ccl.util.GlobalConstants;

/**
 * 表格标准范例暨教程Action
 * 
 * @author XiongChun
 * @since 2010-10-23
 * @see BaseAction
 */
public class GridAction extends BaseAction {

	/**
	 * 表格演示一页面初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gridDemo1Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward("gridDemo1View");
	}
	
	/**
	 * 表格演示二页面初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gridDemo2Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("gridDemo2View");
	}
	
	/**
	 * 表格演示三页面初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gridDemo3Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("gridDemo3View");
	}
	
	/**
	 * 表格演示四页面初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gridDemo4Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("gridDemo4View");
	}
	
	/**
	 * 表格演示五页面初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gridDemo5Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("gridDemo5View");
	}
	
	/**
	 * 表格演示六页面初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gridDemo6Init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.removeSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
		return mapping.findForward("gridDemo6View");
	}

	/**
	 * 查询医院收费项目数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward querySfxmDatas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List list = g4Reader.queryForPage("queryCatalogsForGridDemo", dto);
		Integer countInteger = (Integer) g4Reader.queryForObject("countCatalogsForGridDemo", dto);
		String jsonString = JsonHelper.encodeList2PageJson(list, countInteger, GlobalConstants.FORMAT_Date);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	
	/**
	 * 查询医院结算数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryBalanceInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List list = new ArrayList();
		if (G4Utils.isOracle()) {
			list = g4Reader.queryForPage("queryBalanceInfo", dto);
		} else {
			list = g4Reader.queryForPage("queryBalanceInfoMysql", dto);
		}
		Integer countInteger = (Integer) g4Reader.queryForObject("countBalanceInfo", dto);
		super.setSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO", dto);
		String jsonString = encodeList2PageJson(list, countInteger, GlobalConstants.FORMAT_Date);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	
	/**
	 * 汇总医院结算数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sumBalanceInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto dto = (BaseDto)super.getSessionAttribute(request, "GRIDACTION_QUERYBALANCEINFO_DTO");
		Dto sumDto = new BaseDto();
		if (G4Utils.isOracle()) {
			sumDto = (BaseDto)g4Reader.queryForObject("sumBalanceInfo", dto);
		} else if(G4Utils.isMysql()){
			sumDto = (BaseDto)g4Reader.queryForObject("sumBalanceInfoMysql", dto);
		}
		sumDto.put("success", new Boolean(true));
		String jsonString = JsonHelper.encodeObject2Json(sumDto);
		super.write(jsonString, response);
		return mapping.findForward(null);
	}
	
	/**
	 * 保存表格脏数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveDirtyDatas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		List list  = aForm.getGridDirtyData(request);
		for (int i = 0; i < list.size(); i++) {
			Dto dto = (BaseDto)list.get(i);
			System.out.println("脏数据:\n" + dto);
			//todo anything what u want
		}
		Dto outDto = new BaseDto();
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "数据已提交到后台,但演示程序没有将其持久化到数据库.<br>" + request.getParameter("dirtydata"));
		super.write(JsonHelper.encodeObject2Json(outDto), response);
		return mapping.findForward(null);
	}
}
