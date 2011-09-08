package com.mitian.airad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.AccIncomePayDAO;
import com.mitian.airad.model.AccIncomePay;

@Service
public class AccIncomePayService {

    @Autowired
    private AccIncomePayDAO accIncomePayDAO;

    /**
     * 往充值表里写充值记录
     * 
     * @param acctIncomePay
     */
    public void txInsertIncomePayRecord(AccIncomePay acctIncomePay) {
        accIncomePayDAO.insertSelective(acctIncomePay);

    }

}
