/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.CommonDef;
import com.mitian.airad.Constants;
import com.mitian.airad.ErrorMessages;
import com.mitian.airad.common.constant.AdGroupConstant;
import com.mitian.airad.common.constant.ConstantAd;
import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.common.exception.OperationNotSupportException;
import com.mitian.airad.model.CoreAd;
import com.mitian.airad.model.CoreAdGroup;
import com.mitian.airad.model.CoreBanner;
import com.mitian.airad.model.CoreCampaign;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.model.CoreRichAd;
import com.mitian.airad.model.CoreWapAd;
import com.mitian.airad.model.Dictionary;
import com.mitian.airad.service.AdGroupService;
import com.mitian.airad.service.AdService;
import com.mitian.airad.service.CampaignService;
import com.mitian.airad.service.MemberService;
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.utils.StringUtil;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.BaseRole;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.AdForm;
import com.taobao.api.ApiException;

/**
 * AdController.java 广告信息控制类
 * 
 * @author WANGZHONGWEI
 */
@Controller
@RequestMapping("/ad.do")
@RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_ADVERTISERS, RoleFactory.ROLE_DEVELOPERS,
        RoleFactory.ROLE_GENERAL, RoleFactory.ROLE_OSS_SALES})
public class AdController extends AbstractController implements AdGroupConstant, ConstantAd {
    @Autowired
    private AdService adService;

    @Autowired
    private AdGroupService adGroupService;
    @Autowired
    private CampaignService campaignService;
    @Autowired
    private MemberService memberService;

