package org.eredlab.g4.arm.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.MonitorService;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;
import org.njdt.gg.ccl.datastructure.Dto;
import org.njdt.gg.ccl.datastructure.impl.BaseDto;
import org.njdt.gg.ccl.json.JsonHelper;
import org.njdt.gg.ccl.util.G4Utils;

/**
 * JDBC监控
 * 
* @author njdt
 * @since 2010-09-03
 * @see BaseAction
 */
public class JdbcMonitorAction extends BaseAction {

	private MonitorService monitorService = (MonitorService) super.getService("monitorService");

	/**
	 * JDBC实时监控页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward jdbcInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("jdbcInitView");
	}

	/**
	 * 查询监控信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryMonitorData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		List eventList = null;
		Integer totalCount = null;
		if (G4Utils.isOracle()) {
			eventList = g4Reader.queryForPage("queryJdbcMonitorRecordsByDto", inDto);
			totalCount = (Integer)g4Reader.queryForObject("queryJdbcMonitorRecordsByDtoForPageCount", inDto);
		} else if (G4Utils.isMysql()) {
			eventList = g4Reader.queryForPage("queryJdbcMonitorRecordsByDtoMysql", inDto);
			totalCount = (Integer)g4Reader.queryForObject("queryJdbcMonitorRecordsByDtoForPageCountMysql", inDto);
		}
		String jsonString = encodeList2PageJson(eventList, totalCount, null);
		write(jsonString, response);
		return mapping.findForward(null);
	}

	/**
	 * 重置监控信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward resetMonitorData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm cForm = (CommonActionForm)form;
		monitorService.resetMonitorData();
		Dto outDto = new BaseDto();
		outDto.put("success", new Boolean(true));
		outDto.put("msg", "JDBC监控记录重置成功");
		write(outDto.toJson(), response);
		return mapping.findForward(null);
	}

}
