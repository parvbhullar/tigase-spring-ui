/*
 * Copyright 2011 mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.CoreAdvertiserImgDAO;
import com.mitian.airad.model.CoreAdvertiserImg;

/**
 * advertiserImgService.java
 * 
 * @author leifenghai
 */
@Service
public class AdvertiserImgService {

    @Autowired
    private CoreAdvertiserImgDAO coreAdvertiserImgDAO;

    /**
     * 保存图片
     * 
     * @param coreAdvertiser
     * @return
     */
    public Map<String, String> txAddAdvertiserImg(CoreAdvertiserImg coreAdvertiserImg) {
        Map<String, String> result = new HashMap<String, String>();
        if (null == coreAdvertiserImg) {
            return result;
        }
        coreAdvertiserImgDAO.insertSelective(coreAdvertiserImg);
        return result;
    }

    /**
     * 根据Id查询图片
     * 
     * @param id
     * @return
     */
    public CoreAdvertiserImg queryAdvertiserImg(Integer id) {
        CoreAdvertiserImg coreAdvertiserImg = new CoreAdvertiserImg();
        if (id > 0) {
            coreAdvertiserImg = coreAdvertiserImgDAO.selectByPrimaryKey(id);
        }
        return coreAdvertiserImg;
    }

    /**
     * 每天认证的次数
     * 
     * @param record
     * @return
     */
    public Integer selectAuthentication(Map<String, String> map) {
        return coreAdvertiserImgDAO.selectAuthentication(map);
    }

    /**
     * 更新验证信息
     * 
     * @param IMAGE_TYPE_MAP
     * @return
     */
    public Integer updateAdvertiserImg(CoreAdvertiserImg coreAdvertiserImg) {
        return coreAdvertiserImgDAO.updateAdvertiserImgBymemberId(coreAdvertiserImg);
    }
}
