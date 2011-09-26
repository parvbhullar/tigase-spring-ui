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
import org.json.JSONException;
import org.json.JSONObject;
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
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.utils.ReportDateUtil;
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

    @RequestMapping(params = "action=adGroupChoose")
    public ModelAndView adGroupChoose(HttpServletRequest request, HttpServletResponse response, AdForm form) {

        ModelAndView mv = new ModelAndView("ad/ad_groupchoose", Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 查看广告组下所有广告信息
     * 
     * @param request
     * @return
     * @throws InvalidInfoException
     */

    @RequestMapping(params = "action=adlistajax")
    public ModelAndView adlistajax(HttpServletRequest request, HttpServletResponse response, AdForm form)
            throws InvalidInfoException {
        
        ModelAndView mv = new ModelAndView("ad/adInfo_list2", Constants.DEFAULT_COMMAND, form);
        // 默认降序
        if (StringUtils.isEmpty(form.getDesc())) {
            form.setDesc("desc");
        }
        else {
            // form.setDesc(form.getAsce());
            if (form.getDesc().equals("desc")) {
                form.setDesc("desc");
            }
            else {
                form.setDesc("asce");
            }
        }
        // CookieUtils.setTipMessageCookie(request, response, CommonDef.issue.RETURN_SUCCESS);
        if (StringUtils.isEmpty(form.getTimeSlotFlag())) {
            form.setTimeSlotFlag("6");
        }
        else {
            // form.setTimeSlotFlag(form.getTimeSlotFlag());
        }

        // mv.addObject("flag", "5");

        Long memberId = getLogonMemberId();
        Map<String, String> params = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(form.getAdName())) {
            if (!form.getAdName().contains("/")) {
                params.put("escape", CommonDef.escape.ESCAPE);
            }
            // String adName = StringUtil.replaceChar(form.getAdName());
            params.put("adName", form.getAdName());
        }
        params.put("flag", form.getFlag());
        params.put("memberId", memberId.toString());

        if (form.getDesc().equals("desc")) {
            params.put("desc", "");
        }

        params.put("adGroupId", form.getAdGroupId());
        params.put("timeSlotFlag", form.getTimeSlotFlag());
        String groupId = form.getAdGroupId();
        if (StringUtils.isBlank(groupId) || !NumberUtils.isNumber(groupId)) {
            throw new InvalidInfoException("get agencyList Error groupId: " + groupId + " , memberId=" + memberId);
        }
        Integer adGroupIdInt = Integer.parseInt(groupId);
        CoreAdGroup cag = adGroupService.queryAdGroupByAdGroupId(adGroupIdInt, memberId);

        request.setAttribute("cag", cag);
        // int fromRecord = 0;
        // fromRecord = Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1);
        int pageSize = 15;
        // pageSize = Integer.parseInt(form.getPageSize());
        form.setPageSize(pageSize + "");
        params.put("pageSize", pageSize + "");
        // CoreAd ad = form.getCoreAd();
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

        if (!groupId.equals("0")) {
            Map<String, Integer> map = adGroupService.preparePlatformNumMap(cag);
            cag.setPlatformNumber(map.get("platformNumber"));// 获取平台数
            cag.setOsNumber(map.get("osNumber"));// 获取终端数
            cag.setPlatforms(adGroupService.preparePlatforms(cag));

            form.setAdGroup(cag);
        }
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
     * 查看广告组下所有广告信息
     * 
     * @param request
     * @return
     * @throws InvalidInfoException
     */
    @RequestMapping(params = "action=adList")
    public ModelAndView adList(HttpServletRequest request, AdForm form) throws InvalidInfoException {
        ModelAndView mv = new ModelAndView("ad/adInfo_list", Constants.DEFAULT_COMMAND, form);
        //form = new AdForm();
        form.setAdId(null);
        form.setAdIds(null);
        
        Long memberId = getLogonMemberId();

        String groupId = form.getAdGroupId();

        if (StringUtil.isEmpty(groupId) || groupId.equals("0")) {
            groupId = "0";
            form.setAdGroupId(groupId);
        }

        List<CoreAdGroup> grouplist = adGroupService.findAllGroupByWithOutMemberId(memberId);
        
        if (StringUtils.isBlank(groupId) || !NumberUtils.isNumber(groupId)) {
            throw new InvalidInfoException("get agencyList Error groupId: " + groupId + " , memberId=" + memberId);
        }
        Integer adGroupIdInt = Integer.parseInt(groupId);
        CoreAdGroup cag = adGroupService.queryAdGroupByAdGroupId(adGroupIdInt, memberId);

        request.setAttribute("cag", cag);
        // int fromRecord = 0;
        // fromRecord = Integer.parseInt(form.getPageSize()) * (Integer.parseInt(form.getCurrentPage()) - 1);
        int pageSize = 15;
        // pageSize = Integer.parseInt(form.getPageSize());
        form.setPageSize(pageSize + "");

        // CoreAd ad = form.getCoreAd();

        if (!groupId.equals("0")) {

            Map<String, Integer> map = adGroupService.preparePlatformNumMap(cag);
            cag.setPlatformNumber(map.get("platformNumber"));// 获取平台数
            cag.setOsNumber(map.get("osNumber"));// 获取终端数
            cag.setPlatforms(adGroupService.preparePlatforms(cag));
            form.setAdGroup(cag);
        }
        // 所属行业添加
        List<Dictionary> industryInvolved = new ArrayList<Dictionary>();
        if (null != adService.txQueryDictionaryList()) {
            industryInvolved = adService.txQueryDictionaryList();
            mv.addObject("industryInvolved", industryInvolved);
        }
        mv.addObject("adGroupId", groupId);
        mv.addObject("p", form);
        mv.addObject("groupList", grouplist);
        return mv;
    }

    /**
     * 删除广告信息
     * 
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(params = "action=deleteAd")
    public ModelAndView deleteAdByAdId(HttpServletRequest request, AdForm form, HttpServletResponse response)
            throws IOException {

        // 处理多个ID的问题
        String tmpAdIds = form.getAdIds();
        String result = "1";
        if (StringUtils.isNotBlank(tmpAdIds)) {
            String[] adIds = tmpAdIds.split(",");
            Date date = new Date();
            CoreAd ca = null;
            for (String adId : adIds) {
                if (NumberUtils.isNumber(adId)) {
                    ca = adService.findCoreAdByAdId(Integer.valueOf(adId), getLogonMemberId());
                    ca.setAdId(Integer.valueOf(adId));
                    ca.setUpdOper(getLogonEmail());
                    ca.setDelOper(getLogonEmail());
                    ca.setUpdTime(date);
                    adService.txDeleteCoreAd(ca, getLogonMemberId());
                }
            }
        }
        else {
            result = "0";
        }

        // 前台修改为ajax提交方式

        
         JSONObject js =new JSONObject();
        try {
            js.put("returnCode", result);

        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        response.getWriter().print(js.toString());
        return null;

        // 原代码为表单提交，现在修改为ajax提交，故先注释。
        // CookieUtils.setTipMessageCookie(request, response, CommonDef.delete.RETURN_SUCCESS);
        // ModelAndView mv =
        // new ModelAndView("redirect:ad.do?action=adList&currentPage=" + form.getCurrentPage() + "&asce="
        // + form.getAsce() + "&timeSlotFlag=" + form.getTimeSlotFlag() + "&startTime="
        // + form.getStartTime() + "&endTime=" + form.getEndTime() + "&adName=" + form.getAdName()
        // + "&adGroupId=" + form.getAdGroupId() + "&campaignId=" + form.getCampaignId(),
        // Constants.DEFAULT_COMMAND, form);
        // return mv;
    }

    /**
     * 移动广告信息
     * 
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(params = "action=adDeleve")
    public ModelAndView adDeleve(HttpServletRequest request, AdForm form, HttpServletResponse response)
    throws IOException {
        
        // 处理多个ID的问题
        String result = "1";
        String tmpAdIds = form.getAdIds();
        if (StringUtils.isNotBlank(tmpAdIds)) {
            String[] adIds = tmpAdIds.split(",");
            Date date = new Date();
            CoreAd ca = null;
            for (String adId : adIds) {
                if (NumberUtils.isNumber(adId)) {
                    ca = new CoreAd();
                    ca = adService.findCoreAdByAdId(Integer.valueOf(adId), getLogonMemberId());
                    ca.setAdGroupId(Integer.valueOf(form.getAdGroupId()));
                    ca.setUpdOper(getLogonEmail());
                    ca.setUpdTime(date);
                    adService.txDeleveCoreAd(ca, getLogonMemberId());
                }
            }
            
        }
        else {
            result = "0";
        }
        
        // 前台修改为ajax提交方式
        JSONObject js =new JSONObject();
        try {
            js.put("returnCode", result);

        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        response.getWriter().print(js.toString());
        return null;
        // 原代码为表单提交，现在修改为ajax提交，故先注释。
        // CookieUtils.setTipMessageCookie(request, response, CommonDef.suspend.RETURN_SUCCESS);
        // ModelAndView mv =
        // new ModelAndView("redirect:ad.do?action=adList&currentPage=" + form.getCurrentPage() + "&asce="
        // + form.getAsce() + "&timeSlotFlag=" + form.getTimeSlotFlag() + "&startTime="
        // + form.getStartTime() + "&endTime=" + form.getEndTime() + "&adName=" + form.getAdName()
        // + "&adGroupId=" + form.getAdGroupId() + "&campaignId=" + form.getCampaignId(),
        // Constants.DEFAULT_COMMAND, form);
        // return mv;
    }
    /**
     * 暂停广告信息
     * 
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(params = "action=stopAd")
    public ModelAndView stopAdByAdId(HttpServletRequest request, AdForm form, HttpServletResponse response)
            throws IOException {

        // 处理多个ID的问题
        String tmpAdIds = form.getAdIds();
        String result = "1";
        if (StringUtils.isNotBlank(tmpAdIds)) {
            String[] adIds = tmpAdIds.split(",");
            Date date = new Date();
            CoreAd ca = null;
            for (String adId : adIds) {
                if (NumberUtils.isNumber(adId)) {
                    ca = adService.findCoreAdByAdId(Integer.valueOf(adId), getLogonMemberId());
                    ca.setAdId(Integer.valueOf(adId));
                    ca.setUpdOper(getLogonEmail());
                    ca.setUpdTime(date);
                    adService.txStopCoreAd(ca, getLogonMemberId());
                }
            }

        }
        else {
            result="0";
        }

        // 前台修改为ajax提交方式
        // 前台修改为ajax提交方式
        JSONObject js =new JSONObject();
        try {
            js.put("returnCode", result);

        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        response.getWriter().print(js.toString());
        return null;

        // 原代码为表单提交，现在修改为ajax提交，故先注释。
        // CookieUtils.setTipMessageCookie(request, response, CommonDef.suspend.RETURN_SUCCESS);
        // ModelAndView mv =
        // new ModelAndView("redirect:ad.do?action=adList&currentPage=" + form.getCurrentPage() + "&asce="
        // + form.getAsce() + "&timeSlotFlag=" + form.getTimeSlotFlag() + "&startTime="
        // + form.getStartTime() + "&endTime=" + form.getEndTime() + "&adName=" + form.getAdName()
        // + "&adGroupId=" + form.getAdGroupId() + "&campaignId=" + form.getCampaignId(),
        // Constants.DEFAULT_COMMAND, form);
        // return mv;
    }

    /**
     * 发布广告信息
     * 
     * @param request
     * @return
     * @throws InvalidInfoException
     * @throws IOException
     */
    @RequestMapping(params = "action=issue")
    public ModelAndView sendAd(HttpServletRequest request, AdForm form, HttpServletResponse response)
            throws InvalidInfoException, IOException {

        Long memberId = getLogonMemberId();
        String result = "1";
        CoreMemberInfo memberInfo = getMemberInfo();
        BaseRole role = memberInfo.getRole();
        if (role.isDev() || role.isGeneral()) {
            response.getWriter().write(0);
        }

        // 处理多个ID的问题
        String tmpAdIds = form.getAdIds();
        if (StringUtils.isNotBlank(tmpAdIds)) {
            String[] adIds = tmpAdIds.split(",");
            Date date = new Date();
            CoreAd ca = null;
            for (String adId : adIds) {
                if (NumberUtils.isNumber(adId)) {
                    ca = adService.findCoreAdByAdId(Integer.valueOf(adId), getLogonMemberId());
                    ca.setAdId(Integer.valueOf(adId));
                    ca.setUpdOper(getLogonEmail());
                    ca.setUpdTime(date);
                    adService.txSendCoreAd(ca, memberId);
                }
            }

        }
        else {
             result = "0";
        }

        // 前台修改为ajax提交方式
 
        JSONObject js =new JSONObject();
        try {
            js.put("returnCode", result);
        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        response.getWriter().print(js.toString());
        return null;

        // 原代码为表单提交，现在修改为ajax提交，故先注释。
        // CookieUtils.setTipMessageCookie(request, response, CommonDef.issue.RETURN_SUCCESS);
        // ModelAndView mv =
        // new ModelAndView("redirect:ad.do?action=adList&currentPage=11&adGroupId=" + form.getAdGroupId()
        // + "&campaignId=" + form.getCampaignId(), Constants.DEFAULT_COMMAND, form);
        // return mv;
 
    }

    //    
    /**
     * 获得广告详细信息
     * 
     * @throws InvalidInfoException
     * @throws NumberFormatException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(params = "action=detailEdit")
    public ModelAndView detailEdit(HttpServletRequest request, AdForm form) throws InvalidInfoException {
        String groupId = form.getAdGroupId();
        
        
        if(StringUtil.isEmpty(groupId) || groupId.equals("0") ){
            groupId = "0";
            form.setAdGroupId(groupId);
        }
    //    String campaignId = form.getCampaignId();
        String adId = form.getAdId();
        if(StringUtil.isEmpty(adId)){
            String adGroupId = form.getAdGroupId();
            form = new AdForm();
            form.setAdGroupId(adGroupId);
        }
        Long memberId = getLogonMemberId();
        form.setMemberId(memberId + "");
        // 获取当前活动
       // CoreCampaign campaign = campaignService.findById(campaignId, memberId);
      //  form.setCampaign(campaign);

        

        // 获取广告组信息
        
        
        if(!form.getAdGroupId().equals("0")){
        CoreAdGroup adGroup = adGroupService.findById(groupId, memberId);
        adGroup.setPlatforms(adGroupService.preparePlatforms(adGroup));
        form.setAdGroup(adGroup);
        Integer spType = adService.isPs(adGroup.getAdTagSp());
            if (spType != null) {
                form.setMarketType(spType.toString());
            }
        }else{
            CoreAdGroup adGroup1 = new  CoreAdGroup();
            adGroup1.setAdGroupId(0);
            form.setAdGroup(adGroup1);
        }
        
        // 获取广告信息
        Map<String, Object> adMap = null;
        if(!form.getAdGroupId().equals("0")){
            adMap = adService.findById(adId, memberId);
            form.setCoreAd((CoreAd) adMap.get("ad"));
        }else{

            if(StringUtil.isEmpty(adId)){
                form.coreAd=new CoreAd();
                adMap= new HashMap<String, Object>();
            }else{
                adMap = adService.findById(adId, memberId);
                form.setCoreAd((CoreAd) adMap.get("ad"));
            }
       
        }
        
        if (form.getAdStartTime() == null) {
            form.setAdStartTime(ReportDateUtil.getTomorrowDate("yyyy-MM-dd", 0));
            form.setAdStartHour(ReportDateUtil.getTomorrowDate("HH", 0));
        }
        if (form.getCoreAd().getAdEndTime() != null) {
            // form.sett
            form.setTimeFlag(CommonDef.timeFlag.HAVE_ENDTIME);
        }
        else {
            // form.setTimeFlag(CommonDef.timeFlag.NO_ENDTIME);
            form.setAdEndTime(ReportDateUtil.getTomorrowDate("yyyy-MM-dd", 7));
        }

        form.setBanner((CoreBanner) adMap.get("banner"));
        form.setWap((CoreWapAd) adMap.get("wap"));
        form.setRichList((List<CoreRichAd>) adMap.get("richMedia"));

        //
        form.setBannerTypeDeprecated(adService.isBannerTypeDeprecated((CoreBanner) adMap.get("banner")));
        //
        // ModelAndView mv = new ModelAndView("ad/ad_detail", Constants.DEFAULT_COMMAND, form);
//        if(form.getAdBudgetTotal()!=null)
//            form.setAdBudgetTotal(Float.parseFloat(form.getAdBudgetTotal())+"");
//        if(form.getAdBudgetDay()!=null)
//            form.setAdBudgetDay(Float.parseFloat(form.getAdBudgetDay())+"");
        
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
        // response.setContentType("")
        if (StringUtils.isNotBlank(form.getAdChildNum())) {
            if (Integer.valueOf(form.getAdChildNum()) < 3) {
                form.setAdChildNum("1");
            }
        }

        CoreAd coreAd = form.getCoreAd();
    //    CoreAd coreAdOriginal= adService.findCoreAdByAdId(Integer.parseInt(form.getAdId()), getLogonMemberId());
        
        String adIsModifed = "0";
//        if(!coreAdOriginal.getAdName().equals(form.getAdName())){
//            adIsModifed="1";
//        }

        if (!form.getTimeFlag().equals(CommonDef.timeFlag.HAVE_ENDTIME)) {
            coreAd.setAdEndTime(null);
        }

        JSONObject js = new JSONObject();
        
        if (form.getAdId() != null && form.getAdId().length() > 0) {
            
            
            String flag = form.getDraftStatus().equals("0")?"0":null;
            
            adService.txUpdateCoreAdByAdId(coreAd, flag, memberId, role);
            
            try {
                js.put("adid", form.getAdId());
                js.put("adIsModifed", adIsModifed);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            //新生成的广告编号
            int adId = adService.txAddCoreAd(coreAd, memberId, role);// 保存
 
            try {
                js.put("adid", adId+"");
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

        }
        response.getWriter().print(js.toString());
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
            throws IOException, InvalidInfoException, OperationNotSupportException {

        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        BaseRole role = memberInfo.getRole();
        form.setMemberId(memberId.toString());
        CoreAd coreAd = form.getCoreAd();
        


        if (!form.getTimeFlag().equals(CommonDef.timeFlag.HAVE_ENDTIME)) {
            coreAd.setAdEndTime(null);
        }

        if (Integer.parseInt(form.getAdType()) == AdType.Wap.getCode()) {
            coreAd.setBackground(-1);
        }

        // 根据当前用户是否身份认证设置广告id
        Integer adFlag = role.isSaveAdDirect() ? Flag.CHECK.getCode() : Flag.DRAFT.getCode();
        

        CoreAd coreAdOriginal= adService.findCoreAdByAdId(Integer.parseInt(form.getAdId()), getLogonMemberId());
        
        bre:
        if(!form.getAdIsModifed().equals("1") ){
            //用户没有修改了广告，保持原有
            Integer original = Integer.valueOf(coreAdOriginal.getFlag());
            if(Flag.CHECK.getCode()==original ||  Flag.DRAFT.getCode()==original ){
                adFlag = Flag.CHECK.getCode();
                break bre;
            }
            adFlag = original;
            
        }
        Integer adFlag2 = adFlag;  
        //未分组广告始终是草稿状态
        if(form.getAdGroupId().equals("0")){
            coreAd.setFlag("0");
            adFlag=0;
            adFlag2=0;
        }
        
        int flag = adService.txUpdateCoreAdByAdId(coreAd, adFlag.toString(), memberId, role);
        //未房租给草稿
        
        //更新操作成功
        if (flag == 1) {
            adService.compute(Integer.parseInt(form.getAdId()), memberId, role);
            if (role.isSaveAdDirect()) {
                // 所属行业添加
//                ModelAndView mv = new ModelAndView("ad/adList", Constants.DEFAULT_COMMAND, form);
//                List<Dictionary> industryInvolvedList = new ArrayList<Dictionary>();
//                if (null != adService.txQueryDictionaryList()) {
//                    industryInvolvedList = adService.txQueryDictionaryList();
//                    mv.addObject("industryInvolvedList", industryInvolvedList);
//                }
//                // 根据会员ID, 获取广告组列表对象
//                List<CoreAdGroup> adGroupList = adGroupService.findAllGroupByWithOutMemberId(memberId);
//                for (CoreAdGroup adGroup : adGroupList) {
//                    adGroup.setPlatforms(adGroupService.preparePlatforms(adGroup));
//                }
//
//                mv.addObject("adGroupList", adGroupList);
//                
//                return adList(request, form);
            } else {
                CookieUtils.setAuthFlagCookie(response, coreAd.getAdId() + "", Constants.NEED_AUTH);
            }
        }
        response.sendRedirect("/adGroup.do?action=adGroupSet&adId="+form.getAdId()+"&showAuth=no&submitStatus=1&flag="+adFlag2+"&adGroupId="+form.getAdGroupId());
        return null;
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
        // String delRichIdSp = request.getParameter("delRichId");
        form.getCoreAd().setMemberId(memberId);// 获取会员idAD_OFFER
        if (form.getRichId() != null && form.getRichId().length() > 0) {
            // Integer flag = 0;
            CoreRichAd richAd = adService.txUpdateCoreRich(form.getRich(), memberId);//
            adService.compute(Integer.parseInt(form.getAdId()), memberId, role);// 计算广告价格
            response.getWriter().print(
                    "{\"flag\":\"" + richAd.getFlag() + "\",\"uiId\":\"" + form.getSort() + "\",\"relId\":\""
                            + richAd.getRelId() + "\",\"showType\":\"" + form.getShowType() + "\"}");
        }
        else {
            CoreRichAd rich = adService.txAddCoreRich(form.getRich(), memberId);// 保存
            adService.compute(Integer.parseInt(form.getAdId()), memberId, role);// 计算广告价格
            response.getWriter().print(
                    "{\"richId\":\"" + rich.getRichId() + "\",\"uiId\":\"" + form.getSort() + "\",\"relId\":\""
                            + rich.getRelId() + "\",\"showType\":\"" + rich.getShowType() + "\"}");
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
        sb.append("\"uiId\":\"" + form.getUiId() + "\"");
        try {
            if (!NumberUtils.isNumber(form.getNumIid())) {
                sb.append(",\"err\":\"0\"");
                sb.append("}");
                qes.getWriter().print(sb.toString());
                return;
            }
            com.taobao.api.domain.Item item = adService.getTaobaoInfo(Long.parseLong(form.getNumIid()));
            if (item == null) {
                sb.append(",\"err\":\"0\"");
                sb.append("}");
                qes.getWriter().print(sb.toString());
                return;
            }
            sb.append(",\"pic_title\":\"" + item.getTitle() + "\",\"detail_url\":\"" + item.getDetailUrl()
                    + "\",\"pic_url\":\"" + item.getPicUrl() + "\"");
            sb.append("}");
            qes.getWriter().print(sb.toString());
        }
        catch (ApiException e) {
            sb.append(",\"err\":\"0\"");
            sb.append("}");
            qes.getWriter().print(sb.toString());
            getLogger().error("AdController::ajaxTaobao found an error: " + sb.toString(), e);
        }
    }

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
