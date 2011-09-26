/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad;

/**
 * CommonDef.java
 * 
 * @author Administrator
 */
public interface CommonDef {
    /** 单引号 */
    String SINGLE_QUOTE = "'";
    /** 逗号 */
    String STR_COMMA = ",";
    /** 空字符 */
    String EMPTY = "";
    /** 此处定义正常状态常量,数据库中默认为正常状态 */
    public interface dataBaseConstant {
        /** 删除 */
        String DEL_FLAG = "0";
        /** 暂停 */
        String SUSPEND_TYPE = "0";
        /** 冻结 */
        String BLOCKING = "0";
        /** 审核通过 */
        String FLAG = "3";
    }
    /** 此处定义非正常状态常量,数据库中默认为正常状态 */
    public interface unDataBaseConstant {
        /** 删除 */
        String DEL_FLAG = "1";
        /** 暂停 */
        String SUSPEND_TYPE = "1";
        /** 启用 */
        String UN_SUSPEND_TYPE = "0";
        /** 冻结 */
        String BLOCKING = "1";
    }
    /** 系统角色初步定义 */
    public interface userRoleCon {
        /** 普通会员 */
        String CON_MEMBER = "000000";
        /** 广告商 */
        String CON_ADDER = "000001";
        /** 开发者 */
        String CON_DEVELOP = "000010";
        /** 代理商 */
        String CON_AGENT = "000100";
        /** 广告商开发者 */
        String CON_ADDER_DEVELOP = "000011";
    }
    public interface finalStrCon {
        // 操作成功
        String RETURN_SUCCESS = "\u64cd\u4f5c\u6210\u529f";
        // 操作失败
        String RETURN_FAILURE = "\u64cd\u4f5c\u5931\u8d25";
        String REPORT_CON = "0";
    }
    public interface copyCon {
        // 操作成功
        String RETURN_SUCCESS = "恭喜，复制成功！";
        // 操作失败
        String RETURN_FAILURE = "抱歉，操作有误！";
    }
    public interface withdraw {
        //
        String WITHDRAW_SUCCESS = "资金转换成功，可以到广告主账户查询转换金额";
        String TIKUAN_SUCCESS = "提款申请成功，请等待处理";
        String WITHDRAW_FAIL = "当天提款已满三次，请明天再来提款";
    }
    public interface issue {
        // 操作成功
        String RETURN_SUCCESS = "运行成功";
        // 操作失败
        String RETURN_FAILURE = "运行失败";
    }
    public interface delete {
        // 操作成功
        String RETURN_SUCCESS = "删除成功";
        // 操作失败
        String RETURN_FAILURE = "删除失败";
    }
    public interface suspend {
        // 操作成功
        String RETURN_SUCCESS = "暂停成功";
        // 操作失败
        String RETURN_FAILURE = "暂停失败";
    }

    public interface opera {
        // 操作成功
        String RETURN_SUCCESS = "操作成功";
        // 操作失败
        String RETURN_FAILURE = "操作失败";
    }
    /** 应用程序发布、暂停定义 */
    public interface appCon {
        /** 暂停 */
        String CON_ISSUE = "1";
        /** 发布 */
        String CON_SUSPEND = "0";
    }
    public interface commonFlag {
        String YES = "1";
        String NO = "0";
        String FROZEN = "2";
    }
    /** 会员模型数据key */
    public interface userModelKeyCon {
        /** 激活码key */
        String ACTIVE_CODE = "ACTIVE_CODE";
        /** 会员状态key */
        String MEMBER_STATUS = "MEMBER_STATUS";
        /** 会员类型key */
        String DEV_TYPE = "DEV_TYPE";
        /** 权限信息key */
        String BASE_ROLE = "BASE_ROLE";
    }

