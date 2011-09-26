package com.mitian.airad.web.form;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.mitian.airad.model.CoreAd;
import com.mitian.airad.model.CoreAdGroup;
import com.mitian.airad.model.CoreBanner;
import com.mitian.airad.model.CoreCampaign;
import com.mitian.airad.model.CoreRichAd;
import com.mitian.airad.model.CoreWapAd;
import com.mitian.airad.model.LibCallPhone;
import com.mitian.airad.model.LibMapInfo;
import com.mitian.airad.model.LibMarket;
import com.mitian.airad.model.LibMarketDetail;
import com.mitian.airad.model.LibPointInfo;
import com.mitian.airad.model.LibTaobao;
import com.mitian.airad.utils.ReportDateUtil;
import com.mitian.airad.utils.StringUtil;

/**
 * CoreAd.java 广告信息
 * 
 * @author wangzhongwei
 */
public class AdForm extends AbstractForm {
    /**
     * 广告id
     */
    private String adId;

    /**
     * 多个Id，以","分隔。例如:1,2,3
     */
    private String adIds;

    /**
     * 广告组id
     */
    private String adGroupId;
    private String adIsModifed;
    private String draftStatus;

    /**
     * @return the draftStatus
     */
    public String getDraftStatus() {
        return draftStatus;
    }

    /**
     * @param draftStatus the draftStatus to set
     */
    public void setDraftStatus(String draftStatus) {
        this.draftStatus = draftStatus;
    }

    /**
     * @return the adIsModifed
     */
    public String getAdIsModifed() {
        return adIsModifed;
    }

    /**
     * @param adIsModifed the adIsModifed to set
     */
    public void setAdIsModifed(String adIsModifed) {
        this.adIsModifed = adIsModifed;
    }
    private CoreAdGroup adGroup = new CoreAdGroup();
    private CoreCampaign campaign = new CoreCampaign();

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
    private String adOffer;
    /**
     * 广告背景图片
     */
    private String background;
    /**
     * 广告类型
     */
    private String adType;
    /**
     * 子页面个数
     */
    private String adChildNum;
    /**
     * 审核/草稿标志
     */
    private String flag;
    /**
     * 冻结标志
     */
    private String blocking;
    /**
     * 会员id
     */
    private String memberId;
    /**
     * 活动id
     */
    private String campaignId;

    private List<CoreAd> adList = new ArrayList<CoreAd>();
    public CoreAd coreAd = new CoreAd();
    // 时间段查询标示
    private String timeSlotFlag;
    private String startTime;
    private String endTime;
    /**
     * banner
     */
    private CoreBanner banner;// 

    private String bannerId;

    private String bannerType;// banner类型

    private String bannerColor;// banner颜色

    private String bannerBgType;// 背景图片类型

    private String bannerBgCon;// 获取值

    private String bannerText;// banner文字

    private String bannerFristText;//

    private String bannerSecondText;//

    private String bannerText2;// banner2文字

    private String bannerIconCon;// banner小图片
    private String bannerModelName;// banner小图片
    private String bannerHtml;// banner小图片
    private String bannerGrain;// banner小图片

    /**
     * wap
     */
    private CoreWapAd wap;
    private String wapId;
    private String wapTitle;
    private String wapContent;
    private String wapFax;
    private String wapEmail;
    private String wapQq;
    private String wapMsn;
    private String wapAddress;
    private String wapLinkText;
    private String wapLinkAddres;
    private String wapTelephone;
    /**
     * 富媒体
     */
    private CoreRichAd rich;
    private List<CoreRichAd> richList;
    private String richId;
    private String richMediaTitle;// 标题
    private String richMediaContent;// 内容
    private String formatType;// 类型
    private String showType;
    private String sort;
    private String relId;
    private String phone;

    /**
     * 保存地图信息
     */
    private LibMapInfo map;
    private String mapId;
    private String prosJson;
    private LibPointInfo libPointInfo;
    private String makJson;

    /**
     * 淘宝信息
     * 
     * @return
     */
    private String numIid;// 产品标识,保证和淘宝命名规范一致以便开发
    private String uiId;// 控件id
    /**
     * taobao2
     */
    private LibTaobao libTaobao;
    private LibCallPhone libCallPhone;
    private String taobaoUrl;
    private String taobaoId;

    /**
     * callphone
     */
    private String callPhoneId;
    private String callPhoneNumber;

    /** 每日预算 */
    private String adBudgetDay;
    /** 总预算 */
    private String adBudgetTotal;

