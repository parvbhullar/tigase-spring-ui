/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.model;

import java.math.BigDecimal;

/**
 * CoreAdExtend.java
 * 
 * @author penghan 包含广告表所有字段,及与该广告相关字段
 */
public class CoreAdExtend {

    /**
     * 广告id
     */
    private Integer adId;
    /**
     *贫媒体id
     */
    private Integer wapId;
    /**
     * 广告组id
     */
    private Integer adGroupId;
    /**
     * 富媒体id
     */
    private Integer richId;
    /**
     * bananerID
     */
    private Integer bannerId;
    /**
     * 广告名称
     */
    private String adName;
    /**
     *广告详细信息
     */
    private String adDetailInfo;
    /**
     * 暂停标记
     */
    private String suspendType;
    /**
     *出价
     */
    private BigDecimal adOffer;

    /**
     * 添加人
     */
    private String addOper;
    /**
     * 添加时间
     */
    private String addTime;
    /**
     * 更新人
     */
    private String updOper;
    /**
     * 更新时间
     */
    private String updTime;
    /**
     * 删除人
     */
    private String delOper;
    /**
     * 删除时间
     */
    private String delTime;
    /**
     * 删除标记
     */
    private String delFlag;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 活动id
     */
    private Integer campaignId;

    /**
     * 显示次数
     */
    private Integer showNum;

    /**
     * 账户充钱总额
     */
    private BigDecimal selfMoney;

    /**
     * 代理商充钱总额
     */
    private BigDecimal agentMoney;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 日预算
     */
    private BigDecimal budgetDay;

    /**
     * 权重
     */
    private double weight;

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return the adId
     */
    public Integer getAdId() {
        return adId;
    }

    /**
     * @param adId the adId to set
     */
    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    /**
     * @return the wapId
     */
    public Integer getWapId() {
        return wapId;
    }

    /**
     * @param wapId the wapId to set
     */
    public void setWapId(Integer wapId) {
        this.wapId = wapId;
    }

    /**
     * @return the adGroupId
     */
    public Integer getAdGroupId() {
        return adGroupId;
    }

    /**
     * @param adGroupId the adGroupId to set
     */
    public void setAdGroupId(Integer adGroupId) {
        this.adGroupId = adGroupId;
    }

    /**
     * @return the richId
     */
    public Integer getRichId() {
        return richId;
    }

    /**
     * @param richId the richId to set
     */
    public void setRichId(Integer richId) {
        this.richId = richId;
    }

    /**
     * @return the bannerId
     */
    public Integer getBannerId() {
        return bannerId;
    }

    /**
     * @param bannerId the bannerId to set
     */
    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

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
     * @return the adDetailInfo
     */
    public String getAdDetailInfo() {
        return adDetailInfo;
    }

    /**
     * @param adDetailInfo the adDetailInfo to set
     */
    public void setAdDetailInfo(String adDetailInfo) {
        this.adDetailInfo = adDetailInfo;
    }

    /**
     * @return the suspendType
     */
    public String getSuspendType() {
        return suspendType;
    }

    /**
     * @param suspendType the suspendType to set
     */
    public void setSuspendType(String suspendType) {
        this.suspendType = suspendType;
    }

    /**
     * @return the adOffer
     */
    public BigDecimal getAdOffer() {
        return adOffer;
    }

    /**
     * @param adOffer the adOffer to set
     */
    public void setAdOffer(BigDecimal adOffer) {
        this.adOffer = adOffer;
    }

    /**
     * @return the addOper
     */
    public String getAddOper() {
        return addOper;
    }

    /**
     * @param addOper the addOper to set
     */
    public void setAddOper(String addOper) {
        this.addOper = addOper;
    }

    /**
     * @return the addTime
     */
    public String getAddTime() {
        return addTime;
    }

    /**
     * @param addTime the addTime to set
     */
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    /**
     * @return the updOper
     */
    public String getUpdOper() {
        return updOper;
    }

    /**
     * @param updOper the updOper to set
     */
    public void setUpdOper(String updOper) {
        this.updOper = updOper;
    }

    /**
     * @return the updTime
     */
    public String getUpdTime() {
        return updTime;
    }

    /**
     * @param updTime the updTime to set
     */
    public void setUpdTime(String updTime) {
        this.updTime = updTime;
    }

    /**
     * @return the delOper
     */
    public String getDelOper() {
        return delOper;
    }

    /**
     * @param delOper the delOper to set
     */
    public void setDelOper(String delOper) {
        this.delOper = delOper;
    }

    /**
     * @return the delTime
     */
    public String getDelTime() {
        return delTime;
    }

    /**
     * @param delTime the delTime to set
     */
    public void setDelTime(String delTime) {
        this.delTime = delTime;
    }

    /**
     * @return the delFlag
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag the delFlag to set
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return the memberId
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the campaignId
     */
    public Integer getCampaignId() {
        return campaignId;
    }

    /**
     * @param campaignId the campaignId to set
     */
    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
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
     * @return the selfMoney
     */
    public BigDecimal getSelfMoney() {
        return selfMoney;
    }

    /**
     * @param selfMoney the selfMoney to set
     */
    public void setSelfMoney(BigDecimal selfMoney) {
        this.selfMoney = selfMoney;
    }

    /**
     * @return the agentMoney
     */
    public BigDecimal getAgentMoney() {
        return agentMoney;
    }

    /**
     * @param agentMoney the agentMoney to set
     */
    public void setAgentMoney(BigDecimal agentMoney) {
        this.agentMoney = agentMoney;
    }

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
     * @return the budgetDay
     */
    public BigDecimal getBudgetDay() {
        return budgetDay;
    }

    /**
     * @param budgetDay the budgetDay to set
     */
    public void setBudgetDay(BigDecimal budgetDay) {
        this.budgetDay = budgetDay;
    }

}
