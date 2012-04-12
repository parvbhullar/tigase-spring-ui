package org.eredlab.g4.demo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.ccl.util.G4Constants;
import org.eredlab.g4.rif.report.jasper.ReportData;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

/**
 * Applet报表标准范例暨教程Action
 * 
 * @author XiongChun
 * @since 2010-10-13
 * @see BaseAction
 */
public class JasperReportAction extends BaseAction {

	/**
	 * applet报表页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward appletInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getSessionContainer(request).cleanUp();
		return mapping.findForward("appletReportView");
	}

	/**
	 * PDF报表页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward pdfInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.getSessionContainer(request).cleanUp();
		return mapping.findForward("pdfReportView");
	}

	/**
	 * 查询医院收费目录
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryCatalogs4Print(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm)form;
		Dto inDto = aForm.getParamAsDto(request);
		super.setSessionAttribute(request, "QUERYCATALOGS4PRINT_QUERYDTO", inDto);
		List catalogList = g4Reader.queryForPage("Demo.queryCatalogsForPrint", inDto);
		Integer pageCount = (Integer) g4Reader.queryForObject("Demo.queryCatalogsForPrintForPageCount", inDto);
		String jsonString = encodeList2PageJson(catalogList, pageCount, G4Constants.FORMAT_DateTime);
		write(jsonString, response);
		return mapping.findForward(null);
	}

	/**
	 * 构造报表数据对象
	 */
	public ActionForward buildReportDataObject(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto dto = new BaseDto();
		dto.put("reportTitle", "北京市第一人民医院收费项目明细报表(演示)");
		dto.put("jbr", getSessionContainer(request).getUserInfo().getUsername());
		dto.put("jbsj", G4Utils.getCurrentTime());
		Dto inDto = (BaseDto)getSessionAttribute(request, "QUERYCATALOGS4PRINT_QUERYDTO");
		inDto.put("rownum", "100");
		List catalogList = null;
		if (G4Utils.defaultJdbcTypeOracle()) {
			catalogList = g4Reader.queryForList("Demo.queryCatalogsForPrintLimitRows", inDto);
		}else if (G4Utils.defaultJdbcTypeMysql()) {
			catalogList = g4Reader.queryForList("Demo.queryCatalogsForPrintLimitRowsMysql", inDto);
			for (int i = 0; i < catalogList.size(); i++) {
				Dto dto2 = (BaseDto)catalogList.get(i);
				dto2.put("zfbl", dto2.getAsBigDecimal("zfbl"));
			}
		}
		ReportData reportData = new ReportData();
		reportData.setParametersDto(dto);
		reportData.setFieldsList(catalogList);
		reportData.setReportFilePath("/report/jasper/demo/hisCatalogReport.jasper");
		getSessionContainer(request).setReportData("hisCatalogReport", reportData);
		return mapping.findForward(null);
	}
}
