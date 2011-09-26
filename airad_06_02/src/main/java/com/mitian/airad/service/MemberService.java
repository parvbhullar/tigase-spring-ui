package com.mitian.airad.service;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.mitian.airad.Constants;
import com.mitian.airad.ErrorMessages;
import com.mitian.airad.common.codec.EncryptService;
import com.mitian.airad.common.constant.MemberConstant;
import com.mitian.airad.common.constant.OpenApiConstants;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.component.mail.Email;
import com.mitian.airad.component.mail.MailComponent;
import com.mitian.airad.component.openapi.TaobaoOpenApiComponent;
import com.mitian.airad.dao.CoreBindInfoDAO;
import com.mitian.airad.dao.CoreMemberInfoDAO;
import com.mitian.airad.model.AiradDevmember;
import com.mitian.airad.model.CoreAgentRelation;
import com.mitian.airad.model.CoreBindInfo;
import com.mitian.airad.model.CoreInviteCode;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.model.CoreResettingPassword;
import com.mitian.airad.utils.StringUtil;
import com.mitian.airad.web.auth.roles.RoleFactory;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.User;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.response.UserGetResponse;

/**
 * RegisterService.java
 */
@Service
public class MemberService {
    private static final Log LOGGER = LogFactory.getLog(MemberService.class);
    @Autowired
    private CoreMemberInfoDAO coreMemberInfoDAO;
    @Autowired
    private CoreBindInfoDAO coreBindInfoDAO;
    @Autowired
    private EncryptService encryptService;
    @Autowired(required = false)
    private MailComponent mailComponent;
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private InviteCodeService inviteCodeService;
    @Autowired
    private AgentRelationService agentRelationService;
    //
    @Autowired
    private ResettingPasswordService resettingPasswordService;
    @Autowired
    private TaobaoOpenApiComponent taobaoOpenApiComponent;

    /**
     * 根据email查询会员
     * 
     * @param email
     * @return
     */
    public CoreMemberInfo selectMemberInfoByEmail(String email) {
        return coreMemberInfoDAO.selectByEmail(email);
    }

    /**
     * 根据id查询会员
     * 
     * @param id
     * @return
     */
    public CoreMemberInfo queryMemberById(Long id) {
        return coreMemberInfoDAO.getByMemberId(id);
    }

