/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.dao.CoreCampaignDAO;
import com.mitian.airad.model.CoreCampaign;

/**
 * CampaignServiceTest.java
 * 
 * @author liyuhang
 */
public class CampaignServiceTest {
    private CampaignService campaignService;
    private CoreCampaignDAO campaignDAO;
    private CoreCampaign campaign;

    @Before
    public void setUp() {
        campaignDAO = EasyMock.createMock(CoreCampaignDAO.class);
        campaignService = new CampaignService();
        campaign = new CoreCampaign();
        campaignService.setCoreCampaignDao(campaignDAO);
    }

    @Test
    public void testFindById() throws InvalidInfoException {
        campaign.setCampaignId(100);
        EasyMock.expect(campaignDAO.selectByPrimaryKey(100, 0L)).andReturn(campaign);
        EasyMock.replay(campaignDAO);

        CoreCampaign campaignTest = campaignService.findById("100", 0L);
        Assert.assertEquals(campaign, campaignTest);
        CoreCampaign nullCampaign = campaignService.findById(" ", 0L);
        Assert.assertNull(nullCampaign);
    }

    @Test(expected = NumberFormatException.class)
    public void testFindByIdWhenIdIsNotRightFormat() throws InvalidInfoException {
        campaignService.findById("test", null);
        campaignService.findById("199999999999999999999999999999999999999", null);
    }

}
