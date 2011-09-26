/*
 * Copyright 2011 mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.CommonDef;
import com.mitian.airad.Constants;
import com.mitian.airad.ErrorMessages;
import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.common.exception.OperationNotSupportException;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.model.CoreAd;
import com.mitian.airad.model.CoreAdGroup;
import com.mitian.airad.model.CoreAdGroupextend;
import com.mitian.airad.model.CoreCampaign;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.model.Dictionary;
import com.mitian.airad.model.DictionaryGlobalRegion;
import com.mitian.airad.refreshable.DictionaryRefreshable;
import com.mitian.airad.service.AdGroupExtendService;
import com.mitian.airad.service.AdGroupService;
import com.mitian.airad.service.AdService;
import com.mitian.airad.service.CampaignService;
import com.mitian.airad.service.MemberService;
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.utils.StringUtil;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.BaseRole;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.AdGroupForm;

/**
 * CoreAdGroupController.java 广告组控制类
 * 
 * @author leifenghai
 */
@Controller
@RequestMapping("/adGroup.do")
@RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_ADVERTISERS, RoleFactory.ROLE_DEVELOPERS,
        RoleFactory.ROLE_GENERAL, RoleFactory.ROLE_OSS_SALES})
public class AdGroupController extends AbstractController {
    @Autowired
    private AdGroupService adGroupService;
    @Autowired
    private CampaignService campaignService;
    @Autowired
    private AdService adService;
    @Autowired
    private AdGroupExtendService adGroupExtendService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private DictionaryRefreshable dictionaryRefreshable;

    /**
     * 根据活动ID及其条件获得其下所有广告组
     * 
     * @param form
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=selectAll")
    public ModelAndView selectAll(AdGroupForm form) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("adgroup/group_list", Constants.DEFAULT_COMMAND, form);
        form.form2domain();
        // 根据获得Id获得活动信息
        CoreCampaign campaign = new CoreCampaign();
        Long memberId = getLogonMemberId();
        String campaignId = form.getCampaignId();
        String adGroupName = form.getAdGroupName();
        if (!StringUtil.isEmpty(form.getCampaignId())) {
            campaign = campaignService.findCampaignById(StringUtil.integerToString(campaignId), memberId);
        }
        int fromRecord = Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1);
        int pageSize = Integer.parseInt(form.getPageSize());

        // 封装数据
        Map<String, String> params = new HashMap<String, String>();
        if (StringUtils.isEmpty(form.getTimeSlotFlag())) {
            if (StringUtils.isNotEmpty(form.getAdGroupName())) {
                if (!adGroupName.contains("/")) {
                    params.put("escape", CommonDef.escape.ESCAPE);
                }
                adGroupName = StringUtil.replaceChar(adGroupName);
                params.put("adGroupName", adGroupName);
                form.setAdGroupName(adGroupName);
            }
        }

        params.put("pageSize", form.getPageSize());
        params.put("memberId", memberId.toString());
        params.put("campaignId", campaignId);

        List<CoreAdGroup> cList = new ArrayList<CoreAdGroup>();
        int groupSize = 0;
        if (StringUtils.isEmpty(form.getStartTime()) && StringUtils.isEmpty(form.getEndTime())) {
            groupSize = adGroupService.countTotal(form.getAdGroupName(), campaignId, memberId);
            if (Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1) >= groupSize) {
                params.put("currentPage", "1");
            }
            else {
                params.put("currentPage", form.getCurrentPage());
            }
            cList = adGroupService.queryAdGroupList(form.getAdGroupName(), campaignId, memberId, fromRecord, pageSize);
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
            groupSize = adGroupService.queryCountTotalByTimeSlot(params);
            if (Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1) >= groupSize) {
                params.put("currentPage", "1");
            }
            else {
                params.put("currentPage", form.getCurrentPage());
            }
            cList = adGroupService.queryAllGroupByTimeSlot(params);
        }

        form.setTotalCount(String.valueOf(groupSize));
        form.setGroupList(cList);
        mv.addObject("campaign", campaign);
        mv.addObject("p", form);
        return mv;
    }

    /**
     * 停用广告组广告
     * 
     * @param form
     * @return
     * @throws InvalidInfoException
     * @throws NumberFormatException
     */
    @RequestMapping(params = "action=suspend")
    public ModelAndView stopGroup(AdGroupForm form, HttpServletResponse response, HttpServletRequest request)
            throws InvalidInfoException {
        String adGroupId = form.getAdGroupId();
        if (StringUtils.isNotEmpty(adGroupId)) {
            Long memberId = getLogonMemberId();
            CoreAdGroup coreAdGroup = adGroupService.queryAdGroupByAdGroupId(Integer.parseInt(adGroupId), memberId);
            coreAdGroup.setSuspendType(CommonDef.appCon.CON_ISSUE);
            adGroupService.txUpdateAdGroup(coreAdGroup);
            // 根据广告组Id查询旗下广告
            List<CoreAd> listAd = adService.findAdList(coreAdGroup);
            adService.txstopAd(listAd);
        }
        CookieUtils.setTipMessageCookie(request, response, CommonDef.suspend.RETURN_SUCCESS);
        ModelAndView mv =
        // new ModelAndView("redirect:adGroup.do?action=selectAll&currentPage=" + form.getCurrentPage() + "&asce="
                new ModelAndView("redirect:adGroup.do?action=list&currentPage=" + form.getCurrentPage() + "&asce="
                        + form.getAsce() + "&timeSlotFlag=" + form.getTimeSlotFlag() + "&startTime="
                        + form.getStartTime() + "&endTime=" + form.getEndTime() + "&adGroupName="
                        + form.getAdGroupName() + "&campaignId=" + form.getCampaignId(), Constants.DEFAULT_COMMAND,
                        form);
        return mv;
    }