    // ////

    /** 开始时间 YYYY-MM-dd */
    private String adStartTime;

    /** 结束时间 YYYY-MM-dd */
    private String adEndTime;
    /** 开始时间 HH */
    private String adStartHour;
    /** 开始时间 mm */
    private String adStartMin;
    /** 结束时间 HH */
    private String adEndHour;
    /** 结束时间 mm */
    private String adEndMin;
    /** 00-23小时集合 */
    // @SuppressWarnings("unchecked")
    // private List hourList = new ArrayList();

    /** 是否有结束时间标示 */
    private String timeFlag;

    // //////////

    /**
     * /**
     * 
     * @return the adStartTime
     */
    public String getAdStartTime() {
        return adStartTime;
    }

    /**
     * @param adStartTime the adStartTime to set
     */
    public void setAdStartTime(String adStartTime) {
        this.adStartTime = adStartTime;
    }

    /**
     * @return the adEndTime
     */
    public String getAdEndTime() {
        return adEndTime;
    }

    /**
     * @param adEndTime the adEndTime to set
     */
    public void setAdEndTime(String adEndTime) {
        this.adEndTime = adEndTime;
    }

    /**
     * @return the adStartHour
     */
    public String getAdStartHour() {
        return adStartHour;
    }

    /**
     * @param adStartHour the adStartHour to set
     */
    public void setAdStartHour(String adStartHour) {
        this.adStartHour = adStartHour;
    }

    /**
     * @return the adStartMin
     */
    public String getAdStartMin() {
        return adStartMin;
    }

    /**
     * @param adStartMin the adStartMin to set
     */
    public void setAdStartMin(String adStartMin) {
        this.adStartMin = adStartMin;
    }

    /**
     * @return the adEndHour
     */
    public String getAdEndHour() {
        return adEndHour;
    }

    /**
     * @param adEndHour the adEndHour to set
     */
    public void setAdEndHour(String adEndHour) {
        this.adEndHour = adEndHour;
    }

    /**
     * @return the adEndMin
     */
    public String getAdEndMin() {
        return adEndMin;
    }

