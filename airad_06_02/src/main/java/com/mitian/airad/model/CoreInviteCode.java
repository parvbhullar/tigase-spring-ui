package com.mitian.airad.model;

import java.util.Date;

/**
 * 会员注册邀请信息表 CoreInviteCode.java
 * 
 * @author Administrator
 */
public class CoreInviteCode {

    /**
     * 邀请信息id
     */
    private Integer codeId;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 邀请码
     */
    private String code;
    /**
     * 邀请会员类型
     */
    private String type;
    /**
     * 添加人
     */
    private String addOper;
    /**
     *添加时间
     */
    private Date addTime;
    /**
     *更新人
     */
    private String updOper;
    /**
     * 更新时间
     */
    private Date updTime;
    /**
     * 删除人
     */
    private String delOper;
    /**
     * 删除时间
     */
    private Date delTime;
    /**
     * 删除标记
     */
    private String delFlag;
    /**
     * 状态
     */
    private String inviteStatus;

    /**
     * 使用此链接注册的用户
     */
    private Integer inviteMemberId;

    /**
     * @return the codeId
     */
    public Integer getCodeId() {
        return codeId;
    }

    /**
     * @return the inviteMemberId
     */
    public Integer getInviteMemberId() {
        return inviteMemberId;
    }

    /**
     * @param inviteMemberId the inviteMemberId to set
     */
    public void setInviteMemberId(Integer inviteMemberId) {
        this.inviteMemberId = inviteMemberId;
    }

    /**
     * @param codeId the codeId to set
     */
    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
     * @return the inviteStatus
     */
    public String getInviteStatus() {
        return inviteStatus;
    }

    /**
     * @param inviteStatus the inviteStatus to set
     */
    public void setInviteStatus(String inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

}
