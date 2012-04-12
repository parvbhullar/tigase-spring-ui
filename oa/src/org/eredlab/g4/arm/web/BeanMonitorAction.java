package org.eredlab.g4.arm.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.arm.service.MonitorService;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

/**
 * SpringBean监控
 * 
 * @author XiongChun
 * @since 2010-09-20
 * @see BaseAction
 */
public class BeanMonitorAction extends BaseAction {

	private MonitorService monitorService = (MonitorService) getService("monitorService");

	/**
	 * SpringBean监控页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("beanMonitorView");
	}

	/**
	 * 查询监控信息列表
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryMonitorDatas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		List eventList = null;
		Integer totalCount = null;
		if (G4Utils.defaultJdbcTypeOracle()) {
			eventList = g4Reader.queryForPage("Monitor.queryBeanMonitorRecordsByDto", dto);
			totalCount = (Integer) g4Reader.queryForObject("Monitor.queryBeanMonitorRecordsByDtoForPageCount", dto);
		} else if (G4Utils.defaultJdbcTypeMysql()) {
			eventList = g4Reader.queryForPage("Monitor.queryBeanMonitorRecordsByDtoMysql", dto);
			totalCount = (Integer) g4Reader.queryForObject("Monitor.queryBeanMonitorRecordsByDtoForPageCountMysql", dto);
		}
		String jsonString = encodeList2PageJson(eventList, totalCount, null);
		write(jsonString, response);
		return mapping.findForward(null);
	}

	/**
	 * 删除监控信息
	 * 
	 * @param
	 * @return
	 */
	public ActionForward deleteMonitorDatas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto dto = aForm.getParamAsDto(request);
		monitorService.deleteMonitorData(dto);
		if (dto.getAsString("type").equalsIgnoreCase("reset"))
			setOkTipMsg("重置成功,所有监控记录已被清除!", response);
		else 
			setOkTipMsg("数据删除成功!", response);
		return mapping.findForward(null);
	}
}
