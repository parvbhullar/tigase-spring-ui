/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.common.utils.StringUtils;
import com.mitian.airad.dao.AccountInfoViewDAO;
import com.mitian.airad.dao.CoreAgentRelationDAO;
import com.mitian.airad.model.AccountInfoView;
import com.mitian.airad.model.CoreAgentRelation;
import com.mitian.airad.model.CoreMemberInfo;

/**
 * AgentRelationService.java 代理商与广告商的关系业务类
 * 
 * @author WANGZHONGWEI
 */
@Service
public class AgentRelationService {
    /** spring注入CoreAgentRelationDAO */
    @Autowired
    public CoreAgentRelationDAO coreAgentRelationDAO;
    @Autowired
    public AccountInfoViewDAO accountInfoViewDAO;

    /**
     * 根据代理商的id查询所有的广告商
     * 
     * @param coreAgentRelation
     * @return
     */
    public List<CoreMemberInfo> queryList(Long memberId, String email, String devType, int record, int pageSize,
            List<CoreAgentRelation> agentListBean) {
        Map<String, Object> result = new HashMap<String, Object>(3);
        result.put("memberId", memberId);
        result.put("email", email);
        result.put("devType", devType);
        // 得到memberId集合（string）
        StringBuffer list = new StringBuffer();
        for (CoreAgentRelation agentRelation : agentListBean) {
            list.append(agentRelation.getAdvertiserNum() + ",");
        }
        String memberIdList = null;
        if (list.length() > 0) {
            memberIdList = list.substring(0, list.length() - 1);
        }
        else {
            // 不存在设置默认值0（没有数据）
            memberIdList = "0";
        }
        result.put("memberIdList", memberIdList);
        List<CoreMemberInfo> memberInfoList = new ArrayList<CoreMemberInfo>();
        if (StringUtils.isNotBlank(memberId.toString())) {
            // memberInfoList = coreAgentRelationDAO.pageQueryCoreAgentRelationList(result, record, pageSize);
        }
        return memberInfoList;
    }

    /**
     * 根据memberId查询所有广告商
     * 
     * @param map
     * @return
     */
    public List<CoreMemberInfo> queryListForAdv(Map<String, String> map) {

        List<CoreMemberInfo> memberInfoList = coreAgentRelationDAO.pageQueryCoreAgentRelationList(map);
        return memberInfoList;
    }

    /**
     * 根据时间段查询所有广告商（按照收入排序）
     * 
     * @param map
     * @return
     */
    public List<CoreMemberInfo> queryListByTimeSlot(Map<String, String> map) {
        List<CoreMemberInfo> memberInfoList = coreAgentRelationDAO.pageQueryCoreAgentRelationListByTimeSlot(map);
        return memberInfoList;
    }

    /**
     * 根据时间段查询所有广告商（按照收入排序）总数
     * 
     * @param map
     * @return
     */
    public int queryCountByTimeSlot(Map<String, String> map) {
        int count = 0;
        count = coreAgentRelationDAO.pageQueryCountByTimeSlot(map);
        return count;
    }

    /**
     * 根据memberId查询所有广告商
     * 
     * @param memberId
     * @return
     */
    public List<CoreAgentRelation> queryList(Map<String, String> map) {
        List<CoreAgentRelation> advertistor = coreAgentRelationDAO.agentRelationList(map);
        return advertistor;
    }

    /**
     * 根据代理商的id查询所有的广告商
     * 
     * @param coreAgentRelation
     * @return
     */
    public int countByMemberId(Map<String, String> map) {
        int total = 0;
        total = coreAgentRelationDAO.totalCount(map);
        return total;
    }

    /**
     * 根据代理商和广告商关系id查询代理商和广告商对象
     * 
     * @param coreAgentRelation
     * @return
     */
    public CoreAgentRelation getAgentRelationByRelationId(Integer relationId) {
        CoreAgentRelation car = new CoreAgentRelation();
        if (StringUtils.isNotBlank(relationId.toString())) {
            car = coreAgentRelationDAO.selectByPrimaryKey(relationId);
        }
        return car;
    }

    /**
     * 添加代理商与广告商
     * 
     * @param coreAgentRelation
     * @return
     */
    public Map<String, Object> txCreateAgentRelation(CoreAgentRelation coreAgentRelation) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(coreAgentRelation.getMemberId().toString())
                && StringUtils.isNotBlank(coreAgentRelation.getAdvertiserNum().toString())) {
            result.put("coreAgentRelation", coreAgentRelation);
            coreAgentRelationDAO.insertSelective(coreAgentRelation);

        }
        return result;
    }

    /**
     * 修改代理商与广告商
     * 
     * @param coreAgentRelation
     * @return
     */
    public Map<String, Object> txUpdateAgentRelation(CoreAgentRelation coreAgentRelation) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("coreAgentRelation", coreAgentRelation);
        if (StringUtils.isNotBlank(coreAgentRelation.getRelationId().toString())
                && StringUtils.isNotBlank(coreAgentRelation.getAdvertiserNum().toString())
                && StringUtils.isNotBlank(coreAgentRelation.getMemberId().toString())) {
            coreAgentRelationDAO.updateByPrimaryKeySelective(coreAgentRelation);
        }
        return result;
    }

    /**
     * 修改代理商与广告商绑定关系
     * 
     * @param coreAgentRelation
     * @return
     */
    public Map<String, Object> txDeleteAgentRelation(CoreAgentRelation coreAgentRelation) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("coreAgentRelation", coreAgentRelation);
        if (StringUtils.isNotBlank(coreAgentRelation.getRelationId().toString())) {
            coreAgentRelation.setUpdTime(new Date());
            coreAgentRelation.setDelTime(new Date());
            coreAgentRelation.setDelFlag("1");
            coreAgentRelationDAO.updateByPrimaryKeySelective(coreAgentRelation);

        }
        return result;
    }

    /**
     * 根据memberId集合（string）查询视图数据
     * 
     * @param agentListBean
     * @return
     */
    public List<AccountInfoView> queryByMemberId(Map<String, String> map) {
        List<AccountInfoView> accountInfoViewList = new ArrayList<AccountInfoView>();
        accountInfoViewList = accountInfoViewDAO.findByMemberId(map);
        return accountInfoViewList;
    }

    public int totalCount(Map<String, String> map) {
        int k = 0;
        k = accountInfoViewDAO.totalCount(map);
        return k;
    }

}
