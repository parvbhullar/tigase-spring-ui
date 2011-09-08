/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.common.constant;

/**
 * ConstantAd.java
 * 
 * @author Administrator
 */
public interface ConstantAd {
    /** 发布 */
    String CON_ISSUE = "1";
    /** 暂停 */
    String CON_SUSPEND = "0";
    enum SuspendType {
        YES(1, "停用"), NO(0, "正常");
        SuspendType(Integer code, String des) {
            this.code = code;
            this.des = des;
        }
        private Integer code;
        private String des;

        public Integer getCode() {
            return code;
        }

        private String getDes() {
            return des;
        }
    }
    /**
     * 广告状态 ConstantAd.java
     * 
     * @author Administrator
     */
    enum Flag {
        DRAFT(0, "草稿"), CHECK(1, "待审核"), CHECKED(2, "审核通过"), CHECK_FAIL(3, "审核不通过"), INSERVICE(4, "正在发布");
        Flag(Integer code, String des) {
            this.code = code;
            this.des = des;
        }
        private Integer code;
        private String des;

        public Integer getCode() {
            return code;
        }

        public String getDes() {
            return des;
        }
    }
    /**
     * banner类型 Background 纯图片 BackgroundAndWord 背景加文字 BackgroundAnd2Wordbanner背景两帧文字 BackgroundAndWordAndIco 背景加文字加小图片
     * 
     * @author Administrator
     */
    enum BannerType {
        Background(1, "纯图片banner"), BackgroundAndWord(2, "背景加文字banner"), BackgroundAnd2Word(3, "背景加2帧文字banner"),
        BackgroundAndWordAndIco(4, "背景加文字加小图片banner");
        BannerType(int code, String des) {
            this.code = code;
            this.des = des;
        }
        private int code;
        private String des;

        public int getCode() {
            return code;
        }

        private String getDes() {
            return des;
        }
    }
    /**
     * 背景类型 0 纯颜色 1图片
     * 
     * @author Administrator
     */
    enum BannerBackGroundType {
        Color(0, "纯颜色"), Img(1, "图片");
        BannerBackGroundType(int code, String des) {
            this.code = code;
            this.des = des;
        }
        private int code;
        private String des;

        public int getCode() {
            return code;
        }

        private String getDes() {
            return des;
        }
    }
    /**
     * banner 链接的url
     * 
     * @author Administrator
     */
    enum BannerUrl {
        BackgroundColor("banner/background_color", "纯颜色背景banner"),
        BackgroundImg("banner/background_img", "纯图片背景banner"), BackgroundAndWordColor("banner/background_word_color",
                "背景颜色加文字banner"), BackgroundAndWordImg("banner/background_word_img", "背景图片加文字banner"),
        BackgroundAnd2WordColor("banner/background_2word_color", "背景颜色加2帧文字banner"), BackgroundAnd2WordImg(
                "banner/background_2word_img", "背景图片加2帧文字banner"), BackgroundAndWordAndIcoColor(
                "banner/background_word_ico_color", "背景颜色加文字加小图片banner"), BackgroundAndWordAndIcoImg(
                "banner/background_word_ico_img", "背景图片加文字加小图片banner");
        BannerUrl(String code, String des) {
            this.code = code;
            this.des = des;
        }
        private String code;
        private String des;

        public String getCode() {
            return code;
        }

        private String getDes() {
            return des;
        }
    }
    /**
     * banner url
     * 
     * @author Administrator
     */
    enum WordUrl {
        commonly("word/commonly", "普通页面");
        WordUrl(String code, String des) {
            this.code = code;
            this.des = des;
        }
        private String code;
        private String des;

        public String getCode() {
            return code;
        }

        private String getDes() {
            return des;
        }
    }
    /**
     * 导航类型
     */
    enum NAVIGATION {
        Button(1, "普通按钮"), AdvancedButton(2, "高级按钮");
        NAVIGATION(Integer code, String des) {
            this.code = code;
            this.des = des;
        }
        private Integer code;
        private String des;

        public Integer getCode() {
            return code;
        }

        private String getDes() {
            return des;
        }
    }
    /**
     * ConstantAd.java
     * 
     * @author Administrator
     */
    enum AdType {
        Wap(1, "Wap"), Page(2, "单页面"), Button(3, "普通按钮"), AdvancedButton(4, "高级按钮");
        AdType(int code, String des) {
            this.code = code;
            this.des = des;
        }
        private int code;
        private String des;

        public int getCode() {
            return code;
        }

        private String getDes() {
            return des;
        }
    }
    /**
     * 富媒体页面分类
     * 
     * @author Administrator
     */
    enum ShowType {
        Word(1, "文字"), WordAndPic(2, "文字图片混排"), Pic(3, "图片组"), map(4, "地图"), taobao(5, "淘宝"), taobaoTrain(6, "淘宝直通车"),
        AndroidMarket(7, "AndroidMarket"), iPhoneMarket(8, "iPhoneMarket");
        ShowType(Integer code, String des) {
            this.code = code;
            this.des = des;
        }
        private Integer code;
        private String des;

        public Integer getCode() {
            return code;
        }

        private String getDes() {
            return des;
        }
    }

    enum FormatType {
        PicUp(1, "上图下文"), PicLeft(2, "左图右文"), PicRigth(3, "右图左文");
        FormatType(Integer code, String des) {
            this.code = code;
            this.des = des;
        }
        private Integer code;
        private String des;

        public Integer getCode() {
            return code;
        }

        private String getDes() {
            return des;
        }
    }
    /**
     * 广告投放平台
     */
    public final static String PLATFORM_IPHONE = "0";
    public final static String PLATFORM_ANDROID = "1";

    /**
     * 广告付费类型 CPC按点击付费
     */
    public final static String AD_PAYMENT_TYPE_CPC = "1";
    /**
     * 广告点击类型 商铺店主默认 CPM 按展示付费
     */
    public final static String AD_PAYMENT_TYPE_CPM = "2";

}
