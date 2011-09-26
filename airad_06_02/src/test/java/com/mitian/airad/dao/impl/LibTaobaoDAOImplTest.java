/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mitian.BaseTransactionalCallbackTest;
import com.mitian.airad.dao.LibTaobaoDAO;
import com.mitian.airad.model.LibTaobao;

/**
 * LibTaobaoDAOImplTest.java
 * 
 * @author Administrator
 */
public class LibTaobaoDAOImplTest extends BaseTransactionalCallbackTest {
    @Autowired
    private LibTaobaoDAO libTaobaoDAO;

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.LibTaobaoDAOImpl#insertSelective(com.mitian.airad.model.LibTaobao)}.
     */
    @Test
    public void testInsertSelective() {
        LibTaobao libTaobao = new LibTaobao();
        libTaobao.setRichId(-3);
        libTaobao.setStatus("1");
        libTaobao.setTaobaoUrl("1");
        Integer taobaoId = libTaobaoDAO.insertSelective(libTaobao);
        LibTaobao taobao = libTaobaoDAO.selectByPrimaryKey(taobaoId);
        if (taobao != null) {
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.LibTaobaoDAOImpl#updateByPrimaryKeySelective(com.mitian.airad.model.LibTaobao)}.
     */
    @Test
    public void testUpdateByPrimaryKeySelective() {
        LibTaobao libTaobao = new LibTaobao();
        libTaobao.setRichId(-4);
        libTaobao.setStatus("1");
        libTaobao.setTaobaoUrl("1");
        int taobaoId = libTaobaoDAO.insertSelective(libTaobao);
        LibTaobao taobao = libTaobaoDAO.selectByPrimaryKey(taobaoId);
        taobao.setRichId(-5);
        libTaobaoDAO.updateByPrimaryKeySelective(taobao);
        LibTaobao taobao2 = libTaobaoDAO.selectByPrimaryKey(taobaoId);
        if (taobao2.getRichId() == -5) {
            Assert.assertTrue(true);
        }
        else {
            Assert.assertTrue(false);
        }
    }

}
