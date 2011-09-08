package com.mitian.airad.web.form;

import java.util.ArrayList;
import java.util.List;

import com.mitian.airad.model.AccountInfoView;
import com.mitian.airad.model.CoreAd;
import com.mitian.airad.model.CoreAdGroup;
import com.mitian.airad.model.CoreApp;
import com.mitian.airad.model.CoreCampaign;
import com.mitian.airad.model.CoreReport;
import com.mitian.airad.model.CoreUserHis;

public class PersonalForm extends AbstractForm {

    /**
     * 广告总展示次数
     */
    private Integer adShowCount;

    /**
     * 广告内容预览请求路径
     */
    private String adPreviewUrl;

    /**
     * 广告banner预览请求路径
     */
    private String bannerPreviewUrl;

    private List<CoreAd> adList = new ArrayList<CoreAd>();
    /** 应用活动列表 */
    private List<CoreApp> appList = new ArrayList<CoreApp>();
    private List<CoreAdGroup> groupList = new ArrayList<CoreAdGroup>();
    private List<CoreCampaign> campaignList = new ArrayList<CoreCampaign>();
    private List<CoreReport> reportList = new ArrayList<CoreReport>();
    // 开发者帐户信息
    private AccountInfoView accountInfoView = new AccountInfoView();

    // 登录历史
    private CoreUserHis userHis = new CoreUserHis();

    /**
     * @param userHis the userHis to set
     */
    public void setUserHis(CoreUserHis userHis) {
        this.userHis = userHis;
    }

    /**
     * @return the userHis
     */
    public CoreUserHis getUserHis() {
        return userHis;
    }

    /**
     * @return the accountInfoView
     */
    public AccountInfoView getAccountInfoView() {
        return accountInfoView;
    }

    /**
     * @param accountInfoView the accountInfoView to set
     */
    public void setAccountInfoView(AccountInfoView accountInfoView) {
        this.accountInfoView = accountInfoView;
    }

    /**
     * @return the adList
     */
    public List<CoreAd> getAdList() {
        return adList;
    }

    /**
     * @param adList the adList to set
     */
    public void setAdList(List<CoreAd> adList) {
        this.adList = adList;
    }

    /**
     * @return the appList
     */
    public List<CoreApp> getAppList() {
        return appList;
    }

    /**
     * @param appList the appList to set
     */
    public void setAppList(List<CoreApp> appList) {
        this.appList = appList;
    }

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
     * @return the campaignList
     */
    public List<CoreCampaign> getCampaignList() {
        return campaignList;
    }

    /**
     * @param campaignList the campaignList to set
     */
    public void setCampaignList(List<CoreCampaign> campaignList) {
        this.campaignList = campaignList;
    }

    /**
     * @return the reportList
     */
    public List<CoreReport> getReportList() {
        return reportList;
    }

    /**
     * @param reportList the reportList to set
     */
    public void setReportList(List<CoreReport> reportList) {
        this.reportList = reportList;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.form.AbstractForm#form2domain()
     */
    @Override
    public void form2domain() {

    }

    /**
     * @return the adShowCount
     */
    public Integer getAdShowCount() {
        return adShowCount;
    }

    /**
     * @param adShowCount the adShowCount to set
     */
    public void setAdShowCount(Integer adShowCount) {
        this.adShowCount = adShowCount;
    }

    /**
     * @return the adPreviewUrl
     */
    public String getAdPreviewUrl() {
        return adPreviewUrl;
    }

    /**
     * @param adPreviewUrl the adPreviewUrl to set
     */
    public void setAdPreviewUrl(String adPreviewUrl) {
        this.adPreviewUrl = adPreviewUrl;
    }

    /**
     * @return the bannerPreviewUrl
     */
    public String getBannerPreviewUrl() {
        return bannerPreviewUrl;
    }

    /**
     * @param bannerPreviewUrl the bannerPreviewUrl to set
     */
    public void setBannerPreviewUrl(String bannerPreviewUrl) {
        this.bannerPreviewUrl = bannerPreviewUrl;
    }

}
