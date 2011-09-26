package com.mitian.airad.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AccMailNotice.java
 * 
 * @author baojun
 */
public class AccMailNotice extends BaseModel {
    /**
     * 通知id
     */
    private Integer mailNoticeId;

    /**
     *通知余额
     */
    private BigDecimal accountBlance;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 最后设置时间
     */
    private Date lastSendTime;

    /**
     * 是否启用标志
     */
    private String remindFlag;

    /**
     * 通知邮箱
     */
    private String mailAddr;

    /**
     * 用户
     */
    private String email;

    /**
     * 代理商充值余额
     */
    private BigDecimal agentBlance;
    /**
     * 优惠券余额
     */
    private BigDecimal couponBlance;
    /**
     * 账户余额,关联ACCOUNT_INFO_VIEW
     */
    private BigDecimal acceptBlance;

    private String noticeTypeValue;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人id
     */
    private Long updateId;

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the updateId
     */
    public Long getUpdateId() {
        return updateId;
    }

    /**
     * @param updateId the updateId to set
     */
    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    /**
     * @return the noticeTypeValue
     */
    public String getNoticeTypeValue() {
        return noticeTypeValue;
    }

    /**
     * @param noticeTypeValue the noticeTypeValue to set
     */
    public void setNoticeTypeValue(String noticeTypeValue) {
        this.noticeTypeValue = noticeTypeValue;
    }

    /**
     * @return the triggerType
     */
    public String getTriggerType() {
        return triggerType;
    }

    /**
     * @param triggerType the triggerType to set
     */
    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    /**
     * @return the noticeType
     */
    public Integer getNoticeType() {
        return noticeType;
    }

    /**
     * @param noticeType the noticeType to set
     */
    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    private String triggerType;
    private Integer noticeType;

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
     * @return the agentBlance
     */
    public BigDecimal getAgentBlance() {
        return agentBlance;
    }

    /**
     * @param agentBlance the agentBlance to set
     */
    public void setAgentBlance(BigDecimal agentBlance) {
        this.agentBlance = agentBlance;
    }

    /**
     * @return the couponBlance
     */
    public BigDecimal getCouponBlance() {
        return couponBlance;
    }

    /**
     * @param couponBlance the couponBlance to set
     */
    public void setCouponBlance(BigDecimal couponBlance) {
        this.couponBlance = couponBlance;
    }

    /**
     * @return the acceptBlance
     */
    public BigDecimal getAcceptBlance() {
        return acceptBlance;
    }

    /**
     * @param acceptBlance the acceptBlance to set
     */
    public void setAcceptBlance(BigDecimal acceptBlance) {
        this.acceptBlance = acceptBlance;
    }

    /**
     * @return the mailNoticeId
     */
    public Integer getMailNoticeId() {
        return mailNoticeId;
    }

    /**
     * @param mailNoticeId the mailNoticeId to set
     */
    public void setMailNoticeId(Integer mailNoticeId) {
        this.mailNoticeId = mailNoticeId;
    }

    /**
     * @return the accountBlance
     */
    public BigDecimal getAccountBlance() {
        return accountBlance;
    }

    /**
     * @param accountBlance the accountBlance to set
     */
    public void setAccountBlance(BigDecimal accountBlance) {
        this.accountBlance = accountBlance;
    }

    /**
     * @return the memberId
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the lastSendTime
     */
    public Date getLastSendTime() {
        return lastSendTime;
    }

    /**
     * @param lastSendTime the lastSendTime to set
     */
    public void setLastSendTime(Date lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    /**
     * @return the remindFlag
     */
    public String getRemindFlag() {
        return remindFlag;
    }

    /**
     * @param remindFlag the remindFlag to set
     */
    public void setRemindFlag(String remindFlag) {
        this.remindFlag = remindFlag;
    }

    /**
     * @return the mailAddr
     */
    public String getMailAddr() {
        return mailAddr;
    }

    /**
     * @param mailAddr the mailAddr to set
     */
    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    /**
     * 判断是否小于给定限额，用于决定是否需发送提醒邮件
     * 
     * @param limitValue
     * @return
     */
    public boolean isNeedSendNotice4Advertiser(BigDecimal limitValue) {
        return acceptBlance.compareTo(limitValue) < 0;

    }
}
