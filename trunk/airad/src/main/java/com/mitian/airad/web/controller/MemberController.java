/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.CommonDef;
import com.mitian.airad.Constants;
import com.mitian.airad.ErrorMessages;
import com.mitian.airad.common.auth.cookie.CookieGenerator;
import com.mitian.airad.common.constant.MemberConstant;
import com.mitian.airad.common.exception.NotLogonException;
import com.mitian.airad.common.utils.CaptchaUtil;
import com.mitian.airad.common.utils.ChaosUtil;
import com.mitian.airad.common.utils.IpUtil;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.model.CoreUserHis;
import com.mitian.airad.model.LogonInfo;
import com.mitian.airad.service.CoreUserHisService;
import com.mitian.airad.service.LogonService;
import com.mitian.airad.service.MemberService;
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.utils.StringUtil;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.BaseRole;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.MemberForm;

/**
 * LoginCotroller.java
 * 
 * @author Administrator
 */
@Controller
@RequestMapping("/member.do")
public class MemberController extends AbstractController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private CoreUserHisService coreUserHisService;
    @Autowired
    private CookieGenerator lastVisitCookieGenerator;
    @Autowired
    private CookieGenerator cookieGenerator;
    @Autowired
    private LogonService logonService;
    private final static Logger logger = Logger.getLogger(MemberController.class);

    // 验证用户是否存在
    @RequestMapping(params = "action=ajaxValidataUEmail")
    public ModelAndView ajaxValidataUEmail(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/xml; charset=UTF-8");
        String message = "";
        String email = request.getParameter("email");
        if (org.apache.commons.lang.StringUtils.isNotBlank(email)) {
            email = email.toLowerCase();
            CoreMemberInfo coreMemberInfo = memberService.selectMemberInfoByEmail(email);

            if (null != coreMemberInfo) {
                message = "用户已存在";
            }
            else {
                message = "";
            }

        }
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(message);
        }
        catch (IOException e) {
            logger.error("ajaxValidataUEmail error", e);
        }

        return null;
    }

    @RequestMapping(params = "action=getemail")
    public void getLogonEmail(HttpServletRequest request, HttpServletResponse response) {
        String email = getLogonEmail();
        if (org.apache.commons.lang.StringUtils.isNotBlank(email)) {
            response.setContentType("text/xml; charset=UTF-8");
            PrintWriter out;
            try {
                out = response.getWriter();
                out.print(email);
            }
            catch (IOException e) {
                logger.error("getLogonEmail error", e);
            }
        }
    }

    /**
     * 到登陆页
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=logon")
    public ModelAndView logon(MemberForm form, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("member/logon_page", Constants.DEFAULT_COMMAND, form);
        String nextUrl = "";
        CoreMemberInfo memberInfo = getMemberInfo();
        if (memberInfo != null && memberInfo.isActive()) {
            BaseRole role = memberInfo.getRole();
            if (role.isAdvertisers() || role.isGeneral()) {
                nextUrl = "redirect:campaign.do?action=listCampaign";
            }
            else if (role.isAdvAndDev()) {
                nextUrl = "redirect:app.do?action=listApp";
            }
            else if (role.isAgents()) {
                nextUrl = "redirect:agentRelation.do?action=agencyList";
            }
            else if (role.isDev()) {
                nextUrl = "redirect:app.do?action=listApp";
            }
            mv = new ModelAndView(nextUrl);
            return mv;
        }
        return mv;
    }

    /**
     * 登陆
     * 
     * @param form
     * @param response
     * @return
     * @throws NotLogonException
     */
    @RequestMapping(params = "action=doLogon", method = RequestMethod.POST)
    public ModelAndView doLogon(MemberForm form, HttpServletRequest request, HttpServletResponse response)
            throws NotLogonException {
        // 登陆验证判断
        if (!form.getErrors().isEmpty()) {
            // form.getErrors().clear();
            // form.getErrors().put("loginerror", "请输入用户名或密码");
            return logon(form, request);
        }
        // 看是否有登录失败
        Object logonNumString = request.getSession().getAttribute("logonNum");
        int logonNum = 0;
        String ip = IpUtil.getIpAddr(request);
        if (null != logonNumString) {
            logonNum = Integer.parseInt(logonNumString.toString());
        }
        String logonEmail = StringUtils.trimToEmpty(form.getEmail()).toLowerCase();
        String password = form.getPassword();

        if (StringUtil.isEmpty(form.getEmail()) || StringUtil.isEmpty(form.getPassword())) {
            form.getErrors().put("email", "请输入用户名或密码");
        }
        if (!form.getErrors().isEmpty()) {
            // form.getErrors().clear();
            // form.getErrors().put("loginerror", "请输入用户名或密码");
            return logon(form, request);
        }
        // 当失败次数达到5次的时候需要验证验证码输入
        if (logonNum >= 5) {
            Captcha captcha = (Captcha) request.getSession().getAttribute(CaptchaUtil.CAPTCHA_KEY_IN_SESSION);
            String userInput = org.apache.commons.lang.StringUtils.trimToEmpty(form.getVerifyCode());
            String answer = captcha != null ? captcha.getAnswer() : "the captcha in session is null";
            logonService.sendNoticeMail4TryLogonOver5Times(logonEmail, logonNum, ip, password, userInput, answer);
            if (CaptchaUtil.isExpire(captcha)) {
                form.getErrors().put("verifyCode", ErrorMessages.VERIFYCODE_ABATE);
                return logon(form, request);
            }
            if (!captcha.isCorrect(userInput)) {
                form.getErrors().put("verifyCode", ErrorMessages.VERIFYCODE_ERR);
                return logon(form, request);
            }

        }
        form.setLoginIp(ip);
        form.setRemoteAddr(ip);

        CoreMemberInfo memberInfo = logonService.txDoLogon(logonEmail, password, request, response);
        LogonInfo logonInfo = memberInfo.getLogonInfo();
        if (!logonInfo.isLogonSuccess()) {
            // 登录失败后失败次数加1
            logonNum = logonNum + 1;
            request.getSession().setAttribute("logonNum", logonNum);
            form.getErrors().put("loginerror", logonInfo.getLogonFailTipMessage());
            return logon(form, request);
        }
        Long memberId = memberInfo.getMemberId();
        // 登录成功后把失败次数清空
        request.getSession().setAttribute("logonNum", null);
        // 登录成功之后 获取登录ID、登录名等相关信息
        // 查看用户是否激活
        if (!memberInfo.isActive()) {
            logonService.cleanLogonContext(request, response);
            return logon2Active(form);
        }
        // 判断当前会员的状态是否为冻结
        if (memberInfo.isForzen()) {
            form.getErrors().put("loginerror", ErrorMessages.USER_FROZEN);
            logonService.cleanLogonContext(request, response);
            return logon(form, request);
        }

        form.setUserId(memberId.toString());

        String nextUrl = form.getNextUrl();
        BaseRole baseRole = memberInfo.getRole();
        // 更新身份验证信息(普通会员)
        // 查找上一次登录的时间
        CoreUserHis userHis = coreUserHisService.queryCoreUserHisByMemberId(memberId);
        // 看是否是首次登陆此系统(非店铺主用户第一次登陆的时候都要求)
        if (baseRole.needCompleteMemberInfo(userHis)) {
            nextUrl = "/member.do?action=completeMemberInfo";// 个人完善信息页
        }
        ModelAndView mv = baseRole.getLogonAfterPage(nextUrl);
        return mv;
    }

    /**
     * 完善个人信息页
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=completeMemberInfo")
    @RoleAuthority({RoleFactory.ROLE_ALL})
    public ModelAndView completeMemberInfo(MemberForm form) {
        ModelAndView mv = new ModelAndView("member/member_detail_first", Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 首次提示
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=changeMember")
    @RoleAuthority({RoleFactory.ROLE_ALL})
    public ModelAndView changeMember(MemberForm form) {
        ModelAndView mv = new ModelAndView("member/member_role", Constants.DEFAULT_COMMAND, form);
        if (null == getLogonEmail()) {
            mv = new ModelAndView("member/logon_page", Constants.DEFAULT_COMMAND, form);
        }
        else {// 如果是开发者就不需要进入角色选择了
            BaseRole role = getMemberInfo().getRole();
            if (role.isDev()) {
                mv = new ModelAndView("redirect:/personal.do?action=developlist");
            }
        }
        return mv;
    }

    /**
     * 成为开发者
     * 
     * @param form
     * @return
     */
    @RoleAuthority(RoleFactory.ROLE_GENERAL)
    @RequestMapping(params = "action=changeDevMember")
    public ModelAndView changeDevMember(MemberForm form) {
        ModelAndView mv = new ModelAndView("redirect:/personal.do?action=developlist");
        CoreMemberInfo memberInfo = getMemberInfo();
        // 更新devType为开发者
        memberService.txUpdateMemberType(memberInfo, RoleFactory.ROLE_DEVELOPERS);
        return mv;
    }

    /**
     * 注册页面
     * 
     * @param form
     * @param response
     * @return
     */
    @RequestMapping(params = "action=register")
    public ModelAndView register(MemberForm form, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("member/register_page", Constants.DEFAULT_COMMAND, form);
        // if (StringUtils.isEmpty(form.getInviteCode())) {
        // return logon(form, request);
        // }
        if (null != getLogonEmail()) {
            mv = new ModelAndView("member/no_permission");
            mv.addObject("errMessage", "isLogon");
            return mv;
        }
        // CoreInviteCode codeCoreInviteCode =
        // inviteCodeService.selectInviteByCode(form.getInviteCode());
        // if (null == codeCoreInviteCode) {
        // form.getErrors().put("verifyCode",
        // ErrorMessages.INVITE_CODE_NULLITY);
        // mv = new ModelAndView("member/suss", Constants.DEFAULT_COMMAND,
        // form);
        // form.setMemberMessage("inviteCodeNullity");
        // return mv;
        // }
        // if
        // (Constants.INVITE_STATUS_IS_USAGE.equals(codeCoreInviteCode.getInviteStatus()))
        // {
        // form.getErrors().put("verifyCode",
        // ErrorMessages.INVITE_CODE_NULLITY);
        // mv = new ModelAndView("member/suss", Constants.DEFAULT_COMMAND,
        // form);
        // form.setMemberMessage("inviteCodeNullity");
        // return mv;
        // }
        // form.setInviteCode(form.getInviteCode());
        return mv;
    }

    /**
     * 注册
     * 
     * @param form
     * @param response
     * @return
     */
    @RequestMapping(params = "action=doRegister", method = RequestMethod.POST)
    public ModelAndView doRegister(MemberForm form, HttpServletRequest request, HttpServletResponse response) {
        // 返回错误信息
        if (!form.getErrors().isEmpty()) {
            return register(form, request);
        }

        // 注册验证判断
        String promotionId = CookieUtils.getCookieValue(request, MemberConstant.COOKIE_KEY_PROMOTIONID);// 获取推广员
        if (org.apache.commons.lang.StringUtils.isNotBlank(promotionId)) {
            promotionId = String.valueOf(ChaosUtil.unChaos(promotionId));
        }
        form.setPromotionId(promotionId);

        Captcha captcha = (Captcha) request.getSession().getAttribute(CaptchaUtil.CAPTCHA_KEY_IN_SESSION);

        form.setLoginIp(IpUtil.getIpAddr(request));// 客户端地址

        String userInput = org.apache.commons.lang.StringUtils.trimToEmpty(form.getVerifyCode());

        if (captcha == null || CaptchaUtil.isExpire(captcha)) {
            form.getErrors().put("verifyCode", ErrorMessages.VERIFYCODE_ABATE);
        }
        else if (!captcha.isCorrect(userInput)) {
            form.getErrors().put("verifyCode", ErrorMessages.VERIFYCODE_ERR);
        }
        else if (!form.getPassword().equals(form.getPasswords())) {
            form.getErrors().put("verifyCode", ErrorMessages.AFFIRM_PASSWORD_ERR);
        }
        else {

            // 返回错误信息
            if (!form.getErrors().isEmpty()) {
                return register(form, request);
            }
            // Map<String, String> result =
            // memberService.txAddMember(form.getEmail().toLowerCase(), form.getPassword(), form.getInviteCode(),
            // Constants.AIRAD_HOMEPAGE_URL, form.getLoginIp());
            form.setUrlName(Constants.AIRAD_HOMEPAGE_URL);
            form.form2domain();
            CoreMemberInfo coreMemberInfo = form.getCoreMemberInfo();
            Map<String, String> result = memberService.txAddMember(coreMemberInfo);

            form.getErrors().putAll(result);
            if (form.getErrors().size() > 0) {
                return register(form, request);
            }
            CookieUtils.removeCookie(response, MemberConstant.COOKIE_KEY_PROMOTIONID);
            return new ModelAndView("redirect:/member.do?action=registerOK&memberMessage=merberRegister&email="
                    + form.getEmail().toLowerCase());
        }
        return register(form, request);
    }

    /**
     * 用户要求发送激活邮件
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=registerActivation")
    public ModelAndView registerActivation(MemberForm form, HttpServletRequest request) {
        if (!form.getErrors().isEmpty()) {
            return operateResult(form);
        }
        Map<String, String> result = memberService.txRegisterActivation(form.getEmail(), Constants.AIRAD_HOMEPAGE_URL);
        form.getErrors().putAll(result);
        if (form.getErrors().size() > 0) {
            return operateResult(form);
        }
        return new ModelAndView("redirect:/member.do?action=registerOK&memberMessage=sendEmail",
                Constants.DEFAULT_COMMAND, form);
    }

    /**
     * 操作失败返回页面
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=operateResult")
    public ModelAndView operateResult(MemberForm form) {
        ModelAndView mv = new ModelAndView("member/suss", Constants.DEFAULT_COMMAND, form);
        form.setMemberMessage("errorResult");
        return mv;
    }

    /**
     * 注册成功
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=registerOK")
    public ModelAndView registerOK(MemberForm form) {
        ModelAndView mv = new ModelAndView("member/suss", Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 登陆后到激活页
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=logon2Active")
    @RoleAuthority({RoleFactory.ROLE_ALL})
    public ModelAndView logon2Active(MemberForm form) {
        ModelAndView mv = new ModelAndView("member/suss", Constants.DEFAULT_COMMAND, form);
        form.setMemberMessage("logonRegister");
        return mv;
    }

    /**
     * 注册激活
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=regActive")
    public ModelAndView regActive(MemberForm form) {
        ModelAndView mv = new ModelAndView("member/suss", Constants.DEFAULT_COMMAND, form);
        // String email = request.getParameter("email");
        // String activeCode = request.getParameter("activeCode");
        // form.setActiveCode(activeCode);
        // form.setEmail(email);
        Map<String, String> result = memberService.txRegActive(form.getEmail(), form.getActiveCode());
        form.getErrors().putAll(result);
        // 设置在跳转页面要显示的结果
        form.setMemberMessage("regActiveResult");
        return mv;
    }

    /**
     * 退出操作
     * 
     * @return
     */
    @RequestMapping(params = "action=logout")
    public ModelAndView logout(MemberForm form, HttpServletResponse response, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("member/logon_page", Constants.DEFAULT_COMMAND, form);
        logonService.txDoLogout(request, response);
        return mv;
    }

    /**
     * 密码重置发送邮件页面
     * 
     * @param form
     * @param request
     * @return
     */
    @RequestMapping(params = "action=sendResettingPasswordEmailPage")
    public ModelAndView sendResettingPasswordEmailPage(MemberForm form) {
        ModelAndView mv = new ModelAndView("member/password_retrieve", Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 密码重置(找回密码)页面
     * 
     * @param form
     * @param request
     * @return
     */
    @RequestMapping(params = "action=resettingPasswordPage")
    public ModelAndView resettingPasswordPage(MemberForm form) {
        ModelAndView mv = new ModelAndView("member/password_resetting", Constants.DEFAULT_COMMAND, form);
        // 设置在跳转页面要显示的结果
        if (StringUtil.isEmpty(form.getEmail()) || StringUtil.isEmpty(form.getDate())
                || StringUtil.isEmpty(form.getC())) {
            form.getErrors().put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
        }
        Map<String, String> map =
                memberService.queryResettingPasswordLink(form.getEmail(), form.getDate(), form.getC());
        if (map.size() > 0) {
            form.getErrors().putAll(map);
            mv = new ModelAndView("member/suss", Constants.DEFAULT_COMMAND, form);
            form.setMemberMessage("passwordResult");
        }
        return mv;
    }

    /**
     * 密码重置(找回密码)
     * 
     * @param form
     * @param request
     * @return
     */
    @RequestMapping(params = "action=resettingPassword")
    public ModelAndView resettingPassword(MemberForm form) {
        if (!form.getErrors().isEmpty()) {
            return resettingPasswordPage(form);
        }
        ModelAndView mv = new ModelAndView("member/suss", Constants.DEFAULT_COMMAND, form);
        if (!form.getPassword().equals(form.getPasswords())) {
            form.getErrors().put("verifyCode", ErrorMessages.AFFIRM_PASSWORD_ERR);
            return resettingPasswordPage(form);
        }
        Map<String, String> reult = memberService.txResettingPassword(form.getEmail(), form.getPassword());
        form.getErrors().putAll(reult);
        // 设置在跳转页面要显示的结果
        form.setMemberMessage("passwordResult");

        return mv;
    }

    /**
     * 发送密码重置邮件
     * 
     * @param form
     * @param request
     * @return
     */
    @RequestMapping(params = "action=sendResettingPasswordEmail")
    public ModelAndView sendResettingPasswordEmail(MemberForm form, HttpServletRequest request) {
        // 查询用户是否冻结
        String email = form.getEmail();
        if (StringUtils.isNotBlank(email)) {
            CoreMemberInfo member = memberService.selectMemberInfoByEmail(email);
            if (member != null) {
                String memberStatus = member.getMemberStatus();
                if (Constants.MEMBER_STATUS_FREEZE.equals(memberStatus)) {
                    form.getErrors().put("userErr", "此用户被冻结。");
                }
            }
            else {
                form.getErrors().put("userErr", "此用户不存在。");
            }
        }
        if (!form.getErrors().isEmpty()) {
            return sendResettingPasswordEmailPage(form);
        }
        ModelAndView mv = new ModelAndView("member/suss", Constants.DEFAULT_COMMAND, form);
        Map<String, String> result =
                memberService.txSendMialByResettingPassword(form.getEmail(), Constants.AIRAD_HOMEPAGE_URL);
        form.getErrors().putAll(result);
        // 设置在跳转页面要显示的结果
        form.setMemberMessage("sendEmail");
        return mv;
    }

    /**
     * ==========================chenji=====================================
     */

    /**
     * 查询会员基本信息
     * 
     * @param 会员基本信息
     * @return
     */
    @RequestMapping(params = "action=queryMember")
    @RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_ADVERTISERS, RoleFactory.ROLE_AGENTS,
            RoleFactory.ROLE_DEVELOPERS, RoleFactory.ROLE_GENERAL, RoleFactory.ROLE_SHOPPER})
    public ModelAndView queryMember(MemberForm form) {
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        form.setCoreMemberInfo(memberInfo);
        CoreUserHis userHis = coreUserHisService.queryCoreUserHisByMemberId(memberId);
        form.setUserHis(userHis);
        ModelAndView mv = new ModelAndView("member/member_detail", Constants.DEFAULT_COMMAND, form);
        mv.addObject("userHis", userHis);
        return mv;
    }

    /**
     * 修改会员信息
     * 
     * @param 会员信息
     * @return
     */
    @RequestMapping(params = "action=doUpdateMemberInfo", method = RequestMethod.POST)
    @RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_ADVERTISERS, RoleFactory.ROLE_AGENTS,
            RoleFactory.ROLE_DEVELOPERS, RoleFactory.ROLE_GENERAL, RoleFactory.ROLE_SHOPPER})
    public ModelAndView doUpdateMemberInfo(MemberForm form, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("member/member_detail", Constants.DEFAULT_COMMAND, form);
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        memberInfo.setMobile(form.getCoreMemberInfo().getMobile());
        memberInfo.setAddress(form.getCoreMemberInfo().getAddress());
        memberInfo.setZip(form.getCoreMemberInfo().getZip());
        memberInfo.setQq(form.getCoreMemberInfo().getQq());
        memberInfo.setMsn(form.getCoreMemberInfo().getMsn());
        memberInfo.setPhone(form.getCoreMemberInfo().getPhone());
        memberInfo.setCompanyName(form.getCoreMemberInfo().getCompanyName());
        memberInfo.setUpdTime(new Date());
        form.setCoreMemberInfo(memberInfo);
        CoreUserHis userHis = coreUserHisService.queryCoreUserHisByMemberId(memberId);
        form.setUserHis(userHis);
        if (!form.getErrors().isEmpty()) {
            mv.addObject("userHis", userHis);
            return mv;
        }
        int key = 0;
        key = memberService.txUpdateByPrimaryKeySelective(memberInfo);
        if (key > 0) {
            // mv.addObject("successinfo", CommonDef.accountEdit.ACCOUNT_SUCCESS);
            CookieUtils.setTipMessageCookie(request, response, CommonDef.accountEdit.ACCOUNT_SUCCESS);
            mv.addObject("memberInfo", memberInfo);
            mv.addObject("userHis", userHis);
        }

        return mv;
    }

    /**
     * 首次登陆完善个人信息
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=updateMemberInfo", method = RequestMethod.POST)
    @RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_ADVERTISERS, RoleFactory.ROLE_AGENTS,
            RoleFactory.ROLE_DEVELOPERS, RoleFactory.ROLE_GENERAL, RoleFactory.ROLE_SHOPPER})
    public ModelAndView updateMemberInfo(MemberForm form, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("member/logon_page", Constants.DEFAULT_COMMAND, form);
        if (null == getLogonEmail()) {
            return mv;
        }
        CoreMemberInfo memberInfo = getMemberInfo();
        Long memberId = memberInfo.getMemberId();
        memberInfo.setMobile(form.getCoreMemberInfo().getMobile());
        memberInfo.setAddress(form.getCoreMemberInfo().getAddress());
        memberInfo.setZip(form.getCoreMemberInfo().getZip());
        memberInfo.setQq(form.getCoreMemberInfo().getQq());
        memberInfo.setMsn(form.getCoreMemberInfo().getMsn());
        memberInfo.setPhone(form.getCoreMemberInfo().getPhone());
        memberInfo.setCompanyName(form.getCoreMemberInfo().getCompanyName());
        memberInfo.setUpdTime(new Date());
        form.setCoreMemberInfo(memberInfo);
        // 验证
        if (!form.getErrors().isEmpty()) {
            mv = new ModelAndView("member/member_detail_first", Constants.DEFAULT_COMMAND, form);
            mv.addObject("memberInfo", memberInfo);
            mv.addObject("form", form);
            return mv;
        }

        int suc = 0;
        suc = memberService.txUpdateByPrimaryKeySelective(memberInfo);
        if (suc > 0) {// 提交成功
            request.getSession().setAttribute("sucInfo", "sucInfo");
        }
        return changeMember(form);
    }

    /**
     * 显示登录密码修改页面
     * 
     * @param form
     * @return 登录密码修改页面
     */
    @RequestMapping(params = "action=updateLoginPassword")
    @RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_ADVERTISERS, RoleFactory.ROLE_AGENTS,
            RoleFactory.ROLE_DEVELOPERS, RoleFactory.ROLE_GENERAL, RoleFactory.ROLE_SHOPPER})
    public ModelAndView updateLoginPassword(MemberForm form) {
        ModelAndView mv = new ModelAndView("member/update_login_password", Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 执行修改登录密码
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doUpdateLoginPassword", method = RequestMethod.POST)
    @RoleAuthority({RoleFactory.ROLE_ADV_AND_DEV, RoleFactory.ROLE_ADVERTISERS, RoleFactory.ROLE_AGENTS,
            RoleFactory.ROLE_DEVELOPERS, RoleFactory.ROLE_GENERAL, RoleFactory.ROLE_SHOPPER})
    public ModelAndView doUpdateLoginPassword(MemberForm form, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("member/update_login_password", Constants.DEFAULT_COMMAND, form);
        // 判断用户输入数据是否有错
        if (!form.getErrors().isEmpty()) {
            return mv;
        }
        String newPassword = "";
        newPassword = form.getNewPassword();
        // 判断新密码和重复新密码是否相同
        if (!newPassword.equals(form.getConfirmPassword())) {
            form.getErrors().put("confirmPassword", ErrorMessages.ACTIVATION_CODE_CONFIRM_ERROR);
            return mv;
        }
        // 判断密码的长度
        if (newPassword.length() > 20 || newPassword.length() < 6) {
            form.getErrors().put("newPassword", ErrorMessages.ACTIVATION_CODE_LENGTH_ERROR);
            return mv;
        }
        CoreMemberInfo memberInfo = new CoreMemberInfo();
        memberInfo.setMemberId(getLogonMemberId());
        memberInfo.setPassword(form.getOldPassword());
        // 判断旧密码是否正确
        boolean boolPassword = false;
        boolPassword = memberService.judgePassword(memberInfo);
        if (boolPassword == false) {
            form.getErrors().put("oldPassword", ErrorMessages.ACTIVATION_CODE_OLDPASSWORD_ERROR);
            return mv;
        }
        // 执行修改密码操作
        memberInfo.setPassword(form.getNewPassword());
        int key = 0;
        key = memberService.txUpdateMemberPassword(memberInfo);
        if (key > 0) {

            CookieUtils.setTipMessageCookie(request, response, CommonDef.accountEdit.PASSWORD_SUCCESS);
        }
        return mv;
    }

    /**
     * 无权访问页面
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=noPermission")
    public ModelAndView noPermission(MemberForm form) {
        return new ModelAndView("member/no_permission", Constants.DEFAULT_COMMAND, form);
    }

    /**
     * 获得角色
     * 
     * @param form
     * @return
     * @throws IOException
     */
    @RequestMapping(params = "action=getRoleType")
    public ModelAndView getRoleType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roleId = request.getParameter("roleId");
        if (org.apache.commons.lang.StringUtils.isNotBlank(roleId)) {
            String roleType = org.apache.commons.lang.StringUtils.trimToEmpty(roleId);
            if (RoleFactory.CURRENT_ROLE_TYPE_ADV.equals(roleType)
                    || RoleFactory.CURRENT_ROLE_TYPE_DEV.equals(roleType)) {
                CookieUtils.setSessionCookie(response, CookieUtils.COOKIE_KEY_CURRENT_ROLE_TYPE, roleType);
                // request.getSession().setAttribute("roleTypes", roleType);
                response.getWriter().print(roleType);
            }
        }
        return null;
    }

    @RoleAuthority({RoleFactory.ROLE_GENERAL, RoleFactory.ROLE_ADVERTISERS})
    @RequestMapping(params = "action=toBeDev")
    public ModelAndView toBeDev(MemberForm form, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("personal.do?action=advlist");
        CoreMemberInfo memberInfo = getMemberInfo();
        BaseRole role = getMemberInfo().getRole();
        int devType = 0;
        if (role.isAdvertisers()) {
            // 身份为广告商
            devType = RoleFactory.ROLE_ADV_AND_DEV;
        }
        else if (role.isGeneral()) {
            // 身份为普通会员
            devType = RoleFactory.ROLE_DEVELOPERS;
        }

        memberService.txUpdateMemberType(memberInfo, devType);// 提交数据,更新数据库中的信息

        String chart = "修改成功";

        try {
            response.getWriter().print(chart);
        }
        catch (IOException e) {
            logger.error("修改会员类型失败", e);
        }
        return null;
    }
}
