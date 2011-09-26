package com.mitian.airad.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.AccIncomeDAO;
import com.mitian.airad.model.AccIncome;

/**
 * AppIncomeService.java
 * 
 * @author baojun
 */
@Service
public class AppIncomeService {
    /** spring注入AccIncomeDAO */

    @Autowired
    private AccIncomeDAO accIncomeDAO;

    /**
     * 应用程序收益分页
     * 
     * @param coreCampaign
     */
    public List<AccIncome> queryList(Map<String, String> params) {
        List<AccIncome> listBean = accIncomeDAO.selectByAll(params);
        return listBean;
    }

    /**
     * 查找总条数
     * 
     * @param coreApp
     * @return
     */
    public int queryCount(Long memberId) {
        int k = accIncomeDAO.totalCount(memberId);
        return k;
    }

    /**
     * @return the accIncomeDAO
     */
    public AccIncomeDAO getAccIncomeDAO() {
        return accIncomeDAO;
    }

    /**
     * @param accIncomeDAO the accIncomeDAO to set
     */
    public void setAccIncomeDAO(AccIncomeDAO accIncomeDAO) {
        this.accIncomeDAO = accIncomeDAO;
    }

}
