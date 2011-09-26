package com.mitian.airad.model;

import java.util.Date;

/**
 * ReqResRecord.java
 * 
 * @author baojun
 */
public class ReqResRecord {
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * REQ_RES_RECORD.REQ_RES_ID
     * 
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    private Integer reqResId;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * REQ_RES_RECORD.AD_NUM
     * 
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    private Integer adNum;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * REQ_RES_RECORD.MAX_NUM
     * 
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    private Short maxNum;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * REQ_RES_RECORD.REQ_TIME
     * 
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    private Date reqTime;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * REQ_RES_RECORD.APP_CODE
     * 
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    private String appCode;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * REQ_RES_RECORD.OS_TYPE
     * 
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    private String osType;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * REQ_RES_RECORD.PROVINCE
     * 
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    private Short province;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * REQ_RES_RECORD.CITY
     * 
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    private Short city;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * REQ_RES_RECORD.AREA
     * 
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    private Short area;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;
    /**
     * IP地址
     */
    private String resRecordIp;
    /**
     * 用户代理
     */
    private String resRecordUserAgent;

    /**
     * 硬件唯一识别码
     */
    private String soleId;

    /**
     * @return the soleId
     */
    public String getSoleId() {
        return soleId;
    }

    /**
     * @param soleId the soleId to set
     */
    public void setSoleId(String soleId) {
        this.soleId = soleId;
    }

    /**
     * @return the resRecordUserAgent
     */
    public String getResRecordUserAgent() {
        return resRecordUserAgent;
    }

    /**
     * @param resRecordUserAgent the resRecordUserAgent to set
     */
    public void setResRecordUserAgent(String resRecordUserAgent) {
        this.resRecordUserAgent = resRecordUserAgent;
    }

    /**
     * @return the resRecordIp
     */
    public String getResRecordIp() {
        return resRecordIp;
    }

    /**
     * @param resRecordIp the resRecordIp to set
     */
    public void setResRecordIp(String resRecordIp) {
        this.resRecordIp = resRecordIp;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * REQ_RES_RECORD.REQ_RES_ID
     * 
     * @return the value of REQ_RES_RECORD.REQ_RES_ID
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public Integer getReqResId() {
        return reqResId;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * REQ_RES_RECORD.REQ_RES_ID
     * 
     * @param reqResId the value for REQ_RES_RECORD.REQ_RES_ID
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public void setReqResId(Integer reqResId) {
        this.reqResId = reqResId;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * REQ_RES_RECORD.AD_NUM
     * 
     * @return the value of REQ_RES_RECORD.AD_NUM
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public Integer getAdNum() {
        return adNum;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * REQ_RES_RECORD.AD_NUM
     * 
     * @param adNum the value for REQ_RES_RECORD.AD_NUM
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public void setAdNum(Integer adNum) {
        this.adNum = adNum;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * REQ_RES_RECORD.MAX_NUM
     * 
     * @return the value of REQ_RES_RECORD.MAX_NUM
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public Short getMaxNum() {
        return maxNum;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * REQ_RES_RECORD.MAX_NUM
     * 
     * @param maxNum the value for REQ_RES_RECORD.MAX_NUM
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public void setMaxNum(Short maxNum) {
        this.maxNum = maxNum;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * REQ_RES_RECORD.REQ_TIME
     * 
     * @return the value of REQ_RES_RECORD.REQ_TIME
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public Date getReqTime() {
        return reqTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * REQ_RES_RECORD.REQ_TIME
     * 
     * @param reqTime the value for REQ_RES_RECORD.REQ_TIME
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * REQ_RES_RECORD.APP_CODE
     * 
     * @return the value of REQ_RES_RECORD.APP_CODE
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * REQ_RES_RECORD.APP_CODE
     * 
     * @param appCode the value for REQ_RES_RECORD.APP_CODE
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * REQ_RES_RECORD.OS_TYPE
     * 
     * @return the value of REQ_RES_RECORD.OS_TYPE
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public String getOsType() {
        return osType;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * REQ_RES_RECORD.OS_TYPE
     * 
     * @param osType the value for REQ_RES_RECORD.OS_TYPE
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public void setOsType(String osType) {
        this.osType = osType;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * REQ_RES_RECORD.PROVINCE
     * 
     * @return the value of REQ_RES_RECORD.PROVINCE
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public Short getProvince() {
        return province;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * REQ_RES_RECORD.PROVINCE
     * 
     * @param province the value for REQ_RES_RECORD.PROVINCE
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public void setProvince(Short province) {
        this.province = province;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * REQ_RES_RECORD.CITY
     * 
     * @return the value of REQ_RES_RECORD.CITY
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public Short getCity() {
        return city;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * REQ_RES_RECORD.CITY
     * 
     * @param city the value for REQ_RES_RECORD.CITY
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public void setCity(Short city) {
        this.city = city;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * REQ_RES_RECORD.AREA
     * 
     * @return the value of REQ_RES_RECORD.AREA
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public Short getArea() {
        return area;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * REQ_RES_RECORD.AREA
     * 
     * @param area the value for REQ_RES_RECORD.AREA
     * @ibatorgenerated Mon Mar 21 14:24:16 CST 2011
     */
    public void setArea(Short area) {
        this.area = area;
    }
}
