package com.mitian.airad.web.form;

import java.util.ArrayList;
import java.util.List;

import com.mitian.airad.model.CoreApp;
import com.mitian.airad.model.Dictionary;

/**
 * AppForm.java
 * 
 * @author baojun
 */
public class AppForm extends AbstractForm {

    /** 查询开始时间 */
    private String startTime;
    /** 查询结束时间 */
    private String endTime;
    /** 应用信息 */
    private CoreApp app = new CoreApp();
    /** 用户群体 */
    private List<Dictionary> tagSexs = new ArrayList<Dictionary>();
    /** 用户年龄段 */
    private List<Dictionary> tagAges = new ArrayList<Dictionary>();
    /** 应用分类 */
    private List<Dictionary> tagTypes = new ArrayList<Dictionary>();
    /** 使用平台 */
    private List<Dictionary> tagSps = new ArrayList<Dictionary>();

    /** 应用活动列表 */
    private List<CoreApp> listBean = new ArrayList<CoreApp>();
    /** 应用名称 */
    private String appName;
    /** 应用分类 */
    private String appType;
    /** 应用使用平台 */
    private String appPlatformType;
    /** 用户群体 */
    private String userSex;
    /** 年龄段 */
    private String userAgePag;
    /** 发布范围 */
    private String pubOutside;
    /** sdk下载地址 */
    private String downLoad;
    /** ihponesdk下载地址 */
    private String iphoneDownLoad;
    /** androidsdk下载地址 */
    private String androidDownLoad;
    /** 是否根据时间段查找收益标示 */
    private String timeSlotFlag;

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the app
     */
    public CoreApp getApp() {
        return app;
    }

    /**
     * @param app the app to set
     */
    public void setApp(CoreApp app) {
        this.app = app;
    }

    /**
     * @return the tagSexs
     */
    public List<Dictionary> getTagSexs() {
        return tagSexs;
    }

    /**
     * @param tagSexs the tagSexs to set
     */
    public void setTagSexs(List<Dictionary> tagSexs) {
        this.tagSexs = tagSexs;
    }

    /**
     * @return the tagAges
     */
    public List<Dictionary> getTagAges() {
        return tagAges;
    }

    /**
     * @param tagAges the tagAges to set
     */
    public void setTagAges(List<Dictionary> tagAges) {
        this.tagAges = tagAges;
    }

    /**
     * @return the tagTypes
     */
    public List<Dictionary> getTagTypes() {
        return tagTypes;
    }

    /**
     * @param tagTypes the tagTypes to set
     */
    public void setTagTypes(List<Dictionary> tagTypes) {
        this.tagTypes = tagTypes;
    }

    /**
     * @return the tagSps
     */
    public List<Dictionary> getTagSps() {
        return tagSps;
    }

    /**
     * @param tagSps the tagSps to set
     */
    public void setTagSps(List<Dictionary> tagSps) {
        this.tagSps = tagSps;
    }

    /**
     * @return the listBean
     */
    public List<CoreApp> getListBean() {
        return listBean;
    }

    /**
     * @param listBean the listBean to set
     */
    public void setListBean(List<CoreApp> listBean) {
        this.listBean = listBean;
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
     * @return the appType
     */
    public String getAppType() {
        return appType;
    }

    /**
     * @param appType the appType to set
     */
    public void setAppType(String appType) {
        this.appType = appType;
    }

    /**
     * @return the appPlatformType
     */
    public String getAppPlatformType() {
        return appPlatformType;
    }

    /**
     * @param appPlatformType the appPlatformType to set
     */
    public void setAppPlatformType(String appPlatformType) {
        this.appPlatformType = appPlatformType;
    }

    /**
     * @return the userSex
     */
    public String getUserSex() {
        return userSex;
    }

    /**
     * @param userSex the userSex to set
     */
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    /**
     * @return the userAgePag
     */
    public String getUserAgePag() {
        return userAgePag;
    }

    /**
     * @param userAgePag the userAgePag to set
     */
    public void setUserAgePag(String userAgePag) {
        this.userAgePag = userAgePag;
    }

    /**
     * @return the pubOutside
     */
    public String getPubOutside() {
        return pubOutside;
    }

    /**
     * @param pubOutside the pubOutside to set
     */
    public void setPubOutside(String pubOutside) {
        this.pubOutside = pubOutside;
    }

    /**
     * @return the downLoad
     */
    public String getDownLoad() {
        return downLoad;
    }

    /**
     * @param downLoad the downLoad to set
     */
    public void setDownLoad(String downLoad) {
        this.downLoad = downLoad;
    }

    /**
     * @return the iphoneDownLoad
     */
    public String getIphoneDownLoad() {
        return iphoneDownLoad;
    }

    /**
     * @param iphoneDownLoad the iphoneDownLoad to set
     */
    public void setIphoneDownLoad(String iphoneDownLoad) {
        this.iphoneDownLoad = iphoneDownLoad;
    }

    /**
     * @return the androidDownLoad
     */
    public String getAndroidDownLoad() {
        return androidDownLoad;
    }

    /**
     * @param androidDownLoad the androidDownLoad to set
     */
    public void setAndroidDownLoad(String androidDownLoad) {
        this.androidDownLoad = androidDownLoad;
    }

    /**
     * @return the timeSlotFlag
     */
    public String getTimeSlotFlag() {
        return timeSlotFlag;
    }

    /**
     * @param timeSlotFlag the timeSlotFlag to set
     */
    public void setTimeSlotFlag(String timeSlotFlag) {
        this.timeSlotFlag = timeSlotFlag;
    }

    @Override
    public void form2domain() {

    }
}
