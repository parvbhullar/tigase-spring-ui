package com.mitian.airad.web.form;

import java.util.ArrayList;
import java.util.List;

import com.mitian.airad.model.CoreReport;
import com.mitian.airad.model.StatAppView;

/**
 * ReportForm.java
 * 
 * @author baojun
 */
public class ReportForm extends AbstractForm {

    /** 搜索活动名称 */
    private String campaignName;
    /** 搜索应用名称 */
    private String appName;
    /** 搜索广告商名称 */
    private String agentName;
    /** 报表类型 */
    private CoreReport report = new CoreReport();
    /** 列表 */
    private List reportList = new ArrayList();
    /** 选择范围 */
    private String presetValue;
    /** 选择内容 */
    private List<CoreReport> listBean = new ArrayList<CoreReport>();
    /** 选择内容的ID */
    private String[] checkList;
    /** 报告开始时间 */
    private String reportStartTime;
    /** 报告结束时间 */
    private String reportEndTime;
    /** 报表删除Id集合 */
    private List<Integer> deleteList = new ArrayList<Integer>();
    /** 修改报表的Id */
    private String reportId;
    /** 判断报表类型（活动0、应用1、广告商2 */
    private String flag;
    /** 0代表使用了快捷方式选择时间范围1代表没有 */
    private String dateType;
    /** 统计列表 */
    private List<StatAppView> statAppView = new ArrayList<StatAppView>();
    private String dateFlag;
    /** 修改报错跳转标示 */
    private String editFlag;
    /** 取消标示 */
    private String cancleFlag;
    /** 添加报告名字错误 */
    private String addReortError;
    private String nS;

    /**
     * 广告ID
     */
    private String adId;

    /**
     * @param statAppView the statAppView to set
     */
    public void setStatAppView(List<StatAppView> statAppView) {
        this.statAppView = statAppView;
    }

    /**
     * @return the statAppView
     */
    public List<StatAppView> getStatAppView() {
        return statAppView;
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
     * @return the report
     */
    public CoreReport getReport() {
        return report;
    }

    /**
     * @param report the report to set
     */
    public void setReport(CoreReport report) {
        this.report = report;
    }

    /**
     * @return the reportList
     */
    public List getReportList() {
        return reportList;
    }

    /**
     * @param reportList the reportList to set
     */
    public void setReportList(List reportList) {
        this.reportList = reportList;
    }

    /**
     * @return the presetValue
     */
    public String getPresetValue() {
        return presetValue;
    }

    /**
     * @param presetValue the presetValue to set
     */
    public void setPresetValue(String presetValue) {
        this.presetValue = presetValue;
    }

    /**
     * @return the listBean
     */
    public List<CoreReport> getListBean() {
        return listBean;
    }

    /**
     * @param listBean the listBean to set
     */
    public void setListBean(List<CoreReport> listBean) {
        this.listBean = listBean;
    }

    /**
     * @return the reportStartTime
     */
    public String getReportStartTime() {
        return reportStartTime;
    }

    /**
     * @param reportStartTime the reportStartTime to set
     */
    public void setReportStartTime(String reportStartTime) {
        this.reportStartTime = reportStartTime;
    }

    /**
     * @return the reportEndTime
     */
    public String getReportEndTime() {
        return reportEndTime;
    }

    /**
     * @param reportEndTime the reportEndTime to set
     */
    public void setReportEndTime(String reportEndTime) {
        this.reportEndTime = reportEndTime;
    }

    /**
     * @return the deleteList
     */
    public List<Integer> getDeleteList() {
        return deleteList;
    }

    /**
     * @param deleteList the deleteList to set
     */
    public void setDeleteList(List<Integer> deleteList) {
        this.deleteList = deleteList;
    }

    /**
     * @return the reportId
     */
    public String getReportId() {
        return reportId;
    }

    /**
     * @param reportId the reportId to set
     */
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    /**
     * @return the checkList
     */
    public String[] getCheckList() {
        return checkList;
    }

    /**
     * @param checkList the checkList to set
     */
    public void setCheckList(String[] checkList) {
        this.checkList = checkList;
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
     * @return the agentName
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * @param agentName the agentName to set
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * @return the dateType
     */
    public String getDateType() {
        return dateType;
    }

    /**
     * @param dateType the dateType to set
     */
    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    /**
     * @return the dateFlag
     */
    public String getDateFlag() {
        return dateFlag;
    }

    /**
     * @param dateFlag the dateFlag to set
     */
    public void setDateFlag(String dateFlag) {
        this.dateFlag = dateFlag;
    }

    /**
     * @return the editFlag
     */
    public String getEditFlag() {
        return editFlag;
    }

    /**
     * @param editFlag the editFlag to set
     */
    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }

    /**
     * @return the cancleFlag
     */
    public String getCancleFlag() {
        return cancleFlag;
    }

    /**
     * @param cancleFlag the cancleFlag to set
     */
    public void setCancleFlag(String cancleFlag) {
        this.cancleFlag = cancleFlag;
    }

    /**
     * @return the addReortError
     */
    public String getAddReortError() {
        return addReortError;
    }

    /**
     * @param addReortError the addReortError to set
     */
    public void setAddReortError(String addReortError) {
        this.addReortError = addReortError;
    }

    /**
     * @return the nS
     */
    public String getnS() {
        return nS;
    }

    /**
     * @param nS the nS to set
     */
    public void setnS(String nS) {
        this.nS = nS;
    }

    @Override
    public void form2domain() {

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
}