    /**
     * 批量停用广告组广告
     * 
     * @param form
     * @param response
     * @param request
     * @return
     * @throws NumberFormatException
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=batchSuspend")
    public ModelAndView batchStopGroup(AdGroupForm form, HttpServletResponse response, HttpServletRequest request)
            throws NumberFormatException, InvalidInfoException {
        String[] arrAdGroupId = form.getAdGroupId().split(",");

        for (int i = 0; i < arrAdGroupId.length; i++) {
            String adGroupId = arrAdGroupId[i];
            if (StringUtils.isNotEmpty(adGroupId)) {
                Long memberId = getLogonMemberId();
                Map<String, String> params = new HashMap<String, String>();
                params.put("memberId", memberId.toString());
                params.put("adGroupId", adGroupId);
                params.put("suspendType", CommonDef.appCon.CON_ISSUE);
                adGroupService.updateAdSuspendType(params);
                CoreAdGroup coreAdGroup = adGroupService.queryAdGroupByAdGroupId(Integer.parseInt(adGroupId), memberId);
                coreAdGroup.setSuspendType(CommonDef.appCon.CON_ISSUE);
                coreAdGroup.setUpdTime(new Date());
                adGroupService.txUpdateAdGroup(coreAdGroup);
                // 根据广告组Id查询旗下广告并停用
                List<CoreAd> listAd = adService.findAdList(coreAdGroup);
                for (int j = 0; j < listAd.size(); j++) {
                    Date date = new Date();
                    Integer adId = (listAd.get(j)).getAdId();
                    CoreAd ca = null;
                    ca = adService.findCoreAdByAdId(Integer.valueOf(adId), getLogonMemberId());
                    ca.setAdId(Integer.valueOf(adId));
                    ca.setUpdOper(getLogonEmail());
                    ca.setUpdTime(date);
                    // adService.txSendCoreAd(ca, getLogonMemberId());
                    adService.txStopCoreAd(ca, getLogonMemberId());
                }
            }
        }
        CookieUtils.setTipMessageCookie(request, response, CommonDef.opera.RETURN_SUCCESS);
        ModelAndView mv =
                new ModelAndView("redirect:adGroup.do?action=list&currentPage=" + form.getCurrentPage() + "&sortCol="
                        + form.getSortCol() + "&asce=" + form.getAsce() + "&desc=" + form.getDesc() + "&adGroupName="
                        + form.getAdGroupName(), Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    @RequestMapping(params = "action=batchModIndustry")
    public ModelAndView batchModIndustry(AdGroupForm form, HttpServletResponse response, HttpServletRequest request)
            throws NumberFormatException, InvalidInfoException {
        String[] arrAdGroupId = form.getAdGroupId().split(",");

        for (int i = 0; i < arrAdGroupId.length; i++) {
            String adGroupId = arrAdGroupId[i];
            if (StringUtils.isNotEmpty(adGroupId)) {
                Long memberId = getLogonMemberId();
                CoreAdGroup coreAdGroup = adGroupService.queryAdGroupByAdGroupId(Integer.parseInt(adGroupId), memberId);
                coreAdGroup.setAdTagSoftType(form.getAdTagSoftType());
                coreAdGroup.setUpdTime(new Date());
                adGroupService.txUpdateAdGroup(coreAdGroup);
            }
        }
        CookieUtils.setTipMessageCookie(request, response, CommonDef.opera.RETURN_SUCCESS);
        ModelAndView mv =
                new ModelAndView("redirect:adGroup.do?action=list&currentPage=" + form.getCurrentPage() + "&sortCol="
                        + form.getSortCol() + "&asce=" + form.getAsce() + "&desc=" + form.getDesc() + "&adGroupName="
                        + form.getAdGroupName(), Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    // 批量修改所属地区
    @RequestMapping(params = "action=batchModLoclType")
    public ModelAndView batchModLoclType(AdGroupForm form, HttpServletResponse response, HttpServletRequest request)
            throws NumberFormatException, InvalidInfoException {
        String[] arrAdGroupId = form.getAdGroupId().split(",");

        for (int i = 0; i < arrAdGroupId.length; i++) {
            String adGroupId = arrAdGroupId[i];
            if (StringUtils.isNotEmpty(adGroupId)) {
                Long memberId = getLogonMemberId();
                CoreAdGroup coreAdGroup = adGroupService.queryAdGroupByAdGroupId(Integer.parseInt(adGroupId), memberId);
                coreAdGroup.setAdLoclType(form.getCoreAdGroup().getAdLoclType());
                coreAdGroup.setAdLoclInfo(form.getCoreAdGroup().getAdLoclInfo());
                coreAdGroup.setUpdTime(new Date());

                CoreAdGroupextend adGroupextend =
                        adGroupExtendService.queryAdGroupExtendById(coreAdGroup.getAdGroupId());
                String position = form.getExact();
                position = ";" + position;
                if (adGroupextend == null) {
                    adGroupextend = new CoreAdGroupextend();
                }
                adGroupextend.setGeographicalPosition(position);

                adGroupService.txSaveOrUpdateAdGroup(coreAdGroup, adGroupextend);
            }
        }
        CookieUtils.setTipMessageCookie(request, response, CommonDef.opera.RETURN_SUCCESS);
        ModelAndView mv =
                new ModelAndView("redirect:adGroup.do?action=list&currentPage=" + form.getCurrentPage() + "&sortCol="
                        + form.getSortCol() + "&asce=" + form.getAsce() + "&desc=" + form.getDesc() + "&adGroupName="
                        + form.getAdGroupName(), Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 发布广告组广告
     * 
     * @param form
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=issue")
    public ModelAndView issueGroup(AdGroupForm form, HttpServletResponse response, HttpServletRequest request)
            throws InvalidInfoException {
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        BaseRole role = memberInfo.getRole();
        if (role.isGeneral() || role.isDev()) {
            form.getErrors().put("SUSPEND_ERROR_REQUIRED", ErrorMessages.SUSPEND_ERROR_REQUIRED);
            return selectAll(form);
        }
        String adGroupId = form.getAdGroupId();
        if (StringUtils.isNotEmpty(adGroupId)) {
            CoreAdGroup coreAdGroup = adGroupService.queryAdGroupByAdGroupId(Integer.parseInt(adGroupId), memberId);
            coreAdGroup.setSuspendType(CommonDef.appCon.CON_SUSPEND);
            adGroupService.txUpdateAdGroup(coreAdGroup);
            // 根据广告组Id查询旗下广告
            List<CoreAd> listAd = adService.findAdList(coreAdGroup);
            adService.txSendCoreAd(listAd);
        }
        CookieUtils.setTipMessageCookie(request, response, CommonDef.opera.RETURN_SUCCESS);
        ModelAndView mv =
                new ModelAndView("redirect:adGroup.do?action=selectAll&currentPage=" + form.getCurrentPage() + "&asce="
                        + form.getAsce() + "&timeSlotFlag=" + form.getTimeSlotFlag() + "&startTime="
                        + form.getStartTime() + "&endTime=" + form.getEndTime() + "&adGroupName="
                        + form.getAdGroupName() + "&campaignId=" + form.getCampaignId(), Constants.DEFAULT_COMMAND,
                        form);
        return mv;
    }

    /**
     * 批量发布广告组
     * 
     * @param form
     * @param response
     * @param request
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=batchIssue")
    public ModelAndView batchIssueGroup(AdGroupForm form, HttpServletResponse response, HttpServletRequest request)
            throws InvalidInfoException {
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        BaseRole role = memberInfo.getRole();
        if (role.isGeneral() || role.isDev()) {
            form.getErrors().put("SUSPEND_ERROR_REQUIRED", ErrorMessages.SUSPEND_ERROR_REQUIRED);
            return selectAll(form);
        }

        String[] arrAdGroupId = form.getAdGroupId().split(",");
        for (int i = 0; i < arrAdGroupId.length; i++) {
            String adGroupId = arrAdGroupId[i];
            if (StringUtils.isNotEmpty(adGroupId)) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("memberId", memberId.toString());
                params.put("adGroupId", adGroupId);
                params.put("suspendType", CommonDef.appCon.CON_SUSPEND);
                adGroupService.updateAdSuspendType(params);
                CoreAdGroup coreAdGroup = adGroupService.queryAdGroupByAdGroupId(Integer.parseInt(adGroupId), memberId);
                coreAdGroup.setSuspendType(CommonDef.appCon.CON_SUSPEND);
                coreAdGroup.setUpdTime(new Date());
                adGroupService.txUpdateAdGroup(coreAdGroup);
                // 根据广告组Id查询旗下广告
                List<CoreAd> listAd = adService.findAdList(coreAdGroup);
                for (int j = 0; j < listAd.size(); j++) {
                    Date date = new Date();
                    Integer adId = (listAd.get(j)).getAdId();
                    CoreAd ca = null;
                    ca = adService.findCoreAdByAdId(Integer.valueOf(adId), getLogonMemberId());
                    ca.setAdId(Integer.valueOf(adId));
                    ca.setUpdOper(getLogonEmail());
                    ca.setUpdTime(date);
                    adService.txSendCoreAd(ca, getLogonMemberId());
                    // adService.txStopCoreAd(ca, getLogonMemberId());
                }
            }
        }
        CookieUtils.setTipMessageCookie(request, response, CommonDef.issue.RETURN_SUCCESS);
        ModelAndView mv =
                new ModelAndView("redirect:adGroup.do?action=list&currentPage=" + form.getCurrentPage() + "&sortCol="
                        + form.getSortCol() + "&asce=" + form.getAsce() + "&desc=" + form.getDesc() + "&adGroupName="
                        + form.getAdGroupName(), Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 跳转广告组添加页面
     * 
     * @param form
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=addPage")
    public ModelAndView getAddAdGroupPage(AdGroupForm form) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("adgroup/group_add", Constants.DEFAULT_COMMAND, form);
        // 根据获得Id获得活动信息
        CoreCampaign campaign = new CoreCampaign();
        if (!StringUtil.isEmpty(form.getCampaignId())) {
            campaign =
                    campaignService.findCampaignById(StringUtil.integerToString(form.getCampaignId()),
                            getLogonMemberId());
        }
        else {
            // 没有活动ID不能添加广告组
            return selectAll(form);
        }
        mv.addObject("campaign", campaign);
        // 设备树数据
        // String dataTree = adGroupService.getTreeString();
        // mv.addObject("dataTree", dataTree);
        DictionaryGlobalRegion dictionaryGlobalRegion = new DictionaryGlobalRegion();
        dictionaryGlobalRegion.setRegionType("1");
        List<DictionaryGlobalRegion> proListBean = adGroupService.queryProvList(dictionaryGlobalRegion);
        form.setProListBean(proListBean);
        // 所属行业添加
        List<Dictionary> industryInvolved = new ArrayList<Dictionary>();
        if (null != adService.txQueryDictionaryList()) {
            industryInvolved = adService.txQueryDictionaryList();
            form.setIndustryInvolved(industryInvolved);
        }
        List<Dictionary> arr = adGroupService.queryDictionaryByType1();
        form.setArr(arr);
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 添加广告组新页面
     * 
     * @param form
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=newAddPage")
    public ModelAndView newGetAddAdGroupPage(AdGroupForm form) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("adgroup/new_group_add", Constants.DEFAULT_COMMAND, form);

        // 设备树数据
        // String dataTree = adGroupService.getTreeString();
        // mv.addObject("dataTree", dataTree);
        DictionaryGlobalRegion dictionaryGlobalRegion = new DictionaryGlobalRegion();
        dictionaryGlobalRegion.setRegionType("1");
        List<DictionaryGlobalRegion> proListBean = adGroupService.queryProvList(dictionaryGlobalRegion);
        form.setProListBean(proListBean);
        // 所属行业添加
        List<Dictionary> industryInvolved = new ArrayList<Dictionary>();
        if (null != adService.txQueryDictionaryList()) {
            industryInvolved = adService.txQueryDictionaryList();
            form.setIndustryInvolved(industryInvolved);
        }
        List<Dictionary> arr = adGroupService.queryDictionaryByType1();
        form.setArr(arr);
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 广告组设置页面
     * 
     * @param form
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=adGroupSet")
    public ModelAndView adGroupSet(AdGroupForm form) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("adgroup/adgroup_set", Constants.DEFAULT_COMMAND, form);

        // 设备树数据
        // String dataTree = adGroupService.getTreeString();
        // mv.addObject("dataTree", dataTree);
        DictionaryGlobalRegion dictionaryGlobalRegion = new DictionaryGlobalRegion();
        dictionaryGlobalRegion.setRegionType("1");
        List<DictionaryGlobalRegion> proListBean = adGroupService.queryProvList(dictionaryGlobalRegion);
        form.setProListBean(proListBean);
        // 所属行业添加
        List<Dictionary> industryInvolved = new ArrayList<Dictionary>();
        if (null != adService.txQueryDictionaryList()) {
            industryInvolved = adService.txQueryDictionaryList();
            form.setIndustryInvolved(industryInvolved);
        }
        List<Dictionary> arr = adGroupService.queryDictionaryByType1();
        form.setArr(arr);
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 广告组设置页面
     * 
     * @param form
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=adGroupEdit")
    public ModelAndView adGroupEdit(AdGroupForm form) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("adgroup/adgroup_set", Constants.DEFAULT_COMMAND, form);

        // 设备树数据
        // String dataTree = adGroupService.getTreeString();
        // mv.addObject("dataTree", dataTree);
        DictionaryGlobalRegion dictionaryGlobalRegion = new DictionaryGlobalRegion();
        dictionaryGlobalRegion.setRegionType("1");
        List<DictionaryGlobalRegion> proListBean = adGroupService.queryProvList(dictionaryGlobalRegion);
        form.setProListBean(proListBean);
        // 所属行业添加
        List<Dictionary> industryInvolved = new ArrayList<Dictionary>();
        if (null != adService.txQueryDictionaryList()) {
            industryInvolved = adService.txQueryDictionaryList();
            form.setIndustryInvolved(industryInvolved);
        }
        List<Dictionary> arr = adGroupService.queryDictionaryByType1();
        form.setArr(arr);
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 取得设备树数据
     * 
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @RequestMapping(params = "action=treeData")
    public ModelAndView treeData(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        String str = adGroupService.getTreeString();
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().print(str);
        }
        catch (IOException e) {
            getLogger().error("treeData error", e);
        }
        return null;
    }

    /**
     * 广告组添加
     * 
     * @param form
     * @return
     * @throws InvalidInfoException
     * @throws OperationNotSupportException
     */
    @RequestMapping(params = "action=add") 