    /**
     * 提交注册
     * 
     * @param form
     * @return
     */
    public Map<String, String> txAddMember(CoreMemberInfo coreMember) {
        Map<String, String> result = new HashMap<String, String>();
        // 根据email地址查找当前地址是否重复
        String email = coreMember.getEmail().toLowerCase();
        coreMember.setEmail(email);
        if (coreMemberInfoDAO.selectByEmail(coreMember.getEmail()) != null) {
            result.put("verifyCode", ErrorMessages.USER_EXIST);
            return result;
        }
        // 此处密码采用MD5加密
        coreMember.setPassword(encryptService.digest(coreMember.getPassword(), EncryptService.DIGEST_ALGORITHM.MD5
                .name()));
        // 生成注册成功激活码
        String activeCode = generateActiveCode(coreMember.getEmail());
        // 保存到数据库中
        coreMember.setActiveCode(activeCode);
        // 激活码生成时间
        coreMember.setActiveTime(new Date());
        // 注册时间
        coreMember.setRegisterTime(new Date());
        coreMember.setMemberStatus(Constants.MEMBER_STATUS_NORMAL);
        CoreInviteCode coreInviteCode = null;
        // 判断是否是从邀请链接进来的
        if (!StringUtil.isEmpty(coreMember.getInviteCode())) {
            coreInviteCode = inviteCodeService.selectInviteByCode(coreMember.getInviteCode());
            // 判断邀请码是否存在
            if (null != coreInviteCode) {
                // 判断邀请码是否有效
                if (Constants.INVITE_STATUS_NO_USAGE.equals(coreInviteCode.getInviteStatus())
                        || Constants.INVITE_STATUS_GESENDET.equals(coreInviteCode.getInviteStatus())) {
                    // 是否是开发者邀请
                    if (Constants.INVITE_TYPE_DEVELOPER.equals(coreInviteCode.getType())) {
                        coreMember.setMemberType(RoleFactory.ROLE_DEVELOPERS);
                        int id = coreMemberInfoDAO.insertSelective(coreMember);
                        // 修改邀请码信息状态为无效
                        coreInviteCode.setInviteStatus("1");
                        coreInviteCode.setInviteMemberId(id);
                        inviteCodeService.txUudateInviteCodeStat(coreInviteCode);
                    }
                    // 是否是广告主邀请
                    else if (Constants.INVITE_TYPE_ADVERTISERS.equals(coreInviteCode.getType())) {
                        // 添加广告主和代理商绑定关系
                        coreMember.setMemberType(RoleFactory.ROLE_ADVERTISERS);
                        int id = coreMemberInfoDAO.insertSelective(coreMember);
                        CoreAgentRelation coreAgentRelation = new CoreAgentRelation();
                        coreAgentRelation.setAddTime(new Date());
                        coreAgentRelation.setMemberId(coreInviteCode.getMemberId());
                        coreAgentRelation.setRelationStatus("0");
                        coreAgentRelation.setAdvertiserNum(id);
                        // 绑定关系
                        agentRelationService.txCreateAgentRelation(coreAgentRelation);
                        // 修改邀请码信息状态为无效
                        coreInviteCode.setInviteStatus("1");
                        coreInviteCode.setInviteMemberId(id);
                        inviteCodeService.txUudateInviteCodeStat(coreInviteCode);
                    }
                }
                else {
                    result.put("verifyCodeErr", ErrorMessages.INVITE_CODE_NULLITY);
                    return result;
                }
            }
        }
        else {
            coreMember.setMemberType(RoleFactory.ROLE_GENERAL);
            int memberId = coreMemberInfoDAO.insertSelective(coreMember);
            // 判断是否第三方接口进来注册的(sunncy 20110909)---------------------
            if (StringUtils.isNotEmpty(coreMember.getUid())) {
                coreMember.setMemberId(StringUtil.stringToLong(memberId + ""));
                bindMerberId(coreMember);
            }
            // =====================end=====================
        }
        // 发送邮件给用户
        regSuccessSendMail(coreMember, coreMember.getUrlName());
        return result;
    }

    /**
     * 根据email platformType绑定用户id
     * 
     * @param coreMember
     * @return
     */
    public boolean bindMerberId(CoreMemberInfo coreMember) {
        Long merberId = coreMember.getMemberId();
        Short platformType = StringUtil.stringToShort(coreMember.getPlatformeType());
        if (merberId > 0 && !isBindPlatformByEmailAndPlatformType(coreMember.getEmail(), platformType)) {
            CoreBindInfo coreBindInfo = new CoreBindInfo();
            coreBindInfo.setMemberId(merberId);
            coreBindInfo.setEmail(coreMember.getEmail());
            coreBindInfo.setPlatformType(StringUtil.stringToShort(coreMember.getPlatformeType()));
            if (coreMember.getUid() != null) {
                String uidDec =
                        encryptService.decrypt("" + coreMember.getUid(),
                                EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name());
                if (StringUtil.isNumeric(uidDec)) {
                    coreBindInfo.setUid(StringUtil.stringToLong(uidDec));
                    coreBindInfoDAO.updateMemmberIdByUidAndPlatPlatformType(coreBindInfo);
                }

            }
            else {
                coreBindInfoDAO.updateMemmberIdByEmailAndPlatPlatformType(coreBindInfo);
            }
            return true;
        }
        return false;
    }

    /**
     * 提交注册
     * 
     * @param form
     * @return
     */
    public boolean txAddMemberOther(CoreMemberInfo coreMember) {
        // 此处密码采用MD5加密
        coreMember.setPassword(encryptService.digest(coreMember.getPassword(), EncryptService.DIGEST_ALGORITHM.MD5
                .name()));
        // 注册时间
        coreMember.setRegisterTime(new Date());
        coreMember.setMemberStatus(Constants.MEMBER_STATUS_NORMAL);
        coreMember.setMemberType(RoleFactory.ROLE_GENERAL);
        coreMember.setActiveCode("1");
        int memberId = coreMemberInfoDAO.insertSelective(coreMember);
        coreMember.setMemberId(StringUtil.stringToLong(memberId + ""));
        if (bindMerberId(coreMember)) {
            return true;
        }
        return false;

    }

