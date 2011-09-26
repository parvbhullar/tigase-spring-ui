package com.mitian.airad.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.Constants;
import com.mitian.airad.common.exception.NotLogonException;
import com.mitian.airad.model.AccountInfoView;
import com.mitian.airad.model.CoreAd;
import com.mitian.airad.model.CoreAdGroup;
import com.mitian.airad.model.CoreApp;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.model.CoreReport;
import com.mitian.airad.model.CoreUserHis;
import com.mitian.airad.model.Dictionary;
import com.mitian.airad.model.StatAd;
import com.mitian.airad.service.AccountInfoService;
import com.mitian.airad.service.AdGroupService;
import com.mitian.airad.service.AdService;
import com.mitian.airad.service.AppService;
import com.mitian.airad.service.CoreUserHisService;
import com.mitian.airad.service.DeploymentContextService;
import com.mitian.airad.service.MemberService;
import com.mitian.airad.service.ReportService;
import com.mitian.airad.service.StatAdService;
import com.mitian.airad.service.TradeService;
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.BaseRole;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.PersonalForm;

/**
 * CoreCampaign.java
 * 
 * @author Administrator
 */
@Controller
@RequestMapping("/personal.do")
@RoleAuthority({RoleFactory.ROLE_ALL})
public class PersonalController extends AbstractController {
    @Autowired
    private AdGroupService adGroupService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private AppService appService;
    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    private CoreUserHisService coreUserHisService;
    @Autowired
    private StatAdService statAdService;
    @Autowired
    private AdService adService;
    @Autowired
    private MemberService memberService;

    @Autowired
    private DeploymentContextService deploymentContextService;

