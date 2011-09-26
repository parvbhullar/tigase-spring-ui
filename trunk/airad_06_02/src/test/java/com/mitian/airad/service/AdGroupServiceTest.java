/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.mitian.airad.common.exception.InvalidInfoException;
import com.mitian.airad.dao.CoreAdGroupDAO;
import com.mitian.airad.model.CoreAdGroup;

/**
 * AdGroupServiceTest.java
 * 
 * @author liyuhang
 */
public class AdGroupServiceTest {
    private AdGroupService adGroupService;
    private CoreAdGroupDAO adGroupDAO;
    private CoreAdGroup adGroup;

    @Before
    public void setUp() {
        adGroupDAO = EasyMock.createMock(CoreAdGroupDAO.class);
        adGroupService = new AdGroupService();
        adGroup = new CoreAdGroup();
        adGroupService.setCoreAdGroupDAO(adGroupDAO);
    }

    @Test
    public void testQueryAdGroupByAdGroupId() throws InvalidInfoException {
        adGroup.setAdGroupId(100);
        EasyMock.expect(adGroupDAO.findByPrimaryKey(100, 0L)).andReturn(adGroup);
        EasyMock.replay(adGroupDAO);

        CoreAdGroup testAdGroup = adGroupService.queryAdGroupByAdGroupId(100, 0L);
        Assert.assertEquals(adGroup, testAdGroup);
    }

    @Test(expected = NumberFormatException.class)
    public void testFindByIdWhenIdIsNotRightFormat() throws InvalidInfoException {
        adGroupService.findById("test", null);
        adGroupService.findById("199999999999999999999999999999999999999", null);
    }

}