    /**
     * 查看广告组下所有广告信息
     * 
     * @param request
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=adList")
    public ModelAndView adList(HttpServletRequest request, AdForm form) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("ad/adInfo_list", Constants.DEFAULT_COMMAND, form);
        // 默认降序
        if (StringUtils.isEmpty(form.getAsce())) {
            form.setDesc("desc");
            mv.addObject("flag", "flag");
        }
        else {
            mv.addObject("", "flag");
        }
        Long memberId = getLogonMemberId();
        Map<String, String> params = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(form.getAdName())) {
            if (!form.getAdName().contains("/")) {
                params.put("escape", CommonDef.escape.ESCAPE);
            }
            String adName = StringUtil.replaceChar(form.getAdName());
            params.put("adName", form.getAdName());
        }
        params.put("flag", form.getFlag());
        params.put("memberId", memberId.toString());
        params.put("desc", form.getDesc());
        params.put("adGroupId", form.getAdGroupId());
        String groupId = form.getAdGroupId();
        if (StringUtils.isBlank(groupId) || !NumberUtils.isNumber(groupId)) {
            throw new InvalidInfoException("get agencyList Error groupId: " + groupId + " , memberId=" + memberId);
        }
        Integer adGroupIdInt = Integer.parseInt(groupId);
        CoreAdGroup cag = adGroupService.queryAdGroupByAdGroupId(adGroupIdInt, memberId);

        request.setAttribute("cag", cag);
        // int fromRecord = 0;
        // fromRecord = Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1);
        int pageSize = 0;
        pageSize = Integer.parseInt(form.getPageSize());
        params.put("pageSize", form.getPageSize());
        CoreAd ad = form.getCoreAd();
        List<CoreAd> adList = new ArrayList<CoreAd>();
        Integer adSize = 0;
        if (StringUtils.isEmpty(form.getStartTime()) && StringUtils.isEmpty(form.getEndTime())) {
            adSize = adService.countTotal(params);
            if (Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1) >= adSize) {
                params.put("currentPage", "1");
            }
            else {
                params.put("currentPage", form.getCurrentPage());
            }
            adList = adService.queryList(params);
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
            adSize = adService.countTotalByTimeSlot(params);
            if (Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1) >= adSize) {
                params.put("currentPage", "1");
            }
            else {
                params.put("currentPage", form.getCurrentPage());
            }
            adList = adService.queryListByTimeSlot(params);
        }
        form.setAdList(adList);
        form.setTotalCount(adSize.toString());
        if (!StringUtil.isEmpty(form.getCampaignId())) {
            CoreCampaign campaign =
                    campaignService.findCampaignById(StringUtil.integerToString(form.getCampaignId()), memberId);
            mv.addObject("campaign", campaign);
        }
        Map<String, Integer> map = adGroupService.preparePlatformNumMap(cag);
        cag.setPlatformNumber(map.get("platformNumber"));// 获取平台数
        cag.setOsNumber(map.get("osNumber"));// 获取终端数
        form.setAdGroup(cag);
        // 所属行业添加
        List<Dictionary> industryInvolved = new ArrayList<Dictionary>();
        if (null != adService.txQueryDictionaryList()) {
            industryInvolved = adService.txQueryDictionaryList();
            mv.addObject("industryInvolved", industryInvolved);
        }
        mv.addObject("adGroupId", groupId);
        mv.addObject("p", form);
        return mv;
    }

    /**
     * 删除广告信息
     * 
     * @param request
     * @return
     */
    @RequestMapping(params = "action=deleteAd")
    public ModelAndView deleteAdByAdId(HttpServletRequest request, AdForm form, HttpServletResponse response) {
        CoreAd ca = new CoreAd();
        ca.setAdId(form.getCoreAd().getAdId());
        ca.setUpdOper(getLogonEmail());
        ca.setDelOper(getLogonEmail());
        adService.txDeleteCoreAd(ca, getLogonMemberId());
        CookieUtils.setTipMessageCookie(request, response, CommonDef.delete.RETURN_SUCCESS);
        ModelAndView mv =
                new ModelAndView("redirect:ad.do?action=adList&currentPage=" + form.getCurrentPage() + "&asce="
                        + form.getAsce() + "&timeSlotFlag=" + form.getTimeSlotFlag() + "&startTime="
                        + form.getStartTime() + "&endTime=" + form.getEndTime() + "&adName=" + form.getAdName()
                        + "&adGroupId=" + form.getAdGroupId() + "&campaignId=" + form.getCampaignId(),
                        Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 暂停广告信息
     * 
     * @param request
     * @return
     */
    @RequestMapping(params = "action=stopAd")
    public ModelAndView stopAdByAdId(HttpServletRequest request, AdForm form, HttpServletResponse response) {
        Date date = new Date();
        CoreAd ca = new CoreAd();
        ca.setAdId(form.getCoreAd().getAdId());
        ca.setUpdOper(getLogonEmail());
        ca.setUpdTime(date);
        adService.txStopCoreAd(ca, getLogonMemberId());
        CookieUtils.setTipMessageCookie(request, response, CommonDef.suspend.RETURN_SUCCESS);
        ModelAndView mv =
                new ModelAndView("redirect:ad.do?action=adList&currentPage=" + form.getCurrentPage() + "&asce="
                        + form.getAsce() + "&timeSlotFlag=" + form.getTimeSlotFlag() + "&startTime="
                        + form.getStartTime() + "&endTime=" + form.getEndTime() + "&adName=" + form.getAdName()
                        + "&adGroupId=" + form.getAdGroupId() + "&campaignId=" + form.getCampaignId(),
                        Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 发布广告信息
     * 
     * @param request
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=issue")
    public ModelAndView sendAd(HttpServletRequest request, AdForm form, HttpServletResponse response)
            throws InvalidInfoException {
        Long memberId = getLogonMemberId();
        CoreMemberInfo memberInfo = getMemberInfo();
        BaseRole role = memberInfo.getRole();
        if (role.isDev() || role.isGeneral()) {
            form.getErrors().put("SUSPEND_ERROR_REQUIRED", ErrorMessages.SUSPEND_ERROR_REQUIRED);
            return adList(request, form);
        }
        Date date = new Date();
        CoreAd ca = new CoreAd();
        ca.setAdId(form.getCoreAd().getAdId());
        ca.setUpdOper(getLogonEmail());
        ca.setUpdTime(date);
        adService.txSendCoreAd(ca, memberId);
        CookieUtils.setTipMessageCookie(request, response, CommonDef.issue.RETURN_SUCCESS);
        ModelAndView mv =
                new ModelAndView("redirect:ad.do?action=adList&currentPage=" + form.getCurrentPage() + "&asce="
                        + form.getAsce() + "&timeSlotFlag=" + form.getTimeSlotFlag() + "&startTime="
                        + form.getStartTime() + "&endTime=" + form.getEndTime() + "&adName=" + form.getAdName()
                        + "&adGroupId=" + form.getAdGroupId() + "&campaignId=" + form.getCampaignId(),
                        Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    //    
    /**
     * 获得广告详细信息
     * 
     * @throws InvalidInfoException
     * @throws NumberFormatException
     */
    @RequestMapping(params = "action=detailEdit")
    public ModelAndView detailEdit(HttpServletRequest request, AdForm form) throws NumberFormatException,
            InvalidInfoException {
        String groupId = form.getAdGroupId();
        String campaignId = form.getCampaignId();
        String adId = form.getAdId();
        Long memberId = getLogonMemberId();
        form.setMemberId(memberId + "");
        // 获取当前活动
        CoreCampaign campaign = campaignService.findById(campaignId, memberId);
        form.setCampaign(campaign);

        // 获取广告组信息
        CoreAdGroup adGroup = adGroupService.findById(groupId, memberId);
        form.setAdGroup(adGroup);
        Integer spType = adService.isPs(adGroup.getAdTagSp());
        if (spType != null) {
            form.setMarketType(spType.toString());
        }
        // 获取广告信息
        Map<String, Object> adMap = adService.findById(adId, memberId);
        form.setCoreAd((CoreAd) adMap.get("ad"));
        form.setBanner((CoreBanner) adMap.get("banner"));
        form.setWap((CoreWapAd) adMap.get("wap"));
        form.setRichList((List<CoreRichAd>) adMap.get("richMedia"));

        //
        form.setBannerTypeDeprecated(adService.isBannerTypeDeprecated((CoreBanner) adMap.get("banner")));
        //
        ModelAndView mv = new ModelAndView("ad/ad_detail_add", Constants.DEFAULT_COMMAND, form);
        return mv;

    }

    /**
     * 提交广告
     * 
     * @throws IOException
     */
    @RequestMapping(params = "action=adDraftSave")
    public void adDraftSave(HttpServletRequest request, HttpServletResponse response, AdForm form) throws IOException {
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        BaseRole role = memberInfo.getRole();
        form.setMemberId(memberId.toString());// 获取会员idAD_OFFER
        if (StringUtils.isNotBlank(form.getAdChildNum())) {
            if (Integer.valueOf(form.getAdChildNum()) < 3) {
                form.setAdChildNum("1");
            }
        }
        if (form.getAdId() != null && form.getAdId().length() > 0) {
            adService.txUpdateCoreAdByAdId(form.getCoreAd(), Flag.DRAFT.getCode().toString(), memberId, role);
            response.getWriter().print(form.getAdId());
        }
        else {
            int adId = adService.txAddCoreAd(form.getCoreAd(), memberId, role);// 保存
            response.getWriter().print(adId);
        }
    }

    /**
     * 广告保存
     * 
     * @param request
     * @param response
     * @param form
     * @return
     * @throws IOException
     * @throws InvalidInfoException
     * @throws NumberFormatException
     * @throws OperationNotSupportException
     */
    @RequestMapping(params = "action=adSave")
    public ModelAndView adSave(HttpServletRequest request, HttpServletResponse response, AdForm form)
            throws IOException, NumberFormatException, InvalidInfoException, OperationNotSupportException {

        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        BaseRole role = memberInfo.getRole();
        form.setMemberId(memberId.toString());
        CoreAd coreAd = form.getCoreAd();
        if (Integer.parseInt(form.getAdType()) == AdType.Wap.getCode()) {
            coreAd.setBackground(-1);
        }
        // 根据当前用户是否身份认证设置广告id
        Integer adFlag = role.isSaveAdDirect() ? Flag.CHECK.getCode() : Flag.DRAFT.getCode();
        int flag = adService.txUpdateCoreAdByAdId(coreAd, adFlag.toString(), memberId, role);
        if (flag == 1) {
            adService.compute(Integer.parseInt(form.getAdId()), memberId, role);
            String url = "ad.do?action=adList&adGroupId=" + form.getAdGroupId() + "&campaignId=" + form.getCampaignId();
            if (role.isSaveAdDirect()) {
                return new ModelAndView("redirect:" + url);
            }
            else {
                CookieUtils.setAuthFlagCookie(response, coreAd.getAdId() + "", Constants.NEED_AUTH);
            }
        }
        return detailEdit(request, form);
    }

    /**
     * 提交banner草稿
     * 
     * @throws IOException
     */
    @RequestMapping(params = "action=bannerDraftSave")
    public ModelAndView bannerDraftSave(AdForm form) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        BaseRole role = memberInfo.getRole();
        form.getCoreAd().setMemberId(memberId);// 获取会员idAD_OFFER
        if (StringUtils.isEmpty(form.getBannerId())) {
            form.form2domain();
            Integer bannerId = adService.txAddCoreBanner(form.getBanner(), memberId);// 保存
            result.put("bannerId", bannerId);
        }
        else {
            form.form2domain();
            int falg = adService.txUpdateCoreBannerByAdId(form.getBanner(), memberId);
            result.put("falg", falg);
        }
        adService.compute(Integer.parseInt(form.getAdId()), memberId, role);// 计算广告价格
        return new ModelAndView("jsonView", result);
    }

    /**
     * 提交wap草稿
     * 
     * @throws IOException
     */
    @RequestMapping(params = "action=wapDraftSave")
    public void wapDraftSave(HttpServletRequest request, HttpServletResponse response, AdForm form) throws IOException {
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        BaseRole role = memberInfo.getRole();
        form.getCoreAd().setMemberId(memberId);// 获取会员idAD_OFFER
        if (form.getWapId() != null && form.getWapId().length() > 0) {
            Integer flag = adService.txUpdateCoreWap(form.getWap(), memberId);
            adService.compute(Integer.parseInt(form.getAdId()), memberId, role);// 计算广告价格
            response.getWriter().print("{'flag':'" + flag + "'}");
        }
        else {
            Integer wapId = adService.txAddCoreWap(form.getWap(), memberId);// 保存
            adService.compute(Integer.parseInt(form.getAdId()), memberId, role);// 计算广告价格
            response.getWriter().print("{'wapId':'" + wapId + "'}");
        }
    }

    /**
     * 提交rich草稿
     * 
     * @throws IOException
     */
    @RequestMapping(params = "action=richDraftSave")
    public void richDraftSave(HttpServletRequest request, HttpServletResponse response, AdForm form) throws IOException {
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        BaseRole role = memberInfo.getRole();
        String delRichIdSp = request.getParameter("delRichId");
        form.getCoreAd().setMemberId(memberId);// 获取会员idAD_OFFER
        if (form.getRichId() != null && form.getRichId().length() > 0) {
            Integer flag = 0;
            CoreRichAd richAd = adService.txUpdateCoreRich(form.getRich(), memberId);//
            adService.compute(Integer.parseInt(form.getAdId()), memberId, role);// 计算广告价格
            response.getWriter().print(
                    "{'flag':'" + richAd.getFlag() + "','uiId':'" + form.getSort() + "','relId':'" + richAd.getRelId()
                            + "','showType':'" + form.getShowType() + "'}");
        }
        else {
            CoreRichAd rich = adService.txAddCoreRich(form.getRich(), memberId);// 保存
            adService.compute(Integer.parseInt(form.getAdId()), memberId, role);// 计算广告价格
            response.getWriter().print(
                    "{'richId':'" + rich.getRichId() + "','uiId':'" + form.getSort() + "','relId':'" + rich.getRelId()
                            + "','showType':'" + rich.getShowType() + "'}");
        }
    }

    /**
     * 获取淘宝信息
     * 
     * @param qes
     * @param form
     * @throws IOException
     */
    @RequestMapping(params = "action=ajaxTaobao")
    public void ajaxTaobao(HttpServletResponse qes, AdForm form) throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("'uiId':'" + form.getUiId() + "'");
        try {
            if (!NumberUtils.isNumber(form.getNumIid())) {
                sb.append(",'err':'0'");
                sb.append("}");
                qes.getWriter().print(sb.toString());
                return;
            }
            com.taobao.api.domain.Item item = adService.getTaobaoInfo(Long.parseLong(form.getNumIid()));
            if (item == null) {
                sb.append(",'err':'0'");
                sb.append("}");
                qes.getWriter().print(sb.toString());
                return;
            }
            sb.append(",'pic_title':'" + item.getTitle() + "','detail_url':'" + item.getDetailUrl() + "','pic_url':'"
                    + item.getPicUrl() + "'");
            sb.append("}");
            qes.getWriter().print(sb.toString());
        }
        catch (ApiException e) {
            sb.append(",'err':'0'");
            sb.append("}");
            qes.getWriter().print(sb.toString());
            logger.error("AdController::ajaxTaobao found an error: " + sb.toString(), e);
        }
    }

    @RequestMapping(params = "action=saveBanner")
    public ModelAndView saveBanner(HttpServletRequest request, AdForm form) {
        ModelAndView mv = new ModelAndView("ad/ad_detail_add", Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    // /**
    // * 添加贫媒体广告信息
    // *
    // * @param request
    // * @return
    // */
    // @RequestMapping(params = "action=addWapAd")
    // public ModelAndView addWapAd(WapForm form) {
    // ModelAndView mv = new ModelAndView("ad/wapAd_add", Constants.DEFAULT_COMMAND, form);
    // if (!form.getErrors().isEmpty()) {
    // return mv;
    // }
    // Long memberId = getLogonMemberId();
    // form.form2domain();
    // // 1.首先获取form值
    // // 2.添加到对象
    // // 3.插入到数据库 广告信息
    // CoreWapAd wap = new CoreWapAd();
    // wap = form.getWap();
    // CoreAd ad = new CoreAd();
    // ad.setAdGroupId(wap.getAdGroupId());
    // ad.setAdName(wap.getAdName());
    // ad.setFlag("0");
    // ad.setSuspendType("1");
    // Date date = new Date();
    // ad.setAddTime(date);
    // // 4.返回值
    // int adId = 0;
    // adId = adService.txAddCoreAd(ad, memberId);
    // if (adId > 0) {
    // // 5.保持对象
    // wap.setAdId(adId);
    // // 6.插入到wap贫媒体信息表中
    // wapService.txAddCoreWapAd(wap, memberId);
    // }
    //
    // // 7.查询插入广告信息
    // mv =
    // new ModelAndView("redirect:/ad.do?action=adList&adGroupId=" + wap.getAdGroupId() + "&campaignId="
    // + wap.getCampaignId());
    // return mv;
    // }

    // /**
    // * 贫媒体修改页面
    // *
    // * @param form
    // * @return
    // * @throws InvalidInfoException
    // */
    // @RequestMapping(params = "action=openEditWapAd")
    // public ModelAndView openEditWapAd(WapForm form) throws InvalidInfoException {
    // ModelAndView mv = new ModelAndView("ad/wapAd_edit", Constants.DEFAULT_COMMAND, form);
    // if (!form.getErrors().isEmpty()) {
    // return mv;
    // }
    // form.form2domain();
    // CoreWapAd wap = new CoreWapAd();
    // Long memberId = getLogonMemberId();
    // wap = form.getWap();
    // if (StringUtils.isNotBlank(wap.getAdId().toString()) && StringUtils.isNotBlank(wap.getAdGroupId().toString())
    // && StringUtils.isNotBlank(wap.getCampaignId().toString())) {
    // CoreWapAd coreWapAd = wapService.findCoreWapAdByAdId(wap.getAdId(), memberId);
    // CoreAd coreAd = adService.findCoreAdByAdId(wap.getAdId(), memberId);
    // coreWapAd.setAdName(coreAd.getAdName());
    // coreWapAd.setAdGroupId(wap.getAdGroupId());
    // coreWapAd.setCampaignId(wap.getCampaignId());
    // form.setWap(coreWapAd);
    // CoreAdGroup cag = new CoreAdGroup();
    // cag = adGroupService.queryAdGroupByAdGroupId(wap.getAdGroupId(), memberId);
    // mv.addObject("cag", cag);
    // return mv;
    // }
    // mv =
    // new ModelAndView("redirect:/ad.do?action=adList&adGroupId=" + wap.getAdGroupId() + "&campaignId="
    // + wap.getCampaignId());
    // return mv;
    // }

    /**
     * 删除子页面
     * 
     * @param form
     * @return
     * @throws IOException
     */
    @RequestMapping(params = "action=delRich")
    public void delRich(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String richId = request.getParameter("richId");
        if (richId != null && richId.length() > 0) {
            adService.delRich(richId, getLogonMemberId());
        }
        response.getWriter().print("1");
    }
}
