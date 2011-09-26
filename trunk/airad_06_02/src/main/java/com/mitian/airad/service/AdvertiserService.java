/*
 * Copyright 2011 mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.CoreAdvertiserDAO;
import com.mitian.airad.dao.CoreMemberInfoDAO;
import com.mitian.airad.model.CoreAdvertiser;
import com.mitian.airad.model.CoreAdvertiserImg;
import com.mitian.airad.model.CoreMemberInfo;
import com.mitian.airad.web.auth.roles.BaseRole;
import com.mitian.airad.web.auth.roles.RoleFactory;

/**
 * AdvertiserService.java
 * 
 * @author leifenghai
 */
@Service
public class AdvertiserService {

    @Autowired
    private CoreAdvertiserDAO coreAdvertiserDAO;
    @Autowired
    private AdvertiserImgService advertiserImgService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CoreMemberInfoDAO coreMemberInfoDAO;

    public Map<String, Object> txInsertAdvertiser(CoreAdvertiser coreAdvertiser, CoreAdvertiserImg coreAdvertiserImg) {
        Map<String, Object> reults = new HashMap<String, Object>();
        if (null != coreAdvertiser) {
            coreAdvertiser.setAduitStatus("1");
            coreAdvertiser.setAddTime(new Date());
            int advertiserId = coreAdvertiserDAO.insertSelective(coreAdvertiser);
            if (advertiserId > 0) {
                coreAdvertiserImg.setAdvertiserId(advertiserId);
                advertiserImgService.txAddAdvertiserImg(coreAdvertiserImg);
            }

        }
        return reults;
    }

    public int updateByMemberId(CoreAdvertiser coreAdvertiser) {
        return coreAdvertiserDAO.updateByPrimaryKeySelective(coreAdvertiser);
    }

    public void updateByPrimaryKeyMemberId(CoreAdvertiser record) {
        coreAdvertiserDAO.updateByPrimaryKeyMemberId(record);
    }

    /**
     * 认证广告商
     * 
     * @return
     */
    public void txAdvertiserCertification(Long id, BaseRole role) {
        // 查询用户信息
        CoreMemberInfo member = new CoreMemberInfo();
        member.setMemberId(id);
        // 开发者或普通会员需要身份认证
        if (role.isDev()) {
            member.setMemberType(RoleFactory.ROLE_ADV_AND_DEV);
        }
        else if (role.isGeneral()) {
            member.setMemberType(RoleFactory.ROLE_ADVERTISERS);
        }
        coreMemberInfoDAO.updateMemberType(member);
    }
}
