/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.CommonDef;
import com.mitian.airad.common.constant.ConstantAd;
import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.common.utils.math.NumberUtils;
import com.mitian.airad.dao.CoreAdDAO;
import com.mitian.airad.dao.CoreBannerDAO;
import com.mitian.airad.dao.CoreRichAdDAO;
import com.mitian.airad.dao.CoreRichGuideDAO;
import com.mitian.airad.dao.CoreWapAdDAO;
import com.mitian.airad.dao.DictionaryDAO;
import com.mitian.airad.dao.LibCallPhoneDAO;
import com.mitian.airad.dao.LibMapInfoDAO;
import com.mitian.airad.dao.LibMarketDAO;
import com.mitian.airad.dao.LibMarketDetailDAO;
import com.mitian.airad.dao.LibPointInfoDAO;
import com.mitian.airad.dao.LibTaobaoDAO;
import com.mitian.airad.dao.SysConfigDAO;
import com.mitian.airad.model.CoreAd;
import com.mitian.airad.model.CoreAdGroup;
import com.mitian.airad.model.CoreBanner;
import com.mitian.airad.model.CoreRichAd;
import com.mitian.airad.model.CoreRichGuide;
import com.mitian.airad.model.CoreWapAd;
import com.mitian.airad.model.Dictionary;
import com.mitian.airad.model.LibCallPhone;
import com.mitian.airad.model.LibMapInfo;
import com.mitian.airad.model.LibMarket;
import com.mitian.airad.model.LibMarketDetail;
import com.mitian.airad.model.LibPointInfo;
import com.mitian.airad.model.LibTaobao;
import com.mitian.airad.model.RelAdSDKMapping;
import com.mitian.airad.model.SysConfig;
import com.mitian.airad.utils.StringUtil;
import com.mitian.airad.web.auth.roles.BaseRole;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.response.ItemGetResponse;

/**
 * AdService.java 广告信息业务类
 * 
 * @author wangzhongwei
 */
@Service
public class AdService implements ConstantAd {
    private static final Logger LOG = Logger.getLogger(AdService.class);
    /** spring注入CoreAgentRelationDAO */
    @Autowired
    private CoreAdDAO coreAdDAO;
    @Autowired
    private CoreBannerDAO coreBannerDAO;
    @Autowired
    private CoreWapAdDAO coreWapAdDAO;
    @Autowired
    private CoreRichAdDAO coreRichAdDAO;
    @Autowired
    private LibMapInfoDAO libMapInfoDAO;
    @Autowired
    private LibPointInfoDAO libPointInfoDAO;
    @Autowired
    private CoreRichGuideDAO coreRichGuideDAO;
    @Autowired
    private SysConfigDAO sysConfigDAO;
    @Autowired
    private DictionaryDAO dictionaryDao;
    @Autowired
    private LibTaobaoDAO libTaobaoDao;
    @Autowired
    private RelAdSDKMappingService relAdSDKMappingService;

    @Autowired
    private LibCallPhoneDAO libCallPhoneDao;

    @Autowired
    private LibMarketDAO libMarketDao;
    @Autowired
    private LibMarketDetailDAO libMarketDetailDao;

    /**
     * @return the libTaobaoDao
     */
    public LibTaobaoDAO getLibTaobaoDao() {
        return libTaobaoDao;
    }

    /**
     * @param libTaobaoDao the libTaobaoDao to set
     */
    public void setLibTaobaoDao(LibTaobaoDAO libTaobaoDao) {
        this.libTaobaoDao = libTaobaoDao;
    }

    /**
     * 根据活动Id查找活动下的广告详细信息(统计各个状态条数)
     * 
     * @param ad
     * @return
     */
    public CoreAd queryListByCampaignId(CoreAd ad) {
        CoreAd coreAd = new CoreAd();
        coreAd = coreAdDAO.findAdListByCampaignId(ad);
        return coreAd;
    }

    /**
     * 根据广告组Id查找广告组下的广告详细信息(统计各个状态条数)
     * 
     * @param ad
     * @return
     */
    public CoreAd queryListByGroupId(CoreAd ad) {
        CoreAd coreAd = new CoreAd();
        coreAd = coreAdDAO.findAdListByAdGroupId(ad);
        return coreAd;
    }

    /**
     * 根据广告组的id查询所有的广告信息
     * 
     * @param adGroupId
     * @return
     */
    public List<CoreAd> queryList(Integer adGroupId, String adName, String flag, int record, int pageSize,
            Long memberId, String desc, String currentPage) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("adGroupId", adGroupId);
        if (StringUtils.isNotEmpty(adName)) {
            if (!adName.contains("/")) {
                params.put("escape", CommonDef.escape.ESCAPE);
            }
            adName = StringUtil.replaceChar(adName);
            params.put("adName", adName);
        }
        params.put("+", flag);
        params.put("memberId", memberId);
        params.put("currentPage", currentPage);
        params.put("desc", desc);
        List<CoreAd> adList = new ArrayList<CoreAd>();
        // 个人中心需要adGruopId为null
        adList = coreAdDAO.findAdListByCond(params, record, pageSize);

