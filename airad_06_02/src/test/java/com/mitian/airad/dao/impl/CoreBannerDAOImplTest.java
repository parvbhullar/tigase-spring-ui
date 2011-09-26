/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mitian.BaseTransactionalCallbackTest;
import com.mitian.airad.dao.CoreBannerDAO;
import com.mitian.airad.model.CoreBanner;

/**
 * CoreBannerDAOImplTest.java
 * 
 * @author Administrator
 */
public class CoreBannerDAOImplTest extends BaseTransactionalCallbackTest {

    @Autowired
    private CoreBannerDAO bannerDAO;

    /**
     * Test method for {@link com.mitian.airad.dao.impl.CoreBannerDAOImpl#insert(com.mitian.airad.model.CoreBanner)}.
     */
    @Test
    public void testInsertSelective() {
        CoreBanner banner = new CoreBanner();
        banner.setBannerBgCon("#a4d49d");
        banner.setBannerBgType("1");
        banner.setBannerColor("#00bff3");
        banner.setBannerGrain("半透明遮盖层");
        banner.setBannerHtml("<div>实打实的</div>");
        banner.setBannerIconCon("#a4d49d");
        banner.setAdId(-1000);
        banner.setBannerModelName("小图加文字");
        banner.setBannerText("阿斯蒂芬撒");
        banner.setBannerText2("弃我而去我饿");
        banner.setBannerType("1");
        banner.setMemberId(1L);
        int bannerId = bannerDAO.insertSelective(banner, 1L);
        CoreBanner banner2 = bannerDAO.selectByPrimaryKey(bannerId, 1L);
        if (banner2.getBannerColor() != null && banner2.getBannerHtml() != null && banner2.getBannerGrain() != null
                && banner2.getBannerModelName() != null) {
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.CoreBannerDAOImpl#updateByPrimaryKeySelective(com.mitian.airad.model.CoreBanner)}
     * .
     */
    @Test
    public void testUpdateByPrimaryKeySelective() {
        CoreBanner banner = new CoreBanner();
        banner.setBannerBgCon("#a4d49d");
        banner.setBannerBgType("1");
        banner.setBannerColor("#00bff3");
        banner.setBannerGrain("半透明遮盖层");
        banner.setBannerHtml("<div>实打实的</div>");
        banner.setBannerIconCon("#a4d49d");
        banner.setAdId(-1000);
        banner.setBannerModelName("小图加文字");
        banner.setBannerText("阿斯蒂芬撒");
        banner.setBannerText2("弃我而去我饿");
        banner.setBannerType("1");
        banner.setMemberId(1L);
        int bannerId = bannerDAO.insertSelective(banner, 1L);
        banner.setAdId(-2000);
        bannerDAO.updateByPrimaryKeySelective(banner, 1L);
        CoreBanner banner2 = bannerDAO.selectByPrimaryKey(bannerId, 1L);
        if (banner2.getAdId() == -2000) {
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }

    }

}
