/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.form;

import com.mitian.airad.model.CoreAccountInfo;

/**
 * AccountInfoForm.java
 * 
 * @author chenji
 */
public class AccountForm extends AbstractForm {
    // 账户密码重置
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private String securityProblem;
    private String securityAnswer;
    private String mail;
    private String operateType;
    private String operateMessage;
    private String resettingId;
    private String memberId;

    /**
     * 密码找回的密文
     */
    private String c;
    private String date;
    /**
     * 用户操作后返回结果
     */
    private String memberMessage;
    /** 转换金额 */
    private String money;
    private CoreAccountInfo coreAccountInfo = new CoreAccountInfo();

    public String getMemberMessage() {
        return memberMessage;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberMessage(String memberMessage) {
        this.memberMessage = memberMessage;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperateMessage() {
        return operateMessage;
    }

    public void setOperateMessage(String operateMessage) {
        this.operateMessage = operateMessage;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public CoreAccountInfo getCoreAccountInfo() {
        return coreAccountInfo;
    }

    public void setCoreAccountInfo(CoreAccountInfo coreAccountInfo) {
        this.coreAccountInfo = coreAccountInfo;
    }

    public String getSecurityProblem() {
        return securityProblem;
    }

    public void setSecurityProblem(String securityProblem) {
        this.securityProblem = securityProblem;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    /**
     * @return the money
     */
    public String getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * @return the resettingId
     */
    public String getResettingId() {
        return resettingId;
    }

    /**
     * @param resettingId the resettingId to set
     */
    public void setResettingId(String resettingId) {
        this.resettingId = resettingId;
    }

    @Override
    public void form2domain() {
    }

}
