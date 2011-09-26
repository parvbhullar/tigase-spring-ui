/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * BaseTest.java 此test运行所有数据库的改动操作在单元测试结束后都将真实的反应在数据库中<br/>
 * 若对于单元测试操作数据库的记录想回滚请使用 {@link BaseTransactionalCallbackTest}
 * 
 * @author baojun
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test.xml")
public class BaseTest extends AbstractJUnit4SpringContextTests {
    @Test
    public void default_test() {
    }
}
