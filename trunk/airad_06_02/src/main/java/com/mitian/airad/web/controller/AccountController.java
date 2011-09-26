package com.mitian.airad.web.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mitian.airad.CommonDef;
import com.mitian.airad.Constants;
import com.mitian.airad.ErrorMessages;
import com.mitian.airad.common.codec.EncryptService;
import com.mitian.airad.model.AccIncomePay;
import com.mitian.airad.model.AccountInfoView;
import com.mitian.airad.model.CoreAccountInfo;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.model.CoreRechargeHis;
import com.mitian.airad.model.CoreResettingPassword;
import com.mitian.airad.service.AccountInfoService;
import com.mitian.airad.service.LogonService;
import com.mitian.airad.service.MemberService;
import com.mitian.airad.service.RechargeHisService;
import com.mitian.airad.service.ResettingPasswordService;
import com.mitian.airad.utils.CookieUtils;
import com.mitian.airad.utils.IdCardVerificationUtils;
import com.mitian.airad.utils.PropertiesOperateUtils;
import com.mitian.airad.utils.StringUtil;
import com.mitian.airad.web.auth.RoleAuthority;
import com.mitian.airad.web.auth.roles.BaseRole;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.mitian.airad.web.form.AccountForm;
import com.mitian.airad.web.form.RechargeHisForm;

/**
 * 财务管理
 * 
 * @author chenji
 */
@Controller
@RequestMapping("/account.do")
@RoleAuthority({RoleFactory.ROLE_DEVELOPERS, RoleFactory.ROLE_AGENTS, RoleFactory.ROLE_ADV_AND_DEV})
public class AccountController extends AbstractController {
    @Autowired
    private RechargeHisService rechargeHisService;
    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ResettingPasswordService resettingPasswordService;
    @Autowired
    private EncryptService encryptService;

    @Autowired
    private LogonService logonService;

    /*
     * ============================author:chenji=================================
     */
    /**
     * 显示用户财务信息界面
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=updateMemberAccountInfo")
    public ModelAndView updateMemberAccountInfo(AccountForm form) {
        ModelAndView mv = new ModelAndView("account/account_detail", Constants.DEFAULT_COMMAND, form);
        CoreAccountInfo coreAccountInfo = accountInfoService.selectAccountInfoByMemberId(getLogonMemberId());
        if (null != coreAccountInfo) {
            form.setCoreAccountInfo(coreAccountInfo);
        }
        mv.addObject("coreAccountInfo", form.getCoreAccountInfo());
        return mv;
    }

    /**
     * 执行财务信息修改
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doUpdateMemberAccountInfo", method = RequestMethod.POST)
    public ModelAndView doUpdateMemberAccountInfo(AccountForm form, HttpServletRequest request,
            HttpServletResponse response) {
        // ModelAndView mv = new ModelAndView("account/account_detail", Constants.DEFAULT_COMMAND, form);
        // 判断用户输入数据是否有错

        if (!form.getErrors().isEmpty()) {
            return updateMemberAccountInfo(form);
        }

        // mv = new ModelAndView("redirect:/withdraw.do?action=queryWithdrawListByMemberId");
        CoreAccountInfo accountInfo = form.getCoreAccountInfo();

        if (accountInfo.getAccId() == null) {
            String password = accountInfo.getPassword();
            String encryPassword = encryptService.digest(password, EncryptService.DIGEST_ALGORITHM.MD5.name());
            accountInfo.setPassword(encryPassword);
            accountInfo.setMemberId(getLogonMemberId());
            accountInfo.setAddTime(new Date());
            accountInfo.setAddOper(getLogonEmail());
            accountInfo.setAddName(getLogonEmail());
            accountInfoService.txAddAccountInfo(accountInfo);
            // 添加成功修改信息
            CookieUtils.setTipMessageCookie(request, response, Constants.WITHDRAW_SUC);
            return new ModelAndView("redirect:/withdraw.do?action=createWithdraw");
        }
        else {
            // 验证身份证号码是否正确
            String idCard = accountInfo.getIdCard();
            if (IdCardVerificationUtils.checkIdCard(idCard) != null) {
                form.getErrors().put("coreAccountInfo.idCard", IdCardVerificationUtils.checkIdCard(idCard));
                return updateMemberAccountInfo(form);
            }

            boolean boolPassword = false;
            boolPassword = accountInfoService.judgePassword(accountInfo);
            if (!boolPassword) {
                form.getErrors().put("coreAccountInfo.password", ErrorMessages.ACTIVATION_CODE_OLDPASS_ERROR);
                return updateMemberAccountInfo(form);
            }
            String password = accountInfo.getPassword();
            String encryPassword = encryptService.digest(password, EncryptService.DIGEST_ALGORITHM.MD5.name());
            accountInfo.setPassword(encryPassword);
            accountInfo.setUpdTime(new Date());

            // 执行修改密码操作
            int key = accountInfoService.txEditAccountInfo(accountInfo);

            CookieUtils.setTipMessageCookie(request, response, CommonDef.accountEdit.ACC_SUCCESS);
            return updateMemberAccountInfo(form);
        }
    }

    /**
     * 显示修改财务密码界面
     * 
     * @param form
     * @return
     */

