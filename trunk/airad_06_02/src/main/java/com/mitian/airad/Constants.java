/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad;

import java.util.HashMap;
import java.util.Map;

/**
 * Constants.java
 * 
 * @author Administrator
 */
public abstract class Constants {
    public static final String AIRAD_HOMEPAGE_URL = "http://www.airad.com";

    public static final String DEFAULT_CURRENT_PAGE = "1";
    public static final String DEFAULT_PAGE_SIZE = "30";
    public static final String DEFAULT_PAGE_SIZE_HIS = "10";
    public static final String DEFAULT_PAGE_SIZE_PLATFORM = "8";
    public static final String DEFAULT_COMMAND = "command";

    // 广告展示相关modelName ----------------/
    public static final String HERMES_DEFAULT_COMMAND = "hermes";
    public static final String HERMES_PICUP_COMMAND = "picUpCommand";
    public static final String HERMES_MAP_COMMAND = "mapCommand";
    public static final String HERMES_TAOBAO_COMMAND = "taobaoCommand";
    public static final String HERMES_WORD_COMMAND = "wordCommand";
    public static final String HERMES_PIC_COMMAND = "picCommand";
    public static final String HERMES_PICRIGTH_COMMAND = "picRigthCommand";
    public static final String HERMES_PICLEFT_COMMAND = "picLeftCommand";
    // 广告展示相关modelName ----------------/

    /**
     * 广告展示背景图片
     */
    public static final String RICH_AD_BACKGROUND_IMG = "richAdBackgroundImg";
    /**
     * 广告内容类型的名字
     */
    public static final String RICH_AD_SHOWTYPE = "showType";
    /**
     * 图文类型的名字
     */
    public static final String RICH_AD_FORMATTYPE = "formatType";

    /**
     * 邀请链接已使用
     */
    public static final String INVITE_STATUS_IS_USAGE = "1";

    /**
     * 邀请链接未使用
     */
    public static final String INVITE_STATUS_NO_USAGE = "0";

    /**
     * 邀请链接已发送
     */
    public static final String INVITE_STATUS_GESENDET = "2";

    /**
     * 注册邀请信息邀请对象为广告商类型
     */
    public static final String INVITE_TYPE_ADVERTISERS = "1";
    /**
     * 注册邀请信息邀请对象为开发者类型
     */
    public static final String INVITE_TYPE_DEVELOPER = "0";

    /**
     * app状态为停用 0
     */
    public static final String APP_TYPE_STOP = "0";
    /**
     * app状态为冻结 1
     */
    public static final String APP_TYPE_CONGEAL = "1";

    /**
     * 最大正相差时间
     */
    public static final int MAX_POSITIVE_DISCREPANCY = 1000;
    /**
     * 最大负相差时间
     */
    public static final int MAX_MINUS_DISCREPANCY = -1000;

    /**
     * 邮件发送类型为找回密码
     */
    public static final String PASSWORD_RESETTING_TYPE = "0";
    public static final String PASSWORD_RESETTING_ABATE = "1";
    // 财务密码找回标示位
    public static final String ACCOUNT_PASS_FIND = "1";

    /**
     * 通知类型为广告商
     */
    public static final Integer NOTICE_TYPE_ADVERTISER = 1;

    /**
     * 会员激活
     */
    public static final String MEMBER_ACTIVE_STATS = "1";

    /**
     * 修改成功
     */
    public static final String SETTINGS_SUC = "修改成功";
    public static final String WITHDRAW_SUC = "提款设置成功";
    public static final String ACCOUNTPASSWORD_SUCCESS = "账户密码修改成功";
    public static final String SENDEMAIL_SUCCESS = "邮件发送成功";
    //
    public static final String NEED_AUTH = "1";
    // -------------------start oss------------//

    /**
     * 客服端信息上传失败
     */
    public static final String OSS_UPLOD_STATE_NO = "No";
    /**
     * 客服端信息上传成功
     */
    public static final String OSS_UPLOD_STATE_YES = "Yes";

    /**
     * sdk版本为空
     */
    public static final String SDK_VERSION_ZERO = "0";
    /**
     * sdk版本是无限制的
     */
    public static final String SDK_VERSION_INFINITY = "-1";

    /**
     * app正常
     */
    public static final String OSS_APP_NORMAL = "01";

