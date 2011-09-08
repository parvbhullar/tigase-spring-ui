package com.mitian.airad.model;

import java.util.Date;

public class CoreAgentRelation {

    /**
     * 关系流水号
     */
    private Integer relationId;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 广告商ID
     */
    private Integer advertiserNum;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 添加人
     */
    private String addOper;
    /**
     * 修改时间
     */
    private Date updTime;
    /**
     *删除人
     */
    private String delOper;
    /**
     *删除时间
     */
    private Date delTime;
    /**
     *删除标志
     */
    private String delFlag;
    /**
     * 关系状态
     */
    private String relationStatus;
    /**
     * 广告商名称
     */
    private String email;
    /**
     * 身份类型
     */
    private String devType;

    /**
     * @return the relationId
     */
    public Integer getRelationId() {
        return relationId;
    }

    /**
     * @param relationId the relationId to set
     */
    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
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
     * @return the advertiserNum
     */
    public Integer getAdvertiserNum() {
        return advertiserNum;
    }

    /**
     * @param advertiserNum the advertiserNum to set
     */
    public void setAdvertiserNum(Integer advertiserNum) {
        this.advertiserNum = advertiserNum;
    }

    /**
     * @return the addTime
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime the addTime to set
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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
    public Date getDelTime() {
        return delTime;
    }

    /**
     * @param delTime the delTime to set
     */
    public void setDelTime(Date delTime) {
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
     * @return the relationStatus
     */
    public String getRelationStatus() {
        return relationStatus;
    }

    /**
     * @param relationStatus the relationStatus to set
     */
    public void setRelationStatus(String relationStatus) {
        this.relationStatus = relationStatus;
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
     * @return the devType
     */
    public String getDevType() {
        return devType;
    }

    /**
     * @param devType the devType to set
     */
    public void setDevType(String devType) {
        this.devType = devType;
    }

}
