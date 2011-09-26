package com.mitian.airad.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.CommonDef;
import com.mitian.airad.Constants;
import com.mitian.airad.ErrorMessages;
import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.model.CoreAd;
import com.mitian.airad.model.CoreCampaign;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.service.AdService;
import com.mitian.airad.service.CampaignService;
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.utils.StringUtil;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.BaseRole;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.AdGroupForm;
import com.mitian.airad.web.form.CampaignForm;

/**
 * CoreCampaign.java
 * 
 * @author Administrator
 */
/*
@Controller
@RequestMapping("/campaign.do")
@RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_ADVERTISERS, RoleFactory.ROLE_DEVELOPERS,
        RoleFactory.ROLE_GENERAL, RoleFactory.ROLE_OSS_SALES})
        */
public class CampaignController extends AbstractController {

    @Autowired
    private CampaignService campaignService;
    @Autowired
    private AdService adService;

    /**
     * 初始化form信息
     * 
     * @param form
     * @return
     */
    public CampaignForm initialize(CampaignForm form) {
        List<String> hourList = new ArrayList<String>();
        for (int i = 0; i <= 23; i++) {
            if (i < 10) {
                hourList.add("0" + i);
            }
            else {
                hourList.add("" + i);
            }
        }
        form.setHourList(hourList);
        return form;
    }