    @RequestMapping(params = "action=updateAccountPassword")
    public ModelAndView updateAccountPassword(AccountForm form) {
        ModelAndView mv = new ModelAndView("account/update_password", Constants.DEFAULT_COMMAND, form);

        return mv;
    }

    /**
     * Email：账户密码重置跳转页面
     * 
     * @param form
     * @return
     */

    @RequestMapping(params = "action=updateEmailAccountPassword")
    @RoleAuthority({RoleFactory.ROLE_ALL})
    public ModelAndView updatemailAccountPassword(AccountForm form, HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("account/emailupdate_password", Constants.DEFAULT_COMMAND, form);
        // 上下文信息：
        if (StringUtil.isEmpty(form.getMail()) || StringUtil.isEmpty(form.getDate()) || StringUtil.isEmpty(form.getC())
                || StringUtil.isEmpty(form.getMemberId())) {
            return new ModelAndView("account/abate", Constants.DEFAULT_COMMAND, form);
        }

        Map<String, String> map = memberService.queryAccountRPLink(form.getMail(), form.getDate(), form.getC());
        if (map.size() > 0) {
            return new ModelAndView("account/abate", Constants.DEFAULT_COMMAND, form);
        }

        return mv;
    }

    /**
     * Email:邮件密码修改 获取用户id
     */
    @RequestMapping(params = "action=doEmailUpdateAccountPassword")
    @RoleAuthority({RoleFactory.ROLE_ALL})
    public ModelAndView doEmailUpdateAccountPassword(AccountForm form, HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("account/emailupdate_password", Constants.DEFAULT_COMMAND, form);
        // 判断用户输入数据是否有错
        if (!form.getErrors().isEmpty()) {
            return mv;
        }

        // 判断新密码和重复新密码是否相同
        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
            form.getErrors().put("confirmPassword", ErrorMessages.ACTIVATION_CODE_CONFIRM_ERROR);
            return mv;
        }
        CoreAccountInfo accountInfo = form.getCoreAccountInfo();
        Long memberId = getLogonMemberId();
        accountInfo.setMemberId(memberId);

        accountInfo.setPassword(form.getNewPassword());
        int key = 0;
        key = accountInfoService.txUpdateMemberPassword(accountInfo);
        if (key != 0) {
            // 密码修改成功后使连接失效 "1": 代表失效
            CoreResettingPassword coreResettingPassword = new CoreResettingPassword();
            coreResettingPassword.setMemberId(memberId);
            coreResettingPassword.setResettingType(Constants.ACCOUNT_PASS_FIND);
            coreResettingPassword.setStatus(Constants.PASSWORD_RESETTING_ABATE);
            resettingPasswordService.changeTag(coreResettingPassword);
            CookieUtils.setTipMessageCookie(request, response, Constants.ACCOUNTPASSWORD_SUCCESS);
        }
        // 上下文信息判：断用户是否需要登出

        if (memberId != 0 && Long.parseLong(form.getMemberId()) != getLogonMemberId()) { // 清空上下文
            logonService.cleanLogonContext(request, response);
            mv = new ModelAndView("account/emailsucc_tip");
            mv.addObject("tag", "tag");
            return mv;
        }

