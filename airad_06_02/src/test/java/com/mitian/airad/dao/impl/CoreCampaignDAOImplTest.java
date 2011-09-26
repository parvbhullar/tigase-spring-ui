/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;

import com.mitian.BaseTransactionalCallbackTest;
import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.dao.CoreCampaignDAO;
import com.mitian.airad.model.CoreCampaign;

/**
 * CoreCampaignDAOImplTest.java
 * 
 * @author baojun
 */
public class CoreCampaignDAOImplTest extends BaseTransactionalCallbackTest {

    @Autowired
    private CoreCampaignDAO campaignDAO;

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#deleteByPrimaryKey(java.lang.Integer, java.lang.Long)}.
     */
    @Test
    public void testDeleteByPrimaryKey() {
        int row = campaignDAO.deleteByPrimaryKey(1, 1L);
    }

    /**
     * Test method for {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#insert(com.mitian.airad.model.CoreCampaign)}
     * .
     */
    @Test
    public void testInsert() {
        CoreCampaign campaign = new CoreCampaign();
        campaign.setMemberId(1L);
        campaign.setCampaignName("test by baojun junittest");
        campaignDAO.insert(campaign);
    }

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#insertReturnId(com.mitian.airad.model.CoreCampaign)}.
     */
    @Test
    public void testInsertReturnId() {
        CoreCampaign campaign = new CoreCampaign();
        campaign.setMemberId(1L);
        campaign.setCampaignName("test by baojun junittest");
        campaignDAO.insertReturnId(campaign);
    }

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#selectByPrimaryKey(java.lang.Integer, java.lang.Long)}.
     */
    @Test
    public void testSelectByPrimaryKey() {
        CoreCampaign campaign = campaignDAO.selectByPrimaryKey(1, 1L);
    }

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#updateByPrimaryKeySelective(com.mitian.airad.model.CoreCampaign, java.lang.Long)}
     * .
     */
    @Test
    public void testUpdateByPrimaryKeySelective() {
        CoreCampaign campaign = new CoreCampaign();
        campaign.setCampaignId(1);
        campaign.setCampaignName("test by baojun junit");
        campaignDAO.updateByPrimaryKeySelective(campaign, 1L);
    }

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#updateByPrimaryKey(com.mitian.airad.model.CoreCampaign, java.lang.Long)}
     * .
     */
    @Test
    public void testUpdateByPrimaryKey() {
        CoreCampaign campaign = new CoreCampaign();
        campaign.setCampaignId(1);
        campaign.setCampaignName("test by baojun junit");
        campaignDAO.updateByPrimaryKey(campaign, 1L);
    }

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#txSuspend(com.mitian.airad.model.CoreCampaign)}.
     */
    @Test
    public void testTxSuspend() {
        CoreCampaign campaign = new CoreCampaign();
        campaign.setCampaignId(1);
        campaign.setCampaignName("test by baojun junit");
        campaignDAO.txSuspend(campaign);
    }

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#selectAdGroupId(com.mitian.airad.model.CoreCampaign)}.
     */
    @Test
    public void testSelectAdGroupId() {
        CoreCampaign campaign = new CoreCampaign();
        campaign.setMemberId(1L);
        campaign.setCampaignId(1);
        campaign.setCampaignName("test by baojun junit");
        campaignDAO.selectAdGroupId(campaign);
    }

    /**
     * Test method for {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#txCopy(com.mitian.airad.model.CoreCampaign)}
     * .
     * 
     * @throws InvalidInfoException
     */
    @Test
    @ExpectedException(value = InvalidInfoException.class)
    public void testTxCopy() throws InvalidInfoException {
        CoreCampaign campaign = new CoreCampaign();
        campaign.setCampaignId(1);
        campaign.setMemberId(1L);
        campaign.setCampaignName("test by baojun junit");
        campaignDAO.txCopy(campaign);
    }

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#batchAddExamlog2(java.util.List, java.lang.Integer)}.
     */
    @Test
    public void testBatchAddExamlog2() {
        // fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#selectAdAll(java.lang.String, java.lang.Long)}.
     */
    @Test
    public void testSelectAdAll() {
        campaignDAO.selectAdAll("1", 1L);
    }

    /**
     * Test method for {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#txDel(com.mitian.airad.model.CoreCampaign)}.
     */
    @Test
    public void testTxDel() {
        CoreCampaign campaign = new CoreCampaign();
        campaign.setCampaignId(1);
        campaign.setMemberId(1L);
        campaign.setCampaignName("test by baojun junit");
        campaignDAO.txDel(campaign);
    }

    /**
     * Test method for {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#selectByAll(java.util.Map)}.
     */
    @Test
    public void testSelectByAll() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("pageSize", "10");
        params.put("currentPage", "1");
        params.put("memberId", "1");
        campaignDAO.selectByAll(params);
    }

    /**
     * Test method for {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#selectStatisticByTimeSlot(java.util.Map)}.
     */
    @Test
    public void testSelectStatisticByTimeSlot() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("pageSize", "10");
        params.put("currentPage", "1");
        params.put("memberId", "1");
        campaignDAO.selectStatisticByTimeSlot(params);
    }

    /**
     * Test method for {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#totalCountByTimeSlot(java.util.Map)}.
     */
    @Test
    public void testTotalCountByTimeSlot() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("pageSize", "10");
        params.put("currentPage", "1");
        params.put("memberId", "1");
        campaignDAO.totalCountByTimeSlot(params);
    }

    /**
     * Test method for {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#totalCount(java.util.Map)}.
     */
    @Test
    public void testTotalCount() {
        // fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#queryBycondition(com.mitian.airad.model.CoreCampaign)}.
     */
    @Test
    public void testQueryBycondition() {
        CoreCampaign campaign = new CoreCampaign();
        campaign.setMemberId(1L);
        campaign.setCampaignName("test by baojun junit");
        campaignDAO.queryBycondition(campaign);
    }

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.CoreCampaignDAOImpl#findByNameList(com.mitian.airad.model.CoreCampaign)}.
     */
    @Test
    public void testFindByNameList() {
        CoreCampaign campaign = new CoreCampaign();
        campaign.setMemberId(1L);
        campaign.setCampaignName("test by baojun junit");
        campaignDAO.findByNameList(campaign);
    }

}
