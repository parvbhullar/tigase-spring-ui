/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.CoreAdGroupextendDAO;
import com.mitian.airad.model.CoreAdGroupextend;

/**
 * 会员账户信息
 * 
 * @author chenji
 */
@Service
public class AdGroupExtendService {
    /** spring注入CoreAdGroupDAO */
    @Autowired
    private CoreAdGroupextendDAO adGroupextendDAO;

    /**
     * 增加广告组信息
     * 
     * @param adGroupextend
     */
    public void txAddAdGroupExtend(CoreAdGroupextend adGroupextend) {
        adGroupextendDAO.insertSelective(adGroupextend);
    }

    /**
     * 根据信息查找广告组
     * 
     * @param adGroupextendId
     */
    public CoreAdGroupextend queryAdGroupExtendById(Integer adGroupId) {
        return adGroupextendDAO.selectByAdGroupId(adGroupId);
    }

    /**
     * 根据信息跟新广告组
     * 
     * @param record
     */
    public void txUpdateAdGroupExtend(CoreAdGroupextend adGroupextend) {
        adGroupextendDAO.updateByPrimaryKeySelective(adGroupextend);
    }

    /**
     * @return the adGroupextendDAO
     */
    public CoreAdGroupextendDAO getAdGroupextendDAO() {
        return adGroupextendDAO;
    }

    /**
     * @param adGroupextendDAO the adGroupextendDAO to set
     */
    public void setAdGroupextendDAO(CoreAdGroupextendDAO adGroupextendDAO) {
        this.adGroupextendDAO = adGroupextendDAO;
    }
}
