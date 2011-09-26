package com.mitian.airad.model;

import java.math.BigDecimal;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;


import com.mitian.airad.common.constant.ConstantAd;
import com.mitian.airad.common.constant.ConstantAd.Flag;
import com.mitian.airad.common.utils.math.NumberUtils;
import com.mitian.airad.utils.ReportDateUtil;

/**
 * CoreAd.java 广告信息
 * 
 * @author wangzhongwei
 */
public class CoreAd extends BaseModel {

    /**
     * 判断广告是否为草稿状态
     * 
     * @return
     */
    public boolean isDarft() {
        return Flag.DRAFT.getCode().toString().equals(flag);
    }

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
     * 广告背景
     */
    private Integer background;
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
     * 广告类型
     */
    private String adType;
    /**
     * 子页面个数
     */
    private Integer adChildNum;
    /**
     * 审核/草稿标志
     */
    private String flag;
    /**
     * 冻结标志
     */
    private String blocking;
    /**
     * 添加人
     */
    private String addOper;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 更新人
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
     * 会员id
     */
    private Long memberId;
    /**
     * 活动id
     */
    private Integer campaignId;

    /** 点击数 */
    private Integer clickNum;
    /** 展示数 */
    private Integer showNum;
    /** 点击率 */
    private BigDecimal clickRate;
    /** 成本 */
    private BigDecimal cost;
    /** 平均点击成本 */
    private BigDecimal avgClick;
    /** 广告组名字 */
    private String adGroupName;
    /** 活动名字 */
    private String campaignName;
    /** 用户姓名 */
    private String email;

    /**
     * 冻结的广告数
     */
    private String blockingCount;
    /**
     * 发布的广告数
     */
    private String issuCount;
    /**
     * 暂停的广告数
     */
    private String suspendCount;
    /**
     * 草稿的广告数
     */
    private String draftCount;
    /**
     * 审核中的广告数
     */
    private String checkCount;
    /**
     * 审核通过的广告数
     */
    private String checkPassCount;
    /**
     * 审核不通过的广告数
     */
    private String checkNoCount;
    /**
     * 广告总数
     */
    private String adCount;
    /**
     * banner类型
     */
    private String bannerType;
    /**
     * 地域形式
     */
    private String adLoclType;
    /**
     * 活动开始时间
     */
    private Date startTime;
    /**
     * 每日预算
     */
//    private BigDecimal budgetDay;
    /**
     * 活动结束时间
     */
    private Date endTime;
    
    
 
    /**
     * 常用地区信息
     */
    private String adLoclInfo;
    
    
 
    /**
     * 开始
     */
    private Date adStartTime;
    /**
     * 结束
     */
    private Date adEndTime;
    /**
     * 日预算
     */
    private BigDecimal adBudgetDay;
    
    /**
     * 总预算
     */
    private BigDecimal adBudgetTotal;

    /**
     * 广告平台投放类型
     */
    private String platform;

    /**
     * 插入付费类型 1- CPC 按点击付费 2-CPM 按展示付费
     */
    private String paymentType;

    /**
     * @return the bannerType
     */
    public String getBannerType() {
        return bannerType;
    }

    /**
     * @param bannerType the bannerType to set
     */
    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    /**
     * @return the adStartTime
     */
    public Date getAdStartTime() {
        return adStartTime;
    }

    /**
     * @param adStartTime the adStartTime to set
     */
    public void setAdStartTime(Date adStartTime) {
        this.adStartTime = adStartTime;
    }

    /**
     * @return the adEndTime
     */
    public Date getAdEndTime() {
        return adEndTime;
    }

    /**
     * @param adEndTime the adEndTime to set
     */
    public void setAdEndTime(Date adEndTime) {
        this.adEndTime = adEndTime;
    }

    /**
     * @return the adbudgetDay
     */
    public BigDecimal getAdBudgetDay() {
        return adBudgetDay;
    }

    /**
     * @param adbudgetDay the adbudgetDay to set
     */
    public void setAdBudgetDay(BigDecimal adbudgetDay) {
        this.adBudgetDay = adbudgetDay;
    }

    /**
     * @return the adbudgetTotal
     */
    public BigDecimal getAdBudgetTotal() {
        return adBudgetTotal;
    }

