/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.common.codec.EncryptService;
import com.mitian.airad.dao.AccountInfoViewDAO;
import com.mitian.airad.dao.CoreAccountInfoDAO;
import com.mitian.airad.model.AccountInfoView;
import com.mitian.airad.model.CoreAccountInfo;

/**
 * 会员账户信息
 * 
 * @author chenji
 */
@Service
public class AccountInfoService {
    /** spring注入CoreAdGroupDAO */
    @Autowired
    private CoreAccountInfoDAO coreAccountInfoDao;
    @Autowired
    private EncryptService encryptService;
    @Autowired
    private AccountInfoViewDAO accountInfoViewDAO;

    /**
     * 根据会员id查询会员账户信息
     * 
     * @param memberId
     * @return 会员账户信息
     */
    public CoreAccountInfo selectAccountInfoByMemberId(Long memberId) {
        CoreAccountInfo coreAccountInfo = coreAccountInfoDao.selectAccountInfoByMemberId(memberId);
        return coreAccountInfo;
    }

    /**
     * 可选的修改用户账户信息
     * 
     * @param record
     * @return
     */
    public int txEditAccountInfo(CoreAccountInfo record) {
        int key = 0;
        key = coreAccountInfoDao.updateByPrimaryKeySelective(record);
        return key;
    }

    /**
     * 根据会员ID查找余额
     * 
     * @param memberId
     * @return
     */
    public AccountInfoView queryAccountInfoById(Long memberId) {
        AccountInfoView accountInfoView = accountInfoViewDAO.findByAccountInfo(memberId);
        return accountInfoView;
    }

    /**
     * 增加账户信息
     * 
     * @param record
     */
    public int txAddAccountInfo(CoreAccountInfo record) {
        return coreAccountInfoDao.insertSelective(record);
    }

    /***
     * 账户密码初始设定：判断密码是否为登录密码
     */
    public boolean jugementPassword() {

        return false;
    }

    /**
     * 判断用户账户密码是否正确
     * 
     * @param record
     * @return
     */
    public boolean judgePassword(CoreAccountInfo record) {
        boolean passwordResult = false;
        // 查询用户账户信息
        CoreAccountInfo coreAccountInfo =
                coreAccountInfoDao.selectAccountInfoByMemberId(Long.valueOf(record.getMemberId()));
        if (coreAccountInfo != null) {
            // 判断传递过来的密码和数据库中的密码是否相同，如果相同，执行修改密码操作
            if (encryptService.matches(record.getPassword(), coreAccountInfo.getPassword(),
                    EncryptService.DIGEST_ALGORITHM.MD5.name())) {
                passwordResult = true;
                return passwordResult;
            }

        }
        else {
            // 如果是第一次设置账务密码：往数据库插入账户 财务信息，密码由系统设定默认密码：会员密码与财务密码一致 插入字段(memberId,password)
            CoreAccountInfo cai = new CoreAccountInfo();
            cai.setMemberId(record.getMemberId());

            String initPassword = record.getInitPassword();
            cai.setPassword(initPassword);
            int suc = coreAccountInfoDao.insertSelective(cai);

            // 回调:判断用户输入旧密码与系统默认密码是否一致
            judgePassword(record);
        }

        return passwordResult;
    }

    /**
     * 修改账户密码
     * 
     * @param record
     * @return 用户Id
     */
    public int txUpdateMemberPassword(CoreAccountInfo record) {
        int key = 0;
        record.setPassword(encryptService.digest(record.getPassword(), EncryptService.DIGEST_ALGORITHM.MD5.name()));
        key = coreAccountInfoDao.updateByMemberIdSelective(record);
        return key;
    }

    /**
     * @return the coreAccountInfoDao
     */
    public CoreAccountInfoDAO getCoreAccountInfoDao() {
        return coreAccountInfoDao;
    }

    /**
     * @param coreAccountInfoDao the coreAccountInfoDao to set
     */
    public void setCoreAccountInfoDao(CoreAccountInfoDAO coreAccountInfoDao) {
        this.coreAccountInfoDao = coreAccountInfoDao;
    }

    /**
     * @return the encryptService
     */
    public EncryptService getEncryptService() {
        return encryptService;
    }

    /**
     * @param encryptService the encryptService to set
     */
    public void setEncryptService(EncryptService encryptService) {
        this.encryptService = encryptService;
    }

    /**
     * @return the accountInfoViewDAO
     */
    public AccountInfoViewDAO getAccountInfoViewDAO() {
        return accountInfoViewDAO;
    }

    /**
     * @param accountInfoViewDAO the accountInfoViewDAO to set
     */
    public void setAccountInfoViewDAO(AccountInfoViewDAO accountInfoViewDAO) {
        this.accountInfoViewDAO = accountInfoViewDAO;
    }
}
