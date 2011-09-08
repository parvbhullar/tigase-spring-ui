package com.mitian.airad.web.form;

import org.apache.log4j.Logger;

import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.model.CoreUserHis;
import com.mitian.airad.utils.StringUtil;

public class MemberForm extends AbstractForm {
    private String email;
    private String password;
    private String userId;
    private String loginIp;
    private String passwords;
    private String activeCode;
    private String date;
    private String promotionId;
    private String UrlName;
    private Logger log = Logger.getLogger(MemberForm.class);
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
        UrlName = urlName;
    }

    /**
     * @return the urlName
     */
    public String getUrlName() {
        return UrlName;
    }

    @Override
    public void form2domain() {
        coreMemberInfo = new CoreMemberInfo();
        coreMemberInfo.setEmail(email);
        coreMemberInfo.setIpAddress(loginIp);
        coreMemberInfo.setPassword(password);
        coreMemberInfo.setInviteCode(inviteCode);
        coreMemberInfo.setUrlName(UrlName);
        if (promotionId != null && promotionId.length() > 0) {
            coreMemberInfo.setPromotionId(StringUtil.StringToInteger(promotionId));
        }
    }
}
