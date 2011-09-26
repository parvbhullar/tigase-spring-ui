package com.mitian.airad.model;

import java.math.BigDecimal;

/**
 * StatAppView.java
 * 
 * @author baojun
 */
public class StatAppView {
    private String appId;

    /**
     * @param appId the appId to set
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * @return the appId
     */
    public String getAppId() {
        return appId;
    }
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * SATA_APP_VIEW.APP_CODE
     */
    private String appCode;

    /**
     * @return the appCode
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * @param appCode the appCode to set
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    /**
     * @return the appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName the appName to set
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * @return the clickNum
     */
    public Integer getClickNum() {
        return clickNum;
    }

    /**
     * @param clickNum the clickNum to set
     */
    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    /**
     * @return the showNum
     */
    public Integer getShowNum() {
        return showNum;
    }

    /**
     * @param showNum the showNum to set
     */
    public void setShowNum(Integer showNum) {
        this.showNum = showNum;
    }

    /**
     * @return the offer
     */
    public BigDecimal getOffer() {
        return offer;
    }

    /**
     * @param offer the offer to set
     */
    public void setOffer(BigDecimal offer) {
        this.offer = offer;
    }

    /**
     * @return the maxNum
     */
    public Integer getMaxNum() {
        return maxNum;
    }

    /**
     * @param maxNum the maxNum to set
     */
    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    /**
     * @return the adNum
     */
    public Integer getAdNum() {
        return adNum;
    }

    /**
     * @param adNum the adNum to set
     */
    public void setAdNum(Integer adNum) {
        this.adNum = adNum;
    }

    /**
     * @return the reqtime
     */
    public String getReqtime() {
        return reqtime;
    }

    /**
     * @param reqtime the reqtime to set
     */
    public void setReqtime(String reqtime) {
        this.reqtime = reqtime;
    }

    /**
     * @return the clickRate
     */
    public BigDecimal getClickRate() {
        return clickRate;
    }

    /**
     * @param clickRate the clickRate to set
     */
    public void setClickRate(BigDecimal clickRate) {
        this.clickRate = clickRate;
    }

    /**
     * @return the putRate
     */
    public BigDecimal getPutRate() {
        return putRate;
    }

    /**
     * @param putRate the putRate to set
     */
    public void setPutRate(BigDecimal putRate) {
        this.putRate = putRate;
    }

    /**
     * 应用程序名字
     */
    private String appName;

    /**
     * 点击数
     */
    private Integer clickNum;

    /**
     * 展示数
     */
    private Integer showNum;

    /**
     * 收益
     */
    private BigDecimal offer;

    /**
     * 最大返回数(请求数)
     */
    private Integer maxNum;

    /**
     * 实际返回数
     */
    private Integer adNum;

    /**
     * 时间
     */
    private String reqtime;

    /**
     * 点击率
     */
    private BigDecimal clickRate;

    /**
     *投放率
     */
    private BigDecimal putRate;

}
