/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.CommonDef;
import com.mitian.airad.dao.CoreRechargeHisDAO;
import com.mitian.airad.model.CoreRechargeHis;

/**
 * RechargeHisService.java 充值记录历史表
 * 
 * @author wnagzhongwei
 */
@Service
public class RechargeHisService {
    /** spring注入AppDAO */
    @Autowired
    private CoreRechargeHisDAO coreRechargeHisDAO;

    private static final int CHINAPAY_ORDER_ID_LENGTH = 16;

    public static final String[] RECHARGE_STATE_ARRAY =
            {CommonDef.rechargeHisStatus.CON_RECHARGE_WAIT, CommonDef.rechargeHisStatus.CON_RECHARGE_SUCC,
                    CommonDef.rechargeHisStatus.CON_RECHARGE_CANCEL};

    /**
     * 验证状态查询条件是否有效，无效时返回等待支付状态
     * 
     * @param status
     * @return
     */
    public String getRechargeStatus(String status) {
        String statusTrim = StringUtils.trimToEmpty(status);
        if (ArrayUtils.contains(RECHARGE_STATE_ARRAY, statusTrim)) {
            return statusTrim;
        }
        else {
            return RECHARGE_STATE_ARRAY[0];
        }
    }

    /**
     * 生成16位数字订单id
     * 
     * @return
     */
    public String getChinapayOrderId() {
        String nanoTime = String.valueOf(System.nanoTime());
        if (nanoTime.length() < CHINAPAY_ORDER_ID_LENGTH) {
            int addLength = CHINAPAY_ORDER_ID_LENGTH - nanoTime.length();
            String randomStr = String.valueOf(RandomUtils.nextLong()).substring(0, addLength);
            nanoTime = nanoTime + randomStr;
        }
        if (nanoTime.length() > CHINAPAY_ORDER_ID_LENGTH) {
            nanoTime = nanoTime.substring(0, CHINAPAY_ORDER_ID_LENGTH);
        }
        return nanoTime;
    }