    /**
     * @param adEndMin the adEndMin to set
     */
    public void setAdEndMin(String adEndMin) {
        this.adEndMin = adEndMin;
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
     * @return the callPhoneId
     */
    public String getCallPhoneId() {
        return callPhoneId;
    }

    /**
     * @param callPhoneId the callPhoneId to set
     */
    public void setCallPhoneId(String callPhoneId) {
        this.callPhoneId = callPhoneId;
    }

    /**
     * @return the callPhoneNumber
     */
    public String getCallPhoneNumber() {
        return callPhoneNumber;
    }

    /**
     * @param callPhoneNumber the callPhoneNumber to set
     */
    public void setCallPhoneNumber(String callPhoneNumber) {
        this.callPhoneNumber = callPhoneNumber;
    }
    /**
     * market
     */
    private LibMarket libMarket;
    private String marketId;
    private String marketImgId;
    private String marketName;
    private String marketAndroidId;
    private String marketIphoneId;
    private String marketAndroidUrl;
    private String marketIphoneUrl;
    private String marketType;
    private LibMarketDetail libMarketDetail;

    @Override
    public void form2domain() {
        if (StringUtils.isNotBlank(adGroupId)) {
            coreAd.setAdGroupId(Integer.valueOf(adGroupId));
        }
        if (StringUtils.isNotBlank(campaignId)) {
            coreAd.setCampaignId(Integer.valueOf(campaignId));
        }
        if (StringUtils.isNotBlank(adId)) {
            coreAd.setAdId(Integer.valueOf(adId));
        }
        if (StringUtils.isNotBlank(wapId)) {
            coreAd.setWapId(Integer.valueOf(wapId));
        }
        if (StringUtils.isNotBlank(richId)) {
            coreAd.setRichId(Integer.valueOf(richId));
        }
        if (StringUtils.isNotBlank(bannerId)) {
            coreAd.setBannerId(Integer.valueOf(bannerId));
        }
        if (StringUtils.isNotBlank(memberId)) {
            coreAd.setMemberId(Long.parseLong(memberId));
        }
        if (StringUtils.isNotBlank(adChildNum)) {
            coreAd.setAdChildNum(Integer.valueOf(adChildNum));
        }
        if (StringUtils.isNotBlank(adOffer)) {
            coreAd.setAdOffer(BigDecimal.valueOf(Double.parseDouble(adOffer)));
        }
        if (StringUtils.isNotBlank(flag)) {
            coreAd.setFlag(flag);
        }
        if (StringUtils.isNotBlank(background)) {
            coreAd.setBackground(Integer.valueOf(background));
        }
        coreAd.setAdType(adType);
        coreAd.setAdName(adName);

        // banner信息
        banner = new CoreBanner();
        if (StringUtils.isNotBlank(adId)) {
            banner.setAdId(Integer.valueOf(adId));
        }
        if (StringUtils.isNotBlank(bannerId)) {
            banner.setBannerId(Integer.valueOf(bannerId));
        }

        banner.setBannerType(bannerType);// 获取banner类型
        banner.setBannerBgType(bannerBgType);// 获取背景图片类型
        banner.setBannerBgCon(bannerBgCon);// 获背景值//如果是图片则是图片id
        banner.setBannerText(bannerText);// 文字信息或第一帧文字
        banner.setBannerText2(bannerText2);// 第二帧文字
        banner.setBannerIconCon(bannerIconCon);// 保存小图片信息
        banner.setBannerHtml(bannerHtml);
        banner.setBannerColor(bannerColor);
        banner.setBannerGrain(bannerGrain);
        banner.setBannerModelName(bannerModelName);

        // wap信息
        wap = new CoreWapAd();
        if (StringUtils.isNotBlank(adId)) {
            wap.setAdId(Integer.valueOf(adId));
        }
        if (StringUtils.isNotBlank(wapId)) {
            wap.setWapId(Integer.valueOf(wapId));
        }
        wap.setWapTitle(wapTitle);
        wap.setWapContent(wapContent);
        wap.setFax(wapFax);
        wap.setEmail(wapEmail);
        wap.setQq(wapQq);
        wap.setMsn(wapMsn);
        wap.setContactAddress(wapAddress);
        wap.setLinkText(wapLinkText);
        wap.setUrlAddress(wapLinkAddres);
        wap.setTelephone(wapTelephone);

        // 富媒体页面信息
        rich = new CoreRichAd();
        if (StringUtils.isNotBlank(adId)) {
            rich.setAdId(Integer.valueOf(adId));
        }
        if (StringUtils.isNotBlank(richId)) {
            rich.setRichId(Integer.valueOf(richId));
        }
        if (StringUtils.isNotBlank(formatType)) {
            rich.setFormatType(Integer.valueOf(formatType));
        }
        rich.setRichMediaTitle(richMediaTitle);
        rich.setShowType(showType);
        rich.setRichMediaContent(richMediaContent);
        rich.setPhone(phone);
        if (StringUtils.isNotBlank(sort)) {
            rich.setSort(Integer.valueOf(sort));
        }
        rich.setRelId(relId);
        // 地图信息
        map = new LibMapInfo();
        if (StringUtils.isNotBlank(richId)) {
            map.setRichId(Integer.valueOf(richId));
        }
        libPointInfo = new LibPointInfo();
        libPointInfo.setProsJson(makJson);
        map.setLibPointInfo(libPointInfo);// 组装点的信息
        map.setProsJson(getProsJson());// 装进去的是一组
        rich.setMap(map);// 将地图装入富媒体
        // 淘宝信息
        libTaobao = new LibTaobao();
        libTaobao.setTaobaoUrl(taobaoUrl);
        if (StringUtils.isNotBlank(taobaoId)) {
            libTaobao.setId(Integer.valueOf(taobaoId));
        }
        rich.setLibTaobao(libTaobao);

        // 直拨电话信息
        libCallPhone = new LibCallPhone();
        libCallPhone.setCallPhoneNumber(callPhoneNumber);
        if (StringUtils.isNotBlank(callPhoneId)) {
            libTaobao.setId(Integer.valueOf(callPhoneId));
        }
        rich.setLibCallPhone(libCallPhone);
        // 每日预算

        if (!StringUtils.isEmpty(adBudgetDay)) {
            coreAd.setAdBudgetDay(new BigDecimal(adBudgetDay));
        }

        if (!StringUtils.isEmpty(adBudgetTotal)) {
            coreAd.setAdBudgetTotal(new BigDecimal(adBudgetTotal));
        }

        if (!StringUtil.isEmpty(adStartTime)) {
            try {
                if (StringUtil.isEmpty(getAdStartHour())) {
                    // coreAd.setAdStartTime(ReportDateUtil.getDate("yyyy-MM-dd", getAdStartTime()+" " +
                    // getAdStartHour()+":" + getAdStartMin()));
                }
                else {
                    coreAd.setAdStartTime(ReportDateUtil.getDate("yyyy-MM-dd HH:mm", getAdStartTime() + " "
                            + getAdStartHour() + ":" + getAdStartMin()));
                }
            }
            catch (ParseException e) {
            //    e.printStackTrace();
            }
        }

        if (!StringUtil.isEmpty(adEndTime)) {
            try {
                if (StringUtil.isEmpty(getAdEndHour())) {

                }
                else {
                    coreAd.setAdEndTime(ReportDateUtil.getDate("yyyy-MM-dd HH:mm", getAdEndTime() + " "
                            + getAdEndHour() + ":" + getAdEndMin()));
                }
            }
            catch (ParseException e) {
              //  e.printStackTrace();
            }
        }
        // 2011-09-16

        // 总预算
        // 开始时间
        // 结束时间

        // market信息
        libMarket = new LibMarket();
        libMarketDetail = new LibMarketDetail();
        if (StringUtils.isNotBlank(getMarketId())) {
            libMarket.setId(Integer.valueOf(getMarketId()));
        }
        if (StringUtils.isNotBlank(marketImgId)) {
            libMarket.setMarketImg(Integer.valueOf(marketImgId));
        }
        libMarket.setMarketName(marketName);
        if (StringUtils.isNotBlank(marketType)) {
            int marketTy = Integer.valueOf(marketType);
            libMarketDetail.setMarketType(marketTy);
            List<LibMarketDetail> marketDetailList = new ArrayList<LibMarketDetail>();
            if (marketTy == 0) { // 支持所有平台
                LibMarketDetail libMarketDetailAndroid = new LibMarketDetail();
                if (StringUtils.isNotBlank(marketAndroidId)) {
                    libMarketDetailAndroid.setId(Integer.parseInt(marketAndroidId));
                }
                libMarketDetailAndroid.setMarketUrl(marketAndroidUrl);
                libMarketDetailAndroid.setMarketType(1);
                libMarketDetailAndroid.setStatus("1");
                LibMarketDetail libMarketDetailIphone = new LibMarketDetail();
                if (StringUtils.isNotBlank(marketIphoneId)) {
                    libMarketDetailIphone.setId(Integer.parseInt(marketIphoneId));
                }
                libMarketDetailIphone.setMarketUrl(marketIphoneUrl);
                libMarketDetailIphone.setMarketType(2);
                libMarketDetailIphone.setStatus("1");
                marketDetailList.add(libMarketDetailAndroid);
                marketDetailList.add(libMarketDetailIphone);
            }
            if (marketTy == 1) { // 支持android iphone失效
                LibMarketDetail libMarketDetailAndroid = new LibMarketDetail();
                if (StringUtils.isNotBlank(marketAndroidId)) {
                    libMarketDetailAndroid.setId(Integer.parseInt(marketAndroidId));
                }
                libMarketDetailAndroid.setMarketUrl(marketAndroidUrl);
                libMarketDetailAndroid.setMarketType(1);
                libMarketDetailAndroid.setStatus("1");
                marketDetailList.add(libMarketDetailAndroid);
                LibMarketDetail libMarketDetailIphone = new LibMarketDetail();
                if (StringUtils.isNotBlank(marketIphoneId)) {
                    libMarketDetailIphone.setId(Integer.parseInt(marketIphoneId));
                }
                libMarketDetailIphone.setMarketUrl(marketIphoneUrl);
                libMarketDetailIphone.setMarketType(2);
                libMarketDetailIphone.setStatus("0");
                marketDetailList.add(libMarketDetailIphone);
            }
            if (marketTy == 2) { // 支持iphone android失效
                LibMarketDetail libMarketDetailAndroid = new LibMarketDetail();
                if (StringUtils.isNotBlank(marketAndroidId)) {
                    libMarketDetailAndroid.setId(Integer.parseInt(marketAndroidId));
                }
                libMarketDetailAndroid.setMarketUrl(marketAndroidUrl);
                libMarketDetailAndroid.setMarketType(1);
                libMarketDetailAndroid.setStatus("0");
                marketDetailList.add(libMarketDetailAndroid);
                LibMarketDetail libMarketDetailIphone = new LibMarketDetail();
                if (StringUtils.isNotBlank(marketIphoneId)) {
                    libMarketDetailIphone.setId(Integer.parseInt(marketIphoneId));
                }
                libMarketDetailIphone.setMarketUrl(marketIphoneUrl);
                libMarketDetailIphone.setMarketType(2);
                libMarketDetailIphone.setStatus("1");
                marketDetailList.add(libMarketDetailIphone);
            }
            libMarket.setLibMarketDetailList(marketDetailList);
            rich.setLibMarket(libMarket);
        }

    }

    /**
     * @return the libCallPhone
     */
    public LibCallPhone getLibCallPhone() {
        return libCallPhone;
    }

    /**
     * @param libCallPhone the libCallPhone to set
     */
    public void setLibCallPhone(LibCallPhone libCallPhone) {
        this.libCallPhone = libCallPhone;
    }

    // 是否是不建议使用的banner（老banner类型）
    private boolean bannerTypeDeprecated;

    /**
     * @return the bannerTypeDeprecated
     */
    public boolean isBannerTypeDeprecated() {
        return bannerTypeDeprecated;
    }

    /**
     * @param bannerTypeDeprecated the bannerTypeDeprecated to set
     */
    public void setBannerTypeDeprecated(boolean bannerTypeDeprecated) {
        this.bannerTypeDeprecated = bannerTypeDeprecated;
    }

    public String getWapTitle() {
        return wapTitle;
    }

    public void setWapTitle(String wapTitle) {
        this.wapTitle = wapTitle;
    }

    public String getWapContent() {
        return wapContent;
    }

    public void setWapContent(String wapContent) {
        this.wapContent = wapContent;
    }

    public String getWapFax() {
        return wapFax;
    }

    public void setWapFax(String wapFax) {
        this.wapFax = wapFax;
    }

    public String getWapEmail() {
        return wapEmail;
    }

    public void setWapEmail(String wapEmail) {
        this.wapEmail = wapEmail;
    }

    public String getWapQq() {
        return wapQq;
    }

    public void setWapQq(String wapQq) {
        this.wapQq = wapQq;
    }

    public String getWapMsn() {
        return wapMsn;
    }

    public void setWapMsn(String wapMsn) {
        this.wapMsn = wapMsn;
    }

    public String getWapAddress() {
        return wapAddress;
    }

    public void setWapaddress(String wapAddress) {
        this.wapAddress = wapAddress;
    }

    public String getWapLinkText() {
        return wapLinkText;
    }

    public void setWapLinkText(String wapLinkText) {
        this.wapLinkText = wapLinkText;
    }

    public String getWapPhone() {
        return wapPhone;
    }

    public void setWapPhone(String wapPhone) {
        this.wapPhone = wapPhone;
    }

    private String wapPhone;

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

    /**
     * @return the wapId
     */
    public String getWapId() {
        return wapId;
    }

    /**
     * @param wapId the wapId to set
     */
    public void setWapId(String wapId) {
        this.wapId = wapId;
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
     * @return the richId
     */
    public String getRichId() {
        return richId;
    }

    /**
     * @param richId the richId to set
     */
    public void setRichId(String richId) {
        this.richId = richId;
    }

    /**
     * @return the bannerId
     */
    public String getBannerId() {
        return bannerId;
    }

    /**
     * @param bannerId the bannerId to set
     */
    public void setBannerId(String bannerId) {
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
    public String getAdOffer() {
        return adOffer;
    }

    /**
     * @param adOffer the adOffer to set
     */
    public void setAdOffer(String adOffer) {
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
     * @return the coreAd
     */
    public CoreAd getCoreAd() {
        form2domain();
        return coreAd;
    }

    /**
     * @param coreAd the coreAd to set
     */
    public void setCoreAd(CoreAd coreAd) {
        if (coreAd == null) {
            return;
        }
        setAdId(coreAd.getAdId().toString());
        setAdType(coreAd.getAdType());
        if (coreAd.getAdChildNum() != null) {
            setAdChildNum(coreAd.getAdChildNum().toString());
        }
        if (coreAd.getAdOffer() != null) {
            setAdOffer(coreAd.getAdOffer().toString());
        }
        if (coreAd.getBackground() != null) {
            setBackground(coreAd.getBackground().toString());
        }

        setAdName(coreAd.getAdName());

        // ReportDateUtil.getDate("yyyy-MM-dd H24:mm", coreAd.getAdStartTime());

        try {
            setAdStartHour(ReportDateUtil.getDate("HH", coreAd.getAdStartTime()));
            setAdStartMin(ReportDateUtil.getDate("mm", coreAd.getAdStartTime()));
            setAdStartTime(ReportDateUtil.getDate("yyyy-MM-dd", coreAd.getAdStartTime()));
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
          //  e.printStackTrace();
        }

        if (coreAd.getAdEndTime() != null) {
            setAdEndHour(ReportDateUtil.getDate("HH", coreAd.getAdEndTime()));
            setAdEndMin(ReportDateUtil.getDate("mm", coreAd.getAdEndTime()));
            setAdEndTime(ReportDateUtil.getDate("yyyy-MM-dd", coreAd.getAdEndTime()));
        }

        if (coreAd.getAdBudgetDay() != null) {
            setAdBudgetDay(coreAd.getAdBudgetDay().stripTrailingZeros().toPlainString());
        }
        if (coreAd.getAdBudgetTotal() != null) {
            setAdBudgetTotal(coreAd.getAdBudgetTotal().stripTrailingZeros().toPlainString());
        }

        this.coreAd = coreAd;
    }

    /**
     * @return the adBudgetDay
     */
    public String getAdBudgetDay() {
        return adBudgetDay;
    }

    /**
     * @param adBudgetDay the adBudgetDay to set
     */
    public void setAdBudgetDay(String adBudgetDay) {
        this.adBudgetDay = adBudgetDay;
    }

    /**
     * @return the adBudgetTotal
     */
    public String getAdBudgetTotal() {
        return adBudgetTotal;
    }

    /**
     * @param adBudgetTotal the adBudgetTotal to set
     */
    public void setAdBudgetTotal(String adBudgetTotal) {
        this.adBudgetTotal = adBudgetTotal;
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

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerColor(String bannerColor) {
        this.bannerColor = bannerColor;
    }

    public String getBannerColor() {
        return bannerColor;
    }

    public void setBanner(CoreBanner banner) {
        if (banner != null) {
            setBannerId(banner.getBannerId().toString());
            setBannerType(banner.getBannerType());
            setBannerBgType(banner.getBannerBgType());
            setBannerBgCon(banner.getBannerBgCon());
            setBannerText(banner.getBannerText());
            setBannerText2(banner.getBannerText2());
            setBannerIconCon(banner.getBannerIconCon());
            setBannerColor(banner.getBannerColor());
            setBannerGrain(banner.getBannerGrain());
            setBannerModelName(banner.getBannerModelName());
            setBannerHtml(banner.getBannerHtml());
        }
    }

    public CoreBanner getBanner() {
        return banner;
    }

    public void setBannerBgType(String bannerBgType) {
        this.bannerBgType = bannerBgType;
    }

    public String getBannerBgType() {
        return bannerBgType;
    }

    public void setBannerBgCon(String bannerBgCon) {
        this.bannerBgCon = bannerBgCon;
    }

    public String getBannerBgCon() {
        return bannerBgCon;
    }

    public void setBannerText2(String bannerText2) {
        this.bannerText2 = bannerText2;
    }

    public String getBannerText2() {
        return bannerText2;
    }

    public void setBannerText(String bannerText) {
        this.bannerText = bannerText;
    }

    public String getBannerText() {
        return bannerText;
    }

    public void setBannerIconCon(String bannerIconCon) {
        this.bannerIconCon = bannerIconCon;
    }

    public String getBannerIconCon() {
        return bannerIconCon;
    }

    public void setWap(CoreWapAd wap) {
        // wap信息
        if (wap != null) {
            setWapId(wap.getWapId().toString());
            setWapTitle(wap.getWapTitle());
            setWapContent(wap.getWapContent());
            setWapFax(wap.getFax());
            setWapEmail(wap.getEmail());
            setWapQq(wap.getQq());
            setWapMsn(wap.getMsn());
            setWapAddress(wap.getContactAddress());
            setWapLinkText(wap.getLinkText());
            setWapLinkAddres(wap.getUrlAddress());
            setWapTelephone(wap.getTelephone());
        }
    }

    public CoreWapAd getWap() {
        form2domain();
        return wap;
    }

    public String getWapLinkAddres() {
        return wapLinkAddres;
    }

    public void setWapLinkAddres(String wapLinkAddres) {
        this.wapLinkAddres = wapLinkAddres;
    }

    public String getWapTelephone() {
        return wapTelephone;
    }

    public void setWapTelephone(String wapTelephone) {
        this.wapTelephone = wapTelephone;
    }

    public void setWapAddress(String wapAddress) {
        this.wapAddress = wapAddress;
    }

    /**
     * 单个富媒体暂时不用
     * 
     * @param rich
     */
    public void setRich(CoreRichAd rich) {
        setRichMediaTitle(rich.getRichMediaTitle());
        setShowType(rich.getShowType());
        setRichMediaContent(rich.getRichMediaContent());
        setSort(rich.getSort().toString());
        setRelId(rich.getRelId().toString());
    }

    public CoreRichAd getRich() {
        form2domain();
        return rich;
    }

    /**
     * @param libTaobao the libTaobao to set
     */
    public void setLibTaobao(LibTaobao libTaobao) {
        this.libTaobao = libTaobao;
    }

    /**
     * @return the libTaobao
     */
    public LibTaobao getLibTaobao() {
        return libTaobao;
    }

    public String getRichMediaTitle() {
        return richMediaTitle;
    }

    public void setRichMediaTitle(String richMediaTitle) {
        this.richMediaTitle = richMediaTitle;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public LibMapInfo getMap() {
        form2domain();
        return map;
    }

    public void setProsJson(String prosJson) {
        this.prosJson = prosJson;
    }

    public String getProsJson() {
        return prosJson;
    }

    public LibPointInfo getLibPointInfo() {
        form2domain();
        return libPointInfo;
    }

    /**
     * @param richMediaContent the richMediaContent to set
     */
    public void setRichMediaContent(String richMediaContent) {
        this.richMediaContent = richMediaContent;
    }

    /**
     * @return the richMediaContent
     */
    public String getRichMediaContent() {
        return richMediaContent;
    }

    /**
     * @param sort the sort to set
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * @return the sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * @param relId the relId to set
     */
    public void setRelId(String relId) {
        this.relId = relId;
    }

    /**
     * @return the relId
     */
    public String getRelId() {
        return relId;
    }

    /**
     * @param numIid the numIid to set
     */
    public void setNumIid(String numIid) {
        this.numIid = numIid;
    }

    /**
     * @return the num_iid
     */
    public String getNumIid() {
        return numIid;
    }

    /**
     * @param uiId the uiId to set
     */
    public void setUiId(String uiId) {
        this.uiId = uiId;
    }

    /**
     * @return the uiId
     */
    public String getUiId() {
        return uiId;
    }

    /**
     * @param adChildNum the adChildNum to set
     */
    public void setAdChildNum(String adChildNum) {
        this.adChildNum = adChildNum;
    }

    /**
     * @return the adChildNum
     */
    public String getAdChildNum() {
        return adChildNum;
    }

    /**
     * @param richList the richList to set
     */
    public void setRichList(List<CoreRichAd> richList) {
        this.richList = richList;
    }

    /**
     * @return the richList
     */
    public List<CoreRichAd> getRichList() {
        return richList;
    }

    /**
     * @param formatType the formatType to set
     */
    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    /**
     * @return the formatType
     */
    public String getFormatType() {
        return formatType;
    }

    /**
     * @param mapId the mapId to set
     */
    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    /**
     * @return the mapId
     */
    public String getMapId() {
        return mapId;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.form.AbstractForm#form2domain()
     */
    public void setMakJson(String makJson) {
        this.makJson = makJson;
    }

    public String getMakJson() {
        return makJson;
    }

    /**
     * @param campaign the campaign to set
     */
    public void setCampaign(CoreCampaign campaign) {
        this.campaign = campaign;
    }

    /**
     * @return the campaign
     */
    public CoreCampaign getCampaign() {
        return campaign;
    }

    /**
     * @param adGroup the adGroup to set
     */
    public void setAdGroup(CoreAdGroup adGroup) {
        this.adGroup = adGroup;
    }

    /**
     * @return the adGroup
     */
    public CoreAdGroup getAdGroup() {
        return adGroup;
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
     * @param map the map to set
     */
    public void setMap(LibMapInfo map) {
        this.map = map;
    }

    /**
     * @param libPointInfo the libPointInfo to set
     */
    public void setLibPointInfo(LibPointInfo libPointInfo) {
        this.libPointInfo = libPointInfo;
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
     * @param bannerFristText the bannerFristText to set
     */
    public void setBannerFristText(String bannerFristText) {
        this.bannerFristText = bannerFristText;
    }

    /**
     * @return the bannerFristText
     */
    public String getBannerFristText() {
        return bannerFristText;
    }

    /**
     * @param bannerSecondText the bannerSecondText to set
     */
    public void setBannerSecondText(String bannerSecondText) {
        this.bannerSecondText = bannerSecondText;
    }

    /**
     * @return the bannerSecondText
     */
    public String getBannerSecondText() {
        return bannerSecondText;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * @return the background
     */
    public String getBackground() {
        return background;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param bannerGrain the bannerGrain to set
     */
    public void setBannerGrain(String bannerGrain) {
        this.bannerGrain = bannerGrain;
    }

    /**
     * @return the bannerGrain
     */
    public String getBannerGrain() {
        return bannerGrain;
    }

    /**
     * @param bannerModelName the bannerModelName to set
     */
    public void setBannerModelName(String bannerModelName) {
        this.bannerModelName = bannerModelName;
    }

    /**
     * @return the bannerModelName
     */
    public String getBannerModelName() {
        return bannerModelName;
    }

    /**
     * @param bannerHtml the bannerHtml to set
     */
    public void setBannerHtml(String bannerHtml) {
        this.bannerHtml = bannerHtml;
    }

    /**
     * @return the bannerHtml
     */
    public String getBannerHtml() {
        return bannerHtml;
    }

    /**
     * @return the taobaoUrl
     */
    public String getTaobaoUrl() {
        return taobaoUrl;
    }

    /**
     * @param taobaoUrl the taobaoUrl to set
     */
    public void setTaobaoUrl(String taobaoUrl) {
        this.taobaoUrl = taobaoUrl;
    }

    /**
     * @param taobaoId the taobaoId to set
     */
    public void setTaobaoId(String taobaoId) {
        this.taobaoId = taobaoId;
    }

    /**
     * @return the taobaoId
     */
    public String getTaobaoId() {
        return taobaoId;
    }

    /**
     * @param marketImgId the marketImgId to set
     */
    public void setMarketImgId(String marketImgId) {
        this.marketImgId = marketImgId;
    }

    /**
     * @return the marketImgId
     */
    public String getMarketImgId() {
        return marketImgId;
    }

    /**
     * @param marketName the marketName to set
     */
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    /**
     * @return the marketName
     */
    public String getMarketName() {
        return marketName;
    }

    /**
     * @param marketAndroidUrl the marketAndroidUrl to set
     */
    public void setMarketAndroidUrl(String marketAndroidUrl) {
        this.marketAndroidUrl = marketAndroidUrl;
    }

    /**
     * @return the marketAndroidUrl
     */
    public String getMarketAndroidUrl() {
        return marketAndroidUrl;
    }

    /**
     * @param marketIphoneUrl the marketIphoneUrl to set
     */
    public void setMarketIphoneUrl(String marketIphoneUrl) {
        this.marketIphoneUrl = marketIphoneUrl;
    }

    /**
     * @return the marketIphoneUrl
     */
    public String getMarketIphoneUrl() {
        return marketIphoneUrl;
    }

    /**
     * @param marketType the marketType to set
     */
    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    /**
     * @return the marketType
     */
    public String getMarketType() {
        return marketType;
    }

    /**
     * @param libMarket the libMarket to set
     */
    public void setLibMarket(LibMarket libMarket) {
        this.libMarket = libMarket;
    }

    /**
     * @return the libMarket
     */
    public LibMarket getLibMarket() {
        return libMarket;
    }

    /**
     * @param libMarketDetail the libMarketDetail to set
     */
    public void setLibMarketDetail(LibMarketDetail libMarketDetail) {
        this.libMarketDetail = libMarketDetail;
    }

    /**
     * @return the libMarketDetail
     */
    public LibMarketDetail getLibMarketDetail() {
        return libMarketDetail;
    }

    /**
     * @param marketAndroidId the marketAndroidId to set
     */
    public void setMarketAndroidId(String marketAndroidId) {
        this.marketAndroidId = marketAndroidId;
    }

    /**
     * @return the marketAndroidId
     */
    public String getMarketAndroidId() {
        return marketAndroidId;
    }

    /**
     * @param marketIphoneId the marketIphoneId to set
     */
    public void setMarketIphoneId(String marketIphoneId) {
        this.marketIphoneId = marketIphoneId;
    }

    /**
     * @return the marketIphoneId
     */
    public String getMarketIphoneId() {
        return marketIphoneId;
    }

    /**
     * @param marketId the marketId to set
     */
    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    /**
     * @return the marketId
     */
    public String getMarketId() {
        return marketId;
    }

    /**
     * @return the adIds
     */
    public String getAdIds() {
        return adIds;
    }

    /**
     * @param adIds the adIds to set
     */
    public void setAdIds(String adIds) {
        this.adIds = adIds;
    }

}
