/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.dao.CoreWapAdDAO;
import com.mitian.airad.model.CoreWapAd;

/**
 * WapService.java 贫媒体wap信息
 * 
 * @author wangzhongwei
 */
@Service
public class WapService {
    /** spring注入CoreAgentRelationDAO */
    @Autowired
    private CoreWapAdDAO coreWapAdDAO;

    /**
     * 插入贫媒体wap信息
     * 
     * @param wapAd
     * @return
     */
    public Map<String, Object> txAddCoreWapAd(CoreWapAd wapAd, Long memberId) {
        Map<String, Object> result = new HashMap<String, Object>(1);
        if (StringUtils.isNotBlank(wapAd.getAdId().toString())) {
            result.put("wapAd", wapAd);
            coreWapAdDAO.insertSelective(wapAd, memberId);
        }
        return result;
    }

    /**
     * 根据贫媒体wapid查询wap信息
     * 
     * @param wapAd
     * @return
     */
    // public CoreWapAd findCoreWapAdByWapId(Integer wapId, Long memberId) {
    // CoreWapAd coreWapAd = new CoreWapAd();
    // if (StringUtils.isNotBlank(wapId.toString())) {
    // coreWapAd = coreWapAdDAO.selectByPrimaryKey(wapId, memberId);
    // }
    // return coreWapAd;
    // }

    /**
     * 根据贫媒体aDId查询wap信息
     * 
     * @param wapAd
     * @return
     */
    public CoreWapAd findCoreWapAdByAdId(Integer adId, Long memberId) {
        CoreWapAd coreWapAd = new CoreWapAd();
        if (StringUtils.isNotBlank(adId.toString())) {
            coreWapAd = coreWapAdDAO.findByAdId(adId, memberId);
        }
        return coreWapAd;
    }

    /**
     * 修改贫媒体wap信息
     * 
     * @param wapAd
     * @return
     */
    public Map<String, Object> txUpdateCoreWapAd(CoreWapAd coreWapAd, Long memberId) {
        Map<String, Object> result = new HashMap<String, Object>(1);
        if (StringUtils.isNotBlank(coreWapAd.getAdId().toString())) {
            int id = coreWapAdDAO.updateByPrimaryKeySelective(coreWapAd, memberId);
            result.put("id", id);
        }
        return result;
    }

    /**
     * @return the coreWapAdDAO
     */
    public CoreWapAdDAO getCoreWapAdDAO() {
        return coreWapAdDAO;
    }

    /**
     * @param coreWapAdDAO the coreWapAdDAO to set
     */
    public void setCoreWapAdDAO(CoreWapAdDAO coreWapAdDAO) {
        this.coreWapAdDAO = coreWapAdDAO;
    }
}
