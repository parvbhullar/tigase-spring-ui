/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.form;

import java.util.ArrayList;
import java.util.List;

import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.model.AccountInfoView;
import com.mitian.airad.model.CoreAgentRelation;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.model.StatAgentView;

/**
 * AgentRelationForm.java
 * 
 * @author wangzhongwei
 */
public class AgentRelationForm extends AbstractForm {
    /**
     * 关系流水号
     */
    private String relationId;
    /**
     * 会员id
     */
    private String memberId;
    /**
     * 广告商ID
     */
    private String advertiserNum;

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
     * 关系对象
     */
    private CoreAgentRelation coreAgentRelation = new CoreAgentRelation();
    private List<CoreAgentRelation> coreAgentRelationList = new ArrayList<CoreAgentRelation>();
    private List<StatAgentView> statAgentViewList = new ArrayList<StatAgentView>();
    private List<AccountInfoView> listBean = new ArrayList<AccountInfoView>();
    private List<CoreMemberInfo> memberInfoList = new ArrayList<CoreMemberInfo>();
    /** 是否根据时间段查找收益标示 */
    private String timeSlotFlag;
    private String startTime;
    private String endTime;

    /**
     * @return the relationId
     */
    public String getRelationId() {
        return relationId;
    }

    /**
     * @param relationId the relationId to set
     */
    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    /**
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the advertiserNum
     */
    public String getAdvertiserNum() {
        return advertiserNum;
    }

    /**
     * @param advertiserNum the advertiserNum to set
     */
    public void setAdvertiserNum(String advertiserNum) {
        this.advertiserNum = advertiserNum;
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

    /**
     * @return the coreAgentRelation
     */
    public CoreAgentRelation getCoreAgentRelation() {
        return coreAgentRelation;
    }

    /**
     * @param coreAgentRelation the coreAgentRelation to set
     */
    public void setCoreAgentRelation(CoreAgentRelation coreAgentRelation) {
        this.coreAgentRelation = coreAgentRelation;
    }

    /**
     * @return the coreAgentRelationList
     */
    public List<CoreAgentRelation> getCoreAgentRelationList() {
        return coreAgentRelationList;
    }

    /**
     * @param coreAgentRelationList the coreAgentRelationList to set
     */
    public void setCoreAgentRelationList(List<CoreAgentRelation> coreAgentRelationList) {
        this.coreAgentRelationList = coreAgentRelationList;
    }

    /**
     * @return the statAgentViewList
     */
    public List<StatAgentView> getStatAgentViewList() {
        return statAgentViewList;
    }

    /**
     * @param statAgentViewList the statAgentViewList to set
     */
    public void setStatAgentViewList(List<StatAgentView> statAgentViewList) {
        this.statAgentViewList = statAgentViewList;
    }

    /**
     * @return the listBean
     */
    public List<AccountInfoView> getListBean() {
        return listBean;
    }

    /**
     * @param listBean the listBean to set
     */
    public void setListBean(List<AccountInfoView> listBean) {
        this.listBean = listBean;
    }

    /**
     * @return the memberInfoList
     */
    public List<CoreMemberInfo> getMemberInfoList() {
        return memberInfoList;
    }

    /**
     * @param memberInfoList the memberInfoList to set
     */
    public void setMemberInfoList(List<CoreMemberInfo> memberInfoList) {
        this.memberInfoList = memberInfoList;
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

    @Override
    public void form2domain() {
        if (StringUtils.isNotBlank(relationId)) {
            coreAgentRelation.setRelationId(Integer.parseInt(relationId));
        }
        if (StringUtils.isNotBlank(memberId)) {
            coreAgentRelation.setRelationId(Integer.parseInt(memberId));
        }
        if (StringUtils.isNotBlank(advertiserNum)) {
            coreAgentRelation.setRelationId(Integer.parseInt(advertiserNum));
        }
        if (StringUtils.isNotBlank(email)) {
            coreAgentRelation.setEmail(email);
        }
    }

}
