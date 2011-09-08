/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.form;

import org.apache.commons.lang.StringUtils;

import com.mitian.airad.model.CoreWapAd;

/**
 * WapForm.java
 * 
 * @author Administrator
 */
public class WapForm extends AbstractForm {
    /**
     * 贫媒体id
     */
    private String wapId;
    /**
     * 贫媒体标题
     */
    private String wapTitle;
    /**
     * 点击拨打电话
     */
    private String clickTel;
    /**
     * 选择发送短信
     */
    private String selSendType;
    /**
     * 传真
     */
    private String fax;
    /**
     * email
     */
    private String email;
    /**
     * QQ
     */
    private String qq;
    /**
     * MSN
     */
    private String msn;
    /**
     * 联系地址
     */
    private String contactAddress;
    /**
     *链接文本
     */
    private String linkText;
    /**
     * Url地址
     */
    private String urlAddress;
    /**
     * 联系电话
     */
    private String telephone;
    /**
     * 广告id
     */
    private String adId;
    /**
     * 贫媒体内容
     */
    private String wapContent;
    /**
     * 广告组id
     */
    private String adGroupId;
    /**
     * 广告名称
     */
    private String adName;
    /** 广告活动id */
    private String campaignId;

    /**
     * @return the adName
     */
    public String getAdName() {
        return adName;
    }

    /**
     * @param adName the adName to set
     */
    public void setAdName(String adName) {
        this.adName = adName;
    }

    /**
     * @return the adGroupId
     */
    public String getAdGroupId() {
        return adGroupId;
    }

    /**
     * @param adGroupId the adGroupId to set
     */
    public void setAdGroupId(String adGroupId) {
        this.adGroupId = adGroupId;
    }

    /**
     * @return the wapId
     */
    public String getWapId() {
        return wapId;
    }

    /**
     * @param wapId the wapId to set
     */
    public void setWapId(String wapId) {
        this.wapId = wapId;
    }

    /**
     * @return the wapTitle
     */
    public String getWapTitle() {
        return wapTitle;
    }

    /**
     * @param wapTitle the wapTitle to set
     */
    public void setWapTitle(String wapTitle) {
        this.wapTitle = wapTitle;
    }

    /**
     * @return the clickTel
     */
    public String getClickTel() {
        return clickTel;
    }

    /**
     * @param clickTel the clickTel to set
     */
    public void setClickTel(String clickTel) {
        this.clickTel = clickTel;
    }

    /**
     * @return the selSendType
     */
    public String getSelSendType() {
        return selSendType;
    }

    /**
     * @param selSendType the selSendType to set
     */
    public void setSelSendType(String selSendType) {
        this.selSendType = selSendType;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq the qq to set
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * @return the msn
     */
    public String getMsn() {
        return msn;
    }

    /**
     * @param msn the msn to set
     */
    public void setMsn(String msn) {
        this.msn = msn;
    }

    /**
     * @return the contactAddress
     */
    public String getContactAddress() {
        return contactAddress;
    }

    /**
     * @param contactAddress the contactAddress to set
     */
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    /**
     * @return the linkText
     */
    public String getLinkText() {
        return linkText;
    }

    /**
     * @param linkText the linkText to set
     */
    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    /**
     * @return the urlAddress
     */
    public String getUrlAddress() {
        return urlAddress;
    }

    /**
     * @param urlAddress the urlAddress to set
     */
    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the adId
     */
    public String getAdId() {
        return adId;
    }

    /**
     * @param adId the adId to set
     */
    public void setAdId(String adId) {
        this.adId = adId;
    }

    /**
     * @return the wapContent
     */
    public String getWapContent() {
        return wapContent;
    }

    /**
     * @param wapContent the wapContent to set
     */
    public void setWapContent(String wapContent) {
        this.wapContent = wapContent;
    }

    private CoreWapAd wap = new CoreWapAd();

    /**
     * @return the campaignId
     */
    public String getCampaignId() {
        return campaignId;
    }

    /**
     * @param campaignId the campaignId to set
     */
    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * @return the wap
     */
    public CoreWapAd getWap() {
        return wap;
    }

    /**
     * @param wap the wap to set
     */
    public void setWap(CoreWapAd wap) {
        this.wap = wap;
    }

    @Override
    public void form2domain() {
        if (StringUtils.isNotBlank(wapId)) {
            wap.setWapId(Integer.valueOf(wapId));
        }
        if (StringUtils.isNotBlank(adId)) {
            wap.setAdId(Integer.valueOf(adId));
        }
        if (StringUtils.isNotBlank(adGroupId)) {
            wap.setAdGroupId(Integer.valueOf(adGroupId));
        }
        if (StringUtils.isNotBlank(campaignId)) {
            wap.setCampaignId(Integer.valueOf(campaignId));
        }
    }

}