    /**
     * 与客服端交互数据参数名
     * 
     * @author leifenghai
     */
    public interface interactiveParam {
        /**
         * 应用程序编号（AI）
         */
        String APP_CODE = "AI";
        /**
         * 硬件唯一识别码(SID)
         */
        String S_ID = "SID";
        /**
         * SDK版本(SV)
         */
        String SDK_VERSION = "SV";
        /**
         * 是否为真机环境(SA)
         */
        String RUN_STATE = "SA";
        /**
         * 资源文件版本(FV)
         */
        String FILE_VERSION = "FV";
        /**
         * 控制符(CF)
         */
        String CONTROL_FLAG = "CF";
        /**
         * 应用程序名(AN)
         */
        String APP_NAME = "AN";
        /**
         * 设备名称(D)
         */
        String DEVICE_NAME = "D";
        /**
         * SIM卡号(SM)
         */
        String SIM_CARD = "SM";
        /**
         * 运营商(O)
         */
        String OPERATORS = "O";
        /**
         * 操作系统(SY)
         */
        String SYSTEM = "SY";
        /**
         * 操作系统版本号(V)
         */
        String SYSTEM_VERSION = "V";
        /**
         * 电话号码(PN)
         */
        String PHONE = "PN";
        /**
         * 客户端信息上传标识符(U)
         */
        String UPLOAD_STAT = "U";
        /**
         * 当前操作系统(OS)
         */
        String NONCE_SYSTEM = "OS";
        /**
         * 待更新的资源文件URL列表(RL)
         */
        String RESOURCELIST = "RL";
        /**
         * 省(P)
         */
        String PROVINCE = "P";
        /**
         * 市(C)
         */
        String CITY = "C";
        /**
         * 区(A)
         */
        String AREA = "A";
        /**
         * 广告ID列表(IDL)
         */
        String AD_ID_LIST = "IDL";
        /**
         * 广告ID号(ID)`
         */
        String AD_ID = "ID";
        /**
         * 广告banner的URL(BU)
         */
        String BANNER_URL = "BU";
        /**
         * 广告富内容的URL(AU)
         */
        String AD_URL = "AU";
        /**
         * 屏幕宽度(SW)
         */
        String SCREEN_WIDTH = "SW";
        /**
         * 屏幕高度(SH)
         */
        String SCREEN_HEIGHT = "SH";
        /**
         * 经度(LO)
         */
        String LONGITUDE = "LO";
        /**
         * 纬度(LA)
         */
        String LATITUDE = "LA";
        /**
         * 广告内容结束提示（LS）
         */
        String LEAVE_RESULT = "LS";
    }
    public interface tradeType {
        /**
         * 会员充值时: 账户充值交易类型为0 优惠券:1 代理商代充:2 支出:3收支类型:{广告商支出:A.代理商支出:B.优惠券支出C} 收益:4收支类型:{开发者收益:A.代理商收益:B.平台收益C}
         * 交易事件ID,指对应每条记录在历史表中的ID,如果没有.那表示新加的历史记录 转换:5 转换,6提现,7:展示支出,8展示收益
         */
        String CON_MEMBER_RECHARGE = "0";
        String CON_AGENT_RECHARGE = "2";
        String CON_COUPON_RECHARGE = "1";
        String CON_INCOME = "4";
        String CON_PAY = "3";
        String CON_SHOW_PAY = "7";
        String CON_SHOW_INCOME = "8";
        String CON_CONVERT = "5";
        String CON_ADVERTISER_INCOME_PAY = "A";
        String CON_AGENT_INCOME_PAY = "B";
        String CON_COUPON_INCOME_PAY = "C";
        String CON_APP_INCOME_PAY = "A";
        String CON_AGENT_INCOME_PAY_OWNER = "D";
    }
    public interface incomeType {
        /**
         * 0AIRAD平台1开发商2代理商
         */
        String CON_SPACE = "0";
        String CON_AGENT = "2";
        String CON_APP = "1";

        String CON_APP_ROLE_BLOCK = "3";
        String CON_APP_USER_BLOCK = "4";

        String CON_AGENT_ROLE_BLOCK = "5";
        String CON_AGENT_USER_BLOCK = "6";

        String CON_USER_ROLE_STATUS = "2";

