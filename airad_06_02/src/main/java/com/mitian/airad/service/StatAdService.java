/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.StatAdDAO;
import com.mitian.airad.dao.StatAppViewDAO;
import com.mitian.airad.model.StatAd;

/**
 * StatAdService.java
 * 
 * @author wangzhongwei
 */
@Service
public class StatAdService {
    /** spring注入CoreAgentRelationDAO */
    @Autowired
    private StatAdDAO statAdDAO;
    @Autowired
    private StatAppViewDAO statAppViewDAO;

    /**
     * 某个用户的广告的总展示次数
     * 
     * @param map
     * @return
     */
    public StatAd queryAdShwoByMemberId(Map<String, String> map) {
        return statAdDAO.queryAdShwoByMemberId(map);
    }

    /**
     * 广告统计(详细)
     */
    public List<StatAd> querybyadDetailList(Map<String, String> map) {
        List<StatAd> list = statAdDAO.querybyadDetailList(map);
        return list;
    }

    /**
     * 统计广告
     * 
     * @param ad
     * @return
     */
    public List<StatAd> querybyadList(StatAd ad) {
        List<StatAd> list = statAdDAO.querybyadList(ad);
        return list;
    }

    /**
     * 广告统计(汇总)
     */
    public List<StatAd> querybyadTotalList(Map<String, String> map) {
        List<StatAd> list = statAdDAO.querybyadTotalList(map);
        return list;
    }

    /**
     * 统计广告组
     */
    @SuppressWarnings("unchecked")
    public List<StatAd> querybyadGroupList(StatAd record) {
        List<StatAd> list = statAdDAO.querybyadGroupList(record);
        return list;
    }

    /**
     * 广告组统计(详细)
     */
    public List<StatAd> querybyadGroupDetailList(Map<String, String> map) {
        List<StatAd> list = statAdDAO.querybyadGroupDetailList(map);
        return list;
    }

    /**
     * 广告组统计(汇总)
     */
    public List<StatAd> querybyadGroupTotalList(Map<String, String> map) {
        List<StatAd> list = statAdDAO.querybyadGroupTotalList(map);
        return list;
    }

    /**
     * 活动统计(详细)
     */
    public List<StatAd> queryCampaignDetailList(Map<String, String> map) {
        List<StatAd> list = statAdDAO.queryCampaignDetailList(map);
        return list;
    }

    /**
     * 活动统计(汇总)
     */
    public List<StatAd> queryCampaignTotalList(Map<String, String> map) {
        List<StatAd> list = statAdDAO.queryCampaignTotalList(map);
        return list;
    }

    /**
     * 总条数
     * 
     * @param map
     * @return
     */
    public int selectadDetailCount(Map<String, String> map) {
        int count = statAdDAO.selectadDetailList(map).size();
        return count;
    }

    public int selectadTotalCount(Map<String, String> map) {
        int count = statAdDAO.selectadTotalList(map).size();
        return count;
    }

    public int selectadGroupDetailCount(Map<String, String> map) {
        int count = statAdDAO.selectadGroupDetailList(map).size();
        return count;
    }

    public int selectadGroupTotalCount(Map<String, String> map) {
        int count = statAdDAO.selectadGroupTotalList(map).size();
        return count;
    }

    public int selectcampaignIdDetailCount(Map<String, String> map) {
        int count = statAdDAO.selectcampaignIdDetailList(map).size();
        return count;
    }

    public int selectcampaignIdTotalCount(Map<String, String> map) {
        int count = statAdDAO.selectcampaignIdTotalList(map).size();
        return count;
    }

    /**
     * 所有
     */
    public List<StatAd> selectadDetailList(Map<String, String> map) {
        List<StatAd> list = statAdDAO.selectadDetailList(map);
        return list;
    }

    /**
     * 统计活动
     * 
     * @param record
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<StatAd> queryCampaignbyList(StatAd record) {
        List<StatAd> list = statAdDAO.queryCampaignbyList(record);
        return list;
    }

    public List<StatAd> selectadTotalList(Map<String, String> map) {
        List<StatAd> list = statAdDAO.selectadTotalList(map);
        return list;
    }

    public List<StatAd> selectadGroupDetailList(Map<String, String> map) {
        List<StatAd> list = statAdDAO.selectadGroupDetailList(map);
        return list;
    }

    public List<StatAd> selectadGroupTotalList(Map<String, String> map) {
        List<StatAd> list = statAdDAO.selectadGroupTotalList(map);
        return list;
    }

    public List<StatAd> selectcampaignIdDetailList(Map<String, String> map) {
        List<StatAd> list = statAdDAO.selectcampaignIdDetailList(map);
        return list;
    }

    public List<StatAd> selectcampaignIdTotalList(Map<String, String> map) {
        List<StatAd> list = statAdDAO.selectcampaignIdTotalList(map);
        return list;
    }

    /**
     * @return the statAdDAO
     */
    public StatAdDAO getStatAdDAO() {
        return statAdDAO;
    }

    /**
     * @param statAdDAO the statAdDAO to set
     */
    public void setStatAdDAO(StatAdDAO statAdDAO) {
        this.statAdDAO = statAdDAO;
    }

    /**
     * @return the statAppViewDAO
     */
    public StatAppViewDAO getStatAppViewDAO() {
        return statAppViewDAO;
    }

    /**
     * @param statAppViewDAO the statAppViewDAO to set
     */
    public void setStatAppViewDAO(StatAppViewDAO statAppViewDAO) {
        this.statAppViewDAO = statAppViewDAO;
    }

}