    public ModelAndView addAdGroup(AdGroupForm form, HttpServletResponse response, HttpServletRequest request) throws InvalidInfoException, OperationNotSupportException {

        ModelAndView mv = new ModelAndView("adgroup/new_group_add", Constants.DEFAULT_COMMAND, form);

        CoreMemberInfo memberInfo = getMemberInfo();
        BaseRole role = memberInfo.getRole();

        // 选择了广告组
        if (form.getAdId() != null) {
            // 未分组
            if (form.getCoreAdGroup().getAdGroupId().toString().equals("0")) {
                form.setFlag("0");
            }
            else {
                // 选择不是未分组，如果还是草稿，则改为待审核
                if (form.getFlag().equals("0") && !form.getCoreAdGroup().getAdGroupId().toString().equals("0")
                        && request.getParameter("submitStatus").equals("1")){
                    form.setFlag("1");
                }
            }
            
            if (!role.isSaveAdDirect()) {
                form.setFlag("0");
            }

        }

        int id = 0;
        // 广告组为不等于-1 则为移动 广告的广告组ID
        if (form.getCoreAdGroup().getAdGroupId() != null && -1 != form.getCoreAdGroup().getAdGroupId()) {
            // form.form2domain();
            // CoreAd ca = new CoreAd();
            CoreAd ca = adService.findCoreAdByAdId(Integer.valueOf(form.getAdId()), getLogonMemberId());
            ca.setFlag(form.getFlag());
            Date date = new Date();
            // ca = adService.findCoreAdByAdId(Integer.valueOf(form.getAdId()), getLogonMemberId());
            ca.setAdGroupId(form.getCoreAdGroup().getAdGroupId());
            ca.setUpdOper(getLogonEmail());
            ca.setUpdTime(date);
            adService.txDeleveCoreAd(ca, getLogonMemberId());
        }
        else {
            // form.form2domain();
            CoreAdGroup adGroup = form.getCoreAdGroup();
            String exact = form.getExact();
            String localInfo = adGroup.getAdLoclInfo();
            if (StringUtils.isNotEmpty(localInfo)) {
                form.setAdLoclInfo(localInfo);
            }
            String adLoclType = adGroup.getAdLoclType();
            if (adLoclType.equals("2")) {
                if (StringUtils.isEmpty(exact)) {
                    form.getErrors().put("coreAdGroup.adLoclType", ErrorMessages.LOCAL_ERROR);
                }
            }
            if (adLoclType.equals("1")) {
                if (StringUtils.isEmpty(adGroup.getAdLoclInfo())) {
                    form.getErrors().put("coreAdGroup.adLoclType", ErrorMessages.LOCAL_ERROR);
                }
            }
            if (!form.getErrors().isEmpty()) {
                form.setAddFlag("no");
                form.setAdTagSp(adGroup.getAdTagSp());
                // return getAddAdGroupPage(form);
                return newGetAddAdGroupPage(form);
            }
            adGroup.setMemberId(getLogonMemberId());
            adGroup.setAddOper(getLogonEmail());
            adGroup.setUpdOper(getLogonEmail());
            adGroup.setUpdTime(new Date());
            adGroup.setCampaignId(new Integer("1"));
            id = adGroupService.txCreateAdGroup(adGroup);
            CoreAdGroupextend adGroupextend = new CoreAdGroupextend();
            if (adLoclType.equals("2")) {
                adGroupextend.setAdGroupId(id);
                String position = exact;
                position = ";" + position;
                adGroupextend.setGeographicalPosition(position);
                adGroupExtendService.txAddAdGroupExtend(adGroupextend);
            }
            if (StringUtils.isNotBlank(form.getAdId())) {
                CoreAd ca = adService.findCoreAdByAdId(Integer.valueOf(form.getAdId()), getLogonMemberId());
                ca.setFlag(form.getFlag());
                ca.setAdGroupId(id);
                ca.setUpdOper(getLogonEmail());
                ca.setUpdTime(new Date());
                adService.txDeleveCoreAd(ca, getLogonMemberId());
            }
        }
        mv =
                new ModelAndView("redirect:adGroup.do?action=list&currentPage=" + form.getCurrentPage(),
                        Constants.DEFAULT_COMMAND, form);

        
        if (!role.isSaveAdDirect()  && form.getAdId()!=null && request.getParameter("showAuth")==null)  {
              CookieUtils.setAuthFlagCookie(response, form.getAdId(), Constants.NEED_AUTH);
              String url = "/adGroup.do?action=adGroupSet&adId="+form.getAdId()+"&flag=0&adGroupId="+form.getAdGroupId();
              return new ModelAndView("redirect:" + url);

        }

        return mv;
    }

