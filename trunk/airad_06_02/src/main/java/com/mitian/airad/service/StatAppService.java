/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.StatAppDAO;
import com.mitian.airad.dao.StatAppViewDAO;
import com.mitian.airad.model.StatApp;
import com.mitian.airad.model.StatAppView;

/**
 * StatAppService.java
 * 
 * @author
 */
@Service
public class StatAppService {
    @Autowired
    private StatAppDAO statAppDAO;
    @Autowired
    private StatAppViewDAO statAppViewDAO;

    /**
     * 应用程序（详细） 统计查询(分页)
     * 
     * @param record
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<StatApp> queryDetailList(Map map) {
        List<StatApp> list = statAppDAO.queryDetailList(map);
        return list;
    }

    /**
     * 应用程序（汇总）
     * 
     * @param record
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<StatApp> queryTotalList(Map map) {
        List<StatApp> list = statAppDAO.queryTotalList(map);
        return list;
    }

    /**
     * 条数
     * 
     * @param record
     * @return
     */
    @SuppressWarnings("unchecked")
    public int selectappDetailCount(Map map) {
        int row = statAppDAO.selectappDetailList(map).size();
        return row;
    }

    @SuppressWarnings("unchecked")
    public int selectappTotalCount(Map map) {
        int row = statAppDAO.selectappTotalList(map).size();
        return row;
    }

    /**
     * 所有
     */
    public List<StatApp> selectappDetailList(Map<String, String> map) {
        List<StatApp> list = statAppDAO.selectappDetailList(map);
        return list;
    }

    public List<StatApp> selectappTotalList(Map<String, String> map) {
        List<StatApp> list = statAppDAO.selectappTotalList(map);
        return list;
    }

    /**
     * 统计查询(所有)
     * 
     * @param map
     * @return
     */
    public List<StatAppView> findByTimeLists(Map<String, String> map) {
        List<StatAppView> list = statAppViewDAO.findByTimeList(map);
        return list;
    }

    /**
     * 图表统计
     * 
     * @param map
     * @return
     */
    public List<StatAppView> findTotalByTime(Map<String, String> map) {
        List<StatAppView> list = statAppViewDAO.findTotalByTime(map);
        return list;
    }

    /**
     * 查找总条数
     * 
     * @param map
     * @return
     */
    public Integer findCount(Map<String, String> map) {
        Integer count = statAppViewDAO.findCount(map);
        return count;
    }

    /**
     * ----------------------------------------------------------------author:tangwenjun--------------------------------
     */

    /**
     * 根据应用名称查找应用统计信息
     * 
     * @param appName
     * @return
     */
    public StatAppView queryByAppCode(String appCode) {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("appCode", appCode);
        params.put("startTime", null);
        params.put("endTime", new Date());
        StatAppView statAppView = statAppViewDAO.findByAppName(params);
        return statAppView;
    }
}
