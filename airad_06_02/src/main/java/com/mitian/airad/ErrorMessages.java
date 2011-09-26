/*
 * Copyright 2011 mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad;

/**
 * ErrorMessges.java
 * 
 * @author leifenghai
 */
public abstract class ErrorMessages {
    /**
     * 验证码失效
     */
    public static final String VERIFYCODE_ABATE = "验证码失效";
    /**
     * 验证码错误
     */
    public static final String VERIFYCODE_ERR = "验证码不正确";

    /**
     * 确认密码错误
     */
    public static final String AFFIRM_PASSWORD_ERR = "确认密码错误";

    /**
     * 用户已存在
     */
    public static final String USER_EXIST = "用户已存在";

    /**
     * 用户不存在
     */
    public static final String USER_LACK = "用户不存在";

    /**
     * 用户未激活
     */
    public static final String USER_NOT_ACTIVATION = "用户未激活";
    /**
     * /** 用户被冻结
     */
    public static final String USER_FROZEN = "用户被冻结";

    /**
     * 邮件发送失败
     */
    public static final String SAND_EMAIL_ERR = "邮件发送失败";

    /**
     * 链接已失效
     */
    public static final String ACTIVATION_CODE_ABATE = "链接已失效了";
    /**
     * 激活码无效
     */
    public static final String ACTIVATION_CODE_NULLITY = "激活码无效";
    /**
     * 激活码链接无效
     */
    public static final String ACTIVATION_CODE_LINKS_NULLITY = "激活码链接无效";
    /**
     * 
     */
    public static final String SENDEMAILCOUNT_OUT = "当天发送安全密码找回邮件已满三次，不能再发送";
    /**
     * 邀请链接已无效
     */
    public static final String INVITE_CODE_NULLITY = "邀请链接已无效";
    /**
     * 邀请链接已使用
     */
    public static final String INVITE_STATUS_IS_USAGE_TEXT = "邀请链接已使用";
    /**
     * 邀请链接已发送
     */
    public static final String INVITE_STATUS_GESENDET_TEXT = "邀请链接已发送";
    /**
     *重复密码和新密码不相同
     */
    public static final String ACTIVATION_CODE_CONFIRM_ERROR = "重复密码和新密码不同";
    /**
     *密码长度（6-20）
     */
    public static final String ACTIVATION_CODE_LENGTH_ERROR = "密码长度必须6-20位";
    /**
     *应用名称小于100位
     */
    public static final String APP_LENGTH_ERROR = "应用名称小于50位";
    /**
     * 旧密码输入错误
     */
    public static final String ACTIVATION_CODE_OLDPASSWORD_ERROR = "旧密码输入错误";
    public static final String ACTIVATION_CODE_OLDPASS_ERROR = "安全密码输入错误";
    /**
     * 每天至多发送3封密码重置邮件！
     */
    public static final String SEND_COUNT_ERR = "每天至多发送3封密码重置邮件";

    /**
     * 图片大小超过限制
     */
    public static final String UPLOAD_IMG_SIZE_ERR = "图片大小超过限制";

    /**
     * 图片类型不支持
     */
    public static final String UPLOAD_IMG_TYPE_ERR = "图片类型不支,目前只支持jpg，jpeg，png，gif.";

    /**
     * 广告组复制异常！
     */
    public static final String NO_AD_GROUP_ID = "广告组复制异常";

    public static final String SECURITY_ANSWER_ERROR = "密码提示问题回答错误";
    /**
     * 请填写正确的金额类型！
     */
    public static final String MONEY_ERROR = "请填写正确的金额类型";
    /**
     * 金额为零
     */
    public static final String MONEY_IS_ZERO = "金额不能为零";
    /**
     * 用户余额不足！
     */
    public static final String BALANCE_ERROR = "余额不足";
    /**
     * 请填写金额！
     */
    public static final String REQUIRED_ERROR = "请填写资金转换金额";
    public static final String REQWITHDRAW_ERROR = "开发者每天提现的次数仅为三次";
    /**
     * 提现金额需大于100！
     */
    public static final String MONEYSMALL_ERROR = "提现金额最低100.0RMB";
    public static final String WIDTH_ERROR = "请完善提现详细信息";
    /**
     * 请选择需要删除的报表！
     */
    public static final String DELETE_ERROR = "请选择需要删除的报表";
    /**
     * 提现错误
     */
    public static final String WITHDRAW_ERROR = "请完善个人信息";
    /**
     * 报表错误
     */
    public static final String REPORT_REPORTNAME_ERROR = "请选择需要统计的应用名称";
    public static final String REPORT_REPORTNAME_CAMPAIGN_ERROR = "请选择需要统计的活动名称";
    public static final String REPORT_REPORTNAME_AVG_ERROR = "请选择需要统计的广告商名称";
    public static final String REPORT_REPORTTYPE_ERROR = "请选择报表时间范围";
    public static final String REPORT_REPORTTIME_ERROR = "开始时间不能大于等于结束时间";
    public static final String REPORT_REPORT_ERROR = "报表名称必须小于40位并且不能为空";
    /**
     *活动名称（50）
     */
    public static final String CAMPAIGN_ERROR = "活动名称长度必须小于50位，请修改后再输入";
    /**
     *广告组名称（50）
     */
    public static final String ADGROUP_ERROR = "广告组名称长度必须小于50位，请修改后再输入";
    /**
     *预算大于20
     */
    public static final String BUDGETDAY_ERROR = "预算最低￥50";
    /**
     *地理位置不能为空
     */
    public static final String LOCAL_ERROR = "地理位置不能为空";

    /**
     *开始时间小于当前日期！
     */
    public static final String STARTTIME_ERROR = "开始时间不能小于当前日期";
    /**
     *结束时间小于当前日期！
     */
    public static final String ENDTIME_ERROR = "结束时间不能小于当前日期";
    /**
     *请选择结束时间！
     */
    public static final String ENDTIME_ERROR_REQUIRED = "结束时间不能为空";

    /**
     *广告只有在身份通过审核之后才可以发布
     */
    public static final String SUSPEND_ERROR_REQUIRED = "广告只有在身份通过审核之后才可以发布";
    public static final String ADID_EMPTY = "广告ID为空";
    /**
     * 请填写金额！
     */
    public static final String MONEY_ERROR_REQUIRED = "请填写充值金额";
    /**
     * 请填写备注！
     */
    public static final String REMARK_ERROR_REQUIRED = "请填写备注";
    /**
     * 请选择账号！
     */
    public static final String ACCOUNT_ERROR_REQUIRED = "请选择账号";
    /**
     * 请填写正确参数
     */
    public static final String CONFIG_ERROR_REQUIRED = "请填写参数(1.2-2)";
    /**
     * 身份认证
     */
    public static final String ADVERTISER_UNSIGNED = "该广告的广告主身份未通过验证，请进行验证后在进行提交。";
    /**
     * 广告id
     */
    public static final String AD_ID_ERROR = "广告id不能为空。";
}