    /**
     * 跳转广告组修改页面
     * 
     * @param form
     * @param request
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=editPage")
    public ModelAndView getEditAdGroupPage(AdGroupForm form, HttpServletRequest request) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("adgroup/group_edit", Constants.DEFAULT_COMMAND, form);
        String id = form.getAdGroupId();
        CoreAdGroup coreAdGroup = null;
        Long memberId = getLogonMemberId();
        if (StringUtils.isEmpty(form.getEditErrorFlag())) {
            coreAdGroup = adGroupService.queryAdGroupByAdGroupId(Integer.parseInt(id), memberId);
        }
        else {
            coreAdGroup = form.getCoreAdGroup();
        }
        String localInfo = coreAdGroup.getAdLoclInfo();
        if (StringUtils.isNotEmpty(localInfo)) {
            form.setAdLoclInfo(localInfo);
        }
        form.setCoreAdGroup(coreAdGroup);
        // CoreCampaign campaign = campaignService.findCampaignById(coreAdGroup.getCampaignId(), memberId);
        DictionaryGlobalRegion dictionaryGlobalRegion = new DictionaryGlobalRegion();
        dictionaryGlobalRegion.setRegionType("1");
        List<DictionaryGlobalRegion> proListBean = adGroupService.queryProvList(dictionaryGlobalRegion);
        form.setProListBean(proListBean);
        String adLoclType = coreAdGroup.getAdLoclType();
        if (adLoclType.equals("2")) {
            CoreAdGroupextend adGroupextend = adGroupExtendService.queryAdGroupExtendById(coreAdGroup.getAdGroupId());
            if (StringUtils.isEmpty(form.getEditErrorFlag())) {
                String exact =
                        adGroupextend.getGeographicalPosition().substring(1,
                                adGroupextend.getGeographicalPosition().length());
                // TODO组装信息
                // exact = adGroupService.exactChange(exact);
                form.setExact(exact);
            }
        }
        // 所属行业添加
        List<Dictionary> industryInvolved = new ArrayList<Dictionary>();
        if (null != adService.txQueryDictionaryList()) {
            industryInvolved = adService.txQueryDictionaryList();
            form.setIndustryInvolved(industryInvolved);
        }
        form.setEditFlagCheck("edit");
        List<Dictionary> arr = adGroupService.queryDictionaryByType1();
        form.setArr(arr);
        form.setAdTagSp(coreAdGroup.getAdTagSp());
        mv.addObject("form", form);
        // mv.addObject("campaign", campaign);
        return mv;
    }

    /**
     * 广告组修改
     * 
     * @param form
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=edit")
    public ModelAndView editAdGroup(AdGroupForm form, HttpServletRequest request) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("adgroup/group_edit", Constants.DEFAULT_COMMAND, form);

        CoreAdGroup coreAdGroup = form.getCoreAdGroup();
        if (coreAdGroup.getAdLoclType().equals("2")) {
            if (StringUtils.isEmpty(form.getExact())) {
                form.getErrors().put("coreAdGroup.adLoclType", ErrorMessages.LOCAL_ERROR);
                form.setEditErrorFlag("true");
            }
        }
        if (coreAdGroup.getAdLoclType().equals("1")) {
            if (StringUtils.isEmpty(coreAdGroup.getAdLoclInfo())) {
                form.getErrors().put("coreAdGroup.adLoclType", ErrorMessages.LOCAL_ERROR);
                form.setEditErrorFlag("true");
            }
        }
        if (!form.getErrors().isEmpty()) {
            form.setEditErrorFlag("true");
            return getEditAdGroupPage(form, request);
        }
        form.form2domain();
        coreAdGroup.setUpdOper(getLogonEmail());
        coreAdGroup.setUpdTime(new Date());
        coreAdGroup.setMemberId(getLogonMemberId());
        adGroupService.txUpdateAdGroup(coreAdGroup);
        if (coreAdGroup.getAdLoclType().equals("2")) {
            CoreAdGroupextend adGroupextend = new CoreAdGroupextend();
            adGroupextend = adGroupExtendService.queryAdGroupExtendById(form.getCoreAdGroup().getAdGroupId());
            if (null != adGroupextend) {
                // TODO组装信息
                // String position = adGroupService.exact(form.getExact());
                String position = form.getExact();
                position = ";" + position;
                adGroupextend.setGeographicalPosition(position);
                adGroupExtendService.txUpdateAdGroupExtend(adGroupextend);
            }
            else {
                adGroupextend = new CoreAdGroupextend();
                // TODO组装信息
                // String position = adGroupService.exact(form.getExact());
                String position = form.getExact();
                position = ";" + position;
                adGroupextend.setAdGroupId(form.getCoreAdGroup().getAdGroupId());
                adGroupextend.setGeographicalPosition(position);
                adGroupExtendService.txAddAdGroupExtend(adGroupextend);
            }

        }
        mv = new ModelAndView("redirect:/adGroup.do?action=list");
        return mv;
    }

    /**
     * @param form
     * @param request
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=batchEdit")
    public ModelAndView batchEditAdGroup(AdGroupForm form, HttpServletResponse response, HttpServletRequest request)
            throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("adgroup/group_edit", Constants.DEFAULT_COMMAND, form);
        CoreAdGroup coreAdGroup = form.getCoreAdGroup();
        coreAdGroup.setUpdOper(getLogonEmail());
        coreAdGroup.setUpdTime(new Date());
        coreAdGroup.setMemberId(getLogonMemberId());
        String[] arrAdGroupId = form.getAdGroupId().split(",");
        for (int i = 0; i < arrAdGroupId.length; i++) {
            String adGroupId = arrAdGroupId[i];
            coreAdGroup.setAdGroupId(new Integer(adGroupId));
            adGroupService.txUpdateAdGroup(coreAdGroup);
        }
        CookieUtils.setTipMessageCookie(request, response, CommonDef.opera.RETURN_SUCCESS);
        // mv = new ModelAndView("redirect:/adGroup.do?action=list&memberId=" + getLogonMemberId());
        mv =
                new ModelAndView("redirect:adGroup.do?action=list&currentPage=" + form.getCurrentPage() + "&sortCol="
                        + form.getSortCol() + "&asce=" + form.getAsce() + "&desc=" + form.getDesc() + "&adGroupName="
                        + form.getAdGroupName(), Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 广告组复制
     * 
     * @param form
     * @param request
     * @return
     * @throws InvalidInfoException
     * @throws NumberFormatException
     */
    @RequestMapping(params = "action=copyPage")
    public ModelAndView copyAdGroupPage(AdGroupForm form, HttpServletRequest request) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("adgroup/group_copy", Constants.DEFAULT_COMMAND, form);
        if (!form.getErrors().isEmpty()) {
            return mv;
        }
        String id = request.getParameter("groupId");
        CoreAdGroup coreAdGroup = adGroupService.queryAdGroupByAdGroupId(Integer.parseInt(id), getLogonMemberId());
        form.setCoreAdGroup(coreAdGroup);
        return mv;
    }

    /**
     * 广告组复制
     * 
     * @param form
     * @param request
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=copy")
    public ModelAndView copyAdGroup(AdGroupForm form) throws InvalidInfoException {
        return selectAll(form);
    }

    /**
     * 广告组复制
     * 
     * @param form
     * @param request
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=doCopy")
    public ModelAndView doCopy(AdGroupForm form) throws InvalidInfoException {
        String adGroupId = form.getAdGroupIdTemp();
        String adGroupName = form.getAdGroupNameTemp();
        String adGroupNameCopy = form.getAdGroupNameCopyTemp();
        CoreAdGroup coreAdGroup = new CoreAdGroup();
        if (StringUtils.isNotEmpty(adGroupId)) {
            coreAdGroup.setAdGroupId(StringUtil.stringToInteger(adGroupId));
            if (adGroupName.equals(adGroupNameCopy)) {
                coreAdGroup.setAdGroupName(adGroupNameCopy + "_copy");
            }
            else {
                if (adGroupNameCopy.length() > 50) {
                    form.getErrors().put("CAMPAIGN_ERROR", ErrorMessages.ADGROUP_ERROR);
                    return selectAll(form);
                }
                coreAdGroup.setAdGroupName(adGroupNameCopy);
            }

            coreAdGroup.setAddOper(getLogonEmail());
            coreAdGroup.setUpdOper(getLogonEmail());
            adGroupService.txCopy(coreAdGroup, getLogonMemberId());
        }
        return new ModelAndView("redirect:/adGroup.do?action=selectAll&campaignId=" + form.getCampaignId()
                + "&currentPage=" + form.getCurrentPage());
    }

    /**
     * 广告组删除
     * 
     * @param form
     * @param request
     * @return
     */
    @RequestMapping(params = "action=deletePage")
    public ModelAndView deleteAdGroup(AdGroupForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!form.getErrors().isEmpty()) {
            return new ModelAndView("adgroup/group_list", Constants.DEFAULT_COMMAND, form);
        }
        String id = form.getAdGroupId();
        CoreAdGroup coreAdGroup = new CoreAdGroup();
        if (StringUtils.isNotEmpty(id)) {
            coreAdGroup.setAdGroupId(StringUtil.stringToInteger(id));
            coreAdGroup.setDelOper(getLogonEmail());
            coreAdGroup.setDelTime(new Date());
            coreAdGroup.setMemberId(getLogonMemberId());
            adGroupService.txDeleteAdGroupByAdGroupId(coreAdGroup);
        }
        CookieUtils.setTipMessageCookie(request, response, CommonDef.delete.RETURN_SUCCESS);
        ModelAndView mv =
                new ModelAndView("redirect:adGroup.do?action=selectAll&currentPage=" + form.getCurrentPage() + "&asce="
                        + form.getAsce() + "&timeSlotFlag=" + form.getTimeSlotFlag() + "&startTime="
                        + form.getStartTime() + "&endTime=" + form.getEndTime() + "&adGroupName="
                        + form.getAdGroupName() + "&campaignId=" + form.getCampaignId(), Constants.DEFAULT_COMMAND,
                        form);
        return mv;
    }

    /**
     * 批量删除广告组
     * 
     * @param form
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(params = "action=batchDelAdGroup")
    public ModelAndView batchDelAdGroup(AdGroupForm form, HttpServletRequest request, HttpServletResponse response) {
        if (!form.getErrors().isEmpty()) {
            return new ModelAndView("adgroup/group_list", Constants.DEFAULT_COMMAND, form);
        }
        // String id = form.getAdGroupId();
        String[] arrAdGroupId = form.getAdGroupId().split(",");
        for (int i = 0; i < arrAdGroupId.length; i++) {
            String adGroupId = arrAdGroupId[i];
            CoreAdGroup coreAdGroup = new CoreAdGroup();
            if (StringUtils.isNotEmpty(adGroupId)) {
                coreAdGroup.setAdGroupId(StringUtil.stringToInteger(adGroupId));
                coreAdGroup.setDelOper(getLogonEmail());
                coreAdGroup.setDelTime(new Date());
                coreAdGroup.setMemberId(getLogonMemberId());
                adGroupService.txDeleteAdGroupByAdGroupId(coreAdGroup);
                // List<CoreAd> listAd = adService.findAdList(coreAdGroup);
                // for (int j = 0; j < listAd.size(); j++) {
                // Date date = new Date();
                // Integer adId = (listAd.get(j)).getAdId();
                // CoreAd ca = null;
                // ca = adService.findCoreAdByAdId(Integer.valueOf(adId), getLogonMemberId());
                // ca.setAdId(Integer.valueOf(adId));
                // ca.setUpdOper(getLogonEmail());
                // ca.setUpdTime(date);
                // adService.txDeleteCoreAd(ca, getLogonMemberId());
                // }

            }
        }

        CookieUtils.setTipMessageCookie(request, response, CommonDef.delete.RETURN_SUCCESS);
        ModelAndView mv =
        // new ModelAndView("redirect:adGroup.do?action=selectAll&currentPage=" + form.getCurrentPage() + "&asce="
                // + form.getAsce() + "&timeSlotFlag=" + form.getTimeSlotFlag() + "&startTime="
                // + form.getStartTime() + "&endTime=" + form.getEndTime() + "&adGroupName="
                // + form.getAdGroupName() + "&campaignId=" + form.getCampaignId(), Constants.DEFAULT_COMMAND,
                // form);
                new ModelAndView("redirect:adGroup.do?action=list&currentPage=" + form.getCurrentPage()
                        + form.getAsce() + "&timeSlotFlag=" + form.getTimeSlotFlag(), Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 显示所有省
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=proTree")
    public ModelAndView proTree(AdGroupForm form) {
        DictionaryGlobalRegion dictionaryGlobalRegion = new DictionaryGlobalRegion();
        dictionaryGlobalRegion.setRegionType("1");
        List<DictionaryGlobalRegion> l = adGroupService.queryProvList(dictionaryGlobalRegion);
        return null;
    }

    /**
     * 显示省下面的市
     * 
     * @param form
     * @return
     * @throws JSONException
     */
    @RequestMapping(params = "action=cityTree")
    public ModelAndView cityTree(AdGroupForm form, HttpServletResponse response) throws JSONException {
        DictionaryGlobalRegion dictionaryGlobalRegion = new DictionaryGlobalRegion();
        String json = "";
        if (StringUtils.isNotEmpty(form.getProId())) {
            dictionaryGlobalRegion.setRegionId(Short.valueOf(form.getProId()));
            List<DictionaryGlobalRegion> cityList = adGroupService.queryCityList(dictionaryGlobalRegion);
            json = adGroupService.getJson(cityList, form.getProId()).toString();
        }
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/json");
        byte[] bs;
        try {
            bs = json.getBytes("utf-8");
            response.setContentLength(bs.length);
            OutputStream os = response.getOutputStream();
            os.write(bs);
            os.flush();
            os.close();
        }
        catch (Exception e) {
            getLogger().error("cityTree error", e);
        }
        return null;
    }

    /**
     *根据用户显示广告组
     */
    @RequestMapping(params = "action=list")
    public ModelAndView list(AdGroupForm form, HttpServletRequest request) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("adgroup/group_member_list", Constants.DEFAULT_COMMAND, form);
        // 默认UPD_TIME降序
        if (StringUtils.isEmpty(form.getSortCol())) {
            form.setDesc("1");
            form.setSortCol("UPD_TIME");
        }

        form.form2domain();
        // 根据获得Id获得活动信息
        // CoreCampaign campaign = new CoreCampaign();
        Long memberId = getLogonMemberId();
        // String campaignId = form.getCampaignId();
        String adGroupName = form.getAdGroupName();
        // if (!StringUtil.isEmpty(form.getCampaignId())) {
        // campaign = campaignService.findCampaignById(StringUtil.integerToString(campaignId), memberId);
        // }
        int fromRecord = Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1);
        int pageSize = Integer.parseInt(form.getPageSize());

        // 封装数据
        Map<String, String> params = new HashMap<String, String>();
        if (StringUtils.isEmpty(form.getTimeSlotFlag())) {
            if (StringUtils.isNotEmpty(form.getAdGroupName())) {
                if (!adGroupName.contains("/")) {
                    params.put("escape", CommonDef.escape.ESCAPE);
                }
                adGroupName = StringUtil.replaceChar(adGroupName);
                params.put("adGroupName", adGroupName);
                form.setAdGroupName(adGroupName);
            }
        }
        params.put("descFlag", form.getDesc());
        params.put("asceFlag", form.getAsce());
        params.put("pageSize", form.getPageSize());
        params.put("sortCol", form.getSortCol());
        params.put("memberId", memberId.toString());

        List<CoreAdGroup> cList = new ArrayList<CoreAdGroup>();
        int groupSize = 0;

        groupSize = adGroupService.queryCountTotalByTimeSlot(params);
        if (Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1) >= groupSize) {
            params.put("currentPage", "1");
        }
        else {
            params.put("currentPage", form.getCurrentPage());
        }
        params.put("fromRecord", (new Integer(params.get("currentPage")).intValue() - 1)
                * new Integer(form.getPageSize()).intValue() + "");
        cList = adGroupService.queryAdGroupList(params);
        int defaultAdCout = adGroupService.queryCountDefaultAdCount(params);

        form.setTotalCount(String.valueOf(groupSize));
        form.setGroupList(cList);

        // 添加地区数据
        DictionaryGlobalRegion dictionaryGlobalRegion = new DictionaryGlobalRegion();
        dictionaryGlobalRegion.setRegionType("1");
        List<DictionaryGlobalRegion> proListBean = adGroupService.queryProvList(dictionaryGlobalRegion);
        form.setProListBean(proListBean);
        // 所属行业添加
        List<Dictionary> industryInvolved = new ArrayList<Dictionary>();
        if (null != adService.txQueryDictionaryList()) {
            industryInvolved = adService.txQueryDictionaryList();
            form.setIndustryInvolved(industryInvolved);
        }
        List<Dictionary> arr = adGroupService.queryDictionaryByType1();
        form.setArr(arr);
        // mv.addObject("campaign", campaign);
        mv.addObject("defaultAdCout", defaultAdCout);
        mv.addObject("p", form);
        return mv;
    }

    /**
     *根据用户显示广告组JSON
     * 
     * @throws IOException
     */
    @RequestMapping(params = "action=selectAllByMemberIdJSON")
    public void selectAllByMemberIdJSON(AdGroupForm form, HttpServletRequest request, HttpServletResponse response)
            throws InvalidInfoException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "application/json");
        if (StringUtils.isEmpty(form.getSortCol())) {
            form.setDesc("1");
            form.setSortCol("UPD_TIME");
        }
        Long memberId = getLogonMemberId();
        String adGroupName = form.getAdGroupName();
        Map<String, String> params = new HashMap<String, String>();
        if (StringUtils.isEmpty(form.getTimeSlotFlag())) {
            if (StringUtils.isNotEmpty(form.getAdGroupName())) {
                if (!adGroupName.contains("/")) {
                    params.put("escape", CommonDef.escape.ESCAPE);
                }
                adGroupName = StringUtil.replaceChar(adGroupName);
                params.put("adGroupName", adGroupName);
                form.setAdGroupName(adGroupName);
            }
        }
        params.put("sortCol", form.getSortCol());
        params.put("memberId", memberId.toString());
        List<CoreAdGroup> cList = new ArrayList<CoreAdGroup>();
        cList = adGroupService.queryAdGroupList(params);
        JSONArray jSONArray = new JSONArray();
        for (CoreAdGroup coreAdGroup : cList) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("adGroupId", coreAdGroup.getAdGroupId());
            jSONObject.put("adGroupName", coreAdGroup.getAdGroupName());

            if (StringUtils.isNotBlank(coreAdGroup.getAdTagSoftType())) {
                String tmpStr =
                        dictionaryRefreshable.getValueByTypeKeyAndValueKey("INDUSTRY_INVOLVED", coreAdGroup
                                .getAdTagSoftType());
                jSONObject.put("adTagSoftType", tmpStr);
            }

            if (StringUtils.isNotBlank(coreAdGroup.getAdTagSp())) {
                String[] AdTagSp = null;
                if (coreAdGroup.getAdTagSp().contains(",")) {
                    AdTagSp = coreAdGroup.getAdTagSp().split(",");
                }
                else {
                    AdTagSp = new String[]{coreAdGroup.getAdTagSp()};
                }
                StringBuilder sb = new StringBuilder();
                for (String str : AdTagSp) {
                    String tmpStr = dictionaryRefreshable.getValueByTypeKeyAndValueKey("TAG_SP", str);
                    sb.append(",").append(tmpStr);
                }
                jSONObject.put("adTagSp", sb.toString().substring(1));
            }
            String local = coreAdGroup.getAdLoclType();
            if ("0".equals(local)) {
                local = "全国";
            }
            else if ("2".equals(local)) {
                local = "精确到区";
            }
            else {
                local = "常用地区";
            }
            jSONObject.put("adLoclType", local);
            jSONArray.add(jSONObject);
        }
        // getLogger().info(jSONArray.toString());
        response.getWriter().print(jSONArray.toString());
        // return jSONArray.toString();
    }

    /**
     * 判断是否有默认分组
     * 
     * @return
     */
    private boolean hasDefaultGroup() {
        Map<String, String> params = new HashMap<String, String>();
        Long memberId = getLogonMemberId();
        params.put("memberId", memberId.toString());
        int count = adGroupService.queryCountDefaultCount(params);
        return count == 0;
    }
}
