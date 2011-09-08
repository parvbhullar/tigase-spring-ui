/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.util.List;

import com.mitian.airad.model.BannerImg;
import com.mitian.airad.model.BannerText;
import com.mitian.airad.model.CoreBanner;

/**
 * BannerService.java
 * 
 * @author baojun
 */
public class BannerService {

    /**
     * 获取目前所有可用模板配置 初次访问读取数据库配置，后续访问缓存对象
     * 
     * @return json配置文件
     */
    public String getAllUsedBannerTemplete() {
        return null;
    }

    /**
     * 根据模板类型获取模板URL
     * 
     * @param typeId
     * @return
     */
    public String getBannerURLbyTempleteId(String typeId) {
        return null;
    }

    /**
     * banner添加
     * 
     * @param banner
     * @return
     */
    public int txAddBanner(CoreBanner banner, List<BannerText> texts, List<BannerImg> imgs, Long memberId, int adId) {
        return 0;
    }

    /**
     * 获取banner图片
     * 
     * @param bannerId
     * @param type 图片类型
     * @return
     */
    public List<BannerImg> getBannerImg(int bannerId, String type) {
        return null;
    }

    /**
     * 保存banner图片
     * 
     * @param imgs
     * @return
     */
    public int saveBannerImg(List<BannerImg> imgs) {
        return 0;
    }

    /**
     * 获取banner文本内容
     * 
     * @param bannerId
     * @return
     */
    public List<BannerText> getBannerText(int bannerId) {
        return null;
    }

    /**
     * 保存banner文本内容
     * 
     * @param imgs
     * @return
     */
    public int saveBannerText(List<BannerText> texts) {
        return 0;
    }
}