    /**
     * app错误或异常
     */
    public static final String OSS_APP_ERROR = "02";

    /**
     * app在国外
     */
    public static final String OSS_APP_ABROAD = "03";

    /**
     * 返回默认广告 id的数量
     */
    public static final Integer OSS_DEFAULT_APP_ID_LIST = 5;

    /**
     * 请求错误或请求的数据不存在
     */
    public static final String OSS_REQUEST_ERR = "000";

    /**
     * 否为真机环境
     */
    public static final String RUN_STATE_TEST = "sim";

    /**
     * 真机环境
     */
    public static final String RUN_STATE_NOT_TEST = "dev";

    // ----------------end oss-----leifenghai--------//

    /**
     * airad属性文件路径
     */
    public static final String AIRAD_PROERTIES_PATH = "/WEB-INF/config/common.properties";
    // ip 地址段配置文件
    public static final String IP_PROERTIES_PATH = "/WEB-INF/config/cn_ip_seg.properties";
    // 银联支付：外网ip映射地址配置文件
    // public static final String IP_CHINAUNIONPAYWEB_PATH = "/WEB-INF/classes/environment.properties";
    /* 资金转换成功 提示* */
    public static final String WITHDRAW_SUCCESS = "资金转换成功，可以到广告主账户查询转换金额";
    /* 提款申请成功提示 */
    public static final String TIKUAN_SUCCESS = "提款申请成功，请等待处理";
    /**
     * 默认指向地址
     */
    public static final String DEFAULT_AIRAD_PROERTIES_PATH = "http://www.airad.com";

    public static final String TEST_APP_CODE = "123456789";
    /**
     * 会员状态为冻结
     */
    public static final String MEMBER_STATUS_FREEZE = "2";

    /**
     * 会员状态为正常
     */
    public static final String MEMBER_STATUS_NORMAL = "1";

    /*
     * 定义分页的默认数量
     */
    public static final int PAGE_SIZE = 20;

    public static final Map<String, String> IMAGE_TYPE_MAP = new HashMap<String, String>() {
        // 上传图片时，ie会把 jpg、jpeg翻译成image/pjpeg，png翻译成image/x-png
        private static final long serialVersionUID = 1L;
        {
            put("image/jpeg", "jpeg");
            put("image/jpg", "jpg");
            put("image/png", "png");
            put("image/gif", "gif");
            put("image/pjpeg", "jpg");
            put("image/x-png", "png");
        }
    };

    /**
     * 通用状态描述
     * 
     * @author Administrator
     */
    public enum StatusDesc {
        NORMAL("1", "正常状态"), DELETE("0", "删除状态"), FROZEN("2", "冻结状态");
        private final String statusValue;
        private final String statusName;

        public String getStatusValue() {
            return statusValue;
        }

        public String getStatusName() {
            return statusName;
        }

        private StatusDesc(String statusValue, String statusName) {
            this.statusValue = statusValue;
            this.statusName = statusName;
        }
    }
    /**
     * 常用地区
     * 
     * @author Administrator
     */
    public enum AreaStatus {
        LONGPRD("0", "长三角"), PRD("1", "珠三角"), AROUND("2", "环渤海");
        private final String statusValue;
        private final String statusName;

        public String getStatusValue() {
            return statusValue;
        }

        public String getStatusName() {
            return statusName;
        }

        /**
         * @param statusValue
         * @param statusName
         */
        private AreaStatus(String statusValue, String statusName) {
            this.statusValue = statusValue;
            this.statusName = statusName;
        }

    }
    /**
     * 充值记录中的充值状态
     * 
     * @author chenji
     */
    public enum RechargeHisRechargeStatus {
        SUCCESS(1, "成功"), FAIL(2, "失败"), NOSTATUS(0, "无状态");
        private final int statusNumber;
        private final String statusName;

        public int getStatusNumber() {
            return statusNumber;
        }

        public String getStatusName() {
            return statusName;
        }

        private RechargeHisRechargeStatus(int statusNumber, String statusName) {
            this.statusNumber = statusNumber;
            this.statusName = statusName;
        }
    }
    /**
     * 充值历史中的充值类型
     * 
     * @author chenji
     */
    public enum RechargeHisRechargeType {
        UNIONPAY(0, "银联"), ALIPAY(1, "支付宝"), AGENT(2, "代理商"), CARD(3, "充值卡");
        private RechargeHisRechargeType(int typeNumber, String typeName) {
            this.typeName = typeName;
            this.typeNumber = typeNumber;
        }

