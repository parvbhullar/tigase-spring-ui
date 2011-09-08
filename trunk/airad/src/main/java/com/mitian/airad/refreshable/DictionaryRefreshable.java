/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.refreshable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.DictionaryDAO;
import com.mitian.airad.model.Dictionary;

/**
 * DictionaryRefreshable.java
 * 
 * @author liyuhang
 */
@Service
public class DictionaryRefreshable extends Refreshable {

    private static Map<String, Map<String, Dictionary>> dictionaryCache =
            new HashMap<String, Map<String, Dictionary>>();

    @Autowired
    private DictionaryDAO dictionaryDAO;

    @Override
    public void refresh() {
        List<Dictionary> list = dictionaryDAO.getAll();
        dictionaryCache.clear();
        for (Dictionary dictionary : list) {
            String typeKey = dictionary.getDictType();
            String valueKey = dictionary.getDictKey();
            Map<String, Dictionary> valueMap = dictionaryCache.get(typeKey);
            if (valueMap == null) {
                valueMap = new HashMap<String, Dictionary>();
            }
            valueMap.put(valueKey, dictionary);
            dictionaryCache.put(typeKey, valueMap);
        }
    }

    public String getValueByTypeKeyAndValueKey(String typeKey, String valueKey) {
        checkIfShouldRefresh();
        Map<String, Dictionary> valueMap = dictionaryCache.get(typeKey);
        if (valueMap != null) {
            Dictionary dictionary = valueMap.get(valueKey);
            if (dictionary != null) {
                return dictionary.getDictVal();
            }
        }
        return StringUtils.EMPTY;
    }

    public Dictionary getDictionaryByTypeKeyAndValue(String typeKey, String value) {
        checkIfShouldRefresh();
        Map<String, Dictionary> valueMap = dictionaryCache.get(typeKey);
        if (valueMap != null) {
            for (Dictionary dictionary : valueMap.values()) {
                if (dictionary.getDictVal().equals(value)) {
                    return dictionary;
                }
            }

        }
        return null;
    }

}
