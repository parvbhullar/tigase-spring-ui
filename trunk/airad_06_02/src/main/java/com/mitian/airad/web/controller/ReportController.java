package com.mitian.airad.web.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jofc2.OFCException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.mitian.airad.CommonDef;
import com.mitian.airad.Constants;
import com.mitian.airad.ErrorMessages;
import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.common.exception.NotLogonException;
import com.mitian.airad.model.CoreAd;
import com.mitian.airad.model.CoreAdGroup;
import com.mitian.airad.model.CoreAgentRelation;
import com.mitian.airad.model.CoreApp;
import com.mitian.airad.model.CoreCampaign;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.model.CoreReport;
import com.mitian.airad.model.StatAd;
import com.mitian.airad.model.StatAgent;
import com.mitian.airad.model.StatApp;
import com.mitian.airad.service.AdGroupService;
import com.mitian.airad.service.AdService;
import com.mitian.airad.service.AgentRelationService;
import com.mitian.airad.service.AppService;
import com.mitian.airad.service.CampaignService;
import com.mitian.airad.service.ReportService;
import com.mitian.airad.service.StatAdService;
import com.mitian.airad.service.StatAgentService;
import com.mitian.airad.service.StatAppService;
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.utils.ReportDateUtil;
import com.mitian.airad.utils.StringUtil;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.BaseRole;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.ReportForm;

/**
 * CoreCampaign.java
 * 
 * @author Administrator
 */
@Controller
@RequestMapping("/report.do")
@RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_ADVERTISERS, RoleFactory.ROLE_DEVELOPERS,
        RoleFactory.ROLE_GENERAL, RoleFactory.ROLE_OSS_SALES})
public class ReportController extends AbstractController {
    @Autowired
    private CampaignService campaignService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private AppService appService;
    @Autowired
    private AgentRelationService agentRelationService;
    @Autowired
    private AdGroupService adGroupService;
    @Autowired
    private AdService adService;
    @Autowired
    private StatAdService statAdService;
    @Autowired
    private StatAppService statAppService;
    @Autowired
    private StatAgentService statAgentService;

    /*
     * ================================================tangwenjun=====================================================
     */
    @RequestMapping(params = "action=listReport")
    public ModelAndView listReport(ReportForm form, HttpServletRequest request) throws NotLogonException {
        ModelAndView mv = new ModelAndView("report/report_list", Constants.DEFAULT_COMMAND, form);
        // 默认降序
        if (StringUtils.isEmpty(form.getAsce())) {
            form.setDesc("desc");
            mv.addObject("sortFlag", "flag");
        }
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        CoreReport report = null;
        report = form.getReport();
        report.setMemberId(memberId);
        String roleTypes = CookieUtils.getCookieValue(request, CookieUtils.COOKIE_KEY_CURRENT_ROLE_TYPE);
        BaseRole role = memberInfo.getRole();
        String dType = role.getReportTypes(roleTypes);
        // 封装数据
        Map<String, String> params = new HashMap<String, String>();
        params.put("descFlag", form.getDesc());
        params.put("asceFlag", form.getAsce());
        params.put("pageSize", form.getPageSize());
        params.put("currentPage", form.getCurrentPage());
        params.put("devType", dType);
        params.put("memberId", memberId.toString());
        report.setDevType(dType);
        int k = reportService.queryCount(report);
        form.setTotalCount(Integer.toString(k));
        List<CoreReport> listBean = reportService.queryList(params);
        form.setListBean(listBean);

        // 判断是否有升序
        if (!StringUtil.isEmpty(form.getAsce())) {
            mv.addObject("asce", form.getAsce());
        }
        form.setCoreMemberInfo(memberInfo);
        mv.addObject("p", form);
        return mv;
    }