        String CON_INCOME_TYPE_AGENT = "4";
        String CON_INCOME_TYPE_SPACE = "3";
        String CON_INCOME_TYPE_APP = "5";
    }
    public interface reqType {
        // 富媒体，贫媒体，展示
        String CON_REQ_TYPE_RICH = "0";
        String CON_REQ_TYPE_WAP = "1";
        String CON_REQ_TYPE_SHOW = "2";
        // 请求是点击还是展示
        String CON_REQ_TYPE_SHOW_Z = "Z";
        String CON_REQ_TYPE_CLICK_D = "D";
    }
    /**
     * 充值方式 CommonDef.java
     * 
     * @author Administrator
     */
    public interface rechargeHisType {
        /**
         * 0银联充值，1代理商为广告商充值2收益转换3支付宝4优惠券充值5平台(广告)6平台（开发者） 7平台（商铺店主）8平台(品牌广告主)
         */
        String CON_CHINAPAY = "0";
        String CON_AGENT = "1";
        String CON_RECHARGE = "2";
        String CON_ALIPAY = "3";
        String CON_COUPON = "4";
        String CON_PLATFORM = "5";
        String CON_PLATFORM_APP = "6";
        String CON_PLATFORM_SHOPKEEPER = "7";
        String CON_PLATFORM_BRANDADKEEPER = "8";
    }
    /**
     * 资金转换标示4代表成功，5代表失败,
     * 
     * @author Administrator
     */
    public interface rechargeHisStatus {
        /*
         * 银联支付成功
         */
        String RETURN_ORDER_STATUS_SUCC = "1001";
        String CON_RECHARGE_WAIT = "0"; // 等待支付
        String CON_RECHARGE_SUCC = "1"; // 消费交易成功
        String CON_RECHARGE_CANCEL = "2"; // 取消支付

    }
    public interface chinaUnionPay {
        String CHINAUNIONPAY_BGRETURL = "chinaUnionpay.BgRetUrl";
        String CHINAUNIONPAY_PAGERETURL = "chinaUnionpay.PageRetUrl";
    }
    public interface accountEdit {
        // 操作成功
        String ACCOUNT_SUCCESS = "账号信息修改成功";
        String ACC_SUCCESS = "账户信息修改成功";
        // 操作失败
        String PASSWORD_SUCCESS = "密码修改成功";
    }
    public interface reportDelete {
        // 操作成功
        String REPORT_SUCCESS = "报表删除成功！";

    }
    public interface report {
        // 修改标志
        String REPORT_EDIT = "1";
        // 修改取消标志
        String REPORT_CANCLE = "1";
        // 活动、广告组、广告报表报告标示
        String REPORT_AD = "0";
        // 开发者类型
        String REPORT_DEVELOP = "1";
        // 代理商类型
        String REPORT_AGENT = "2";
        // 提现类型
        String REPORT_CARRY = "3";
        // 应用程序
        String REPORT_APP = "4";
        // 添加、修改跳转标示
        // 广告
        String REPORT_ADD_AD = "2";
        // 活动
        String REPORT_ADD_CAM = "0";
        // 广告组
        String REPORT_ADD_ADGROUP = "1";
        // 开发者
        String REPORT_ADD_DEVELOP = "3";
        // 代理商
        String REPORT_ADD_AGENT = "4";
        // 自己选择日期
        String REPORT_DATE_TYPE_SELF = "0";
        // 选择快捷方式
        String REPORT_DATE_TYPE_CHOOSE = "1";
        // 报告类型（1详细0汇总）
        String REPORT_TOTAL_TYPE = "0";
        String REPORT_DETAIL_TYPE = "1";
        // 初始化
        String INIT_AD_TITLE = "总展示次数";
        String INIT_ADV_TITLE = "广告商广告展示次数";
        String INIT_APP_TITLE = "总请求数/次";
        // 图表样式
        String CHART_X_COLOR = "#CCCCCC";
        String CHART_BG_COLOR = "#FFFFFF";
    }
    /**
     * 应用类型 CommonDef.java
     * 
     * @author Administrator
     */
    public interface appType {
        /**
         * 0iphone1android
         */
        String CON_IPHONE = "0";
        String CON_ANDROID = "1";
    }
    public interface appTypeDetail {
        /**
         * 0iphone1android
         */
        String APP_IPHONE = "iPhone";
        String APP_ANDROID = "Android";
    }
    /**
     * 下载地址 CommonDef.java
     * 
     * @author Administrator
     */
    public interface downUrlKey {
        String KEY_IPHONE = "iphoneUrl";
        String KEY_ANDROID = "androidUrl";
        String ADURL = "adUrl";
        String ADBANNERURL = "adBannerUrl";
    }