        private final int typeNumber;
        private final String typeName;

        public int getTypeNumber() {
            return typeNumber;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    public enum RechargeHisInvoice {
        YES(1, "需要"), NO(0, "不需要");
        private RechargeHisInvoice(int invoiceNumber, String invoiceName) {
            this.invoiceName = invoiceName;
            this.invoiceNumber = invoiceNumber;
        }
        private final int invoiceNumber;
        private final String invoiceName;

        public int getInvoiceNumber() {
            return invoiceNumber;
        }

        public String getInvoiceName() {
            return invoiceName;
        }
    }

    /**
     * 账户类型
     * 
     * @author chenji
     */
    public enum AccountType {
        PERSONAL1(0, "个人"), ENTERPRISE(1, "企业");
        private final int typeNumber;
        private final String typeName;

        public int getTypeNumber() {
            return typeNumber;
        }

        public String getTypeName() {
            return typeName;
        }

        private AccountType(int typeNumber, String typeName) {
            this.typeName = typeName;
            this.typeNumber = typeNumber;
        }
    }
    /**
     * 账户标记
     * 
     * @author chenji
     */
    public enum AccountFlag {
        COMMON(0, "普通"), VIP(1, "VIP");
        private final int flagNumber;
        private final String flagName;

        public int getFlagNumber() {
            return flagNumber;
        }

        public String getFlagName() {
            return flagName;
        }

        private AccountFlag(int flagNumber, String flagName) {
            this.flagName = flagName;
            this.flagNumber = flagNumber;
        }
    }

    /**
     * 账户状态
     * 
     * @author chenji
     */
    public enum AccountStatus {
        UNTUTORED(0, "未开通"), DREDGE(1, "已开通"), FREEZE(2, "已冻结");
        private final int statusNumber;
        private final String statusName;

        public int getStatusNumber() {
            return statusNumber;
        }

        public String getStatusName() {
            return statusName;
        }

        private AccountStatus(int statusNumber, String statusName) {
            this.statusName = statusName;
            this.statusNumber = statusNumber;
        }
    }

    /**
     * 提现申请受理状态
     * 
     * @author chenji
     */
    public enum WithdrawStatus {
        ACCEPTING(0, "受理中"), PAID(1, "已支付"), FAIL(2, "支付失败");
        private final int statusNumber;
        private final String statusName;

        public int getStatusNumber() {
            return statusNumber;
        }

        public String getStatusName() {
            return statusName;
        }

        private WithdrawStatus(int statusNumber, String statusName) {
            this.statusNumber = statusNumber;
            this.statusName = statusName;
        }
    }
    /**
     * 操作结果
     * 
     * @author chenji
     */
    public enum OperateStatus {
        ACCOUNT_SEND_EMAIL_SUCCESS("成功", "发送邮件成功"), ACCOUNT_SEND_EMAIL_FAIL("失败", "发送邮件失败"),
        ACCOUNT_SEND_EMAIL_URL_ABATE("链接失效", "链接失效");
        private final String operateType;
        private final String operateMessage;

        public String getOperateType() {
            return operateType;
        }

        public String getOperateMessage() {
            return operateMessage;
        }

        private OperateStatus(String operateType, String operateMessage) {
            this.operateType = operateType;
            this.operateMessage = operateMessage;
        }
    }
    /**
     * 会员报表类型 0-活动报告 1-广告组报告 2-广告报告 3-应用报告 4-广告商报告
     */
    public static final String REPORT_TYPE_AD_CAMPAIGN = "0";
    public static final String REPORT_TYPE_AD_GROUOP = "1";
    public static final String REPORT_TYPE_AD = "2";
    public static final String REPORT_TYPE_APP = "3";
    public static final String REPORT_TYPE_AGENTS = "4";

    /**
     * 树添加样式
     */
    public static final String TREE_DEL_STYLE = "<span class='orange'>&nbsp;[已删除]</span>";

    /**
     * 定义log4j的appender name常量
     */
    public static final String LOG_APPENDER_PAYLOG = "paylog";
}
