/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.StatAgentDAO;
import com.mitian.airad.model.StatAgent;

/**
 * StatAppService.java
 * 
 * @author
 */
@Service
public class StatAgentService {
    @Autowired
    private StatAgentDAO statAgentDAO;

    /**
     * 应用程序（详细）
     * 
     * @param record
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<StatAgent> queryAngenDetailList(Map map) {
        List<StatAgent> list = statAgentDAO.queryAngenDetailList(map);
        return list;
    }

    /**
     * 应用程序（汇总）
     * 
     * @param record
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<StatAgent> queryAngenTotalList(Map map) {
        List<StatAgent> list = statAgentDAO.queryAngenTotalList(map);
        return list;
    }

    /**
     * 条数
     * 
     * @param record
     * @return
     */
    @SuppressWarnings("unchecked")
    public int selectAngenDetailListCount(Map map) {
        int row = statAgentDAO.selectAngenDetailList(map).size();
        return row;
    }

    @SuppressWarnings("unchecked")
    public int selectAngenTotalListCount(Map map) {
        int row = statAgentDAO.selectAngenTotalList(map).size();
        return row;
    }

    /**
     * 所有
     */
    public List<StatAgent> selectAngenDetailList(Map<String, String> map) {
        List<StatAgent> list = statAgentDAO.selectAngenDetailList(map);
        return list;
    }

    public List<StatAgent> selectAngenTotalList(Map<String, String> map) {
        List<StatAgent> list = statAgentDAO.selectAngenTotalList(map);
        return list;
    }

    /**
     * @return the statAgentDAO
     */
    public StatAgentDAO getStatAgentDAO() {
        return statAgentDAO;
    }

    /**
     * @param statAgentDAO the statAgentDAO to set
     */
    public void setStatAgentDAO(StatAgentDAO statAgentDAO) {
        this.statAgentDAO = statAgentDAO;
    }

}
