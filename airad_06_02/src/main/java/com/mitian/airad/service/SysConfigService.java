/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.impl.SysConfigDAOImpl;
import com.mitian.airad.model.SysConfig;

/**
 * @author
 */
@Service
public class SysConfigService {
    /** spring注入CoreAdGroupDAO */
    @Autowired
    private SysConfigDAOImpl sysConfigDAOImpl;

    /**
     * 根据ID查找
     * 
     * @param memberId
     * @return
     */
    public SysConfig querySysConfigById() {
        // -1111是默认插入的值
        SysConfig sysConfig = sysConfigDAOImpl.selectByPrimaryKey(-1111);
        if (sysConfig == null) {
            sysConfig = new SysConfig();
        }
        return sysConfig;
    }

    /**
     * 根据ID更新
     * 
     * @param memberId
     * @return
     */
    public void updateSysConfigById() {
        SysConfig sysConfig = new SysConfig();
        // sysConfig.setPublish(1);
        sysConfig.setSysVal("1");
        sysConfig.setSysConfigId(-1111);
        sysConfigDAOImpl.updateByPrimaryKeySelective(sysConfig);
    }

    /**
     * @return the sysConfigDAOImpl
     */
    public SysConfigDAOImpl getSysConfigDAOImpl() {
        return sysConfigDAOImpl;
    }

    /**
     * @param sysConfigDAOImpl the sysConfigDAOImpl to set
     */
    public void setSysConfigDAOImpl(SysConfigDAOImpl sysConfigDAOImpl) {
        this.sysConfigDAOImpl = sysConfigDAOImpl;
    }
}
