/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.refreshable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.SysConfigDAO;
import com.mitian.airad.model.SysConfig;

/**
 * SysConfigRefreshable.java
 * 
 * @author baojun
 */
@Service
public class SysConfigRefreshable extends Refreshable {

    private static Map<String, Map<String, SysConfig>> sysConfig = new HashMap<String, Map<String, SysConfig>>();

    @Autowired
    private SysConfigDAO sysConfigDAO;

    @Override
    public void refresh() {
        List<SysConfig> list = sysConfigDAO.getAll();
        sysConfig.clear();
        for (int i = 0; i < list.size(); i++) {
            SysConfig config = list.get(i);
            String typeKey = config.getSysType();
            String valueKey = config.getSysKey();
            Map<String, SysConfig> valueMap = sysConfig.get(typeKey);
            if (valueMap == null) {
                valueMap = new HashMap<String, SysConfig>();
            }
            valueMap.put(valueKey, config);
            sysConfig.put(typeKey, valueMap);
        }
    }

    public String getValueByTypeKeyAndValueKey(String typeKey, String valueKey) {
        checkIfShouldRefresh();
        Map<String, SysConfig> valueMap = sysConfig.get(typeKey);
        if (valueMap != null) {
            SysConfig config = valueMap.get(valueKey);
            if (config != null) {
                return config.getSysVal();
            }
        }
        return StringUtils.EMPTY;
    }

    public SysConfig getSysConfigByTypeKeyAndValue(String typeKey, String value) {
        checkIfShouldRefresh();
        Map<String, SysConfig> valueMap = sysConfig.get(typeKey);
        if (valueMap != null) {
            for (Object o : valueMap.values()) {
                SysConfig sysConfig = (SysConfig) o;
                if (sysConfig.getSysVal().equals(value)) {
                    return sysConfig;
                }
            }

        }
        return null;
    }

    // bug 477 测试用，看缓存是否刷新标志有问题
    public int getCacheSize() {
        return sysConfig.size();
    }
}
