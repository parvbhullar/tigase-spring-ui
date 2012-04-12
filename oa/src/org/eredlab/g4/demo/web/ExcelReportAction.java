package org.eredlab.g4.demo.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.eredlab.g4.ccl.datastructure.Dto;
import org.eredlab.g4.ccl.datastructure.impl.BaseDto;
import org.eredlab.g4.ccl.util.G4Utils;
import org.eredlab.g4.ccl.util.G4Constants;
import org.eredlab.g4.rif.report.excel.ExcelExporter;
import org.eredlab.g4.rif.report.excel.ExcelReader;
import org.eredlab.g4.rif.web.BaseAction;
import org.eredlab.g4.rif.web.CommonActionForm;

/**
 * Excel导入导出标准范例暨教程Action
 * 
 * @author XiongChun
 * @since 2010-10-13
 * @see BaseAction
 */
public class ExcelReportAction extends BaseAction {

	/**
	 * Excel导出页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward exportInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		removeSessionAttribute(request, "QUERYCATALOGS4EXPORT_QUERYDTO");
		return mapping.findForward("exportExcelView");
	}

	/**
	 * 查询医院收费目录
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryCatalogs4Export(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm aForm = (CommonActionForm) form;
		Dto inDto = aForm.getParamAsDto(request);
		super.setSessionAttribute(request, "QUERYCATALOGS4EXPORT_QUERYDTO", inDto);
		List catalogList = g4Reader.queryForPage("Demo.queryCatalogsForPrint", inDto);
		Integer pageCount = (Integer) g4Reader.queryForObject("Demo.queryCatalogsForPrintForPageCount", inDto);
		String jsonString = encodeList2PageJson(catalogList, pageCount, G4Constants.FORMAT_DateTime);
		write(jsonString, response);
		return mapping.findForward(null);
	}

	/**
	 * 导入Excel
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward importExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommonActionForm actionForm = (CommonActionForm) form;
		FormFile theFile = actionForm.getTheFile();
		String metaData = "xmid,xmmc,xmrj,gg,dw,jx,zfbl,cd,ggsj";
		ExcelReader excelReader = new ExcelReader(metaData, theFile.getInputStream());
		List list = excelReader.read(3, 1);
		super.setSessionAttribute(request, "importExcelList", list);
		setOkTipMsg("导入成功", response);
		return mapping.findForward(null);
	}

	/**
	 * 查询医院收费目录
	 * 
	 * @param
	 * @return
	 */
	public ActionForward queryCatalogs4Import(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List catalogList = (List) super.getSessionAttribute(request, "importExcelList");
		Integer pageCount = new Integer(1); // 演示数据默认在一页上显示
		String jsonString = encodeList2PageJson(catalogList, pageCount, G4Constants.FORMAT_Date);
		write(jsonString, response);
		return mapping.findForward(null);
	}

	/**
	 * Excel导出
	 * 
	 * @param
	 * @return
	 */
	public ActionForward exportExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Thread.sleep(2000);
		Dto parametersDto = new BaseDto();
		parametersDto.put("reportTitle", "北京市第一人民医院收费项目表");
		parametersDto.put("jbr", super.getSessionContainer(request).getUserInfo().getUsername());
		parametersDto.put("jbsj", G4Utils.getCurrentTime());
		Dto inDto = (BaseDto) super.getSessionAttribute(request, "QUERYCATALOGS4EXPORT_QUERYDTO");
		inDto.put("rownum", "500");
		List fieldsList = null;
		if (G4Utils.defaultJdbcTypeOracle()) {
			fieldsList = g4Reader.queryForList("Demo.queryCatalogsForPrintLimitRows", inDto);
		} else if (G4Utils.defaultJdbcTypeMysql()) {
			fieldsList = g4Reader.queryForList("Demo.queryCatalogsForPrintLimitRowsMysql", inDto);
		}
		parametersDto.put("countXmid", new Integer(fieldsList.size()));// 合计条数
		ExcelExporter excelExporter = new ExcelExporter();
		excelExporter.setTemplatePath("/report/excel/demo/hisCatalogReport.xls");
		excelExporter.setData(parametersDto, fieldsList);
		excelExporter.setFilename("北京市第一人民医院收费项目表.xls");
		excelExporter.export(request, response);
		return mapping.findForward(null);
	}

	/**
	 * Excel导出
	 * 
	 * @param
	 * @return
	 */
	public ActionForward exportExcel2(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto parametersDto = new BaseDto();
		parametersDto.put("reportTitle", "北京市第一人民医院收费项目表");
		parametersDto.put("jbr", super.getSessionContainer(request).getUserInfo().getUsername());
		parametersDto.put("jbsj", G4Utils.getCurrentTime());
		Dto inDto = (BaseDto) super.getSessionAttribute(request, "QUERYCATALOGS4EXPORT_QUERYDTO");
		inDto.put("rownum", "500");
		List fieldsList = null;
		if (G4Utils.defaultJdbcTypeOracle()) {
			fieldsList = g4Reader.queryForList("Demo.queryCatalogsForPrintLimitRows", inDto);
		} else if (G4Utils.defaultJdbcTypeMysql()) {
			fieldsList = g4Reader.queryForList("Demo.queryCatalogsForPrintLimitRowsMysql", inDto);
		}
		parametersDto.put("countXmid", new Integer(fieldsList.size()));// 合计条数
		ExcelExporter excelExporter = new ExcelExporter();
		excelExporter.setTemplatePath("/report/excel/demo/hisCatalogReport2.xls");
		excelExporter.setData(parametersDto, fieldsList);
		excelExporter.setFilename("北京市第一人民医院收费项目表.xls");
		excelExporter.export(request, response);
		return mapping.findForward(null);
	}

	/**
	 * Excel导出
	 * 
	 * @param
	 * @return
	 */
	public ActionForward exportExcel3(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Dto parametersDto = new BaseDto();
		parametersDto.put("reportTitle", "北京市第一人民医院收费项目表");
		parametersDto.put("jbr", super.getSessionContainer(request).getUserInfo().getUsername());
		parametersDto.put("jbsj", G4Utils.getCurrentTime());
		parametersDto.put("d1_1", "100.00");
		ExcelExporter excelExporter = new ExcelExporter();
		excelExporter.setTemplatePath("/report/excel/demo/hisCatalogReport3.xls");
		List fuckList = new ArrayList();
		excelExporter.setData(parametersDto, fuckList);
		excelExporter.setFilename("北京市第一人民医院收费项目表.xls");
		excelExporter.export(request, response);
		return mapping.findForward(null);
	}

	/**
	 * Excel导入页面初始化
	 * 
	 * @param
	 * @return
	 */
	public ActionForward importInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.removeSessionAttribute(request, "importExcelList");
		return mapping.findForward("importExcelView");
	}

}