    /**
     * 添加报表跳转页面
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=add")
    public ModelAndView add(ReportForm form) {
        ModelAndView mv = new ModelAndView();
        // 获得报告的类型，跳转不同页面
        String flag = form.getFlag();
        if (CommonDef.report.REPORT_AD.equals(flag)) { // 活动、广告组、广告报表
            mv = new ModelAndView("report/campaign_report_add", Constants.DEFAULT_COMMAND, form);
            mv.addObject("form", form);
            return mv;
        }
        else if (CommonDef.report.REPORT_DEVELOP.equals(flag)) { // 应用报表
            mv = new ModelAndView("report/app_report_add", Constants.DEFAULT_COMMAND, form);
            mv.addObject("form", form);
            return mv;
        }
        else if (CommonDef.report.REPORT_AGENT.equals(flag)) { // 代理商报表
            mv = new ModelAndView("report/agentRelation_report_add", Constants.DEFAULT_COMMAND, form);
            mv.addObject("form", form);
            return mv;
        }

        return mv;
    }

    /**
     * 添加报表
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doAdd", method = RequestMethod.POST)
    public ModelAndView doAdd(ReportForm form, HttpServletRequest request) {
        CoreReport report = form.getReport();
        Long memberId = getLogonMemberId();
        report.setMemberId(memberId);
        report.setAddOper(getLogonEmail());
        // 封装信息到map
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("report", report);
        params.put("reportStartTime", form.getReportStartTime());
        params.put("reportEndTime", form.getReportEndTime());
        params.put("presetValue", form.getPresetValue());
        params.put("dateType", report.getDateType());
        reportService.txAddOrEditReport(params, memberId);
        ModelAndView mv = new ModelAndView("redirect:report.do?action=listReport", Constants.DEFAULT_COMMAND, form);

        request.getSession().setAttribute("savereport", "ok");// 标志
        return mv;
    }

    /**
     * 报表修改跳转页面
     * 
     * @param form
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=edit")
    public ModelAndView edit(ReportForm form) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("report/campaign_report_edit", Constants.DEFAULT_COMMAND, form);
        // 根据reportId查找报表
        String reportId = form.getReportId();
        Long memberId = getLogonMemberId();
        if (StringUtils.isEmpty(reportId)) {
            reportId = form.getReport().getReportId().toString();
        }
        CoreReport report = new CoreReport();
        if (CommonDef.report.REPORT_CANCLE.equals(form.getCancleFlag())) {
            report = form.getReport();
        }
        else {
            report = reportService.queryById(StringUtil.stringToInteger(reportId), memberId);
        }
        String reportStartTime = null;
        String reportEndTime = null;
        if (report.getReportStartTime() != null) {
            reportStartTime = StringUtil.getStringDateYear(report.getReportStartTime());
        }
        if (report.getReportEndTime() != null) {
            reportEndTime = StringUtil.getStringDateYear(report.getReportEndTime());
        }
        form.setReportStartTime(reportStartTime);
        form.setReportEndTime(reportEndTime);
        String reportType = report.getReportType();
        // 判断是否存在修改报错挑战标志
        String editFlag = form.getEditFlag();
        if (CommonDef.report.REPORT_ADD_AD.equals(reportType)
        // || CommonDef.report.REPORT_ADD_CAM.equals(reportType)
                || CommonDef.report.REPORT_ADD_ADGROUP.equals(reportType)) { // 活动、广告组、广告报表
            form.setFlag("0");
            form.setReport(report);
            mv.addObject("form", form);
            return mv;
        }
        else if (CommonDef.report.REPORT_ADD_DEVELOP.equals(reportType)) { // 应用报表
            mv = new ModelAndView("report/app_report_edit", Constants.DEFAULT_COMMAND, form);
            if (!StringUtils.isEmpty(editFlag)) {
                report = form.getReport();
            }
            form.setReport(report);
            String dateType = report.getDateType();// 2昨天 3过去7天 4过去30天 5上一个月 6上一个季度 7本月开始到现在 8本季开始到现在
            if (!StringUtils.isEmpty(dateType)) {
                StringUtil.stringToInteger(dateType);
                if (StringUtil.stringToInteger(dateType) > 0) {
                    form.setPresetValue(dateType);
                    report.setDateType(CommonDef.report.REPORT_DATE_TYPE_CHOOSE);
                }
                if (StringUtil.stringToInteger(dateType) == 0) {
                    report.setDateType(CommonDef.report.REPORT_DATE_TYPE_SELF);
                }
            }
            form.setFlag("1");
            form.setEditFlag(CommonDef.report.REPORT_EDIT);
            mv.addObject("form", form);
            return mv;
        }
        else { // 广告商报表
            mv = new ModelAndView("report/agentRelation_report_edit", Constants.DEFAULT_COMMAND, form);
            if (!StringUtils.isEmpty(editFlag)) {
                report = form.getReport();
            }
            form.setReport(report);
            form.setFlag("2");
            mv.addObject("form", form);
            return mv;
        }

    }

    /**
     * 报表查看
     * 
     * @param form
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=show")
    public ModelAndView show(ReportForm form) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView();
        Long memberId = getLogonMemberId();
        CoreReport report = new CoreReport();
        if (!StringUtils.isEmpty(form.getReportId())) {
            report = reportService.queryById(StringUtil.stringToInteger(form.getReportId()), memberId);
        }

        String reportType = report.getReportType();
        // 校验
        String reportName = report.getReportName();
        if (reportName.length() > 40 || StringUtils.isEmpty(reportName)) {
            // form.setAddReortError(reportName);
            form.getErrors().put("reportNameError", ErrorMessages.REPORT_REPORT_ERROR);
        }
        if (CommonDef.report.REPORT_ADD_AD.equals(reportType)
        // || CommonDef.report.REPORT_ADD_CAM.equals(reportType)
                || CommonDef.report.REPORT_ADD_ADGROUP.equals(reportType)) { // 活动、广告组、广告报表
            mv = new ModelAndView("report/report", Constants.DEFAULT_COMMAND, form);
        }
        else if (CommonDef.report.REPORT_ADD_DEVELOP.equals(reportType)) { // 应用报表
            mv = new ModelAndView("report/appReport", Constants.DEFAULT_COMMAND, form);
        }
        else { // 广告商报表
            mv = new ModelAndView("report/advReport", Constants.DEFAULT_COMMAND, form);
        }

        if (!form.getErrors().isEmpty()) {
            mv.addObject("form", form);
            return mv;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        // 非活动类型报表
        if (!CommonDef.report.REPORT_ADD_CAM.equals(report.getDateType())) {
            String pre = report.getDateType();
            // 选择范围
            form.setPresetValue(pre);
            // 设置时间类型，
            report.setDateType("1");
            // 设置时间(特殊处理)
            params.put("report", form.getReport());
            params.put("reportStartTime", form.getReportStartTime());
            params.put("reportEndTime", form.getReportEndTime());
            params.put("presetValue", form.getPresetValue());
            params.put("dateType", report.getDateType());
            CoreReport r = reportService.getReport(params);
            report.setReportEndTime(r.getReportEndTime());
            report.setReportStartTime(r.getReportStartTime());
        }

        String reportStartTime = StringUtil.getStringDateYear(report.getReportStartTime());
        String reportEndTime = StringUtil.getStringDateYear(report.getReportEndTime());

        form.setReportStartTime(reportStartTime);
        form.setReportEndTime(reportEndTime);
        form.setReport(report);

        if (report.getDateType().equals("0")) {
            form.setPresetValue("");
        }

        mv.addObject("form", form);
        mv.addObject("report", report);
        // 查看标志
        // mv.addObject("show", "show");
        return mv;

    }

    /**
     * 报表修改
     * 
     * @param form
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=doEdit", method = RequestMethod.POST)
    public ModelAndView doEdit(ReportForm form) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView();
        // 添加修改人
        CoreReport report = form.getReport();
        String type = report.getReportType();
        Long memberId = getLogonMemberId();
        String reportName = report.getReportName();
        // 校验
        if (StringUtils.isEmpty(report.getReportCon())) {
            String flag = form.getFlag();
            if ("0".equals(flag)) {
                form.getErrors().put("reportConErr", ErrorMessages.REPORT_REPORTNAME_CAMPAIGN_ERROR);
            }
            else if ("1".equals(flag)) {
                form.getErrors().put("reportConErr", ErrorMessages.REPORT_REPORTNAME_ERROR);
            }
            else {
                form.getErrors().put("reportConErr", ErrorMessages.REPORT_REPORTNAME_AVG_ERROR);
            }
            form.setFlag(flag);
        }
        if (StringUtils.isEmpty(form.getPresetValue())
                && (StringUtils.isEmpty(form.getReportStartTime()) || StringUtils.isEmpty(form.getReportEndTime()))
                || StringUtils.isEmpty(report.getDateType())) {
            form.getErrors().put("reportTypeErr", ErrorMessages.REPORT_REPORTTYPE_ERROR);
        }
        else if (!StringUtils.isEmpty(report.getDateType())) {
            if (report.getDateType().equals(CommonDef.dateType.ONESELF)) {
                Date reportStartTime = null;
                Date reportEndTime = null;
                if (!StringUtils.isEmpty(form.getReportStartTime())) {
                    reportStartTime = StringUtil.getDateY(form.getReportStartTime());
                }
                if (!StringUtils.isEmpty(form.getReportEndTime())) {
                    reportEndTime = StringUtil.getDateY(form.getReportEndTime());
                }
                if (reportStartTime.compareTo(reportEndTime) == 1) {
                    form.getErrors().put("reportTimeErr", ErrorMessages.REPORT_REPORTTIME_ERROR);
                }
            }
        }
        if (reportName.length() > 40 || StringUtils.isEmpty(reportName)) {
            form.getErrors().put("reportNameError", ErrorMessages.REPORT_REPORT_ERROR);
        }
        if (!form.getErrors().isEmpty()) {
            if ("0".equals(type)) {
                mv = new ModelAndView("report/campaign_report_edit", Constants.DEFAULT_COMMAND, form);
            }
            else if ("1".equals(type)) {
                mv = new ModelAndView("report/app_report_edit", Constants.DEFAULT_COMMAND, form);
            }
            else {
                mv = new ModelAndView("report/agentRelation_report_edit", Constants.DEFAULT_COMMAND, form);
            }
            mv.addObject("form", form);
            return mv;
        }

        report.setUpdOper(getLogonEmail());
        // 封装信息到map
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("report", form.getReport());
        params.put("reportStartTime", form.getReportStartTime());
        params.put("reportEndTime", form.getReportEndTime());
        params.put("presetValue", form.getPresetValue());
        if (report.getDateType().equals("0")) {
            form.setPresetValue("");
        }
        params.put("dateType", report.getDateType());
        reportService.txAddOrEditReport(params, memberId);
        if (report.getReportId() != null) {
            form.setReportId(report.getReportId().toString());
        }
        return show(form);
    }

    /**
     * 报表删除
     * 
     * @param form
     * @return
     * @throws NotLogonException
     */
    @RequestMapping(params = "action=doDelete", method = RequestMethod.POST)
    public ModelAndView doDelete(ReportForm form, HttpServletRequest request) throws NotLogonException {
        List<Integer> deleteListId = form.getDeleteList();
        if (null == deleteListId) {
            form.getErrors().put("DELETE_ERROR", ErrorMessages.DELETE_ERROR);
            return listReport(form, request);
        }
        reportService.txDeleteReport(deleteListId, getLogonMemberId());
        ModelAndView mv = new ModelAndView("redirect:report.do?action=listReport", Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /*
     * ================================================xiaxianli=====================================================
     */
    /**
     * xml
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doxmlReport")
    @RoleAuthority({RoleFactory.ROLE_ALL})
    public ModelAndView doxmlReport(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("e_ids");
        String type = request.getParameter("e_type");
        // 报表内容类型（1详细0汇总）
        String listType = request.getParameter("e_reportStatus");
        // 时间范围
        CoreReport report = new CoreReport();
        getParamter(report, request);
        Date start = report.getReportStartTime();
        Date end = report.getReportEndTime();

        Element root = new Element("list");
        String labelName = "";
        String fileName = "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sf.format(new Date());
        Map<String, String> params = new HashMap<String, String>();
        params.put("startTime", ReportDateUtil.getDate("yyyy-MM-dd", start));
        params.put("endTime", ReportDateUtil.getDate("yyyy-MM-dd", end));
        params.put("memberId", getLogonMemberId().toString());
        // 默认给降序
        params.put("descFlag", "true");
        CoreMemberInfo coreMemberInfo = getMemberInfo();
        Long memberId = coreMemberInfo.getMemberId();
        BaseRole role = coreMemberInfo.getRole();
        if (CommonDef.report.REPORT_ADD_AD.equals(type) || CommonDef.report.REPORT_ADD_ADGROUP.equals(type)
        // || CommonDef.report.REPORT_ADD_CAM.equals(type)
        ) {
            List<StatAd> reportlist = new ArrayList<StatAd>();
            ids = convertIds(ids);
            // if (CommonDef.report.REPORT_ADD_CAM.equals(type)) {
            // params.put("inCampaignId", ids);
            // labelName = "campaign";
            // fileName = "活动报告(" + time + ").xml";
            // if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
            // reportlist = statAdService.selectcampaignIdDetailList(params);
            // }
            // else {
            // reportlist = statAdService.selectcampaignIdTotalList(params);
            // }
            // }
            // else
            if (CommonDef.report.REPORT_ADD_ADGROUP.equals(type)) {
                params.put("inAdGroupId", ids);
                labelName = "adGroup";
                fileName = "广告组报告(" + time + ").xml";
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                    reportlist = statAdService.selectadGroupDetailList(params);
                }
                else {
                    reportlist = statAdService.selectadGroupTotalList(params);
                }
            }
            else if (CommonDef.report.REPORT_ADD_AD.equals(type)) {
                params.put("inAdId", ids);
                labelName = "ad";
                fileName = "广告报告(" + time + ").xml";
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                    reportlist = statAdService.selectadDetailList(params);
                }
                else {
                    reportlist = statAdService.selectadTotalList(params);
                }
            }

            // 出列日期为 0 的队列，包括 详情和汇总
            List<StatAd> keyNames = null;
            try {
                keyNames = reportService.getStaKeyName(type, memberId, ids);
            }
            catch (InvalidInfoException e) {
                getLogger().error("获取报表数据错误 会员id: " + memberId + "报表id:  " + ids + "报表类型: " + type);
            }
            reportService.dealEmptyDateList(type, ids, getLogonMemberId(), listType, 15, 0, params.get("startTime")
                    .toString(), params.get("endTime").toString(), true, reportlist, keyNames, null);
            for (int i = 0; i < reportlist.size(); i++) {
                StatAd s = reportlist.get(i);
                Element elements = new Element(labelName);
                elements.setAttribute("id", "" + i);
                String n = "";
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                    if ("0".equals(type)) {
                        n = s.getCampaignName();
                    }
                    else if ("1".equals(type)) {
                        n = s.getAdGroupName();
                    }
                    else {
                        n = s.getAdName();
                    }
                    elements.addContent(new Element("name").setText(n));
                }
                elements.addContent(new Element("addTime").setText(sf.format(s.getAddTime())));
                elements.addContent(new Element("showNum").setText(s.getShowNum().toString()));
                elements.addContent(new Element("clickNum").setText(s.getClickNum().toString()));
                elements.addContent(new Element("clickRate").setText(s.getClickRate().toString()));
                if (!(role.isShopper() || role.isOssSales())) {
                    elements.addContent(new Element("clickMoney").setText(s.getClickMoney().toString()));
                }
                root.addContent(elements);
            }
        }
        else if (CommonDef.report.REPORT_ADD_DEVELOP.equals(type)) { // 应用程序
            params.put("inAppId", ids);
            labelName = "app";
            fileName = "应用程序报告(" + time + ").xml";
            List<StatApp> reportlist = new ArrayList<StatApp>();
            if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                reportlist = statAppService.selectappDetailList(params);
            }
            else {
                reportlist = statAppService.selectappTotalList(params);
            }

            reportService.dealEmptyDateList(type, ids, memberId, listType, 15, 0, params.get("startTime").toString(),
                    params.get("endTime").toString(), true, reportlist, null, reportService.getAppList(ids, memberId));
            for (int i = 0; i < reportlist.size(); i++) {
                StatApp s = reportlist.get(i);
                Element elements = new Element(labelName);

                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                    elements.setAttribute("id", s.getAppCode());
                    elements.addContent(new Element("name").setText(s.getAppName()));
                }
                elements.addContent(new Element("addTime").setText(sf.format(s.getAddTime())));
                elements.addContent(new Element("maxNum").setText(s.getMaxNum().toString()));
                elements.addContent(new Element("showNum").setText(s.getShowNum().toString()));
                elements.addContent(new Element("clickNum").setText(s.getClickNum().toString()));
                elements.addContent(new Element("offer").setText(s.getOffer().setScale(2, BigDecimal.ROUND_DOWN)
                        .toString()));
                elements.addContent(new Element("putRate").setText(s.getPutRate().setScale(2, BigDecimal.ROUND_DOWN)
                        .toString()));
                elements.addContent(new Element("clickRate").setText(s.getClickRate()
                        .setScale(2, BigDecimal.ROUND_DOWN).toString()));
                root.addContent(elements);
            }
        }
        else if (CommonDef.report.REPORT_ADD_AGENT.equals(type)) { // 代理商
            fileName = "广告商报告(" + time + ").xml";
            labelName = "adv";
            params.put("inAgentId", ids);
            List<StatAgent> reportlist = new ArrayList<StatAgent>();
            if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                reportlist = statAgentService.selectAngenDetailList(params);
            }
            else {
                reportlist = statAgentService.selectAngenTotalList(params);
            }

            for (int i = 0; i < reportlist.size(); i++) {
                StatAgent a = reportlist.get(i);
                Element elements = new Element(labelName);
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                    elements.addContent(new Element("advertiserName").setText(a.getAdvertiserName()));
                }
                elements.addContent(new Element("createTime").setText(sf.format(a.getCreateTime())));
                elements.addContent(new Element("showNum").setText(a.getClickNum().toString()));
                elements.addContent(new Element("clickNum").setText(a.getClickNum().toString()));
                elements.addContent(new Element("offer").setText(a.getOffer().setScale(2, BigDecimal.ROUND_DOWN)
                        .toString()));
                root.addContent(elements);
            }
        }

        Document doc = new Document(root);
        XMLOutputter xmlOut = new XMLOutputter();
        try {
            // 处理乱码
            fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            OutputStream out = response.getOutputStream();
            xmlOut.output(doc, out);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * csv
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=docsvReport")
    @RoleAuthority({RoleFactory.ROLE_ALL})
    public ModelAndView docsvReport(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("e_ids");
        String type = request.getParameter("e_type");
        // 报表内容类型（1详细0汇总）
        String listType = request.getParameter("e_reportStatus");

        CoreReport report = new CoreReport();
        getParamter(report, request);
        Date start = report.getReportStartTime();
        Date end = report.getReportEndTime();

        CoreMemberInfo coreMemberInfo = getMemberInfo();
        Long memberId = coreMemberInfo.getMemberId();
        BaseRole role = coreMemberInfo.getRole();

        LinkedHashMap map = new LinkedHashMap();
        List exportData = new ArrayList<Map>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sf.format(new Date());
        String fileName = "";// users.csv
        Map<String, String> params = new HashMap<String, String>();
        params.put("startTime", ReportDateUtil.getDate("yyyy-MM-dd", start));
        params.put("endTime", ReportDateUtil.getDate("yyyy-MM-dd", end));
        params.put("memberId", memberId.toString());
        // 默认给降序
        params.put("descFlag", "true");

        try {
            if (CommonDef.report.REPORT_ADD_AD.equals(type) || CommonDef.report.REPORT_ADD_ADGROUP.equals(type)
            // || CommonDef.report.REPORT_ADD_CAM.equals(type)
            ) {
                List<StatAd> reportlist = new ArrayList<StatAd>();
                ids = convertIds(ids);
                // if (CommonDef.report.REPORT_ADD_CAM.equals(type)) {
                // params.put("inCampaignId", ids);
                // fileName = "活动报告(" + time + ").csv";
                // if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                // reportlist = statAdService.selectcampaignIdDetailList(params);
                // }
                // else {
                // reportlist = statAdService.selectcampaignIdTotalList(params);
                // }
                // }
                // else
                if (CommonDef.report.REPORT_ADD_ADGROUP.equals(type)) {
                    fileName = "广告组报告(" + time + ").csv";
                    params.put("inAdGroupId", ids);
                    if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                        reportlist = statAdService.selectadGroupDetailList(params);
                    }
                    else {
                        reportlist = statAdService.selectadGroupTotalList(params);
                    }
                }
                else if (CommonDef.report.REPORT_ADD_AD.equals(type)) {
                    params.put("inAdId", ids);
                    fileName = "广告报告(" + time + ").csv";
                    if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                        reportlist = statAdService.selectadDetailList(params);
                    }
                    else {
                        reportlist = statAdService.selectadTotalList(params);
                    }
                }

                reportService.dealEmptyDateList(type, ids, memberId, listType, 15, 0, params.get("startTime")
                        .toString(), params.get("endTime").toString(), true, reportlist, reportService.getStaKeyName(
                        type, memberId, ids), null);

                String name = "";
                for (int i = 0; i < reportlist.size(); i++) {
                    StatAd s = reportlist.get(i);
                    Map<String, Object> row = new LinkedHashMap<String, Object>();
                    if ("0".equals(type)) {
                        name = s.getCampaignName();
                    }
                    else if ("1".equals(type)) {
                        name = s.getAdGroupName();
                    }
                    else {
                        name = s.getAdName();
                    }
                    if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                        row.put("1", name);
                    }
                    row.put("2", sf.format(s.getAddTime()));
                    row.put("3", s.getShowNum());
                    row.put("4", s.getClickNum());
                    row.put("5", s.getClickRate());
                    if (!(role.isShopper() || role.isOssSales())) {
                        row.put("6", s.getClickMoney());
                    }
                    exportData.add(row);
                }
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                    map.put("1", CommonDef.initData.REPORT_CAM_NAME);
                }
                map.put("2", CommonDef.initData.REPORT_CAM_TIME);
                map.put("3", CommonDef.initData.REPORT_CAM_SUMSHOW);
                map.put("4", CommonDef.initData.REPORT_CAM_SUMCLICK);
                map.put("5", CommonDef.initData.REPORT_CAM_CLICLRATE);
                if (!(role.isShopper() || role.isOssSales())) {
                    map.put("6", CommonDef.initData.REPORT_CAM_CLICKMONEY);
                }

            }
            else if (CommonDef.report.REPORT_ADD_DEVELOP.equals(type)) { // 应用程序报告
                fileName = "应用程序报告(" + time + ").csv";
                params.put("inAppId", ids);
                List<StatApp> reportlist = new ArrayList<StatApp>();
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                    reportlist = statAppService.selectappDetailList(params);
                }
                else {
                    reportlist = statAppService.selectappTotalList(params);
                }

                reportService.dealEmptyDateList(type, ids, memberId, listType, 15, 0, params.get("startTime")
                        .toString(), params.get("endTime").toString(), true, reportlist, null, reportService
                        .getAppList(ids, memberId));
                for (int i = 0; i < reportlist.size(); i++) {
                    StatApp s = reportlist.get(i);
                    Map<String, String> row = new LinkedHashMap<String, String>();
                    if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                        row.put("1", s.getAppName());
                    }
                    row.put("2", sf.format(s.getAddTime()));
                    row.put("3", s.getMaxNum().toString());
                    row.put("4", s.getShowNum().toString());
                    row.put("5", s.getClickNum().toString());
                    row.put("6", s.getOffer().setScale(2, BigDecimal.ROUND_DOWN).toString());
                    row.put("7", s.getPutRate().setScale(2, BigDecimal.ROUND_DOWN).toString());
                    row.put("8", s.getClickRate().setScale(2, BigDecimal.ROUND_DOWN).toString());
                    exportData.add(row);
                }
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                    map.put("1", CommonDef.initData.REPORT_APP_NAME);
                }
                map.put("2", CommonDef.initData.REPORT_APP_TIME);
                map.put("3", CommonDef.initData.REPORT_APP_REQNUM);
                map.put("4", CommonDef.initData.REPORT_APP_SHOWNUM);
                map.put("5", CommonDef.initData.REPORT_APP_CLICKNUM);
                map.put("6", CommonDef.initData.REPORT_APP_TOTALMONY);
                map.put("7", CommonDef.initData.REPORT_APP_CLICKRATE);
                map.put("8", CommonDef.initData.REPORT_APP_CLICKPUT);

            }
            else if (CommonDef.report.REPORT_ADD_AGENT.equals(type)) { // 广告商(代理商)报告
                fileName = "广告商报告(" + time + ").csv";
                params.put("inAgentId", ids);
                List<StatAgent> reportlist = new ArrayList<StatAgent>();
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                    reportlist = statAgentService.selectAngenDetailList(params);
                }
                else {
                    reportlist = statAgentService.selectAngenTotalList(params);
                }
                for (int i = 0; i < reportlist.size(); i++) {
                    StatAgent a = reportlist.get(i);
                    Map<String, String> row = new LinkedHashMap<String, String>();
                    if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                        row.put("1", a.getAdvertiserName());
                    }
                    row.put("2", sf.format(a.getCreateTime()));
                    row.put("3", a.getShowNum().toString());
                    row.put("4", a.getClickNum().toString());
                    row.put("5", a.getOffer().setScale(2, BigDecimal.ROUND_DOWN).toString());
                    exportData.add(row);
                }
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(listType)) {
                    map.put("1", CommonDef.initData.REPORT_ADV_NAME);
                }
                map.put("2", CommonDef.initData.REPORT_ADV_TIME);
                map.put("3", CommonDef.initData.REPORT_ADV_SHOWNUM);
                map.put("4", CommonDef.initData.REPORT_ADV_CLICKNUM);
                map.put("5", CommonDef.initData.REPORT_ADV_OFFER);

            }
            // 处理乱码
            fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            OutputStream out = response.getOutputStream();
            writeCsv(map, exportData, out);
        }
        catch (Exception e) {
            getLogger().error("docsvReport error", e);
        }
        return null;
    }

    /**
     * 生成csv文件
     * 
     * @param title 标题
     * @param exportData 数据
     * @param out 输出对象
     */
    @SuppressWarnings("unchecked")
    public void writeCsv(LinkedHashMap title, List exportData, OutputStream out) {
        BufferedWriter csvFileOutputStream = null;
        try {
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(out, "GB2312"), 1024);
            // 写入文件头部
            for (Iterator propertyIterator = title.entrySet().iterator(); propertyIterator.hasNext();) {
                Entry propertyEntry = (Entry) propertyIterator.next();
                if (propertyEntry == null) {
                    return;
                }
                Object entry = propertyEntry.getValue();
                String head = StringUtils.EMPTY;
                if (entry != null) {
                    head = entry.toString();
                }
                csvFileOutputStream.write("\"" + head + "\"");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
            csvFileOutputStream.newLine();
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
                Object row = iterator.next();
                for (Iterator propertyIterator = title.entrySet().iterator(); propertyIterator.hasNext();) {
                    Entry propertyEntry = (Entry) propertyIterator.next();
                    if (row == null || propertyEntry == null) {
                        return;
                    }
                    String txt = propertyEntry.getKey() == null ? "" : propertyEntry.getKey().toString();
                    String text = BeanUtils.getProperty(row, txt) == null ? "" : BeanUtils.getProperty(row, txt);
                    csvFileOutputStream.write("\"" + text + "\"");
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(",");
                    }
                }
                if (iterator.hasNext()) {
                    csvFileOutputStream.newLine();
                }
            }
            csvFileOutputStream.flush();
        }
        catch (Exception e) {
            getLogger().error("writeCsv error", e);
        }
        finally {
            try {
                csvFileOutputStream.close();
            }
            catch (Exception e) {
                getLogger().error("writeCsv error", e);
            }
        }
    }

