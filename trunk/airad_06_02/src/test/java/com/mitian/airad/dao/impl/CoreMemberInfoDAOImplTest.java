/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.dao.impl;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mitian.BaseTransactionalCallbackTest;
import com.mitian.airad.dao.CoreMemberInfoDAO;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.web.auth.roles.RoleFactory;

/**
 * CoreMemberInfoDAOImplTest.java
 * 
 * @author Administrator
 */
public class CoreMemberInfoDAOImplTest extends BaseTransactionalCallbackTest {
    @Autowired
    private CoreMemberInfoDAO coreMemberInfoDao;

    /**
     * Test method for
     * {@link com.mitian.airad.dao.impl.CoreMemberInfoDAOImpl#updateByPrimaryKeySelective(com.mitian.airad.model.CoreMemberInfo)}
     * .
     */
    @Test
    public void testUpdateByPrimaryKeySelective() {
        CoreMemberInfo coreMemberInfo = new CoreMemberInfo();
        coreMemberInfo.setMemberType(RoleFactory.ROLE_DEVELOPERS);
        coreMemberInfo.setMemberId(Long.getLong("337"));
        coreMemberInfo.setQq("111111");
        int count = 0;
        count = coreMemberInfoDao.updateByPrimaryKeySelective(coreMemberInfo);
        System.out.println("count:----------------------------" + count);
    }

    @Test
    public void testGetbyEmail() {
        CoreMemberInfo memberInfo = coreMemberInfoDao.selectByEmail("toby941@gmail.com");
        System.out.println(ToStringBuilder.reflectionToString(memberInfo));
    }
}
