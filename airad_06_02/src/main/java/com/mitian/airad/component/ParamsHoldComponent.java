package com.mitian.airad.component;

import java.util.Map;

import com.mitian.airad.common.utils.StringUtils;

/**
 * 用于保存经常变更的参数
 * 
 * @author Administrator
 */
public class ParamsHoldComponent {
    private Map<String, String> holdMap;

    public String getString(String key) {
        String value = holdMap.get(key);
        return value;
    }

    public Integer getInteger(String key) {
        Integer num = 0;
        String value = holdMap.get(key);
        if (StringUtils.isNotBlank(value)) {
            num = Integer.parseInt(value);
        }
        return num;
    }

    public Map<String, String> getHoldMap() {
        return holdMap;
    }

    public void setHoldMap(Map<String, String> holdMap) {
        this.holdMap = holdMap;
    }
}