    /**
     * 获得导出的参数
     * 
     * @param report
     * @param request
     */
    public void getParamter(CoreReport report, HttpServletRequest request) {
        // 时间范围
        String start = request.getParameter("e_reportStartTime");
        String end = request.getParameter("e_reportEndTime");
        String se = request.getParameter("e_presetValue");
        String dateType = request.getParameter("e_dateType");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("presetValue", se);
        params.put("reportStartTime", start);
        params.put("reportEndTime", end);
        params.put("dateType", dateType);
        params.put("report", report);
        report = reportService.getReport(params);

    }

    /**
     * 店铺主报表
     * 
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(params = "action=shop", method = RequestMethod.GET)
    @RoleAuthority({RoleFactory.ROLE_SHOPPER, RoleFactory.ROLE_OSS_SALES})
    public ModelAndView shopView(ReportForm form) {
        CoreReport report = form.getReport();

        // 活动报告 ; 广告组报告 ; 广告报告

        ModelAndView mv = new ModelAndView("report/shop_report", Constants.DEFAULT_COMMAND, form);
        List<CoreAdGroup> groupList = adGroupService.findAllGroupByMemberId(getLogonMemberId());
        StringBuilder ids = new StringBuilder();
        for (CoreAdGroup a : groupList) {
            ids.append(a.getAdGroupId());
            ids.append(",");
        }

        if (ids.length() > 0) {
            ids = ids.deleteCharAt(ids.length() - 1);
        }

        // 广告组id
        report.setReportCon(ids.toString());
        // 报表类型 广告组
        report.setReportType(CommonDef.report.REPORT_ADD_ADGROUP);
        // 事件类型 来自下拉框
        report.setDateType("1");
        // 形式 汇总 详情
        report.setReportStatus("0");

        form.setReport(report);

        setClassValue(report, form);

        mv.addObject("report", report);
        mv.addObject("form", form);
        return mv;
    }

    @RequestMapping(params = "action=showAdReport", method = RequestMethod.GET)
    public ModelAndView showAdReport(ReportForm form) {
        ModelAndView mv = new ModelAndView();
        CoreReport report = new CoreReport();

        // 类型：广告报告
        report.setReportType("2");
        // 时间：过去7天
        report.setDateType("1");
        // 报表形式：汇总报告
        report.setReportStatus("0");
        // 广告ID
        report.setReportCon(form.getAdId());
        form.setDateType("1");
        form.setPresetValue("3");
        form.setReport(report);
        // 活动报告 ; 广告组报告 ; 广告报告
        mv = new ModelAndView("report/report", Constants.DEFAULT_COMMAND, form);
        setClassValue(report, form);
        // try {
        // report.setReportStartTime(ReportDateUtil.getDate(form.getReportStartTime(), "yyyy-MM-dd"));
        // report.setReportEndTime(ReportDateUtil.getDate(form.getReportEndTime(), "yyyy-MM-dd"));
        // }
        // catch (ParseException e) {
        // e.printStackTrace();
        // }
        mv.addObject("report", report);
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 管理报告
     * 
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(params = "action=doReport", method = RequestMethod.POST)
    public ModelAndView doReport(ReportForm form) {
        ModelAndView mv = new ModelAndView();
        CoreReport report = form.getReport();
        // 报告类型（xx报告）
        String type = report.getReportType();
        // 校验
        if (StringUtils.isEmpty(report.getReportCon())) {
            String flag = form.getFlag();
            if ("0".equals(flag)) {
                form.getErrors().put("reportConErr", ErrorMessages.REPORT_REPORTNAME_ERROR);
            }
            else if ("1".equals(flag)) {
                form.getErrors().put("reportConErr", ErrorMessages.REPORT_REPORTNAME_CAMPAIGN_ERROR);
            }
            else {
                form.getErrors().put("reportConErr", ErrorMessages.REPORT_REPORTNAME_AVG_ERROR);
            }
        }
        else if (StringUtils.isEmpty(form.getPresetValue())
                && (StringUtils.isEmpty(form.getReportStartTime()) || StringUtils.isEmpty(form.getReportEndTime()))
                || StringUtils.isEmpty(report.getDateType())) {
            form.getErrors().put("reportTypeErr", ErrorMessages.REPORT_REPORTTYPE_ERROR);
        }
        else if (!StringUtils.isEmpty(report.getDateType())) {
            if (report.getDateType().equals(CommonDef.dateType.ONESELF)) {
                Date reportStartTime = null;
                Date reportEndTime = null;
                if (!StringUtils.isEmpty(form.getReportStartTime())) {
                    reportStartTime = StringUtil.getDateY(form.getReportStartTime());
                }
                if (!StringUtils.isEmpty(form.getReportEndTime())) {
                    reportEndTime = StringUtil.getDateY(form.getReportEndTime());
                }
                if (reportStartTime.compareTo(reportEndTime) == 1) {
                    form.getErrors().put("reportTimeErr", ErrorMessages.REPORT_REPORTTIME_ERROR);
                }
            }
        }

        if (!form.getErrors().isEmpty()) {
            if (CommonDef.report.REPORT_ADD_AD.equals(type) || CommonDef.report.REPORT_ADD_ADGROUP.equals(type)
            // || CommonDef.report.REPORT_ADD_CAM.equals(type)
            ) {
                mv = new ModelAndView("report/campaign_report_add", Constants.DEFAULT_COMMAND, form);
            }
            else if (CommonDef.report.REPORT_ADD_DEVELOP.equals(type)) {
                mv = new ModelAndView("report/app_report_add", Constants.DEFAULT_COMMAND, form);
            }
            else {
                mv = new ModelAndView("report/agentRelation_report_add", Constants.DEFAULT_COMMAND, form);
            }
            mv.addObject("form", form);
            return mv;
        }

        // 活动报告 ; 广告组报告 ; 广告报告
        if (CommonDef.report.REPORT_ADD_AD.equals(type) || CommonDef.report.REPORT_ADD_ADGROUP.equals(type)
        // || CommonDef.report.REPORT_ADD_CAM.equals(type)
        ) {
            mv = new ModelAndView("report/report", Constants.DEFAULT_COMMAND, form);
        }
        else if (CommonDef.report.REPORT_ADD_DEVELOP.equals(type)) { // 应用程序报告
            mv = new ModelAndView("report/appReport", Constants.DEFAULT_COMMAND, form);
        }
        else if (CommonDef.report.REPORT_ADD_AGENT.equals(type)) { // 广告商(代理商)报告
            mv = new ModelAndView("report/advReport", Constants.DEFAULT_COMMAND, form);
        }
        setClassValue(report, form);
        if (report.getDateType().equals("0")) {
            form.setPresetValue("");
        }
        mv.addObject("report", report);
        mv.addObject("form", form);
        return mv;
    }

    @RequestMapping(params = "action=fillPage", method = RequestMethod.POST)
    @RoleAuthority({RoleFactory.ROLE_ALL})
    public ModelAndView fillPage(ReportForm form) {
        CoreReport report = form.getReport();
        ModelAndView mv = new ModelAndView();
        String type = report.getReportType();
        String ids = report.getReportCon();
        String status = report.getReportStatus();

        // 设置查询所需参数值
        Map<String, String> map = new HashMap<String, String>();
        Long memberId = getLogonMemberId();
        setClassValue(report, form);
        // 页的条数自定义
        int pageSize = 15;
        form.setPageSize(pageSize + "");
        map.put("pageSize", form.getPageSize()); // 默认1
        map.put("currentPage", form.getCurrentPage().equals("") || form.getCurrentPage() == null ? "1" : form
                .getCurrentPage());
        map.put("descFlag", form.getDesc()); // 默认 desc
        map.put("asceFlag", form.getAsce());
        boolean sortFlag = form.getDesc() == null || form.getDesc().equals("") ? false : true;

        int currentPage = Integer.parseInt(map.get("currentPage").toString());

        if (report.getReportStartTime() != null && report.getReportEndTime() != null) {
            map.put("startTime", ReportDateUtil.getDate("yyyy-MM-dd", report.getReportStartTime()));
            map.put("endTime", ReportDateUtil.getDate("yyyy-MM-dd", report.getReportEndTime()));
        }
        map.put("memberId", memberId.toString());// 用户id
        // 时间起始队列
        String stDate = map.get("startTime");
        String endDate = map.get("endTime");

        // 日期跨度队列
        List<String> datelist = null;
        int count = 0;
        List<StatAd> keyNames = null;
        try {

            // 出列日期为 0 的队列，包括 详情和汇总
            ids = convertIds(ids);
            int fillCount = 0;
            if (CommonDef.report.REPORT_ADD_AD.equals(type) || CommonDef.report.REPORT_ADD_ADGROUP.equals(type)
            // || CommonDef.report.REPORT_ADD_CAM.equals(type)
            ) {
                keyNames = reportService.getStaKeyName(type, memberId, ids);
                fillCount = keyNames.size();
            }
            else if (CommonDef.report.REPORT_ADD_DEVELOP.equals(type)) {
                List<CoreApp> appList = reportService.getAppList(ids, memberId);
                fillCount = appList.size();
            }

            // 优化分页，从时间段优化（尽可能缩小时间段）
            if (CommonDef.report.REPORT_DETAIL_TYPE.equals(status)) {
                count =
                        (ReportDateUtil.getDays(ReportDateUtil.getDate("yyyy-MM-dd", stDate), ReportDateUtil.getDate(
                                "yyyy-MM-dd", endDate)) + 1)
                                * fillCount;

                int addDays = (currentPage - 1) * pageSize / fillCount;
                int leftCount = fillCount - ((currentPage - 1) * pageSize % fillCount);

                int addDays2 = 0;
                // 剩余的大于 页面全部大小
                if (leftCount >= pageSize) {
                    addDays2 = 0;
                }
                else {
                    if ((pageSize - leftCount) % fillCount == 0) {
                        addDays2 = ((pageSize - leftCount) / fillCount);
                    }
                    else {
                        addDays2 = ((pageSize - leftCount) / fillCount) + 1;
                    }
                }

                // 降序查看
                if (sortFlag) {
                    endDate =
                            ReportDateUtil.getTomorrowDate("yyyy-MM-dd", ReportDateUtil.getDate("yyyy-MM-dd", endDate),
                                    -addDays);
                    stDate =
                            ReportDateUtil.getTomorrowDate("yyyy-MM-dd", ReportDateUtil.getDate("yyyy-MM-dd", endDate),
                                    -addDays2);
                    if (ReportDateUtil.getDate("yyyy-MM-dd", stDate).before(report.getReportStartTime())) {
                        stDate = map.get("startTime");
                    }

                }
                else { // 升序查看
                    stDate =
                            ReportDateUtil.getTomorrowDate("yyyy-MM-dd", ReportDateUtil.getDate("yyyy-MM-dd", stDate),
                                    addDays);
                    endDate =
                            ReportDateUtil.getTomorrowDate("yyyy-MM-dd", ReportDateUtil.getDate("yyyy-MM-dd", stDate),
                                    addDays2);
                    if (ReportDateUtil.getDate("yyyy-MM-dd", endDate).after(report.getReportEndTime())) {
                        endDate = map.get("endTime");
                    }
                }

            }
            else { // 汇总
                count =
                        ReportDateUtil.getDays(ReportDateUtil.getDate("yyyy-MM-dd", stDate), ReportDateUtil.getDate(
                                "yyyy-MM-dd", endDate)) + 1;
                if (sortFlag) {
                    stDate =
                            ReportDateUtil.getTomorrowDate("yyyy-MM-dd", ReportDateUtil.getDate("yyyy-MM-dd", endDate),
                                    -((currentPage - 1) * pageSize));
                    endDate =
                            ReportDateUtil.getTomorrowDate("yyyy-MM-dd", ReportDateUtil.getDate("yyyy-MM-dd", stDate),
                                    -(pageSize - 1));
                    if (ReportDateUtil.getDate("yyyy-MM-dd", endDate).before(report.getReportStartTime())) {
                        endDate = map.get("startTime");
                    }
                    String temp = endDate;
                    endDate = stDate;
                    stDate = temp;
                }
                else {
                    stDate =
                            ReportDateUtil.getTomorrowDate("yyyy-MM-dd", ReportDateUtil.getDate("yyyy-MM-dd", stDate),
                                    (currentPage - 1) * pageSize);
                    endDate =
                            ReportDateUtil.getTomorrowDate("yyyy-MM-dd", ReportDateUtil.getDate("yyyy-MM-dd", stDate),
                                    pageSize - 1);
                    if (ReportDateUtil.getDate("yyyy-MM-dd", endDate).after(report.getReportEndTime())) {
                        endDate = map.get("endTime");
                    }
                }
            }

            datelist = ReportDateUtil.getDateList(stDate, endDate);
            map.put("startTime", stDate);
            map.put("endTime", endDate);
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }

        // 活动报告 ; 广告组报告 ; 广告报告
        if (CommonDef.report.REPORT_ADD_AD.equals(type) || CommonDef.report.REPORT_ADD_ADGROUP.equals(type)
        // || CommonDef.report.REPORT_ADD_CAM.equals(type)
        ) {
            mv = new ModelAndView("report/ad_table_list");
            List<StatAd> list = new ArrayList<StatAd>();
            ids = convertIds(ids);
            if (CommonDef.report.REPORT_ADD_AD.equals(type)) {
                map.put("inAdId", ids);
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(status)) {// 详情
                    list = statAdService.querybyadDetailList(map);
                }
                else { // 汇总
                    list = statAdService.querybyadTotalList(map);
                }
            }
            else if (CommonDef.report.REPORT_ADD_ADGROUP.equals(type)) {
                map.put("inAdGroupId", ids);
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(status)) { // 详情
                    list = statAdService.querybyadGroupDetailList(map);
                }
                else { // 汇总
                    list = statAdService.querybyadGroupTotalList(map);
                }
            }
            else {
                map.put("inCampaignId", ids);
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(status)) { // 详情
                    list = statAdService.queryCampaignDetailList(map);
                }
                else { // 汇总
                    list = statAdService.queryCampaignTotalList(map);
                }
            }

            List list1 =
                    reportService.dealEmptyDateList(type, ids, memberId, status, pageSize, currentPage, datelist,
                            sortFlag, list, keyNames, null);

            list = list1;

            mv.addObject("adlist", list);
            form.setTotalCount(Integer.toString(count));
        }
        else if (CommonDef.report.REPORT_ADD_DEVELOP.equals(type)) { // 应用程序报告
            mv = new ModelAndView("report/app_table_list");
            List<StatApp> list = new ArrayList<StatApp>();
            map.put("inAppId", ids);

            if (CommonDef.report.REPORT_DETAIL_TYPE.equals(status)) { // 详情
                list = statAppService.queryDetailList(map);
            }
            else {
                list = statAppService.queryTotalList(map);
            }

            List list1 =
                    reportService.dealEmptyDateList(type, ids, memberId, status, pageSize, currentPage, datelist,
                            sortFlag, list, null, reportService.getAppList(ids, memberId));
            list = list1;

            mv.addObject("applist", list);
            form.setTotalCount(Integer.toString(count));
        }
        else if (CommonDef.report.REPORT_ADD_AGENT.equals(type)) { // 广告商(代理商)报告
            mv = new ModelAndView("report/adv_table_list");
            List<StatAgent> list = new ArrayList<StatAgent>();
            map.put("inAdId", ids);
            if (CommonDef.report.REPORT_DETAIL_TYPE.equals(status)) {
                count = statAgentService.selectAngenDetailListCount(map);
                list = statAgentService.queryAngenDetailList(map);
            }
            else {
                count = statAgentService.selectAngenTotalListCount(map);
                list = statAgentService.queryAngenTotalList(map);
            }

            mv.addObject("advlist", list);
            form.setTotalCount(Integer.toString(count));
        }
        // 排序
        if (StringUtils.isNotEmpty(form.getAsce())) {
            mv.addObject("asce", form.getAsce());
        }
        else {
            mv.addObject("sortFlag", "flag");
        }
        mv.addObject("form", form);
        return mv;
    }

    /**
     * ids转换处理
     * 
     * @param ids
     * @modify wangxing 去除活动后，增加了新旧数据的兼容性
     * @return
     */
    public String convertIds(String ids) {
        String[] all = ids.split(";");
        if (all.length == 3) {
            // 三层、原数据、广告
            ids = all[2].replaceAll("a", "");
        }
        else if (all.length == 2) {
            // 两层、原数据广告组或新数据广告
            if (all[1].indexOf("a") >= 0) {
                // 新数据广告
                ids = all[1].replaceAll("a", "");
            }
            else {
                // 原数据广告组
                ids = all[1].replaceAll("g", "");
            }
        }
        else if (all.length == 1) {
            // 一层、原数据活动或新数据广告组
            if (all[0].indexOf("g") >= 0) {
                // 新数据广告组
                ids = all[0].replaceAll("g", "");
            }
            else if (all[0].indexOf("a") >= 0) {
                // 貌似数据库中存在只存入了广告id没有存入广告组id的数据
                ids = all[0].replaceAll("a", "");
            }
            else {
                // 原数据活动
                ids = all[0].replaceAll("c", "");
            }
        }

        return ids;
    }

