package com.mitian.airad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.CoreMoneyCountDAO;
import com.mitian.airad.model.CoreMoneyCount;

/**
 * MoneyCountService.java
 * 
 * @author baojun
 */
@Service
public class MoneyCountService {
    /** spring注入CoreMoneyCountDAO */
    @Autowired
    private CoreMoneyCountDAO moneyCountDAO;

    /**
     * 根据会员ID查找帐户金额表的详细情况
     * 
     * @param memberId
     * @return
     */
    public CoreMoneyCount queryById(Long memberId) {
        return moneyCountDAO.selectByMemberId(memberId);
    }

    public void txEditMoneyCount(CoreMoneyCount moneyCount) {
        moneyCountDAO.updateByPrimaryKeySelective(moneyCount);
    }
}
