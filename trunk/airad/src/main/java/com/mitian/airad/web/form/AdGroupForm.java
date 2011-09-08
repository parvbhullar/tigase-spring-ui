/*
 * Copyright 2011 mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mitian.airad.model.CoreAdGroup;
import com.mitian.airad.model.CoreAdvertiser;
import com.mitian.airad.model.Dictionary;
import com.mitian.airad.model.DictionaryGlobalRegion;

/**
 * AdGroupForm.java
 * 
 * @author leifenghai
 */
public class AdGroupForm extends AbstractForm {

    /** 广告组id */
    private String adGroupId;

    /** 广告活动id */
    private String campaignId;

    /** 广告组名称 */
    private String adGroupName;

    /** 目标广告类型 */
    private String adTagType;

    /** 目标地理位置信息 */
    private String adLoclInfo;

    /** 目标流量信息 */
    private String adFlowInfo;

    /** 运营商信息 */
    private String changceInfo;

    /** 目标人群性别 */
    private String adTagSex;

    /** 目标人群年龄段 */
    private String adTagAge;

    /** 目标投放软件类型 */
    private String adTagSoftType;

    /** 目标设备平台 */
    private String adTagSp;

    /** 审核/草稿标志 */
    private String flag;

    /** 冻结标志 */
    private String blocking;

    /** 添加人 */
    private String addOper;

    /** 添加时间 */
    private Date addTime;

    /** 更新人 */
    private String updOper;

    /** 更新时间 */
    private Date updTime;

    /** 删除人 */
    private String delOper;

    /** 删除时间 */
    private Date delTime;

    /** 删除标记 */
    private String delFlag;

    /** 暂停标记 */
    private String suspendType;

    /** 会员id */
    private Long memberId;

    /** 开始时间 */
    private String startTime;
    /** 结束时间 */
    private String endTime;
    /** 广告列表 */
    private List<CoreAdvertiser> coreAdvertisers;
    /** 广告活动名称 */
    private String campaignName;
    private List<CoreAdGroup> groupList = new ArrayList<CoreAdGroup>();
    /** 复制暂时用的广告组Id */
    private String adGroupIdTemp;
    /** 复制暂时用的广告组名称 */
    private String adGroupNameTemp;
    /** 复制暂时用的广告组名称修改 */
    private String adGroupNameCopyTemp;
    /** 查询所有省 */
    private List<DictionaryGlobalRegion> proListBean = new ArrayList<DictionaryGlobalRegion>();
    /** 省Id */
    private String proId;
    /** 精确选择的省、市、区ID */
    private String exact;
    /** 修改出错标示 */
    private String editErrorFlag;
    /** 是否根据时间段查找收益标示 */
    private String timeSlotFlag;
    /** 判断是否第一次进入增加页面 */
    private String addFlag;
    /** 所属行业集合 */
    private List<Dictionary> industryInvolved = new ArrayList<Dictionary>();
    /** 判断是否是进入修改页面 */
    private String editFlagCheck;
    /** 平台 */
    private List<Dictionary> arr = new ArrayList<Dictionary>();

    /**
     * @return the groupList
     */
    public List<CoreAdGroup> getGroupList() {
        return groupList;
    }