    /**
     * 系统自定义变量
     * 
     * @author wangzhongwei
     */
    public interface sysConfigType {
        // 请求广告最大数
        String MAX_NUM = "AD_NUM";
        // 代理商给自己转换金额比例
        String AGENT_PERCENT = "AGENT_PERCENT";
        // 开发者给自己转换金额比例（000011身份）
        String APP_PERCENT = "APP_PERCENT";
    }
    /**
     * 报表时间范围类型 CommonDef.java
     * 
     * @author Administrator
     */
    public interface dateType {
        String ONESELF = "0";
        String TODAY = "1";
        String YESTERDAY = "2";
        String LAST7 = "3";
        String LAST30 = "4";
        String PREMONTH = "5";
        String PREVQUARTER = "6";
        String YTD = "7";
        String QTD = "8";
        String SHORTCUTKEY = "1";
        String CON_JOB_DAY = "0";
        String CON_JOB_MONTH = "1";
    }
    // 配置表分成
    public interface configType {
        String CON_SHARE_BASI = "SHARE_BASI";
        String CON_SHOW_COST = "SHOW_COST";
    }

    public interface intNum {
        // 小数点后位
        int CON_INT_NUM = 2;
        // 展示次数
        int CON_SHOW_NUM = 1000;
    }
    // 数据初始化
    public interface initData {
        String REPORT_CAM_NAME = "名称";
        String REPORT_CAM_TIME = "日期";
        String REPORT_CAM_SHOWTYPE = "广告展现形式";
        String REPORT_CAM_SUMSHOW = "总展示次数";
        String REPORT_CAM_SUMCLICK = "总点击数";
        String REPORT_CAM_SHOWMONEY = "展示成本";
        String REPORT_CAM_CLICLRATE = "点击率";
        String REPORT_CAM_CLICKMONEY = "点击成本";

        String REPORT_APP_NAME = "应用程序名称";
        String REPORT_APP_TIME = "日期";
        String REPORT_APP_REQNUM = "请求数";
        String REPORT_APP_SHOWNUM = "展示数";
        String REPORT_APP_CLICKNUM = "点击数";
        String REPORT_APP_CLICKMONEY = "点击收入";
        String REPORT_APP_TOTALMONY = "总收入";
        String REPORT_APP_CLICKRATE = "投放率";
        String REPORT_APP_CLICKPUT = "点击率(CTR)";

