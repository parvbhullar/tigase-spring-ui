/*
 * Copyright 2011 mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.Constants;
import com.mitian.airad.ErrorMessages;
import com.mitian.airad.dao.CoreResettingPasswordDAO;
import com.mitian.airad.model.CoreResettingPassword;
import com.mitian.airad.utils.StringUtil;

/**
 * ResettingPassword.java
 * 
 * @author leifenghai
 */
@Service
public class ResettingPasswordService {

    @Autowired
    private CoreResettingPasswordDAO coreResettingPasswordDAO;

    /**
     * 添加密码重置（找回密码）数据
     * 
     * @param email 添加人
     * @param memberId 找回密码会员ID
     * @return
     */
    public Map<String, String> txAddCoreResettingPassword(String email, Long memberId, String resettingType, Date time,
            String ciphertext) {
        Map<String, String> result = new HashMap<String, String>();
        if (StringUtil.isEmpty(email)) {
            result.put("verifyCode", ErrorMessages.USER_LACK);
            return result;
        }
        CoreResettingPassword resettingPassword = queryByMemberId(memberId, Constants.PASSWORD_RESETTING_TYPE);
        if (null == resettingPassword) {
            CoreResettingPassword coreResettingPassword = new CoreResettingPassword();
            coreResettingPassword.setMemberId(memberId);
            coreResettingPassword.setResettingTime(time);
            coreResettingPassword.setAddOper(email);
            coreResettingPassword.setAddTime(time);
            coreResettingPassword.setSendCount(1);
            coreResettingPassword.setStatus(Constants.PASSWORD_RESETTING_TYPE);
            coreResettingPassword.setResettingType(resettingType);
            coreResettingPassword.setCiphertext(ciphertext);
            coreResettingPasswordDAO.insertSelective(coreResettingPassword);
        }
        else {
            String dbDate = DateFormatUtils.format(resettingPassword.getResettingTime(), "yyyy-MM-dd");
            String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            if (dbDate.equals(date)) {
                if (resettingPassword.getSendCount() >= 3) {
                    result.put("verifyCode", ErrorMessages.SEND_COUNT_ERR);
                    return result;
                }
                else {
                    resettingPassword.setResettingTime(time);
                    resettingPassword.setSendCount(resettingPassword.getSendCount() + 1);
                    resettingPassword.setStatus(Constants.PASSWORD_RESETTING_TYPE);
                    resettingPassword.setCiphertext(ciphertext);
                    coreResettingPasswordDAO.updateByPrimaryKeySelective(resettingPassword);
                }
            }
            else {
                resettingPassword.setResettingTime(time);
                resettingPassword.setSendCount(1);
                resettingPassword.setStatus(Constants.PASSWORD_RESETTING_TYPE);
                resettingPassword.setCiphertext(ciphertext);
                coreResettingPasswordDAO.updateByPrimaryKeySelective(resettingPassword);
            }
        }
        return result;
    }

    /**
     * 账户密码修改成功后，邮件失效，标示位处理
     */
    public void changeTag(CoreResettingPassword resettingPassword) {
        coreResettingPasswordDAO.updateTag(resettingPassword);
    }

    /**
     *财务密码邮件处理，判断是否已经超过三次
     */

    public Integer txAddSendEmail(CoreResettingPassword resettingPassword) {
        // "1"代表账户账本历史记录
        CoreResettingPassword resetting = coreResettingPasswordDAO.findByMemBerId(resettingPassword.getMemberId(), "1");
        if (null == resetting) {
            // 增加
            // 财务密码找回标示
            resettingPassword.setResettingType(Constants.ACCOUNT_PASS_FIND);
            // 0正常状态1无效
            resettingPassword.setStatus(Constants.PASSWORD_RESETTING_TYPE);
            resettingPassword.setSendCount(1);
            coreResettingPasswordDAO.insertReturnId(resettingPassword);
            return new Integer(1);
        }
        else {
            String dbDate = DateFormatUtils.format(resetting.getAddTime(), "yyyy-MM-dd");
            String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
            if (dbDate.equals(date)) {
                if (resetting.getSendCount() >= 3) {
                    return new Integer(4);
                }
                else {
                    int count = resetting.getSendCount();
                    resetting.setSendCount(count + 1);
                    resetting.setAddOper(resettingPassword.getAddOper());
                    resetting.setAddTime(resettingPassword.getAddTime());
                    resetting.setCiphertext(resettingPassword.getCiphertext());
                    resetting.setStatus(Constants.PASSWORD_RESETTING_TYPE);
                    coreResettingPasswordDAO.updateRestingPasswordHistory(resetting);
                    return new Integer(1);
                }

            }
            else {

                resetting.setSendCount(1);
                resetting.setStatus(Constants.PASSWORD_RESETTING_TYPE);
                resetting.setAddOper(resettingPassword.getAddOper());
                resetting.setAddTime(resettingPassword.getAddTime());
                resetting.setCiphertext(resettingPassword.getCiphertext());
                coreResettingPasswordDAO.updateRestingPasswordHistory(resetting);
                return new Integer(1);
            }

        }
    }

    /**
     * 财务密码找回
     * 
     * @param resettingPassword
     * @return
     */
    public Integer txAddCoreResettingPassword(CoreResettingPassword resettingPassword) {
        CoreResettingPassword resetting = coreResettingPasswordDAO.findByMemBerId(resettingPassword.getMemberId(), "1");
        if (null == resetting) {
            // 增加
            // 财务密码找回标示
            resettingPassword.setResettingType("1");
            // 0正常状态1无效
            resettingPassword.setStatus("0");
            resettingPassword.setSendCount(1);
            return coreResettingPasswordDAO.insertReturnId(resettingPassword);
        }
        else {
            // 更新
            int count = resetting.getSendCount();
            resettingPassword.setSendCount(count++);
            coreResettingPasswordDAO.updateByPrimaryKey(resettingPassword);
            return resetting.getResettingId();
        }

    }

    /**
     * 密码修改成功后将状态设为无效
     * 
     * @param memberId
     * @return
     */
    public Map<String, String> txEditCoreResettingPassword(CoreResettingPassword coreResettingPassword) {
        Map<String, String> result = new HashMap<String, String>();
        coreResettingPassword.setStatus("1");
        coreResettingPasswordDAO.updateByPrimaryKeySelective(coreResettingPassword);
        return result;
    }

    /**
     * 通过条件修改密码邮件地址表
     * 
     * @param coreResettingPassword
     * @return
     */
    public int txUpdateByPrimaryKeySelective(CoreResettingPassword coreResettingPassword) {
        int key = 0;
        key = coreResettingPasswordDAO.updateByPrimaryKeySelective(coreResettingPassword);
        return key;
    }

    /**
     * 根据会员Id及类型查询
     * 
     * @param memberId
     * @param resettingType
     * @return
     */
    public CoreResettingPassword queryByMemberId(Long memberId, String resettingType) {
        CoreResettingPassword coreResettingPassword = new CoreResettingPassword();
        if (memberId > 0) {
            coreResettingPassword = coreResettingPasswordDAO.findByMemBerId(memberId, resettingType);
        }
        return coreResettingPassword;
    }

    public CoreResettingPassword queryByResettingId(int resettingId) {
        CoreResettingPassword coreResettingPassword = coreResettingPasswordDAO.selectByPrimaryKey(resettingId);
        return coreResettingPassword;
    }
}