    /**
     * 根据代理商id 查询代理商下所有的充值记录进行分页
     * 
     * @param memberId
     * @return
     */
    public List<CoreRechargeHis> rechargeHisList(Long agentAdderId, String email, int fromRecordHis, int pageSizeHis) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        List<CoreRechargeHis> historyList = new ArrayList<CoreRechargeHis>();
        if (StringUtils.isNotBlank(agentAdderId.toString())) {
            result.put("agentAdderId", agentAdderId);
            result.put("email", email);
            historyList = coreRechargeHisDAO.selectCoreRechargeHisByAgentAdderId(result, fromRecordHis, pageSizeHis);
        }
        return historyList;
    }

    /**
     * 统计历史记录总数
     * 
     * @param agentAdderId
     * @param email
     * @return
     */
    public int totalHis(Long agentAdderId, String email) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        int size = 0;
        if (StringUtils.isNotBlank(agentAdderId.toString())) {
            result.put("agentAdderId", agentAdderId);
            result.put("email", email);
            size = coreRechargeHisDAO.countTotal(result);
        }
        return size;
    }

    // /**
    // * 根据会员id查询会员充值历史记录
    // *
    // * @param memberId
    // * @return
    // */
    // public List<CoreRechargeHis> queryRechargeHisListByMemberId(Long memberId, int currentPage, int pageSize) {
    // List<CoreRechargeHis> rechargeHisList = new ArrayList<CoreRechargeHis>();
    // if (StringUtils.isNotBlank(memberId.toString())) {
    // rechargeHisList = coreRechargeHisDAO.selectRechargeHisByMemberId(memberId, currentPage, pageSize);
    // }
    // return rechargeHisList;
    // }
    /**
     * 根据会员id查询会员充值历史记录
     * 
     * @param memberId
     * @return
     */
    public List<CoreRechargeHis> queryRechargeHisListByMemberId(Long memberId, int currentPage, int pageSize,
            String flag, String desc) {
        List<CoreRechargeHis> rechargeHisList = new ArrayList<CoreRechargeHis>();
        if (StringUtils.isNotBlank(memberId.toString())) {
            rechargeHisList =
                    coreRechargeHisDAO.selectRechargeHisByMemberId(memberId, currentPage, pageSize, flag, desc);
        }
        return rechargeHisList;
    }

    public int updateByPrimaryKeySelective(int id) {
        CoreRechargeHis coreRechargeHis = new CoreRechargeHis();
        coreRechargeHis.setId(id);
        coreRechargeHis.setRechargeStatus("2");
        return coreRechargeHisDAO.updateByPrimaryKeyStatus(coreRechargeHis);
    }

    /**
     * 报表导出
     * 
     * @param memberId
     * @return
     */
    public List<CoreRechargeHis> queryRechargeHisListByMemberId(Long memberId, String status) {
        List<CoreRechargeHis> rechargeHisList = new ArrayList<CoreRechargeHis>();
        if (StringUtils.isNotBlank(memberId.toString())) {
            rechargeHisList = coreRechargeHisDAO.selectRechargeHisByMemberId(memberId, status);
        }
        return rechargeHisList;
    }

    // /**
    // * 根据会员id查询提现历史记录的数量
    // *
    // * @param memberId
    // * @return
    // */
    // public int selectCountByMemberId(Long memberId) {
    // int count = 0;
    // if (StringUtils.isNotBlank(memberId.toString())) {
    // count = coreRechargeHisDAO.selectCountByMemberId(memberId);
    // }
    // return count;
    // }
    /**
     * 根据会员id查询提现历史记录的数量
     * 
     * @param memberId
     * @return
     */
    public int selectCountByMemberId(Long memberId, String status) {
        int count = 0;
        if (StringUtils.isNotBlank(memberId.toString()) && StringUtils.isNotEmpty(status)) {
            CoreRechargeHis coreRechargeHis = new CoreRechargeHis();
            coreRechargeHis.setMemberId(memberId);
            coreRechargeHis.setRechargeStatus(status);
            count = coreRechargeHisDAO.selectCountByMemberId(coreRechargeHis);
        }
        return count;
    }

    /**
     * 添加充值记录信息
     * 
     * @param coreAdGroup
     * @return
     */
    public Map<String, Object> txCreateRechargeHis(CoreRechargeHis coreRechargeHis) {
        Map<String, Object> result = new HashMap<String, Object>(1);
        if (StringUtils.isNotBlank(coreRechargeHis.getMemberId().toString())
                && StringUtils.isNotBlank(coreRechargeHis.getAgentAdderId().toString())) {
            result.put("coreRechargeHis", coreRechargeHis);
            coreRechargeHisDAO.insertSelective(coreRechargeHis);
        }
        return result;

    }

    /**
     * 创建银联支付记录
     * 
     * @param coreRechargeHis
     */
    public void txCreateChinaPayRecord(CoreRechargeHis coreRechargeHis) {
        coreRechargeHis.setRechargeType(CommonDef.rechargeHisType.CON_CHINAPAY);
        coreRechargeHis.setRechargeStatus(CommonDef.rechargeHisStatus.CON_RECHARGE_WAIT);
        coreRechargeHisDAO.insertSelective(coreRechargeHis);
    }

    /**
     * 资金转换
     * 
     * @param coreAdGroup
     * @return
     */
    public void txCreateRecharge(CoreRechargeHis coreRechargeHis) {
        coreRechargeHisDAO.insertSelective(coreRechargeHis);

    }

    /**
     * 银联充值历史记录更新
     * 
     * @param coreRechargeHis
     */
    public void txUpdateRecharge(CoreRechargeHis coreRechargeHis) {
        coreRechargeHisDAO.updateByPrimaryKeySelective(coreRechargeHis);

    }

    /**
     * 获取充值记录，银联回调验证查询使用
     * 
     * @param serialNum
     * @return
     */
    public CoreRechargeHis queryRechargeHisByOrderId(String serialNum) {

        return coreRechargeHisDAO.selectRechargeHisByOrderId(serialNum);
    }

    /**
     * 获取充值记录,继续支付验证使用
     * 
     * @param serialNum
     * @param memberId
     * @return
     */
    public CoreRechargeHis getCoreRechargeHisByOrderIdAndMemberId(String serialNum, Long memberId) {
        return coreRechargeHisDAO.getCoreRechargeHisByOrderIdAndMemberId(serialNum, memberId);

    }

    /**
     * 根据时间顺序查询充值历史记录
     */
    public List<CoreRechargeHis> queryList(Map<String, Object> params) {

        return coreRechargeHisDAO.queryRecHisOrderByTime(params);
    }

    /**
     * 充值订单取消
     * 
     * @param serialNum
     * @param memberId
     * @return
     */
    public int txCancelRechargeOrder(String serialNum, Long memberId) {
        CoreRechargeHis coreRechargeHis = new CoreRechargeHis();
        coreRechargeHis.setSerialNum(serialNum);
        coreRechargeHis.setMemberId(memberId);
        coreRechargeHis.setRechargeStatus(CommonDef.rechargeHisStatus.CON_RECHARGE_CANCEL);
        return coreRechargeHisDAO.updateRechargeOrderStatus(coreRechargeHis);
    }

}
