/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.mitian.airad.dao.CoreAdDAO;
import com.mitian.airad.dao.CoreBannerDAO;
import com.mitian.airad.dao.CoreRichAdDAO;
import com.mitian.airad.dao.LibTaobaoDAO;
import com.mitian.airad.model.CoreBanner;
import com.mitian.airad.model.CoreRichAd;
import com.mitian.airad.model.LibTaobao;

/**
 * AdServiceTest.java
 * 
 * @author Administrator
 */
public class AdServiceTest {

    /**
     * Test method for {@link com.mitian.airad.service.AdService#findBanner(java.lang.Integer, java.lang.Long)}.
     */
    private CoreBannerDAO coreBannerDAO = EasyMock.createMock(CoreBannerDAO.class);
    private LibTaobaoDAO libTaobaoDao = EasyMock.createMock(LibTaobaoDAO.class);
    private CoreRichAdDAO coreRichAdDAO = EasyMock.createMock(CoreRichAdDAO.class);
    private CoreAdDAO coreAdDAO = EasyMock.createMock(CoreAdDAO.class);

    @Test
    public void testFindBanner() {

    }

    /**
     * Test method for
     * {@link com.mitian.airad.service.AdService#txAddCoreBanner(com.mitian.airad.model.CoreBanner, java.lang.Long)}.
     */
    @Test
    public void testTxAddCoreBanner() {
        // CoreBanner banner = new CoreBanner();
        // banner.setBannerBgCon("#a4d49d");
        // banner.setBannerBgType("1");
        // banner.setBannerColor("#00bff3");
        // banner.setBannerGrain("半透明遮盖层");
        // banner.setBannerHtml("<div>实打实的</div>");
        // banner.setBannerIconCon("#a4d49d");
        // banner.setAdId(123);
        // banner.setBannerModelName("小图加文字");
        // banner.setBannerText("阿斯蒂芬撒");
        // banner.setBannerText2("弃我而去我饿");
        // banner.setBannerType("1");
        // banner.setMemberId(1L);
        //
        // EasyMock.expect(coreBannerDAO.insertSelective(banner, 1L)).andReturn(1);
        // EasyMock.replay(coreBannerDAO);
        // CoreAd coreAd = new CoreAd();
        // coreAd.setAdId(123);
        // coreAd.setBannerId(1);
        // coreAd.setMemberId(1L);
        // EasyMock.expect(coreAdDAO.updateByPrimaryKeySelective(coreAd)).andReturn(1);
        // EasyMock.replay(coreAdDAO);
        // AdService adService = new AdService();
        // adService.setCoreBannerDAO(coreBannerDAO);
        // int bannerId = adService.txAddCoreBanner(banner, 1l);
        // Assert.assertEquals((Integer) bannerId, (Integer) 1);
    }

    /**
     * Test method for
     * {@link com.mitian.airad.service.AdService#txUpdateCoreBannerByAdId(com.mitian.airad.model.CoreBanner, java.lang.Long)}
     * .
     */
    @Test
    public void testTxUpdateCoreBannerByAdId() {
        CoreBanner banner = new CoreBanner();
        banner.setBannerBgCon("#a4d49d");
        banner.setBannerBgType("1");
        banner.setBannerColor("#00bff3");
        banner.setBannerGrain("半透明遮盖层");
        banner.setBannerHtml("<div>实打实的</div>");
        banner.setBannerIconCon("#a4d49d");
        banner.setAdId(123);
        banner.setBannerModelName("小图加文字");
        banner.setBannerText("阿斯蒂芬撒");
        banner.setBannerText2("弃我而去我饿");
        banner.setBannerType("1");
        banner.setMemberId(1L);

        EasyMock.expect(coreBannerDAO.updateByPrimaryKeySelective(banner, 1L)).andReturn(1);
        EasyMock.replay(coreBannerDAO);
        AdService adService = new AdService();
        adService.setCoreBannerDAO(coreBannerDAO);
        int bannerId = adService.txUpdateCoreBannerByAdId(banner, 1L);
        Assert.assertEquals((Integer) bannerId, (Integer) 1);
    }

    @Test
    public void testTxAddCoreRichForTaobaoTrain() {
        CoreRichAd coreRichAd = new CoreRichAd();
        coreRichAd.setShowType("6");
        coreRichAd.setRichId(123);
        LibTaobao libTaobao = new LibTaobao();
        libTaobao.setRichId(123);
        libTaobao.setStatus("1");
        libTaobao.setTaobaoUrl("asdfasd");
        coreRichAd.setLibTaobao(libTaobao);
        EasyMock.expect(libTaobaoDao.insertSelective(libTaobao)).andReturn(1);
        EasyMock.replay(libTaobaoDao);
        EasyMock.expect(coreRichAdDAO.updateRelByPrimaryKey(coreRichAd, null)).andReturn(1);
        EasyMock.replay(coreRichAdDAO);

        AdService adService = new AdService();
        adService.setLibTaobaoDao(libTaobaoDao);
        adService.setCoreRichAdDAO(coreRichAdDAO);
        CoreRichAd rich = adService.txAddCoreRich(coreRichAd, null);
        Assert.assertEquals("1", rich.getRelId());
    }
}
