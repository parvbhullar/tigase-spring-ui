package com.mitian.airad.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.AccIncomeDAO;
import com.mitian.airad.model.AccIncome;

@Service
public class AppIncomeService {
    /** spring注入AccIncomeDAO */

    @Autowired
    private AccIncomeDAO AccIncomeDAO;

    /**
     * 应用程序收益分页
     * 
     * @param coreCampaign
     */
    public List<AccIncome> queryList(Map<String, String> params) {
        List<AccIncome> listBean = AccIncomeDAO.selectByAll(params);
        return listBean;
    }

    /**
     * 查找总条数
     * 
     * @param coreApp
     * @return
     */
    public int queryCount(Long memberId) {
        int k = AccIncomeDAO.totalCount(memberId);
        return k;
    }

}
