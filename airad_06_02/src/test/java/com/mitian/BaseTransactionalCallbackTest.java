/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * BaseTransactionalCallbackTest 此test运行所有数据库的改动操作在单元测试结束后都会进行数据回滚<br/>
 * 若对于单元测试操作数据库的记录想保持请使用 {@link BaseTest}
 * 
 * @author baojun
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test.xml")
public class BaseTransactionalCallbackTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Test
    public void default_test() {
    }
}