    /**
     * URL不区分角色，内部controller判断
     * 
     * @param form
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "action=index")
    @RoleAuthority({RoleFactory.ROLE_ALL})
    public ModelAndView index(PersonalForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CoreMemberInfo memberInfo = getMemberInfo();
        BaseRole role = memberInfo.getRole();
        if (role.isAdvAndDev() || role.isDev()) {
            String roleTypes = CookieUtils.getCookieValue(request, CookieUtils.COOKIE_KEY_CURRENT_ROLE_TYPE);
            if (RoleFactory.CURRENT_ROLE_TYPE_DEV.equals(roleTypes)) {
                return personaldevList(form, request, response);
            }
            return personalList(form, request, response);
        }
        else if (role.isShopper() || role.isOssSales()) {
            return new ModelAndView(shopHomePage(form, request), Constants.DEFAULT_COMMAND, form);
        }
        else {
            return personalList(form, request, response);
        }

    }

    @RequestMapping(params = "action=shop")
    @RoleAuthority({RoleFactory.ROLE_SHOPPER})
    public String shopHomePage(PersonalForm form, HttpServletRequest request) {
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        // 冻结提醒
        form.setCoreMemberInfo(memberInfo);
        int page = Integer.valueOf(form.getCurrentPage());
        String sortName = form.getKeyWords();
        String sortType = form.getDesc();
        String searchName = form.getSw();
        List<CoreAd> adList = adService.queryList4Shopper(memberId, page, sortName, sortType, searchName);
        form.setAdList(adList);
        Map<String, String> params = new HashMap<String, String>();
        params.put("memberId", String.valueOf(memberId));
        params.put("adName", searchName);
        Integer adCount = adService.countTotal(params);
        form.setTotalCount(adCount.toString());
        form.setAdShowCount(adService.getTotalAdShowCount(memberId));
        // 报表
        List<CoreReport> reportList = reportService.getList4Shopper(memberId);
        form.setReportList(reportList);
        // 登陆信息
        CoreUserHis userHis = coreUserHisService.queryCoreUserHisByMemberId(memberInfo.getMemberId());
        form.setUserHis(userHis);
        form.setAdPreviewUrl(deploymentContextService.getHermesApplicationAdRequestUrl());
        form.setBannerPreviewUrl(deploymentContextService.getHermesApplicationAdBannerRequestUrl());
        return "personal/shopper";
    }

    /**
     * 个人中心(广告主)
     * 
     * @param form
     * @return
     * @throws ServletException
     * @throws NotLogonException
     */
    @RequestMapping(params = "action=advlist")
    @RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_ADVERTISERS, RoleFactory.ROLE_AGENTS,
            RoleFactory.ROLE_GENERAL, RoleFactory.ROLE_DEVELOPERS})
    public ModelAndView personalList(PersonalForm form, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, NotLogonException {
        CookieUtils.setSessionCookie(response, CookieUtils.COOKIE_KEY_CURRENT_ROLE_TYPE,
                RoleFactory.CURRENT_ROLE_TYPE_ADV);
        ModelAndView mv = new ModelAndView("personal/advert", Constants.DEFAULT_COMMAND, form);
        // 帐户信息
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        // -------------------sunncy 20110910-------------
        // 默认给淘宝1
        memberInfo.setPlatformeType(form.getPlatformType());
        memberInfo = memberService.findBindInfoByMerberId(memberInfo);
        form.setOtherEmail(memberInfo.getOtherEmail());
        form.setOtherName(memberInfo.getOtherName());
        form.setPlatformType(memberInfo.getPlatformeType());
        // ========================end===============
        // 冻结提醒
        form.setCoreMemberInfo(memberInfo);
        // 财务状况
        AccountInfoView aiv = tradeService.findMoneyById(memberId);
        if (null != aiv) {
            mv.addObject("moneylittle", aiv.getAcceptBlance());
        }
        else {
            mv.addObject("moneylittle", 0);
        }
        // 活动列表
        Map<String, String> params = new HashMap<String, String>();
        params.put("descFlag", "1");
        params.put("fromRecord", "0");
        params.put("pageSize", "2");
        params.put("memberId", memberId.toString());
        params.put("sortCol", "UPD_TIME");
        List<CoreAdGroup> cList = new ArrayList<CoreAdGroup>();
        cList = adGroupService.queryAdGroupList(params);
        form.setGroupList(cList);

        // 所属行业添加
        List<Dictionary> industryInvolved = new ArrayList<Dictionary>();
        if (null != adService.txQueryDictionaryList()) {
            industryInvolved = adService.txQueryDictionaryList();
            form.setIndustryInvolved(industryInvolved);
        }
        // 默认广告组个数
        int defaultAdCout = adGroupService.queryCountDefaultAdCount(params);
        // List<Dictionary> arr = adGroupService.queryDictionaryByType1();
        // form.setArr(arr);

        // List<CoreCampaign> campaignList = campaignService.queryList(params);
        // form.setCampaignList(campaignList);
        // 报表列表
        Map<String, String> p = new HashMap<String, String>();
        // String dType = "1,2,3,4";
        getReportParamter(p, memberInfo, request);
        List<CoreReport> reportList = reportService.queryList(p);
        form.setReportList(reportList);

        // 登陆信息
        CoreUserHis userHis = coreUserHisService.queryCoreUserHisByMemberId(memberInfo.getMemberId());
        form.setUserHis(userHis);
        // 此用户的广告的总展示次数
        StatAd statAd = statAdService.queryAdShwoByMemberId(p);
        String adcount = "0";
        if (statAd.getShowNum() != null) {
            adcount = statAd.getShowNum().toString();
        }
        mv.addObject("adShowCount", adcount);
        mv.addObject("defaultAdCout", defaultAdCout);
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 个人中心(开发者)
     * 
     * @param form
     * @return
     * @throws ServletException
     * @throws NotLogonException
     */
    @RequestMapping(params = "action=developlist")
    @RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_DEVELOPERS})
    public ModelAndView personaldevList(PersonalForm form, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, NotLogonException {
        CookieUtils.setSessionCookie(response, CookieUtils.COOKIE_KEY_CURRENT_ROLE_TYPE,
                RoleFactory.CURRENT_ROLE_TYPE_DEV);
        ModelAndView mv = new ModelAndView("personal/developer", Constants.DEFAULT_COMMAND, form);
        // 帐户信息
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        form.setCoreMemberInfo(memberInfo);
        // 财务状况
        /*
         * 根据memeberId找到用户金额信息
         */
        AccountInfoView accountInfoView = accountInfoService.queryAccountInfoById(memberId);
        if (accountInfoView != null) {
            if (null != accountInfoView.getMemberId()) {
                form.setAccountInfoView(accountInfoView);
            }
        }
        // 应用程序列表
        Map<String, String> params = new HashMap<String, String>();
        params.put("descFlag", "desc");
        params.put("pageSize", "3");
        params.put("currentPage", "1");
        params.put("memberId", memberId.toString());
        List<CoreApp> appList = appService.queryList(params);
        form.setAppList(appList);

        // 报表列表
        Map<String, String> p = new HashMap<String, String>();
        // String dType = "1,2,3,4";
        getReportParamter(p, memberInfo, request);
        List<CoreReport> reportList = reportService.queryList(p);
        form.setReportList(reportList);

        // 登陆信息
        CoreUserHis userHis = coreUserHisService.queryCoreUserHisByMemberId(memberInfo.getMemberId());
        form.setUserHis(userHis);
        mv.addObject("form", form);
        return mv;
    }

    public void getReportParamter(Map<String, String> params, CoreMemberInfo memberInfo, HttpServletRequest request)
            throws NotLogonException {
        // 处理权限冻结
        String roleTypes = CookieUtils.getCookieValue(request, CookieUtils.COOKIE_KEY_CURRENT_ROLE_TYPE);
        String dType = memberInfo.getRole().getReportTypes(roleTypes);
        params.put("descFlag", "desc");
        params.put("pageSize", "3");
        params.put("currentPage", "1");
        params.put("devType", dType);
        params.put("memberId", memberInfo.getMemberId().toString());
    }
}
