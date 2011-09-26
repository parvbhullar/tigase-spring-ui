/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mitian.BaseTransactionalCallbackTest;

/**
 * CoreAdGroupDAOTest.java
 * 
 * @author baojun
 */
public class CoreAdGroupDAOTest extends BaseTransactionalCallbackTest {

    @Autowired
    private CoreAdDAO coreAdDAO;

    @Test
    public void test() {
        System.out.println(2);
    }
}
