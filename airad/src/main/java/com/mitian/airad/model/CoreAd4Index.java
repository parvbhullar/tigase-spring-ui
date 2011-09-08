package com.mitian.airad.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 包括广告的主要信息，用于索引数据使用
 */
public class CoreAd4Index {
    private Integer adId;
    private String adName;
    private BigDecimal adOffer;
    private String delFlag;
    private String adTagSex;
    private String adTagAge;
    private String adTagSoftType;
    private String adTagSp;
    private String geographicalPosition;
    private Date startTime;
    private Date endTime;
    private Date updTime;
    //
    private Integer adOfferInteger;

    /**
     * @return the adOfferInteger
     */
    public Integer getAdOfferInteger() {
        return adOfferInteger;
    }

    /**
     * @param adOfferInteger the adOfferInteger to set
     */
    public void setAdOfferInteger(Integer adOfferInteger) {
        this.adOfferInteger = adOfferInteger;
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
     * @return the adTagSex
     */
    public String getAdTagSex() {
        return adTagSex;
    }

    /**
     * @param adTagSex the adTagSex to set
     */
    public void setAdTagSex(String adTagSex) {
        this.adTagSex = adTagSex;
    }

    /**
     * @return the adTagAge
     */
    public String getAdTagAge() {
        return adTagAge;
    }

    /**
     * @param adTagAge the adTagAge to set
     */
    public void setAdTagAge(String adTagAge) {
        this.adTagAge = adTagAge;
    }

    /**
     * @return the adTagSoftType
     */
    public String getAdTagSoftType() {
        return adTagSoftType;
    }

    /**
     * @param adTagSoftType the adTagSoftType to set
     */
    public void setAdTagSoftType(String adTagSoftType) {
        this.adTagSoftType = adTagSoftType;
    }

    /**
     * @return the adTagSp
     */
    public String getAdTagSp() {
        return adTagSp;
    }

    /**
     * @param adTagSp the adTagSp to set
     */
    public void setAdTagSp(String adTagSp) {
        this.adTagSp = adTagSp;
    }

    /**
     * @return the geographicalPosition
     */
    public String getGeographicalPosition() {
        return geographicalPosition;
    }

    /**
     * @param geographicalPosition the geographicalPosition to set
     */
    public void setGeographicalPosition(String geographicalPosition) {
        this.geographicalPosition = geographicalPosition;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the updTime
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * @param updTime the updTime to set
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
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
}
