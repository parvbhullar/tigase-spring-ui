package com.mitian.airad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.dao.AccountInfoViewDAO;
import com.mitian.airad.model.AccountInfoView;

@Service
public class TradeService {
    @Autowired
    private AccountInfoViewDAO accountInfoViewDAO;

    /**
     * 查询余额
     * 
     * @return
     */
    public AccountInfoView findMoneyById(Long memberId) {
        AccountInfoView aiv = new AccountInfoView();
        if (StringUtils.isNotBlank(memberId.toString())) {
            aiv = accountInfoViewDAO.findByAccountInfo(memberId);
        }
        return aiv;
    }
}