    /**
     * @param groupList the groupList to set
     */
    public void setGroupList(List<CoreAdGroup> groupList) {
        this.groupList = groupList;
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
     * @return the adGroupName
     */
    public String getAdGroupName() {
        return adGroupName;
    }

    /**
     * @param adGroupName the adGroupName to set
     */
    public void setAdGroupName(String adGroupName) {
        this.adGroupName = adGroupName;
    }

    /**
     * @return the adTagType
     */
    public String getAdTagType() {
        return adTagType;
    }

    /**
     * @param adTagType the adTagType to set
     */
    public void setAdTagType(String adTagType) {
        this.adTagType = adTagType;
    }

    /**
     * @return the adLoclInfo
     */
    public String getAdLoclInfo() {
        return adLoclInfo;
    }

    /**
     * @param adLoclInfo the adLoclInfo to set
     */
    public void setAdLoclInfo(String adLoclInfo) {
        this.adLoclInfo = adLoclInfo;
    }

    /**
     * @return the adFlowInfo
     */
    public String getAdFlowInfo() {
        return adFlowInfo;
    }

    /**
     * @param adFlowInfo the adFlowInfo to set
     */
    public void setAdFlowInfo(String adFlowInfo) {
        this.adFlowInfo = adFlowInfo;
    }

    /**
     * @return the changceInfo
     */
    public String getChangceInfo() {
        return changceInfo;
    }

    /**
     * @param changceInfo the changceInfo to set
     */
    public void setChangceInfo(String changceInfo) {
        this.changceInfo = changceInfo;
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
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @return the blocking
     */
    public String getBlocking() {
        return blocking;
    }

    /**
     * @param blocking the blocking to set
     */
    public void setBlocking(String blocking) {
        this.blocking = blocking;
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
     * @return the coreAdvertisers
     */
    public List<CoreAdvertiser> getCoreAdvertisers() {
        return coreAdvertisers;
    }

    /**
     * @param coreAdvertisers the coreAdvertisers to set
     */
    public void setCoreAdvertisers(List<CoreAdvertiser> coreAdvertisers) {
        this.coreAdvertisers = coreAdvertisers;
    }

    /**
     * @return the campaignName
     */
    public String getCampaignName() {
        return campaignName;
    }

    /**
     * @param campaignName the campaignName to set
     */
    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    private CoreAdGroup coreAdGroup = new CoreAdGroup();

    /**
     * @return the coreAdGroup
     */
    public CoreAdGroup getCoreAdGroup() {
        return coreAdGroup;
    }

    /**
     * @param coreAdGroup the coreAdGroup to set
     */
    public void setCoreAdGroup(CoreAdGroup coreAdGroup) {
        this.coreAdGroup = coreAdGroup;
    }

    /**
     * @return the adGroupIdTemp
     */
    public String getAdGroupIdTemp() {
        return adGroupIdTemp;
    }

    /**
     * @param adGroupIdTemp the adGroupIdTemp to set
     */
    public void setAdGroupIdTemp(String adGroupIdTemp) {
        this.adGroupIdTemp = adGroupIdTemp;
    }

    /**
     * @return the adGroupNameTemp
     */
    public String getAdGroupNameTemp() {
        return adGroupNameTemp;
    }

    /**
     * @param adGroupNameTemp the adGroupNameTemp to set
     */
    public void setAdGroupNameTemp(String adGroupNameTemp) {
        this.adGroupNameTemp = adGroupNameTemp;
    }

    /**
     * @return the adGroupNameCopyTemp
     */
    public String getAdGroupNameCopyTemp() {
        return adGroupNameCopyTemp;
    }

    /**
     * @param adGroupNameCopyTemp the adGroupNameCopyTemp to set
     */
    public void setAdGroupNameCopyTemp(String adGroupNameCopyTemp) {
        this.adGroupNameCopyTemp = adGroupNameCopyTemp;
    }

    /**
     * @return the proListBean
     */
    public List<DictionaryGlobalRegion> getProListBean() {
        return proListBean;
    }

    /**
     * @param proListBean the proListBean to set
     */
    public void setProListBean(List<DictionaryGlobalRegion> proListBean) {
        this.proListBean = proListBean;
    }

    /**
     * @return the proId
     */
    public String getProId() {
        return proId;
    }

    /**
     * @param proId the proId to set
     */
    public void setProId(String proId) {
        this.proId = proId;
    }

    /**
     * @return the exact
     */
    public String getExact() {
        return exact;
    }

    /**
     * @param exact the exact to set
     */
    public void setExact(String exact) {
        this.exact = exact;
    }

    /**
     * @return the editErrorFlag
     */
    public String getEditErrorFlag() {
        return editErrorFlag;
    }

    /**
     * @param editErrorFlag the editErrorFlag to set
     */
    public void setEditErrorFlag(String editErrorFlag) {
        this.editErrorFlag = editErrorFlag;
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

    /**
     * @return the addFlag
     */
    public String getAddFlag() {
        return addFlag;
    }

    /**
     * @param addFlag the addFlag to set
     */
    public void setAddFlag(String addFlag) {
        this.addFlag = addFlag;
    }

    /**
     * @return the industryInvolved
     */
    public List<Dictionary> getIndustryInvolved() {
        return industryInvolved;
    }

    /**
     * @param industryInvolved the industryInvolved to set
     */
    public void setIndustryInvolved(List<Dictionary> industryInvolved) {
        this.industryInvolved = industryInvolved;
    }

    /**
     * @return the editFlagCheck
     */
    public String getEditFlagCheck() {
        return editFlagCheck;
    }

    /**
     * @param editFlagCheck the editFlagCheck to set
     */
    public void setEditFlagCheck(String editFlagCheck) {
        this.editFlagCheck = editFlagCheck;
    }

    /**
     * @return the arr
     */
    public List<Dictionary> getArr() {
        return arr;
    }

    /**
     * @param arr the arr to set
     */
    public void setArr(List<Dictionary> arr) {
        this.arr = arr;
    }

    @Override
    public void form2domain() {
        if (StringUtils.isNotBlank(adGroupId)) {
            coreAdGroup.setAdGroupId(Integer.valueOf(adGroupId));
        }
        if (StringUtils.isNotBlank(campaignId)) {
            coreAdGroup.setCampaignId(Integer.valueOf(campaignId));
        }
    }

}
