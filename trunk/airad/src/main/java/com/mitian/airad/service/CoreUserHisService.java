/*
 * Copyright 2011 mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.CoreUserHisDAO;
import com.mitian.airad.model.CoreUserHis;

/**
 * UserHistoryInfoSservice.java
 * 
 * @author leifenghai
 */
@Service
public class CoreUserHisService {
    @Autowired
    private CoreUserHisDAO coreUserHisDAO;

    /**
     * 添加用户登录记录
     * 
     * @param historyInfo
     * @return
     */
    public String txAddUserLoginLog(CoreUserHis historyInfo) {
        String result = "succ";
        if (null != historyInfo) {
            Date date = new Date();
            historyInfo.setAddTime(date);
            historyInfo.setLoginTime(date);
            historyInfo.setUpdTime(date);
            historyInfo.setDelTime(date);
            historyInfo.setDelFlag("0");
            coreUserHisDAO.insertSelective(historyInfo);
            return result;
        }
        return "err";
    }

    public CoreUserHis queryCoreUserHisByMemberId(Long memberId) {
        CoreUserHis userHis = coreUserHisDAO.selectByMemberId(memberId);
        return userHis;
    }
}