    /**
     * @param airadDevmember
     * @return
     */
    public Map<String, String> txAddMember(AiradDevmember airadDevmember) {
        Map<String, String> result = new HashMap<String, String>();
        int id = coreMemberInfoDAO.insertAiradDevmember(airadDevmember);
        return result;
    }

    /**
     * 再次发送激活邮件
     * 
     * @param email 会员
     * @return
     */
    public Map<String, String> txRegisterActivation(String email, final String urlName) {
        Map<String, String> result = new HashMap<String, String>();
        final CoreMemberInfo coreMemberInfo = coreMemberInfoDAO.selectByEmail(email);
        if (null != coreMemberInfo) {
            if (coreMemberInfo.getActiveCode().length() < 2) {
                result.put("sendmailerr", ErrorMessages.ACTIVATION_CODE_ABATE);
                return result;
            }
            // 生成注册成功激活码
            String activeCode = generateActiveCode(email);
            coreMemberInfo.setActiveCode(activeCode);
            coreMemberInfo.setActiveTime(new Date());
            // 发送邮件给用户(异步发送)
            regSuccessSendMail(coreMemberInfo, urlName);
            coreMemberInfoDAO.updateByPrimaryKeySelective(coreMemberInfo);
        }
        else {
            result.put("sendmailerr", ErrorMessages.SAND_EMAIL_ERR);
        }
        return result;
    }

    /**
     * 根据邮件地址产生激活码
     * 
     * @param loginEmail
     * @return
     */
    public String generateActiveCode(String loginEmail) {
        StringBuffer activeCode = new StringBuffer();
        try {
            // 用户登录名加密
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // md5.digest(loginEmail.getBytes());
            byte[] byte1 = md5.digest(loginEmail.getBytes());
            // encryptService.digest(loginEmail, EncryptService.DIGEST_ALGORITHM.MD5.name());

            // 当前时间加密
            // byte[] byte2 = md5.digest(DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm").getBytes());

            // activeCode = activeCode + "_" + curTime;

            // String xx=new String(byte1);
            for (byte element : byte1) {
                // char c = (char) element;
                activeCode.append(String.valueOf(element));
            }
        }
        catch (Exception e) {
            LOGGER.error("generateActiveCode error", e);
        }
        return activeCode.toString();
    }

