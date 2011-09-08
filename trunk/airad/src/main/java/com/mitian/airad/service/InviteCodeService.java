/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.dao.CoreInviteCodeDAO;
import com.mitian.airad.model.CoreInviteCode;

/**
 * 会员注册邀请信息业务 InviteCodeService.java
 * 
 * @author WANGZHONGWEI
 */
@Service
public class InviteCodeService {
    /** spring注入CoreInviteCodeDAO */
    @Autowired
    public CoreInviteCodeDAO coreInviteCodeDAO;

    /**
     * 添加 会员注册邀请
     * 
     * @param coreAgentRelation
     * @return
     */
    public Map<String, Object> txCreateInviteCode(CoreInviteCode coreInviteCode) {
        Map<String, Object> result = new HashMap<String, Object>(1);
        if (StringUtils.isNotBlank(coreInviteCode.getMemberId().toString())) {
            result.put("coreAgentRelation", coreInviteCode);
            coreInviteCodeDAO.insertSelective(coreInviteCode);

        }
        return result;
    }

    /**
     * 修改邀请码状态
     * 
     * @param coreInviteCode
     * @return
     */
    public Map<String, Object> txUudateInviteCodeStat(CoreInviteCode coreInviteCode) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(coreInviteCode.getCodeId().toString())) {
            coreInviteCodeDAO.updateByPrimaryKeySelective(coreInviteCode);
            result.put("coreInviteCode", coreInviteCode);
        }
        return result;
    }

    /**
     * 根据邀请码查询邀请信息
     * 
     * @param coreInviteCode
     * @return
     */
    public CoreInviteCode selectInviteByCode(String code) {
        CoreInviteCode cic = null;
        if (StringUtils.isNotBlank(code)) {
            cic = coreInviteCodeDAO.selectInviteByCode(code);
        }
        return cic;
    }

}