    /**
     * 设置时间
     * 
     * @param report
     * @param form
     */
    public void setClassValue(CoreReport report, ReportForm form) {
        // 封装信息到map
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("report", form.getReport());
        params.put("reportStartTime", form.getReportStartTime());
        params.put("reportEndTime", form.getReportEndTime());
        params.put("presetValue", form.getPresetValue());
        params.put("dateType", report.getDateType());
        // 设置时间
        report = reportService.getReport(params);
    }

    /**
     * 类型树
     * 
     * @param form
     * @return
     * @throws JSONException
     */
    @RequestMapping(params = "action=doAdTree")
    public ModelAndView doAdTree(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        // 默认全部显示
        boolean showdel = true;
        if ("false".equals(status)) {
            showdel = false;
        }
        Long mid = getLogonMemberId();
        CoreCampaign campaign = new CoreCampaign();
        campaign.setMemberId(mid);
        // 活动
        List<CoreCampaign> clistBean = new ArrayList<CoreCampaign>();
        // 广告组
        List<CoreAdGroup> glistBean = new ArrayList<CoreAdGroup>();
        // 广告
        List<CoreAd> alistBean = new ArrayList<CoreAd>();
        // 应用
        List<CoreApp> applistBean = new ArrayList<CoreApp>();
        JSONArray parentObj = new JSONArray();
        JSONObject parent = new JSONObject();
        parent.put("id", "0");
        parent.put("text", "全部");
        JSONArray childArray = new JSONArray();
        if ("0".equals(type)) {
            clistBean = campaignService.queryByNameList(campaign);
            for (CoreCampaign c : clistBean) {
                String name = c.getCampaignName();
                name = HtmlUtils.htmlEscape(name);
                name = StringUtil.overlayWith20(name);
                int id = c.getCampaignId();
                if (showdel) {
                    if ("1".equals(c.getDelFlag())) {
                        name = name + Constants.TREE_DEL_STYLE;
                    }
                    JSONObject childObject = new JSONObject();
                    childObject.put("text", name);
                    childObject.put("id", "c" + id);
                    childArray.put(childObject);
                }
                else if (!"1".equals(c.getDelFlag()) && !showdel) {
                    JSONObject childObject = new JSONObject();
                    childObject.put("text", name);
                    childObject.put("id", "c" + id);
                    childArray.put(childObject);
                }
            }
            parent.put("children", childArray);
        }
        else if ("3".equals(type)) {
            CoreApp app = new CoreApp();
            app.setMemberId(mid);
            applistBean = appService.queryList(app);
            // 应用程序没有删除
            for (CoreApp a : applistBean) {
                String name = a.getAppName();
                name = HtmlUtils.htmlEscape(name);
                name = StringUtil.overlayWith20(name);
                int id = a.getAppId();
                JSONObject childObject = new JSONObject();
                childObject.put("text", name);
                childObject.put("id", id);
                childArray.put(childObject);
            }
            parent.put("children", childArray);
        }
        else if ("4".equals(type)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("memberId", mid.toString());
            List<CoreAgentRelation> agentListBean = agentRelationService.queryList(map);
            for (CoreAgentRelation a : agentListBean) {
                String name = a.getEmail();
                name = StringUtil.overlayWith20(name);
                int id = a.getRelationId();
                if (showdel) {
                    if ("1".equals(a.getDelFlag()) && showdel) {
                        name = name + Constants.TREE_DEL_STYLE;
                    }
                    JSONObject childObject = new JSONObject();
                    childObject.put("text", name);
                    childObject.put("id", id);
                    childArray.put(childObject);
                }
                else if (!"1".equals(a.getDelFlag()) && !showdel) {
                    JSONObject childObject = new JSONObject();
                    childObject.put("text", name);
                    childObject.put("id", id);
                    childArray.put(childObject);
                }
            }
            parent.put("children", childArray);
        }
        else if ("1".equals(type)) {
            // clistBean = campaignService.queryByNameList(campaign);
            glistBean = adGroupService.findAllGroupByMemberId(mid);
            JSONArray p = new JSONArray();
            // for (CoreCampaign c : clistBean) {
            // String name = c.getCampaignName();
            // name = HtmlUtils.htmlEscape(name);
            // int id = c.getCampaignId();
            JSONArray child = new JSONArray();
            for (CoreAdGroup g : glistBean) {
                String gname = g.getAdGroupName();
                gname = HtmlUtils.htmlEscape(gname);
                int gid = g.getAdGroupId();
                int cid = g.getCampaignId();
                // if (cid == id) {
                if (true) {
                    if (showdel) {
                        gname = gname.length() > 20 ? gname.substring(0, 20) + "..." : gname;
                        if ("1".equals(g.getDelFlag()) && showdel) {
                            gname = gname + Constants.TREE_DEL_STYLE;
                        }
                        JSONObject childObject = new JSONObject();
                        childObject.put("text", gname);
                        childObject.put("id", "g" + gid);
                        child.put(childObject);
                    }
                    else if (!"1".equals(g.getDelFlag()) && !showdel) {
                        gname = gname.length() > 20 ? gname.substring(0, 20) + "..." : gname;
                        JSONObject childObject = new JSONObject();
                        childObject.put("text", gname);
                        childObject.put("id", "g" + gid);
                        child.put(childObject);
                    }
                }
            }

            // if (false) {
            // name = name.length() > 20 ? name.substring(0, 20) + "..." : name;
            // if ("1".equals(c.getDelFlag())) {
            // name = name + Constants.TREE_DEL_STYLE;
            // }
            // JSONObject childobj = new JSONObject();
            // childobj.put("id", "c" + id);
            // childobj.put("text", name);
            // childobj.put("children", child);
            // p.put(childobj);
            // }
            // else if (!"1".equals(g.getDelFlag()) && !showdel) {
            // name = name.length() > 20 ? name.substring(0, 20) + "..." : name;
            // JSONObject childobj = new JSONObject();
            // childobj.put("id", "c" + id);
            // childobj.put("text", gname);
            // childobj.put("children", child);
            // p.put(childobj);
            // }

            JSONObject childobj = new JSONObject();

            // }
            parent.put("children", child);
        }
        else if ("2".equals(type)) {
            // clistBean = campaignService.queryByNameList(campaign);
            glistBean = adGroupService.findAllGroupByMemberId(mid);
            alistBean = adService.findAdListByMemberId(mid);
            JSONArray p = new JSONArray();
            // for (CoreCampaign c : clistBean) {// 活动
            // String name = c.getCampaignName();
            // name = HtmlUtils.htmlEscape(name);
            // int id = c.getCampaignId();
            JSONArray sun = new JSONArray();
            for (CoreAdGroup g : glistBean) {// 广告组
                String gname = g.getAdGroupName();
                gname = HtmlUtils.htmlEscape(gname);
                int gid = g.getAdGroupId();
                // int cid = g.getCampaignId();
                JSONArray child = new JSONArray();
                for (CoreAd a : alistBean) {// 广告
                    String aname = a.getAdName();
                    aname = HtmlUtils.htmlEscape(aname);
                    int aid = a.getAdId();
                    int adid = a.getAdGroupId();
                    if (adid == gid) {
                        if (showdel) {
                            aname = aname.length() > 20 ? aname.substring(0, 20) + "..." : aname;
                            if ("1".equals(a.getDelFlag()) && showdel) {
                                aname = aname + Constants.TREE_DEL_STYLE;
                            }
                            JSONObject childObject = new JSONObject();
                            childObject.put("text", aname);
                            childObject.put("id", "a" + aid);
                            child.put(childObject);
                        }
                        else if (!"1".equals(a.getDelFlag()) && !showdel) {
                            aname = aname.length() > 20 ? aname.substring(0, 20) + "..." : aname;
                            JSONObject childObject = new JSONObject();
                            childObject.put("text", aname);
                            childObject.put("id", "a" + aid);
                            child.put(childObject);
                        }
                    }
                }
                // if (cid == id) {
                if (showdel) {
                    gname = gname.length() > 20 ? gname.substring(0, 20) + "..." : gname;
                    if ("1".equals(g.getDelFlag()) && showdel) {
                        gname = gname + Constants.TREE_DEL_STYLE;
                    }
                    JSONObject childObj = new JSONObject();
                    childObj.put("id", "g" + gid);
                    childObj.put("text", gname);
                    childObj.put("children", child);
                    sun.put(childObj);
                }
                else if (!"1".equals(g.getDelFlag()) && !showdel) {
                    gname = gname.length() > 20 ? gname.substring(0, 20) + "..." : gname;
                    JSONObject childObj = new JSONObject();
                    childObj.put("id", "g" + gid);
                    childObj.put("text", gname);
                    childObj.put("children", child);
                    sun.put(childObj);
                }
                // }
            }
            // if (showdel) {
            // name = name.length() > 20 ? name.substring(0, 20) + "..." : name;
            // if ("1".equals(c.getDelFlag()) && showdel) {
            // name = name + Constants.TREE_DEL_STYLE;
            // }
            // JSONObject childObj = new JSONObject();
            // childObj.put("id", "c" + id);
            // childObj.put("text", name);
            // childObj.put("children", sun);
            // p.put(childObj);
            // }
            // else if (!"1".equals(c.getDelFlag()) && !showdel) {
            // name = name.length() > 20 ? name.substring(0, 20) + "..." : name;
            // JSONObject childObj = new JSONObject();
            // childObj.put("id", "c" + id);
            // childObj.put("text", name);
            // childObj.put("children", sun);
            // p.put(childObj);
            // }
            // }
            parent.put("children", sun);
        }
        // 打印JSON格式的文本
        String jsonObj = parentObj.put(parent).toString();
        response.setContentType("application/json-rpc;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Pragma", "No-cache");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().print(jsonObj);
        }
        catch (OFCException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 报表
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "action=dochartShow")
    @RoleAuthority({RoleFactory.ROLE_ALL})
    public ModelAndView dochartShow(HttpServletRequest request, HttpServletResponse response) {
        // 打印JSON格式的文本
        response.setContentType("application/json-rpc;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Pragma", "No-cache");
        // 页面需要统计的id
        String ids = request.getParameter("ids");
        // 报表程序类型
        String type = request.getParameter("type");
        // 页签
        String to = request.getParameter("to");
        // 报告类型（1详细0汇总）
        String chartType = request.getParameter("status");

        CoreReport report = new CoreReport();

        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String se = request.getParameter("presetValue");
        String dateType = request.getParameter("dateType");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("presetValue", se);
        params.put("reportStartTime", start);
        params.put("reportEndTime", end);
        params.put("dateType", dateType);
        params.put("report", report);
        report = reportService.getReport(params);

        Map<String, String> pmap = new HashMap<String, String>();
        String startTime = ReportDateUtil.getDate("yyyy-MM-dd", report.getReportStartTime());
        String endTime = ReportDateUtil.getDate("yyyy-MM-dd", report.getReportEndTime());
        pmap.put("startTime", startTime);
        pmap.put("endTime", endTime);
        pmap.put("memberId", getLogonMemberId().toString());// 用户id

        String title = request.getParameter("title");// 页面传递的参数
        String chart = "";
        if (CommonDef.report.REPORT_ADD_AD.equals(type) || CommonDef.report.REPORT_ADD_ADGROUP.equals(type)
        // || CommonDef.report.REPORT_ADD_CAM.equals(type)
        ) {
            List<StatAd> reportlist = new ArrayList<StatAd>();
            // 转换id ，去除 A、B、C 内容
            ids = convertIds(ids);
            // if (CommonDef.report.REPORT_ADD_CAM.equals(type)) {
            // pmap.put("inCampaignId", ids);
            // if (CommonDef.report.REPORT_DETAIL_TYPE.equals(chartType)) {
            // reportlist = statAdService.selectcampaignIdDetailList(pmap);
            // }
            // else if (CommonDef.report.REPORT_TOTAL_TYPE.equals(chartType)) { // 汇总
            // reportlist = statAdService.selectcampaignIdTotalList(pmap);
            // }
            // }
            // else
            if (CommonDef.report.REPORT_ADD_ADGROUP.equals(type)) {
                pmap.put("inAdGroupId", ids);
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(chartType)) {
                    reportlist = statAdService.selectadGroupDetailList(pmap);
                }
                else if (CommonDef.report.REPORT_TOTAL_TYPE.equals(chartType)) { // 汇总
                    reportlist = statAdService.selectadGroupTotalList(pmap);
                }
            }
            else {
                pmap.put("inAdId", ids);
                // 广告详细
                if (CommonDef.report.REPORT_DETAIL_TYPE.equals(chartType)) {
                    reportlist = statAdService.selectadDetailList(pmap);
                }
                else if (CommonDef.report.REPORT_TOTAL_TYPE.equals(chartType)) { // 汇总
                    reportlist = statAdService.selectadTotalList(pmap);
                }
            }

            if (StringUtils.isBlank(title)) {
                title = CommonDef.report.INIT_AD_TITLE;
            }
            chart = getchartData(title, reportlist, to, type, chartType, pmap, se, ids);

        }
        // 开发角色
        else if (CommonDef.report.REPORT_ADD_DEVELOP.equals(type)) {
            pmap.put("inAppId", ids);
            List<StatApp> reportlist = new ArrayList<StatApp>();
            if (CommonDef.report.REPORT_DETAIL_TYPE.equals(chartType)) { // 详情
                reportlist = statAppService.selectappDetailList(pmap);
            }
            else if (CommonDef.report.REPORT_TOTAL_TYPE.equals(chartType)) { // 汇总
                reportlist = statAppService.selectappTotalList(pmap);
            }

            if (StringUtils.isBlank(title)) {
                title = CommonDef.report.INIT_APP_TITLE;
            }
            chart = getchartData(title, reportlist, to, type, chartType, pmap, se, ids);

        }
        else if (CommonDef.report.REPORT_ADD_AGENT.equals(type)) { // 代理商

            pmap.put("inAgentId", ids);
            List<StatAgent> reportlist = new ArrayList<StatAgent>();
            // 广告详细
            if (CommonDef.report.REPORT_DETAIL_TYPE.equals(chartType)) {
                reportlist = statAgentService.selectAngenDetailList(pmap);
            }
            else if (CommonDef.report.REPORT_TOTAL_TYPE.equals(chartType)) { // 汇总
                reportlist = statAgentService.selectAngenTotalList(pmap);
            }
            if (StringUtils.isBlank(title)) {
                title = CommonDef.report.INIT_ADV_TITLE;
            }
            chart = getchartData(title, reportlist, to, type, chartType, pmap, se, ids);
        }
        try {
            response.getWriter().print(chart);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * @param title 查询标题
     * @param list 查询结果对象
     * @param to 输出索引位置
     * @param type 报表程序类型（活动 、应用。。。）
     * @param chartType 统计类型 汇总、详情
     * @return 返回json 格式 数据
     */
    public String getchartData(String title, List<?> list, String to, String type, String chartType,
            Map<String, String> pmap, String se, String ids) {
        JSONObject json = new JSONObject();
        // y轴数据集合
        JSONArray ylist = new JSONArray();
        // x轴数据集合
        JSONArray xlist = new JSONArray();

        // 日期数据重复索引队列
        List<Integer> needRemoveIndex = new ArrayList<Integer>();
        // 时间标签间隔
        int dividelength = 1;
        try {
            /***
             * 时间起始队列
             */
            List<String> datelist = ReportDateUtil.getDateList(pmap.get("startTime"), pmap.get("endTime"));
            if (se == null || se.equals("")) {
                if (datelist.size() > 7) {
                    dividelength = (int) Math.ceil(datelist.size() / 5);
                }
            }
            else {
                dividelength = ReportDateUtil.getDivideLength(se, datelist.size());
            }

            // 获取需要显示的数据集(详细)
            if (CommonDef.report.REPORT_DETAIL_TYPE.equals(chartType)) {
                // 得到当前数据中的所有的日期
                Map<String, String> statDateMaps = new LinkedHashMap<String, String>();
                // 每个广告的数据 存放 appid,<DATA>
                Map<String, List> keyDataMaps = new LinkedHashMap<String, List>();
                // 每个广告的数据 存放 appid,<DATE>
                Map<String, List<String>> keyDateMaps = new LinkedHashMap<String, List<String>>();
                // 每个广告的名称
                // 每个广告的数据 存放 appid,appName
                Map<String, String> namelist = new LinkedHashMap<String, String>();

                // 多条线操作
                String id = "";
                String name = "";

                if (CommonDef.report.REPORT_ADD_AD.equals(type) || CommonDef.report.REPORT_ADD_ADGROUP.equals(type)
                // || CommonDef.report.REPORT_ADD_CAM.equals(type)
                ) {
                    for (int i = 0; i < list.size(); i++) {
                        StatAd s = (StatAd) list.get(i);
                        List data = null;
                        List data1 = null;
                        // x轴数据
                        Date date = s.getAddTime();
                        String d = ReportDateUtil.getDate("yyyy-MM-dd", date);

                        if (CommonDef.report.REPORT_ADD_AD.equals(type)) {
                            id = String.valueOf(s.getAdId());
                            name = s.getAdName();
                        }
                        else if (CommonDef.report.REPORT_ADD_ADGROUP.equals(type)) {
                            id = String.valueOf(s.getAdGroupId());
                            name = s.getAdGroupName();
                        }
                        else {
                            id = String.valueOf(s.getCampaignId());
                            name = s.getCampaignName();
                        }

                        if (!statDateMaps.containsKey(d + id)) {
                            statDateMaps.put(d + id, d);
                            // xlist.put(d);
                        }
                        else {
                            // 增强数据容错能力，同一个app 同一天 表正常只能有一条数据
                            needRemoveIndex.add(i);
                            continue;
                        }

                        // 加载数据和名称
                        if (!keyDataMaps.containsKey(id)) {
                            keyDataMaps.put(id, new ArrayList());
                            keyDateMaps.put(id, new ArrayList<String>());
                            namelist.put(id, name);
                        }
                        data = keyDataMaps.get(id);
                        data1 = keyDateMaps.get(id);
                        data1.add(d);
                        if ("4".equals(to)) {
                            double clickmon = s.getClickMoney().doubleValue();
                            data.add(clickmon);
                        }
                        else if ("1".equals(to)) {
                            int sunshow = s.getShowNum();
                            data.add(sunshow);
                        }
                        else if ("2".equals(to)) {
                            int sunnum = s.getClickNum();
                            data.add(sunnum);
                        }
                        else if ("3".equals(to)) {
                            BigDecimal clickRate = s.getClickRate();
                            data.add(clickRate);// 填充y轴
                        }

                    }

                    // 移除日期数据重复的索引
                    for (int i = needRemoveIndex.size() - 1; i >= 0; i--) {
                        list.remove((int) needRemoveIndex.get(i));
                    }

                    // 填充空白日期数据

                    // key ,name队列
                    List<StatAd> keyNames = reportService.getStaKeyName(type, getLogonMemberId(), ids);
                    xlist = new JSONArray();
                    for (String d : datelist) {
                        // 报表x 坐标
                        xlist.put(d);
                        try {
                            // 放入日期
                            for (StatAd apptemp : keyNames) {
                                String appid = apptemp.getAdGroupName();
                                String appName = apptemp.getAdName();

                                if (!keyDataMaps.containsKey(appid)) {
                                    keyDataMaps.put(appid, new ArrayList());
                                    keyDateMaps.put(appid, new ArrayList<String>());
                                    namelist.put(appid, appName);
                                }
                                if (!statDateMaps.containsKey(d + appid)) {
                                    statDateMaps.put(d + appid, d);
                                    List data = keyDataMaps.get(appid);
                                    try {
                                        List<String> dateList = keyDateMaps.get(appid);
                                        // 数据保存索引位置
                                        int dataIndex = ReportDateUtil.getDateIndex(dateList, d);
                                        // 更新数据索引队列
                                        dateList.add(dataIndex, d);

                                        // 没有查询到则给默认0 值
                                        data.add(dataIndex, 0);
                                    }
                                    catch (Exception e) {
                                        getLogger().debug("处理为0数据异常" + e);
                                    }
                                }
                            }
                        }
                        catch (Exception e) {
                            getLogger().debug("处理为0数据异常" + e);
                        }
                    }
                    // end 填充数据
                }

                // 应用类型报表详情
                else if (CommonDef.report.REPORT_ADD_DEVELOP.equals(type)) { // 开发
                    for (int i = 0; i < list.size(); i++) {
                        List data = null;
                        List data1 = null;
                        StatApp p = (StatApp) list.get(i);
                        // x轴数据
                        Date date = p.getAddTime();
                        String d = ReportDateUtil.getDate("yyyy-MM-dd", date);
                        if (!statDateMaps.containsKey(d + p.getAppId())) {
                            statDateMaps.put(d + p.getAppId(), d);
                            // xlist.put(d);
                        }
                        else {
                            // 增强数据容错能力，同一个app 同一天 表正常只能有一条数据
                            needRemoveIndex.add(i);
                            continue;
                        }
                        // 加载数据
                        id = String.valueOf(p.getAppId());
                        name = p.getAppName();
                        if (!keyDataMaps.containsKey(id)) {
                            keyDataMaps.put(id, new ArrayList());
                            keyDateMaps.put(id, new ArrayList<String>());
                            namelist.put(id, name);
                        }
                        data = keyDataMaps.get(id);
                        data1 = keyDateMaps.get(id);
                        data1.add(d);
                        if ("4".equals(to)) {
                            double clickmon = p.getOffer().doubleValue();
                            data.add(clickmon);
                        }
                        else if ("2".equals(to)) {
                            int sunshow = p.getShowNum();
                            data.add(sunshow);
                        }
                        else if ("3".equals(to)) {
                            int sunnum = p.getClickNum();
                            data.add(sunnum);
                        }
                        else if ("1".equals(to)) {
                            int reqnum = p.getMaxNum();
                            data.add(reqnum);// 填充y轴
                        }
                    }

                    // 移除日期数据重复的索引
                    for (int i = needRemoveIndex.size() - 1; i >= 0; i--) {
                        list.remove((int) needRemoveIndex.get(i));
                    }

                    // 没有数据的统计默认给予 0
                    List<CoreApp> appList = reportService.getAppList(ids, getLogonMemberId());

                    xlist = new JSONArray();
                    for (String d : datelist) {
                        // 报表x 坐标
                        xlist.put(d);
                        try {
                            // 放入日期
                            for (CoreApp apptemp : appList) {
                                String appid = apptemp.getAppId() + "";
                                String appName = apptemp.getAppName();

                                if (!keyDataMaps.containsKey(appid)) {
                                    keyDataMaps.put(appid, new ArrayList());
                                    keyDateMaps.put(appid, new ArrayList<String>());
                                    namelist.put(appid, appName);
                                }

                                if (!statDateMaps.containsKey(d + appid)) {
                                    statDateMaps.put(d + appid, d);
                                    List data = keyDataMaps.get(appid);
                                    try {
                                        List<String> dateList = keyDateMaps.get(appid);
                                        // 数据保存索引位置
                                        int dataIndex = ReportDateUtil.getDateIndex(dateList, d);
                                        // 更新数据索引队列
                                        dateList.add(dataIndex, d);

                                        // 没有查询到则给默认0 值
                                        data.add(dataIndex, 0);
                                    }
                                    catch (Exception e) {
                                        getLogger().debug("处理为0数据异常" + e);
                                    }
                                }
                            }
                        }
                        catch (Exception e) {
                            getLogger().debug("处理为0数据异常" + e);
                        }
                    }
                    // 开发者应用详情默认0 结束
                    // add by pengli 2011-07-15 end
                }
                else if (CommonDef.report.REPORT_ADD_AGENT.equals(type)) { // 代理商

                    for (int i = 0; i < list.size(); i++) {
                        List data = null;
                        StatAgent a = (StatAgent) list.get(i);
                        // x轴数据
                        Date date = a.getAddTime();
                        String d = ReportDateUtil.getDate("yyyy-MM-dd", date);
                        if (!statDateMaps.containsKey(d)) {
                            statDateMaps.put(d, d);
                            xlist.put(d);
                        }
                        // 加载数据
                        id = String.valueOf(a.getAgentId());
                        name = a.getAdvertiserName();
                        if (!keyDataMaps.containsKey(id)) {
                            keyDataMaps.put(id, new ArrayList());
                            namelist.put(id, name);
                        }
                        data = keyDataMaps.get(id);
                        if ("3".equals(to)) {
                            double clickmon = a.getOffer().doubleValue();
                            data.add(clickmon);
                        }
                        else if ("1".equals(to)) {
                            int sunshow = a.getShowNum();
                            data.add(sunshow);
                        }
                        else if ("2".equals(to)) {
                            int sunnum = a.getClickNum();
                            data.add(sunnum);
                        }
                    }
                }

                // 处理标签
                for (Entry<String, List> identry : keyDataMaps.entrySet()) {
                    int numIndex = 0;
                    boolean tflag = false;
                    List l2 = new ArrayList();
                    for (Object d : identry.getValue()) {
                        tflag = false;
                        if (numIndex++ % dividelength == 0) {
                            tflag = true;
                        }
                        JSONObject ylisttmp1 = new JSONObject();
                        ylisttmp1.put("enabled", tflag);
                        JSONObject ylisttmp2 = new JSONObject();
                        ylisttmp2.put("y", d);
                        ylisttmp2.put("marker", ylisttmp1);
                        l2.add(ylisttmp2);
                    }
                    keyDataMaps.put(identry.getKey(), l2);
                }

                for (Entry<String, List> identry : keyDataMaps.entrySet()) {
                    JSONObject detailjson = new JSONObject();
                    String eid = identry.getKey();
                    for (Entry<String, String> nameentry : namelist.entrySet()) {
                        String nid = nameentry.getKey();
                        if (eid.equals(nid)) {
                            // detailjson.put("name", detailjson.put("name",
                            // com.mitian.airad.common.utils.StringUtils.abbreviate(nameentry);
                            detailjson.put("name", com.mitian.airad.common.utils.StringUtils.abbreviate(nameentry
                                    .getValue(), 10));
                            detailjson.put("data", identry.getValue());
                        }
                    }
                    ylist.put(detailjson);
                }
            }
            else { // 汇总 ok
                if (CommonDef.report.REPORT_ADD_AD.equals(type) || CommonDef.report.REPORT_ADD_ADGROUP.equals(type)
                // || CommonDef.report.REPORT_ADD_CAM.equals(type)
                ) { // 广告主
                    setAdDataSet(list, to, ylist, xlist, datelist, dividelength);// 设值
                }
                else if (CommonDef.report.REPORT_ADD_DEVELOP.equals(type)) { // 开发者

                    setAppDataSet(list, to, ylist, xlist, datelist, dividelength);// 设值
                }
                else if (CommonDef.report.REPORT_ADD_AGENT.equals(type)) { // 代理商
                    setAdvDataSet(list, to, ylist, xlist, datelist, dividelength);
                }
                json.put("seriesName", "总统计数据");
            }

            json.put("divide", dividelength);
            json.put("title", title);
            json.put("columnkeys", xlist);
            json.put("rowkeys", ylist);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (InvalidInfoException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        String totalChart = json.toString();
        return totalChart;
    }

    /**
     * 广告商汇总
     * 
     * @param list
     * @param to
     * @param ylist
     * @param xlist
     * @param datelist
     * @param divideLength
     * @throws JSONException
     */
    public void setAdDataSet(List list, String to, JSONArray ylist, JSONArray xlist, List<String> datelist,
            int divideLength) throws JSONException {

        // 存放有数据的日期队列，用于确定数据存放的位置
        List<String> statDates = new ArrayList<String>();
        // 数据
        List<Object> ylist1 = new ArrayList<Object>();
        // 保存有日期的数据队列
        Map<String, String> dateMaps = new LinkedHashMap<String, String>();
        List<Integer> needRemoveIndex = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            StatAd s = (StatAd) list.get(i);
            Date date = s.getAddTime();
            String d = ReportDateUtil.getDate("yyyy-MM-dd", date);
            // xlist.put(d);

            if (!dateMaps.containsKey(date)) {
                statDates.add(d);
                dateMaps.put(d, d);

            }
            else {
                needRemoveIndex.add(i);
                continue;
            }

            if ("4".equals(to)) {
                double clickmon = s.getClickMoney().doubleValue();
                ylist1.add(clickmon);
            }
            else if ("1".equals(to)) {
                int sunshow = s.getShowNum();
                ylist1.add(sunshow);
            }
            else if ("2".equals(to)) {
                int sunnum = s.getClickNum();
                ylist1.add(sunnum);
            }
            else if ("3".equals(to)) {
                // double showmon = s.getShowMoney().doubleValue();
                // s.getShowMoney().doubleValue();
                // ylist1.add(showmon);

                BigDecimal clickRate = s.getClickRate();
                s.getShowMoney().doubleValue();
                ylist1.add(clickRate);// 填充y轴
            }

        }

        // 移除日期数据重复的索引
        for (int i = needRemoveIndex.size() - 1; i >= 0; i--) {
            list.remove((int) needRemoveIndex.get(i));
        }

        for (String d : datelist) {
            xlist.put(d);
            if (!dateMaps.containsKey(d)) {
                try {
                    int dateIndex = ReportDateUtil.getDateIndex(statDates, d);
                    statDates.add(dateIndex, d);
                    ylist1.add(dateIndex, 0);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                dateMaps.put(d, d);
            }
        }

        int numIndex = 0;
        boolean tflag = false;
        for (Object d : ylist1) {
            tflag = false;
            if (numIndex++ % divideLength == 0) {
                tflag = true;
            }
            JSONObject ylisttmp1 = new JSONObject();
            ylisttmp1.put("enabled", tflag);
            JSONObject ylisttmp2 = new JSONObject();
            ylisttmp2.put("y", d);
            ylisttmp2.put("marker", ylisttmp1);

            ylist.put(ylisttmp2);
        }
    }

    /***
     * 开发者 应用统计报表汇总
     * 
     * @param list
     * @param to
     * @param ylist
     * @param xlist
     * @throws JSONException
     */
    public void setAppDataSet(List list, String to, JSONArray ylist, JSONArray xlist, List<String> datelist,
            int divideLength) throws JSONException {

        List<String> statDates = new ArrayList<String>();
        List<Object> ylist1 = new ArrayList<Object>();
        Map<String, String> dateMaps = new LinkedHashMap<String, String>();
        List<Integer> needRemoveIndex = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            StatApp app = (StatApp) list.get(i);

            Date date = app.getAddTime();
            String d = ReportDateUtil.getDate("yyyy-MM-dd", date);
            if (!dateMaps.containsKey(date)) {
                statDates.add(d);
                dateMaps.put(d, d);

            }
            else {
                needRemoveIndex.add(i);
                continue;
            }

            // 1总请求数/次 2总展示数/次 3总点击数/次总 4总点击收入/元
            if ("4".equals(to)) {
                double clickmon = app.getOffer().doubleValue();
                ylist1.add(clickmon);
            }
            else if ("2".equals(to)) {
                int sunshow = app.getShowNum();
                ylist1.add(sunshow);
            }
            else if ("3".equals(to)) {
                int sunnum = app.getClickNum();
                ylist1.add(sunnum);
            }
            else if ("1".equals(to)) {
                double showmon = app.getMaxNum();
                ylist1.add(showmon);
            }

        }

        // 移除日期数据重复的索引
        for (int i = needRemoveIndex.size() - 1; i >= 0; i--) {
            list.remove((int) needRemoveIndex.get(i));
        }

        for (String d : datelist) {
            xlist.put(d);
            if (!dateMaps.containsKey(d)) {
                try {
                    int dateIndex = ReportDateUtil.getDateIndex(statDates, d);
                    statDates.add(dateIndex, d);
                    ylist1.add(dateIndex, 0);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                dateMaps.put(d, d);
            }
        }

        int numIndex = 0;
        boolean tflag = false;
        for (Object d : ylist1) {
            tflag = false;
            if (numIndex++ % divideLength == 0) {
                tflag = true;
            }
            JSONObject ylisttmp1 = new JSONObject();
            ylisttmp1.put("enabled", tflag);
            JSONObject ylisttmp2 = new JSONObject();
            ylisttmp2.put("y", d);
            ylisttmp2.put("marker", ylisttmp1);

            ylist.put(ylisttmp2);
        }
    }

    /**
     * 代理详情
     * 
     * @param list
     * @param to
     * @param ylist
     * @param xlist
     * @param datelist
     * @param divideLength
     * @throws JSONException
     */

    public void setAdvDataSet(List list, String to, JSONArray ylist, JSONArray xlist, List<String> datelist,
            int divideLength) throws JSONException {

        List<String> statDates = new ArrayList<String>();
        List<Object> ylist1 = new ArrayList<Object>();
        Map<String, String> dateMaps = new LinkedHashMap<String, String>();

        // 日期重复位置索引队列
        List<Integer> needRemoveIndex = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            StatAgent s = (StatAgent) list.get(i);

            // 时间到底是创建时间还是统计时间？？？？
            Date date = s.getAddTime();
            String d = ReportDateUtil.getDate("yyyy-MM-dd", date);

            if (!dateMaps.containsKey(date)) {
                statDates.add(d);
                dateMaps.put(d, d);
            }
            else {
                needRemoveIndex.add(i);
                continue;
            }
            // 1广告商广告展示次数 2广告商广告点击次数 3分成收入
            if ("3".equals(to)) {
                double clickmon = s.getOffer().doubleValue();
                ylist1.add(clickmon);
            }
            else if ("2".equals(to)) {
                int sunshow = s.getShowNum();
                ylist1.add(sunshow);
            }
            else if ("1".equals(to)) {
                int sunnum = s.getClickNum();
                ylist1.add(sunnum);
            }
        }

        // 移除日期数据重复的索引
        for (int i = needRemoveIndex.size() - 1; i >= 0; i--) {
            list.remove((int) needRemoveIndex.get(i));
        }

        for (String d : datelist) {
            xlist.put(d);
            if (!dateMaps.containsKey(d)) {
                try {
                    int dateIndex = ReportDateUtil.getDateIndex(statDates, d);
                    statDates.add(dateIndex, d);
                    ylist1.add(dateIndex, 0);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                dateMaps.put(d, d);
            }
        }

        int numIndex = 0;
        boolean tflag = false;
        for (Object d : ylist1) {
            tflag = false;
            if (numIndex++ % divideLength == 0) {
                tflag = true;
            }
            JSONObject ylisttmp1 = new JSONObject();
            ylisttmp1.put("enabled", tflag);
            JSONObject ylisttmp2 = new JSONObject();
            ylisttmp2.put("y", d);
            ylisttmp2.put("marker", ylisttmp1);

            ylist.put(ylisttmp2);
        }
    }

}