    /**
     * 注册激活
     * 
     * @param activeEmail
     * @param activeCode
     * @return
     */
    public Map<String, String> txRegActive(String activeEmail, String activeCode) {
        Map<String, String> result = new HashMap<String, String>();
        if (activeEmail != null && activeCode != null) {
            // CoreMemberInfo coreMemberInfo = new CoreMemberInfo();
            // coreMemberInfo.setActiveCode(activeCode);
            // coreMemberInfo.setEmail(activeEmail);
            CoreMemberInfo coreMemberInfo = coreMemberInfoDAO.selectByEmail(activeEmail);
            // 判断激活码是否对应用户,激活码是否失效
            if (coreMemberInfo != null) {
                if (coreMemberInfo.getActiveTime() != null) {
                    Long time = (new Date().getTime() - coreMemberInfo.getActiveTime().getTime()) / 1000;
                    if (time < 24 * 3600) {
                        if (coreMemberInfo.getActiveCode().equals(activeCode)) {
                            coreMemberInfo.setActiveCode(MemberConstant.ACTIVE_CODE_ACTIVED);
                            coreMemberInfoDAO.updateByPrimaryKeySelective(coreMemberInfo);
                            return result;
                        }
                        result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_NULLITY);
                    }
                    else {
                        result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
                    }
                }
            }
            result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_LINKS_NULLITY);
        }
        return result;
    }

    /**
     * 注册成功后，需要发送激活链接
     * 
     * @throws Exception
     */
    public String regSuccessSendMail(CoreMemberInfo newRecord, String urlName) {
        // 发送邮件
        Map<String, Object> model = new HashMap<String, Object>();
        // 邮件地址
        model.put("email", newRecord.getEmail());
        // 激活链接
        // model.put("activeUrl", "http://www.airad.com/member.do?action=regActive&email=" + newRecord.getEmail()
        // + "&activeCode=" + newRecord.getActiveCode());
        // 测试激活链接
        model.put("activeUrl", Constants.AIRAD_HOMEPAGE_URL + "/member.do?action=regActive&email="
                + newRecord.getEmail() + "&activeCode=" + newRecord.getActiveCode());
        // 调用发送邮件接口发送邮件
        String result = sendMail(model, "registration", newRecord.getEmail(), "欢迎加入airAD", true);
        return result;
    }

    /**
     * 邮件发送实现
     * 
     * @param model 参数
     * @param vmName 配置文件名字
     * @param toMail 要发送的邮件
     * @param sendTitle 只有第一个参数有效!
     * @return 发送结果
     */
    public String sendMail(final Map<String, Object> model, final String vmName, final String toMail,
            final String sendTitle, boolean isAsync) {
        String result = "succ";
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, vmName + ".vm", model);
        Email email = new Email(toMail, Email.DEFAULT_FROM_ADDRESS, sendTitle, text, true);
        try {
            if (isAsync) {
                mailComponent.sendSingleMailByAsynchronousMode(email);
            }
            else {
                mailComponent.sendSingleMail(email);
            }
        }
        catch (Exception e) {
            LOGGER.error("memberService.sendMail error!", e);
            result = "fail";
        }
        return result;
    }

    /**
     * 发送邮件找回登录密码
     */
    public Map<String, String> txSendMialByResettingPassword(final String email, String urlName) {
        Map<String, String> res = new HashMap<String, String>();
        // 发送邮件
        final Map<String, Object> model = new HashMap<String, Object>();
        // 邮件地址
        model.put("email", email);
        // // 加密后的邮箱
        // String email = encryptService.digest(form.getEmail(), EncryptService.DIGEST_ALGORITHM.MD5.name());
        Date date = new Date();
        // 加密
        String ciphertext = generateActiveCode(email + date.getTime());
        // 激活链接
        model.put("activeUrl", urlName + "/member.do?action=resettingPasswordPage&email=" + email + "&c=" + ciphertext
                + "&date=" + date.getTime());
        CoreMemberInfo coreMemberInfo = coreMemberInfoDAO.selectByEmail(email);
        if (null == coreMemberInfo) {
            res.put("verifyCode", ErrorMessages.USER_LACK);
            return res;
        }
        if (!"1".equals(coreMemberInfo.getActiveCode())) {
            res.put("verifyCode", ErrorMessages.USER_NOT_ACTIVATION);
            return res;
        }
        // 保存到数据库
        Map<String, String> map =
                resettingPasswordService.txAddCoreResettingPassword(coreMemberInfo.getEmail(), coreMemberInfo
                        .getMemberId(), Constants.PASSWORD_RESETTING_TYPE, date, ciphertext);
        if (map.size() > 0) {
            res.putAll(map);
            return res;
        }
        // 调用发送邮件接口发送邮件
        // 发送邮件给用户(异步发送)
        sendMail(model, "resettingPassword", email, "密码找回", true);
        return res;
    }

    public Map<String, String> queryAccountRPLink(String email, String date, String ciphertext) {
        Map<String, String> result = new HashMap<String, String>();
        if (StringUtil.isEmpty(ciphertext) || StringUtil.isEmpty(date) || StringUtil.isEmpty(email)) {
            result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
        }
        else {
            CoreMemberInfo coreMemberInfo = coreMemberInfoDAO.selectByEmail(email);
            if (null != coreMemberInfo) {
                CoreResettingPassword coreResettingPassword =
                        resettingPasswordService.queryByMemberId(coreMemberInfo.getMemberId(),
                                Constants.ACCOUNT_PASS_FIND);
                if (null == coreResettingPassword) {
                    result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
                    return result;
                }
                if (!ciphertext.equals(coreResettingPassword.getCiphertext())) {
                    result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
                    return result;
                }
                if ("1".equals(coreResettingPassword.getStatus())) {
                    result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
                    return result;
                }
                // 判断邮件是否超过24小时
                long time1 = new Date().getTime();
                long time2 = Long.parseLong(date);
                long time = (time1 - time2) / 1000;
                // 122032
                if (time > (24 * 3600)) {
                    result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
                    return result;
                }

            }
            else {
                result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
            }
        }

        return result;
    }

    /**
     * 判断重置邮件是否有效！
     * 
     * @param email
     * @param date
     * @return
     */
    public Map<String, String> queryResettingPasswordLink(String email, String date, String ciphertext) {
        Map<String, String> result = new HashMap<String, String>();
        if (StringUtil.isEmpty(ciphertext) || StringUtil.isEmpty(date) || StringUtil.isEmpty(email)) {
            result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
        }
        else {
            CoreMemberInfo coreMemberInfo = coreMemberInfoDAO.selectByEmail(email);
            if (null != coreMemberInfo) {
                CoreResettingPassword coreResettingPassword =
                        resettingPasswordService.queryByMemberId(coreMemberInfo.getMemberId(),
                                Constants.PASSWORD_RESETTING_TYPE);
                if (null == coreResettingPassword) {
                    result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
                    return result;
                }
                if (!ciphertext.equals(coreResettingPassword.getCiphertext())) {
                    result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
                    return result;
                }
                if ("1".equals(coreResettingPassword.getStatus())) {
                    result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
                    return result;
                }
                long time1 = coreResettingPassword.getResettingTime().getTime();
                long time2 = Long.parseLong(date);
                long time = time1 - time2;
                if (time > Constants.MAX_POSITIVE_DISCREPANCY || time < Constants.MAX_MINUS_DISCREPANCY) {
                    result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
                    return result;
                }
            }
            else {
                result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
            }
        }
        return result;
    }

    /**
     * 密码重置
     * 
     * @param form
     * @return
     */
    public Map<String, String> txResettingPassword(final String email, String password) {
        Map<String, String> result = new HashMap<String, String>();
        CoreMemberInfo coreMemberInfo = coreMemberInfoDAO.selectByEmail(email);
        if (null != coreMemberInfo) {
            CoreResettingPassword coreResettingPassword =
                    resettingPasswordService.queryByMemberId(coreMemberInfo.getMemberId(),
                            Constants.PASSWORD_RESETTING_TYPE);
            if (null == coreResettingPassword) {
                result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
                return result;
            }
            if ("1".equals(coreResettingPassword.getStatus())) {
                result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
                return result;
            }
            if ((new Date().getTime() - coreResettingPassword.getResettingTime().getTime()) < (3600 * 1000 * 24)) {
                // 加密密码
                coreMemberInfo.setPassword(encryptService.digest(password, EncryptService.DIGEST_ALGORITHM.MD5.name()));
                coreMemberInfoDAO.updateByPrimaryKeySelective(coreMemberInfo);
                resettingPasswordService.txEditCoreResettingPassword(coreResettingPassword);
                final Map<String, Object> model = new HashMap<String, Object>();
                model.put("email", email);
                // 发送邮件给用户(异步发送)
                sendMail(model, "resettingPasswordSucc", email, "密码重置成功", true);
                return result;
            }
            else {
                result.put("verifyCode", ErrorMessages.ACTIVATION_CODE_ABATE);
                return result;
            }
        }
        return result;
    }

    /**
     * 根据用户id查询用户基本信息
     * 
     * @param memberId
     * @return
     */
    public CoreMemberInfo queryMemberInfo(Long memberId) {
        CoreMemberInfo memberInfo = coreMemberInfoDAO.getByMemberId(memberId);
        return memberInfo;
    }

    /**
     * 判断传递传递过来的密码和数据库中的密码是否匹配
     * 
     * @param record
     * @return
     */
    public boolean judgePassword(CoreMemberInfo record) {
        boolean passwordResult = false;
        // CoreMemberInfo memberInfo = new CoreMemberInfo();
        // 对传递过来的password进行加密
        // String password = encryptService.digest(record.getPassword(),
        // EncryptService.DIGEST_ALGORITHM.MD5.name());
        // 通过email获取密码
        CoreMemberInfo coreMemberInfo = coreMemberInfoDAO.getByMemberId(record.getMemberId());
        // 判断传递过来的密码和数据库中的密码是否相同，如果相同，执行修改密码操作
        if (encryptService.matches(record.getPassword(), coreMemberInfo.getPassword(),
                EncryptService.DIGEST_ALGORITHM.MD5.name())) {
            passwordResult = true;
        }
        return passwordResult;
    }

    /**
     * 修改用户信息
     * 
     * @param 需要提供的数据
     * @return 修改用户的id
     */
    public int txUpdateByPrimaryKeySelective(CoreMemberInfo record) {
        int key = 0;
        key = coreMemberInfoDAO.updateByPrimaryKeySelective(record);
        return key;
    }

    /**
     * 修改用户密码
     * 
     * @param record
     * @return 用户Id
     */
    public int txUpdateMemberPassword(CoreMemberInfo record) {
        int key = 0;
        record.setPassword(encryptService.digest(record.getPassword(), EncryptService.DIGEST_ALGORITHM.MD5.name()));
        key = coreMemberInfoDAO.updateByPrimaryKeySelective(record);
        return key;
    }

    /**
     * 更新会员状态
     * 
     * @param memberInfo
     */
    public void txUpdateMemberType(CoreMemberInfo memberInfo, int memberType) {
        memberInfo.setMemberType(memberType);
        coreMemberInfoDAO.updateMemberType(memberInfo);
    }

    public String getSessionKey(String oauthUrl, String appKey, String appSecret, String code) throws Exception {
        HttpClient hc = new HttpClient();
        if (StringUtils.isBlank(code)) {
            throw new Exception("code cannot be null!");
        }
        PostMethod method = new PostMethod(oauthUrl);
        NameValuePair grantType = new NameValuePair("grant_type", "authorization_code");
        NameValuePair c = new NameValuePair("code", code);
        NameValuePair redirectUri = new NameValuePair("redirect_uri", taobaoOpenApiComponent.getCallBackUrl());
        NameValuePair clientId = new NameValuePair("client_id", appKey);
        NameValuePair clientSecret = new NameValuePair("client_secret", appSecret);
        method.setRequestBody(new NameValuePair[]{grantType, c, redirectUri, clientId, clientSecret});
        hc.executeMethod(method);
        String resBody = method.getResponseBodyAsString();
        JSONObject resJson = new JSONObject(resBody);
        String accessToken = resJson.optString("access_token");
        if (StringUtils.isBlank(accessToken)) {
            throw new Exception("sessionKey cannot be null!");
        }
        return accessToken;
    }

    public String txprocessTaobaoLogonAndPrepareRedirectUrl(String apiHost, String appKey, String appSecret,
            String sessionKey) throws Exception {
        // 得到淘宝用户信息
        TaobaoClient client = new DefaultTaobaoClient(apiHost, appKey, appSecret);
        UserGetRequest uReq = new UserGetRequest();
        uReq
                .setFields("user_id,nick,email,sex,buyer_credit,seller_credit,"
                        + "created,last_visit,location,birthday,type,"
                        + "has_more_pic,item_img_num,item_img_size,prop_img_num,prop_img_size,auto_repost,promoted_type,status,"
                        + "alipay_bind,consumer_protection,alipay_account,alipay_no,"
                        + "avatar,liangpin,sign_food_seller_promise,has_shop,is_lightning_consignment,vip_info,magazine_subscribe,vertical_market,online_gaming");
        UserGetResponse res = client.execute(uReq, sessionKey);
        //
        CoreBindInfo bindInfo = getBindInfoFromTaobaoUserResponse(res);
        String suffix = prepareParams4TaobaoLogonRedirectUrl(bindInfo.getUid());

        String email = bindInfo.getEmail();
        String nextUrl = null;
        if (bindInfo.getMemberId() != null) {
            nextUrl = OpenApiConstants.ONCE_LOGON.concat(suffix);
        }
        else {
            if (StringUtils.isNotBlank(email)) {
                CoreMemberInfo memberInfo = coreMemberInfoDAO.selectByEmail(email);
                nextUrl =
                        memberInfo != null ? OpenApiConstants.ONCE_LOGON.concat(suffix)
                                : OpenApiConstants.ONCE_REGISTER.concat(suffix);
            }
            else {
                nextUrl = OpenApiConstants.REGISTER.concat(suffix);
            }
        }
        return nextUrl;
    }

    private String prepareParams4TaobaoLogonRedirectUrl(Long uid) {
        return "&uid=" + encryptService.encrypt("" + uid, EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name())
                + "&loginType=" + OpenApiConstants.PLATFORM_TAOBAO;
    }

    // 通过解析过来的数据显示页面
    public CoreMemberInfo memberInfoDecryptUid(String uidEnc, String loginType) {
        CoreMemberInfo coreMemberInfo = null;
        String uidDec = encryptService.decrypt("" + uidEnc, EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name());
        Long uid = StringUtil.stringToLong(uidDec);
        short lt = StringUtil.stringToShort(loginType);
        CoreBindInfo coreBindInfo = findBindInfoByUidAndPlatformType(uid, lt);
        if (coreBindInfo.getMemberId() != null) {
            coreMemberInfo = queryMemberById(coreBindInfo.getMemberId());
            if (coreMemberInfo == null) {
                coreMemberInfo = selectMemberInfoByEmail(coreBindInfo.getEmail());
            }
        }
        else {
            coreMemberInfo = selectMemberInfoByEmail(coreBindInfo.getEmail());
        }

        return coreMemberInfo;
    }

    // 通过解析过来的数据显示email
    public String emailDecryptUid(String uidEnc, String loginType) {
        String uidDec = encryptService.decrypt("" + uidEnc, EncryptService.ENCRYPT_ALGORITHM.PBEWithMD5AndDES.name());
        Long uid = StringUtil.stringToLong(uidDec);
        short lt = StringUtil.stringToShort(loginType);
        CoreBindInfo coreBindInfo = findBindInfoByUidAndPlatformType(uid, lt);
        if (coreBindInfo != null) {
            return coreBindInfo.getEmail();
        }

        return null;
    }

    private CoreBindInfo getBindInfoFromTaobaoUserResponse(UserGetResponse res) throws Exception {
        CoreBindInfo bindInfo = userResponse2BindInfo(res);
        CoreBindInfo dbRecord = findBindInfoByUidAndPlatformType(bindInfo.getUid(), OpenApiConstants.PLATFORM_TAOBAO);
        //
        if (dbRecord != null) {
            if (!bindInfo.equals(dbRecord)) {
                bindInfo.setId(dbRecord.getId());
                coreBindInfoDAO.updateByPrimaryKeySelective(bindInfo);
                return bindInfo;
            }
            return dbRecord;
        }
        else {
            coreBindInfoDAO.insertSelective(bindInfo);
            return bindInfo;
        }
    }

    private CoreBindInfo userResponse2BindInfo(UserGetResponse res) throws Exception {
        User curUser = res.getUser();
        if (curUser == null) {
            throw new Exception("curUser is null!");
        }
        String email = curUser.getEmail().toLowerCase().trim();
        String nickName = curUser.getNick().trim();
        Long uid = curUser.getUserId();
        String body = res.getBody();
        //
        CoreBindInfo bindInfo = new CoreBindInfo();
        bindInfo.setEmail(email);
        bindInfo.setPlatformType(OpenApiConstants.PLATFORM_TAOBAO);
        bindInfo.setUid(uid);
        bindInfo.setUserName(nickName);
        String user = new JSONObject(body).optString("user_get_response");
        String userInfo = new JSONObject(user).optString("user");
        bindInfo.setUserJson(userInfo);
        return bindInfo;
    }

    private CoreBindInfo findBindInfoByUidAndPlatformType(Long uid, short platformType) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", uid);
        params.put("platformType", platformType);
        return coreBindInfoDAO.selectOne(params);
    }

    private CoreBindInfo findBindInfoByMemberIdAndPlatformType(Long memberId, short platformType) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("memberId", memberId);
        params.put("platformType", platformType);
        return coreBindInfoDAO.selectOne(params);
    }

    /**
     * 根据绑定表判断接口登录页面跳转(20110908 sunncy)
     */
    public String urlBindInfo(Long uid, short platformType) {
        CoreBindInfo coreBindInfo = findBindInfoByUidAndPlatformType(uid, platformType);
        boolean b = isExistMemberInfoByEmail(coreBindInfo.getEmail());
        if (b) {
            // 跳转到广告主页面
            return OpenApiConstants.DOREGISTER;
        }
        // 跳转到绑定页面
        return OpenApiConstants.ONCE_LOGON_PAGE;
    }

    /**
     * 通过email判断数据库中是否存在memberInfo信息 (20110908 sunncy )
     * 
     * @param email
     * @return
     */
    private boolean isExistMemberInfoByEmail(String email) {
        CoreMemberInfo coreMemberInfo = selectMemberInfoByEmail(email);
        if (coreMemberInfo == null) {
            return false;
        }
        return true;
    }

    /**
     * 通过uid和平台判断数据库中是否存在memberInfo信息 (20110908 sunncy )
     * 
     * @param email
     * @return
     */
    private boolean isExistMemberInfoByUidAndPlatformType(Long uid, Short platformType) {
        CoreBindInfo coreBindInfo = findBindInfoByUidAndPlatformType(uid, platformType);
        if (coreBindInfo == null) {
            return false;
        }
        return true;
    }

    /**
     * 通过memberid和平台判断这个用户是否和平台绑定了(20110908 sunncy )
     * 
     * @param email
     * @return
     */
    public boolean isBindPlatformByEmailAndPlatformType(String email, Short platformType) {
        CoreMemberInfo coreMemberInfo = selectMemberInfoByEmail(email);
        if (coreMemberInfo == null || coreMemberInfo.getMemberId() == null || platformType == null) {
            LOGGER.error("参数产传递错误isBindPlatformByEmailAndPlatformType方法：coreMemberInfo" + coreMemberInfo
                    + ",platformType" + platformType + "或者coreMemberInfo.getMemberId()为空");
            return true;
        }
        CoreBindInfo coreBindInfo = findBindInfoByMemberIdAndPlatformType(coreMemberInfo.getMemberId(), platformType);
        if (coreBindInfo == null) {
            return false;
        }
        return true;
    }

    /**
     * 通过会员id和平台id获取uid
     * 
     * @param coreMemberInfo
     * @return
     */
    private Long selectUidByMemberIdAndPlatformType(CoreMemberInfo coreMemberInfo) {
        Short platformType = StringUtil.stringToShort(coreMemberInfo.getPlatformeType());
        CoreBindInfo bindInfo = findBindInfoByMemberIdAndPlatformType(coreMemberInfo.getMemberId(), platformType);
        return bindInfo.getUid();
    }

    /**
     * 通过会员id和平台id获取登录的url
     * 
     * @param coreMemberInfo
     * @return
     */
    public String selectOnceLogonUrl(CoreMemberInfo coreMemberInfo) {
        Long uid = selectUidByMemberIdAndPlatformType(coreMemberInfo);
        String p = prepareParams4TaobaoLogonRedirectUrl(uid);
        return OpenApiConstants.ONCE_LOGON + p;
    }

    public CoreMemberInfo findBindInfoByMerberId(CoreMemberInfo coreMemberInfo) {
        Short platformType = 1;
        CoreBindInfo bindInfo = findBindInfoByMemberIdAndPlatformType(coreMemberInfo.getMemberId(), platformType);
        if (bindInfo != null) {
            coreMemberInfo.setOtherEmail(bindInfo.getEmail());
            coreMemberInfo.setOtherName(bindInfo.getUserName());
            coreMemberInfo.setPlatformeType("1");
        }
        else {
            coreMemberInfo.setPlatformeType(null);
        }
        return coreMemberInfo;

    }
}
