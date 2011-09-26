/*
 * Copyright 2011 MITIAN Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mitian.BaseTest;

/**
 * MemberServiceTest.java
 * 
 * @author baojun
 */
public class MemberServiceTest extends BaseTest {

    @Autowired
    private MemberService memberService;

    public void just_test() {
        System.out.println("test done");
    }

}
