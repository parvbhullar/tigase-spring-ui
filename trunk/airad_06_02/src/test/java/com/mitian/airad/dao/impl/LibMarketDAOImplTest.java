/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mitian.BaseTransactionalCallbackTest;
import com.mitian.airad.dao.LibMarketDAO;

/**
 * LibMarketDAOImplTest.java
 * 
 * @author Administrator
 */

public class LibMarketDAOImplTest extends BaseTransactionalCallbackTest {
    @Autowired
    private LibMarketDAO libMarketDAO;

    /**
     * Test method for {@link com.mitian.airad.dao.impl.LibMarketDAOImpl#insert(com.mitian.airad.model.LibMarket)}.
     */
    @Test
    public void testInsert() {
        // LibMarket libMarket = new LibMarket();
        // libMarket.setRichId(-3);
        // libMarket.setStatus("1");
        // libMarket.setMarketUrl("1");
        // Integer marketId = libMarketDAO.insert(libMarket);
        // LibMarket libMarket2 = libMarketDAO.selectByPrimaryKey(marketId);
        // if (libMarket2 != null) {
        // Assert.assertTrue(true);
        // }
        // else {
        // Assert.assertTrue(false);
        // }
    }
}
