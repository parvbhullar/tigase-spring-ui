package com.mitian.airad.web.form;

import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.model.CoreUserHis;
import com.mitian.airad.utils.StringUtil;

/**
 * MemberForm.java
 * 
 * @author baojun
 */
public class MemberForm extends AbstractForm {
    private String email;
    private String password;
    private String userId;
    private String loginIp;
    private String passwords;
    private String activeCode;
    private String date;
    private String promotionId;
    private String urlName;

    // 淘宝开放平台认证网址(taobao open platform)
    private String taobaoAuthUrl;
    private String uid;
    private String platformType;
    private String otherEmail;
    private String otherName;
    /**
     * 用户操作后返回结果
     */
    private String memberMessage;

    /**
     * 密码找回的密文
     */
    private String c;

    // 邀请码
    private String inviteCode;

    // 登录密码重置
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private CoreUserHis userHis = new CoreUserHis();
    /**
     * ---sunncy 20110908 增加登录类型0为正常登录 1为淘宝登陆
     */
    private String loginType;

    @Override
    public void form2domain() {
        CoreMemberInfo coreMemberInfo = new CoreMemberInfo();
        coreMemberInfo.setEmail(email);
        coreMemberInfo.setIpAddress(loginIp);
        coreMemberInfo.setPassword(password);
        coreMemberInfo.setInviteCode(inviteCode);
        coreMemberInfo.setUrlName(urlName);
        coreMemberInfo.setUid(uid);
        coreMemberInfo.setPlatformeType(loginType);
        if (promotionId != null && promotionId.length() > 0) {
            coreMemberInfo.setPromotionId(StringUtil.stringToInteger(promotionId));
        }
        setCoreMemberInfo(coreMemberInfo);
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    /**
     * @return the memberMessage
     */
    public String getMemberMessage() {
        return memberMessage;
    }

    /**
     * @param memberMessage the memberMessage to set
     */
    public void setMemberMessage(String memberMessage) {
        this.memberMessage = memberMessage;
    }

    /**
     * @return the inviteCode
     */
    public String getInviteCode() {
        return inviteCode;
    }

    /**
     * @param inviteCode the inviteCode to set
     */
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return the activeCode
     */
    public String getActiveCode() {
        return activeCode;
    }

    /**
     * @param activeCode the activeCode to set
     */
    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    /**
     * @return the passwords
     */
    public String getPasswords() {
        return passwords;
    }

    /**
     * @param passwords the passwords to set
     */
    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    /**
     * @return the loginIp
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * @param loginIp the loginIp to set
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 判断是否是有代理商权限
     * 
     * @param devType
     * @return
     */
    public boolean isAgent() {
        return getCoreMemberInfo().getRole().isAgents();
    }

    /**
     * 判断是否是有广告商权限
     * 
     * @param devType
     * @return
     */
    public boolean isAdvertisers() {
        return getCoreMemberInfo().getRole().isAdvertisers();
    }

    /**
     * 判断是否是有开发者权限
     * 
     * @param devType
     * @return
     */
    public boolean isDeveloper(String devType) {
        return getCoreMemberInfo().getRole().isDev();
    }

    /**
     * @return the userHis
     */
    public CoreUserHis getUserHis() {
        return userHis;
    }

    /**
     * @param userHis the userHis to set
     */
    public void setUserHis(CoreUserHis userHis) {
        this.userHis = userHis;
    }

    /**
     * @param promotionId the promotionId to set
     */
    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    /**
     * @return the promotionId
     */
    public String getPromotionId() {
        return promotionId;
    }

    /**
     * @param urlName the urlName to set
     */
    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    /**
     * @return the topAuthUrl
     */
    public String getTaobaoAuthUrl() {
        return taobaoAuthUrl;
    }

    /**
     * @param topAuthUrl the topAuthUrl to set
     */
    public void setTaobaoAuthUrl(String topAuthUrl) {
        taobaoAuthUrl = topAuthUrl;
    }

    /**
     * @return the urlName
     */
    public String getUrlName() {
        return urlName;
    }

    /**
     * @param loginType the loginType to set
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    /**
     * @return the loginType
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param platformType the platformType to set
     */
    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    /**
     * @return the platformType
     */
    public String getPlatformType() {
        return platformType;
    }

    /**
     * @param otherEmail the otherEmail to set
     */
    public void setOtherEmail(String otherEmail) {
        this.otherEmail = otherEmail;
    }

    /**
     * @return the otherEmail
     */
    public String getOtherEmail() {
        return otherEmail;
    }

    /**
     * @param otherName the otherName to set
     */
    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    /**
     * @return the otherName
     */
    public String getOtherName() {
        return otherName;
    }

}