    /**
     * 跳转到活动组增加页面
     * 
     * @param form
     * @return
     * @throws ServletException
     */
    @RequestMapping(params = "action=add")
    public ModelAndView add(CampaignForm form) throws ServletException {
        // 跳转到增加页面
        ModelAndView mv = new ModelAndView("campaign/campaign_add", Constants.DEFAULT_COMMAND, form);
        initialize(form);
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 增加新的活动组
     * 
     * @param form
     * @return
     * @throws ServletException
     * @throws ParseException
     */
    @RequestMapping(params = "action=doAdd", method = RequestMethod.POST)
    public ModelAndView doAdd(CampaignForm form) throws ServletException, ParseException {
        CoreCampaign campaign = form.getCampaign();
        String timeFlag = form.getTimeFlag();
        // 根据form封装CoreCampaign
        form.form2domain();
        Date startTime = campaign.getStartTime();
        Date endTime = campaign.getEndTime();
        Date date = new Date();
        date = StringUtil.getDateYM(date);
        if (timeFlag.equals(CommonDef.timeFlag.HAVE_ENDTIME)) {
            // 有结束时间，必填
            if (endTime == null) {
                form.getErrors().put("endTime", ErrorMessages.ENDTIME_ERROR_REQUIRED);
            }
        }
        if (null != endTime) {
            if (timeFlag.equals(CommonDef.timeFlag.NO_ENDTIME)) {
                // 没有结束时间，将结束时间置空
                campaign.setEndTime(null);
                campaign.setEndTimeFlag("true");
            }
        }
        if (null != startTime && null != endTime) {
            if (startTime.compareTo(endTime) >= 0 && timeFlag.equals(CommonDef.timeFlag.HAVE_ENDTIME)) {
                form.getErrors().put("startTime", ErrorMessages.REPORT_REPORTTIME_ERROR);
            }
        }
        if (!form.getErrors().isEmpty()) {
            String buggetDay = form.getErrors().get("buggetDay");
            if (StringUtils.isEmpty(buggetDay)) {
                if (StringUtils.isNotBlank(form.getBuggetDay())) {
                    campaign.setBuggetDay(Long.parseLong(form.getBuggetDay()));
                    if (null != campaign.getBuggetDay()) {
                        if (campaign.getBuggetDay() < 50) {
                            form.getErrors().put("buggetDay", ErrorMessages.BUDGETDAY_ERROR);
                        }
                    }
                }
            }
        }
        if (form.getErrors().isEmpty()) {
            if (StringUtils.isNotBlank(form.getBuggetDay())) {
                campaign.setBuggetDay(Long.parseLong(form.getBuggetDay()));
                if (null != campaign.getBuggetDay()) {
                    if (campaign.getBuggetDay() < 50) {
                        form.getErrors().put("buggetDay", ErrorMessages.BUDGETDAY_ERROR);
                    }
                }
            }
        }
        if (!form.getErrors().isEmpty()) {
            return add(form);
        }
        if (StringUtils.isNotBlank(form.getBuggetDay())) {
            campaign.setBuggetDay(Long.parseLong(form.getBuggetDay()));
        }
        campaign.setMemberId(getLogonMemberId());
        // 添加操作
        campaign.setAddOper(getLogonEmail());
        int campaignId = campaignService.txAddCampaign(campaign);
        // 实现业务代码
        AdGroupForm adGroupForm = new AdGroupForm();
        adGroupForm.getCoreAdGroup().setCampaignId(campaignId);
        return new ModelAndView("redirect:/adGroup.do?action=addPage&campaignId=" + campaignId,
                Constants.DEFAULT_COMMAND, form);
    }

    /**
     * 跳转到活动组详细信息，修改
     * 
     * @param form
     * @param request
     * @return
     * @throws ServletException
     * @throws ParseException
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=update")
    public ModelAndView update(CampaignForm form) throws ServletException, ParseException, InvalidInfoException {

        ModelAndView mv = new ModelAndView("campaign/campaign_edit", Constants.DEFAULT_COMMAND, form);
        // 根据id查询活动信息
        String campaignId = form.getCampaignId();
        Long memberId = getLogonMemberId();
        if (org.apache.commons.lang.StringUtils.isBlank(campaignId) || !NumberUtils.isNumber(campaignId)) {
            throw new InvalidInfoException("get CoreCampaign Error campaignId: " + campaignId);
        }

        CoreCampaign campaign = campaignService.findCampaignById(Integer.parseInt(campaignId), memberId);

        if (StringUtils.isNotEmpty(form.getEidtErrorFlag())) {
            campaign = form.getCampaign();
        }
        initialize(form);
        // 将活动信息赋值给form
        form.setCampaign(campaign);
        if (null != campaign.getStartTime()) {
            form.setStartTime(StringUtil.getStringDateYear(campaign.getStartTime()));
            form.setStartHour(StringUtil.getStringDateHour(campaign.getStartTime()));
            form.setStartMin(StringUtil.getStringDateMin(campaign.getStartTime()));
        }
        if (null != campaign.getEndTime()) {
            form.setEndTime(StringUtil.getStringDateYear(campaign.getEndTime()));
            form.setEndHour(StringUtil.getStringDateHour(campaign.getEndTime()));
            form.setEndMin(StringUtil.getStringDateMin(campaign.getEndTime()));
        }
        if (null != campaign.getBuggetDay()) {
            form.setBuggetDay(campaign.getBuggetDay().toString());
        }
        form.setCampaignId(campaign.getCampaignId().toString());
        if (campaign.getEndTime() != null) {
            form.setTimeFlag(CommonDef.timeFlag.HAVE_ENDTIME);
        }
        mv.addObject("form", form);
        mv.addObject("campaign", campaign);
        return mv;
    }

    /**
     * 修改活动组信息
     * 
     * @param form
     * @return
     * @throws ServletException
     * @throws ParseException
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=doUpdate", method = RequestMethod.POST)
    public ModelAndView doUpdate(CampaignForm form) throws ServletException, ParseException, InvalidInfoException {
        ModelAndView mv = new ModelAndView("campaign/campaign_edit", Constants.DEFAULT_COMMAND, form);
        String timeFlag = form.getTimeFlag();
        // 根据form封装活动修改信息
        form.form2domain();
        CoreCampaign campaign = form.getCampaign();
        campaign.setCampaignId(Integer.parseInt(form.getCampaignId()));
        Date startTime = campaign.getStartTime();
        Date endTime = campaign.getEndTime();
        Date date = new Date();
        date = StringUtil.getDateYM(date);
        if (null != endTime) {
            if (timeFlag.equals(CommonDef.timeFlag.NO_ENDTIME)) {
                // 没有结束时间，将结束时间置空
                campaign.setEndTime(null);
                campaign.setEndTimeFlag("true");
            }
        }
        if (timeFlag.equals(CommonDef.timeFlag.HAVE_ENDTIME)) {
            // 有结束时间，必填
            if (endTime == null) {
                form.getErrors().put("endTime", ErrorMessages.ENDTIME_ERROR_REQUIRED);
            }
        }
        if (null != startTime && null != endTime) {
            if (startTime.compareTo(endTime) >= 0) {
                form.getErrors().put("startTime", ErrorMessages.REPORT_REPORTTIME_ERROR);
            }
        }
        if (!form.getErrors().isEmpty()) {
            String buggetDay = form.getErrors().get("buggetDay");
            if (StringUtils.isEmpty(buggetDay)) {
                if (StringUtils.isNotBlank(form.getBuggetDay())) {
                    campaign.setBuggetDay(Long.parseLong(form.getBuggetDay()));
                    if (null != campaign.getBuggetDay()) {
                        if (campaign.getBuggetDay() < 50) {
                            form.getErrors().put("buggetDay", ErrorMessages.BUDGETDAY_ERROR);
                        }
                    }
                }
            }
        }
        if (form.getErrors().isEmpty()) {
            if (StringUtils.isNotBlank(form.getBuggetDay())) {
                campaign.setBuggetDay(Long.parseLong(form.getBuggetDay()));
                if (null != campaign.getBuggetDay()) {
                    if (campaign.getBuggetDay() < 50) {
                        form.getErrors().put("buggetDay", ErrorMessages.BUDGETDAY_ERROR);
                    }
                }
            }
        }
        if (!form.getErrors().isEmpty()) {
            // 给出错标示赋值
            form.setEidtErrorFlag("true");
            return update(form);
        }
        if (StringUtils.isNotBlank(form.getBuggetDay())) {
            campaign.setBuggetDay(Long.parseLong(form.getBuggetDay()));
        }
        campaign.setUpdOper(getLogonEmail());
        campaignService.txEditCampaign(campaign, getLogonMemberId());
        mv =
                new ModelAndView("redirect:/campaign.do?action=listCampaign&currentPage=" + form.getCurrentPage(),
                        Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 查找活动信息，分页
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=listCampaign")
    public ModelAndView listCampaign(CampaignForm form) {
        ModelAndView mv = new ModelAndView("campaign/campaign_list", Constants.DEFAULT_COMMAND, form);
        // 默认降序
        Long memberId = getLogonMemberId();
        if (StringUtils.isEmpty(form.getAsce())) {
            form.setDesc("desc");
            mv.addObject("flag", "flag");
        }
        else {
            mv.addObject("", "flag");
        }
        // 封装数据
        String campaignName = form.getCampaign().getCampaignName();
        Map<String, String> params = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(campaignName)) {
            if (!campaignName.contains("/")) {
                params.put("escape", CommonDef.escape.ESCAPE);
            }
            campaignName = StringUtil.replaceChar(campaignName);
            params.put("campaignName", campaignName);

        }
        params.put("descFlag", form.getDesc());
        params.put("asceFlag", form.getAsce());
        params.put("pageSize", form.getPageSize());
        params.put("memberId", getLogonMemberId().toString());
        int pageSize = 0;
        pageSize = Integer.parseInt(form.getPageSize());
        int k = 0;
        List<CoreCampaign> listBean = new ArrayList<CoreCampaign>();
        if (StringUtils.isEmpty(form.getStartTime()) && StringUtils.isEmpty(form.getEndTime())) {
            k = campaignService.queryCount(params);
            if (Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1) >= k) {
                params.put("currentPage", "1");
            }
            else {
                params.put("currentPage", form.getCurrentPage());
            }
            listBean = campaignService.queryList(params);
        }
        else {
            if (StringUtils.isNotEmpty(form.getStartTime()) && StringUtils.isNotEmpty(form.getEndTime())) {
                if (StringUtil.getDateY(form.getStartTime()).compareTo(StringUtil.getDateY(form.getEndTime())) >= 0) {
                    form.setTimeSlotFlag("");
                    form.getErrors().put("REPORTTIME_ERROR", ErrorMessages.REPORT_REPORTTIME_ERROR);
                    return mv;
                }
            }
            params.put("startTime", form.getStartTime());
            params.put("endTime", form.getEndTime());
            k = campaignService.queryCountByTimeSlot(params);
            if (Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1) >= k) {
                params.put("currentPage", "1");
            }
            else {
                params.put("currentPage", form.getCurrentPage());
            }
            listBean = campaignService.queryListByTimeSlot(params);
        }
        form.setTotalCount(Integer.toString(k));
        form.setListBean(listBean);
        if (StringUtils.isNotEmpty(campaignName)) {
            mv.addObject("campaignName", campaignName);

        }

        // 判断是否有升序
        if (!StringUtil.isEmpty(form.getAsce())) {
            mv.addObject("asce", form.getAsce());
        }
        CoreMemberInfo memberInfo = getMemberInfo();
        mv.addObject("memberInfo", memberInfo);
        mv.addObject("p", form);
        return mv;
    }

    @RequestMapping(params = "action=querybycontion")
    public ModelAndView querybycontion(CampaignForm form, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("campaign/campaign_list");
        CoreCampaign campaign = new CoreCampaign();
        String name = request.getParameter("campaignName");
        String s = request.getParameter("start");
        String e = request.getParameter("end");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        if (s != null && !"".equals(s) && e != null && !"".equals(e)) {
            try {
                Date start = sf.parse(s);
                Date end = sf.parse(e);
                campaign.setStartTime(start);
                campaign.setEndTime(end);
            }
            catch (ParseException e1) {
                getLogger().error("querybycontion error start date:" + s + " end date: " + e);
            }
        }
        campaign.setCampaignName(name);
        List<CoreCampaign> coreCampaigns = campaignService.queryBycondition(campaign, getLogonMemberId());
        mv.addObject("CoreCampaigns", coreCampaigns);
        return mv;
    }

    /**
     * 活动暂停和发布
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doSuspend", method = RequestMethod.POST)
    public ModelAndView doSuspend(HttpServletRequest request, HttpServletResponse response, CampaignForm form) {
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        BaseRole role = memberInfo.getRole();
        if (role.isGeneral() || role.isDev()) {
            form.getErrors().put("SUSPEND_ERROR_REQUIRED", ErrorMessages.SUSPEND_ERROR_REQUIRED);
            return listCampaign(form);
        }
        CoreCampaign campaign = new CoreCampaign();
        String campaignId = request.getParameter("campaignIdTemp");
        String suspendType = request.getParameter("suspendTypeTemp");
        if (StringUtils.isNotEmpty(campaignId)) {
            campaign.setCampaignId(StringUtil.integerToString(campaignId));
            if (CommonDef.unDataBaseConstant.UN_SUSPEND_TYPE.equals(suspendType)) {
                suspendType = CommonDef.unDataBaseConstant.SUSPEND_TYPE;
                CookieUtils.setTipMessageCookie(request, response, CommonDef.suspend.RETURN_SUCCESS);
            }
            else {
                suspendType = CommonDef.unDataBaseConstant.UN_SUSPEND_TYPE;
                CookieUtils.setTipMessageCookie(request, response, CommonDef.issue.RETURN_SUCCESS);
            }
            campaign.setSuspendType(suspendType);
            campaign.setUpdOper(getLogonMemberId().toString());
            campaignService.txSuspend(campaign, memberId);
        }
        ModelAndView mv =
                new ModelAndView("redirect:campaign.do?action=listCampaign&currentPage=" + form.getCurrentPage()
                        + "&asce=" + form.getAsce() + "&timeSlotFlag=" + form.getTimeSlotFlag() + "&startTime="
                        + form.getStartTime() + "&endTime=" + form.getEndTime() + "&campaign.campaignName="
                        + form.getCampaign().getCampaignName(), Constants.DEFAULT_COMMAND, form);
        return mv;

    }

    /**
     * 活动删除
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doDel", method = RequestMethod.POST)
    public ModelAndView doDel(HttpServletRequest request, HttpServletResponse response, CampaignForm form) {
        String campaignId = request.getParameter("campaignIdTemp");
        CoreCampaign campaign = new CoreCampaign();
        Long memberId = getLogonMemberId();
        if (StringUtils.isNotEmpty(campaignId)) {
            campaign.setCampaignId(StringUtil.integerToString(campaignId));
            campaign.setDelOper(memberId.toString());
            campaignService.txdel(campaign, memberId);
            CookieUtils.setTipMessageCookie(request, response, CommonDef.delete.RETURN_SUCCESS);
        }
        ModelAndView mv =
                new ModelAndView("redirect:campaign.do?action=listCampaign&currentPage=" + form.getCurrentPage()
                        + "&asce=" + form.getAsce() + "&timeSlotFlag=" + form.getTimeSlotFlag() + "&startTime="
                        + form.getStartTime() + "&endTime=" + form.getEndTime() + "&campaign.campaignName="
                        + form.getCampaign().getCampaignName(), Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 活动copy
     * 
     * @param form
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=doCopy", method = RequestMethod.POST)
    public ModelAndView doCopy(HttpServletRequest request, CampaignForm form) throws InvalidInfoException {
        String campaignId = request.getParameter("campaignIdTemp");
        String campaignName = request.getParameter("campaignNameTemp");
        String campaignNameCopy = request.getParameter("campaignNameCopyTemp");
        CoreCampaign campaign = new CoreCampaign();
        ModelAndView mv =
                new ModelAndView("redirect:campaign.do?action=listCampaign&currentPage=" + form.getCurrentPage());
        if (StringUtils.isNotEmpty(campaignId)) {
            campaign.setCampaignId(StringUtil.integerToString(campaignId));
            if (campaignName.equals(campaignNameCopy)) {
                campaign.setCampaignName(campaignName + "_copy");
            }
            else {
                if (campaignNameCopy.length() > 50) {
                    form.getErrors().put("CAMPAIGN_ERROR", ErrorMessages.CAMPAIGN_ERROR);
                    return listCampaign(form);
                }
                campaign.setCampaignName(campaignNameCopy);
            }
            Long memberId = getLogonMemberId();
            campaign.setAddOper(memberId.toString());
            campaignService.txCopy(campaign, memberId);
        }
        return mv;
    }

    /**
     * 根据活动ID查找广告
     * 
     * @param form
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "action=adDetial")
    public ModelAndView adDetial(CampaignForm form, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        // 判断是否存在appId，不存在返回列表页面
        try {
            if (StringUtils.isNotEmpty(request.getParameter("id"))) {
                String type = request.getParameter("type");
                CoreAd ad = new CoreAd();
                ad.setMemberId(getLogonMemberId());
                CoreAd coreAd = new CoreAd();
                if ("0".equals(type)) {
                    ad.setCampaignId(StringUtil.stringToInteger(request.getParameter("id")));
                    coreAd = adService.queryListByCampaignId(ad);
                }
                else {
                    ad.setAdGroupId(StringUtil.stringToInteger(request.getParameter("id")));
                    coreAd = adService.queryListByGroupId(ad);
                }

                // 冻结的广告数
                String blockingCount = coreAd.getBlockingCount();
                // 发布的广告数
                String issuCount = coreAd.getIssuCount();
                // 暂停的广告数
                String suspendCount = coreAd.getSuspendCount();
                // 草稿的广告数
                String draftCount = coreAd.getDraftCount();
                // 审核中的广告数
                String checkCount = coreAd.getCheckCount();
                // 审核通过的广告数
                String checkPassCount = coreAd.getCheckPassCount();
                // 审核不通过的广告数
                String checkNoCount = coreAd.getCheckNoCount();
                // 广告总数
                String adCount = coreAd.getAdCount();
                if (StringUtils.isNotEmpty(adCount)) {
                    if (adCount.equals("0")) {
                        response.getWriter().print(CommonDef.adStatus.HASNO);
                    }
                    else {
                        response.getWriter().print(
                                CommonDef.adStatus.ADCOUNT + adCount + "\n" + CommonDef.adStatus.BLOCKINGCOUNT
                                        + blockingCount + "\n" + CommonDef.adStatus.SUSPENDCOUNT + suspendCount + "\n"
                                        + CommonDef.adStatus.DRAFTCOUNT + draftCount + "\n"
                                        + CommonDef.adStatus.CHECKCOUNT + checkCount + "\n"
                                        + CommonDef.adStatus.CHECKPASSCOUNT + checkPassCount + "\n"
                                        + CommonDef.adStatus.CHECKNOCOUNT + checkNoCount);
                    }

                }
            }
        }
        catch (Exception e) {
            getLogger().error("issue error", e);
        }
        return null;
    }
}