        return adList;
    }

    public List<CoreAd> queryList(Map<String, String> params) {
        List<CoreAd> adList = coreAdDAO.findAdListByCond(params);
        return adList;
    }

    public List<CoreAd> queryList4Shopper(Long memberId, int page, String sortName, String sortType, String searchAdName) {
        CoreAd coreAd = new CoreAd();
        coreAd.setMemberId(memberId);
        coreAd.setAdName(searchAdName);
        coreAd.setLimit(10);
        int page4Query = page - 1;
        if (page4Query < 0) {
            page4Query = 0;
        }
        coreAd.setOffset(10 * page4Query);
        coreAd.setSortName(sortName);
        coreAd.setSortType(sortType);
        List<CoreAd> adList = coreAdDAO.queryList4Shopper(coreAd);
        return adList;
    }

    /**
     * 根据时间统计
     * 
     * @param adGroupId
     * @param adName
     * @param flag
     * @param record
     * @param pageSize
     * @param memberId
     * @return
     */
    public List<CoreAd> queryListByTimeSlot(Integer adGroupId, String adName, String flag, int record, int pageSize,
            Long memberId, String startTime, String endTime, String desc, String currentPage) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(adName)) {
            if (!adName.contains("/")) {
                params.put("escape", CommonDef.escape.ESCAPE);
            }
            adName = StringUtil.replaceChar(adName);
            params.put("adName", adName);
        }
        params.put("adGroupId", adGroupId);
        params.put("flag", flag);
        params.put("memberId", memberId);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("endTime", endTime);
        params.put("currentPage", currentPage);
        List<CoreAd> adList = new ArrayList<CoreAd>();
        if (StringUtils.isNotBlank(adGroupId.toString())) {
            adList = coreAdDAO.findAdListByTimeSlot(params, record, pageSize);
        }
        return adList;
    }

    public List<CoreAd> queryListByTimeSlot(Map<String, String> params) {
        List<CoreAd> adList = new ArrayList<CoreAd>();
        // params.put("adGroupId", adGroupId.toString());
        // if (StringUtils.isNotBlank(adGroupId.toString())) {
        adList = coreAdDAO.findAdListByTimeSlot(params);
        // }
        return adList;
    }

    public int countTotalByTimeSlot(Integer adGroupId, String adName, String flag, Long memberId, String startTime,
            String endTime) {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("adGroupId", adGroupId);
        params.put("flag", flag);
        params.put("memberId", memberId);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        int adSize = 0;
        // if (StringUtils.isNotBlank(adGroupId.toString())) {
        adSize = coreAdDAO.totalCountByTimeSlot(params, adSize, adSize);
        // }
        return adSize;
    }

    public int countTotalByTimeSlot(Map<String, String> params) {
        Integer adSize = coreAdDAO.totalCountByTimeSlot(params);
        return adSize;
    }

    /**
     * 分页
     * 
     * @param adGroupId
     * @param adName
     * @param flag
     * @param record
     * @param pageSize
     * @return
     */
    public int countTotal(Integer adGroupId, String adName, String flag, Long memberId) {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("adGroupId", adGroupId);
        if (StringUtils.isNotEmpty(adName)) {
            if (!adName.contains("/")) {
                params.put("escape", CommonDef.escape.ESCAPE);
            }
            adName = StringUtil.replaceChar(adName);
            params.put("adName", adName);
        }
        params.put("flag", flag);
        params.put("memberId", memberId);
        int adSize = 0;
        return adSize;
    }

    public int countTotal(Map<String, String> params) {
        Integer adSize = coreAdDAO.countTotal(params);
        return adSize;
    }

    /**
     * 根据广告的id删除 广告信息
     * 
     * @param adGroupId
     * @return
     */
    public void txDeleteCoreAd(CoreAd ca, Long memberId) {
        ca.setDelFlag(CommonDef.unDataBaseConstant.DEL_FLAG);
        ca.setDelTime(new Date());
        ca.setMemberId(memberId);
        coreAdDAO.updateByPrimaryKeySelective(ca);
    }

    /**
     * 根据广告的id暂停 广告信息
     * 
     * @param adGroupId
     * @return
     */
    public void txStopCoreAd(CoreAd ca, Long memberId) {
        ca.setSuspendType(CommonDef.appCon.CON_ISSUE);
        ca.setMemberId(memberId);
        coreAdDAO.updateByPrimaryKeySelective(ca);
    }
    /**
     * 根据广告的id移动 广告信息
     * 
     * @param adGroupId
     * @return
     */
    public void txDeleveCoreAd(CoreAd ca, Long memberId) {
//        ca.setSuspendType(CommonDef.appCon.CON_ISSUE);
        ca.setMemberId(memberId);
        coreAdDAO.updateByPrimaryKeySelective(ca);
    }

    /**
     * 根据广告的id 发布 广告信息
     * 
     * @param adGroupId
     * @return
     */
    public void txSendCoreAd(CoreAd ca, Long memberId) {
        ca.setSuspendType(CommonDef.appCon.CON_SUSPEND);
        ca.setMemberId(memberId);
        coreAdDAO.updateByPrimaryKeySelective(ca);
    }

    /**
     * 根据广告的id 发布 广告信息
     * 
     * @param adGroupId
     * @return
     */
    public void delRich(Integer richId, Long memberId) {
        coreRichAdDAO.deleteByPrimaryKey(richId, memberId);
    }

    /**
     * 批量发布广告
     * 
     * @param listAd
     */
    public void txSendCoreAd(List<CoreAd> listAd) {
        coreAdDAO.batchSendCoreAd(listAd);
    }

    /**
     * 批量暂停广告
     * 
     * @param listAd
     */
    public void txstopAd(List<CoreAd> listAd) {
        coreAdDAO.batchStopCoreAd(listAd);
    }

    /**
     * 根据广告的id 查找广告信息
     * 
     * @param adGroupId
     * @return
     */
    public CoreAd findCoreAdByAdId(Integer adId, Long memberId) {
        CoreAd ad = new CoreAd();
        if (StringUtils.isNotBlank(adId.toString())) {
            ad = coreAdDAO.selectByPrimaryKey(adId, memberId);
        }
        return ad;
    }

    /**
     * 根据广告的id 查找广告信息修改
     * 
     * @param role
     * @param adGroupId
     * @return
     */
    public int txUpdateCoreAdByAdId(CoreAd ad, String flag, Long memberId, BaseRole role) {
        int up = 0;
        ad.setUpdTime(new Date());
        ad.setFlag(flag);
        ad.setMemberId(memberId);
        if (role.isOssSales()) {
            ad.setPaymentType(ConstantAd.AD_PAYMENT_TYPE_CPM);
        }
        else {
            ad.setPaymentType(ConstantAd.AD_PAYMENT_TYPE_CPC);
        }
        ad.setUpdOper(ad.getMemberId().toString());
        if (StringUtils.isNotBlank(ad.getAdId().toString())) {
            up = coreAdDAO.updateByPrimaryKeySelective(ad);
        }
        return up;
    }

    /**
     * 插入广告信息
     * 
     * @param ca
     * @param role
     * @return
     */
    public int txAddCoreAd(CoreAd ca, Long memberId, BaseRole role) {
        int adId = 0;
        if (null != ca.getAdGroupId() && StringUtils.isNotBlank(ca.getAdGroupId().toString())) {
            ca.setAddTime(new Date());
            ca.setUpdTime(new Date());
            ca.setFlag(Flag.DRAFT.getCode().toString());
            ca.setAddOper(ca.getMemberId().toString());
            ca.setSuspendType(SuspendType.NO.getCode().toString());
            ca.setMemberId(memberId);
            if (role.isOssSales()) {
                ca.setPaymentType(ConstantAd.AD_PAYMENT_TYPE_CPM);
            }
            else {
                ca.setPaymentType(ConstantAd.AD_PAYMENT_TYPE_CPC);
            }
            adId = coreAdDAO.insertAd(ca);
        }
        return adId;
    }

    /**
     * 查询广告信息
     * 
     * @param ca
     * @return
     */
    // public AdForm findCoreAd(CoreAd ca, AdForm adform) {
    //
    // if (StringUtils.isNotBlank(ca.getAdId().toString())) {
    // ca = coreAdDAO.selectByPrimaryKey(ca.getAdId(), ca.getMemberId());
    //
    // // 广告
    // if (ca != null) {
    // adform.setCoreAd(ca);
    //
    // /**
    // * 不知原因bug 或突然给form 的banner赋值
    // */
    // // banner
    // CoreBanner banner = findBanner(ca.getAdId());
    // if (banner != null) {
    // adform.setBanner(banner);
    // }
    // // wap
    // adform.setWap(findCoreWap(ca.getWapId()));
    // // 富媒体
    // List<CoreRichAd> richList = findCoreRichList(ca.getAdId());
    //
    // adform.setRichList(richList);
    // }
    //
    // }
    // return adform;
    // }

    /**
     * 根据广告id返回所有广告相关信息
     * 
     * @param adId
     * @param memberId
     * @return adMap { "ad": CoreAd, "banner": CoreBanner, "wap": CoreWapAd, "richMedia": List<CoreRichAd> }
     * @throws InvalidInfoException
     */
    public Map<String, Object> findById(String adId, Long memberId) throws InvalidInfoException {
        Map<String, Object> adMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(adId)) {
            int aId = Integer.parseInt(adId);
            CoreAd ad = coreAdDAO.selectByPrimaryKey(aId, memberId);
            if (ad == null) {
                throw new InvalidInfoException("findById error adId:" + adId + " ,memberId:" + memberId);
            }
            CoreBanner banner = findBanner(aId, memberId);
            CoreWapAd wap = ad == null ? null : findCoreWap(aId, memberId);
            List<CoreRichAd> richList = findCoreRichList(aId, memberId);
            //
            adMap.put("ad", ad);
            adMap.put("banner", banner);
            adMap.put("wap", wap);
            adMap.put("richMedia", richList);
        }
        return adMap;
    }

    /**
     * 查询banner信息
     * 
     * @param adId
     * @return
     */
    public CoreBanner findBanner(Integer adId, Long memberId) {
        CoreBanner banner = getCoreBannerDAO().selectBannerByAdId(adId, memberId);
        if (banner != null) {
            if (banner.getBannerText() != null) {
                banner.setBannerText(banner.getBannerText().replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
            }
            if (banner.getBannerText2() != null) {
                banner.setBannerText2(banner.getBannerText2().replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
            }
            if (banner.getBannerHtml() != null) {
                banner.setBannerHtml(banner.getBannerHtml().replaceAll("(\r\n|\r|\n|\n\r)", ""));
            }
        }
        return banner;
    }

    /**
     * 插入广告banner信息
     * 
     * @param ca
     * @return
     */
    public int txAddCoreBanner(CoreBanner banner, Long memberId) {
        if (StringUtils.isNotBlank(banner.getAdId().toString())) {
            Integer bannerId = getCoreBannerDAO().insertSelective(banner, memberId);
            if (bannerId != 0) {
                CoreAd upBanner = new CoreAd();
                upBanner.setAdId(banner.getAdId());
                upBanner.setBannerId(bannerId);
                upBanner.setMemberId(memberId);
                coreAdDAO.updateByPrimaryKeySelective(upBanner);
            }
            return bannerId;
        }
        return 0;
    }

    /**
     * 修改banner信息
     * 
     * @param banner
     * @return
     */
    public int txUpdateCoreBannerByAdId(CoreBanner banner, Long memberId) {
        return getCoreBannerDAO().updateByPrimaryKeySelective(banner, memberId);
    }

    /**
     * 插入广告wap信息
     * 
     * @param ca
     * @return
     */
    public Integer txAddCoreWap(CoreWapAd wap, Long memberId) {
        Integer wapId = 0;
        if (StringUtils.isNotBlank(wap.getAdId().toString())) {
            wapId = coreWapAdDAO.insert(wap, memberId);
            if (wapId != 0) {
                CoreAd upWap = new CoreAd();
                upWap.setAdId(wap.getAdId());
                upWap.setWapId(wapId);
                upWap.setMemberId(memberId);
                coreAdDAO.updateByPrimaryKeySelective(upWap);
            }
        }
        return wapId;
    }

    /**
     * 更新广告wap信息
     * 
     * @param ca
     * @return
     */
    public Integer txUpdateCoreWap(CoreWapAd wap, Long memberId) {
        Integer flag = 0;
        if (StringUtils.isNotBlank(wap.getAdId().toString())) {
            flag = coreWapAdDAO.updateByPrimaryKeyWithBLOBs(wap, memberId);
        }
        return flag;
    }

    /**
     * 查询wap信息
     * 
     * @param ca
     * @return
     */
    public CoreWapAd findCoreWap(Integer adId, Long memberId) {
        return coreWapAdDAO.selectByadId(adId, memberId);
    }

    /**
     * 插入广告rich信息
     * 
     * @param ca
     * @return
     */
    public CoreRichAd txAddCoreRich(CoreRichAd rich, Long memberId) {

        Integer richId = rich.getRichId();
        if (null != rich.getAdId()) {
            if (!StringUtils.isEmpty(rich.getSort().toString())) {
                coreRichAdDAO.deleteByPrimarySortAndAdId(rich.getSort(), rich.getAdId(), memberId);
                richId = coreRichAdDAO.insertSelective(rich, memberId);
            }
        }
        if (StringUtils.isNotBlank(rich.getShowType())) {
            if (rich.getShowType().equals(ShowType.map.getCode().toString())) {
                rich.getMap().setRichId(richId);
                Integer relId = saveMapInfo(rich.getMap());
                rich.setRelId(relId.toString());
                coreRichAdDAO.updateRelByPrimaryKey(rich, memberId);
            }
            else if (rich.getShowType().equals(ShowType.taobaoTrain.getCode().toString())) {
                LibTaobao libTaobao = rich.getLibTaobao();
                libTaobao.setRichId(richId);
                libTaobao.setStatus("1");
                Integer taobaoId = saveTaobaoInfo(libTaobao);
                rich.setRelId(taobaoId.toString());
                coreRichAdDAO.updateRelByPrimaryKey(rich, memberId);
            }
            else if (rich.getShowType().equals(ShowType.AndroidMarket.getCode().toString())
                    || rich.getShowType().equals(ShowType.iPhoneMarket.getCode().toString())) {
                LibMarket libMarket = rich.getLibMarket();
                if (libMarket != null) {
                    libMarket.setRichId(richId);
                    libMarket.setStatus("1");
                    Integer markerId = saveMarketInfo(libMarket);
                    libMarket.setRichId(markerId);
                    rich.setRelId(markerId.toString());
                    coreRichAdDAO.updateRelByPrimaryKey(rich, memberId);
                    
                    ///下载类广告SDK投放
                    if(rich.getShowType().equals(ShowType.AndroidMarket.getCode().toString())){
                        
                        RelAdSDKMapping record = new RelAdSDKMapping();
                        record.setAdId(rich.getAdId());
                        //6 SDK_VERSION sandroid ,sdkbersion 1.2 主键，交互
                        record.setSdkId(6);
                        relAdSDKMappingService.txAddAdSdkMapping(record);
                    }
                }
            }
            // add by pengli 201109-15
            else if (rich.getShowType().equals(ShowType.callPhone.getCode().toString())) {
                LibCallPhone libCallPhone = rich.getLibCallPhone();
                libCallPhone.setRichId(richId);
                libCallPhone.setStatus("1");
                Integer CallPhoneId = saveCallPhoneInfo(libCallPhone);
                rich.setRelId(CallPhoneId.toString());
                coreRichAdDAO.updateRelByPrimaryKey(rich, memberId);
            }
        }
        return rich;
    }

    /**
     * 删除rich字段
     */
    public void delRich(String delRichIdSp, Long memberId) {
        // 删除多余的rich
        if (delRichIdSp != null && delRichIdSp.trim().length() > 0) {
            String[] richArry = delRichIdSp.split(",");
            for (String val : richArry) {
                if (StringUtils.isNotBlank(val)) {
                    delRich(Integer.parseInt(val), memberId);
                }

            }
        }
    }

    /**
     * 更新广告rich信息
     * 
     * @param ca
     * @return
     */
    public CoreRichAd txUpdateCoreRich(CoreRichAd rich, Long memberId) {

        Integer flag = 0;
        rich.setMemberId(memberId);
        Integer richId = rich.getRichId();
        if (richId != null) {
            if (StringUtils.isNotBlank(rich.getShowType())) {
                
                

                
                if (rich.getShowType().equals(ShowType.map.getCode().toString())) {
                    if (rich.getRelId() != null && rich.getRelId().length() > 0) {
                        updateMapInfo(rich.getMap(), rich.getRelId());
                    }
                    else {
                        Integer relId = saveMapInfo(rich.getMap());
                        rich.setRelId(relId.toString());
                    }
                }
                if (rich.getShowType().equals(ShowType.taobaoTrain.getCode().toString())) {
                    LibTaobao libTaobao = libTaobaoDao.selectByRichId(richId);
                    LibTaobao taobao = new LibTaobao();
                    if (null == libTaobao) {
                        taobao = rich.getLibTaobao();
                        if (taobao.getId() == null) {
                            taobao.setRichId(richId);
                            taobao.setStatus("1");
                            Integer taobaoId = saveTaobaoInfo(taobao);
                            taobao.setId(taobaoId);
                        }
                    }
                    else {
                        taobao = libTaobao;
                        String url = rich.getLibTaobao().getTaobaoUrl();
                        taobao.setTaobaoUrl(url);
                        libTaobaoDao.updateByPrimaryKeySelective(taobao);
                    }
                    rich.setRelId(taobao.getId().toString());
                }

                if (rich.getShowType().equals(ShowType.callPhone.getCode().toString())) {
                    LibCallPhone libCallPhone = libCallPhoneDao.selectByRichId(richId);
                    LibCallPhone callPhone = new LibCallPhone();
                    if (null == libCallPhone) {
                        callPhone = rich.getLibCallPhone();
                        if (callPhone.getId() == null) {
                            callPhone.setRichId(richId);
                            callPhone.setStatus("1");
                            Integer callPhoneId = saveCallPhoneInfo(callPhone);
                            callPhone.setId(callPhoneId);
                        }
                    }
                    else {
                        callPhone = libCallPhone;
                        String number = rich.getLibCallPhone().getCallPhoneNumber();
                        callPhone.setCallPhoneNumber(number);
                        libCallPhoneDao.updateByPrimaryKeySelective(callPhone);
                    }
                    rich.setRelId(callPhone.getId().toString());
                }

                if (rich.getShowType().equals(ShowType.AndroidMarket.getCode().toString())
                        || rich.getShowType().equals(ShowType.iPhoneMarket.getCode().toString())) {
                    LibMarket libMarket = libMarketDao.selectByRichId(richId);
                    LibMarket market = new LibMarket();
                    if (null == libMarket) {
                        market = rich.getLibMarket();
                        market.setRichId(richId);
                        market.setStatus("1");
                        Integer marketId = saveMarketInfo(market);
                        market.setId(marketId);
                        rich.setRelId(market.getId().toString());

                    }
                    else {
                        market = rich.getLibMarket();
                        market.setRichId(richId);
                        market.setStatus("1");
                        saveMarketInfo(market);
                        rich.setRelId(market.getId().toString());
                    }
                    
                    rich.setLibMarket(libMarket);
                    
                    
                    if (rich.getShowType().equals(ShowType.AndroidMarket.getCode().toString())){
                        relAdSDKMappingService.txDeleteAdSdkMapping(rich.getAdId());
                        ///
                        RelAdSDKMapping record = new RelAdSDKMapping();
                        record.setAdId(rich.getAdId());
                        //6 SDK_VERSION sandroid ,sdkbersion 1.2 主键，交互
                        record.setSdkId(6);
                        relAdSDKMappingService.txAddAdSdkMapping(record);
                    
                    }
                    
                    
                }
            }
            flag = coreRichAdDAO.updateByPrimaryKey(rich, memberId);
            rich.setFlag(flag);
            return rich;
        }
        return rich;
    }

    /**
     * 查询广告rich列表信息
     * 
     * @param ca
     * @return
     */
    public List<CoreRichAd> findCoreRichList(Integer adId, Long memberId) {
        List<CoreRichAd> richList = coreRichAdDAO.findAdListByAsId(adId, memberId);
        for (CoreRichAd rich : richList) {
            if (rich.getRichMediaContent() != null) {
                rich.setRichMediaContent(rich.getRichMediaContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>"));
            }
            if (rich.getShowType() != null) {
                if (rich.getShowType().equals("4")) {
                    if (StringUtils.isNotBlank(rich.getRelId())) {
                        LibMapInfo map = findMapInfo(Integer.valueOf(rich.getRelId()));
                        rich.setMap(map);
                    }
                }
                if (rich.getShowType().equals("6")) {
                    if (StringUtils.isNotBlank(rich.getRelId())) {
                        LibTaobao taobao = libTaobaoDao.selectByPrimaryKey(Integer.parseInt(rich.getRelId()));
                        rich.setLibTaobao(taobao);
                    }
                }
                if (rich.getShowType().equals("7") || rich.getShowType().equals("8")) {
                    if (StringUtils.isNotBlank(rich.getRelId())) {
                        LibMarket market = libMarketDao.selectByPrimaryKey(Integer.parseInt(rich.getRelId()));
                        List<LibMarketDetail> libMarketDetail =
                                libMarketDetailDao.selectByMarketId(Integer.parseInt(rich.getRelId()));
                        market.setLibMarketDetailList(libMarketDetail);
                        rich.setLibMarket(market);
                    }
                }
                if (rich.getShowType().equals("9")) {
                    if (StringUtils.isNotBlank(rich.getRelId())) {
                        LibCallPhone callPhone = libCallPhoneDao.selectByPrimaryKey(Integer.parseInt(rich.getRelId()));
                        rich.setLibCallPhone(callPhone);
                    }
                }
            }
        }
        return richList;
    }

    /**
     * 保存地图
     * 
     * @param map
     */
    private int saveMapInfo(LibMapInfo map) {
        int mapId = libMapInfoDAO.insert(map);
        // 修改引用

        String srtingArr = map.getLibPointInfo().getProsJson();
        if (srtingArr != null && srtingArr.length() > 0) {
            srtingArr = srtingArr.substring(0, srtingArr.length() - 1);
        }
        String[] arr = srtingArr.split("-");
        for (String prosJson : arr) {
            if (prosJson.length() > 0) {
                map.getLibPointInfo().setMapId(map.getMapId());
                map.getLibPointInfo().setProsJson(prosJson);
                libPointInfoDAO.insert(map.getLibPointInfo());
            }
        }
        return mapId;
    }

    /**
     * 修改地图
     * 
     * @param map
     */
    private void updateMapInfo(LibMapInfo map, String mapId) {
        if (StringUtils.isNotBlank(mapId)) {
            map.setMapId(Integer.valueOf(mapId));
            libPointInfoDAO.deleteByMapId(Integer.valueOf(mapId));
        }
        libMapInfoDAO.updateByPrimaryKeySelective(map);
        String srtingArr = map.getLibPointInfo().getProsJson();
        if (srtingArr != null && srtingArr.length() > 0) {
            srtingArr = srtingArr.substring(0, srtingArr.length() - 1);
        }
        String[] arr = srtingArr.split("-");
        for (String prosJson : arr) {
            if (prosJson.length() > 0) {
                map.getLibPointInfo().setMapId(map.getMapId());
                map.getLibPointInfo().setProsJson(prosJson);
                libPointInfoDAO.insert(map.getLibPointInfo());
            }
        }
    }

    /**
     * 查询地图信息
     * 
     * @param mapId
     * @return
     */
    private LibMapInfo findMapInfo(Integer mapId) {
        LibMapInfo map = libMapInfoDAO.selectByPrimaryKey(mapId);
        List<LibPointInfo> pointList = findPointInfo(map.getMapId());
        map.setLibPointInfoList(pointList);
        return map;
    }

    /**
     * 查询地图点的信息
     * 
     * @param adId
     * @return
     */
    private List<LibPointInfo> findPointInfo(Integer mapId) {
        List<LibPointInfo> pointList = libPointInfoDAO.findAdListByMapId(mapId);
        return pointList;
    }

    /**
     * 保存htmlBox
     * 
     * @param map
     */
    private void saveHtmlBoxInfo(LibMapInfo map) {

    }

    /**
     * 保存淘宝链接信息
     */
    private int saveTaobaoInfo(LibTaobao taobao) {
        int taobaoId = libTaobaoDao.insertSelective(taobao);
        return taobaoId;
    }

    // /**
    // * 保存直播号码信息
    // */
    // private int saveCallPhoneInfo(LibCallPhone callPhone) {
    // int taobaoId = libCallPhoneDao.insertSelective(callPhone);
    // return taobaoId;
    // }
    /**
     * 保存直播号码链接信息
     */
    private int saveCallPhoneInfo(LibCallPhone callPhone) {
        int taobaoId = libCallPhoneDao.insertSelective(callPhone);
        return taobaoId;
    }

    /**
     * 保存market
     */
    private int saveMarketInfo(LibMarket market) {
        if (null == market.getId()) {
            int marketId = libMarketDao.insert(market);
            List<LibMarketDetail> marketList = market.getLibMarketDetailList();
            if (marketList != null) {
                for (LibMarketDetail detail : marketList) {
                    detail.setMarketId(marketId);
                    libMarketDetailDao.insert(detail);
                }
            }
            return marketId;
        }
        else {
            libMarketDao.updateByPrimaryKeySelective(market);
            List<LibMarketDetail> marketList = market.getLibMarketDetailList();
            if (marketList != null) {
                for (LibMarketDetail detail : marketList) {
                    if (null == detail.getId()) {
                        detail.setMarketId(market.getId());
                        libMarketDetailDao.insert(detail);
                    }
                    else {
                        libMarketDetailDao.updateByPrimaryKeySelective(detail);
                    }
                }
            }
            return market.getId();
        }
    }

    /**
     * 保存导航
     * 
     * @param coreRichGuide
     */
    private int saveGuide(CoreRichGuide coreRichGuide) {
        int guidId = coreRichGuideDAO.insert(coreRichGuide);
        return guidId;
    }

    /**
     * 通过淘宝api输入商品id获取商品信息
     * 
     * @param num_id
     * @return
     * @throws ApiException
     */
    public com.taobao.api.domain.Item getTaobaoInfo(long num_id) throws ApiException {
        TaobaoClient client =
                new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "12213670",
                        "58db0d36c2189e4cbc0042553825a116");
        ItemGetRequest req = new ItemGetRequest();
        req.setFields("detail_url,num_iid,title,pic_url,wap_detail_url");
        req.setNumIid(num_id);
        ItemGetResponse res = client.execute(req);
        return res.getItem();

    }

    /**
     * 根据用户id查询所有广告
     * 
     * @param memberId
     * @return
     */
    public List<CoreAd> findAdListByMemberId(Long memberId) {
        List<CoreAd> coreAdList = new ArrayList<CoreAd>();
        coreAdList = coreAdDAO.findAdListByMemberId(memberId);
        return coreAdList;
    }

    /**
     * 根据广告组查找旗下所有广告
     * 
     * @param adGroup
     * @return
     */
    public List<CoreAd> findAdList(CoreAdGroup adGroup) {
        List<CoreAd> coreAdList = new ArrayList<CoreAd>();
        coreAdList = coreAdDAO.findAllAd(adGroup);
        return coreAdList;
    }

    /**
     * 计算价格
     * 
     * @param adId
     * @return
     */
    public BigDecimal compute(Integer adId, Long memberId, BaseRole role) {
        // 后台人员制作广告按CPM定价
        if (role.isOssSales()) {
            return null;
        }
        CoreAd ad = coreAdDAO.selectByPrimaryKey(adId, memberId);
        // 获取banner
        CoreBanner banner = coreBannerDAO.selectBannerByAdId(adId, memberId);

        Double free = 0.00;
        String bannerFree = "";
        String wapFree = "";
        String richFree = "";
        String butterFree = "";
        String advancedButton = "";
        // 获取banner价格
        if (banner != null && banner.getBannerType() != null && banner.getBannerType().length() > 0) {
            bannerFree = sysConfigDAO.findConfigByTypeAndKey("BANNER_TYPE", banner.getBannerType()).getSysVal();

            if (wapFree != null) {
                free += Double.parseDouble(bannerFree);
            }
        }

        if (ad.getAdType() != null && !ad.getAdType().equals("")) {
            if (Integer.parseInt(ad.getAdType()) == AdType.Wap.getCode()) {
                // wap价格
                wapFree = sysConfigDAO.findConfigByTypeAndKey("WAP", ad.getAdType()).getSysVal();
                if (wapFree != null) {
                    free += Double.parseDouble(wapFree);
                }
            }
            else {
                // 导航价格
                if (Integer.parseInt(ad.getAdType()) == AdType.Button.getCode()) {
                    SysConfig sysConfig =
                            sysConfigDAO.findConfigByTypeAndKey("NAVIGATION", NAVIGATION.Button.getCode().toString());
                    if (sysConfig != null) {
                        butterFree = sysConfig.getSysVal();
                        if (butterFree != null) {
                            free += Double.parseDouble(butterFree);
                        }
                    }
                }
                else if (Integer.parseInt(ad.getAdType()) == AdType.AdvancedButton.getCode()) {
                    // 高级导航价格
                    SysConfig sysConfig =
                            sysConfigDAO.findConfigByTypeAndKey("NAVIGATION", NAVIGATION.AdvancedButton.getCode()
                                    .toString());
                    if (sysConfig != null) {
                        advancedButton = sysConfig.getSysVal();
                        if (advancedButton != null) {
                            free += Double.parseDouble(advancedButton);
                        }
                    }

                }
                // 单页面价格
                if (Integer.parseInt(ad.getAdType()) == AdType.Wap.getCode()) {
                    wapFree = sysConfigDAO.findConfigByTypeAndKey("WAP", ad.getAdType()).getSysVal();
                    if (wapFree != null) {
                        free += Double.parseDouble(wapFree);
                    }
                }
                List<CoreRichAd> coreRichAdList = coreRichAdDAO.findAdListByAsId(adId, memberId);
                for (int i = 0; i < ad.getAdChildNum() && i < coreRichAdList.size(); i++) {
                    CoreRichAd richad = coreRichAdList.get(i);
                    if (richad == null) {
                        break;
                    }
                    SysConfig config = null;
                    if (richad.getShowType() != null) {
                        config = sysConfigDAO.findConfigByTypeAndKey("SHOW_TYPE", richad.getShowType());
                        if (config != null) {
                            richFree = config.getSysVal();
                        }
                        if (wapFree != null) {
                            free += Double.parseDouble(richFree);
                        }
                    }
                }
            }
        }

        DecimalFormat df = new DecimalFormat("0.00");
        CoreAd adFree = new CoreAd();
        BigDecimal compute = new BigDecimal(df.format(free));
        adFree.setAdId(adId);
        adFree.setMemberId(memberId);
        adFree.setAdOffer(compute);
        adFree.setAdBudgetDay(ad.getAdBudgetDay());
        adFree.setAdBudgetTotal(ad.getAdBudgetTotal());
        adFree.setAdStartTime(ad.getAdStartTime());
        adFree.setAdEndTime(ad.getAdEndTime());

        coreAdDAO.updateByPrimaryKeySelective(adFree);

        return compute;

    }

    public boolean isBannerTypeDeprecated(CoreBanner banner) {
        boolean isDeprecated = false;
        if (banner != null && banner.getBannerType() != null && banner.getBannerType().length() > 0
                && Integer.parseInt(banner.getBannerType()) <= 4) {
            isDeprecated = true;
        }
        return isDeprecated;
    }

    /**
     * 查询所属行业信息
     * 
     * @return
     */
    public List<Dictionary> txQueryDictionaryList() {
        return dictionaryDao.findIndustryInvolvedList();
    }

    /**
     * @param coreBannerDAO the coreBannerDAO to set
     */
    public void setCoreBannerDAO(CoreBannerDAO coreBannerDAO) {
        this.coreBannerDAO = coreBannerDAO;
    }

    /**
     * @return the coreBannerDAO
     */
    public CoreBannerDAO getCoreBannerDAO() {
        return coreBannerDAO;
    }

    /**
     * @return the coreRichAdDAO
     */
    public CoreRichAdDAO getCoreRichAdDAO() {
        return coreRichAdDAO;
    }

    /**
     * @param coreRichAdDAO the coreRichAdDAO to set
     */
    public void setCoreRichAdDAO(CoreRichAdDAO coreRichAdDAO) {
        this.coreRichAdDAO = coreRichAdDAO;
    }

    /**
     * 根据组判断支持的平台
     */
    public int isPs(String value) {
        if ("1".equals(value)) {
            return 1;
        }
        else if ("0".equals(value)) {
            return 2;
        }
        else {
            return 0;
        }
    }

    /**
     * 获取广告总展示数量
     * 
     * @param adList
     * @return
     */
    public Integer getTotalAdShowCount(Long memberId) {
        Integer count = coreAdDAO.getTotalAdShowCount(memberId);

        return NumberUtils.trimNullToZero(count);
    }

}
