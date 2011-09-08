package com.mitian.airad.dao;

import java.util.List;
import java.util.Map;

import com.mitian.airad.model.StatAppView;

public interface StatAppViewDAO {
    List<StatAppView> findByTimeList(Map<String, String> map);

    List<StatAppView> findTotalByTime(Map<String, String> map);

    List<StatAppView> findByTimeLists(Map<String, String> map);

    /**
     * 根据应用名称查找应用的视图详细信息
     * 
     * @param appName
     * @return
     */
    public StatAppView findByAppName(Map<String, Object> params);

    public Integer findCount(Map<String, String> map);
}
