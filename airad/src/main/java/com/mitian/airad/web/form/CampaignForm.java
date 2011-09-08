package com.mitian.airad.web.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mitian.airad.model.CoreCampaign;

public class CampaignForm extends AbstractForm {

    /** 活动对象 */
    private CoreCampaign campaign = new CoreCampaign();
    /** 开始时间 YYYY-MM-dd */
    private String startTime;
    /** 每日预算 */
    private String buggetDay;
    /** 活动Id */
    private String campaignId;
    /** 结束时间 YYYY-MM-dd */
    private String endTime;
    /** 开始时间 HH */
    private String startHour;
    /** 开始时间 mm */
    private String startMin;
    /** 结束时间 HH */
    private String endHour;
    /** 结束时间 mm */
    private String endMin;
    /** 00-23小时集合 */
    @SuppressWarnings("unchecked")
    private List hourList = new ArrayList();
    /** 活动结合 */
    private List<CoreCampaign> listBean = new ArrayList<CoreCampaign>();
    /** 是否根据时间段查找收益标示 */
    private String timeSlotFlag;
    /** 是否有结束时间标示 */
    private String timeFlag;
    /** 修改出错标示 */
    private String eidtErrorFlag;
    /** 0，根据campaignId查找广告（统计）、1根据adGroupId查找广告（统计） */
    private String type;

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
     * @return the campaign
     */
    public CoreCampaign getCampaign() {
        return campaign;
    }

    /**
     * @param campaign the campaign to set
     */
    public void setCampaign(CoreCampaign campaign) {
        this.campaign = campaign;
    }

    /**
     * @return the buggetDay
     */
    public String getBuggetDay() {
        return buggetDay;
    }

    /**
     * @param buggetDay the buggetDay to set
     */
    public void setBuggetDay(String buggetDay) {
        this.buggetDay = buggetDay;
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
     * @return the startHour
     */
    public String getStartHour() {
        return startHour;
    }

    /**
     * @param startHour the startHour to set
     */
    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    /**
     * @return the startMin
     */
    public String getStartMin() {
        return startMin;
    }

    /**
     * @param startMin the startMin to set
     */
    public void setStartMin(String startMin) {
        this.startMin = startMin;
    }

    /**
     * @return the endHour
     */
    public String getEndHour() {
        return endHour;
    }

    /**
     * @param endHour the endHour to set
     */
    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    /**
     * @return the endMin
     */
    public String getEndMin() {
        return endMin;
    }

    /**
     * @param endMin the endMin to set
     */
    public void setEndMin(String endMin) {
        this.endMin = endMin;
    }

    /**
     * @return the hourList
     */
    public List getHourList() {
        return hourList;
    }

    /**
     * @param hourList the hourList to set
     */
    public void setHourList(List hourList) {
        this.hourList = hourList;
    }

    /**
     * @return the listBean
     */
    public List<CoreCampaign> getListBean() {
        return listBean;
    }

    /**
     * @param listBean the listBean to set
     */
    public void setListBean(List<CoreCampaign> listBean) {
        this.listBean = listBean;
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
     * @return the timeFlag
     */
    public String getTimeFlag() {
        return timeFlag;
    }

    /**
     * @param timeFlag the timeFlag to set
     */
    public void setTimeFlag(String timeFlag) {
        this.timeFlag = timeFlag;
    }

    /**
     * @return the eidtErrorFlag
     */
    public String getEidtErrorFlag() {
        return eidtErrorFlag;
    }

    /**
     * @param eidtErrorFlag the eidtErrorFlag to set
     */
    public void setEidtErrorFlag(String eidtErrorFlag) {
        this.eidtErrorFlag = eidtErrorFlag;
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

    @Override
    public void form2domain() {

        // if (StringUtils.isNotBlank(buggetDay)) {
        // campaign.setBuggetDay(Long.parseLong(buggetDay));
        // }
        try {
            if (StringUtils.isNotBlank(startTime)) {
                SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                campaign.setStartTime(form.parse(startTime + " " + startHour + ":" + startMin));
            }
            if (StringUtils.isNotBlank(endTime)) {
                SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                campaign.setEndTime(form.parse(endTime + " " + endHour + ":" + endMin));
            }
        }
        catch (Exception e) {
        }
    }
}