    /**
     * @param adbudgetTotal the adgudgetTotal to set
     */
    public void setAdBudgetTotal(BigDecimal adbudgetTotal) {
        this.adBudgetTotal = adbudgetTotal;
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
     * @return the budgetDay
     */
//    public BigDecimal getbudgetDay() {
//        return budgetDay;
//    }
//
//    /**
//     * @param budgetDay the budgetDay to set
//     */
//    public void setbudgetDay(BigDecimal budgetDay) {
//        this.budgetDay = budgetDay;
//    }

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
     * @return the adLoclType
     */
    public String getAdLoclType() {
        return adLoclType;
    }

    /**
     * @param adLoclType the adLoclType to set
     */
    public void setAdLoclType(String adLoclType) {
        this.adLoclType = adLoclType;
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
     * @return the adType
     */
    public String getAdType() {
        return adType;
    }

    /**
     * @param adType the adType to set
     */
    public void setAdType(String adType) {
        this.adType = adType;
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
     * @param adChildNum the adChildNum to set
     */
    public void setAdChildNum(Integer adChildNum) {
        this.adChildNum = adChildNum;
    }

    /**
     * @return the adChildNum
     */
    public Integer getAdChildNum() {
        return adChildNum;
    }

    /**
     * @return the clickNum
     */
    public Integer getClickNum() {
        return NumberUtils.trimNullToZero(clickNum);
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
        return NumberUtils.trimNullToZero(showNum);
    }

    /**
     * @param showNum the showNum to set
     */
    public void setShowNum(Integer showNum) {
        this.showNum = showNum;
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
     * @return the cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * @return the avgClick
     */
    public BigDecimal getAvgClick() {
        return avgClick;
    }

    /**
     * @param avgClick the avgClick to set
     */
    public void setAvgClick(BigDecimal avgClick) {
        this.avgClick = avgClick;
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
     * @return the blockingCount
     */
    public String getBlockingCount() {
        return blockingCount;
    }

    /**
     * @param blockingCount the blockingCount to set
     */
    public void setBlockingCount(String blockingCount) {
        this.blockingCount = blockingCount;
    }

    /**
     * @return the issuCount
     */
    public String getIssuCount() {
        return issuCount;
    }

    /**
     * @param issuCount the issuCount to set
     */
    public void setIssuCount(String issuCount) {
        this.issuCount = issuCount;
    }

    /**
     * @return the suspendCount
     */
    public String getSuspendCount() {
        return suspendCount;
    }

    /**
     * @param suspendCount the suspendCount to set
     */
    public void setSuspendCount(String suspendCount) {
        this.suspendCount = suspendCount;
    }

    /**
     * @return the draftCount
     */
    public String getDraftCount() {
        return draftCount;
    }

    /**
     * @param draftCount the draftCount to set
     */
    public void setDraftCount(String draftCount) {
        this.draftCount = draftCount;
    }

    /**
     * @return the checkCount
     */
    public String getCheckCount() {
        return checkCount;
    }

    /**
     * @param checkCount the checkCount to set
     */
    public void setCheckCount(String checkCount) {
        this.checkCount = checkCount;
    }

    /**
     * @return the checkPassCount
     */
    public String getCheckPassCount() {
        return checkPassCount;
    }

    /**
     * @param checkPassCount the checkPassCount to set
     */
    public void setCheckPassCount(String checkPassCount) {
        this.checkPassCount = checkPassCount;
    }

    /**
     * @return the checkNoCount
     */
    public String getCheckNoCount() {
        return checkNoCount;
    }

    /**
     * @param checkNoCount the checkNoCount to set
     */
    public void setCheckNoCount(String checkNoCount) {
        this.checkNoCount = checkNoCount;
    }

    /**
     * @return the adCount
     */
    public String getAdCount() {
        return adCount;
    }

    /**
     * @param adCount the adCount to set
     */
    public void setAdCount(String adCount) {
        this.adCount = adCount;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(Integer background) {
        this.background = background;
    }

    /**
     * @return the background
     */
    public Integer getBackground() {
        return background;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    public String getPlatformShowName() {
        String name = "";
        if (StringUtils.isNotBlank(platform)) {
            if (platform.contains(ConstantAd.PLATFORM_ANDROID)) {
                name = name.concat("android ");
            }
            if (platform.contains(ConstantAd.PLATFORM_IPHONE)) {
                name = name.concat("iphone");
            }
        }
        return name;
    }

    /**
     * @param platform the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * 后台冻结广告
     * 
     * @return
     */
    public boolean isAdBlocking() {
        return "1".equals(blocking);
    }

    /**
     * 前台用户手动暂停广告
     * 
     * @return
     */
    public boolean isSuspend() {
        return "1".equals(suspendType);
    }

    public String getFlagShowName() {
        if (ConstantAd.Flag.DRAFT.getCode().toString().equals(flag)) {
            return ConstantAd.Flag.DRAFT.getDes();
        }
        else if (ConstantAd.Flag.CHECK.getCode().toString().equals(flag)) {
            return ConstantAd.Flag.CHECK.getDes();
        }
        else if (ConstantAd.Flag.CHECKED.getCode().toString().equals(flag)) {
            return ConstantAd.Flag.CHECKED.getDes();
        }
        else if (ConstantAd.Flag.CHECK_FAIL.getCode().toString().equals(flag)) {
            return ConstantAd.Flag.CHECK_FAIL.getDes();
        }
        else if (ConstantAd.Flag.INSERVICE.getCode().toString().equals(flag)) {
            return ConstantAd.Flag.INSERVICE.getDes();
        }
        else {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 用于前台广告列表显示广告状态
     */
    public String getStatusShowName() {
        if (isAdBlocking()) {
            return "<span class=\"orange\">冻结</span>";
        }
        else {
            if (isSuspend()) {
                return "<span class=\"red\">停用</span>";
            }
            else if (ConstantAd.Flag.INSERVICE.getCode().toString().equals(flag)) {
                return getFlagShowName();
            }
            else {
                return "<span class=\"red\">" + getFlagShowName() + "</span>";
            }
        }
    }

    /**
     * @return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    
    public boolean getIsTime() throws ParseException{
        boolean t= false;
        
        try {
            t=this.addTime.getTime()<=ReportDateUtil.getDate("yyyyMMdd","20110923").getTime();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
           // e.printStackTrace();
        }
        
        return t;
    }

}