        return new ModelAndView("account/emailsucc_tip");
    }

    /**
     * 执行修改财务密码
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doUpdateAccountPassword", method = RequestMethod.POST)
    public ModelAndView doUpdateAccountPassword(AccountForm form, HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("account/update_password", Constants.DEFAULT_COMMAND, form);

        // 判断用户输入数据是否有错
        if (!form.getErrors().isEmpty()) {
            return mv;
        }

        // 判断新密码和重复新密码是否相同
        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
            form.getErrors().put("confirmPassword", ErrorMessages.ACTIVATION_CODE_CONFIRM_ERROR);
            return mv;
        }

        CoreAccountInfo accountInfo = form.getCoreAccountInfo();
        accountInfo.setMemberId(getLogonMemberId());
        accountInfo.setPassword(form.getOldPassword());
        // 获取会员信息,用来初始化用户财务账户信息
        CoreMemberInfo memberInfo = getMemberInfo();
        accountInfo.setInitPassword(memberInfo.getPassword());
        // 判断旧密码是否正确
        boolean boolPassword = false;
        boolPassword = accountInfoService.judgePassword(accountInfo);
        if (!boolPassword) {
            form.getErrors().put("oldPassword", ErrorMessages.ACTIVATION_CODE_OLDPASSWORD_ERROR);
            return mv;
        }
        // 执行修改密码操作
        accountInfo.setPassword(form.getNewPassword());
        int key = 0;
        key = accountInfoService.txUpdateMemberPassword(accountInfo);
        if (key != 0) {
            CookieUtils.setTipMessageCookie(request, response, Constants.ACCOUNTPASSWORD_SUCCESS);
        }

        return mv;
    }

    /**
     * 找回财务密码链接页面
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=retrieveAccountPassword")
    public ModelAndView retrieveAccountPassword(AccountForm form, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("account/retrieve_account_password", Constants.DEFAULT_COMMAND, form);
        // 判断用户是否登录
        Long memberId = 0L;
        memberId = getLogonMemberId();
        String serverUrl = "http://" + request.getServerName() + ":" + request.getLocalPort();
        if (memberId == 0) {
            return new ModelAndView("redirect:" + serverUrl + "/member.do?action=logon");
        }
        String email = getLogonEmail();
        form.setMail(email);
        mv.addObject("mail", email);
        mv.addObject("f", form);
        return mv;
    }

    @RequestMapping(params = "action=sendEmail")
    public ModelAndView sendEmail(AccountForm form, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("account/retrieve_account_password", Constants.DEFAULT_COMMAND, form);
        Long memberId = 0L;
        memberId = getLogonMemberId();
        String serverUrl = "http://" + request.getServerName() + ":" + request.getLocalPort();
        // 判断用户是否登录
        if (memberId == 0) {
            return new ModelAndView("redirect:" + serverUrl + "/member.do?action=logon");
        }
        // 判断数据验证是否通过
        if (!form.getErrors().isEmpty()) {
            return mv;
        }

        return mv;

    }

    /**
     * 处理财务找回密码链接传递过来的数据并且发送邮件
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doRetrieveAccountPassword", method = RequestMethod.POST)
    public ModelAndView doRetrieveAccountPassword(AccountForm form, HttpServletRequest request,
            HttpServletResponse response) {
        Long memberId = getLogonMemberId();

        String serverUrl1 = Constants.AIRAD_HOMEPAGE_URL;
        String path = request.getSession().getServletContext().getRealPath(Constants.AIRAD_PROERTIES_PATH);
        String serverUrl = PropertiesOperateUtils.getString(path, "mailServer", serverUrl1);

        // 判断用户是否登录

        if (memberId.equals(0L)) {
            return new ModelAndView("redirect:" + serverUrl + "/member.do?action=logon");
        }

        // 判断数据验证是否通过
        /*
         * if (!form.getErrors().isEmpty()) { return retrieveAccountPassword(form, request); }
         */

        // 发送邮件
        String mail = getLogonEmail();
        Map<String, Object> model = new HashMap<String, Object>();
        // 邮件地址
        model.put("email", mail);
        // 加密
        Date date = new Date();
        String ciphertext = memberService.generateActiveCode(mail + date.getTime());
        // 激活链接

        model.put("activeUrl", serverUrl + "/account.do?action=updateEmailAccountPassword&mail=" + mail + "&c="
                + ciphertext + "&date=" + date.getTime() + "&memberId=" + memberId);

        // 写数据库记录发送邮件历史
        CoreResettingPassword coreResettingPassword = new CoreResettingPassword();
        coreResettingPassword.setMemberId(memberId);
        coreResettingPassword.setAddTime(date);
        coreResettingPassword.setAddOper(form.getMail());
        coreResettingPassword.setCiphertext(ciphertext);

        Integer resettingId = resettingPasswordService.txAddSendEmail(coreResettingPassword);
        // Integer resettingId = resettingPasswordService.txAddCoreResettingPassword(coreResettingPassword);
        // 判断当天是否已经发送满三封
        if (resettingId > 3) {
            form.getErrors().put("errorInfo", ErrorMessages.SENDEMAILCOUNT_OUT);
            return retrieveAccountPassword(form, request);
        }
        // 调用发送邮件接口发送邮件
        String sendEmailresult = memberService.sendMail(model, "resettingAccountPassword", mail, "安全密码重置", true);

        if ("fail".equals(sendEmailresult)) {
            form.getErrors().put("verifyCode", ErrorMessages.SAND_EMAIL_ERR);
        }
        else {

            CookieUtils.setTipMessageCookie(request, response, Constants.SENDEMAIL_SUCCESS);
        }
        return retrieveAccountPassword(form, request);
    }

    /**
     * 密码提示问题界面
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=answerAccountQuestion")
    public ModelAndView answerAccountQuestion(AccountForm form, HttpServletRequest request) {
        String resettingId = request.getParameter("resettingId");
        if (StringUtils.isEmpty(resettingId)) {
            resettingId = form.getResettingId();
        }
        if (StringUtils.isBlank(resettingId)) {
            return new ModelAndView("redirect:/member.do?action=logon");
        }
        CoreResettingPassword coreResettingPassword =
                resettingPasswordService.queryByResettingId(StringUtil.stringToInteger(resettingId));
        CoreAccountInfo coreAccountInfo =
                accountInfoService.selectAccountInfoByMemberId(coreResettingPassword.getMemberId());
        form.setCoreAccountInfo(coreAccountInfo);
        form.setSecurityProblem(coreAccountInfo.getSecurityProblem());
        form.setResettingId(resettingId);
        return new ModelAndView("account/answer_account_question", Constants.DEFAULT_COMMAND, form);
    }

    /**
     * 显示操作结果页面
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=operateResult")
    public ModelAndView operateResult(AccountForm form) {
        ModelAndView mv = new ModelAndView("account/operate", Constants.DEFAULT_COMMAND, form);
        return mv;
    }

    /**
     * 查看用户输入的密码提示问题和数据库中的密码提示问题是否一致
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doAnswerAccountQuestion", method = RequestMethod.POST)
    public ModelAndView doAnswerAccountQuestion(AccountForm form, HttpServletRequest request) {
        String securityAnswer = form.getSecurityAnswer();
        CoreAccountInfo coreAccountInfo = form.getCoreAccountInfo();
        Long memberId = coreAccountInfo.getMemberId();
        coreAccountInfo = accountInfoService.selectAccountInfoByMemberId(memberId);
        if (!coreAccountInfo.getSecurityAnswer().equals(securityAnswer)) {
            form.getErrors().put("verifyCode", ErrorMessages.SECURITY_ANSWER_ERROR);
            return answerAccountQuestion(form, request);
        }
        else {
            form.setCoreAccountInfo(coreAccountInfo);
            return resettingAccountPassword(form, request);
        }
    }

    /**
     * 显示重置账户密码页面
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=resettingAccountPassword")
    public ModelAndView resettingAccountPassword(AccountForm form, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("account/resetting_account_password", Constants.DEFAULT_COMMAND, form);
        // 判断用户是否登录
        Long memberId = 0L;
        memberId = getLogonMemberId();
        String serverUrl = "http://" + request.getServerName() + ":" + request.getLocalPort();
        if (memberId == 0) {
            return new ModelAndView("redirect:" + serverUrl + "/member.do?action=logon");
        }
        return mv;
    }

    /**
     * 执行修改账户密码
     * 
     * @param form
     * @return
     */
    @RequestMapping(params = "action=doResettingAccountPassword", method = RequestMethod.POST)
    public ModelAndView doResettingAccountPassword(AccountForm form) {
        ModelAndView mv = new ModelAndView("account/resetting_account_password", Constants.DEFAULT_COMMAND, form);
        String newPassword = form.getNewPassword();
        String confirmPassword = form.getConfirmPassword();
        if (!newPassword.equals(confirmPassword)) {
            form.getErrors().put("verifyCode", ErrorMessages.ACTIVATION_CODE_CONFIRM_ERROR);
            return mv;
        }
        // 执行修改密码操作
        CoreAccountInfo coreAccountInfo = form.getCoreAccountInfo();
        coreAccountInfo = accountInfoService.selectAccountInfoByMemberId(coreAccountInfo.getMemberId());
        coreAccountInfo.setPassword(form.getNewPassword());
        int k = 0;
        k = accountInfoService.txUpdateMemberPassword(coreAccountInfo);
        if (k > 0) {
            form.getErrors().put("passwordSuc", CommonDef.accountEdit.PASSWORD_SUCCESS);
            return mv;
        }
        return mv;
    }

    /*
     * ==========================author:tangwenjun===============================
     */
    /**
     * 开发者、代理商资金转换,跳转页面
     * 
     * @param form
     * @param request
     * @return
     */
    @RoleAuthority({RoleFactory.ROLE_DEVELOPERS, RoleFactory.ROLE_AGENTS})
    @RequestMapping(params = "action=recharge")
    public ModelAndView recharger(RechargeHisForm form) {
        ModelAndView mv = new ModelAndView("account/app_recharge_create", Constants.DEFAULT_COMMAND, form);
        AccountInfoView accountInfoView = accountInfoService.queryAccountInfoById(getLogonMemberId());
        mv.addObject("accountInfoView", accountInfoView);
        // 得到会员基本信息
        CoreMemberInfo memberInfo = getMemberInfo();
        form.setCoreMemberInfo(memberInfo);
        BaseRole role = memberInfo.getRole();
        // 开发者广告商给自己转换金额
        if (role.isAdvAndDev()) {
            mv.addObject("form", form);
            mv.addObject("accountInfoView", accountInfoView);
            return mv;
        }
        // 代理商给转换金额
        else if (role.isAgents()) {
            mv = new ModelAndView("account/agent_recharge_create", Constants.DEFAULT_COMMAND, form);
            mv.addObject("form", form);
            mv.addObject("accountInfoView", accountInfoView);
            return mv;
        }
        return new ModelAndView("redirect:withdraw.do?action=queryWithdrawListByMemberId");
    }

    /**
     * 开发者、代理商资金转换
     * 
     * @param form
     * @param request
     * @return
     */
    @RoleAuthority({RoleFactory.ROLE_DEVELOPERS, RoleFactory.ROLE_AGENTS})
    @RequestMapping(params = "action=doRecharge")
    public ModelAndView saveOrUpdate(RechargeHisForm form) {
        // 发现表单校验有错误返回增加页面
        CoreRechargeHis rechargeHis = form.getCoreRechargeHis();
        // 判断正确类型
        if (!StringUtils.isEmpty(form.getMoney())) {
            boolean b = StringUtil.bigDecimalCon(form.getMoney());
            if (!b) {
                form.getErrors().put("MONEY_ERROR", ErrorMessages.MONEY_ERROR);
                return recharger(form);
            }
            rechargeHis.setMoney(new BigDecimal(form.getMoney()));
            /*
             * 根据memeberId找到用户金额信息
             */
            AccountInfoView accountInfoView = accountInfoService.queryAccountInfoById(getLogonMemberId());
            BigDecimal rechargeHisMoney = rechargeHis.getMoney();
            // 判断余额是否足够转换
            if (rechargeHisMoney.compareTo(accountInfoView.getIncomeBlance()) == 1) {
                form.getErrors().put("BALANCE_ERROR", ErrorMessages.BALANCE_ERROR);
                return recharger(form);
            }
            AccIncomePay accIncomePay = new AccIncomePay();
            accIncomePay.setMemberId(getLogonMemberId());
            accIncomePay.setAddOper(getLogonEmail());
            accIncomePay.setTradeType(CommonDef.tradeType.CON_CONVERT);
            String type = "";
            // withdrawService.txAddWithDraw(accIncomePay, rechargeHis, type);
            rechargeHis.setMemberId(getLogonMemberId());
            rechargeHis.setRechargeTime(new Date());
            rechargeHis.setRechargeType(CommonDef.rechargeHisType.CON_RECHARGE);
            rechargeHisService.txCreateRecharge(rechargeHis);
            ModelAndView mv = new ModelAndView("redirect:withdraw.do?action=queryWithdrawListByMemberId");
            return mv;
        }
        else {
            form.getErrors().put("MONEY_ERROR", ErrorMessages.REQUIRED_ERROR);
            return recharger(form);
        }
    }

    /**
     * 代理商给广告商充值跳转页面
     * 
     * @param form
     * @return
     */
    @RoleAuthority({RoleFactory.ROLE_AGENTS})
    @RequestMapping(params = "action=advRecharge")
    public ModelAndView advRecharge(RechargeHisForm form) {
        /*
         * 根据memeberId找到用户金额信息
         */
        AccountInfoView accountInfoView = accountInfoService.queryAccountInfoById(getLogonMemberId());
        Map<String, String> map = new HashMap<String, String>();
        map.put("memberId", getLogonMemberId().toString());
        // List<CoreAgentRelation> advertistorList = agentRelationService.queryList(map);
        // form.setAdvertistorList(advertistorList);
        ModelAndView mv = new ModelAndView("account/agentForAd_recharge_create", Constants.DEFAULT_COMMAND, form);
        mv.addObject("accountInfoView", accountInfoView);
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 代理商给广告商充值
     * 
     * @param form
     * @return
     */
    @RoleAuthority({RoleFactory.ROLE_AGENTS})
    @RequestMapping(params = "action=doAdvRecharge", method = RequestMethod.POST)
    public ModelAndView doAdvRecharge(RechargeHisForm form) {
        CoreRechargeHis rechargeHis = form.getCoreRechargeHis();
        rechargeHis.setMemberId(getLogonMemberId());
        rechargeHis.setMoney(new BigDecimal(form.getMoney()));
        /*
         * 根据memeberId找到用户金额信息
         */
        AccountInfoView accountInfoView = accountInfoService.queryAccountInfoById(getLogonMemberId());
        BigDecimal rechargeHisMoney = rechargeHis.getMoney();
        // 判断余额是否足够转换
        if (rechargeHisMoney.compareTo(accountInfoView.getAcceptBlance()) == 1) {
            form.getErrors().put("BALANCE_ERROR", ErrorMessages.BALANCE_ERROR);
            return advRecharge(form);
        }
        // 代理商支出
        AccIncomePay agentAccIncomePay = new AccIncomePay();
        agentAccIncomePay.setMemberId(getLogonMemberId());
        agentAccIncomePay.setTradeType(CommonDef.tradeType.CON_PAY);
        agentAccIncomePay.setIncomePayType(CommonDef.tradeType.CON_AGENT_INCOME_PAY);
        agentAccIncomePay.setAddOper(getLogonEmail());
        agentAccIncomePay.setAdId(form.getAdvertistorId());
        String type = "";
        // withdrawService.txAddWithDraw(agentAccIncomePay, rechargeHis, type);
        // 广告商收益
        AccIncomePay advAccIncomePay = new AccIncomePay();
        Integer advertistorId = form.getAdvertistorId();
        advAccIncomePay.setMemberId(Long.valueOf(advertistorId));
        advAccIncomePay.setTradeType(CommonDef.tradeType.CON_AGENT_RECHARGE);
        agentAccIncomePay.setAddOper(getLogonEmail());
        agentAccIncomePay.setAdId(advertistorId);
        // withdrawService.txAddWithDraw(advAccIncomePay, rechargeHis, type);
        // 充值历史记录
        rechargeHis.setMemberId(getLogonMemberId());
        rechargeHis.setRechargeTime(new Date());
        rechargeHis.setRechargeType(CommonDef.rechargeHisType.CON_AGENT);
        rechargeHisService.txCreateRecharge(rechargeHis);
        return new ModelAndView("redirect:withdraw.do?action=queryWithdrawListByMemberId");
    }
}
