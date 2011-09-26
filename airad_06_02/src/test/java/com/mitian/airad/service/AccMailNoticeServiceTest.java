/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import org.easymock.EasyMock;
import org.junit.Before;

import com.mitian.airad.dao.AccMailNoticeDAO;
import com.mitian.airad.model.AccMailNotice;

/**
 * AccMailNoticeServiceTest.java
 * 
 * @author liyuhang
 */
public class AccMailNoticeServiceTest {
    private AccMailNotice notice;
    private AccMailNoticeDAO accMailNoticeDAO;
    private AccMailNoticeService noticeService;

    @Before
    public void setUp() {
        accMailNoticeDAO = EasyMock.createMock(AccMailNoticeDAO.class);
        notice = new AccMailNotice();
        noticeService = new AccMailNoticeService();
        noticeService.setAccMailNoticeDAO(accMailNoticeDAO);
    }
}
