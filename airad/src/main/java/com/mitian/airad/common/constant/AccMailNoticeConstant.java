/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.constant;

/**
 * AccMailNoticeConstant.java
 * 
 * @author baojun
 */
public abstract class AccMailNoticeConstant {
    /**
     * 通知类型
     */
    public static final Integer NOTICE_TYPE_DEV = 0;// 开发者收入达限
    public static final Integer NOTICE_TYPE_ADVERTISER = 1;// 广告商资金达限
    /**
     * 邮件通知启用标志
     */
    public static final String REMIND_FLAG_ON = "1";
    public static final String REMIND_FLAG_OFF = "0";
    /**
     * 邮件通知触发类型
     */
    public static final String TRIGGER_TYPE_MANUAL = "1";
    public static final String TRIGGER_TYPE_JOB = "2";
    public static final String TRIGGER_TYPE_APP = "3";
    //
    public static final String DEFAULT_REMIND_AMOUNT = "50.00";
}
