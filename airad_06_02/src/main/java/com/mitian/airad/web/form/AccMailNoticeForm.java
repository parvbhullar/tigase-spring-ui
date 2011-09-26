/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.mitian.airad.model.AccMailNotice;

/**
 * AccMailNoticeFrom.java
 * 
 * @author Administrator
 */
public class AccMailNoticeForm extends AbstractForm {
    /**
     * 通知id
     */
    private String mailNoticeId;

    /**
     *通知余额
     */
    private String accountBlance;

    /**
     * 会员id
     */
    private String memberId;

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
    // 通知列表
    private List<AccMailNotice> noticeList = new ArrayList<AccMailNotice>();
    // 通知对象
    private AccMailNotice accMailNotice = new AccMailNotice();

    /**
     * @return the mailNoticeId
     */
    public String getMailNoticeId() {
        return mailNoticeId;
    }

    /**
     * @param mailNoticeId the mailNoticeId to set
     */
    public void setMailNoticeId(String mailNoticeId) {
        this.mailNoticeId = mailNoticeId;
    }

    /**
     * @return the accountBlance
     */
    public String getAccountBlance() {
        return accountBlance;
    }

    public String getAccountBlanceKey() {
        if (StringUtils.isBlank(accountBlance)) {
            return StringUtils.EMPTY;
        }
        else {
            return accountBlance.split("\\.")[0];
        }
    }

    /**
     * @param accountBlance the accountBlance to set
     */
    public void setAccountBlance(String accountBlance) {
        this.accountBlance = accountBlance;
    }

    /**
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
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
     * @return the noticeList
     */
    public List<AccMailNotice> getNoticeList() {
        return noticeList;
    }

    /**
     * @param noticeList the noticeList to set
     */
    public void setNoticeList(List<AccMailNotice> noticeList) {
        this.noticeList = noticeList;
    }

    /**
     * @return the accMailNotice
     */
    public AccMailNotice getAccMailNotice() {
        return accMailNotice;
    }

    /**
     * @param accMailNotice the accMailNotice to set
     */
    public void setAccMailNotice(AccMailNotice accMailNotice) {
        this.accMailNotice = accMailNotice;
    }

    @Override
    public void form2domain() {
        if (StringUtils.isNotBlank(mailNoticeId)) {
            accMailNotice.setMailNoticeId(Integer.valueOf(mailNoticeId));
        }
        if (StringUtils.isNotBlank(accountBlance) && NumberUtils.isNumber(accountBlance)) {
            accMailNotice.setAccountBlance(new BigDecimal(accountBlance));
        }
        if (StringUtils.isNotBlank(memberId)) {
            accMailNotice.setMemberId(Long.parseLong(memberId));
        }
    }

}
