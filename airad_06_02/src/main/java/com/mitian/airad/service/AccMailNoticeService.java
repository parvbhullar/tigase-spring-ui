/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.SysConfigConstants;
import com.mitian.airad.common.constant.AccMailNoticeConstant;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.dao.AccMailNoticeDAO;
import com.mitian.airad.model.AccMailNotice;
import com.mitian.airad.model.SysConfig;
import com.mitian.airad.refreshable.SysConfigRefreshable;

/**
 * AccMailNoticeService.java
 * 
 * @author Administrator
 */
@Service
public class AccMailNoticeService {
    @Autowired
    private AccMailNoticeDAO accMailNoticeDAO;
    @Autowired
    private SysConfigRefreshable sysConfigRefreshable;
    private static final Log LOGGER = LogFactory.getLog(AccMailNoticeService.class);

    /**
     * 设置金额限度
     */
    public void txSetAccMailNotice(AccMailNotice amn) {
        if (StringUtils.isNotBlank(amn.getAccountBlance().toString())
                && StringUtils.isNotBlank(amn.getMemberId().toString()) && StringUtils.isNotBlank(amn.getMailAddr())) {
            checkNoticeBlanceAndResetIfNeed(amn);
            accMailNoticeDAO.insert(amn);
        }
    }

    /**
     * 创建记录
     * 
     * @param memberId
     * @param email
     * @param date
     */
    public AccMailNotice txCreateAccMailNotice(Long memberId, String email, Date date) {
        AccMailNotice am = new AccMailNotice();
        am.setMemberId(memberId);
        am.setUpdateId(memberId);
        am.setMailAddr(email);
        am.setAccountBlance(new BigDecimal(AccMailNoticeConstant.DEFAULT_REMIND_AMOUNT));
        am.setUpdateTime(date);
        am.setLastSendTime(date);
        am.setRemindFlag(AccMailNoticeConstant.REMIND_FLAG_ON);
        am.setNoticeType(AccMailNoticeConstant.NOTICE_TYPE_ADVERTISER);
        am.setTriggerType(AccMailNoticeConstant.TRIGGER_TYPE_JOB);
        checkNoticeBlanceAndResetIfNeed(am);
        accMailNoticeDAO.insert(am);
        return am;
    }

    /**
     * 启用/停用 通知设置
     */
    public int txUpdateAccMailNotice(AccMailNotice amn) {
        int notice = 0;
        if (StringUtils.isNotBlank(amn.getMailNoticeId().toString())
                && StringUtils.isNotBlank(amn.getAccountBlance().toString())
                && StringUtils.isNotBlank(amn.getMemberId().toString()) && StringUtils.isNotBlank(amn.getMailAddr())) {
            checkNoticeBlanceAndResetIfNeed(amn);
            notice = accMailNoticeDAO.updateByPrimaryKey(amn);
        }
        return notice;
    }

    /**
     * 启用/停用 通知设置
     */
    public int txUpdateAccMailNoticeRemndFlag(AccMailNotice amn) {
        int notice = 0;
        notice = accMailNoticeDAO.updateRemindFlag(amn);
        return notice;
    }

    /**
     * 金额检查
     * 
     * @param amn
     */
    private void checkNoticeBlanceAndResetIfNeed(AccMailNotice amn) {
        String value = String.valueOf(amn.getAccountBlance().intValue());
        sysConfigRefreshable.setNeedRefresh();
        SysConfig config =
                sysConfigRefreshable.getSysConfigByTypeKeyAndValue(SysConfigConstants.MAIL_NOTICE_TYPE_VALUE, value);
        if (config != null) {
            amn.setNoticeTypeValue(config.getSysKey());
        }
        else {
            String newValue =
                    sysConfigRefreshable.getValueByTypeKeyAndValueKey(SysConfigConstants.MAIL_NOTICE_TYPE_VALUE, "1");
            BigDecimal bigDecimal = new BigDecimal(StringUtils.isBlank(newValue) ? "30" : newValue);
            amn.setAccountBlance(bigDecimal);
            amn.setNoticeTypeValue("1");
        }
    }

    /**
     * 根据
     */
    public AccMailNotice findAccMailNoticeByMemberId(Long memberId) {
        AccMailNotice notice = null;
        notice = accMailNoticeDAO.selectByMemberId(memberId);
        return notice;
    }

    public AccMailNotice insertOrFindAccMailNotice(Long memberId, String email) {
        AccMailNotice notice = accMailNoticeDAO.selectByMemberId(memberId);
        if (null == notice) {
            notice = txCreateAccMailNotice(memberId, email, new Date());
        }
        return notice;
    }

    /**
     * @return the accMailNoticeDAO
     */
    public AccMailNoticeDAO getAccMailNoticeDAO() {
        return accMailNoticeDAO;
    }

    /**
     * @param accMailNoticeDAO the accMailNoticeDAO to set
     */
    public void setAccMailNoticeDAO(AccMailNoticeDAO accMailNoticeDAO) {
        this.accMailNoticeDAO = accMailNoticeDAO;
    }

    /**
     * @return the sysConfigRefreshable
     */
    public SysConfigRefreshable getSysConfigRefreshable() {
        return sysConfigRefreshable;
    }

    /**
     * @param sysConfigRefreshable the sysConfigRefreshable to set
     */
    public void setSysConfigRefreshable(SysConfigRefreshable sysConfigRefreshable) {
        this.sysConfigRefreshable = sysConfigRefreshable;
    }
}