        String REPORT_ADV_NAME = "广告商名称";
        String REPORT_ADV_TIME = "广告商创建时间";
        String REPORT_ADV_SHOWNUM = "广告商广告展示次数";
        String REPORT_ADV_CLICKNUM = "广告商广告点击次数";
        String REPORT_ADV_OFFER = "分成收入";
    }
    // 展现形式
    public interface reportShowType {
        // 代号
        String TYPE_WORD = "1";
        String TYPE_WORDANDPIC = "2";
        String TYPE_PICTURE = "3";
        String TYPE_MAP = "4";
        String TYPE_TAOBAO = "5";
        String TYPE_OTHER = "";
        // 文本
        String TYPE_WORD_TEXT = "文字";
        String TYPE_WORDANDPIC_TEXT = "图文";
        String TYPE_PICTURE_TEXT = "图片";
        String TYPE_MAP_TEXT = "地图";
        String TYPE_TAOBAO_TEXT = "淘宝";
        String TYPE_OTHER_TEXT = "其他";
    }
    // 日志表中的成功失败标志
    public interface logErrorStatus {
        String CON_STATUS_FAIL = "0";
        String CON_STATUS_SUCC = "1";
    }
    // JOB名称
    public interface jobName {
        String CON_JOB_APP = "APPJOB";
        String CON_JOB_AGENT = "AGENTJOB";
        String CON_JOB_AD = "ADJOB";
        String CON_JOB_SHOW_COST = "SHOWCOSTJOB";
        String CON_JOB_WITHDRAWJOB = "WITHDRAWJOB";
    }
    // 报表导出名称
    public interface reportImportName {
        String WITHDRAW_IMPORT_NAME = "提现历史";
    }
    public interface dateStyle {
        String CON_YYYYMMDDHHMMDD = "yyyy-MM-dd HH:mm:dd";
        String CON_YYYYMMDD = "yyyy-MM-dd";
        String CON_YYYYMM = "yyyy-MM";
    }
    public interface timeFlag {
        String HAVE_ENDTIME = "1";
        String NO_ENDTIME = "0";
    }
    public interface withDrawOpe {
        /**
         * 等待审核
         */
        String WITH_WAIT = "0";
        /**
         * 审核通过
         */
        String WITH_PASS = "1";
        /**
         * 审核不通过
         */
        String WITH_NO_PASS = "2";
        /**
         * 已付款
         */
        String WITH_PAY = "3";
    }
    public interface intro {
        /**
         * 系统已经发布
         */
        String INTRO_YES = "1";
        /**
         * 系统暂未发布
         */
        String INTRO_NO = "0";
    }
    public interface CVSTB {
        /**
         * cvs 表头信息
         */
        String DRAW_MONEY_ID = "序号";
        String DRAW_MONEY_SERIAL_ID = "提现流水号";
        String DRAW_MONEY = "提现金额";
        String STATUS = "受理状态";
        String DRAW_TIME = "提现充值时间";
        /**
         * 处理数据显示信息
         */
        String STATUS1 = "受理中";
        String STATUS2 = "已支付";
        String STATUS3 = "支付失败";
        String STATUS4 = "其他";

    }
    public interface RECHARGE {
        String RHARGE_STA1 = "充值成功";
        String RHARGE_STA2 = "资金转换成功";
        String RHARGE_STA3 = "交易失败";
        String RHARGE_ID = "序号";
        String RHARGE_SERIALID = "流水号";
        String RHARGE_MONEY = "金额";
        String RHARGE_TYPE = "充值类型";
        String RHARGE_TIME = "充值时间";
        String RHARGE_REMARK = "备注";
        String RHARGE_EMAIL = "账户";
        String RHARGE_STATE = "充值状态";
        String TRADE_TYPE0 = "银联充值";
        String TRADE_TYPE1 = "代理商代充值";
        String TRADE_TYPE2 = "收益金转换";
        String TRADE_TYPE3 = "平台充值";
        String APP_RACHARGE_CONFIG = "开发者资金转换比例设置";
    }
    /**
     * 广告的状态 CommonDef.java
     * 
     * @author Administrator
     */
    public interface adStatus {
        // 冻结的广告数
        String BLOCKINGCOUNT = "冻结的广告数:";
        // 发布的广告数
        String ISSUCOUNT = "发布的广告数:";
        // 暂停的广告数
        String SUSPENDCOUNT = "暂停的广告数:";
        // 草稿的广告数
        String DRAFTCOUNT = "草稿的广告数:";
        // 审核中的广告数
        String CHECKCOUNT = "审核中的广告数:";
        // 审核通过的广告数
        String CHECKPASSCOUNT = "审核通过的广告数:";
        // 审核不通过的广告数
        String CHECKNOCOUNT = "审核不通过的广告数:";
        // 广告总数
        String ADCOUNT = "广告总数:";
        // 暂无广告
        String HASNO = " 暂无广告";

        // 冻结
        String BLOCKING = "5";
        // 发布
        String ISSU = "4";
        // 暂停
        String SUSPEND = "6";
        // 草稿
        String DRAFT = "0";
        // 审核
        String CHECK = "1";
        // 审核通过
        String CHECKPASS = "2";
        // 审核不通过
        String CHECKNO = "3";
    }
    public interface campaignStatus {
        /**
         * 已冻结
         */
        String BLOCKING_YES = "1";
        /**
         * 未冻结
         */
        String BLOCKING_NO = "0";
        /**
         * 设置投放方式标准(正常)
         */
        String PUT_KING = "1";
        /**
         * 运行
         */
        String SUSPENDTYPE = "0";
    }
    /**
     * 转义常量 CommonDef.java
     * 
     * @author Administrator
     */
    public interface escape {
        String ESCAPE = "escape '/'";
    }
}
